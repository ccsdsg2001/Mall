package com.example.eduorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.eduorder.OrderNoUtil;
import com.example.eduorder.client.EduCLient;
import com.example.eduorder.client.UcenterClient;

import com.example.eduorder.entity.Order;
import com.example.eduorder.mapper.OrderMapper;
import com.example.eduorder.service.OrderService;
import com.example.ordervo.CourseWebVoOrder;
import com.example.ordervo.UcenterMemberOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-31
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduCLient eduCLient;

    @Autowired
    private UcenterClient ucenterClient;


    @Override
    public String createOrders(String courseId, String memberId) {
        UcenterMemberOrder getuseinfo = ucenterClient.getuseinfo(memberId);

        CourseWebVoOrder getinfo = eduCLient.getinfo(courseId);

        //create Order Object SET data to order
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(getinfo.getTitle());
        order.setCourseCover(getinfo.getCover());
        order.setTeacherName(getinfo.getTeacherName());
        order.setTotalFee(getinfo.getPrice());
        order.setMemberId(memberId);
        order.setMobile(getuseinfo.getMobile());
        order.setNickname(getuseinfo.getNickname());

        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        //order No
        return order.getOrderNo();
    }
}
