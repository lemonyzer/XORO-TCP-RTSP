package mktvsmart.screen;

public class FindPlayerAndPlayChannel {
    private android.content.Context mContext;
    private android.content.Intent mIntent;
    private mktvsmart.screen.FindPlayerAndPlayChannel.PlayByDesignatedPlayer mPlayChannel;

    public FindPlayerAndPlayChannel(android.content.Context a)
    {
        super();
        this.mContext = a;
    }

    public static String getRtspPipeFilePath(android.content.Context a)
    {
        return new StringBuilder(String.valueOf((Object)a.getFilesDir().getAbsolutePath())).append("/").append(a.getResources().getString(2131427377)).append(".ts").toString();
    }

    public void implementPlayByDesignatedPlayer(mktvsmart.screen.FindPlayerAndPlayChannel.PlayByDesignatedPlayer a)
    {
        this.mPlayChannel = a;
    }

    public void selectPlayer(int i)
    {
        String s = new mktvsmart.screen.EditPlayerSettingFile(this.mContext).getPlayerPkgName();
        if (s.equals((Object)"com.mktech.player"))
        {
            this.mPlayChannel.designatedBuiltInPlayer(i);
            return;
        }
        java.util.List a = new mktvsmart.screen.ParseThirdPlayer(this.mContext).queryAppInfo();
        boolean b = new mktvsmart.screen.ParseThirdPlayer(this.mContext).containPlayerInfo(a, s);
        android.net.Uri a0 = android.net.Uri.parse(mktvsmart.screen.FindPlayerAndPlayChannel.getRtspPipeFilePath(this.mContext));
        if (b)
        {
            this.mIntent = new android.content.Intent("android.intent.action.VIEW", a0);
            this.mIntent.setDataAndType(a0, "video/*");
            this.mIntent.setPackage(s);
            this.mIntent.setFlags(268435456);
            this.mPlayChannel.designatedExternalPlayer(i, this.mIntent);
            return;
        }
        String s0 = mktvsmart.screen.GMScreenGlobalInfo.getDefaultPlayer();
        new mktvsmart.screen.EditPlayerSettingFile(this.mContext).setPlayerPkgName(s0);
        this.mIntent = new android.content.Intent("android.intent.action.VIEW", a0);
        this.mIntent.setDataAndType(a0, "video/*");
        this.mIntent.setPackage(s0);
        this.mIntent.setFlags(268435456);
        if (s0.equals((Object)"com.mktech.player"))
        {
            this.mPlayChannel.designatedBuiltInPlayer(i);
            return;
        }
        if (!new mktvsmart.screen.ParseThirdPlayer(this.mContext).containPlayerInfo(a, s0))
        {
            this.mPlayChannel.playerNotExist();
            return;
        }
        this.mPlayChannel.designatedExternalPlayer(i, this.mIntent);
    }


    abstract public interface PlayByDesignatedPlayer {

        abstract public void designatedBuiltInPlayer(int arg);


        abstract public void designatedExternalPlayer(int arg, android.content.Intent arg0);


        abstract public void playerNotExist();
    }


}
