package mktvsmart.screen.gchat.bean;

import java.io.*;

public class GsChatUser implements Serializable
{
    private static final long serialVersionUID = 1L;
    private boolean mBlock;
    private int mState;
    private int mUserID;
    private String mUsername;

    public boolean getBlock() {
        return this.mBlock;
    }

    public int getState() {
        return this.mState;
    }

    public int getUserID() {
        return this.mUserID;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public void setBlock(final boolean mBlock) {
        this.mBlock = mBlock;
    }

    public void setState(final int mState) {
        this.mState = mState;
    }

    public void setUserID(final int mUserID) {
        this.mUserID = mUserID;
    }

    public void setUsername(final String mUsername) {
        this.mUsername = mUsername;
    }
}
