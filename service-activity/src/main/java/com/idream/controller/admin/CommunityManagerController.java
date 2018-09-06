package com.idream.controller.admin;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.activity.BookHouseListDto;
import com.idream.service.CommunityInfoListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/communitylist")
@Api(tags = "社区列表(管理端)", description = "CommunityManagerController")
public class CommunityManagerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommunityManagerController.class);
    @Autowired
    private CommunityInfoListService communityInfoListService;

    @RequestMapping(value = "/find/bookhouse", method = RequestMethod.GET)
    @ApiOperation(value = "书屋名称列表", notes = "通过前端传的参数来查询书屋名称", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPublicDto selectBookHouseByAddress(@ApiParam(value = "社区id") @RequestParam(value = "communityId", required = false) Integer communityId,
                                                  @ApiParam(value = "省编码") @RequestParam(value = "provinceCode", required = false) String provinceCode,
                                                  @ApiParam(value = "市编码") @RequestParam(value = "cityCode", required = false) String cityCode,
                                                  @ApiParam(value = "地区编码") @RequestParam(value = "adCode", required = false) String adCode,
                                                  @ApiParam(value = "书屋名称") @RequestParam(value = "bookHouseName", required = false) String bookHouseName) {
        List<BookHouseListDto> data = communityInfoListService.selectBookHouseListByAdCode(provinceCode, cityCode, adCode, bookHouseName, communityId);
        return JSONPublicDto.returnSuccessData(RetCodeConstants.SUCESS, "查询成功", data);
    }


}
