package com.mynote.notes.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhishubin
 * @date 2023/12/18 15:39
 * @description
 */
@Configuration
@EnableSwagger2 // 开始Swagger功能
@EnableKnife4j // 开始knife4j增强功能
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {

    @Bean(value = "note")
    public Docket apiNote() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("note")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mynote.notes"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // //大标题
                .title("mynote-note后台服务API接口文档")
                // 版本号
                .version("1.0")
//				.termsOfServiceUrl("NO terms of service")
                // 描述
                .description("后台API接口")
                // 作者
                .contact(new Contact("zhishubin","xxxxx","853378409@qq.com"))
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

}
