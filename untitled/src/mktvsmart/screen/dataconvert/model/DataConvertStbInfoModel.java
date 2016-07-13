package mktvsmart.screen.dataconvert.model;

public class DataConvertStbInfoModel
{
    private int mChannelNum;
    private int mMaxNumOfPrograms;
    private String mProductName;
    private String mSerialNumber;
    private String mSoftwareVersion;
    private int mStbStatus;

    public int getmChannelNum() {
        return this.mChannelNum;
    }

    public int getmMaxNumOfPrograms() {
        return this.mMaxNumOfPrograms;
    }

    public String getmProductName() {
        return this.mProductName;
    }

    public String getmSerialNumber() {
        return this.mSerialNumber;
    }

    public String getmSoftwareVersion() {
        return this.mSoftwareVersion;
    }

    public int getmStbStatus() {
        return this.mStbStatus;
    }

    public void setmChannelNum(final int mChannelNum) {
        this.mChannelNum = mChannelNum;
    }

    public void setmMaxNumOfPrograms(final int mMaxNumOfPrograms) {
        this.mMaxNumOfPrograms = mMaxNumOfPrograms;
    }

    public void setmProductName(final String mProductName) {
        this.mProductName = mProductName;
    }

    public void setmSerialNumber(final String mSerialNumber) {
        this.mSerialNumber = mSerialNumber;
    }

    public void setmSoftwareVersion(final String mSoftwareVersion) {
        this.mSoftwareVersion = mSoftwareVersion;
    }

    public void setmStbStatus(final int mStbStatus) {
        this.mStbStatus = mStbStatus;
    }
}
