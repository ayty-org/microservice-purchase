package com.phoebus.library.librarymicroservicepurchase.purchase.service;

import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPageService {
    Page<PurchaseReturnDTO> listPagePurchase(Pageable pageable);
}
