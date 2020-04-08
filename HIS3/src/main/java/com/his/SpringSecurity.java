package com.his;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/ajax/**","/build/**","/idioma/**","/images/**","/vendors/**","/views/**").permitAll()
		.antMatchers("/usuario/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		  .formLogin()
		        //.successHandler(successHandler)
		        .loginPage("/login")
		    .permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
		http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		
		build
		.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("select nombre_usuario, contrasenia, id_usuario  from usuario where nombre_usuario=?")
		.authoritiesByUsernameQuery("select u.nombre_usuario, r.nombre from rol r inner join usuario u on (r.id_usuario=u.id_usuario) where u.nombre_usuario=?");
		
	}

	
	
}
