package com.example.guliMall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.utils.PageUtils;
import com.example.guliMall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author cc
 * @email ccsdsg2016@gmail.com
 * @date 2023-02-18 13:24:47
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

