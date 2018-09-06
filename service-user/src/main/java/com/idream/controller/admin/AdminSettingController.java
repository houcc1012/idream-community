package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.setting.AdminBannerInfoDto;
import com.idream.commons.lib.dto.setting.OperateSensitiveWordParams;
import com.idream.commons.lib.dto.setting.SearchSensitiveWordsDto;
import com.idream.commons.lib.dto.setting.SearchSensitiveWordsParams;
import com.idream.commons.lib.dto.user.AdminBannerImageListDto;
import com.idream.commons.lib.dto.user.AdminBannerImageListParams;
import com.idream.commons.lib.dto.user.AdminDisplayBannerParams;
import com.idream.service.BannerImageService;
import com.idream.service.SensitiveWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;

/**
 * @Author: jeffery
 * @Date: 2018/6/8 16:38
 */
@RestController
@RequestMapping("/admin/banner")
@Api(tags = "设置", description = "AdminSettingController")
public class AdminSettingController {

    @Autowired
    private BannerImageService bannerImageService;

    @Autowired
    private SensitiveWordService sensitiveWordService;

    @RequestMapping(value = "/select/list/banner", method = RequestMethod.GET)
    @ApiOperation(value = "banner图列表", notes = "banner图列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<AdminBannerImageListDto>> selectBannerImageList(AdminBannerImageListParams adminBannerImageListParams) {
        PagesDto<AdminBannerImageListDto> data = bannerImageService.selectBannerImageList(adminBannerImageListParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "/update/ondisplay", method = RequestMethod.PUT)
    @ApiOperation(value = "修改banner为上架状态", notes = "修改banner为上架状态", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateOnDisplay(@RequestBody AdminDisplayBannerParams adminDisplayBannerParams) {
        bannerImageService.updateOnDisplay(adminDisplayBannerParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "banner上架成功", null);
    }

    @RequestMapping(value = "/update/outdisplay", method = RequestMethod.PUT)
    @ApiOperation(value = "修改banner为下架状态", notes = "修改banner为下架状态", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateOutDisplay(@RequestBody AdminDisplayBannerParams adminDisplayBannerParams) {
        bannerImageService.updateOutDisplay(adminDisplayBannerParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "banner下架成功", null);
    }

    @RequestMapping(value = "/add/newbanner", method = RequestMethod.POST)
    @ApiOperation(value = "新增banner", notes = "新增banner", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addNewBanner(@ApiIgnore Integer authUserId, @RequestBody AdminBannerInfoDto adminAddNewBannerParams) {
        bannerImageService.addNewBanner(authUserId, adminAddNewBannerParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "banner新增成功", null);
    }

    @RequestMapping(value = "/update/banner", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑banner", notes = "编辑banner", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<String> updateBanner(@ApiIgnore Integer authUserId, @RequestBody AdminBannerInfoDto adminUpdateBannerParams) {
        bannerImageService.updateBanner(authUserId, adminUpdateBannerParams);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "banner编辑成功", null);

    }

    @RequestMapping(value = "/select/banner/{bannerId}", method = RequestMethod.GET)
    @ApiOperation(value = "编辑banner回显数据", notes = "编辑banner回显数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<AdminBannerInfoDto> selectBannerOfUpdate(@ApiParam(value = "banner主键") @NotNull(message = "banner主键不为空") @PathVariable("bannerId") Integer bannerId) {
        AdminBannerInfoDto data = bannerImageService.selectBannerOfUpdateByBannerId(bannerId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑banner回显数据成功", data);
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation("删除banner,下架的才能删除")
    public JSONPublicDto delBanner(@NotNull @RequestParam("bannerId") Integer bannerId) {
        bannerImageService.deleteBanner(bannerId);
        return JSONPublicDto.returnSuccessData("删除成功");
    }

    @RequestMapping(value = "/sensitive/word", method = RequestMethod.GET)
    @ApiOperation(value = "敏感字列表查询", notes = "敏感字列表查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<SearchSensitiveWordsDto>> getSensitiveWords (SearchSensitiveWordsParams param) {
        PagesDto<SearchSensitiveWordsDto> pagesDto = sensitiveWordService.getSensitiveWords(param);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", pagesDto);
    }

    @RequestMapping(value = "/sensitive/word", method = RequestMethod.POST)
    @ApiOperation(value = "敏感字新增", notes = "敏感字新增", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto addSensitiveWord (@RequestBody JSONPublicParam<OperateSensitiveWordParams> param) {
        sensitiveWordService.addSensitiveWord(param);
        return JSONPublicDto.returnSuccessData("新增成功");
    }

    @RequestMapping(value = "/sensitive/word", method = RequestMethod.PUT)
    @ApiOperation(value = "敏感字修改", notes = "敏感字修改", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateSensitiveWord (@RequestBody JSONPublicParam<OperateSensitiveWordParams> param) {
        sensitiveWordService.updateSensitiveWord(param);
        return JSONPublicDto.returnSuccessData("修改成功");
    }

    @RequestMapping(value = "/sensitive/word", method = RequestMethod.DELETE)
    @ApiOperation(value = "敏感字删除", notes = "敏感字删除", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteSensitiveWord (@ApiIgnore @RequestParam("authUserId") Integer authUserId,
                                                                              @ApiParam("主键ID")@RequestParam Integer id) {
        sensitiveWordService.deleteSensitiveWord(authUserId, id);
        return JSONPublicDto.returnSuccessData("删除成功");
    }
}
