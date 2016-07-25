package mktvsmart.screen;

import com.bugtech.Client;
import mktvsmart.screen.dataconvert.parser.SerializedDataModel;

/**
 * Created by it on 18.07.2016.
 */
public class GsSendSocket {


    public static boolean sendOnlyCommandSocketToStb(java.net.Socket a, int i) {

        //byte[] a0 = null;
        SerializedDataModel a0 = null;
        mktvsmart.screen.dataconvert.parser.DataParser a1 = mktvsmart.screen.dataconvert.parser.ParserFactory.getParser();
        try
        {

            a0 = a1.serialize((java.util.List)null, i);
            a0.serializedData = a0.serializedDataAsString.getBytes("UTF-8");
        }
        catch(Exception a2)
        {
            a2.printStackTrace();
            a0 = null;
        }
        //return mktvsmart.screen.GsSendSocket.sendSocketToStb(a0, a, 0, a0.length, i, dataType);
        return mktvsmart.screen.GsSendSocket.sendSocketToStb(a0, a, 0, a0.length, i, dataType);

    }

    public static boolean sendSocketToStb(SerializedDataModel a, java.net.Socket socket, int i, int i0, int i1)
    {
        boolean b = false;
        //monenter(mktvsmart.screen.GsSendSocket.class);
        try
        {
            if(a == null)
                return false;
            String s = new String(mktvsmart.screen.GsSendSocket.subBytes(a.serializedData, i, i0));
            int i2 = new StringBuilder().append(i0).toString().length();
            String s0 = "";
            int i3 = 0;
            while(i3 < 7 - i2)
            {
                s0 = new StringBuilder(String.valueOf((Object)s0)).append("0").toString();
                i3 = i3 + 1;
            }
            // Start0000070End<?xml version="1.0" encoding="UTF-8"?><Command request="26"></Command>
            byte[] a1 = new StringBuilder("Start").append(s0).append(i0).append("End").append(s).toString().getBytes();
            try
            {
                System.out.println("ssWrite to Socket: " + new String(a1, "UTF-8"));
                Client.getInstance().AddSentCommand(a, i, i0, i1);
                java.io.OutputStream a2 = socket.getOutputStream();
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
            // Start0000070End<?xml version="1.0" encoding="UTF-8"?><Command request="26"></Command>
            byte[] a1 = new StringBuilder("Start").append(s0).append(i0).append("End").append(s).toString().getBytes();
            try
            {
                System.out.println("ssWrite to Socket: " + new String(a1, "UTF-8"));
                Client.getInstance().AddSentCommand(a, i, i0, i1);
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
