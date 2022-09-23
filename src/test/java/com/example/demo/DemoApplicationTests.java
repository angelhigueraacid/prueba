package com.example.demo;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.api.prices.dtos.FindProductPriceDto;
import com.example.demo.prices.domain.Currency;
import com.example.demo.prices.domain.ProductPrice;
import com.example.demo.prices.domain.ProductPriceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ProductPriceRepository productPriceRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void givenProductPrices_whenGetProductPrice_thenStatus200() throws Exception {
		createProductPrices();

		FindProductPriceDto dto = new FindProductPriceDto();
		dto.setBrandId(1L);
		dto.setInDate(new Date());
		dto.setProductId(1L);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String sendJson = ow.writeValueAsString(dto);

		mvc.perform(get("/prices").contentType(MediaType.APPLICATION_JSON).content(sendJson))
				.andExpect(status().isOk());

	}

	private void createProductPrices() {
		productPriceRepository.save(new ProductPrice(1L, 1L, 1L, 1, 35.45, Currency.EUR, new Date(), new Date()));
	}
}
