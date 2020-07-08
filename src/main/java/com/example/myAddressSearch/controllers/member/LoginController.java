package com.example.myAddressSearch.controllers.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/login")
public class LoginController {

	@GetMapping
	public String login() {
		return "/member/login";
	}

	@GetMapping(path = "/success")
	public void loginPageRedirect(HttpServletResponse response, Authentication authResult) throws ServletException, IOException {
		String role =  authResult.getAuthorities().toString();
		log.info("ROLE : {}", role);
		if (role.contains("ROLE_USER")) {
			response.sendRedirect("/member/addresses");
		} else {
			response.sendRedirect("/member/login?error");
		}
	}
}
