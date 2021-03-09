package com.phoebus.library.librarymicroservicepurchase.purchase.v1;

import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseReturnDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.PurchaseSaveDTO;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.DeletePurchaseService;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.EditPurchaseService;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.GetPurchaseService;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.ListPageService;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.ListPurchaseService;
import com.phoebus.library.librarymicroservicepurchase.purchase.service.SavePurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/purchase")
public class PurchaseControllerV1 {
    private final DeletePurchaseService deletePurchaseService;
    private final EditPurchaseService editPurchaseService;
    private final GetPurchaseService getPurchaseService;
    private final ListPageService listPageService;
    private final ListPurchaseService listPurchaseService;
    private final SavePurchaseService savePurchaseService;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseReturnDTO getPurchaseById(@PathVariable("id") Long id) {
        return getPurchaseService.getPurchase(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseReturnDTO> getAllPurchases(){
        return listPurchaseService.listAllPurchases();
    }

    @GetMapping(value = {"/"})
    @ResponseStatus(HttpStatus.OK)
    public Page<PurchaseReturnDTO> listPagePurchase(Pageable pageable){
        return listPageService.listPagePurchase(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody PurchaseSaveDTO purchaseSaveDTO){
        savePurchaseService.save(purchaseSaveDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) { deletePurchaseService.delete(id);}

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}")
    public void editPurchase(@PathVariable("id") Long id) {
        editPurchaseService.editPurchase(id);
    }
}
