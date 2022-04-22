package com.phani.micorservices.restfulwebservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.phani.micorservices.restfulwebservices.model.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	@Autowired
	MessageSource messageSource;
	
	@GetMapping(path = "/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/helloWorldBean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	@GetMapping(path = "/helloWorld/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello World " + name);
	}
	
	@GetMapping(path = "/helloWorldInternationalization")
	public String helloWorldI18n(
			// @RequestHeader(name = "Accept-Language" , required = false) Locale locale
			) {
		return messageSource.getMessage("good.morning.message",null, "Default Message", LocaleContextHolder.getLocale()//locale
				);
	}
}
