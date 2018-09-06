package com.idream.commons.mvc.swagger;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author hejiang
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.show", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = Lists.newArrayList();
        tokenPar.name("token").description("令牌").modelRef(new ModelRef("String")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        tokenPar.name("deviceType").description("设备类型:1-ios ;2-andriod; 3-微信小程序；4-admin web; 5-manger web; 6-app h5")
                .defaultValue("1")
                .modelRef(new ModelRef("Byte")).parameterType("header").required(true).build();
        pars.add(tokenPar.build());
        tokenPar.name("appVersion").description("app版本号")
                .defaultValue("1.0.0")
                .modelRef(new ModelRef("String")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(pars)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.idream.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("活动服务api")
                .description("活动服务接口文档说明")
                .contact(new Contact("hejiang", "", "hejiang@dream-china.com"))
                .version("1.0.0")
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(null, "list", "alpha", "schema",
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
    }
}