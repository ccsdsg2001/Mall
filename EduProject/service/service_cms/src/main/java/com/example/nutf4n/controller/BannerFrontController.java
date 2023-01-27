package com.example.nutf4n.controller;

import com.example.R;
import com.example.nutf4n.entity.CrmBanner;
import com.example.nutf4n.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * @author cc
 * @date 2023年01月27日 15:15
 */

@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    //select
    @GetMapping("getAllBanner")
    @Cacheable(key = "'selectIndexList'",value = "banner")
    public R get(){
        List<CrmBanner> list =bannerService.selectALLBanner();
        return R.ok().data("list", list);
    }

}
