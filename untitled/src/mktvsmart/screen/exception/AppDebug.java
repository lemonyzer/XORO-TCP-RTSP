package mktvsmart.screen.exception;

import android.util.Log;

public class AppDebug {
//    final private static String LOG_FILE;

//    static
//    {
//        String s = (android.os.Environment.getExternalStorageState().equals((Object)"mounted")) ? new StringBuilder(String.valueOf((Object)android.os.Environment.getExternalStorageDirectory().toString())).append("/G-MScreen/log").toString() : new StringBuilder(String.valueOf((Object)android.os.Environment.getDownloadCacheDirectory().toString())).append("/G-MScreen/log").toString();
//        java.io.File a = new java.io.File(s);
//        if (!a.exists())
//        {
//            a.mkdirs();
//        }
//        LOG_FILE = new StringBuilder(String.valueOf((Object)s)).append("/log.txt").toString();
//        java.io.File a0 = new java.io.File(LOG_FILE);
//        boolean b = a0.exists();
//        label1: {
//            java.io.IOException a1 = null;
//            label0: {
//                if (!b)
//                {
//                    try
//                    {
//                        a0.createNewFile();
//                    }
//                    catch(java.io.IOException a2)
//                    {
//                        a1 = a2;
//                        break label0;
//                    }
//                }
//                break label1;
//            }
//            a1.printStackTrace();
//        }
//    }

    public AppDebug()
    {
        super();
    }

    public static void writeLog(String s)
    {
        Log.d("AppDebug", s);
//        //monenter(mktvsmart.screen.exception.AppDebug.class);
//        try
//        {
//            try
//            {
//                java.io.FileWriter a = new java.io.FileWriter(LOG_FILE, true);
//                java.util.Date a0 = java.util.Calendar.getInstance().getTime();
//                a.write(new StringBuilder(String.valueOf((Object)new java.text.SimpleDateFormat("yyyy_MM_dd_HH:mm", java.util.Locale.US).format(a0))).append(" :  ").append(s).toString());
//                a.write("\r\n");
//                a.close();
//            }
//            catch(java.io.IOException a1)
//            {
//                a1.printStackTrace();
//            }
//        }
//        catch(Throwable a2)
//        {
//            //monexit(mktvsmart.screen.exception.AppDebug.class);
//            throw a2;
//        }
//        //monexit(mktvsmart.screen.exception.AppDebug.class);
    }
}
