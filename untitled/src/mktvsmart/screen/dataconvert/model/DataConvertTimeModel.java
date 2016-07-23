package mktvsmart.screen.dataconvert.model;

import java.io.Serializable;

public class DataConvertTimeModel
        implements Serializable
{
    public static int isConfirm = 0;
    private static final long serialVersionUID = 1L;
    public static int stbDay = 1;
    public static int stbHour = 0;
    public static int stbMin = 0;
    public static int stbMonth = 1;
    private String ProgramId;
    private int dayLen;
    private int hourLen;
    private boolean isShowItemDetail = false;
    private int minLen;
    private int timerDay;
    private int timerEndHour;
    private int timerEndMin;
    private int timerIndex;
    private int timerMonth;
    private String timerProgramName;
    private int timerRepeat;
    private int timerStartHour;
    private int timerStartMin;
    private int timerStatus;
    private int timerUniqID;

    public int GetEndHour()
    {
        return this.timerEndHour;
    }

    public int GetEndMin()
    {
        return this.timerEndMin;
    }

    public boolean GetShowDetail()
    {
        return this.isShowItemDetail;
    }

    public int GetStartHour()
    {
        return this.timerStartHour;
    }

    public int GetStartMin()
    {
        return this.timerStartMin;
    }

    public int GetTimeDay()
    {
        return this.timerDay;
    }

    public int GetTimeMonth()
    {
        return this.timerMonth;
    }

    public String GetTimeProgramName()
    {
        return this.timerProgramName;
    }

    public int GetTimerDayLen()
    {
        return this.dayLen;
    }

    public int GetTimerHourLen()
    {
        return this.hourLen;
    }

    public int GetTimerIndex()
    {
        return this.timerIndex;
    }

    public int GetTimerMinLen()
    {
        return this.minLen;
    }

    public int GetTimerRepeat()
    {
        return this.timerRepeat;
    }

    public int GetTimerStatus()
    {
        return this.timerStatus;
    }

    public void SetEndHour(int paramInt)
    {
        this.timerEndHour = paramInt;
    }

    public void SetEndMin(int paramInt)
    {
        this.timerEndMin = paramInt;
    }

    public void SetStartHour(int paramInt)
    {
        this.timerStartHour = paramInt;
    }

    public void SetStartMin(int paramInt)
    {
        this.timerStartMin = paramInt;
    }

    public void SetTimeDay(int paramInt)
    {
        this.timerDay = paramInt;
    }

    public void SetTimeMonth(int paramInt)
    {
        this.timerMonth = paramInt;
    }

    public void SetTimeProgramName(String paramString)
    {
        this.timerProgramName = paramString;
    }

    public void SetTimerDayLen(int paramInt)
    {
        this.dayLen = paramInt;
    }

    public void SetTimerHourLen(int paramInt)
    {
        this.hourLen = paramInt;
    }

    public void SetTimerIndex(int paramInt)
    {
        this.timerIndex = paramInt;
    }

    public void SetTimerMinLen(int paramInt)
    {
        this.minLen = paramInt;
    }

    public void SetTimerRepeat(int paramInt)
    {
        this.timerRepeat = paramInt;
    }

    public void SetTimerStatus(int paramInt)
    {
        this.timerStatus = paramInt;
    }

    public int getEventId()
    {
        return this.timerUniqID;
    }

    public String getProgramId()
    {
        return this.ProgramId;
    }

    public void setEventId(int paramInt)
    {
        this.timerUniqID = paramInt;
    }

    public void setProgramId(String paramString)
    {
        this.ProgramId = paramString;
    }

    public void setShowDetail(boolean paramBoolean)
    {
        this.isShowItemDetail = paramBoolean;
    }
}

/* Location:           mktvsmart.screen-dex2jar.jar
 * Qualified Name:     mktvsmart.screen.dataconvert.model.DataConvertTimeModel
 * JD-Core Version:    0.6.2
 */