package com.syff;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Demo {
	
	@RequestMapping("/hello1")
	public String test() {
		
		return "hello,this is a springboot demo ";
	}

}
