package com.phoebus.library.librarymicroservicepurchase.feign;

import com.phoebus.library.librarymicroservicepurchase.purchase.UserLibraryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "GetUser", url = "http://localhost:9092/v1/user", name = "userLibrary-service")
public interface GetUserLibrary {
    @GetMapping(value = "/id/{specificID}")
    UserLibraryDTO findSpecificID(@PathVariable(value = "specificID") String specificID);

}