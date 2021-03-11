package com.phoebus.library.librarymicroservicepurchase.purchase.service.utils;

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

@Service
@RequiredArgsConstructor
public class ReturnAllPurchases {
    private final PurchaseRepository repository;
    private final ReturnListBookOfFeign returnListBookOfFeign;
    private final FeignGetBook feignGetBook;
    private final FeignGetUserLibrary feignGetUserLibrary;

    public List<PurchaseReturnDTO> findAllPurchase() {
        List<PurchaseReturnDTO> purchaseReturnDTOS = new ArrayList<>();
        for(Purchase purchase: repository.findAll()){
            UserLibraryDTO userLibraryDTO = feignGetUserLibrary.findSpecificID(purchase.getSpecificIdUserLibrary());
            String[] specificsIdBook = purchase.getSpecificIdBooks().split(",");
            List<BookDTO> shoppingListBookFounded = new ArrayList<>();
            for(String book: specificsIdBook) {
                shoppingListBookFounded.add(feignGetBook.findSpecificID(book));
            }
            purchaseReturnDTOS.add(PurchaseReturnDTO.from(purchase,userLibraryDTO,shoppingListBookFounded));
        }
        return purchaseReturnDTOS;
    }
}

