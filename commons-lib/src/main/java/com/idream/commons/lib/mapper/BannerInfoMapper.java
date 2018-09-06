package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.user.*;
import com.idream.commons.lib.model.BannerInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BannerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BannerInfo record);

    int insertSelective(BannerInfo record);

    BannerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BannerInfo record);

    int updateByPrimaryKey(BannerInfo record);

    List<AdminBannerImageListDto> selectAdminPage(AdminBannerImageListParams adminBannerImageListParams);

    List<AdminBannerUrlParams> selectDisplayByTypeId(Integer typeId);

    int selectOnlineNumByTypeId(int typeId);
}