package edu.mum.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void	configureGlobalSecurity(AuthenticationManagerBuilder auth) 	throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService());
		return authProvider;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		JdbcDaoImpl dao = new JdbcDaoImpl();
		dao.setDataSource(dataSource);
		dao.setAuthoritiesByUsernameQuery("select u1.username, u2.authority from credentials u1, authority u2" +
										  " where u1.username = u2.username and u1.username =?");
		dao.setUsersByUsernameQuery("select username,password,enabled from credentials where username=?");
		return dao;
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/welcome")
			.failureUrl("/loginfailed");
		httpSecurity.logout().logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessUrl("/login");
		httpSecurity.exceptionHandling().accessDeniedPage("/accessDenied");
		httpSecurity.authorizeRequests()
			.antMatchers("/").authenticated()
			.antMatchers("/assessments/*").hasRole("STUDENT")
			.antMatchers("/questions/*").hasRole("PROFESSOR")
			.antMatchers("/exam/addExam").hasRole("PROFESSOR")
			.antMatchers("/exam/detail").hasRole("PROFESSOR")
			.antMatchers("/users/*").hasRole("ADMIN");
			
		httpSecurity.csrf().disable();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
