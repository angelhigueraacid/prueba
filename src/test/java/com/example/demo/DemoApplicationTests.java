package com.example.demo;

import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.api.prices.dtos.FindProductPriceDto;
import com.example.demo.prices.domain.Currency;
import com.example.demo.prices.domain.ProductPrice;
import com.example.demo.prices.domain.ProductPriceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ProductPriceRepository productPriceRepository;

	@BeforeAll
	private void setup() throws Exception {
		productPriceRepository.save(new ProductPrice(1L, 35455L, 1L, 0,
				35.50, Currency.EUR,
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-06-14 00:00:00"),
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-12-31 23:59:59")));
		productPriceRepository.save(new ProductPrice(1L, 35455L, 2L, 1,
				25.45, Currency.EUR,
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-06-14 15:00:00"),
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-06-14 18:30:00")));
		productPriceRepository.save(new ProductPrice(1L, 35455L, 3L, 1,
				30.50, Currency.EUR,
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-06-15 00:00:00"),
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-06-15 11:00:00")));
		productPriceRepository.save(new ProductPrice(1L, 35455L, 4L, 1,
				38.95, Currency.EUR,
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-06-15 16:00:00"),
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-12-31 23:59:59")));

	}

	@Test
	public void givenProductPrices_whenGetProductPriceOnDay14At10_thenStatus200()
			throws Exception {

		FindProductPriceDto dto = createFindProductPriceDto("2020-06-14 10:00:00");

		mvc.perform(get("/prices")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(35.50));

	}

	@Test
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
	public void givenProductPrices_whenGetProductPriceOnDay15At10_thenStatus200() throws Exception {

		FindProductPriceDto dto = createFindProductPriceDto("2020-06-15 10:00:00");

		mvc.perform(get("/prices")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(30.50));

	}

	@Test
	public void givenProductPrices_whenGetProductPriceOnDay15At21_thenStatus200() throws Exception {
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
