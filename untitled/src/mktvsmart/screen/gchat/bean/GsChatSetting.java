package mktvsmart.screen.gchat.bean;

public class GsChatSetting
{
    private static GsChatSetting mInstance;
    private String mSerialNumber;
    private int mShowWindow;
    private int mUserId;
    private String mUsername;
    private int mWindowPosition;
    private int mWindowSize;
    private int mWindowTransparency;

    static {
        GsChatSetting.mInstance = new GsChatSetting();
    }

    public static GsChatSetting getInstance() {
        return GsChatSetting.mInstance;
    }

    public int getSHowWindow() {
        return this.mShowWindow;
    }

    public String getSerialNumber() {
        return this.mSerialNumber;
    }

    public int getUserId() {
        return this.mUserId;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public int getWindowPosition() {
        return this.mWindowPosition;
    }

    public int getWindowSize() {
        return this.mWindowSize;
    }

    public int getWindowTransparency() {
        return this.mWindowTransparency;
    }

    public void setSerialNumber(final String mSerialNumber) {
        this.mSerialNumber = mSerialNumber;
    }

    public void setShowWindow(final int mShowWindow) {
        this.mShowWindow = mShowWindow;
    }

    public void setUserId(final int mUserId) {
        this.mUserId = mUserId;
    }

    public void setUsername(final String mUsername) {
        this.mUsername = mUsername;
    }

    public void setWindowPosition(final int mWindowPosition) {
        this.mWindowPosition = mWindowPosition;
    }

    public void setWindowSize(final int mWindowSize) {
        this.mWindowSize = mWindowSize;
    }

    public void setWindowTransparency(final int mWindowTransparency) {
        this.mWindowTransparency = mWindowTransparency;
    }
}
