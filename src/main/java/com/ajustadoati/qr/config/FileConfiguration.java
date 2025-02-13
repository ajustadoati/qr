package com.ajustadoati.qr.config;

import com.ajustadoati.qr.config.properties.FileProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FileProperties.class)
@Slf4j
public class FileConfiguration {

}
