package com.example.demo.prices.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.prices.domain.ProductPrice;
import com.example.demo.prices.domain.ProductPriceDoesNotExistError;
import com.example.demo.prices.domain.ProductPriceRepository;

@Service
public class FindProductPriceService {

    private ProductPriceRepository repo;

    public FindProductPriceService(ProductPriceRepository repo) {
        this.repo = repo;
    }

    public ProductPrice execute(Long productId, Long brandId, Date inDate) {

        List<ProductPrice> productPrices = this.repo.findByProductIdAndBrandIdInDate(productId,
                brandId, inDate,
                Sort.by(Direction.DESC, "priority"));

        if (productPrices.isEmpty())
            throw new ProductPriceDoesNotExistError(productId, brandId, inDate);

        return productPrices.get(0);

    }
}
