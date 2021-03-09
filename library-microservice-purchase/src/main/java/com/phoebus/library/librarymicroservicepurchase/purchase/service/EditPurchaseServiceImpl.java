package com.phoebus.library.librarymicroservicepurchase.purchase.service;

import com.phoebus.library.librarymicroservicepurchase.exceptions.PurchaseNotFoundException;
import com.phoebus.library.librarymicroservicepurchase.purchase.Purchase;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditPurchaseServiceImpl implements EditPurchaseService{

    private final PurchaseRepository repository;

    @Override
    public void editPurchase(Long id) {
        Purchase purchase = repository.findById(id).orElseThrow(PurchaseNotFoundException::new);

        purchase.setPurchaseCompleted(true);
        repository.save(purchase);

    }
}
