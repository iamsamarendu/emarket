package com.share.cloud.gateway.config;

import com.netflix.appinfo.AmazonInfo;
import com.share.cloud.gateway.config.aws.Container;
import com.share.cloud.gateway.config.aws.Converter;
import com.share.cloud.gateway.config.aws.EcsTaskMetadata;
import com.share.cloud.gateway.config.aws.Network;
import com.share.cloud.gateway.filters.ErrorFilter;
import com.share.cloud.gateway.filters.PostFilter;
import com.share.cloud.gateway.filters.PreFilter;
import com.share.cloud.gateway.filters.RouteFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Configuration
@Slf4j
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
