package com.example.eduorder.client;

import com.example.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author cc
 * @date 2023年01月31日 19:31
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getuseinfo(@PathVariable String id);
}
