package config;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.pfchoice.springmvc.service.ApplicationMailer;

@Configuration
@PropertySource("classpath:email.properties")
@ComponentScan(value = "com.pfchoice")
public class MailConfig {

	@Value("${port}")
    private Integer port;
	
	@Value("${host}")
    private String host;
	
	@Value("${emailId}")
    private String emailId;
	
	@Value("${cc}")
    private String cc;

	@Value("${password}")
    private String password;
	
    @Value("${mail.smtp.auth}")
    private String auth;
    
    @Value("${mail.smtp.starttls.enable}")
    private String starttls;
	
    @Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public  ApplicationMailer applicationMailer(){
		
		 final Session session = Session.getInstance(getEmailProperties(), new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(emailId, password);
	        }

		});
		
		ApplicationMailer applicationMailer = new ApplicationMailer();
		applicationMailer.setEmailId(emailId);
		applicationMailer.setSession(session);
		applicationMailer.setCc(cc);
		applicationMailer.setEmailProperties(getEmailProperties());
		
		return applicationMailer;
	}
	

    public Properties getEmailProperties() {
    	 final Properties config = new Properties();
    	 config.put("mail.smtp.host", host);
         config.put("mail.smtp.port", port);
        config.put("mail.smtp.auth", auth);
        config.put("mail.smtp.starttls.enable", starttls);
       
        return config;
    }


}
