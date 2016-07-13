package mktvsmart.screen.dataconvert.model;

public class DataConvertSatModel
{
    private int mSatAngle;
    private int mSatDir;
    private int mSatIndex;
    private String mSatName;

    public String toString() {
        return mSatIndex + ", " + mSatName + " " +  mSatAngle;
    }

    public int getmSatAngle() {
        return this.mSatAngle;
    }

    public int getmSatDir() {
        return this.mSatDir;
    }

    public int getmSatIndex() {
        return this.mSatIndex;
    }

    public String getmSatName() {
        return this.mSatName;
    }

    public void setmSatAngle(final int mSatAngle) {
        this.mSatAngle = mSatAngle;
    }

    public void setmSatDir(final int mSatDir) {
        this.mSatDir = mSatDir;
    }

    public void setmSatIndex(final int mSatIndex) {
        this.mSatIndex = mSatIndex;
    }

    public void setmSatName(final String mSatName) {
        this.mSatName = mSatName;
    }
}
