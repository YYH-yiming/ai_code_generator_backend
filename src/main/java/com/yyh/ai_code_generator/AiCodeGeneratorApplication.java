package com.yyh.ai_code_generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yyh.ai_code_generator.mapper")
public class AiCodeGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiCodeGeneratorApplication.class, args);
	}

}
