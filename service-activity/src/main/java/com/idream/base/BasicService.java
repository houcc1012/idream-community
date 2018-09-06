package com.idream.base;

import com.idream.commons.lib.mapper.SensitiveWordMapper;
import com.idream.commons.mvc.utils.SensitiveWordUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hejiang
 * @date 2018/8/28
 */
@Service
public class BasicService {

    private Logger logger = LoggerFactory.getLogger(BasicService.class);

    @Resource
    private SensitiveWordMapper sensitiveWordMapper;

    public void initSensitiveWord(){
        List<String> sensitiveWords = sensitiveWordMapper.findWordAllNotRepeat();
        if(CollectionUtils.isNotEmpty(sensitiveWords)){
            SensitiveWordUtil.init(sensitiveWords);
        }
    }
}
