package mktvsmart.screen.dataconvert.model;

public class DataConvertDebugModel
{
    private int isEnableDebug;
    private int mRequestDataFrom;
    private int mRequestDataTo;
    private int totalDataLength;

    public DataConvertDebugModel() {
        this.totalDataLength = 0;
    }

    public int getDebugValue() {
        return this.isEnableDebug;
    }

    public int getRequestDataFrom() {
        return this.mRequestDataFrom;
    }

    public int getRequestDataTo() {
        return this.mRequestDataTo;
    }

    public int getTotalDataLength() {
        return this.totalDataLength;
    }

    public void setDebugValue(final int isEnableDebug) {
        this.isEnableDebug = isEnableDebug;
    }

    public void setRequestDataFrom(final int mRequestDataFrom) {
        this.mRequestDataFrom = mRequestDataFrom;
    }

    public void setRequestDataTo(final int mRequestDataTo) {
        this.mRequestDataTo = mRequestDataTo;
    }

    public void setTotalDataLength(final int totalDataLength) {
        this.totalDataLength = totalDataLength;
    }
}
