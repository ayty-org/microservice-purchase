package com.phoebus.library.librarymicroservicepurchase.purchase.service;

import com.phoebus.library.librarymicroservicepurchase.exceptions.BookNotFoundException;
import com.phoebus.library.librarymicroservicepurchase.exceptions.UserLibraryNotFoundException;
import com.phoebus.library.librarymicroservicepurchase.feign.GetBook;
import com.phoebus.library.librarymicroservicepurchase.feign.GetUserLibrary;
import com.phoebus.library.librarymicroservicepurchase.purchase.Purchase;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseRepository;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseSaveDTO;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SavePurchaseServiceImpl implements SavePurchaseService {

    private final PurchaseRepository repository;
    private final GetBook getBook;
    private final GetUserLibrary getUserLibrary;

    @Override
    public void save(PurchaseSaveDTO purchaseSaveDTO) {
        String booksID = "";
        try {
            getUserLibrary.findSpecificID(purchaseSaveDTO.getSpecificIdUserLibrary());
        } catch (FeignException.NotFound requisition) {
            throw new UserLibraryNotFoundException(requisition.getMessage());
        }

        try {
            for (String books : purchaseSaveDTO.getSpecificIdBooks()) {
                getBook.findSpecificID(books);
                booksID += books;
                booksID += ",";
            }
        } catch (FeignException.NotFound requisition) {
            throw new BookNotFoundException(requisition.getMessage());
        }

        purchaseSaveDTO.setPurchaseCompleted(false);

        Purchase purchase = Purchase.to(purchaseSaveDTO, booksID);

        repository.save(purchase);
    }
}
