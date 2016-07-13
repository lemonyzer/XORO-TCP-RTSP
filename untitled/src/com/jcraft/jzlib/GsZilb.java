package com.jcraft.jzlib;

import java.io.*;

public class GsZilb
{
    private static final int BUFFERSIZE = 1024;
    private static final int MAXLENGTH = 3145728;
    
    public static byte[] Compress(byte[] byteArray) throws IOException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final ZOutputStream zOutputStream = new ZOutputStream(byteArrayOutputStream, 9);
            final DataOutputStream dataOutputStream = new DataOutputStream(zOutputStream);
            dataOutputStream.write(byteArray);
            dataOutputStream.flush();
            zOutputStream.close();
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public static byte[] UnCompressFail(final byte[] rawMsgData) throws IOException {
    Label_0089_Outer:
        while (true) {
            final byte[] array2 = new byte[3145728];
            while (true) {
                int nTotalRead = 0;
                int n2Step = 0;
                int read = 0;
                Label_0117: {
                    try {
                        final ByteArrayInputStream rawMsgDataInputStream = new ByteArrayInputStream(rawMsgData);
                        final ZInputStream zInputStream = new ZInputStream(rawMsgDataInputStream);
                        final DataInputStream dataInputStream = new DataInputStream(zInputStream);
                        nTotalRead = 0;
                        n2Step = 1024;
                        Label_0042: {
                            read = dataInputStream.read(array2, nTotalRead, n2Step);
                        }
                        if (read == -1) {
                            final byte[] array3 = new byte[nTotalRead];
                            System.arraycopy(array2, 0, array3, 0, nTotalRead);
                            dataInputStream.close();
                            zInputStream.close();
                            rawMsgDataInputStream.close();
                            return array3;
                        }
                        break Label_0117;
                        //while (true) {
                        //    final int n3 = n3 / 2;
                        //    final int n4;
                        //    nTotalRead = n4;
                        //    n2Step = n3;
                        //    continue Label_0089_Outer;
                        //}
                    }
                    // iftrue(Label_0042:, n3 <= 3145728 - n4)
                    catch (IOException ex) {
                        ex.printStackTrace();
                        throw ex;
                    }
                }
                final int n4 = nTotalRead + read;
                final int n3 = n2Step + n4;
                continue;
            }
        }
    }

    public static byte[] UnCompress(byte[] a) throws IOException
    {
        byte[] a0 = new byte[3145728];
        try
        {
            java.io.ByteArrayInputStream a1 = new java.io.ByteArrayInputStream(a);
            com.jcraft.jzlib.ZInputStream a2 = new com.jcraft.jzlib.ZInputStream((java.io.InputStream)a1);
            java.io.DataInputStream a3 = new java.io.DataInputStream((java.io.InputStream)a2);
            int i = 1024;
            int i0 = 0;
            while(true)
            {
                int i1 = a3.read(a0, i0, i);
                if (i1 == -1)
                {
                    byte[] a4 = new byte[i0];
                    System.arraycopy((Object)a0, 0, (Object)a4, 0, i0);
                    a3.close();
                    a2.close();
                    a1.close();
                    return a4;
                }
                i0 = i0 + i1;
                i = i + i0;
                while(i > 3145728 - i0)
                {
                    i = i / 2;
                }
            }
        }
        catch(java.io.IOException a5)
        {
            a5.printStackTrace();
            throw a5;
        }
    }

}
