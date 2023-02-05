package com.example.demo.edu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.R;
import com.example.demo.edu.Client.UcenterClient;
import com.example.demo.edu.entity.StatisticsDaily;
import com.example.demo.edu.mapper.StatisticsDailyMapper;
import com.example.demo.edu.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomUtils;
import org.bouncycastle.pqc.math.linearalgebra.RandUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author NutF4n
 * @since 2023-02-01
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void registerCount(String day) {

        QueryWrapper<StatisticsDaily> wrapper =new QueryWrapper<>();
        wrapper.eq("date_calculated", day);
        baseMapper.delete(wrapper);

        R r = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) r.getData().get("countRegister");

        //set to db
        StatisticsDaily sta = new StatisticsDaily();
        sta.setRegisterNum(countRegister);
        sta.setDateCalculated(day);


        sta.setVideoViewNum(RandomUtils.nextInt(1,100000));
        sta.setLoginNum(RandomUtils.nextInt(1,100000));
        sta.setCourseNum(RandomUtils.nextInt(1,100000));

        baseMapper.insert(sta);
    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        //根据条件查询对应数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);
        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);

        //因为返回有两部分数据：日期 和 日期对应数量
        //前端要求数组json结构，对应后端java代码是list集合
        //创建两个list集合，一个日期list，一个数量list
        List<String> date_calculatedList = new ArrayList<>();
        List<Integer> numDataList = new ArrayList<>();

        //遍历查询所有数据list集合，进行封装
        for (int i = 0; i < staList.size(); i++) {
            StatisticsDaily daily = staList.get(i);
            //封装日期list集合
            date_calculatedList.add(daily.getDateCalculated());
            //封装对应数量
            switch (type) {
                case "login_num":
                    numDataList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        //把封装之后两个list集合放到map集合，进行返回
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList",date_calculatedList);
        map.put("numDataList",numDataList);
        return map;
    }
}
