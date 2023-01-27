package com.example.nutf4n.service;

import com.example.nutf4n.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author NutF4n
 * @since 2023-01-27
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectALLBanner();

}
