package com.idream.commons.lib.enums;

/**
 * @author hejiang
 */
public class BusinessEnum {

    /**
     * user_activity_status
     * 用户活动状态
     */
    public enum UserActivityStatusEnum {
        //参加中
        ATTEND(1),
        //已退出
        EXIST(2);

        private Integer code;

        private UserActivityStatusEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    /**
     * activity_task_status
     * 活动打卡状态
     */
    public enum ActivityTaskStatusEnum {
        //活动打卡未开始
        NOT_STARTED(1),
        //活动打卡进行中
        IN_PROGRESS(2),
        //活动打卡已结束
        HAVE_FINISHED(3);

        private Integer code;

        private ActivityTaskStatusEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    /**
     * activity_status
     * 活动状态
     */
    public enum ActivityStatusEnum {
        //未完成
        UNFINISHED(1),
        //已完成
        HAVE_FINISHED(2);

        private Integer code;

        private ActivityStatusEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    /**
     * 发现页  当前用户是否参加活动
     */
    public enum ActivityExitStatusEnum {
        //立即参加
        ATTEND_IMMEDIATELY(1),
        //进入社群
        ENTER_COMMUNITY(2),
        //活动结束
        ACTIVITY_OVER(3),
        //不显示活动
        NO_SHOW_ACTIVITY(4);

        private Integer code;

        private ActivityExitStatusEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    /**
     * 发现页  //判断定位当前城市是否有活动列表
     * 1:有活动,2:无活动,3:无定位权限
     */
    public enum ActivityListStatusEnum {
        //有活动
        HAVING_ACTIVITY(1),
        //无活动
        NO_HAVING_ACTIVITY(2),
        //无定位权限
        NO_LOCATION(3);

        private Integer code;

        private ActivityListStatusEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    /**
     * app端 社区认证绑定状态  建设中:认证社区:默认 | 0:1:2
     */
    public enum CommunityAuthStatusEnum {
        //社区建设中
        BUILDING_COMMUNITY(0),
        //认证社区
        AUTH_COMMUNITY(1),
        //默认
        DEFAULT_COMMUNITY(2);

        private Integer code;

        private CommunityAuthStatusEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    /**
     * app端我的社区button状态
     * 不显示我的社区button:显示我的社区button:显示未添加我的社区button | 0:1:2
     */
    public enum MyCommunityButtonStatusEnum {
        //不显示我的社区button
        NO_SHOW_MYCOMMUNITY_BUTTON(0),
        //显示我的社区button
        SHOW_MYCOMMUNITY_BUTTON(1),
        //显示未添加我的社区button
        SHOW_NO_ADD_MYCOMMUNITY_BUTTON(2);

        private Integer code;

        private MyCommunityButtonStatusEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

}
