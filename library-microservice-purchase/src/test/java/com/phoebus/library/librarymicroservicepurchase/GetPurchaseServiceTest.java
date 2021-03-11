package com.phoebus.library.librarymicroservicepurchase;

import com.phoebus.library.librarymicroservicepurchase.exceptions.PurchaseNotFoundException;
import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetBook;
import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetUserLibrary;
import com.phoebus.library.librarymicroservicepurchase.purchase.BookDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.Purchase;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseRepository;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.GetPurchaseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.phoebus.library.librarymicroservicepurchase.builders.BookBuilderDTO.createBookDTO;
import static com.phoebus.library.librarymicroservicepurchase.builders.PurchaseBuilder.createPurchase;
import static com.phoebus.library.librarymicroservicepurchase.builders.UserLibraryBuilderDTO.createUserLibraryDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify get purchase service")
public class GetPurchaseServiceTest {

    @Mock
    private PurchaseRepository repository;

    @Mock
    private FeignGetUserLibrary feignGetUserLibrary;

    @Mock
    private FeignGetBook feignGetBook;

    private GetPurchaseServiceImpl getPurchaseServiceImpl;

    @BeforeEach
    void setUp() {
        this.getPurchaseServiceImpl = new GetPurchaseServiceImpl(repository,feignGetBook,feignGetUserLibrary);
    }

    @Test
    @DisplayName("Should get a purchase")
    void shouldGetPurchase() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(createPurchase().build()));

        when(feignGetUserLibrary.findSpecificID(anyString())).thenReturn(createUserLibraryDTO().build());

        BookDTO bookDTO = createBookDTO().build();

        when(feignGetBook.findSpecificID(anyString())).thenReturn(bookDTO);

        PurchaseReturnDTO result = this.getPurchaseServiceImpl.getPurchase(1L);

        assertAll("Purchase",
                () -> assertThat(result.getCustomer().getSpecificID(),is("69661bd1-6092-4068-bd28-c60517f8a16b")),
                () -> assertThat(result.getShoppingList().get(0).getSpecificID(), is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")),
                () -> assertThat(result.getPriceToPay(), is(150.2)),
                () -> assertThat(result.isPurchaseCompleted(), is(false))
        );

        verify(repository).findById(anyLong());

    }
    @Test
    @DisplayName("Should not get a purchase")
    void shouldNotGetPurchase() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(PurchaseNotFoundException.class, () -> getPurchaseServiceImpl.getPurchase(1L));

        verify(repository).findById(anyLong());
    }
}