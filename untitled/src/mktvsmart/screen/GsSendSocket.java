package mktvsmart.screen;

import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by it on 18.07.2016.
 */
public class GsSendSocket {


    public static boolean sendOnlyCommandSocketToStb(java.net.Socket a, int i) {

        byte[] a0 = null;
        mktvsmart.screen.dataconvert.parser.DataParser a1 = mktvsmart.screen.dataconvert.parser.ParserFactory.getParser();
        try
        {
            a0 = a1.serialize((java.util.List)null, i).getBytes("UTF-8");
        }
        catch(Exception a2)
        {
            a2.printStackTrace();
            a0 = null;
        }
        return mktvsmart.screen.GsSendSocket.sendSocketToStb(a0, a, 0, a0.length, i);

    }

    public static boolean sendSocketToStb(byte[] a, java.net.Socket a0, int i, int i0, int i1)
    {
        boolean b = false;
        //monenter(mktvsmart.screen.GsSendSocket.class);
        try
        {
            String s = new String(mktvsmart.screen.GsSendSocket.subBytes(a, i, i0));
            int i2 = new StringBuilder().append(i0).toString().length();
            String s0 = "";
            int i3 = 0;
            while(i3 < 7 - i2)
            {
                s0 = new StringBuilder(String.valueOf((Object)s0)).append("0").toString();
                i3 = i3 + 1;
            }
            byte[] a1 = new StringBuilder("Start").append(s0).append(i0).append("End").append(s).toString().getBytes();
            try
            {
                System.out.println("ssWrite to Socket: " + new String(a1, "UTF-8"));
                java.io.OutputStream a2 = a0.getOutputStream();
                a2.write(a1, 0, a1.length);
                a2.flush();
                b = true;
            }
            catch(Exception a3)
            {
                a3.printStackTrace();
                b = false;
            }
        }
        catch(Throwable a4)
        {
            //monexit(mktvsmart.screen.GsSendSocket.class);
            throw a4;
        }
        //monexit(mktvsmart.screen.GsSendSocket.class);
        return b;
    }

    private static byte[] subBytes(final byte[] array, final int n, final int n2) {
        final byte[] array2 = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            array2[i] = array[i + n];
        }
        return array2;
    }

}
