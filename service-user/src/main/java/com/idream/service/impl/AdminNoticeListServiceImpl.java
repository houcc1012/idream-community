package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.app.NoticeListRequestDto;
import com.idream.commons.lib.dto.app.NoticeListResponseDto;
import com.idream.commons.lib.dto.app.SystemNoticeParams;
import com.idream.commons.lib.dto.app.SystemNoticeRequestDto;
import com.idream.commons.lib.mapper.SystemNoticeMapper;
import com.idream.commons.lib.model.SystemNotice;
import com.idream.commons.lib.util.StringFilterUtils;
import com.idream.service.AdminNoticeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminNoticeListServiceImpl implements AdminNoticeListService {

    @Autowired
    private SystemNoticeMapper systemNoticeMapper;

    @Override
    public PagesDto<NoticeListResponseDto> selectNoticeList(NoticeListRequestDto noticeListRequestDto) {
        int page = noticeListRequestDto.getPage();
        int rows = noticeListRequestDto.getRows();
        String content = StringFilterUtils.emojiAndEscapeFilter(noticeListRequestDto.getContent());
        PageInfo pageInfo = null;
        PageHelper.startPage(page, rows);
        List<NoticeListResponseDto> noticeList = systemNoticeMapper.selectNoticeList(content);
        if (noticeList.size() != 0 && noticeList != null) {
            pageInfo = new PageInfo<>(noticeList);
        } else {
            return new PagesDto(noticeList, 0, page, rows);
        }
        return new PagesDto(noticeList, pageInfo.getTotal(), page, rows);
    }

    @Override
    public int addNewNotice(Integer authUserId, SystemNoticeRequestDto systemNoticeRequestDto) {
        Date date = new Date();
        SystemNotice systemNotice = new SystemNotice();
        systemNotice.setSendId(authUserId);
        systemNotice.setTitle("");
        systemNotice.setContent(systemNoticeRequestDto.getContent());
        //通知方式 1-全渠道通知; 2-站内信 ; 3-app推送
        systemNotice.setNoticeWay((byte) 1);
        //消息类型 1-全局消息 ;2-社区消息(社区管理员发送的对本社区用户可见的消息)
        systemNotice.setType((byte) 1);
        //通知状态, 0-未发布;1-已发布
        systemNotice.setStatus((byte) 0);
        systemNotice.setCreateTime(date);
        systemNotice.setUpdateTime(date);
        int i = systemNoticeMapper.insertSelective(systemNotice);
        return i;
    }

    @Override
    public int updateNotice(SystemNoticeParams systemNoticeParams) {
        SystemNotice systemNotice = new SystemNotice();
        Date date = new Date();
        systemNotice.setId(systemNoticeParams.getId());
        systemNotice.setContent(systemNoticeParams.getContent());
        systemNotice.setUpdateTime(date);
        int i = systemNoticeMapper.updateByPrimaryKeySelective(systemNotice);
        return i;
    }

    @Override
    public SystemNotice selectNoticeById(Integer id) {
        SystemNotice systemNotice = systemNoticeMapper.selectByPrimaryKey(id);
        return systemNotice;
    }

    @Override
    public int deleteNotice(Integer id) {
        int i = systemNoticeMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int updatePublishNotice(Integer authUserId, Integer id) {
        SystemNotice systemNotice = new SystemNotice();
        systemNotice.setId(id);
        //发布人
        systemNotice.setSendId(authUserId);
        //发布
        systemNotice.setStatus((byte) 1);
        systemNotice.setPublishTime(new Date());
        int i = systemNoticeMapper.updateByPrimaryKeySelective(systemNotice);
        return i;
    }

}
