package com.example.nutf4n.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.R;
import com.example.nutf4n.entity.CrmBanner;

import com.example.nutf4n.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 *
 * 后台管理接口
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-27
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    //pageselect
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable Long page, @PathVariable Long limit){
        Page<CrmBanner> pageBanner =new Page<>(page,limit);
        bannerService.page(pageBanner, null);
        return R.ok().data("items",pageBanner.getRecords()).data("total", pageBanner.getTotal());

    }

    //addBanner
    @PostMapping("addBanner")
    public R add(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return R.ok();
    }


    @PutMapping("update")
    public R ad123d(@RequestBody CrmBanner crmBanner){
        bannerService.updateById(crmBanner);
        return R.ok();
    }
    @DeleteMapping("remove/{id}")
    public R ad213d(@RequestBody String  id){
        bannerService.removeById(id);
        return R.ok();
    }
    @GetMapping("get/{id}")
    public R get(@RequestBody String  id){
        CrmBanner byId = bannerService.getById(id);
        return R.ok().data("item", byId);
    }

}

