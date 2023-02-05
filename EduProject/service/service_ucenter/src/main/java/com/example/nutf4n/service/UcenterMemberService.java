package com.example.nutf4n.service;

import com.example.nutf4n.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.nutf4n.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-28
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(Object openid);

    Integer countRegister(String day);

}
