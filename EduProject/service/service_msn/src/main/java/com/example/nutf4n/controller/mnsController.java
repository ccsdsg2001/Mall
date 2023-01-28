package com.example.nutf4n.controller;

import com.example.R;
import com.example.nutf4n.Utils.RandomUtil;
import com.example.nutf4n.service.Msnservice;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author cc
 * @date 2023年01月28日 0:19
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class mnsController {

    @Autowired
    private Msnservice msnservice;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //sendMsg
    @GetMapping("send/{phone}")
    public R send(@PathVariable String phone){
        //get code from redis
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return  R.ok();
        }
        //if none, get code

     code = RandomUtil.getFourBitRandom();
        Map<String,Object> param =new HashMap<>();
        param.put("code", code);
       Boolean issend= msnservice.send(param,phone);
    if(issend){
        //set code to redis
        redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
        return R.ok();
    }else{
        return R.error().message("sendFail");
    }

    }

}
