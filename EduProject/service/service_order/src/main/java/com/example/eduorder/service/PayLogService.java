package com.example.eduorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.eduorder.entity.PayLog;

import java.util.Map;


/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-31
 */
public interface PayLogService extends IService<PayLog> {

    Map createNative(String orderNo);

    //order info by orderID
    Map<String, String> queryPayStatus(String orderNo);

    //update status and log
    void updateOrdersStatus(Map<String, String> map);
}
