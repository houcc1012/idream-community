/**
 *
 */
package com.idream.service;

import com.idream.commons.lib.model.TagInfo;
import com.idream.commons.lib.model.TagInfoTree;

import java.util.List;

/**
 * @author xiaogang
 */
public interface TagInfoService {


    List<TagInfoTree> getAll();

    void addEntity(TagInfo bean);

}
