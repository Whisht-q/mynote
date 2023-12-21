package com.mynote.blog;

import com.mynote.base.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhishubin
 * @date 2023/12/20 11:27
 * @description
 */
@Slf4j
@SpringBootApplication
@ComponentScan({"com.mynote.blog","com.mynote.base"})
@EnableDiscoveryClient
@RefreshScope
public class BlogApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(BlogApplication.class, args);
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
                "Application MyNote-blog is running! Access URLs:\n\t" +
                "local: \thttp://localhost" + ":" + port + path + "/doc.html\n\t" +
                "Swagger文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
    }
}
