package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.SystemConfigCodeConstans;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.setting.AdminBannerImageTypeDto;
import com.idream.commons.lib.dto.setting.AdminBannerInfoDto;
import com.idream.commons.lib.dto.setting.AdminBannerTypeDto;
import com.idream.commons.lib.dto.user.*;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.BannerInfo;
import com.idream.commons.lib.model.BannerType;
import com.idream.service.BannerImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: jeffery
 * @Date: 2018/6/8 19:22
 */
@Service
public class BannerImageServiceImpl implements BannerImageService {

    @Autowired
    private BannerInfoMapper bannerInfoMapper;
    @Autowired
    private BannerTypeMapper bannerTypeMapper;
    @Autowired
    private BannerImageMapper bannerImageMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public PagesDto<AdminBannerImageListDto> selectBannerImageList(AdminBannerImageListParams adminBannerImageListParams) {
        PageHelper.startPage(adminBannerImageListParams.getPage(), adminBannerImageListParams.getRows());
        List<AdminBannerImageListDto> list = bannerInfoMapper.selectAdminPage(adminBannerImageListParams);
        PageInfo<AdminBannerImageListDto> info = new PageInfo<>(list);
        return new PagesDto(list, info.getTotal(), adminBannerImageListParams.getPage(), adminBannerImageListParams.getRows());
    }

    @Override
    public void updateOnDisplay(AdminDisplayBannerParams adminDisplayBannerParams) {
        Integer typeId = bannerInfoMapper.selectByPrimaryKey(adminDisplayBannerParams.getBannerId()).getTypeId();
        if(typeId != null){
            int num = bannerInfoMapper.selectOnlineNumByTypeId(typeId);
            int maxOnlineNum = bannerTypeMapper.selectByPrimaryKey(typeId).getMaxUpNum();
            if(num > maxOnlineNum - 1){
                throw new BusinessException("最多上架"+maxOnlineNum+"个，请检查！");
            }
            BannerInfo bannerInfo = new BannerInfo();
            bannerInfo.setId(adminDisplayBannerParams.getBannerId());
            bannerInfo.setDisplayEnable(true);
            bannerInfo.setUpdateTime(new Date());
            bannerInfoMapper.updateByPrimaryKeySelective(bannerInfo);
        }else{
            throw new BusinessException("banner信息异常，请检查");
        }

    }

    @Override
    public void updateOutDisplay(AdminDisplayBannerParams adminDisplayBannerParams) {
        //修改banner为下架状态
        BannerInfo bannerInfo = new BannerInfo();
        bannerInfo.setDisplayEnable(false);
        bannerInfo.setId(adminDisplayBannerParams.getBannerId());
        bannerInfo.setUpdateTime(new Date());
        bannerInfoMapper.updateByPrimaryKeySelective(bannerInfo);
    }

    @Override
    public void addNewBanner(Integer userId, AdminBannerInfoDto adminAddNewBannerParams) {
        checkImages(adminAddNewBannerParams.getType(), adminAddNewBannerParams.getJumpLink());
        addBannerInfo(userId, adminAddNewBannerParams);
    }

    private void checkImages(Byte type, String jumpLink) {
        if (type.equals(SystemEnum.BannerImageType.ACTIVITY_LIST.getCode())) {
            boolean matches = jumpLink.matches("\\d+(,\\d+)*");
            if (!matches) {
                throw new BusinessException("活动列表请输入活动id,用英文逗号分隔");
            }
        }
        if (type.equals(SystemEnum.BannerImageType.ACTIVITY_RECOMMEND.getCode())) {
            boolean matches = jumpLink.matches("^\\d+$");
            if (!matches) {
                throw new BusinessException("活动推荐,请输入单个活动id,");
            }
        }
    }

    private BannerInfo addBannerInfo(Integer userId, AdminBannerInfoDto adminAddNewBannerParams) {
        BannerInfo record = new BannerInfo();
        record.setDescription(adminAddNewBannerParams.getDescription());
        record.setTypeId(adminAddNewBannerParams.getTypeId());
        record.setType(adminAddNewBannerParams.getType());
        record.setDisplayEnable(false);
        record.setUserId(userId);
        record.setImageUrl(adminAddNewBannerParams.getImageUrl());
        record.setJumpLink(adminAddNewBannerParams.getJumpLink());
        Date date = new Date();
        record.setRealName(userInfoMapper.selectByPrimaryKey(userId).getRealName());
        record.setSorted(1);
        record.setUpdateTime(date);
        record.setCreateTime(date);
        bannerInfoMapper.insertSelective(record);
        return record;
    }

    @Override
    public AdminBannerInfoDto selectBannerOfUpdateByBannerId(Integer bannerId) {

        AdminBannerInfoDto dto = new AdminBannerInfoDto();
        BannerInfo info = bannerInfoMapper.selectByPrimaryKey(bannerId);
        dto.setTypeId(info.getTypeId());
        dto.setBannerId(info.getId());
        dto.setDescription(info.getDescription());
        dto.setType(info.getType());
        dto.setImageUrl(info.getImageUrl());
        dto.setJumpLink(info.getJumpLink());
        return dto;
    }

    @Override
    public void updateBanner(Integer userId, AdminBannerInfoDto adminUpdateBannerParams) {
        checkImages(adminUpdateBannerParams.getType(), adminUpdateBannerParams.getJumpLink());
        BannerInfo bannerInfo = new BannerInfo();
        bannerInfo.setUpdateTime(new Date());
        bannerInfo.setUserId(userId);
        bannerInfo.setRealName(userInfoMapper.selectByPrimaryKey(userId).getRealName());
        bannerInfo.setJumpLink(adminUpdateBannerParams.getJumpLink());
        bannerInfo.setImageUrl(adminUpdateBannerParams.getImageUrl());
        bannerInfo.setTypeId(adminUpdateBannerParams.getTypeId());
        bannerInfo.setType(adminUpdateBannerParams.getType());
        bannerInfo.setDescription(adminUpdateBannerParams.getDescription());
        bannerInfo.setId(adminUpdateBannerParams.getBannerId());
        bannerInfoMapper.updateByPrimaryKeySelective(bannerInfo);

    }

    @Override
    public List<AdminBannerUrlParams> selectWXBannerList() {

        Integer typeId = 1;
        List<AdminBannerUrlParams> list = bannerInfoMapper.selectDisplayByTypeId(typeId);
        if (list.isEmpty()) {
            AdminBannerUrlParams adminBannerUrlParams = new AdminBannerUrlParams();
            String banner_mini_image = systemConfigMapper.selectByConfigCode(SystemConfigCodeConstans.BANNER_MINI_IMAGE);
            String banner_mini_jump_link = systemConfigMapper.selectByConfigCode(SystemConfigCodeConstans.BANNER_MINI_JUMP_LINK);
            adminBannerUrlParams.setImageUrl(banner_mini_image);
            adminBannerUrlParams.setJumpLink(banner_mini_jump_link);
            adminBannerUrlParams.setType(SystemEnum.BannerImageType.NOMARL.getCode());
            list.add(adminBannerUrlParams);
        }
        return list;

    }

    @Override
    public List<AdminBannerTypeDto> listType() {

        List<BannerType> list = bannerTypeMapper.selectAll();
        return list.stream().map(i -> {
            AdminBannerTypeDto dto = new AdminBannerTypeDto();
            dto.setTypeId(i.getId());
            dto.setTypeName(i.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<AdminBannerImageTypeDto> listImageType() {
        List<AdminBannerImageTypeDto> list = new ArrayList<>();

        AdminBannerImageTypeDto dto = new AdminBannerImageTypeDto();
        dto.setTypeId(SystemEnum.BannerImageType.NOMARL.getCode());
        dto.setTypeName("普通");

        AdminBannerImageTypeDto dto2 = new AdminBannerImageTypeDto();
        dto.setTypeId(SystemEnum.BannerImageType.ACTIVITY_LIST.getCode());
        dto.setTypeName("活动列表");

        AdminBannerImageTypeDto dto3 = new AdminBannerImageTypeDto();
        dto.setTypeId(SystemEnum.BannerImageType.ACTIVITY_RECOMMEND.getCode());
        dto.setTypeName("活动推荐");

        list.add(dto);
        list.add(dto2);
        list.add(dto3);
        return list;
    }

    @Override
    public void deleteBanner(Integer bannerId) {
        BannerInfo bannerInfo = bannerInfoMapper.selectByPrimaryKey(bannerId);
        if (bannerInfo != null && bannerInfo.getDisplayEnable()) {
            throw new BusinessException("上架的banner设置不能删除");
        }
        bannerInfoMapper.deleteByPrimaryKey(bannerId);
    }
}
