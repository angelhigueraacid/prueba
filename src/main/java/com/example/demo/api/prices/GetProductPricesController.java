package com.example.demo.api.prices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.prices.dtos.ProductPriceDetailsDto;
import com.example.demo.api.prices.dtos.FindProductPriceDto;
import com.example.demo.prices.domain.FindProductPriceParams;
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
            @RequestBody FindProductPriceDto findProductPriceDto) {
        ProductPrice productPrice = this.findProductPriceService
                .execute(new FindProductPriceParams(findProductPriceDto.getInDate(),
                        findProductPriceDto.getBrandId(), findProductPriceDto.getProductId()));
        return new ProductPriceDetailsDto(productPrice);
    }

}
