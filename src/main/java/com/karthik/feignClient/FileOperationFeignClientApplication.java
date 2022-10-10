package com.karthik.feignClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FileOperationFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileOperationFeignClientApplication.class, args);
	}

}
