package com.phoebus.library.librarymicroservicepurchase.builders;

import com.phoebus.library.librarymicroservicepurchase.purchase.CategoryOfBookDTO;

public class CategoryOfBookBuilderDTO {
    public static CategoryOfBookDTO.Builder createCategoryOfBookDTO() {
        return CategoryOfBookDTO.builder()
                .name("action");

    }
}
