package com.phoebus.library.librarymicroservicepurchase;

import com.phoebus.library.librarymicroservicepurchase.exceptions.PurchaseNotFoundException;
import com.phoebus.library.librarymicroservicepurchase.purchase.Purchase;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseRepository;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.EditPurchaseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.phoebus.library.librarymicroservicepurchase.builders.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test to verify edit purchase service")
public class EditPurchaseServiceTest {
    @Mock
    private PurchaseRepository repository;

    private EditPurchaseServiceImpl editPurchaseServiceImpl;

    @BeforeEach
    void setUp() {
        this.editPurchaseServiceImpl = new EditPurchaseServiceImpl(repository);
    }

    @Test
    @DisplayName("Should edit a purchase")
    void shouldEditPurchase() {
        when(repository.findById(1L)).thenReturn(Optional.of(createPurchase().build()));
        editPurchaseServiceImpl.editPurchase(1L);

        ArgumentCaptor<Purchase> purchaseArgumentCaptor = ArgumentCaptor.forClass(Purchase.class);
        verify(repository).save(purchaseArgumentCaptor.capture());

        Purchase result = purchaseArgumentCaptor.getValue();

        assertAll("Purchase",
                () -> assertThat(result.getSpecificID(), is("5edc11dd-2017-4c20-9d89-cc96970435cb")),
                () -> assertThat(result.isPurchaseCompleted(), is(true))
        );
    }
    @Test
    @DisplayName("Should not edit a purchase")
    void shouldNotEditPurchase() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(PurchaseNotFoundException.class,() -> editPurchaseServiceImpl.editPurchase(1L));

        verify(repository,times(0)).save(ArgumentMatchers.any(Purchase.class));
    }
}