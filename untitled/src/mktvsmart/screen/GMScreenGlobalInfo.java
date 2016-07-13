package mktvsmart.screen;

public class GMScreenGlobalInfo {
    final public static String DEFAULT_BUILT_IN_PLAYER = "com.mktech.player";
    final public static String DEFAULT_EXTERNAL_PLAYER = "com.mxtech.videoplayer.ad";
    final public static int GMS_CLIENT_TYPE_MASTER = 0;
    final public static int GMS_CLIENT_TYPE_SLAVE = 1;
    final public static int GS_PLATFORM_ALI_3329 = 3;
    final public static int GS_PLATFORM_ALI_3510E = 11;
    final public static int GS_PLATFORM_ALI_3511 = 8;
    final public static int GS_PLATFORM_ALI_3516 = 9;
    final public static int GS_PLATFORM_ALI_3601 = 6;
    final public static int GS_PLATFORM_ALI_3606 = 5;
    final public static int GS_PLATFORM_ALI_3616 = 7;
    final public static int GS_PLATFORM_ALI_3618 = 10;
    final public static int GS_PLATFORM_ALI_3821 = 12;
    final public static int GS_PLATFORM_BCM = 2;
    final public static int GS_PLATFORM_GPRS_6500 = 46;
    final public static int GS_PLATFORM_GX3200 = 40;
    final public static int GS_PLATFORM_GX6601 = 41;
    final public static int GS_PLATFORM_GX6601E = 42;
    final public static int GS_PLATFORM_GX6622 = 44;
    final public static int GS_PLATFORM_G_CARD = 100;
    final public static int GS_PLATFORM_HAISI = 1;
    final public static int GS_PLATFORM_HD7101 = 0;
    final public static int GS_PLATFORM_HISI3712 = 72;
    final public static int GS_PLATFORM_HISI3719 = 71;
    final public static int GS_PLATFORM_HISI3796 = 74;
    final public static int GS_PLATFORM_INVALID = 9999;
    final public static int GS_PLATFORM_MSD7819 = 89;
    final public static int GS_PLATFORM_MSD7821 = 88;
    final public static int GS_PLATFORM_MSTAR = 4;
    final public static int GS_PLATFORM_NT78326 = 25;
    final public static int GS_PLATFORM_SUNPLUS_1502 = 21;
    final public static int GS_PLATFORM_SUNPLUS_1512 = 20;
    final public static int GS_PLATFORM_TCS131 = 31;
    final public static int GS_PLATFORM_TCS188 = 32;
    final public static int GS_PLATFORM_TRIDENT_8471 = 30;
    final public static int GS_SAT_ENABLE = 1;
    final public static int GS_SAT_UNENABLE = 0;
    final public static int NO_PLAY = 0;
    final public static int PVR_MENU_ENABLE = 1;
    final public static int PVR_PLAY = 1;
    final public static int SAT2IP_DISABLE = 2;
    final public static int SAT2IP_ENABLE = 1;
    final public static int SAT2IP_PLAY = 2;
    final public static int SOCKET_TIME_OUT_EXCEPTION = -1;
    private static mktvsmart.screen.GsMobileLoginInfo curStbInfo;
    public static java.util.List favGroups;
    public static java.util.ArrayList favType;
    private static int mIsSdsOpen;
    private static int mMaxDebugDataLenthPerRequest;
    private static int mMaxPasswordNum;
    private static int mMaxProgramNumPerRequest;
    private static int mMaxProgramNumber;
    private static int mPvr2ipServerSupport;
    private static int mSatIndexSelected;
    private static boolean mSendEmailFinished;
    private static int mWaitDialogTimeOut;
    public static int playType;

    static
    {
        mSatIndexSelected = 100;
        mMaxPasswordNum = 4;
        mSendEmailFinished = true;
        mMaxProgramNumPerRequest = 100;
        mMaxDebugDataLenthPerRequest = 131072;
        mMaxProgramNumber = 6100;
        mPvr2ipServerSupport = 0;
        mIsSdsOpen = 0;
        mWaitDialogTimeOut = 30000;
        playType = 0;
        favType = new mktvsmart.screen.GMScreenGlobalInfo$1();
        favGroups = (java.util.List)(Object)new java.util.ArrayList();
        curStbInfo = null;
    }

    public GMScreenGlobalInfo()
    {
        super();
    }

    public static boolean check_is_apk_match_platform(int i)
    {
        switch(i){
            case 8: case 9: case 12: case 20: case 21: case 25: case 30: case 31: case 32: case 40: case 41: case 42: case 44: case 71: case 72: case 74: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static mktvsmart.screen.GsMobileLoginInfo getCurStbInfo()
    {
        if (curStbInfo == null)
        {
            curStbInfo = new mktvsmart.screen.GsMobileLoginInfo();
        }
        return curStbInfo;
    }

    public static int getCurStbPlatform()
    {
        return mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().getPlatform_id();
    }

    public static String getDefaultPlayer()
    {
        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().getPlatform_id()){
            case 20: case 21: case 25: case 30: case 32: case 41: case 42: case 44: case 71: case 72: case 74: {
                return "com.mktech.player";
            }
            default: {
                return "com.mxtech.videoplayer.ad";
            }
        }
    }

    public static int getIndexOfAllSat()
    {
        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().getPlatform_id()){
            case 30: case 31: case 32: case 71: case 72: case 74: {
                return 0;
            }
            case 25: {
                return 120;
            }
            case 20: case 21: {
                return 239;
            }
            default: {
                return 100;
            }
        }
    }

    public static int getMaxDebugDataLenthPerRequest()
    {
        mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().getPlatform_id();
        mMaxDebugDataLenthPerRequest = 131072;
        return mMaxDebugDataLenthPerRequest;
    }

    public static int getMaxProgramNumPerRequest()
    {
        mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().getPlatform_id();
        mMaxProgramNumPerRequest = 100;
        return mMaxProgramNumPerRequest;
    }

    public static int getmMaxPasswordNum()
    {
        return mMaxPasswordNum;
    }

    public static int getmMaxProgramNumber()
    {
        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().getPlatform_id()){
            case 20: case 21: case 25: case 40: case 41: case 42: case 44: {
                return 8000;
            }
            default: {
                return mMaxProgramNumber;
            }
        }
    }

    public static int getmPvr2ipServerSupport()
    {
        return mPvr2ipServerSupport;
    }

    public static int getmSatIndexSelected()
    {
        return mSatIndexSelected;
    }

    public static int getmWaitDialogTimeOut()
    {
        return mWaitDialogTimeOut;
    }

    public static boolean isChatSupport()
    {
        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().getPlatform_id()){
            case 8: case 9: case 12: {
                if (mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().getSw_sub_version() >= 14804)
                {
                    return true;
                }
            }
            default: {
                return false;
            }
        }
    }

    public static boolean isClientTypeSlave()
    {
        if (mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().getClient_type() != 1)
        {
            return false;
        }
        return true;
    }

    public static int isSdsOpen()
    {
        return mIsSdsOpen;
    }

    public static boolean ismSendEmailFinished()
    {
        return mSendEmailFinished;
    }

    public static void setSdsOpen(int i)
    {
        mIsSdsOpen = i;
    }

    public static void setmCurStbInfo(mktvsmart.screen.GsMobileLoginInfo a)
    {
        curStbInfo = a;
        mktvsmart.screen.dataconvert.parser.ParserFactory.setDataType(a.getSend_data_type());
    }

    public static void setmMaxPasswordNum(int i)
    {
        mMaxPasswordNum = i;
    }

    public static void setmPvr2ipServerSupport(int i)
    {
        mPvr2ipServerSupport = i;
    }

    public static void setmSatIndexSelected(int i)
    {
        mSatIndexSelected = i;
    }

    public static void setmSendEmailFinished(boolean b)
    {
        mSendEmailFinished = b;
    }
}
