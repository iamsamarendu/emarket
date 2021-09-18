package com.share.stock.api.config;

import com.netflix.appinfo.AmazonInfo;
import com.share.stock.api.config.aws.Container;
import com.share.stock.api.config.aws.Converter;
import com.share.stock.api.config.aws.EcsTaskMetadata;
import com.share.stock.api.config.aws.Network;
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

@Slf4j
@Configuration
public class EurekaClientConfig {
    private static final String AWS_API_VERSION = "v2";
    private static final String AWS_METADATA_URL = "http://169.254.170.2/" + AWS_API_VERSION + "/metadata";
    // Used as string.contains to search correct container
    // Make sure that your Docker container in AWS Task definition has this as part of its name
    private static final String DOCKER_CONTAINER_NAME = "stock-api";

    private final Environment env;

    public EurekaClientConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
        log.info("Customize EurekaInstanceConfigBean for AWS");
        EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
        AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        config.setDataCenterInfo(info);
        try {
            String json = readEcsMetadata();
            EcsTaskMetadata metadata = Converter.fromJsonString(json);
            String ipAddress = findContainerPrivateIP(metadata);
            log.info("Override ip address to " + ipAddress);
            config.setIpAddress(ipAddress);
            config.setNonSecurePort(getPortNumber());
        } catch (Exception ex){
            log.info("Something went wrong when reading ECS metadata: " + ex.getMessage());
        }

        return config;
    }
    private int getPortNumber(){
        return env.getProperty("server.port", Integer.class);
    }

    private String findContainerPrivateIP(EcsTaskMetadata metadata){
        if (null != metadata){
            for (Container container: metadata.getContainers()){
                boolean found = container.getName().toLowerCase().contains(DOCKER_CONTAINER_NAME);
                if (found){
                    Network network = container.getNetworks()[0];
                    return network.getIPv4Addresses()[0];
                }
            }
        }
        return "";
    }

    private String readEcsMetadata() throws Exception{
        String url = AWS_METADATA_URL;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        StringBuilder response = new StringBuilder();
        try{
            con.setRequestMethod("GET");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
        } finally {
            con.disconnect();
        }
        return response.toString();
    }
}
