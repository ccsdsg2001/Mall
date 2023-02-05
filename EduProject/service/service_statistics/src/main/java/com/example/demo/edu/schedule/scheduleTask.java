package com.example.demo.edu.schedule;

import com.example.demo.edu.service.StatisticsDailyService;
import com.example.demo.edu.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author cc
 * @date 2023年02月02日 15:15
 */
@Component
public class scheduleTask {

    @Autowired
    private StatisticsDailyService staService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void taks(){
        staService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }


//    @Scheduled(cron = "0/5 * * * * ?")

}
