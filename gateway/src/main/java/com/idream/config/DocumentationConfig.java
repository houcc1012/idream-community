package com.idream.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hejiang
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("用户服务", "/service-user/v2/api-docs", "2.0"));
        resources.add(swaggerResource("活动服务", "/service-activity/v2/api-docs", "2.0"));
        resources.add(swaggerResource("营销服务", "/service-marketing/v2/api-docs", "2.0"));
        resources.add(swaggerResource("第三方接口", "/third-interface/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
