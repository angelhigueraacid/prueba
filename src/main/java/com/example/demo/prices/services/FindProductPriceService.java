package com.example.demo.prices.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.prices.domain.FindProductPriceParams;
import com.example.demo.prices.domain.ProductPrice;
import com.example.demo.prices.domain.ProductPriceDoesNotExistError;
import com.example.demo.prices.domain.ProductPriceRepository;

@Service
public class FindProductPriceService {

    private ProductPriceRepository repo;

    public FindProductPriceService(ProductPriceRepository repo) {
        this.repo = repo;
    }

    public ProductPrice execute(FindProductPriceParams params) {

        List<ProductPrice> productPrices = this.repo.findByProductIdAndBrandIdInDate(params.getProductId(),
                params.getBrandId(), params.getInDate(),
                Sort.by(Direction.DESC, "priority"));

        if (productPrices.isEmpty())
            throw new ProductPriceDoesNotExistError(params.getProductId(),
                    params.getBrandId(), params.getInDate());

        return productPrices.get(0);

    }
}
