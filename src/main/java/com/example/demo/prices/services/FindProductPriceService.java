package com.example.demo.prices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.prices.domain.FindProductPriceParams;
import com.example.demo.prices.domain.ProductPrice;
import com.example.demo.prices.domain.ProductPriceRepository;

@Service
public class FindProductPriceService {

    private ProductPriceRepository repo;

    @Autowired
    public FindProductPriceService(ProductPriceRepository repo) {
        this.repo = repo;
    }

    public ProductPrice execute(FindProductPriceParams params) {

        List<ProductPrice> productPrices = this.repo.findByProductId(params.getProductId());

        return productPrices.get(0);

    }
}
