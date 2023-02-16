package com.example.demo.edu.Client;

import com.example.demo.edu.Client.UcenterClient;
import com.example.ordervo.UcenterMemberOrder;
import org.springframework.stereotype.Component;

/**
 * @author cc
 * @date 2023年02月09日 20:52
 */
//服務降級 Hystrix
@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UcenterMemberOrder getinfo(String id) {
        return null;
    }
}
