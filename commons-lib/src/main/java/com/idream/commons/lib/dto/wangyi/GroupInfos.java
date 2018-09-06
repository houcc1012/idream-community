package com.idream.commons.lib.dto.wangyi;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 00:21
 * @Description:
 */
public class GroupInfos {

    private String owner;
    private String tname;
    private int maxusers;
    private long tid;
    private int size;

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTname() {
        return tname;
    }

    public void setMaxusers(int maxusers) {
        this.maxusers = maxusers;
    }

    public int getMaxusers() {
        return maxusers;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public long getTid() {
        return tid;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

}

