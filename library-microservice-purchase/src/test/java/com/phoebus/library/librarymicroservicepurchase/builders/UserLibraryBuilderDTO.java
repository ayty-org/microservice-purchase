package com.phoebus.library.librarymicroservicepurchase.builders;

import com.phoebus.library.librarymicroservicepurchase.purchase.UserLibraryDTO;

public class UserLibraryBuilderDTO {
    public static UserLibraryDTO.Builder createUserLibraryDTO() {
        return UserLibraryDTO.builder()
                .name("Test")
                .age(22)
                .phone("0000-0000")
                .specificID("69661bd1-6092-4068-bd28-c60517f8a16b")
                .email("teste@teste.com")
                .gender("M");
    }
}
