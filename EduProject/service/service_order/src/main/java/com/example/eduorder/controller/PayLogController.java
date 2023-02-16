package com.example.eduorder.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.R;
import com.example.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-31
 */
@RestController
@RequestMapping("/eduorder/paylog")
//@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    //payment by orderiD
    @GetMapping("createNative/{orderNo}")
    public R create(@PathVariable String orderNo){
        //RETURN info include qrcode and info
        Map map=payLogService.createNative(orderNo);
        return R.ok().data(map);
    }

    //order status
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPay(@PathVariable String orderNo){
       Map<String,String> map = payLogService.queryPayStatus(orderNo);
       if(map==null){
           return R.error().message("fail");
       }

        if(map.get("trade_state").equals("SUCCESS")){
            payLogService.updateOrdersStatus(map);
            return R.ok().message("success");

        }
        return R.ok().code(25000).message("loading");


    }

}

