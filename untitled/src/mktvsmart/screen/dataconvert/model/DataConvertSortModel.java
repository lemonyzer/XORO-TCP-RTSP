package mktvsmart.screen.dataconvert.model;

import java.util.*;

public class DataConvertSortModel
{
    private int mMacroFlag;
    private int mSortType;
    private int mTvState;
    private ArrayList<String> sortTypeList;

    public ArrayList<String> getSortTypeList() {
        return this.sortTypeList;
    }

    public int getmMacroFlag() {
        return this.mMacroFlag;
    }

    public int getmSortType() {
        return this.mSortType;
    }

    public int getmTvState() {
        return this.mTvState;
    }

    public void setSortTypeList(final ArrayList<String> sortTypeList) {
        this.sortTypeList = sortTypeList;
    }

    public void setmMacroFlag(final int mMacroFlag) {
        this.mMacroFlag = mMacroFlag;
    }

    public void setmSortType(final int mSortType) {
        this.mSortType = mSortType;
    }

    public void setmTvState(final int mTvState) {
        this.mTvState = mTvState;
    }
}
