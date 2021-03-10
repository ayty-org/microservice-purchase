package com.phoebus.library.librarymicroservicepurchase.purchase.service.utils;

import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetBook;
import com.phoebus.library.librarymicroservicepurchase.purchase.BookDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReturnListBookOfFeign {
    
    private final FeignGetBook feignGetBook;

    public List<BookDTO> findAllFeign(Purchase purchase) {
        String[] purchasedBookID = purchase.getSpecificIdBooks().split(",");
        List<BookDTO> shoppingListBookFound = new ArrayList<>();
        for(String book: purchasedBookID) {
            shoppingListBookFound.add(feignGetBook.findSpecificID(book));
        }
        return shoppingListBookFound;
    }

}
