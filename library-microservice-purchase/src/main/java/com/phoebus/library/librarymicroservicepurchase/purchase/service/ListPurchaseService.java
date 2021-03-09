package com.phoebus.library.librarymicroservicepurchase.purchase.service;

import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;

import java.util.List;

@FunctionalInterface
public interface ListPurchaseService {
    List<PurchaseReturnDTO> listAllPurchases();
}
