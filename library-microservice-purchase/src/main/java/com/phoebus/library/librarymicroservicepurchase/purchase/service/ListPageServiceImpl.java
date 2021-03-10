package com.phoebus.library.librarymicroservicepurchase.purchase.service;

import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetBook;
import com.phoebus.library.librarymicroservicepurchase.feign.FeignGetUserLibrary;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.utils.ReturnAllPurchases;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListPageServiceImpl implements ListPageService{
    private final FeignGetBook feignGetBook;
    private final FeignGetUserLibrary feignGetUserLibrary;
    private final ReturnAllPurchases returnAllPurchases;

    @Override
    public Page<PurchaseReturnDTO> listPagePurchase(Pageable pageable) {
        List<PurchaseReturnDTO> purchaseReturnDTOList = returnAllPurchases.findAllPurchase(feignGetBook, feignGetUserLibrary);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), purchaseReturnDTOList.size());

        return new PageImpl(purchaseReturnDTOList.subList(start, end), pageable, purchaseReturnDTOList.size());
    }
}
