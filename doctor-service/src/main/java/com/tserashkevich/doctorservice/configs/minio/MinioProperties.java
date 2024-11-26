package com.tserashkevich.doctorservice.configs.minio;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    private String host;
    private Integer port;
    private String accessKey;
    private String secretKey;
    private String bucket;
}
