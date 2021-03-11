package com.phoebus.library.librarymicroservicepurchase;

import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetBook;
import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetUserLibrary;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.ListPageServiceImpl;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.utils.ReturnAllPurchases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.phoebus.library.librarymicroservicepurchase.builders.PurchaseReturnBuilderDTO.createPurchaseReturnDTO;
import static com.phoebus.library.librarymicroservicepurchase.builders.UserLibraryBuilderDTO.createUserLibraryDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Test to verify if could list page of purchases")
public class ListPagePurchaseServiceTest {
    @Mock
    private ReturnAllPurchases returnAllPurchases;

    @Mock
    private FeignGetUserLibrary feignGetUserLibrary;

    @Mock
    private FeignGetBook feignGetBook;

    private ListPageServiceImpl listPageServiceImpl;

    @BeforeEach
    void setUp() {
        this.listPageServiceImpl = new ListPageServiceImpl(feignGetBook,feignGetUserLibrary,returnAllPurchases);
    }

    @Test
    @DisplayName("listAll returns list of purchase inside page object when successful")
    void shouldListPageOfPurchaseWhenSuccessful() {

        Pageable pageable = PageRequest.of(0,2);

        when(returnAllPurchases.findAllPurchase()).thenReturn(Stream.of(
                createPurchaseReturnDTO().specificID("69661bd1-6092-4068-bd28-c60517f8a16a").build(),
                createPurchaseReturnDTO().specificID("69661bd1-6092-4068-bd28-c60517f8a16b").build()).collect(Collectors.toList())
        );

        lenient().when(feignGetUserLibrary.findSpecificID(anyString())).thenReturn(createUserLibraryDTO().build());

        Page<PurchaseReturnDTO> result = this.listPageServiceImpl.listPagePurchase(pageable);

        assertAll("Purchase",
                ()-> assertThat(result.getNumber(), is(0)),
                ()-> assertThat(result.getSize(), is(2)),
                ()-> assertThat(result.getContent().get(0).getSpecificID(), is("69661bd1-6092-4068-bd28-c60517f8a16a")),
                ()-> assertThat(result.getContent().get(0).getCustomer().getSpecificID(), is("69661bd1-6092-4068-bd28-c60517f8a16b")),
                ()-> assertThat(result.getContent().get(0).getShoppingList().get(0).getSpecificID(), is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")),
                ()-> assertThat(result.getContent().get(0).isPurchaseCompleted(), is(false))
        );

        verify(returnAllPurchases).findAllPurchase();
    }
}
