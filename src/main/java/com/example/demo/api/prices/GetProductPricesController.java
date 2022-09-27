package com.example.demo.api.prices;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.prices.dtos.ProductPriceDetailsDto;
import com.example.demo.prices.domain.ProductPrice;
import com.example.demo.prices.services.FindProductPriceService;

@RestController()
public class GetProductPricesController {

    private final FindProductPriceService findProductPriceService;

    public GetProductPricesController(FindProductPriceService findProductPriceService) {
        this.findProductPriceService = findProductPriceService;
    }

    @GetMapping("/prices")
    @ResponseBody
    public ProductPriceDetailsDto productPrice(
            @RequestParam Long productId, @RequestParam Long brandId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date inDate) {
        ProductPrice productPrice = this.findProductPriceService.execute(productId, brandId, inDate);
        return new ProductPriceDetailsDto(productPrice);
    }

}
