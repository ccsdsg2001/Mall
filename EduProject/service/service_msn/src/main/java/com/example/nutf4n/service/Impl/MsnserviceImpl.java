package com.example.nutf4n.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.example.nutf4n.service.Msnservice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author cc
 * @date 2023年01月28日 0:20
 */
@Service
public class MsnserviceImpl implements Msnservice {
    @Override
    public Boolean send(Map<String, Object> param, String phone) {
        if(StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI5t6Hsous9HoD7F7PNLgk",
                        "lHm1uCxESIBjsJVwQYW8oX3VoCwcLe");
        IAcsClient client = new DefaultAcsClient(profile);


        //set param
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        //final param
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
//param
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "阿里云短信测试"); //signname
        request.putQueryParameter("TemplateCode", "SMS_154950909");//module name
        request.putQueryParameter("TemplateParam",
                JSONObject.toJSONString(param));

        try {
            CommonResponse commonResponse = client.getCommonResponse(request);
            boolean success = commonResponse.getHttpResponse().isSuccess();
            return success;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }



    }
}
