package mktvsmart.screen.dataconvert.model;

public class DataConvertGChatChannelInfoModel
{
    private String mEpg;
    private String mSatAngle;
    private int mServiceId;
    private int mTp;

    public String getEpg() {
        return this.mEpg;
    }

    public String getSatAngle() {
        return this.mSatAngle;
    }

    public int getServiceId() {
        return this.mServiceId;
    }

    public int getTp() {
        return this.mTp;
    }

    public void setEpg(final String mEpg) {
        this.mEpg = mEpg;
    }

    public void setSatAngle(final String mSatAngle) {
        this.mSatAngle = mSatAngle;
    }

    public void setServiceId(final int mServiceId) {
        this.mServiceId = mServiceId;
    }

    public void setTp(final int mTp) {
        this.mTp = mTp;
    }
}
