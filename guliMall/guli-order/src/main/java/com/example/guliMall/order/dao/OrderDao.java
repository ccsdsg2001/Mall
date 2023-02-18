package com.example.guliMall.order.dao;

import com.example.guliMall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author cc
 * @email ccsdsg2016@gmail.com
 * @date 2023-02-18 13:33:33
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
