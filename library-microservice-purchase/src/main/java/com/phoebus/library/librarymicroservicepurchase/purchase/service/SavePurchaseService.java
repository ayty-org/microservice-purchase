package com.phoebus.library.librarymicroservicepurchase.purchase.service;

import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseSaveDTO;

@FunctionalInterface
public interface SavePurchaseService {
    void save(PurchaseSaveDTO purchaseSaveDTO);
}
