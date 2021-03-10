package com.phoebus.library.librarymicroservicepurchase.purchase.service;


import com.phoebus.library.librarymicroservicepurchase.exceptions.PurchaseNotFoundException;
import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetBook;
import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetUserLibrary;
import com.phoebus.library.librarymicroservicepurchase.purchase.BookDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.Purchase;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseRepository;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.UserLibraryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetPurchaseServiceImpl implements GetPurchaseService{

    private final PurchaseRepository repository;
    private final FeignGetBook feignGetBook;
    private final FeignGetUserLibrary feignGetUserLibrary;

    @Override
    public PurchaseReturnDTO getPurchase(Long id) {
        Purchase purchase = repository.findById(id).orElseThrow(PurchaseNotFoundException::new);
        UserLibraryDTO userLibraryDTO = feignGetUserLibrary.findSpecificID(purchase.getSpecificIdUserLibrary());
        String[] purchasedBookID = purchase.getSpecificIdBooks().split(",");
        List<BookDTO> shoppingListBookFound = new ArrayList<>();
        for(String book: purchasedBookID) {
            shoppingListBookFound.add(feignGetBook.findSpecificID(book));
        }

        return PurchaseReturnDTO.from(purchase,userLibraryDTO,shoppingListBookFound);
    }
}
