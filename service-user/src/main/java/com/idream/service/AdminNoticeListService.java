package com.idream.service;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.app.NoticeListRequestDto;
import com.idream.commons.lib.dto.app.NoticeListResponseDto;
import com.idream.commons.lib.dto.app.SystemNoticeParams;
import com.idream.commons.lib.dto.app.SystemNoticeRequestDto;
import com.idream.commons.lib.model.SystemNotice;

public interface AdminNoticeListService {

    //通知列表(后台管理)
    PagesDto<NoticeListResponseDto> selectNoticeList(NoticeListRequestDto noticeListRequestDto);

    //新增新通知
    int addNewNotice(Integer authUserId, SystemNoticeRequestDto systemNoticeRequestDto);

    //编辑通知数据回显
    SystemNotice selectNoticeById(Integer id);

    //编辑通知
    int updateNotice(SystemNoticeParams systemNoticeParams);

    //删除通知
    int deleteNotice(Integer id);

    //发布通知(站内信)
    int updatePublishNotice(Integer authUserId, Integer id);
}