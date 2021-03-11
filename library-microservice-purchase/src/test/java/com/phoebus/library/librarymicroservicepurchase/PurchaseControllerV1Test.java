package com.phoebus.library.librarymicroservicepurchase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.phoebus.library.librarymicroservicepurchase.exceptions.PurchaseNotFoundException;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseSaveDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.DeletePurchaseService;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.EditPurchaseService;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.GetPurchaseService;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.ListPageService;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.ListPurchaseService;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.SavePurchaseService;
import com.phoebus.library.librarymicroservicepurchase.purchase.v1.PurchaseControllerV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static com.phoebus.library.librarymicroservicepurchase.builders.PurchaseReturnBuilderDTO.createPurchaseReturnDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PurchaseControllerV1.class)
@DisplayName("Verify if the controller can do all tasks")
public class PurchaseControllerV1Test {

    private final String URL_PURCHASE = "/v1/purchase";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeletePurchaseService deletePurchaseService;

    @MockBean
    private GetPurchaseService getPurchaseService;

    @MockBean
    private ListPurchaseService listPurchaseService;

    @MockBean
    private ListPageService listPageService;

    @MockBean
    private SavePurchaseService savePurchaseService;

    @MockBean
    private EditPurchaseService editPurchaseService;

    @Test
    @DisplayName("Should get Purchase by id")
    void shouldGetPurchaseById() throws Exception {
        when(getPurchaseService.getPurchase(anyLong())).thenReturn(createPurchaseReturnDTO().build());

        mockMvc.perform(get(URL_PURCHASE + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.customer.specificID", is("69661bd1-6092-4068-bd28-c60517f8a16b")))
                .andExpect(jsonPath("$.shoppingList.[0].specificID", is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")))
                .andExpect(jsonPath("$.priceToPay", is(150.2)))
                .andExpect(jsonPath("$.purchaseCompleted", is(false)));

        verify(getPurchaseService).getPurchase(anyLong());
    }

    @Test
    @DisplayName("findById throws PurchaseNotFoundException when purchase is not found")
    void shouldNotGetPurchaseById() throws Exception {

        when(getPurchaseService.getPurchase(anyLong())).thenThrow(new PurchaseNotFoundException());

        mockMvc.perform(get(URL_PURCHASE + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getPurchaseService).getPurchase(1L);
    }

    @Test
    @DisplayName("listAll returns list of purchase when successful")
    void shouldListPurchase() throws Exception {

        when(listPurchaseService.listAllPurchases()).thenReturn(Lists.newArrayList(
                createPurchaseReturnDTO().id(1L).build(),
                createPurchaseReturnDTO().id(2L).build()
        ));

        mockMvc.perform(get(URL_PURCHASE).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].customer.specificID", is("69661bd1-6092-4068-bd28-c60517f8a16b")))
                .andExpect(jsonPath("$[0].shoppingList.[0].specificID", is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")))
                .andExpect(jsonPath("$[0].priceToPay", is(150.2)))
                .andExpect(jsonPath("$[0].purchaseCompleted", is(false)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].customer.specificID", is("69661bd1-6092-4068-bd28-c60517f8a16b")))
                .andExpect(jsonPath("$[1].shoppingList.[0].specificID", is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")))
                .andExpect(jsonPath("$[1].priceToPay", is(150.2)))
                .andExpect(jsonPath("$[1].purchaseCompleted", is(false)));

        verify(listPurchaseService).listAllPurchases();
    }

    @Test
    @DisplayName("listAll returns list of purchase inside page object when successful")
    void shouldListPageOfPurchase() throws Exception{

        Page<PurchaseReturnDTO> purchasePage = new PageImpl<>(Collections.singletonList(createPurchaseReturnDTO().build()));

        Pageable pageable = PageRequest.of(0,2);

        when(listPageService.listPagePurchase(pageable)).thenReturn(purchasePage);

        mockMvc.perform(get(URL_PURCHASE + "/?page=0&size=2").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].customer.specificID", is("69661bd1-6092-4068-bd28-c60517f8a16b")))
                .andExpect(jsonPath("$.content[0].shoppingList.[0].specificID", is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")))
                .andExpect(jsonPath("$.content[0].priceToPay", is(150.2)))
                .andExpect(jsonPath("$.content[0].purchaseCompleted", is(false)));

        verify(listPageService).listPagePurchase(pageable);
    }
    @Test
    @DisplayName("Should save purchase when successful")
    void saveReturnsPurchaseWhenSuccessful() throws Exception {
        mockMvc.perform(post(URL_PURCHASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("purchaseSaveDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(savePurchaseService).save(any(PurchaseSaveDTO.class));
    }

    @Test
    @DisplayName("Should edit a purchase")
    void shouldEditPurchaseToCompleted() throws Exception{
        mockMvc.perform(put(URL_PURCHASE + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(editPurchaseService).editPurchase(eq(1L));
    }

    @Test
    @DisplayName("Should delete a purchase when successful")
    void shouldDeletePurchaseWhenSuccessful() throws Exception{
        mockMvc.perform(delete(URL_PURCHASE + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deletePurchaseService).delete(anyLong());
    }

    public static String readJson(String file) throws Exception {
            byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/dataJson/" + file).toAbsolutePath());
            return new String(bytes);
        }
}
