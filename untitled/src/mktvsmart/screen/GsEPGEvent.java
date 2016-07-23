package mktvsmart.screen;

public class GsEPGEvent
{
    static final byte maxEpgLanguage = 5;
    private String ProgramId;
    private String ProgramName;
    private byte ageRating;
    private short[] descLanCode;
    private short[] descLen;
    private String endTime;
    private byte epgTimerType;
    private int eventDate;
    private String[] eventDesc;
    private int eventMonth;
    private String[] eventSubTitle;
    private String[] eventTitle;
    private String startTime;
    private short[] subtitleLanCode;
    private short[] subtitleLen;
    private short[] titleLanCode;
    private short[] titleLen;
    private byte totalEpgLanguage;

    public GsEPGEvent() {
        this.titleLanCode = new short[5];
        this.subtitleLanCode = new short[5];
        this.descLanCode = new short[5];
        this.titleLen = new short[5];
        this.subtitleLen = new short[5];
        this.descLen = new short[5];
        this.eventTitle = new String[5];
        this.eventSubTitle = new String[5];
        this.eventDesc = new String[5];
    }

    public byte getAgeRating() {
        return this.ageRating;
    }

    public short[] getDescLanCode() {
        return this.descLanCode;
    }

    public short[] getDescLen() {
        return this.descLen;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public byte getEpgTimerType() {
        return this.epgTimerType;
    }

    public int getEventDate() {
        return this.eventDate;
    }

    public String[] getEventDesc() {
        return this.eventDesc;
    }

    public int getEventMonth() {
        return this.eventMonth;
    }

    public String[] getEventSubTitle() {
        return this.eventSubTitle;
    }

    public String[] getEventTitle() {
        return this.eventTitle;
    }

    public String getProgramId() {
        return this.ProgramId;
    }

    public String getProgramName() {
        return this.ProgramName;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public short[] getSubtitleLanCode() {
        return this.subtitleLanCode;
    }

    public short[] getSubtitleLen() {
        return this.subtitleLen;
    }

    public short[] getTitleLanCode() {
        return this.titleLanCode;
    }

    public short[] getTitleLen() {
        return this.titleLen;
    }

    public byte getTotalEpgLanguage() {
        return this.totalEpgLanguage;
    }

    public void setAgeRating(final int n) {
        this.ageRating = (byte)n;
    }

    public void setDescLanCode(final int n, final int n2) {
        this.descLanCode[n] = (short)n2;
    }

    public void setDescLen(final int n, final int n2) {
        this.descLen[n] = (short)n2;
    }

    public void setEndTime(final String endTime) {
        this.endTime = endTime;
    }

    public void setEpgTimerType(final int n) {
        this.epgTimerType = (byte)n;
    }

    public void setEventDate(final int eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventDesc(final int n, final String s) {
        this.eventDesc[n] = s;
    }

    public void setEventMonth(final int eventMonth) {
        this.eventMonth = eventMonth;
    }

    public void setEventSubTitle(final int n, final String s) {
        this.eventSubTitle[n] = s;
    }

    public void setEventTitle(final int n, final String s) {
        this.eventTitle[n] = s;
    }

    public void setProgramId(final String programId) {
        this.ProgramId = programId;
    }

    public void setProgramName(final String programName) {
        this.ProgramName = programName;
    }

    public void setStartTime(final String startTime) {
        this.startTime = startTime;
    }

    public void setSubtitleLanCode(final int n, final int n2) {
        this.subtitleLanCode[n] = (short)n2;
    }

    public void setSubtitleLen(final int n, final int n2) {
        this.subtitleLen[n] = (short)n2;
    }

    public void setTitleLanCode(final int n, final int n2) {
        this.titleLanCode[n] = (short)n2;
    }

    public void setTitleLen(final int n, final int n2) {
        this.titleLen[n] = (short)n2;
    }

    public void setTotalEpgLanguage(final int n) {
        this.totalEpgLanguage = (byte)n;
    }
}
