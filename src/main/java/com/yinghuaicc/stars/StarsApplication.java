package com.yinghuaicc.stars;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(value = "com.yinghuaicc.stars.repository.mapper")
public class StarsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StarsApplication.class, args);
	}

	/**
	 *重写configure
	 * @param builder
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(StarsApplication.class);
	}
}
