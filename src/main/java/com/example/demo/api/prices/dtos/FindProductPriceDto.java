package com.example.demo.api.prices.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class FindProductPriceDto {

    private Long brandId;

    private Long productId;

    private Date inDate;

}
