package com.example.demo.prices.domain;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductPriceDoesNotExistError extends ResponseStatusException {

    public ProductPriceDoesNotExistError(Long productId, Long brandId, Date inDate) {
        super(HttpStatus.NOT_FOUND, "Product price for product with ID " + productId + " and brand ID " + brandId
                + " does not exist for date " + inDate);
    }

}
