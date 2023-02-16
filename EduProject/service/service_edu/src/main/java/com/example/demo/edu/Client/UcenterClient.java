package com.example.demo.edu.Client;

import com.example.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author cc
 * @date 2023年02月09日 15:56
 */
@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {

    @GetMapping("/educenter/member/getInfoUc/{id}")
    public UcenterMemberOrder getinfo(@PathVariable("id") String id);
}
