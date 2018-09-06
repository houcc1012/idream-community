package com.idream;

import com.idream.filter.AccessFilter;
import com.idream.filter.RateLimitFilter;
import com.idream.filter.ResubmitPostFilter;
import com.idream.filter.ResubmitPreFilter;
import com.idream.filter.UserRequestRecordPostFilter;
import com.idream.filter.UserRequestRecordPreFilter;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableZuulProxy
@SpringCloudApplication
@EnableHystrixDashboard
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    @Bean
    public UserRequestRecordPreFilter userRequestRecordPreFilter() {
        return new UserRequestRecordPreFilter();
    }

    @Bean
    public UserRequestRecordPostFilter userRequestRecordPostFilter() {
        return new UserRequestRecordPostFilter();
    }

    @Bean
    public RateLimitFilter rateLimitFilter() {
        return new RateLimitFilter();
    }

    @Bean
    public ResubmitPreFilter resubmitPreFilter() {
        return new ResubmitPreFilter();
    }

    @Bean
    public ResubmitPostFilter resubmitPostFilter() {
        return new ResubmitPostFilter();
    }

    /**
     * @Author: hejiang
     * @Description: 解决跨域问题
     * @Date: 13:54 2018/4/11
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod(HttpMethod.OPTIONS);
        config.addAllowedMethod(HttpMethod.HEAD);
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.PATCH);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(hystrixMetricsStreamServlet);
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addUrlMappings("/hystrix.stream");
        servletRegistrationBean.setName("HystrixMetricsStreamServlet");
        return servletRegistrationBean;
    }

}
