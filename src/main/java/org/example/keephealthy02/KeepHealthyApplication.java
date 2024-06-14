package org.example.keephealthy02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.example.keephealthy02"})
 // 确保扫描到 Mapper 接口所在的包
public class KeepHealthyApplication {
	public static void main(String[] args) {
		SpringApplication.run(KeepHealthyApplication.class, args);
	}
}
