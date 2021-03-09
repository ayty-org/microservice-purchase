package com.phoebus.library.librarymicroservicepurchase.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderClassName = "Builder")
@Table(name = "tb_purchase")
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="specific_id_user")
    private String specificIdUserLibrary;

    @Column(name = "specific_id_books")
    private String specificIdBooks;

    @Column(name = "price_to_pay")
    private double priceToPay;

    @Column(name="purchase_completed")
    private boolean purchaseCompleted;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String specificID = UUID.randomUUID().toString();

    public static Purchase to(PurchaseSaveDTO purchaseDTO, String specificIdBooks) {
        return Purchase
                .builder()
                .specificIdUserLibrary(purchaseDTO.getSpecificIdUserLibrary())
                .specificIdBooks(specificIdBooks)
                .priceToPay(purchaseDTO.getPriceToPay())
                .purchaseCompleted(purchaseDTO.isPurchaseCompleted())
                .specificID(purchaseDTO.getSpecificID())
                .build();
    }

}
