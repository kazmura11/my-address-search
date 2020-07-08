package com.example.myAddressSearch.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig {

	@Configuration
	public static class UserConfiguereAdapter extends WebSecurityConfigurerAdapter {
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/css/**", "/vendor/**");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.antMatcher("/member/**")
					.authorizeRequests()
					.antMatchers(
							"/",
							"/member/login",
							"/member/login/success"
							).permitAll()
					.antMatchers("/member/**").hasRole("USER")
					.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginProcessingUrl("/member/loginProcess")
					.loginPage("/member/login")
					.failureUrl("/member/login?error")
					.defaultSuccessUrl("/member/login/success", true)
					.usernameParameter("emailAddress")
					.passwordParameter("password")
				.and()
					.logout()
					// ログアウトが(GET)の場合設定する（CSRF対応）
					.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
					.logoutSuccessUrl("/member/login")
					.deleteCookies("JSESSIONID", "remember-me")
					.invalidateHttpSession(true)
				.and()
					.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and()
					.rememberMe()
					.rememberMeParameter("remember-me")
					.tokenValiditySeconds(60 * 60 * 24 * 30);
		}

		@Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	}
}
