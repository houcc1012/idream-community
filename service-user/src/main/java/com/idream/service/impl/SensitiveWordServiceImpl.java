package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.setting.OperateSensitiveWordParams;
import com.idream.commons.lib.dto.setting.SearchSensitiveWordsDto;
import com.idream.commons.lib.dto.setting.SearchSensitiveWordsParams;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.SensitiveWordMapper;
import com.idream.commons.lib.model.SensitiveWord;
import com.idream.rabbit.publisher.SensitiveWordPublisher;
import com.idream.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author hejiang
 * @date 2018/8/28
 */
@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {

    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;

    @Autowired
    private SensitiveWordPublisher sensitiveWordPublisher;

    @Override
    public PagesDto<SearchSensitiveWordsDto> getSensitiveWords(SearchSensitiveWordsParams param) {
        PageHelper.startPage(param.getPage(), param.getRows());
        List<SearchSensitiveWordsDto> list = sensitiveWordMapper.selectSensitiveWordList(param);
        PageInfo<SearchSensitiveWordsDto> pageInfo = new PageInfo<>(list);
        return new PagesDto<>(list, pageInfo.getTotal(), param.getPage(), param.getRows());
    }

    @Override
    public void addSensitiveWord(JSONPublicParam<OperateSensitiveWordParams> param) {
        //校验敏感词是否存在
        int result = sensitiveWordMapper.countByWord(param.getRequestParam().getWord());
        if(result > 0){
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "敏感字已存在！");
        }
        SensitiveWord word = new SensitiveWord();
        word.setCreateTime(new Date());
        word.setUpdateTime(word.getCreateTime());
        word.setWord(param.getRequestParam().getWord());
        word.setPinYin("");
        sensitiveWordMapper.insert(word);
        sensitiveWordPublisher.publish("add sensitive word");
    }

    @Override
    public void updateSensitiveWord(JSONPublicParam<OperateSensitiveWordParams> param) {
        int result = sensitiveWordMapper.countByWordAndId(param.getRequestParam().getWord(), param.getRequestParam().getId());
        if(result > 0){
            throw new BusinessException(RetCodeConstants.VERIFY_ERROR, "敏感字已存在！");
        }
        SensitiveWord word = new SensitiveWord();
        word.setUpdateTime(new Date());
        word.setWord(param.getRequestParam().getWord());
        word.setId(param.getRequestParam().getId());
        int count = sensitiveWordMapper.updateByPrimaryKeySelective(word);
        if(count > 0){
            sensitiveWordPublisher.publish("update sensitive word");
        }
    }

    @Override
    public void deleteSensitiveWord(Integer authUserId, Integer id) {
        int count = sensitiveWordMapper.deleteByPrimaryKey(id);
        if(count > 0){
            sensitiveWordPublisher.publish("delete sensitive word");
        }
    }
}
