package com.pulku;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by pınar on 29.03.2016.
 */
@Configuration
public class QuickPollMvcConfigAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
        // Normally default size 20, set the default size to 5
        phmar.setFallbackPageable(new PageRequest(0, 5));
        argumentResolvers.add(phmar);
        super.addArgumentResolvers(argumentResolvers);
    }
}
