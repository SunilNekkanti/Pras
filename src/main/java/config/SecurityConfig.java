package config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pfchoice.core.service.impl.AuthenticationServiceImpl;

 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
    Environment env;
	
	@Autowired
	UserDetailsService authenticationService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		 .antMatchers("/resources/**","/","/index").permitAll()
		 .antMatchers("/**").hasAnyRole("ADMIN","USER")
		 .and().formLogin().loginPage("/index")
		 .usernameParameter("username").passwordParameter("password")
		 .loginProcessingUrl("/loginform")
		 .and()
		  .csrf().disable();
		
		/* http.authorizeRequests()
		    .antMatchers("/resources/**","/","/index").permitAll()
			.antMatchers("/**").hasAnyRole("ADMIN","USER")
			.and()
			  .formLogin().loginPage("/index").failureUrl("/index?error")
			  .usernameParameter("username").passwordParameter("password")
			.and()
			  .logout().logoutSuccessUrl("/index?logout")
			.and()
			  .exceptionHandling().accessDeniedPage("/403")
			.and()
			  .csrf().disable();*/
	}
	    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
	      //  auth.userDetailsService(authenticationService).passwordEncoder(encoder);4
	        auth.userDetailsService(authenticationService);
	}
}
