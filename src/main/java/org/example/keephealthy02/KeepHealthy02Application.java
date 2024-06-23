package org.example.keephealthy02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan("org.example.keephealthy02.Mapper")
public class KeepHealthy02Application {
	public static void main(String[] args) {
		SpringApplication.run(KeepHealthy02Application.class, args);
	}

}
