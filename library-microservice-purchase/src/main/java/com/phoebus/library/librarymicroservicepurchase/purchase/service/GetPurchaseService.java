package com.phoebus.library.librarymicroservicepurchase.purchase.service;

import com.phoebus.library.librarymicroservicepurchase.purchase.Purchase;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;

@FunctionalInterface
public interface GetPurchaseService {
    public PurchaseReturnDTO getPurchase(Long id);
}
