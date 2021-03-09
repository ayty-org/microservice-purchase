package com.phoebus.library.librarymicroservicepurchase.feign;

import com.phoebus.library.librarymicroservicepurchase.purchase.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "GetBook", url = "http://localhost:8080/v1/book", name = "book-service")
public interface GetBook {
    @GetMapping(value = "/id/{specificID}") //list books by specificId
    BookDTO findSpecificID(@PathVariable(value = "specificID") String specificID);
}