package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.user.*;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.HelpInfoMapper;
import com.idream.commons.lib.mapper.HelpTypeMapper;
import com.idream.commons.lib.mapper.OpenCityMapper;
import com.idream.commons.lib.model.HelpInfo;
import com.idream.commons.lib.model.HelpType;
import com.idream.commons.lib.model.OpenCity;
import com.idream.service.AdminPlatFromSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @Description :
 * @Created by xiaogang on 2018/7/19.
 */
@Service
public class AdminPlatFromSettingServiceImpl implements AdminPlatFromSettingService {

    @Autowired
    private HelpInfoMapper helpInfoMapper;

    @Resource
    private HelpTypeMapper helpTypeMapper;

    @Autowired
    private OpenCityMapper openCityMapper;

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    public static final int sorted = 1;

    /**
     * @param : helpInfoParams
     */
    @Override
    public void saveHelpInfo(HelpInfoParams helpInfoParams) {
        HelpInfo helpInfo = new HelpInfo();
        Date date = new Date();

        helpInfo.setContent(helpInfoParams.getContent());
        helpInfo.setTitle(helpInfoParams.getTitle());
        helpInfo.setTypeId(helpInfoParams.getTypeId());
        helpInfo.setCreateTime(date);
        helpInfo.setUpdateTime(date);

        helpInfoMapper.insert(helpInfo);
    }

    /**
     * @param : helpInfoParams
     */
    @Override
    public void updateHelpInfo(HelpInfoParams helpInfoParams) {
        HelpInfo helpInfo = new HelpInfo();
        Date date = new Date();

        helpInfo.setId(helpInfoParams.getId());
        helpInfo.setContent(helpInfoParams.getContent());
        helpInfo.setTitle(helpInfoParams.getTitle());
        helpInfo.setTypeId(helpInfoParams.getTypeId());
        helpInfo.setUpdateTime(date);

        helpInfoMapper.updateByPrimaryKeySelective(helpInfo);
    }

    /**
     * @param : id
     */
    @Override
    public void deleteHelpInfoById(int id) {

        int res = helpInfoMapper.deleteByPrimaryKey(id);
        logger.info("删除了" + res + "条记录");
        if (res == 0) {
            throw new BusinessException("未找到id为" + id + "的数据，删除失败！");
        }
    }

    /**
     * @param : params
     */
    @Override
    public PagesDto<HelpInfoDto> selectHelpInfoList(HelpInfoSearchParams params) {

        int page = params.getPage();
        int rows = params.getRows();
        PageHelper.startPage(page, rows);
        List<HelpInfoDto> list = helpInfoMapper.selectAllList(params.getTitle(), params.getTypeId());

        PageInfo pageInfo = new PageInfo(list);
        return new PagesDto<>(list, pageInfo.getTotal(), page, rows);
    }

    /**
     * @param : id
     */
    @Override
    public HelpInfoDto selectHelpInfoById(int id) {
        return helpInfoMapper.selectHelpInfoById(id);
    }

    /**
     * @param :
     */
    @Override
    public List<HelpTypeDto> selectHelpType() {
        return helpTypeMapper.selectAll();
    }

    /**
     * @param :
     */
    @Override
    public List<AdminOpenCityDto> selectOpenCity() {
        return openCityMapper.selectOpenCity();
    }

    /**
     * @param : adminOpenCityParams
     */
    @Override
    public void saveOpenCity(AdminOpenCityParams adminOpenCityParams) {
        OpenCity openCity = new OpenCity();
        Date date = new Date();

        openCity.setCityCode(adminOpenCityParams.getCityCode());
        openCity.setCityName(adminOpenCityParams.getCityName());
        openCity.setSorted(sorted);
        openCity.setCreateTime(date);
        openCity.setUpdateTime(date);
        AdminOpenCityDto city = openCityMapper.selectOpenByCityCode(adminOpenCityParams.getCityCode());
        if (city == null) {
            openCityMapper.insert(openCity);
        } else {
            throw new BusinessException("请勿重复添加");
        }
    }

    /**
     * @param : cityCode
     */
    @Override
    public void deleteOpenCityByCityCode(String cityCode) {
        int res = openCityMapper.deleteOpenCityByCityCode(cityCode);
        logger.info("删除了" + res + "条记录");
        if (res == 0) {
            throw new BusinessException("未找到cityCode为" + cityCode + "的数据，删除失败！");
        }
    }

    /**
     * @param : typeId
     */
    @Override
    public List<AppHelpInfoDto> selectHelpTitleByTypeId(int typeId) {
        return helpInfoMapper.selectHelpTitleByTypeId(typeId);
    }

    /**
     * @param : title
     */
    @Override
    public String selectContentByHelpTitle(int id) {
        return helpInfoMapper.selectContentByHelpTitle(id);
    }

    @Override
    public List<MiniProgramOpenCityDto> selectMiniOpenCity() {
        return openCityMapper.selectMiniOpenCity();
    }


}
