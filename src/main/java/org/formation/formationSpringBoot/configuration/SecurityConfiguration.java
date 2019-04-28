package org.formation.formationSpringBoot.configuration;

import javax.sql.DataSource;

import org.formation.formationSpringBoot.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private CustomUserDetailsService userDetailsService;

	// definition des urls accessibles en direct ou en authentifier
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).anonymous();
		http.authorizeRequests().antMatchers("/rest/user").permitAll().and().csrf().disable();
		http.authorizeRequests().antMatchers("/api/**").authenticated().and().httpBasic().and().csrf().disable();
		// http.authorizeRequests().antMatchers("/rest/**").permitAll();
		http.authorizeRequests().antMatchers("/rest/**").authenticated().and().httpBasic().and().csrf().disable();
		http.authorizeRequests().antMatchers("/**/list").permitAll();
		http.authorizeRequests().antMatchers("/**").authenticated().and().formLogin().loginPage("/login")
				.failureUrl("/login?error=true").permitAll().and().logout().logoutSuccessUrl("/personne/list");
	}

	// definition la methode d'authentification
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("toto").password("{noop}toto").roles("ADMIN");
//	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("select username,password,enable from users where username=?")
//				.authoritiesByUsernameQuery("select username,role from user_role where username=?");
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncorder());
	}

	@Bean(name="passwordEncoder")
	public PasswordEncoder getPasswordEncorder() {
		System.out.println("password");
		return new BCryptPasswordEncoder();
	}
}
