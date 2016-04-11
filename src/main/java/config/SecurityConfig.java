package config;

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

import com.pfchoice.springmvc.session.CustomAuthenticationSuccessHandler;

 
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
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/resources/**","/index");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		 .antMatchers("/user*/**","/home").hasAnyAuthority("ROLE_USER" ,"ROLE_ADMIN")
		 .antMatchers("/**").hasAuthority("ROLE_ADMIN")
		 .anyRequest().authenticated()
		 .and()
		 
		 .formLogin().loginPage("/index")
		 .usernameParameter("username").passwordParameter("password")
		 .loginProcessingUrl("/loginform.do")
		 .successHandler(new CustomAuthenticationSuccessHandler())
		 .failureUrl("/index?error")
		 .and()
		 
		  .logout().logoutUrl("/logout").logoutSuccessUrl("/index?logout")
          .invalidateHttpSession(true)
          .deleteCookies("JSESSIONID")
          .permitAll()
          .and()
          
		  .exceptionHandling().accessDeniedPage("/403")
		  .and()
		  
		  .csrf().disable()
		  
		  .sessionManagement().maximumSessions(1);
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
	        auth.userDetailsService(authenticationService).passwordEncoder(encoder);
	        auth.userDetailsService(authenticationService);
	}
}
