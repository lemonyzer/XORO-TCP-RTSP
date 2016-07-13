package mktvsmart.screen.dataconvert.model;

public class DataConvertChannelTypeModel
{
    public static int current_channel_tv_radio_type;
    private int isFavList;
    private int selectListType;
    private int tvRadioKeyPress;

    public static int getCurrent_channel_tv_radio_type() {
        return DataConvertChannelTypeModel.current_channel_tv_radio_type;
    }

    public static void setCurrent_channel_tv_radio_type(final int current_channel_tv_radio_type) {
        DataConvertChannelTypeModel.current_channel_tv_radio_type = current_channel_tv_radio_type;
    }

    public int getIsFavList() {
        return this.isFavList;
    }

    public int getSelectListType() {
        return this.selectListType;
    }

    public int getTvRadioKeyPress() {
        return this.tvRadioKeyPress;
    }

    public void setIsFavList(final int isFavList) {
        this.isFavList = isFavList;
    }

    public void setSelectListType(final int selectListType) {
        this.selectListType = selectListType;
    }

    public void setTvRadioKeyPress(final int tvRadioKeyPress) {
        this.tvRadioKeyPress = tvRadioKeyPress;
    }
}
