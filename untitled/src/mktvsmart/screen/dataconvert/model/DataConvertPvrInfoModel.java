package mktvsmart.screen.dataconvert.model;

public class DataConvertPvrInfoModel
{
    private String mProgramName;
    private int mPvrCrypto;
    private String mPvrDuration;
    private int mPvrId;
    private String mPvrTime;
    private int mPvrType;

    public String getProgramName() {
        return this.mProgramName;
    }

    public int getmPvrCrypto() {
        return this.mPvrCrypto;
    }

    public String getmPvrDuration() {
        return this.mPvrDuration;
    }

    public int getmPvrId() {
        return this.mPvrId;
    }

    public String getmPvrTime() {
        return this.mPvrTime;
    }

    public int getmPvrType() {
        return this.mPvrType;
    }

    public void setProgramName(final String mProgramName) {
        this.mProgramName = mProgramName;
    }

    public void setmPvrCrypto(final int mPvrCrypto) {
        this.mPvrCrypto = mPvrCrypto;
    }

    public void setmPvrDuration(final String mPvrDuration) {
        this.mPvrDuration = mPvrDuration;
    }

    public void setmPvrId(final int mPvrId) {
        this.mPvrId = mPvrId;
    }

    public void setmPvrTime(final String mPvrTime) {
        this.mPvrTime = mPvrTime;
    }

    public void setmPvrType(final int mPvrType) {
        this.mPvrType = mPvrType;
    }
}
