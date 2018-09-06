package com.idream.controller.admin;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.PagesParam;
import com.idream.commons.lib.dto.achievement.*;
import com.idream.commons.lib.enums.AchievementEnum;
import com.idream.service.AdminAchievementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author charles
 */
@RestController
@RequestMapping("admin/achievement")
@Api(tags = "后台成就接口")
public class AdminAchievementController {
    @Autowired
    private AdminAchievementService adminAchievementService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("成就显示接口")
    public JSONPublicDto<PagesDto<AchievementPageDto>> list(AdminAchievementPageParams param) {
        PagesDto<AchievementPageDto> a = adminAchievementService.getAchievementPage(param);
        return JSONPublicDto.returnSuccessData(a);
    }

    @RequestMapping(value = "/up", method = RequestMethod.PUT)
    @ApiOperation("上架")
    public JSONPublicDto achievementUp(@RequestBody JSONPublicParam<AdminAchievementUpdateParams> param) {
        adminAchievementService.updateAchievementStatus(param.getRequestParam().getAchievementId(), AchievementEnum.Status.UP.getCode());
        return JSONPublicDto.returnSuccessData("上架成功");
    }

    @RequestMapping(value = "/down", method = RequestMethod.PUT)
    @ApiOperation("下架")
    public JSONPublicDto achievementDown(@RequestBody JSONPublicParam<AdminAchievementUpdateParams> param) {
        adminAchievementService.updateAchievementStatus(param.getRequestParam().getAchievementId(), AchievementEnum.Status.DOWN.getCode());
        return JSONPublicDto.returnSuccessData("下架成功");
    }

    @RequestMapping(value = "/award", method = RequestMethod.POST)
    @ApiOperation("成就奖励添加")
    public JSONPublicDto award(@RequestBody JSONPublicParam<AdminAchievementAwardParams> param) {
        adminAchievementService.saveAchievementPool(param.getRequestParam());
        return JSONPublicDto.returnSuccessData("添加成功");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiOperation("成就明细")
    public JSONPublicDto<PagesDto<AdminAchievementUserDto>> userList(AdminAchievementUserParams params) {
        PagesDto<AdminAchievementUserDto> page = adminAchievementService.listUsers(params);
        return JSONPublicDto.returnSuccessData(page);
    }
}

