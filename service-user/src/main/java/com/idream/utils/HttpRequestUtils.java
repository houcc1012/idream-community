package com.idream.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

public class HttpRequestUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

    //默认编码集
    private static final String CHARSET = "utf-8";

    /**
     * 超时时间 2000毫秒
     */
    private static final int TIME_OUT = 2000;

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url 发送请求的URL
     * @param map 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     *
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, Map<String, String> map) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {

            StringBuilder params = new StringBuilder();
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                String key = entry.getKey();
                String value = entry.getValue();
                params.append("&").append(key).append("=").append(value);
            }
            if (StringUtils.isNotEmpty(params)) {
                url = (url.endsWith("?") ? url : url + "?") + params.substring(1);
            }
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(TIME_OUT);
            connection.setReadTimeout(TIME_OUT);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.error("发送GET请求出现异常！", e);
        } finally {
            IOUtils.closeQuietly(in);
        }
        return result.toString();
    }

    /**
     * 发送HttpPost请求
     *
     * @param urlStr 服务地址
     * @param params 参数
     *
     * @return 成功:返回json字符串<br/>
     */
    public static String sendJsonPost(String urlStr, Object params) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String result = null;
        try {
            // 创建连接
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.setConnectTimeout(TIME_OUT);
            connection.setReadTimeout(TIME_OUT);
            connection.connect();
            //发送参数
            outputStream = connection.getOutputStream();
            //放入参数
            if (params != null) {
                outputStream.write(JSON.toJSONString(params).toString().getBytes(CHARSET));
                outputStream.flush();
            }
            //获取服务器返回数据流
            inputStream = connection.getInputStream();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                IOUtils.copy(inputStream, byteArrayOutputStream);
                result = new String(byteArrayOutputStream.toByteArray(), CHARSET);
            }
            connection.connect();
        } catch (IOException e) {
            logger.error("发送 post json请求失败!", e);
        } finally {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(inputStream);
        }
        return result;
    }

}