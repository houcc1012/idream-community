package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.BookExtension;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BookExtensionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookExtension record);

    int insertSelective(BookExtension record);

    BookExtension selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookExtension record);

    int updateByPrimaryKey(BookExtension record);

    //通过code 查询bookHouseId
    @Select("SELECT book_id bookId FROM book_extension WHERE code = #{code}")
    BookExtension getBookHouseByCode(@Param("code") String code);

    @Select("select * from book_extension where book_id=#{bookId}")
    BookExtension selectByBookId(@Param("bookId") Integer bookId);
}