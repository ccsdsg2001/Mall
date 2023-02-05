package com.example.eduorder.client;

import com.example.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author cc
 * @date 2023年01月31日 19:31
 */
@Component
@FeignClient("service-edu")
public interface EduCLient {

    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getinfo(@PathVariable("id") String id);
}
