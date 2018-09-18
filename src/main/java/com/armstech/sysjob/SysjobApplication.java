package com.armstech.sysjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.armstech.sysjob.config.property.ApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class SysjobApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysjobApplication.class, args);
	}
}
