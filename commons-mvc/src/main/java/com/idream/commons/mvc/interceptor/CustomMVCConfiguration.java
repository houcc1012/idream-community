package com.idream.commons.mvc.interceptor;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: hejiang
 * @Description:
 * @Date: 10:01 2018/3/28
 */
@Configuration
public class CustomMVCConfiguration implements WebMvcConfigurer {
    /**
     * 利用fastjson替换掉jackson
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //解决字符串系列化两侧加引号问题
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        converters.add(stringHttpMessageConverter);

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse);
        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = Lists.newArrayList();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }
}
