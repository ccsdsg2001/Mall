package com.example.nutf4n.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.nutf4n.entity.CrmBanner;
import com.example.nutf4n.mapper.CrmBannerMapper;
import com.example.nutf4n.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-27
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public List<CrmBanner> selectALLBanner() {
        //show data  by id and sort by desc and limit 2
        QueryWrapper<CrmBanner> wrapper =new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(wrapper);
        return list;
    }
}
