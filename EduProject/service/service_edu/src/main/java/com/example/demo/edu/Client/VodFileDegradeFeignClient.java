package com.example.demo.edu.Client;

import com.example.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cc
 * @date 2023年01月26日 17:00
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("time out");
    }

    @Override
    public R delte(List<String> videoIdList) {
        return null;
    }


}