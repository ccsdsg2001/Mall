package com.example.nutf4n.controller;

import com.example.JwtUtils;
import com.example.exception.fuliexception;
import com.example.nutf4n.entity.UcenterMember;
import com.example.nutf4n.service.UcenterMemberService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author cc
 * @date 2023年01月29日 16:37
 */

@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService memberService;
    
    
    @GetMapping("callback")
    public String CLAA(String code,String state){
        System.out.println(code+state);
        String baseAccessTokenUrl =
                "https://api.weixin.qq.com/sns/oauth2/access_token" +"?appid=%s" +
                        "&secret=%s" +
                        "&code=%s" +
                        "&grant_type=authorization_code";

        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                ConstantPropertiesUtil.WX_OPEN_APP_SECRET,
                code);
        //request url return two key use httpclient

        String result = null;
        try {
            result = HttpClientUtils.get(accessTokenUrl);
            System.out.println("accessToken=============" + result);
            //transfer from json string to map
            Gson gson = new Gson();
            HashMap hashMap = gson.fromJson(result, HashMap.class);
            Object access_token = hashMap.get("access_token");
            Object openid = hashMap.get("openid");
            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);
            String USERINFO = HttpClientUtils.get(userInfoUrl);
            System.out.println(USERINFO);

            HashMap userInfoMap = gson.fromJson(USERINFO, HashMap.class);
            Object nickname = userInfoMap.get("nickname");
            Object headimgurl = userInfoMap.get("headimgurl");


            //judge same wechat id

           UcenterMember member= memberService.getOpenIdMember(openid);
           if(member==null){
               member=  new UcenterMember();
               member.setOpenid((String) openid);
               member.setNickname((String) nickname);
               member.setAvatar((String) headimgurl);
               memberService.save(member);
           }

           //jwt generate token by member object
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());


            return "redirect:http://localhost:3000?token="+jwtToken;

        } catch (Exception e) {
            throw new fuliexception(20001, "获取access_token失败");
        }




    }


    //wechat login
    @GetMapping("login")
    public String getWxcode(){

        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        String redirect = ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL;
        try {
            redirect = URLEncoder.encode(redirect, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }


        String url = String.format(baseUrl, ConstantPropertiesUtil.WX_OPEN_APP_ID, redirect
                , "Nutf4n");


        return "redirect:"+url;


    }



}
