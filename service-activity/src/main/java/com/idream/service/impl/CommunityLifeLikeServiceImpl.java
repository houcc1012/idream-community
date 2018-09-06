package com.idream.service.impl;

import com.idream.commons.lib.dto.app.CommunityLifeLikeCountDto;
import com.idream.commons.lib.dto.app.EgisLikeRecordDto;
import com.idream.commons.lib.dto.app.EgisLikeRecordResponseDto;
import com.idream.commons.lib.dto.app.LifeLikeResponseDto;
import com.idream.commons.lib.mapper.CommunityLifeLikeRecordMapper;
import com.idream.commons.lib.mapper.EgisLikeRecordMapper;
import com.idream.commons.lib.model.CommunityLifeLikeRecord;
import com.idream.service.CommunityLifeLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL2018-005 on 2018/5/18.
 */
@Service
public class CommunityLifeLikeServiceImpl implements CommunityLifeLikeService {

    @Autowired
    private CommunityLifeLikeRecordMapper communityLifeLikeRecordMapper;

    @Autowired
    private EgisLikeRecordMapper egisLikeRecordMapper;

    @Override
    public List<LifeLikeResponseDto> selectCommunityLifeLikeChecked(Integer authUserId) {
        EgisLikeRecordResponseDto egisLikeRecordResponseDto = egisLikeRecordMapper.selectLikeRecordId(authUserId);
        Date date = new Date();
        if (egisLikeRecordResponseDto == null) {
            return Collections.EMPTY_LIST;
        } else {
            List<LifeLikeResponseDto> lifeLikeCheckedList = communityLifeLikeRecordMapper.selectCommunityLifeLikeChecked(authUserId, egisLikeRecordResponseDto.getLikeRecordId(), egisLikeRecordResponseDto.getEmptyId());
            return lifeLikeCheckedList;
        }
    }

    @Override
    public List<LifeLikeResponseDto> selectCommunityLifeLikeUnchecked(Integer authUserId) {
        EgisLikeRecordResponseDto egisLikeRecordResponseDto = egisLikeRecordMapper.selectLikeRecordId(authUserId);
        Date date = new Date();
        if (egisLikeRecordResponseDto == null) {
            EgisLikeRecordDto egisLikeRecordDto = new EgisLikeRecordDto();
            egisLikeRecordDto.setUserId(authUserId);
            egisLikeRecordDto.setLikeRecordId(0);
            egisLikeRecordDto.setEmptyId(0);
            egisLikeRecordDto.setCreateTime(date);
            egisLikeRecordDto.setUpdateTime(date);
            communityLifeLikeRecordMapper.insertEgisLikeRecordByUserId(egisLikeRecordDto);
            List<LifeLikeResponseDto> lifeLikeUncheckedList = communityLifeLikeRecordMapper.selectCommunityLifeLikeUnchecked(authUserId, 0);
            //修改egis_like_record表中的like_record_id
            if (lifeLikeUncheckedList.size() > 0) {
                communityLifeLikeRecordMapper.updateEgisLikeRecordByUserId(lifeLikeUncheckedList.get(0).getCommunityLifeLikeId(), authUserId);
            }
            return lifeLikeUncheckedList;
        } else {
            List<LifeLikeResponseDto> lifeLikeUncheckedList = communityLifeLikeRecordMapper.selectCommunityLifeLikeUnchecked(authUserId, egisLikeRecordResponseDto.getLikeRecordId());
            //修改egis_like_record表中的like_record_id
            if (lifeLikeUncheckedList.size() > 0) {
                communityLifeLikeRecordMapper.updateEgisLikeRecordByUserId(lifeLikeUncheckedList.get(0).getCommunityLifeLikeId(), authUserId);
            }
            return lifeLikeUncheckedList;
        }
    }

    @Override
    public CommunityLifeLikeCountDto selectCountCommunityLifeLikeUnchecked(Integer authUserId) {
        EgisLikeRecordResponseDto egisLikeRecordResponseDto = egisLikeRecordMapper.selectLikeRecordId(authUserId);
        CommunityLifeLikeCountDto dto = new CommunityLifeLikeCountDto();
        if (egisLikeRecordResponseDto == null) {
            List<CommunityLifeLikeRecord> lifeLikeUncheckedList = communityLifeLikeRecordMapper.getCountCommunityLifeLikeUnchecked(authUserId);
            Integer likeCount = communityLifeLikeRecordMapper.selectLikeCount(authUserId);
            String image = null;
            if (lifeLikeUncheckedList.size() > 0) {
                image = communityLifeLikeRecordMapper.selectUserImage(lifeLikeUncheckedList.get(0).getUserId());
            }
            dto.setUnCheckedCount(likeCount);
            dto.setImage(image);
            return dto;
        } else {
            Integer likeCount = communityLifeLikeRecordMapper.selectCountCommunityLifeLikeUnchecked(authUserId, egisLikeRecordResponseDto.getLikeRecordId());
            List<CommunityLifeLikeRecord> lifeLikeUncheckedList = communityLifeLikeRecordMapper.getCommunityLifeLikeUnchecked(authUserId, egisLikeRecordResponseDto.getLikeRecordId());
            String image = null;
            if (lifeLikeUncheckedList.size() > 0) {
                image = communityLifeLikeRecordMapper.selectUserImage(lifeLikeUncheckedList.get(0).getUserId());
            }
            dto.setUnCheckedCount(likeCount);
            dto.setImage(image);
            return dto;
        }
    }

    @Override
    public int updateEmptyLike(Integer authUserId) {
        EgisLikeRecordResponseDto egisLikeRecordResponseDto = egisLikeRecordMapper.selectLikeRecordId(authUserId);
        Integer likeRecordId = egisLikeRecordResponseDto.getLikeRecordId();
        //Integer emptyId = egisLikeRecordResponseDto.getEmptyId();
        Date date = new Date();
        int i = egisLikeRecordMapper.updateEmptyLike(authUserId, likeRecordId, date);
        return i;
    }

}
