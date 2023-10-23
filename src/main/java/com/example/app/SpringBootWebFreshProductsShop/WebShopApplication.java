package com.example.app.SpringBootWebFreshProductsShop;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.logging.Logger;

@SpringBootApplication
public class WebShopApplication {

	private static final Logger LOGGER =
			Logger.getLogger(WebShopApplication.class.getName());

	public static void main(String[] args) {

		new SpringApplicationBuilder(WebShopApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);

		LOGGER.info("APP is running...");
	}
}
