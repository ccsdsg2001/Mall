package com.example.eduorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.eduorder.controller.HttpClient;
import com.example.eduorder.entity.Order;
import com.example.eduorder.entity.PayLog;
import com.example.eduorder.mapper.PayLogMapper;
import com.example.eduorder.service.OrderService;
import com.example.eduorder.service.PayLogService;

import com.github.wxpay.sdk.WXPayUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-31
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private OrderService orderService;

    @Override
    public Map createNative(String orderNo) {

        try {
            //order info by order id

            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no", orderNo);
            Order order = orderService.getOne(wrapper);

            //qrcode by map
            Map m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle());
            m.put("out_trade_no", orderNo);
            m.put("total_fee", order.getTotalFee().multiply(new
                    BigDecimal("100")).longValue() + "");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url",
                    "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            m.put("trade_type", "NATIVE");


            HttpClient client = new
                    HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            ;
            client.setXmlParam(WXPayUtil.generateSignedXml(m,
                    "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //Http request USING xml


            Map map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));

            return map;
        } catch (Exception e) {
            throw new fuliexception(20001, "fail");

        }
    }

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            //1 aŁﬂ
            Map m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //2 a!Âhp
            HttpClient client = new
                    HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,
                    "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();
            //3 aRí»ØZ¥ﬂ	
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //6 aîMap
            //7 aRí
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrdersStatus(Map<String, String> map) {
        //获取订单id
        String orderNo = map.get("out_trade_no");
        //根据订单id查询订单信息
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);

        if(order.getStatus().intValue() == 1) return;
        order.setStatus(1);
        orderService.updateById(order);

        //:c¦±°½
        PayLog payLog=new PayLog();
        payLog.setOrderNo(order.getOrderNo());//¦±èƒ|
        payLog.setPayTime(new Date());
        payLog.setPayType(1);//¦±	ËŸ
        payLog.setTotalFee(order.getTotalFee());//9Á-(s)
        payLog.setTradeState(map.get("trade_state"));//¦±ÿ
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));
        baseMapper.insert(payLog);//¬Æž¦±°½V
    }
}
