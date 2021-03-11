package com.phoebus.library.librarymicroservicepurchase.builders;

import com.phoebus.library.librarymicroservicepurchase.purchase.BookDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;

import java.util.ArrayList;
import java.util.List;

import static com.phoebus.library.librarymicroservicepurchase.builders.BookBuilderDTO.createBookDTO;
import static com.phoebus.library.librarymicroservicepurchase.builders.UserLibraryBuilderDTO.createUserLibraryDTO;

public class PurchaseReturnBuilderDTO {
    public static PurchaseReturnDTO.Builder createPurchaseReturnDTO() {
        List<BookDTO> bookDTOList = new ArrayList<>();
        bookDTOList.add(createBookDTO().build());

        return PurchaseReturnDTO.builder()
                .id(1L)
                .specificID("5edc11dd-2017-4c20-9d89-cc96970435cb")
                .customer(createUserLibraryDTO().build())
                .priceToPay(150.2)
                .shoppingList(bookDTOList)
                .purchaseCompleted(false);

    }
}
