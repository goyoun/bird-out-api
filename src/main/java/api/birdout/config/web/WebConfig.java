package api.birdout.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/img/**")
            .addResourceLocations("file:///" + "/Users/superpil/Documents/etc-doc/")
            .setCachePeriod(20)
            .resourceChain(true)
            .addResolver(new PathResourceResolver());
            ;
  }
    
}
