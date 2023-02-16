package com.example.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.JwtUtils;
import com.example.R;
import com.example.eduorder.entity.Order;
import com.example.eduorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-31
 */
@RestController
@RequestMapping("/eduorder/order")
//@CrossOrigin
public class OrderController {


    @Autowired
    private OrderService orderService;

    //item code
    @PostMapping("createOrder/{courseId}")
    public R save(@PathVariable String courseId, HttpServletRequest request){
        String orderNo= orderService.createOrders(courseId, JwtUtils.getMemberIdByJwtToken(request));

        return  R.ok().data("orderId",orderNo);
    }


    //order info by orderId
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){
        QueryWrapper<Order> wrapper =new QueryWrapper<>();

        wrapper.eq("order_no", orderId);

        Order one = orderService.getOne(wrapper);
        return R.ok().data("item",one);


    }

    //order status by course id and userid
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isbuy(@PathVariable String courseId,@PathVariable String memberId){
        QueryWrapper<Order> wrapper= new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id", memberId);
        wrapper.eq("status", 1);
        int count = orderService.count(wrapper);
        if(count>0){
            return true;
        }else {
            return false;
        }
    }


}

