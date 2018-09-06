package com.idream.tool;


import com.idream.job.BaseJob;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author hejiang
 * @Description: quartz工具类
 */
public class QuartzTool {

    /**
     * @param classname
     */
    public static BaseJob getClass(String classname) throws Exception {
        //非全路径开头的类名加上全路径
        if (!classname.startsWith("com.")) {
            classname = "com.idream.job.impl." + classname;
        }
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }

    /**
     * 获得任务分组名称
     *
     * @param jobClassName
     * @param jobGroupName
     */
    public static String getJobClassGroup(String jobClassName, String jobGroupName) {
        if (StringUtils.isEmpty(jobGroupName)) {
            int index = jobClassName.lastIndexOf(".");
            if (index > 0) {
                jobGroupName = jobClassName.substring(index + 1) + "_group";
            } else {
                jobGroupName = jobClassName + "_group";
            }
        }
        return jobGroupName;
    }

}
