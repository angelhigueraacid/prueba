package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void givenProductPrices_whenGetProductPriceOnDay14At10_thenStatus200()
			throws Exception {
		String brandId = "1";
		String prodcutId = "35455";
		String inDate = "2020-06-14 10:00:00";

		mvc.perform(get("/prices")
				.accept(MediaType.APPLICATION_JSON)
				.param("brandId", brandId)
				.param("productId", prodcutId)
				.param("inDate", inDate))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(35.50));

	}

	@Test
	public void givenProductPrices_whenGetProductPriceOnDay14At16_thenStatus200()
			throws Exception {
		String brandId = "1";
		String prodcutId = "35455";
		String inDate = "2020-06-14 16:00:00";

		mvc.perform(get("/prices")
				.accept(MediaType.APPLICATION_JSON)
				.param("brandId", brandId)
				.param("productId", prodcutId)
				.param("inDate", inDate))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(25.45));

	}

	@Test
	public void givenProductPrices_whenGetProductPriceOnDay14At21_thenStatus200()
			throws Exception {
		String brandId = "1";
		String prodcutId = "35455";
		String inDate = "2020-06-14 21:00:00";

		mvc.perform(get("/prices")
				.contentType(MediaType.APPLICATION_JSON)
				.param("brandId", brandId)
				.param("productId", prodcutId)
				.param("inDate", inDate))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(35.50));

	}

	@Test
	public void givenProductPrices_whenGetProductPriceOnDay15At10_thenStatus200()
			throws Exception {

		String brandId = "1";
		String prodcutId = "35455";
		String inDate = "2020-06-15 10:00:00";

		mvc.perform(get("/prices")
				.contentType(MediaType.APPLICATION_JSON)
				.param("brandId", brandId)
				.param("productId", prodcutId)
				.param("inDate", inDate))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(30.50));

	}

	@Test
	public void givenProductPrices_whenGetProductPriceOnDay15At21_thenStatus200()
			throws Exception {
		String brandId = "1";
		String prodcutId = "35455";
		String inDate = "2020-06-15 21:00:00";

		mvc.perform(get("/prices")
				.contentType(MediaType.APPLICATION_JSON)
				.param("brandId", brandId)
				.param("productId", prodcutId)
				.param("inDate", inDate))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(38.95));

	}

}
