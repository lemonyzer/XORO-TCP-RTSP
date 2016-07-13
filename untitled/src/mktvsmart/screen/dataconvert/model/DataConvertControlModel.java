package mktvsmart.screen.dataconvert.model;

public class DataConvertControlModel {
    private int AgeRatingSwitch;
    private int EditChannelLockSwitch;
    private int InstallLockSwitch;
    private int IsLockScreen;
    private int IsPowerOff;
    private int NetworkLockSwitch;
    private String Password;
    private int PswLockSwitch;
    private int ServiceLockSwitch;
    private int SettingsLockSwitch;
    private int sleepSwitch;
    private int sleepTime;

    public DataConvertControlModel()
    {
        super();
    }

    public int GetAgeRatingSwitch()
    {
        return this.AgeRatingSwitch;
    }

    public int GetEditChannelLockSwitch()
    {
        return this.EditChannelLockSwitch;
    }

    public int GetInstallLockSwitch()
    {
        return this.InstallLockSwitch;
    }

    public int GetIsLockScreen()
    {
        return this.IsLockScreen;
    }

    public int GetNetworkLockSwitch()
    {
        return this.NetworkLockSwitch;
    }

    public String GetPassword()
    {
        return this.Password;
    }

    public int GetPowerOff()
    {
        return this.IsPowerOff;
    }

    public int GetPswLockSwitch()
    {
        return this.PswLockSwitch;
    }

    public int GetServiceLockSwitch()
    {
        return this.ServiceLockSwitch;
    }

    public int GetSettingsLockSwitch()
    {
        return this.SettingsLockSwitch;
    }

    public void SetAgeRatingSwitch(int i)
    {
        this.AgeRatingSwitch = i;
    }

    public void SetEditChannelLockSwitch(int i)
    {
        this.EditChannelLockSwitch = i;
    }

    public void SetInstallLockSwitch(int i)
    {
        this.InstallLockSwitch = i;
    }

    public void SetIsLockScreen(int i)
    {
        this.IsLockScreen = i;
    }

    public void SetNetworkLockSwitch(int i)
    {
        this.NetworkLockSwitch = i;
    }

    public void SetPassword(String s)
    {
        this.Password = s;
    }

    public void SetPowerOff(int i)
    {
        this.IsPowerOff = i;
    }

    public void SetPswLockSwitch(int i)
    {
        this.PswLockSwitch = i;
    }

    public void SetServiceLockSwitch(int i)
    {
        this.ServiceLockSwitch = i;
    }

    public void SetSettingsLockSwitch(int i)
    {
        this.SettingsLockSwitch = i;
    }

    public int getSleepSwitch()
    {
        return this.sleepSwitch;
    }

    public int getSleepTime()
    {
        return this.sleepTime;
    }

    public void setSleepSwitch(int i)
    {
        this.sleepSwitch = i;
    }

    public void setSleepTime(int i)
    {
        this.sleepTime = i;
    }
}

