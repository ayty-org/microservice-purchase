package com.phoebus.library.librarymicroservicepurchase.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class PurchaseReturnDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private UserLibraryDTO customer;

    private String specificID;

    private List<BookDTO> shoppingList;

    private double priceToPay;

    private boolean purchaseCompleted;

    public static PurchaseReturnDTO from(Purchase entity, UserLibraryDTO customer, List<BookDTO> shoppingList) {
        return PurchaseReturnDTO
                .builder()
                .id(entity.getId())
                .customer(customer)
                .shoppingList(shoppingList)
                .specificID(entity.getSpecificID())
                .priceToPay(entity.getPriceToPay())
                .purchaseCompleted(entity.isPurchaseCompleted())
                .build();
    }
}
