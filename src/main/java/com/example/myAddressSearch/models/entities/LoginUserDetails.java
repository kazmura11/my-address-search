package com.example.myAddressSearch.models.entities;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
	private final User user;

	public LoginUserDetails(User user, List<GrantedAuthority> grantedAuthorities) {
		super(user.getEmailAddress(), user.getPassword(), grantedAuthorities);
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

}
