package com.idream.service;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.setting.AdminBannerImageTypeDto;
import com.idream.commons.lib.dto.setting.AdminBannerInfoDto;
import com.idream.commons.lib.dto.setting.AdminBannerTypeDto;
import com.idream.commons.lib.dto.user.*;

import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/6/8 19:19
 */
public interface BannerImageService {

    //banner图片列表
    PagesDto<AdminBannerImageListDto> selectBannerImageList(AdminBannerImageListParams adminBannerImageListParams);

    //修改banner为上架状态
    void updateOnDisplay(AdminDisplayBannerParams adminDisplayBannerParams);

    //修改banner为下架状态
    void updateOutDisplay(AdminDisplayBannerParams adminDisplayBannerParams);

    //新增banner
    void addNewBanner(Integer userId, AdminBannerInfoDto adminAddNewBannerParams);

    //编辑banner数据回显
    AdminBannerInfoDto selectBannerOfUpdateByBannerId(Integer bannerId);

    //编辑banner
    void updateBanner(Integer userId, AdminBannerInfoDto adminUpdateBannerParams);

    //小程序banner显示
    List<AdminBannerUrlParams> selectWXBannerList();

    /**
     * banner类型
     */
    List<AdminBannerTypeDto> listType();

    /**
     * banner图片类型
     */
    List<AdminBannerImageTypeDto> listImageType();

    /**
     * 删除bannerId
     *
     * @param bannerId
     */
    void deleteBanner(Integer bannerId);
}
