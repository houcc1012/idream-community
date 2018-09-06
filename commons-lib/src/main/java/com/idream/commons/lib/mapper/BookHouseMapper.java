package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.BookHouseListDto;
import com.idream.commons.lib.dto.activity.CommunityBookHouseDto;
import com.idream.commons.lib.dto.activity.CommunityBookHouseRequestDto;
import com.idream.commons.lib.dto.activity.FindBookAssociationCommunityResponseDto;
import com.idream.commons.lib.model.BookHouse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookHouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookHouse record);

    int insertSelective(BookHouse record);

    BookHouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookHouse record);

    int updateByPrimaryKey(BookHouse record);

    List<BookHouseListDto> selectBookHouseListByAddress(@Param("provinceCode") String provinceCode,
                                                        @Param("cityCode") String cityCode,
                                                        @Param("adCode") String adCode,
                                                        @Param("bookHouseName") String bookHouseName,
                                                        @Param("communityId") Integer communityId);

    //查询书屋列表
    List<CommunityBookHouseDto> selectBookHouseListByExample(CommunityBookHouseRequestDto communityBookHouseRequestDto);

    //编辑书屋数据回显
    CommunityBookHouseDto selectBookHouseById(@Param("bookHouseId") Integer bookHouseId);

    List<FindBookAssociationCommunityResponseDto> getBookAssociationCommunity(Integer bookId);

    List<FindBookAssociationCommunityResponseDto> getBookAndUserAssociationCommunity(@Param("bookId") Integer bookId, @Param("userId") Integer userId);

    @Select("select id,longitude,latitude from book_house where code =#{code}")
    BookHouse selectByCode(@Param("code") String code);

    BookHouse selectBookHouseByName(@Param("bookHouseName") String bookHouseName);

    //查询数据库中书屋的数量
    @Select("SELECT count(*) AS count FROM book_house WHERE ad_code LIKE CONCAT(#{adCode},'%')")
    Integer selectBookHouseCount(@Param("adCode") String adCode);

    //通过cityCode查询cityName
    @Select("SELECT city_name cityName FROM district WHERE city_code=#{cityCode}")
    String selectCityNameByCityCode(@Param("cityCode") String cityCode);

    //书屋列表条数
    Integer getTotal(CommunityBookHouseRequestDto communityBookHouseRequestDto);
}