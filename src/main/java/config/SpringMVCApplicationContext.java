package config;

import java.util.ArrayList;
import java.util.List;

import ml.rugal.sshcommon.springmvc.method.annotation.FormModelMethodArgumentResolver;

import com.pfchoice.springmvc.interceptor.AuthenticationInterceptor;


import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;


/**
 * Java based Web context configuration class.
 * <p>
 * Including argument resolution, message converter, view resolution etc.,
 *
 * @author Sarath
 */
@Configuration
@EnableWebMvc
@ComponentScan(
    {
        "com.pfchoice.springmvc.controller"    
    })
public class SpringMVCApplicationContext extends WebMvcConfigurerAdapter
{
	
 //   @Autowired
 //   private AuthenticationInterceptor authenticationInterceptor;
    
//	private static final Map<String, Definition> tiles = new HashMap<String,Definition>();
//	private static final Attribute TEMPLATE = new Attribute("/WEB-INF/jsp/layout.jsp");


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
    {
        argumentResolvers.add(new FormModelMethodArgumentResolver());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
    {
        configurer.favorPathExtension(false).favorParameter(false);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        GsonHttpMessageConverter messageConverter = new GsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        messageConverter.setSupportedMediaTypes(supportedMediaTypes);
        converters.add(messageConverter);
    }


    public @Bean TilesViewResolver tilesViewResolver() {
        return new TilesViewResolver();
    }

    @Bean
    public HandlerAdapter annotationMethodHandlerAdapter()
    {
        return new RequestMappingHandlerAdapter();
    }

    @Bean
    public AbstractHandlerMapping defaultAnnotationHandlerMapping()
    {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setUseSuffixPatternMatch(false);
        return mapping;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //This is a very important interceptor for authentication usage
     //  registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**");
    }
    
      @Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");

		//viewResolver.setViewClass(JstlView.class);
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry
        .addResourceHandler("/resources/**").addResourceLocations("/resources/");
    	registry.setOrder(-1);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(new String[] {
            "/WEB-INF/**/tiles.xml"        });

        configurer.setCheckRefresh(true);

        return configurer;

    }
    
    /**
     * <code>Resolves views selected for rendering by @Controllers to tiles resources in the Apache TilesConfigurer bean</code>
     */
    @Bean
    public TilesViewResolver getTilesViewResolver() {
     TilesViewResolver tilesViewResolver = new TilesViewResolver();
     tilesViewResolver.setViewClass(TilesView.class);
     return tilesViewResolver;
    }


    /**
     * <code>Configures Apache tiles definitions bean used by Apache TilesViewResolver to resolve views selected for rendering by @Controllers</code>
     */ 
   /* @Bean
    public TilesConfigurer getTilesConfigurer() {
     TilesConfigurer tilesConfigurer = new TilesConfigurer();
     tilesConfigurer.setCheckRefresh(true);
     tilesConfigurer.setDefinitionsFactoryClass(TilesDefinitionsConfig.class);
     
     // Add apache tiles definitions
     TilesDefinitionsConfig.addDefinitions();
     
     return tilesConfigurer;
    }*/
}
