package com.idream.commons.lib.dto.wangyi;


public class Admins {

    private long createtime;
    private long updatetime;
    private String nick;
    private String accid;
    private boolean mute;
    private String custom;

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setUpdatetime(long updatetime) {
        this.updatetime = updatetime;
    }

    public long getUpdatetime() {
        return updatetime;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getAccid() {
        return accid;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public boolean getMute() {
        return mute;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getCustom() {
        return custom;
    }

}