package com.example.guliMall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.utils.PageUtils;
import com.example.common.utils.Query;

import com.example.guliMall.product.dao.PmsAttrDao;
import com.example.guliMall.product.entity.PmsAttrEntity;
import com.example.guliMall.product.service.PmsAttrService;


@Service("pmsAttrService")
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrDao, PmsAttrEntity> implements PmsAttrService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsAttrEntity> page = this.page(
                new Query<PmsAttrEntity>().getPage(params),
                new QueryWrapper<PmsAttrEntity>()
        );

        return new PageUtils(page);
    }

}