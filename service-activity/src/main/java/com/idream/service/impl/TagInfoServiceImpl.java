package com.idream.service.impl;

import com.idream.commons.lib.mapper.ActivityTagMapper;
import com.idream.commons.lib.model.TagInfo;
import com.idream.commons.lib.model.TagInfoTree;
import com.idream.service.TagInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaogang
 */
@Service
public class TagInfoServiceImpl implements TagInfoService {

    @Autowired
    private ActivityTagMapper tagInfoMapper;

    @Override
    public List<TagInfoTree> getAll() {
        List<TagInfoTree> parent = tagInfoMapper.getAllParent();
        List<TagInfoTree> children = tagInfoMapper.getAllChildren();

        for (int i = 0; i < parent.size(); i++) {
            List<TagInfoTree> tree = new ArrayList<>();
            for (TagInfoTree child : children) {
                if (child.getPid().equals(parent.get(i).getId())) {
                    tree.add(child);
                }
            }
            parent.get(i).setChildren(tree);
        }
        return parent;
    }

    @Override
    public void addEntity(TagInfo bean) {

//		tagInfoMapper.addTagInfo(bean);
    }

}
