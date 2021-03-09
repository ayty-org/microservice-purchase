package com.phoebus.library.librarymicroservicepurchase.purchase.service;

import com.phoebus.library.librarymicroservicepurchase.exceptions.PurchaseNotFoundException;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeletePurchaseServiceImpl implements DeletePurchaseService{

    private final PurchaseRepository repository;

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new PurchaseNotFoundException();
        }
        repository.deleteById(id);
    }
}
