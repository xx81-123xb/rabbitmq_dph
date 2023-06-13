package com.dupenghao.rabbitmq_dph.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Created by 杜鹏豪 on 2023/6/13.
 */
@Configuration
@EnableSwagger2WebMvc
@EnableWebMvc
public class SwaggerConfiguration implements WebMvcConfigurer {

//    @Bean
//    public Docket apiDocket() {
//        Docket docket = new Docket(DocumentationType.SWAGGER_2);
//        docket.apiInfo(apiInfo())
//                .groupName("webApi")
//                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
////                .apis(RequestHandlerSelectors.basePackage("com.dupenghao.rabbitmq_dph.controller"))
//                .build();
//        return docket;
//    }
//
//    public ApiInfo apiInfo() {
//        ApiInfo apiInfo = new ApiInfoBuilder()
//                .title("RabbitMq 学习")
//                .description("测试")
//                .version("1.0")
//                .contact(new Contact("杜鹏豪", "www.dupenghao.com", "xb8023xx@gmail.com"))
//                .build();
//        return apiInfo;
//    }

    // 解决swagger-ui.html 404报错
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/",
                        "classpath:");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
