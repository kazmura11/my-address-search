package com.example.myAddressSearch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.myAddressSearch.models.entities.LoginUserDetails;
import com.example.myAddressSearch.models.entities.User;
import com.example.myAddressSearch.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
		User user = userRepository.findByEmailAddressEqualsAndDeletedIsFalse(emailAddress);
		if (user == null) {
			throw new UsernameNotFoundException("The requested user is not found");
		}
		List<GrantedAuthority> grantedAuthorities = null;
		if (user.isAdminFlag()) {
			grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
			log.info("ADMIN LOGIN");
		} else {
			grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_USER");
			log.info("USER LOGIN");
		}

		return new LoginUserDetails(user, grantedAuthorities);
	}
}