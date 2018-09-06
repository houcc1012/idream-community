package com.idream.commons.lib.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/19.
 */

public class PageRowsUtils {

    public static int getPage(int page, int rows) {
        return (page - 1) * rows < 0 ? 0 : (page - 1) * rows;
    }

    /**
     * 代码分页通用方法
     *
     * @param list 待分页对象
     * @param page 页码
     * @param rows 行数
     * @param <T>  返回对象
     */
    public static <T> List<T> getPageObj(List<T> list, int page, int rows) {
        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        //计算初始页
        int startPage = getPage(page, rows);
        //分页对象数据不够起始数据的情况
        if (list.size() < startPage) {
            //最大分页数
            int totalPage = (list.size() + rows - 1) / rows;
            return list.subList((totalPage - 1) * rows, list.size());
        } else {
            //分页对象数量够的情况
            int endPage = (startPage + rows) > list.size() ? list.size() : startPage + rows;
            return list.subList(startPage, endPage);
        }
    }
}
