package de.cosmicit.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc //!!
@ComponentScan
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * Add redirect from /documentation[/] to /documentation/index.html
     * Satisfy the swagger documentation page
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/documentation").setViewName(
                "redirect:/documentation/index.html");
        registry.addViewController("/documentation/").setViewName(
                "redirect:/documentation/index.html");
        super.addViewControllers(registry);
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix("");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (! registry.hasMappingForPattern("/documentation/**")) {
            registry.addResourceHandler("/documentation/**").addResourceLocations("classpath:/public/documentation/");
        }
        if (!registry.hasMappingForPattern("/swagger-ui/**")) {
            registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/public/swagger-ui/");
        }
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).
            favorParameter(true).
            parameterName("mediaType").
            ignoreAcceptHeader(false).
            useJaf(false).
            defaultContentType(MediaType.APPLICATION_JSON).
            mediaType("json", MediaType.APPLICATION_JSON);
    }

}
