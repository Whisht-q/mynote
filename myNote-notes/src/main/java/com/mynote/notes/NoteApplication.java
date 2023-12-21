package com.mynote.notes;

import com.mynote.base.utils.CommonUtil;
import com.mynote.notes.feign.SystemFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhishubin
 * @date 2023/12/19 16:55
 * @description
 */
@Slf4j
@EnableDiscoveryClient
@RefreshScope
@EnableFeignClients(basePackageClasses = {SystemFeignClient.class})
@ComponentScan(basePackages = {"com.mynote.notes","com.mynote.base"})
@SpringBootApplication
public class NoteApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(NoteApplication.class, args);
        Environment env = application.getEnvironment();
        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String port = env.getProperty("server.port");
        String path = CommonUtil.getStr(env.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n\t" +
                "Application MyNote-note is running! Access URLs:\n\t" +
                "local: \thttp://localhost" + ":" + port + path + "/doc.html\n\t" +
                "Swagger文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
    }
}
