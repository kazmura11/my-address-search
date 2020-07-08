package com.example.myAddressSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myAddressSearch.generators.FQCNBeanNameGenerator;

@Controller
@SpringBootApplication
@ComponentScan(nameGenerator = FQCNBeanNameGenerator.class)
public class MyAddressSearchApplication {

	@RequestMapping(value = {"/", "/member"})
	public String redirectToTopPage() {
		return "redirect:/member/addresses";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MyAddressSearchApplication.class, args);
	}

}
