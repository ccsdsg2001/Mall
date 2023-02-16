package com.example.demo.edu.controller;


import com.example.R;
import com.example.demo.edu.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author NutF4n
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/staservice/sta")
//@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService dailyService;


    //count register
    @PostMapping("registerCount/{day}")
    public R registercount(@PathVariable String day){
        dailyService.registerCount(day);
        return R.ok();
    }

    //show sta table
    @GetMapping("showData/{type}/{begin}/{end}")
    public R showdata(@PathVariable String type,@PathVariable String begin,
                      @PathVariable String end){
       Map<String,Object> map = dailyService.getShowData(type,begin,end);
       return R.ok().data(map);
    }

}

