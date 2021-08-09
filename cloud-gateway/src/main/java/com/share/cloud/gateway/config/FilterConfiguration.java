package com.share.cloud.gateway.config;

import com.share.cloud.gateway.filters.ErrorFilter;
import com.share.cloud.gateway.filters.PostFilter;
import com.share.cloud.gateway.filters.PreFilter;
import com.share.cloud.gateway.filters.RouteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Bean
    public PostFilter postFilter(){
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }
    @Bean
    public PreFilter preFilter(){
        return new PreFilter();
    }
    @Bean
    public RouteFilter routeFilter(){
        return new RouteFilter();
    }
}
