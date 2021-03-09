package com.phoebus.library.librarymicroservicepurchase.purchase.service;

import com.phoebus.library.librarymicroservicepurchase.feign.GetBook;
import com.phoebus.library.librarymicroservicepurchase.feign.GetUserLibrary;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.utils.ReturnAllPurchases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListPurchaseServiceImpl implements ListPurchaseService{
    private final ReturnAllPurchases returnAllPurchases;
    private final GetBook getBook;
    private final GetUserLibrary getUserLibrary;

    @Override
    public List<PurchaseReturnDTO> listAllPurchases() {
        return returnAllPurchases.findAllPurchase(getBook,getUserLibrary);
    }
}
