package com.example.demo.prices.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long brandId;

    private Long productId;

    private Long priceListId;

    private int priority;

    private Double finalPrice;

    private Currency currency;

    private Date startingDate;

    private Date endingDate;

    protected ProductPrice() {
    }

    public ProductPrice(Long brandId, Long productId, Long priceListId, int priority, Double finalPrice,
            Currency currency, Date startingDate, Date endingDate) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceListId = priceListId;
        this.priority = priority;
        this.finalPrice = finalPrice;
        this.currency = currency;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

}
