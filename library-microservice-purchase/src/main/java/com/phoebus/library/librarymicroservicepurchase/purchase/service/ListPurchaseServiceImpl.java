package com.phoebus.library.librarymicroservicepurchase.purchase.service;

import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetBook;
import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetUserLibrary;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.utils.ReturnAllPurchases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListPurchaseServiceImpl implements ListPurchaseService{
    private final ReturnAllPurchases returnAllPurchases;
    @Override
    public List<PurchaseReturnDTO> listAllPurchases() {
        return returnAllPurchases.findAllPurchase();
    }
}
