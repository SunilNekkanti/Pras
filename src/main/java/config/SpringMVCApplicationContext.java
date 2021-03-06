package config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import ml.rugal.sshcommon.springmvc.method.annotation.FormModelMethodArgumentResolver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pfchoice.core.entity.MembershipFollowup;
import com.pfchoice.core.entity.deserializer.DateDeserializer;
import com.pfchoice.core.entity.formatter.*;
import com.pfchoice.core.entity.serializer.MembershipFollowupSerializer;
import com.pfchoice.springmvc.interceptor.LastPageInterceptor;

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
@ComponentScan({ "com.pfchoice.springmvc.controller" })
@Import({ SecurityConfig.class })
public class SpringMVCApplicationContext extends WebMvcConfigurerAdapter {

	// @Autowired
	// private AuthenticationInterceptor authenticationInterceptor;

	@Autowired
	private CountyFormatter countyFormatter;

	@Autowired
	private CPTMeasureFormatter cptMeasureFormatter;

	@Autowired
	private EmailTemplateFormatter emailTemplateFormatter;

	@Autowired
	private FileTypeFormatter fileTypeFormatter;

	@Autowired
	private FrequencyTypeFormatter frequencyTypeFormatter;

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
	private LastPageInterceptor lastPageInterceptor;

	@Autowired
	private MembershipFormatter membershipFormatter;

	@Autowired
	private MembershipStatusFormatter membershipStatusFormatter;

	@Autowired
	private PlanTypeFormatter planTypeFormatter;

	@Autowired
	private ProblemFormatter problemFormatter;

	@Autowired
	private ProviderFormatter providerFormatter;

	@Autowired
	private RoleFormatter roleFormatter;

	@Autowired
	private StateFormatter stateFormatter;

	@Autowired
	private ZipCodeFormatter zipCodeFormatter;

	@Autowired
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

	@PostConstruct
	public void init() {
	    requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #configureDefaultServletHandling(org.springframework.web.servlet.config.
	 * annotation.DefaultServletHandlerConfigurer)
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #addArgumentResolvers(java.util.List)
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new FormModelMethodArgumentResolver());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #configureContentNegotiation(org.springframework.web.servlet.config.
	 * annotation.ContentNegotiationConfigurer)
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).favorParameter(false).ignoreAcceptHeader(true);
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
		configurer.mediaType("json", MediaType.APPLICATION_OCTET_STREAM);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #configureMessageConverters(java.util.List)
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		GsonHttpMessageConverter messageConverter =   createGsonHttpMessageConverter(); 
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		messageConverter.setSupportedMediaTypes(supportedMediaTypes);
		converters.add(messageConverter);
	}

	/**
	 * @return
	 */
	public @Bean TilesViewResolver tilesViewResolver() {
		return new TilesViewResolver();
	}

	/**
	 * @return
	 */
	@Bean
	public HandlerAdapter annotationMethodHandlerAdapter() {
		return new RequestMappingHandlerAdapter();
	}

	/**
	 * @return
	 */
	@Bean
	public AbstractHandlerMapping defaultAnnotationHandlerMapping() {
		RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
		mapping.setUseSuffixPatternMatch(false);
		return mapping;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #addInterceptors(org.springframework.web.servlet.config.annotation.
	 * InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// This is a very important interceptor for authentication usage
		// registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**");
		registry.addInterceptor(lastPageInterceptor);
	}

	/**
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");

		// viewResolver.setViewClass(JstlView.class);
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #addResourceHandlers(org.springframework.web.servlet.config.annotation.
	 * ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.setOrder(-1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #addViewControllers(org.springframework.web.servlet.config.annotation.
	 * ViewControllerRegistry)
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "/WEB-INF/**/tiles.xml" });

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #addFormatters(org.springframework.format.FormatterRegistry)
	 */
	@Override
	public void addFormatters(FormatterRegistry formatterRegistry) {
		formatterRegistry.addFormatter(countyFormatter);
		formatterRegistry.addFormatter(cptMeasureFormatter);
		formatterRegistry.addFormatter(emailTemplateFormatter);
		formatterRegistry.addFormatter(fileTypeFormatter);
		formatterRegistry.addFormatter(frequencyTypeFormatter);
		formatterRegistry.addFormatter(genderFormatter);
		formatterRegistry.addFormatter(hedisMeasureFormatter);
		formatterRegistry.addFormatter(hedisMeasureGroupFormatter);
		formatterRegistry.addFormatter(icdFormatter);
		formatterRegistry.addFormatter(insuranceFormatter);
		formatterRegistry.addFormatter(membershipFormatter);
		formatterRegistry.addFormatter(membershipStatusFormatter);
		formatterRegistry.addFormatter(planTypeFormatter);
		formatterRegistry.addFormatter(problemFormatter);
		formatterRegistry.addFormatter(providerFormatter);
		formatterRegistry.addFormatter(roleFormatter);
		formatterRegistry.addFormatter(stateFormatter);
		formatterRegistry.addFormatter(zipCodeFormatter);
	}

	/**
	 * @return
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(624572800); // 10 MB=10485760
		multipartResolver.setMaxInMemorySize(624572800); // 1MB
		multipartResolver.setDefaultEncoding("utf-8");
		return multipartResolver;
	}
	
	private GsonHttpMessageConverter createGsonHttpMessageConverter() {
	 	Gson gson = new GsonBuilder()
				.registerTypeAdapter(MembershipFollowup.class, new MembershipFollowupSerializer())
				.registerTypeAdapter(Date.class, new DateDeserializer()).create();  
	 	
        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
        gsonConverter.setGson(gson);

        return gsonConverter;
    }


}
