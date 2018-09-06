package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.AdminUserInfoRequestDto;
import com.idream.commons.lib.dto.activity.AdminUserInfoResponseDto;
import com.idream.commons.lib.dto.admin.*;
import com.idream.commons.lib.dto.app.CommunityActivityListResponseDto;
import com.idream.commons.lib.dto.app.CommunityActivityRequestDto;
import com.idream.commons.lib.dto.app.CommunityNameListResponseDto;
import com.idream.commons.lib.dto.app.CommunitySetParams;
import com.idream.commons.lib.dto.marketing.AdminAwardPoolDto;
import com.idream.commons.lib.dto.marketing.IntegrationPoolDto;
import com.idream.commons.lib.enums.BusinessEnum;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.*;
import com.idream.commons.lib.util.PageRowsUtils;
import com.idream.commons.lib.util.StringFilterUtils;
import com.idream.service.RegionGroupInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Author: jeffery
 * @Date: 2018/6/13 19:15
 */
@Service
public class RegionGroupInfoServiceImpl implements RegionGroupInfoService {

    @Autowired
    private RegionGroupInfoMapper regionGroupInfoMapper;
    @Autowired
    private ImageInfoMapper imageInfoMapper;
    @Autowired
    private RegionGroupRelationMapper regionGroupRelationMapper;
    @Autowired
    private BookExtensionMapper bookExtensionMapper;
    @Autowired
    private RegionInfoMapper regionInfoMapper;
    @Autowired
    private UserCommunityRelationMapper userCommunityRelationMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private PromotionRecordMapper promotionRecordMapper;
    @Autowired
    private PromotionTeamMapper promotionTeamMapper;
    @Autowired
    private ActivityTypeMapper activityTypeMapper;
    @Autowired
    private ActivityGroupRelationMapper activityGroupRelationMapper;
    @Autowired
    private AwardPoolMapper awardPoolMapper;
    @Autowired
    private IntegrationPoolMapper integrationPoolMapper;

    @Override
    public PagesDto<SmallCommunityListDto> getCommunityList(SmallCommunityListParams smallCommunityListParams) {
        PageHelper.startPage(smallCommunityListParams.getPage(), smallCommunityListParams.getRows());
        String communityName = smallCommunityListParams.getCommunityName();
        communityName = StringFilterUtils.emojiAndEscapeFilter(communityName);
        smallCommunityListParams.setCommunityName(communityName);
        List<SmallCommunityListDto> smallCommunityList = regionGroupInfoMapper.getSmallCommunityList(smallCommunityListParams);
        PageInfo<SmallCommunityListDto> info = new PageInfo<>(smallCommunityList);
        return new PagesDto<>(info.getList(), info.getTotal(), smallCommunityListParams.getPage(), smallCommunityListParams.getRows());
    }

    @Override
    public int addSmallCommunity(AddSmallCommunityParams addSmallCommunityParams) {
        RegionGroupInfo regionGroupInfo = new RegionGroupInfo();
        Date date = new Date();
        regionGroupInfo.setName(addSmallCommunityParams.getCommunityName());
        //category=1为小区 category=2为书屋
        regionGroupInfo.setCategory((byte) 1);
        regionGroupInfo.setLongitude(addSmallCommunityParams.getLongitude());
        regionGroupInfo.setLatitude(addSmallCommunityParams.getLatitude());
        regionGroupInfo.setDescription(addSmallCommunityParams.getDescription());
        regionGroupInfo.setAddress(addSmallCommunityParams.getAddress());
        regionGroupInfo.setProvince(addSmallCommunityParams.getProvince());
        regionGroupInfo.setProvinceCode(addSmallCommunityParams.getProvinceCode());
        regionGroupInfo.setCity(addSmallCommunityParams.getCity());
        regionGroupInfo.setCityCode(addSmallCommunityParams.getCityCode());
        regionGroupInfo.setDistrict(addSmallCommunityParams.getDistrict());
        regionGroupInfo.setDistrictCode(addSmallCommunityParams.getDistrictCode());
        regionGroupInfo.setStatus("1");
        //设置小区icon category=1
        List<String> imageList = imageInfoMapper.selectImage(1);
        int count = new Random().nextInt(imageList.size());//产生随机数
        String icon = imageList.get(count);
        regionGroupInfo.setIcon(icon);
        regionGroupInfo.setCreateTime(date);
        regionGroupInfo.setUpdateTime(date);
        regionGroupInfoMapper.insertSelective(regionGroupInfo);

        //type = 1 新建小区时自动创建的高级社区
        String communityName = addSmallCommunityParams.getCommunityName();
        RegionInfo regionInfo = new RegionInfo();
        if (communityName.contains("小区")) {
            communityName = new StringBuffer(communityName.substring(0, communityName.length() - 2)).append("大社区").toString();
        } else {
            communityName = new StringBuffer(communityName).append("大社区").toString();
        }
        regionInfo.setRegionName(communityName);
        regionInfo.setLongitude(addSmallCommunityParams.getLongitude());
        regionInfo.setLatitude(addSmallCommunityParams.getLatitude());
        regionInfo.setDescription(addSmallCommunityParams.getDescription());
        regionInfo.setAddress(addSmallCommunityParams.getAddress());
        regionInfo.setProvince(addSmallCommunityParams.getProvince());
        regionInfo.setProvinceCode(addSmallCommunityParams.getProvinceCode());
        regionInfo.setCity(addSmallCommunityParams.getCity());
        regionInfo.setCityCode(addSmallCommunityParams.getCityCode());
        regionInfo.setDistrict(addSmallCommunityParams.getDistrict());
        regionInfo.setDistrictCode(addSmallCommunityParams.getDistrictCode());
        regionInfo.setStatus((byte) 1);
        //新建小区时自动创建的高级社区
        regionInfo.setType((byte) 1);
        //设置大社区icon category=2
        List<String> regionIconList = imageInfoMapper.selectImage(2);
        //产生随机数
        int regionIconCount = new Random().nextInt(imageList.size());
        regionInfo.setIcon(regionIconList.get(regionIconCount));
        regionInfo.setCreateTime(date);
        regionInfo.setUpdateTime(date);
        regionInfoMapper.insertSelective(regionInfo);

        //建立小区和社区(自动创建的高级社区)的关联关系
        //小区id
        Integer communityId = regionGroupInfo.getId();
        //社区id(自动创建的高级社区)
        Integer regionId = regionInfo.getId();
        RegionGroupRelation regionGroupRelation = new RegionGroupRelation();
        regionGroupRelation.setRegionId(regionId);
        regionGroupRelation.setGroupId(communityId);
        regionGroupRelation.setCreateTime(date);
        int i = regionGroupRelationMapper.insertSelective(regionGroupRelation);
        return i;
    }

    @Override
    public int updateSmallCommunity(UpdateSmallCommunityParams updateSmallCommunityParams) {
        RegionGroupInfo regionGroupInfo = new RegionGroupInfo();
        Date date = new Date();
        regionGroupInfo.setId(updateSmallCommunityParams.getCommunityId());
        regionGroupInfo.setName(updateSmallCommunityParams.getCommunityName());
        //category=1为小区 category=2为书屋
        regionGroupInfo.setCategory((byte) 1);
        regionGroupInfo.setLongitude(updateSmallCommunityParams.getLongitude());
        regionGroupInfo.setLatitude(updateSmallCommunityParams.getLatitude());
        regionGroupInfo.setDescription(updateSmallCommunityParams.getDescription());
        regionGroupInfo.setAddress(updateSmallCommunityParams.getAddress());
        regionGroupInfo.setProvince(updateSmallCommunityParams.getProvince());
        regionGroupInfo.setProvinceCode(updateSmallCommunityParams.getProvinceCode());
        regionGroupInfo.setCity(updateSmallCommunityParams.getCity());
        regionGroupInfo.setCityCode(updateSmallCommunityParams.getCityCode());
        regionGroupInfo.setDistrict(updateSmallCommunityParams.getDistrict());
        regionGroupInfo.setDistrictCode(updateSmallCommunityParams.getDistrictCode());
        regionGroupInfo.setUpdateTime(date);
        int i = regionGroupInfoMapper.updateByPrimaryKeySelective(regionGroupInfo);
        return i;
    }

    @Override
    public UpdateSmallCommunityDto selectShowSmallCommunity(Integer communityId) {
        UpdateSmallCommunityDto updateSmallCommunityDto = regionGroupInfoMapper.selectShowSmallCommunity(communityId);
        return updateSmallCommunityDto;
    }

    @Override
    public List<ShowConnectRegionNameDto> getCommunityRegionListByExample(ShowConnectRegionNameParams showConnectRegionNameParams) {
        List<Integer> regionIds = showConnectRegionNameParams.getRegionIds();
        List<ShowConnectRegionNameDto> regionListByExampleList = regionGroupInfoMapper.getCommunityRegionListByExample(showConnectRegionNameParams);
        if (CollectionUtils.isNotEmpty(regionListByExampleList)) {
            if (CollectionUtils.isNotEmpty(regionIds)) {
                for (Integer regionId : regionIds) {
                    //删除已经关联的社区
                    regionListByExampleList.removeIf(item -> regionId.equals(item.getRegionId()));
                }
                if (CollectionUtils.isEmpty(regionListByExampleList)) {
                    throw new BusinessException(RetCodeConstants.NO_OTHER_REGION, "该小区或书屋已无其他社区可以关联");
                }
            }
        } else {
            throw new BusinessException(RetCodeConstants.DATA_BASE_NO_REGION, "该条件下数据库没有社区相关数据");
        }
        return regionListByExampleList;
    }

    @Override
    public int addConnectRegionToCommunity(ConnectRegionParams connectRegionParams) {
        RegionGroupRelation rgr = regionGroupRelationMapper.getRegionGroupRelationByGroupId(connectRegionParams.getGroupId());
        int i = 0;
        if (rgr != null) {
            if (connectRegionParams.getRegionId().equals(rgr.getRegionId())) {
                throw new BusinessException(RetCodeConstants.SAVE_FAILED, "该小区已关联该社区,请勿重复关联...");
            } else {
                //删除之前的关联关系
                regionGroupRelationMapper.deleteByPrimaryKey(rgr.getId());
                //建立新的关联关系
                RegionGroupRelation regionGroupRelation = new RegionGroupRelation();
                Date date = new Date();
                regionGroupRelation.setGroupId(connectRegionParams.getGroupId());
                regionGroupRelation.setRegionId(connectRegionParams.getRegionId());
                regionGroupRelation.setCreateTime(date);
                i = regionGroupRelationMapper.insertSelective(regionGroupRelation);
            }
        } else {
            //建立新的关联关系
            RegionGroupRelation regionGroupRelation = new RegionGroupRelation();
            Date date = new Date();
            regionGroupRelation.setGroupId(connectRegionParams.getGroupId());
            regionGroupRelation.setRegionId(connectRegionParams.getRegionId());
            regionGroupRelation.setCreateTime(date);
            i = regionGroupRelationMapper.insertSelective(regionGroupRelation);
        }
        return i;
    }

    @Override
    public PagesDto<BookHouseListDto> getBookHouseList(BookHouseListParams bookHouseListParams) {
        int page = bookHouseListParams.getPage();
        int rows = bookHouseListParams.getRows();
        int page1 = PageRowsUtils.getPage(page, rows);
        bookHouseListParams.setPage(page1);
        List<BookHouseListDto> bookHouseList = regionGroupInfoMapper.getBookHouseList(bookHouseListParams);
        Integer total = regionGroupInfoMapper.getBookHouseTotal(bookHouseListParams);
        return new PagesDto(bookHouseList, total, page, rows);
    }

    @Override
    public int addBookHouse(AddBookHouseParams addBookHouseParams) {
        RegionGroupInfo rgi = regionGroupInfoMapper.selectBookHouseByBookHouseName(addBookHouseParams.getBookHouseName());
        if (rgi != null) {
            throw new BusinessException(RetCodeConstants.SAVE_FAILED, "新增失败,该书屋名已存在!");
        }
        RegionGroupInfo regionGroupInfo = new RegionGroupInfo();
        Date date = new Date();
        regionGroupInfo.setName(addBookHouseParams.getBookHouseName());
        //category=1 小程序 category=2 书屋
        regionGroupInfo.setCategory((byte) 2);
        regionGroupInfo.setLongitude(addBookHouseParams.getLongitude());
        regionGroupInfo.setLatitude(addBookHouseParams.getLatitude());
        regionGroupInfo.setDescription(addBookHouseParams.getDescription());
        regionGroupInfo.setAddress(addBookHouseParams.getAddress());
        regionGroupInfo.setProvince(addBookHouseParams.getProvince());
        regionGroupInfo.setProvinceCode(addBookHouseParams.getProvinceCode());
        regionGroupInfo.setCity(addBookHouseParams.getCity());
        regionGroupInfo.setCityCode(addBookHouseParams.getCityCode());
        regionGroupInfo.setDistrict(addBookHouseParams.getDistrict());
        regionGroupInfo.setDistrictCode(addBookHouseParams.getDistrictCode());
        regionGroupInfo.setCreateTime(date);
        regionGroupInfo.setUpdateTime(date);
        regionGroupInfo.setStatus("1");
        regionGroupInfoMapper.insertSelective(regionGroupInfo);
        //书屋码新增
        Integer id = regionGroupInfo.getId();
        Integer count = regionGroupInfoMapper.selectBookHouseCount(new StringBuffer(addBookHouseParams.getDistrictCode().substring(0, 2)).append("0000").toString());
        BookExtension bookExtension = new BookExtension();
        bookExtension.setBookId(id);
        bookExtension.setCreateTime(date);
        bookExtension.setUpdateTime(date);
        bookExtension.setCode(String.valueOf((Integer.parseInt(addBookHouseParams.getDistrictCode().substring(0, 2) + "0000")) + count + 1));
        int i = bookExtensionMapper.insertSelective(bookExtension);
        return i;
    }

    @Override
    public UpdateBookHouseDto selectShowBookHouse(Integer bookHouseId) {
        UpdateBookHouseDto updateBookHouseDto = regionGroupInfoMapper.selectShowBookHouse(bookHouseId);
        return updateBookHouseDto;
    }

    @Override
    public int updateBookHouse(UpdateBookHouseParams updateBookHouseParams) {
        RegionGroupInfo regionGroupInfo = new RegionGroupInfo();
        Date date = new Date();
        regionGroupInfo.setId(updateBookHouseParams.getBookHouseId());
        regionGroupInfo.setName(updateBookHouseParams.getBookHouseName());
        //category=1为小区 category=2为书屋
        regionGroupInfo.setCategory((byte) 2);
        regionGroupInfo.setLongitude(updateBookHouseParams.getLongitude());
        regionGroupInfo.setLatitude(updateBookHouseParams.getLatitude());
        regionGroupInfo.setDescription(updateBookHouseParams.getDescription());
        regionGroupInfo.setAddress(updateBookHouseParams.getAddress());
        regionGroupInfo.setProvince(updateBookHouseParams.getProvince());
        regionGroupInfo.setProvinceCode(updateBookHouseParams.getProvinceCode());
        regionGroupInfo.setCity(updateBookHouseParams.getCity());
        regionGroupInfo.setCityCode(updateBookHouseParams.getCityCode());
        regionGroupInfo.setDistrict(updateBookHouseParams.getDistrict());
        regionGroupInfo.setDistrictCode(updateBookHouseParams.getDistrictCode());
        regionGroupInfo.setUpdateTime(date);
        int i = regionGroupInfoMapper.updateByPrimaryKeySelective(regionGroupInfo);
        return i;
    }

    @Override
    public PagesDto<RegionListDto> selectRegionList(RegionListParams regionListParams) {
        PageHelper.startPage(regionListParams.getPage(), regionListParams.getRows());
        List<RegionDto> regionList = regionGroupInfoMapper.getRegionList(regionListParams);
        PageInfo<RegionDto> pageInfo = new PageInfo<>(regionList);
        List<RegionListDto> list = new ArrayList<>();
        for (RegionDto regionDto : regionList) {
            RegionListDto regionListDto = new RegionListDto();
            regionListDto.setRegionId(regionDto.getRegionId());
            regionListDto.setRegionName(regionDto.getRegionName());
            String address = new StringBuffer(regionDto.getProvince()).append(regionDto.getCity()).append(regionDto.getDistrict()).toString();
            regionListDto.setAddress(address);
            regionListDto.setConnectBookHouseCount(regionDto.getConnectBookHouseCount());
            regionListDto.setConnectCommunityCount(regionDto.getConnectCommunityCount());
            regionListDto.setIcon(regionDto.getIcon());
            list.add(regionListDto);
        }
        PageInfo<RegionListDto> info = new PageInfo<>(list);
        return new PagesDto<>(info.getList(), pageInfo.getTotal(), regionListParams.getPage(), regionListParams.getRows());
    }

    @Override
    public int addRegion(AddRegionParams addRegionParams) {
        RegionInfo regionInfo = new RegionInfo();
        Date date = new Date();
        regionInfo.setRegionName(addRegionParams.getRegionName());
        regionInfo.setLongitude(addRegionParams.getLongitude());
        regionInfo.setLatitude(addRegionParams.getLatitude());
        regionInfo.setDescription(addRegionParams.getDescription());
        regionInfo.setProvince(addRegionParams.getProvince());
        regionInfo.setProvinceCode(addRegionParams.getProvinceCode());
        regionInfo.setCity(addRegionParams.getCity());
        regionInfo.setAddress(addRegionParams.getAddress());
        regionInfo.setCityCode(addRegionParams.getCityCode());
        regionInfo.setDistrict(addRegionParams.getDistrict());
        regionInfo.setDistrictCode(addRegionParams.getDistrictCode());
        regionInfo.setStatus((byte) 1);
        //管理员手动创建的 type=2
        regionInfo.setType((byte) 2);
        String icon = addRegionParams.getIcon();
        //判断管理员是否添加了icon
        if (StringUtils.isBlank(icon)) {
            //设置大区icon
            List<String> imageList = imageInfoMapper.selectImage(2);
            //产生随机数
            int count = new Random().nextInt(imageList.size());
            icon = imageList.get(count);
        }
        regionInfo.setIcon(icon);
        regionInfo.setCreateTime(date);
        regionInfo.setUpdateTime(date);
        int i = regionInfoMapper.insertSelective(regionInfo);
        return i;
    }

    @Override
    public UpdateRegionDto selectRegionByRegionId(Integer regionId) {
        UpdateRegionDto updateRegionDto = regionGroupInfoMapper.getRegionByRegionId(regionId);
        return updateRegionDto;
    }

    @Override
    public int updateRegion(UpdateRegionParams updateRegionParams) {
        RegionInfo regionInfo = new RegionInfo();
        regionInfo.setId(updateRegionParams.getRegionId());
        regionInfo.setProvince(updateRegionParams.getProvince());
        regionInfo.setProvinceCode(updateRegionParams.getProvinceCode());
        regionInfo.setCity(updateRegionParams.getCity());
        regionInfo.setCityCode(updateRegionParams.getCityCode());
        regionInfo.setDistrict(updateRegionParams.getDistrict());
        regionInfo.setDistrictCode(updateRegionParams.getDistrictCode());
        regionInfo.setRegionName(updateRegionParams.getRegionName());
        regionInfo.setAddress(updateRegionParams.getAddress());
        regionInfo.setLongitude(updateRegionParams.getLongitude());
        regionInfo.setLatitude(updateRegionParams.getLatitude());
        String icon = updateRegionParams.getIcon();
        //判断管理员是否添加了icon
        if (StringUtils.isBlank(icon)) {
            //设置大区icon
            List<String> imageList = imageInfoMapper.selectImage(2);
            //产生随机数
            int count = new Random().nextInt(imageList.size());
            icon = imageList.get(count);
        }
        regionInfo.setIcon(icon);
        regionInfo.setDescription(updateRegionParams.getDescription());
        int i = regionInfoMapper.updateByPrimaryKeySelective(regionInfo);
        return i;
    }

    @Override
    public List<CommunityDetailDto> selectCommunityListByRegionId(Integer regionId) {
        List<CommunityDetailDto> communityDetailList = regionGroupInfoMapper.getCommunityListByRegionId(regionId);
        return communityDetailList;
    }

    @Override
    public int deleteUnfollowRegion(ConnectRegionParams connectRegionParams) {
        RegionGroupRelation regionGroupRelation = regionGroupRelationMapper.getRegionGroupRelationByGroupIdAndRegionId(connectRegionParams.getGroupId(), connectRegionParams.getRegionId());
        int i = regionGroupRelationMapper.deleteByPrimaryKey(regionGroupRelation.getId());
        return i;
    }

    @Override
    public List<BookHouseDetailDto> selectBookHouseListByRegionId(Integer regionId) {
        List<BookHouseDetailDto> bookHouseDetailList = regionGroupInfoMapper.getBookHouseListByRegionId(regionId);
        return bookHouseDetailList;
    }

    @Override
    public List<String> selectGroupDetail(GroupDetailParams groupDetailParams) {
        List<String> groupDetail = regionGroupInfoMapper.getGroupDetail(groupDetailParams);
        return groupDetail;
    }

    @Override
    public List<String> selectRegionDetail(RegionDetailParams regionDetailParams) {
        List<String> regionDetail = regionGroupInfoMapper.getRegionDetail(regionDetailParams);
        return regionDetail;
    }

    @Override
    public int addConnectRegionToBookHouse(BookConnectRegionParams bookConnectRegionParams) {
        Integer regionId = bookConnectRegionParams.getRegionId();
        List<RegionGroupRelation> rgr = regionGroupRelationMapper.getBookHouseAndRegionByGroupId(bookConnectRegionParams.getGroupId());
        int i = 0;
        if (CollectionUtils.isNotEmpty(rgr)) {
            for (RegionGroupRelation r : rgr) {
                if (regionId.equals(r.getRegionId())) {
                    throw new BusinessException(RetCodeConstants.SAVE_FAILED, "该书屋已关联该社区,请勿重复关联...");
                }
            }
            //建立新的关联关系
            RegionGroupRelation regionGroupRelation = new RegionGroupRelation();
            Date date = new Date();
            regionGroupRelation.setGroupId(bookConnectRegionParams.getGroupId());
            regionGroupRelation.setRegionId(regionId);
            regionGroupRelation.setCreateTime(date);
            i = regionGroupRelationMapper.insertSelective(regionGroupRelation);
        } else {
            //建立新的关联关系
            RegionGroupRelation regionGroupRelation = new RegionGroupRelation();
            Date date = new Date();
            regionGroupRelation.setGroupId(bookConnectRegionParams.getGroupId());
            regionGroupRelation.setRegionId(regionId);
            regionGroupRelation.setCreateTime(date);
            i = regionGroupRelationMapper.insertSelective(regionGroupRelation);
        }
        return i;
    }

    @Override
    public CommunityActivityListResponseDto selectRegionGroupInfo(CommunityActivityRequestDto communityActivityRequestDto, Integer userId) {
        List<CommunitySetParams> communitySetParamsList = regionGroupInfoMapper.getRegionGroupInfoByCityCode(communityActivityRequestDto);
        //过滤出距离当前位置1km之内的小区信息
        List<CommunitySetParams> communitySetParamsCollect = communitySetParamsList.stream().filter(c -> c.getDistance().doubleValue() < 1000).collect(Collectors.toList());
        List<CommunityNameListResponseDto> myCommunityList = userCommunityRelationMapper.selectMyCommunityList(userId, communityActivityRequestDto.getLongitude(), communityActivityRequestDto.getLatitude());
        List<CommunityNameListResponseDto> myCommunityCollect = myCommunityList.stream().filter(c -> c.getDistance().doubleValue() < 1000).collect(Collectors.toList());
        CommunityActivityListResponseDto communityActivityListResponseDto = new CommunityActivityListResponseDto();
        UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(userId);
        //判断用户是否是游客状态
        //用户为非游客状态
        if (!userInfo.getUserRole().equals(UserEnum.UserRoleEnum.VISITOR.getCode())) {
            //判断我的小区是否为空
            if (CollectionUtils.isNotEmpty(myCommunityList)) {
                //判断我的小区1km之内是否有
                if (CollectionUtils.isNotEmpty(myCommunityCollect)) {
                    //大社区类型type=2,手动创建的大社区
                    RegionInfo r = regionInfoMapper.getRegionInfoByCommunityId(2, myCommunityCollect.get(0).getCommunityId());
                    if (r != null) {
                        communityActivityListResponseDto.setRegionId(r.getId());
                        communityActivityListResponseDto.setRegionName(r.getRegionName());
                        communityActivityListResponseDto.setRegionLongitude(r.getLongitude());
                        communityActivityListResponseDto.setRegionLatitude(r.getLatitude());
                        communityActivityListResponseDto.setCommunityId(myCommunityCollect.get(0).getCommunityId());
                        communityActivityListResponseDto.setCommunityName(myCommunityCollect.get(0).getCommunityName());
                        communityActivityListResponseDto.setLongitude(myCommunityCollect.get(0).getLongitude());
                        communityActivityListResponseDto.setLatitude(myCommunityCollect.get(0).getLatitude());
                        //button展示状态 0:1:2 | 不显示我的社区button : 显示我的社区button : 显示未添加我的社区button
                        //不显示我的社区button
                        communityActivityListResponseDto.setDisplayButton(BusinessEnum.MyCommunityButtonStatusEnum.NO_SHOW_MYCOMMUNITY_BUTTON.getCode());
                    } else {
                        //大社区类型 type=1(创建小区时自动床建的大社区)
                        RegionInfo re = regionInfoMapper.getRegionInfoByCommunityId(1, myCommunityCollect.get(0).getCommunityId());
                        communityActivityListResponseDto.setRegionId(re.getId());
                        communityActivityListResponseDto.setRegionName(re.getRegionName());
                        communityActivityListResponseDto.setRegionLongitude(re.getLongitude());
                        communityActivityListResponseDto.setRegionLatitude(re.getLatitude());
                        communityActivityListResponseDto.setCommunityId(myCommunityCollect.get(0).getCommunityId());
                        communityActivityListResponseDto.setCommunityName(myCommunityCollect.get(0).getCommunityName());
                        communityActivityListResponseDto.setLongitude(myCommunityCollect.get(0).getLongitude());
                        communityActivityListResponseDto.setLatitude(myCommunityCollect.get(0).getLatitude());
                        //button展示状态 0:1:2 | 不显示我的社区button : 显示我的社区button : 显示未添加我的社区button
                        //不显示我的社区button
                        communityActivityListResponseDto.setDisplayButton(BusinessEnum.MyCommunityButtonStatusEnum.NO_SHOW_MYCOMMUNITY_BUTTON.getCode());
                    }
                } else {
                    if (CollectionUtils.isNotEmpty(communitySetParamsList)) {
                        //大社区类型type=2,手动创建的大社区
                        RegionInfo r = regionInfoMapper.getRegionInfoByCommunityId(2, communitySetParamsList.get(0).getCommunityId());
                        if (r != null) {
                            communityActivityListResponseDto.setRegionId(r.getId());
                            communityActivityListResponseDto.setRegionName(r.getRegionName());
                            communityActivityListResponseDto.setRegionLongitude(r.getLongitude());
                            communityActivityListResponseDto.setRegionLatitude(r.getLatitude());
                            communityActivityListResponseDto.setCommunityId(communitySetParamsList.get(0).getCommunityId());
                            communityActivityListResponseDto.setCommunityName(communitySetParamsList.get(0).getCommunityName());
                            communityActivityListResponseDto.setLongitude(communitySetParamsList.get(0).getLongitude());
                            communityActivityListResponseDto.setLatitude(communitySetParamsList.get(0).getLatitude());
                            //button展示状态 0:1:2 | 不显示我的社区button : 显示我的社区button : 显示未添加我的社区button
                            //显示未添加我的社区button
                            communityActivityListResponseDto.setDisplayButton(BusinessEnum.MyCommunityButtonStatusEnum.SHOW_MYCOMMUNITY_BUTTON.getCode());
                        } else {
                            //大社区类型 type=1(创建小区时自动床建的大社区)
                            RegionInfo re = regionInfoMapper.getRegionInfoByCommunityId(1, communitySetParamsList.get(0).getCommunityId());
                            communityActivityListResponseDto.setRegionId(re.getId());
                            communityActivityListResponseDto.setRegionName(re.getRegionName());
                            communityActivityListResponseDto.setRegionLongitude(re.getLongitude());
                            communityActivityListResponseDto.setRegionLatitude(re.getLatitude());
                            communityActivityListResponseDto.setCommunityId(communitySetParamsList.get(0).getCommunityId());
                            communityActivityListResponseDto.setCommunityName(communitySetParamsList.get(0).getCommunityName());
                            communityActivityListResponseDto.setLongitude(communitySetParamsList.get(0).getLongitude());
                            communityActivityListResponseDto.setLatitude(communitySetParamsList.get(0).getLatitude());
                            //button展示状态 0:1:2 | 不显示我的社区button : 显示我的社区button : 显示未添加我的社区button
                            //显示未添加我的社区button
                            communityActivityListResponseDto.setDisplayButton(BusinessEnum.MyCommunityButtonStatusEnum.SHOW_MYCOMMUNITY_BUTTON.getCode());
                        }
                    }
                }
            } else {
                ShowNoAddMyCommunityButtonMethod(communitySetParamsList, communityActivityListResponseDto);
            }
        } else {
            //用户为游客状态
            ShowNoAddMyCommunityButtonMethod(communitySetParamsList, communityActivityListResponseDto);
        }
        return communityActivityListResponseDto;
    }

    //显示"您没有添加社区(小区)噢,点此添加"button
    private void ShowNoAddMyCommunityButtonMethod(List<CommunitySetParams> communitySetParamsList, CommunityActivityListResponseDto communityActivityListResponseDto) {
        if (CollectionUtils.isNotEmpty(communitySetParamsList)) {
            //大社区类型type=2,手动创建的大社区
            RegionInfo r = regionInfoMapper.getRegionInfoByCommunityId(2, communitySetParamsList.get(0).getCommunityId());
            if (r != null) {
                communityActivityListResponseDto.setRegionId(r.getId());
                communityActivityListResponseDto.setRegionName(r.getRegionName());
                communityActivityListResponseDto.setRegionLongitude(r.getLongitude());
                communityActivityListResponseDto.setRegionLatitude(r.getLatitude());
                communityActivityListResponseDto.setCommunityId(communitySetParamsList.get(0).getCommunityId());
                communityActivityListResponseDto.setCommunityName(communitySetParamsList.get(0).getCommunityName());
                communityActivityListResponseDto.setLongitude(communitySetParamsList.get(0).getLongitude());
                communityActivityListResponseDto.setLatitude(communitySetParamsList.get(0).getLatitude());
                //button展示状态 0:1:2 | 不显示我的社区button : 显示我的社区button : 显示未添加我的社区button
                //显示未添加我的社区button
                communityActivityListResponseDto.setDisplayButton(BusinessEnum.MyCommunityButtonStatusEnum.SHOW_NO_ADD_MYCOMMUNITY_BUTTON.getCode());
            } else {
                //大社区类型 type=1(创建小区时自动床建的大社区)
                RegionInfo re = regionInfoMapper.getRegionInfoByCommunityId(1, communitySetParamsList.get(0).getCommunityId());
                communityActivityListResponseDto.setRegionId(re.getId());
                communityActivityListResponseDto.setRegionName(re.getRegionName());
                communityActivityListResponseDto.setRegionLongitude(re.getLongitude());
                communityActivityListResponseDto.setRegionLatitude(re.getLatitude());
                communityActivityListResponseDto.setCommunityId(communitySetParamsList.get(0).getCommunityId());
                communityActivityListResponseDto.setCommunityName(communitySetParamsList.get(0).getCommunityName());
                communityActivityListResponseDto.setLongitude(communitySetParamsList.get(0).getLongitude());
                communityActivityListResponseDto.setLatitude(communitySetParamsList.get(0).getLatitude());
                //button展示状态 0:1:2 | 不显示我的社区button : 显示我的社区button : 显示未添加我的社区button
                //显示未添加我的社区button
                communityActivityListResponseDto.setDisplayButton(BusinessEnum.MyCommunityButtonStatusEnum.SHOW_NO_ADD_MYCOMMUNITY_BUTTON.getCode());
            }
        }
    }

    @Override
    public List<MyRegionInfoListDto> selectRegionInfoList(Integer bookId) {
        List<MyRegionInfoListDto> myRegionInfoList = regionInfoMapper.getMyRegionInfoList(bookId);
        return myRegionInfoList;
    }

    @Override
    public List<CommunityInfoDetailDto> selectCommunityInfoDetail(Integer regionId) {
        List<CommunityInfoDetailDto> communityInfoDetail = regionGroupInfoMapper.getCommunityInfoDetail(regionId);
        return communityInfoDetail;
    }

    @Override
    public PagesDto<AdminUserInfoResponseDto> selectUserInfo(AdminUserInfoRequestDto adminUserInfoRequestDto) {
        int page = adminUserInfoRequestDto.getPage();
        int rows = adminUserInfoRequestDto.getRows();
        List<AdminUserInfoResponseDto> userInfoList = userInfoMapper.selectUserInfo(adminUserInfoRequestDto.getPhone(), adminUserInfoRequestDto.getNickName(), adminUserInfoRequestDto.getRegionId());
        List<AdminUserInfoResponseDto> dtoList = PageRowsUtils.getPageObj(userInfoList, page, rows);
        return new PagesDto(dtoList, userInfoList.size(), page, rows);
    }

    @Override
    public PagesDto<BookHousePromotionDto> getBookPromotionList(BookHousePromotionParams bookHousePromotionParams) {
        int page = bookHousePromotionParams.getPage();
        int rows = bookHousePromotionParams.getRows();
        List<BookHousePromotionDto> bookPromotionList = promotionRecordMapper.getBookPromotionList(bookHousePromotionParams);
        List<BookHousePromotionDto> bpl = PageRowsUtils.getPageObj(bookPromotionList, page, rows);
        return new PagesDto(bpl, bookPromotionList.size(), page, rows);
    }

    @Override
    public PagesDto<BookHousePromotionDetailDto> getBookPromotionDetailList(BookHousePromotionDetailParams bookHousePromotionDetailParams) {
        int page = bookHousePromotionDetailParams.getPage();
        int rows = bookHousePromotionDetailParams.getRows();
        List<BookHousePromotionDetailDto> bookPromotionDetailList = promotionRecordMapper.getBookPromotionDetailList(bookHousePromotionDetailParams);
        List<BookHousePromotionDetailDto> pageObj = PageRowsUtils.getPageObj(bookPromotionDetailList, page, rows);
        return new PagesDto(pageObj, bookPromotionDetailList.size(), page, rows);
    }

    @Override
    public PagesDto<OtherPromotionDto> getOtherPromotionList(OtherPromotionParams otherPromotionParams) {
        int page = otherPromotionParams.getPage();
        int rows = otherPromotionParams.getRows();
        List<OtherPromotionDto> otherPromotionList = promotionTeamMapper.getOtherPromotionList(otherPromotionParams);
        List<OtherPromotionDto> pageObj = PageRowsUtils.getPageObj(otherPromotionList, page, rows);
        return new PagesDto(pageObj, otherPromotionList.size(), page, rows);
    }

    @Override
    public int addPromotionTeam(AddPromotionTeamParams addPromotionTeamParams) {
        Integer c = promotionTeamMapper.selectPromotionTeamCountByExample(addPromotionTeamParams);
        if (c > 0) {
            throw new BusinessException(RetCodeConstants.WX_PROMOTION_NAME_REPEAT, "该省市下的该推广名称已经存在,请重新编辑推广名称...");
        }
        PromotionTeam promotionTeam = new PromotionTeam();
        promotionTeam.setProvince(addPromotionTeamParams.getProvince());
        promotionTeam.setProvinceCode(addPromotionTeamParams.getProvinceCode());
        promotionTeam.setCity(addPromotionTeamParams.getCity());
        promotionTeam.setCityCode(addPromotionTeamParams.getCityCode());
        promotionTeam.setName(addPromotionTeamParams.getTeamName());
        //推广码
        Integer count = promotionTeamMapper.selectPromotionTeamCount(addPromotionTeamParams.getProvinceCode());
        promotionTeam.setCode("T" + String.valueOf((Integer.parseInt(addPromotionTeamParams.getCityCode().substring(0, 2) + "0000")) + count + 1));
        Date date = new Date();
        promotionTeam.setCreateTime(date);
        promotionTeam.setUpdateTime(date);
        int i = promotionTeamMapper.insertSelective(promotionTeam);
        return i;
    }

    @Override
    public int updatePromotionTeam(AddPromotionTeamParams addPromotionTeamParams) {
        Integer c = promotionTeamMapper.selectPromotionTeamCountByExample(addPromotionTeamParams);
        if (c > 0) {
            throw new BusinessException(RetCodeConstants.WX_PROMOTION_NAME_REPEAT, "该省市下的该推广名称已经存在,请重新编辑推广名称...");
        }
        PromotionTeam promotionTeam = new PromotionTeam();
        promotionTeam.setId(addPromotionTeamParams.getId());
        promotionTeam.setName(addPromotionTeamParams.getTeamName());
        int i = promotionTeamMapper.updateByPrimaryKeySelective(promotionTeam);
        return i;
    }

    @Override
    public PagesDto<BookHousePromotionDetailDto> getOtherPromotionDetailList(OtherPromotionDetailParams otherPromotionDetailParams) {
        int page = otherPromotionDetailParams.getPage();
        int rows = otherPromotionDetailParams.getRows();
        List<BookHousePromotionDetailDto> bookPromotionDetailList = promotionRecordMapper.getOtherPromotionDetailList(otherPromotionDetailParams);
        List<BookHousePromotionDetailDto> pageObj = PageRowsUtils.getPageObj(bookPromotionDetailList, page, rows);
        return new PagesDto(pageObj, bookPromotionDetailList.size(), page, rows);
    }

    @Override
    public ShowOtherPromotionTeamDto getShowPromotionTeam(Integer id) {
        PromotionTeam promotionTeam = promotionTeamMapper.selectByPrimaryKey(id);
        ShowOtherPromotionTeamDto s = new ShowOtherPromotionTeamDto();
        s.setId(promotionTeam.getId());
        s.setCity(promotionTeam.getCity());
        s.setCityCode(promotionTeam.getCityCode());
        s.setProvince(promotionTeam.getProvince());
        s.setProvinceCode(promotionTeam.getProvinceCode());
        s.setTeamName(promotionTeam.getName());
        return s;
    }

    @Override
    public List<ActivityType> getActivityType() {
        List<ActivityType> activityTypeList = activityTypeMapper.getActivityType();
        return activityTypeList;
    }

    @Override
    public PagesDto<BookHouseListActivityDetailDto> selectActivityDetailByBookId(BookHouseListActivityDetailParams bookHouseListActivityDetailParams) {
        int page = bookHouseListActivityDetailParams.getPage();
        int rows = bookHouseListActivityDetailParams.getRows();
        List<BookHouseListActivityDetailDto> list = activityGroupRelationMapper.getActivityInfoByBookId(bookHouseListActivityDetailParams);
        List<BookHouseListActivityDetailDto> pageObj = PageRowsUtils.getPageObj(list,page,rows);
        return new PagesDto(pageObj, list.size(), page, rows);
    }

    @Override
    public PagesDto<AdminAwardPoolDto> getAwardDetailByBookId(AwardDetailParams awardDetailParams) {
        int page = awardDetailParams.getPage();
        int rows = awardDetailParams.getRows();
        PageHelper.startPage(page, rows);
        List<AdminAwardPoolDto> list = awardPoolMapper.getAwardDetailByBookId(awardDetailParams.getBookId());
        PageInfo<AdminAwardPoolDto> pageInfo = new PageInfo<>(list);
        return new PagesDto(list, pageInfo.getTotal(), page, rows);
    }

    @Override
    public PagesDto<IntegrationPoolDto> getIntegrationDetailByBookId(IntegrationDetailParams integrationDetailParams) {
        int page = integrationDetailParams.getPage();
        int rows = integrationDetailParams.getRows();
        PageHelper.startPage(page, rows);
        List<IntegrationPoolDto> list = integrationPoolMapper.getIntegrationDetailByBookId(integrationDetailParams.getBookId());
        PageInfo<IntegrationPoolDto> pageInfo = new PageInfo<>(list);
        return new PagesDto(list, pageInfo.getTotal(), page, rows);
    }
}
