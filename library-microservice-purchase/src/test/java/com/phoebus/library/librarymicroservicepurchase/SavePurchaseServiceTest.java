package com.phoebus.library.librarymicroservicepurchase;

import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetBook;
import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetUserLibrary;
import com.phoebus.library.librarymicroservicepurchase.purchase.Purchase;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseRepository;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.SavePurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.phoebus.library.librarymicroservicepurchase.builders.PurchaseSaveBuilderDTO.createPurchaseSave;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Test to verify if could save a purchase")
public class SavePurchaseServiceTest {

    @Mock
    private PurchaseRepository repository;

    @Mock
    private FeignGetBook feignGetBook;

    @Mock
    private FeignGetUserLibrary feignGetUserLibrary;

    private SavePurchaseServiceImpl savePurchaseServiceImpl;

    @BeforeEach
    void setUp() {
        this.savePurchaseServiceImpl = new SavePurchaseServiceImpl(repository, feignGetBook, feignGetUserLibrary);
    }

    @Test
    @DisplayName("save returns purchase when successful")
    void SaveReturnsPurchaseWhenSuccessful() {
        savePurchaseServiceImpl.save(createPurchaseSave().build());

        ArgumentCaptor<Purchase> purchaseArgumentCaptor = ArgumentCaptor.forClass(Purchase.class);
        verify(repository).save(purchaseArgumentCaptor.capture());

        Purchase result = purchaseArgumentCaptor.getValue();

        assertAll( "Purchase",
                ()-> assertThat(result.getSpecificIdBooks(), is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949,")),
                ()-> assertThat(result.getSpecificIdUserLibrary(), is("69661bd1-6092-4068-bd28-c60517f8a16b")),
                ()-> assertThat(result.getPriceToPay(), is(150.2)),
                ()-> assertThat(result.isPurchaseCompleted(), is(false))
        );
    }
}
