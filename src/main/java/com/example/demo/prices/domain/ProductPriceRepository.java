package com.example.demo.prices.domain;

import java.util.List;

//import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    @Query("select p from ProductPrice p where p.productId = ?1 and p.brandId = ?2")
    ProductPrice findByProductIdAndBrandId(Long productId, Long brandId);

    List<ProductPrice> findByProductId(Long productId);

}
