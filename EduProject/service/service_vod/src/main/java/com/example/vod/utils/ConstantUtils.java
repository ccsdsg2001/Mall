package com.example.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author cc
 * @date 2023年01月21日 14:45
 */
@Component
public class ConstantUtils implements InitializingBean {


    @Value("${aliyun.vod.file.keyid}")
    private String keyId;

    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;



    //定义public static类
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;


    @Override
    public void afterPropertiesSet() throws Exception {

        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;

    }
}
