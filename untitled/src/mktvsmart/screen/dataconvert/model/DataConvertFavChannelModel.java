package mktvsmart.screen.dataconvert.model;

public class DataConvertFavChannelModel
{
    private String ProgramId;
    private int selectListType;
    private int tvState;

    public String GetProgramId()
    {
        return this.ProgramId;
    }

    public void SetProgramId(String paramString)
    {
        this.ProgramId = paramString;
    }

    public int getChannelTpye()
    {
        return this.tvState;
    }

    public int getSelectListType()
    {
        return this.selectListType;
    }

    public void setChannelTpye(int paramInt)
    {
        this.tvState = paramInt;
    }

    public void setSelectListType(int paramInt)
    {
        this.selectListType = paramInt;
    }
}

/* Location:           mktvsmart.screen-dex2jar.jar
 * Qualified Name:     mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel
 * JD-Core Version:    0.6.2
 */