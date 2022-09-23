package com.example.demo.api.prices.dtos;

import java.util.Date;

import com.example.demo.prices.domain.Currency;
import com.example.demo.prices.domain.ProductPrice;

import lombok.Data;

@Data
public class ProductPriceDetailsDto {

    private Long brandId;

    private Long productId;

    private Long priceListId;

    private Double finalPrice;

    private Currency currency;

    private Date startingDate;

    private Date endingDate;

    public ProductPriceDetailsDto(ProductPrice productPrice) {
        this.brandId = productPrice.getBrandId();
        this.productId = productPrice.getProductId();
        this.priceListId = productPrice.getPriceListId();
        this.finalPrice = productPrice.getFinalPrice();
        this.currency = productPrice.getCurrency();
        this.startingDate = productPrice.getStartingDate();
        this.endingDate = productPrice.getEndingDate();
    }

}
