package com.cartera_temp.cartera_temp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class CarteraTempApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarteraTempApplication.class, args);
	}

}
