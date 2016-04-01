package config;

import java.util.ArrayList;
import java.util.List;

import ml.rugal.sshcommon.springmvc.method.annotation.FormModelMethodArgumentResolver;

import com.pfchoice.core.entity.formatter.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
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
@Import({SecurityConfig.class})
public class SpringMVCApplicationContext extends WebMvcConfigurerAdapter
{
	
    //@Autowired
    //private AuthenticationInterceptor authenticationInterceptor;
	
	@Autowired
	private CountyFormatter countyFormatter;
	
	@Autowired
	private CPTMeasureFormatter cptMeasureFormatter;
	
	@Autowired
	private FileTypeFormatter fileTypeFormatter;
	
	@Autowired
	private GenderFormatter genderFormatter;
	
	@Autowired
	private HedisMeasureFormatter hedisMeasureFormatter;
	
	@Autowired
	private HedisMeasureGroupFormatter hedisMeasureGroupFormatter;
	
	@Autowired
	private ICDMeasureFormatter icdFormatter;
	
	@Autowired
	private InsuranceFormatter insuranceFormatter;

	@Autowired
	private InsuranceProviderFormatter insuranceProviderFormatter;

	@Autowired
	private MembershipFormatter membershipFormatter;
	
	@Autowired
	private MembershipStatusFormatter membershipStatusFormatter;
	
	@Autowired
	private PlanTypeFormatter planTypeFormatter;
	
	@Autowired
	private ProviderFormatter providerFormatter;
	
	@Autowired
	private RoleFormatter roleFormatter;
	
	@Autowired
	private StateFormatter stateFormatter;
	
	@Autowired
	private ZipCodeFormatter zipCodeFormatter;
	
	
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
       // configurer.favorPathExtension(false).favorParameter(false).parameterName("mediaType").ignoreAcceptHeader(true);
    	configurer.favorPathExtension(false).favorParameter(false).ignoreAcceptHeader(true);
    	configurer.defaultContentType( MediaType.APPLICATION_JSON);
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
     // super.configureMessageConverters(converters);
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
      // registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**");
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

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry)
    {
    	formatterRegistry.addFormatter(countyFormatter);
    	formatterRegistry.addFormatter(cptMeasureFormatter);
    	formatterRegistry.addFormatter(fileTypeFormatter);
    	formatterRegistry.addFormatter(genderFormatter);
    	formatterRegistry.addFormatter(hedisMeasureFormatter);
    	formatterRegistry.addFormatter(hedisMeasureGroupFormatter);
    	formatterRegistry.addFormatter(icdFormatter);
    	formatterRegistry.addFormatter(insuranceFormatter);
    	formatterRegistry.addFormatter(insuranceProviderFormatter);
    	formatterRegistry.addFormatter(membershipFormatter);
    	formatterRegistry.addFormatter(membershipStatusFormatter);
    	formatterRegistry.addFormatter(planTypeFormatter);
    	formatterRegistry.addFormatter(providerFormatter);
    	formatterRegistry.addFormatter(roleFormatter);
    	formatterRegistry.addFormatter(stateFormatter);
    	formatterRegistry.addFormatter(zipCodeFormatter);
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1048576);   // 1MB
        multipartResolver.setMaxInMemorySize(1048576);  // 1MB
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
    }
    
}
