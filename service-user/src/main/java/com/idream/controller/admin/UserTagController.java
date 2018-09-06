package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.user.AdminAddUserTagParams;
import com.idream.commons.lib.dto.user.AdminDeleteUserTagParams;
import com.idream.commons.lib.dto.user.UserTagResponseDto;
import com.idream.service.UserTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/admin/usertag")
@Api(tags = "用户标签库(管理端)", description = "UserTagController")
public class UserTagController {
    @Autowired
    private UserTagService userTagService;

    /**
     * 查询
     *
     * @param
     * @param label
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "用户标签库", notes = "通过前端传的参数来查询标签列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto selectCommunityBookHouseByExample(@ApiParam(value = "用户标签名", required = false) @RequestParam(value = "label", required = false) String label) {

        List<UserTagResponseDto> data = userTagService.selectUserTagListByLabel(label);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }

    /**
     * @param @param  authUserId
     * @param @param  label
     * @param @return
     *
     * @return JSONPublicDto
     */
    @RequestMapping(value = "/add/usertag", method = RequestMethod.POST)
    @ApiOperation(value = "新增和编辑 用户标签库", notes = "通过前端传的参数来新增和编辑用户标签", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto insertUserTag(@ApiIgnore @RequestParam(value = "authUserId") int authUserId, @RequestBody AdminAddUserTagParams adminAddUserTagParams) {
        int i = userTagService.insertUserTag(authUserId, adminAddUserTagParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SAVE_FAILED, "保存失败", null);
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "保存成功", null);
    }

    /**
     * @param @param  id
     * @param @return
     *
     * @return JSONPublicDto
     */
    @RequestMapping(value = "/delete/usertag", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除 用户标签库", notes = "通过前端传的参数来删除用户标签", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto deleteUserTag(AdminDeleteUserTagParams adminDeleteUserTagParams) {
        int i = userTagService.deleteUserTag(adminDeleteUserTagParams);
        if (i == 0) {
            return JSONPublicDto.returnSuccessData(RetCodeConstants.SAVE_FAILED, "删除失败", null);
        }
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "删除成功", null);
    }

}
