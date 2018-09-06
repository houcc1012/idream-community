package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.PagesParam;
import com.idream.commons.lib.dto.achievement.*;
import com.idream.commons.lib.enums.AchievementEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.AchievementInfoMapper;
import com.idream.commons.lib.mapper.AchievementPoolMapper;
import com.idream.commons.lib.mapper.AchievementUserMapper;
import com.idream.commons.lib.model.AchievementInfo;
import com.idream.commons.lib.model.AchievementPool;
import com.idream.service.AdminAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminAchievementServiceImpl implements AdminAchievementService {
    @Autowired
    private AchievementInfoMapper achievementInfoMapper;
    @Autowired
    private AchievementPoolMapper achievementPoolMapper;
    @Autowired
    private AchievementUserMapper achievementUserMapper;

    @Override
    public PagesDto<AchievementPageDto> getAchievementPage(AdminAchievementPageParams param) {
        PageHelper.startPage(param.getPage(), param.getRows());
        List<AchievementPageDto> list = achievementInfoMapper.selectAdminPageList(param);
        PageInfo info = new PageInfo(list);

        return new PagesDto<>(info.getList(), info.getTotal(), param.getPage(), param.getRows());
    }

    @Override
    public void updateAchievementStatus(Integer achievementId, byte status) {
        achievementInfoMapper.updateStatusByAchievementId(achievementId, status);
    }

    @Override
    public void saveAchievementPool(AdminAchievementAwardParams param) {

        if (param.getType().equals(AchievementEnum.PoolType.INTEGRATION.getCode())) {
            String regex = "\\d+";
            boolean matches = param.getAwardValue().matches(regex);
            if (!matches) {
                throw new BusinessException("请输入正确的数字");
            }
        }

        achievementPoolMapper.deleteByAchieveIdAndType(param.getAchievementId(), param.getType());
        AchievementPool record = new AchievementPool();
        record.setAchieveId(param.getAchievementId());
        record.setAwardValue(param.getAwardValue());
        record.setName(param.getAwardValue());
        record.setType(param.getType());
        if (param.getType().equals(AchievementEnum.PoolType.INTEGRATION.getCode())) {
            record.setName("积分+" + param.getAwardValue());
        }
        Date date = new Date();
        record.setCreateTime(date);
        record.setUpdateTime(date);

        achievementPoolMapper.insertSelective(record);
    }

    @Override
    public PagesDto<AdminAchievementUserDto> listUsers(AdminAchievementUserParams params) {
        PageHelper.startPage(params.getPage(), params.getRows());
        List<AdminAchievementUserDto> list = achievementUserMapper.selectCompletedUserByAchievementId(params);
        PageInfo<AdminAchievementUserDto> info = new PageInfo<>(list);
        return new PagesDto<>(info.getList(), info.getTotal(), params.getPage(), params.getRows());
    }

}
