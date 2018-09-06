package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.dto.admin.IntegrationConfigDto;
import com.idream.commons.lib.dto.admin.IntegrationConfigParams;
import com.idream.commons.lib.model.ActivityTag;
import com.idream.service.ActivityTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/activitytag")
@Api(tags = "活动标签库(管理端)", description = "ActivityTagController")
public class ActivityTagController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommunityManagerController.class);

    @Autowired
    private ActivityTagService activityTagService;

    //创建活动时根据活动类型选择回显标签
    @RequestMapping(value = "secondTag", method = RequestMethod.GET)
    @ApiOperation(value = "查询活动类型下的标签", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<List<AppActivityTypeRelateTagResponseDto>> listByTypeId(@ApiParam(value = "typeId", required = true) @RequestParam("typeId") Integer typeId) {
        List<AppActivityTypeRelateTagResponseDto> list = activityTagService.listTagByTypeId(typeId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", list);
    }

    //活动类型库列表展示
    @RequestMapping(value = "/type/library", method = RequestMethod.GET)
    @ApiOperation(value = "活动类型库", notes = "通过前端传的参数条件查询活动类型库", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<PagesDto<ActivityTypeLibraryResponseDto>> selectActivityTypeLibrary(@ApiParam(value = "活动类型名") @RequestParam(value = "type", required = false) String type,
                                                                                             @ApiParam(value = "当前页", required = true) @RequestParam(value = "page") Integer page,
                                                                                             @ApiParam(value = "每页多少条", required = true) @RequestParam(value = "rows") Integer rows) {
        PagesDto<ActivityTypeLibraryResponseDto> data = activityTagService.selectActivityTypeLibraryByExample(type, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    //新增活动类型
    @RequestMapping(value = "/add/activitytype", method = RequestMethod.POST)
    @ApiOperation(value = "新增活动类型", notes = "通过前端传的参数来新增活动类型", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto insertActivityType(@ApiParam(value = "新增活动类型请求参数", required = true) @RequestBody JSONPublicParam<ActivityTypeAddRequestDto> param) {
        int i = activityTagService.insertActivityType(param);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SAVE_FAILED, "新增活动类型失败", null);
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "新增活动类型成功", null);
    }

    //编辑活动类型
    @RequestMapping(value = "/update/activitytype", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑活动类型", notes = "通过前端传的参数编辑活动类型", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateActivityType(@ApiParam(value = "修改活动类型请求参数", required = true) @RequestBody JSONPublicParam<ActivityUpdateTypeRequestDto> param) {
        int i = activityTagService.updateActivityType(param);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SAVE_FAILED, "编辑活动类型失败", null);
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑活动类型成功", null);
    }

    //删除活动类型
    @RequestMapping(value = "/delete/activitytype", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除活动类型", notes = "通过前端传的参数删除活动类型", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteActivityType(@ApiParam(value = "删除活动类型请求参数") @RequestParam Integer typeId) {
        int i = activityTagService.deleteActivityTypeById(typeId);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SAVE_FAILED, "删除活动类型失败", null);
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除活动类型成功", null);
    }

    //查询所有的一级活动标签和二级标签
    @RequestMapping(value = "/all/ActivityTags", method = RequestMethod.GET)
    @ApiOperation(value = "查询一级和二级标签(关联到活动类型)", notes = "一二级活动标签", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto selectAllActivityTags() {
        List<ActivityTagTreeResponseDto> dto = activityTagService.selectAllActivityTags();
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);
    }

    //查询活动类型关联的活动标签
    @RequestMapping(value = "/type/tags", method = RequestMethod.GET)
    @ApiOperation(value = "活动类型关联的活动标签", notes = "查询活动类型关联的活动标签", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto selectActivityTagsAssociationType(@ApiParam(value = "活动类型id", required = true) @RequestParam Integer typeId) {
        List<ActivityTagTreeResponseDto> dto = activityTagService.selectActivityTagsByTypeId(typeId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", dto);
    }

    //关联活动类型和活动标签
    @RequestMapping(value = "type/relate/tag", method = RequestMethod.POST)
    @ApiOperation(value = "活动类型关联活动标签", notes = "关联活动类型和活动标签", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto insertActivityTypeRelateTag(@RequestBody ActivityTypeRelateTagRequestDto requestDto) {

        activityTagService.insertActivityTypeRelateTag(requestDto);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "关联成功", null);
    }

    //新增活动标签
    @RequestMapping(value = "/add/activity/tag", method = RequestMethod.POST)
    @ApiOperation(value = "新增活动标签", notes = "新增一二三级活动标签", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto insertActivityTag(@ApiParam(value = "新增活动标签请求参数", required = true) @RequestBody JSONPublicParam<ActivityTagAddRequestDto> param) {
        int i = activityTagService.insertAllKindActivityTag(param);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SAVE_FAILED, "新增活动标签失败", null);
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "新增活动标签成功", null);
    }

    //编辑活动标签
    @RequestMapping(value = "/update/activity/tag", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑活动标签", notes = "编辑活动标签名称", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto updateActivityTag(@ApiParam(value = "编辑活动标签请求参数", required = true) @RequestBody JSONPublicParam<ActivityTagUpdateRequestDto> param) {
        int i = activityTagService.updateActivityTagById(param);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SAVE_FAILED, "保存失败", null);
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "编辑活动标签成功", null);
    }

    //删除活动标签
    @RequestMapping(value = "/delete/activity/tag", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除活动标签", notes = "删除活动标签", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteActivityTag(@ApiParam(value = "标签id") @RequestParam Integer tagId) {

        activityTagService.deleteActivityTagById(tagId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除活动标签成功", null);
    }

    @RequestMapping(value = "/tag/by/example", method = RequestMethod.GET)
    @ApiOperation(value = "活动标签库模糊查询标签和父级标签", notes = "模糊查询标签和所有父级标签", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto selectTagByExample(@ApiParam(value = "活动标签名") @RequestParam(value = "label", required = false) String label,
                                            @ApiParam(value = "当前页", required = true) @RequestParam(value = "page", required = true) int page,
                                            @ApiParam(value = "每页多少条", required = true) @RequestParam(value = "rows", required = true) int rows) {
        PagesDto data = activityTagService.selectActivityTagListByExample(label, page, rows);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    @RequestMapping(value = "saveActivityScore", method = RequestMethod.POST)
    @ApiOperation(value = "活动积分设置", notes = "活动积分设置", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto saveActivityScore(@RequestBody IntegrationConfigParams params) {
        activityTagService.saveActivityScore(params);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "保存成功", null);
    }

    @RequestMapping(value = "selectActivityScore", method = RequestMethod.GET)
    @ApiOperation(value = "查询积分设置", notes = "查询积分设置", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto<IntegrationConfigDto> selectActivityScore() {
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", activityTagService.selectActivityScore());
    }

}
