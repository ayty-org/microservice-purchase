package com.phoebus.library.librarymicroservicepurchase.builders;

import com.phoebus.library.librarymicroservicepurchase.purchase.Purchase;

public class PurchaseBuilder {
    public static Purchase.Builder createPurchase(){
        return Purchase
                .builder()
                .id(1L)
                .priceToPay(150.2)
                .specificID("5edc11dd-2017-4c20-9d89-cc96970435cb")
                .specificIdBooks("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")
                .specificIdUserLibrary("69661bd1-6092-4068-bd28-c60517f8a16b")
                .purchaseCompleted(false);
    }
}

