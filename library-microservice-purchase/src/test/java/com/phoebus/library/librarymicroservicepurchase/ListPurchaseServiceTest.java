package com.phoebus.library.librarymicroservicepurchase;

import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetBook;
import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetUserLibrary;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.ListPurchaseServiceImpl;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.utils.ReturnAllPurchases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.phoebus.library.librarymicroservicepurchase.builders.PurchaseReturnBuilderDTO.createPurchaseReturnDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Test to verify if could list all purchases")
public class ListPurchaseServiceTest {
    @Mock
    private ReturnAllPurchases returnAllPurchases;

    @Mock
    private FeignGetUserLibrary feignGetUserLibrary;

    @Mock
    private FeignGetBook feignGetBook;

    private ListPurchaseServiceImpl listPurchaseServiceImpl;

    @BeforeEach
    void setUp() {
        this.listPurchaseServiceImpl = new ListPurchaseServiceImpl(returnAllPurchases);
    }

    @Test
    @DisplayName("Should return a list with all purchases")
    void shouldReturnListOfPurchaseWhenSuccessful() {
        when(returnAllPurchases.findAllPurchase()).thenReturn(Stream.of(createPurchaseReturnDTO().build()).collect(Collectors.toList()));

        List<PurchaseReturnDTO> result = this.listPurchaseServiceImpl.listAllPurchases();

        assertAll( "Purchase",
                ()-> assertThat(result.size(), is(1)),
                ()-> assertThat(result.get(0).getCustomer().getSpecificID(), is("69661bd1-6092-4068-bd28-c60517f8a16b")),
                ()-> assertThat(result.get(0).getShoppingList().get(0).getSpecificID(), is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")),
                ()-> assertThat(result.get(0).getPriceToPay(), is(150.2)),
                ()-> assertThat(result.get(0).isPurchaseCompleted(), is(false))
        );

        verify(returnAllPurchases, times(1)).findAllPurchase();
    }
}
