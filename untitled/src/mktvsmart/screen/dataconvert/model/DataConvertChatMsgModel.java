package mktvsmart.screen.dataconvert.model;

public class DataConvertChatMsgModel
{
    public static final int MSG_TYPE_IN = 0;
    public static final int MSG_TYPE_OUT = 1;
    private String mContent;
    private int mMsgType;
    private long mTimestamp;
    private int mUserID;
    private String mUsername;

    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof DataConvertChatMsgModel)) {
                return false;
            }
            final DataConvertChatMsgModel dataConvertChatMsgModel = (DataConvertChatMsgModel)o;
            if (this.getUserID() != dataConvertChatMsgModel.getUserID() || this.getMsgType() != dataConvertChatMsgModel.getMsgType() || !this.getContent().equals(dataConvertChatMsgModel.getContent()) || this.getTimestamp() != dataConvertChatMsgModel.getTimestamp() || !this.getUsername().equals(dataConvertChatMsgModel.getUsername())) {
                return false;
            }
        }
        return true;
    }

    public String getContent() {
        return this.mContent;
    }

    public int getMsgType() {
        return this.mMsgType;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public int getUserID() {
        return this.mUserID;
    }

    public String getUsername() {
        return this.mUsername;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.getContent() == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.getContent().hashCode();
        }
        if (this.getUsername() != null) {
            hashCode = this.getUsername().hashCode();
        }
        return ((((hashCode2 + 527) * 31 + hashCode) * 31 + (int)(this.getTimestamp() ^ this.getTimestamp() >> 32)) * 31 + this.getMsgType()) * 31 + this.getUserID();
    }

    public void setContent(final String mContent) {
        this.mContent = mContent;
    }

    public void setMsgType(final int mMsgType) {
        this.mMsgType = mMsgType;
    }

    public void setTimestamp(final long mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public void setUserID(final int mUserID) {
        this.mUserID = mUserID;
    }

    public void setUsername(final String mUsername) {
        this.mUsername = mUsername;
    }
}
