package com.phoebus.library.librarymicroservicepurchase.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseSaveDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String specificIdUserLibrary;

    @NotNull
    private List<String> specificIdBooks;

    @NotNull
    private double priceToPay;

    @NotNull
    private boolean purchaseCompleted;

    private String specificID;

}
