package com.example.demo;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.api.prices.dtos.FindProductPriceDto;
import com.example.demo.prices.domain.ProductPriceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ProductPriceRepository productPriceRepository;

	@Test
	@Sql({ "classpath:import_product_prices.h2.sql" })
	public void givenProductPrices_whenGetProductPriceOnDay14At10_thenStatus200()
			throws Exception {

		assertEquals(4, productPriceRepository.findAll().size());

		FindProductPriceDto dto = createFindProductPriceDto("2020-06-14 10:00:00");

		mvc.perform(get("/prices")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(35.50));

	}

	@Test
	@Sql({ "classpath:import_product_prices.h2.sql" })
	public void givenProductPrices_whenGetProductPriceOnDay14At16_thenStatus200()
			throws Exception {
		FindProductPriceDto dto = createFindProductPriceDto("2020-06-14 16:00:00");

		mvc.perform(get("/prices")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(25.45));

	}

	@Test
	@Sql({ "classpath:import_product_prices.h2.sql" })
	public void givenProductPrices_whenGetProductPriceOnDay14At21_thenStatus200()
			throws Exception {
		FindProductPriceDto dto = createFindProductPriceDto("2020-06-14 21:00:00");

		mvc.perform(get("/prices")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(35.50));

	}

	@Test
	@Sql({ "classpath:import_product_prices.h2.sql" })
	public void givenProductPrices_whenGetProductPriceOnDay15At10_thenStatus200()
			throws Exception {

		FindProductPriceDto dto = createFindProductPriceDto("2020-06-15 10:00:00");

		mvc.perform(get("/prices")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(30.50));

	}

	@Test
	@Sql({ "classpath:import_product_prices.h2.sql" })
	public void givenProductPrices_whenGetProductPriceOnDay15At21_thenStatus200()
			throws Exception {
		FindProductPriceDto dto = createFindProductPriceDto("2020-06-15 21:00:00");

		mvc.perform(get("/prices")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(38.95));

	}

	public FindProductPriceDto createFindProductPriceDto(String date) throws Exception {

		FindProductPriceDto findProductPriceDto = new FindProductPriceDto();
		findProductPriceDto.setBrandId(1L);
		findProductPriceDto.setInDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date));
		findProductPriceDto.setProductId(35455L);

		return findProductPriceDto;
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
