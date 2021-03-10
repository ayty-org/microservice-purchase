package com.phoebus.library.librarymicroservicepurchase.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class BookDTO {

    @NotNull
    @Size(min = 2)
    private String title;
    @NotNull
    @Size(min = 3)
    private String synopsis;
    @NotNull
    @Size(min = 3)
    private String isbn;
    @NotNull
    @Size(min = 3)
    private String author;
    @NotNull
    @Min(0)
    private double price;
    @NotNull
    @Min(0)
    private int quantityAvailable;
    @NotNull
    private Set<CategoryOfBookDTO> category = new HashSet<>();
    @NotNull
    private String specificID;
}
