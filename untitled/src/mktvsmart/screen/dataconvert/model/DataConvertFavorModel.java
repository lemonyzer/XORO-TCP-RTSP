package mktvsmart.screen.dataconvert.model;

public class DataConvertFavorModel
{
    public static int favorNum;
    int favNameID;
    int favorIndex;
    String favorName;

    public int GetFavorIndex()
    {
        return this.favorIndex;
    }

    public String GetFavorName()
    {
        return this.favorName;
    }

    public void SetFavorIndex(int paramInt)
    {
        this.favorIndex = paramInt;
    }

    public void SetFavorName(String paramString)
    {
        this.favorName = paramString;
    }

    public int getFavorTypeID()
    {
        return this.favNameID;
    }

    public void setFavorTypeID(int paramInt)
    {
        this.favNameID = paramInt;
    }
}

/* Location:           mktvsmart.screen-dex2jar.jar
 * Qualified Name:     mktvsmart.screen.dataconvert.model.DataConvertFavorModel
 * JD-Core Version:    0.6.2
 */