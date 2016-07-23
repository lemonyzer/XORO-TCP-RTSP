package mktvsmart.screen;

import java.util.ArrayList;

public class GsEPGTableChannel
{

    public class EpgDay
    {
        private final GsEPGTableChannel this$0;
        private ArrayList<GsEPGEvent> arrayEventDay;

        public EpgDay(final GsEPGTableChannel this$0) {
            this.this$0 = this$0;
            this.arrayEventDay = new ArrayList();
        }

        public ArrayList<GsEPGEvent> getArrayList() {
            return (ArrayList<GsEPGEvent>)this.arrayEventDay;
        }
    }


    static final byte maxEpgDay = 7;
    private String ProgramId;
    private String ProgramName;
    private int[] arrayEventFields;
    private int currentEpgTime;
    public GsEPGTableChannel.EpgDay[] epgChannelEvent;
    private int originalNetworkID;
    private int progNo;
    private byte todayDate;
    private int transportStreamID;

    public GsEPGTableChannel() {
        this.arrayEventFields = new int[7];
        this.epgChannelEvent = new GsEPGTableChannel.EpgDay[7];
        for (int i = 0; i < 7; ++i) {
            this.epgChannelEvent[i] = new GsEPGTableChannel.EpgDay(this);
        }
    }

    public int[] getArrayEventFields() {
        return this.arrayEventFields;
    }

    public int getCurrentEpgTime() {
        return this.currentEpgTime;
    }

    public GsEPGTableChannel.EpgDay[] getEpgChannelEvent() {
        return this.epgChannelEvent;
    }

    public GsEPGTableChannel.EpgDay getEpgDayByIndex(final int n) {
        return this.epgChannelEvent[n];
    }

    public int getOriginalNetworkID() {
        return this.originalNetworkID;
    }

    public int getProgNo() {
        return this.progNo;
    }

    public String getProgramId() {
        return this.ProgramId;
    }

    public String getProgramName() {
        return this.ProgramName;
    }

    public byte getTodayDate() {
        return this.todayDate;
    }

    public int getTransportStreamID() {
        return this.transportStreamID;
    }

    public void setArrayEventFieldByIndex(final int n, final int n2) {
        this.arrayEventFields[n] = n2;
    }

    public void setCurrentEpgTime(final int currentEpgTime) {
        this.currentEpgTime = currentEpgTime;
    }

    public void setEpgChannelEvent(final GsEPGTableChannel.EpgDay[] epgChannelEvent) {
        this.epgChannelEvent = epgChannelEvent;
    }

    public void setOriginalNetworkID(final int originalNetworkID) {
        this.originalNetworkID = originalNetworkID;
    }

    public void setProgNo(final int progNo) {
        this.progNo = progNo;
    }

    public void setProgramId(final String programId) {
        this.ProgramId = programId;
    }

    public void setProgramName(final String programName) {
        this.ProgramName = programName;
    }

    public void setTodayDate(final byte todayDate) {
        this.todayDate = todayDate;
    }

    public void setTransportStreamID(final int transportStreamID) {
        this.transportStreamID = transportStreamID;
    }
}
