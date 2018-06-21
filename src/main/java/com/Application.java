package com;

import com.trs.hybase.client.TRSConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	@Value(value = "${hybase.url}")
	private  String HYBASE_URL;

	@Value(value = "${hybase.user}")
	private  String HYBASE_USER;

	@Value(value = "${hybase.password}")
	private  String HYBASE_PWD;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public TRSConnection connection(){
		return new TRSConnection(HYBASE_URL,HYBASE_USER,HYBASE_PWD,null);
	}

	@Bean
	public RestTemplate initRestTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
}
