package com.example.eduorder.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.eduorder.entity.Order;


/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-31
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberIdByJwtToken);
}
