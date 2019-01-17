package com.door.match.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/***
 * 全局配置参数
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "global")
public class Global {
    private String systemPath;
    private String imgNgixPath;

}