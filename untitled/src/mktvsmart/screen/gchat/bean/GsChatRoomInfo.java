package mktvsmart.screen.gchat.bean;

import java.io.*;
import java.util.*;

public class GsChatRoomInfo implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String mEventTitle;
    private int mOnlineNum;
    private int mRoomID;
    private List<GsChatUser> mUserList;

    public GsChatRoomInfo() {
        this.mUserList = new ArrayList<GsChatUser>();
    }

    public String getEventTitle() {
        return this.mEventTitle;
    }

    public int getOnlineNum() {
        return this.mOnlineNum;
    }

    public int getRoomID() {
        return this.mRoomID;
    }

    public GsChatUser getUser(final int n) {
        return this.mUserList.get(n);
    }

    public List<GsChatUser> getUserList() {
        return this.mUserList;
    }

    public void removeUser(final int n) {
        this.mUserList.remove(n);
    }

    public void setEventTitle(final String mEventTitle) {
        this.mEventTitle = mEventTitle;
    }

    public void setOnlineNum(final int mOnlineNum) {
        this.mOnlineNum = mOnlineNum;
    }

    public void setRoomID(final int mRoomID) {
        this.mRoomID = mRoomID;
    }

    public void setUserList(final List<GsChatUser> mUserList) {
        this.mUserList = mUserList;
    }
}
