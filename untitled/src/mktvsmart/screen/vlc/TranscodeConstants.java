package mktvsmart.screen.vlc;

import mktvsmart.screen.*;
//import android.content.*;

public class TranscodeConstants
{
    private static final String PREFENCE_NAME = "transcode";
    public static final int[] aiBitrate_7588;
    public static final int[] aiBitrate_hisi;
    public static final String[] asResolution_7588;
    public static final String[] asResolution_hisi;
    public static int iCurBitrate;
    public static int iCurResolution;

    static {
        TranscodeConstants.iCurResolution = 1;
        TranscodeConstants.iCurBitrate = 0;
        aiBitrate_7588 = new int[] { 500, 800, 1000, 1500, 5000 };
        aiBitrate_hisi = new int[] { 256, 512, 1024, 2048 };
        asResolution_7588 = new String[] { "720x576", "576x384", "360x240" };
        asResolution_hisi = new String[] { "720x576", "720x480", "356x288", "320x240" };
//        switch (GMScreenGlobalInfo.getCurStbPlatform()) {
//            default: {
//                final SharedPreferences sharedPreferences = GMScreenApp.getAppContext().getSharedPreferences("transcode", 0);
//                TranscodeConstants.iCurResolution = sharedPreferences.getInt("7588_cur_resolution", 1);
//                TranscodeConstants.iCurBitrate = sharedPreferences.getInt("7588_cur_bitrate", 0);
//            }
//            case 71:
//            case 72:
//            case 74: {
//                final SharedPreferences sharedPreferences2 = GMScreenApp.getAppContext().getSharedPreferences("transcode", 0);
//                TranscodeConstants.iCurResolution = sharedPreferences2.getInt("3719_cur_resolution", 0);
//                TranscodeConstants.iCurBitrate = sharedPreferences2.getInt("3719_cur_bitrate", 1);
//                System.out.println("iCurBitrate = " + TranscodeConstants.iCurBitrate);
//            }
//        }
    }

    public static void saveTranscodeSetting(final int n, final int n2) {
//        switch (GMScreenGlobalInfo.getCurStbPlatform()) {
//            default: {
//                GMScreenApp.getAppContext().getSharedPreferences("transcode", 0).edit().putInt("7588_cur_resolution", n).putInt("7588_cur_bitrate", n2).apply();
//            }
//            case 71:
//            case 72:
//            case 74: {
//                GMScreenApp.getAppContext().getSharedPreferences("transcode", 0).edit().putInt("3719_cur_resolution", n).putInt("3719_cur_bitrate", n2).apply();
//            }
//        }
    }
}
