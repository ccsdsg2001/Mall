package com.example.nutf4n.mapper;

import com.example.nutf4n.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-28
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer countRegister(String day);
}
