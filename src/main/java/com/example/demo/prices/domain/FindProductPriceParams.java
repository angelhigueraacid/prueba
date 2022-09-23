package com.example.demo.prices.domain;

import java.util.Date;

import lombok.Data;

@Data

public class FindProductPriceParams {

    private final Date inDate;

    private final Long brandId;

    private final Long productId;

}
