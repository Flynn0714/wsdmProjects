package com.wsdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author wsdm
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WSDMApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(WSDMApplication.class, args);
    }
}
