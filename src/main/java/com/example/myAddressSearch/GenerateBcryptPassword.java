package com.example.myAddressSearch;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateBcryptPassword {
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("demo"));
	}
}
