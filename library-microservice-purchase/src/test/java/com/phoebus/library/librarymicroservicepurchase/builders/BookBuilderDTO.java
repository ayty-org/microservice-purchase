package com.phoebus.library.librarymicroservicepurchase.builders;

import com.phoebus.library.librarymicroservicepurchase.purchase.BookDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.CategoryOfBookDTO;

import java.util.HashSet;
import java.util.Set;

import static com.phoebus.library.librarymicroservicepurchase.builders.CategoryOfBookBuilderDTO.createCategoryOfBookDTO;

public class BookBuilderDTO {
    public static BookDTO.Builder createBookDTO() {
        Set<CategoryOfBookDTO> categoryOfBookSet = new HashSet<>();
        categoryOfBookSet.add(createCategoryOfBookDTO().build());

        return BookDTO.builder()
                .title("teste book")
                .synopsis("test")
                .isbn("0000")
                .author("teste")
                .specificID("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")
                .price(150.2)
                .quantityAvailable(2)
                .category(categoryOfBookSet);
    }
}