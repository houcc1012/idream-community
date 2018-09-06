package com.idream.service;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.quartz.OperateQuartzJobParams;
import com.idream.commons.lib.dto.quartz.QueryQuartzJobData;
import com.idream.commons.lib.dto.quartz.QueryQuartzJobParams;

/**
 * @Author hejiang
 * @Description: quartz 任务相关操作
 * @Modified By:
 */
public interface QuartzJobService {

    /**
     * 添加 quartz 任务
     */
    void addJob(OperateQuartzJobParams params);

    /**
     * 暂停任务
     *
     * @param jobClassName job 全类名（必须要该类所在的包的全类名）
     * @param jobGroupName job 分组的名称
     */
    void pauseJob(String jobClassName, String jobGroupName);

    /**
     * 恢复暂停任务
     *
     * @param jobClassName job 全类名（必须要该类所在的包的全类名）
     * @param jobGroupName job 分组的名称
     */
    void resumeJob(String jobClassName, String jobGroupName);

    /**
     * 更新任务
     */
    void updateJob(OperateQuartzJobParams params);

    /**
     * 删除任务
     *
     * @param jobClassName job 全类名（必须要该类所在的包的全类名）
     * @param jobGroupName job 分组的名称
     */
    void deleteJob(String jobClassName, String jobGroupName);

    /**
     * 查询任务列表
     *
     * @param params
     */
    PagesDto<QueryQuartzJobData> queryJob(QueryQuartzJobParams params);
}
