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

    public static byte[] UnCompress(byte[] compr) throws IOException
    {
        int comprLen = compr.length;
        int uncomprLen = 500000;
        byte[] uncompr = new byte[uncomprLen];
        int err;

        Inflater inflater = new Inflater();

        inflater.setInput(compr);
        inflater.setOutput(uncompr);

        err=inflater.init();
        CHECK_ERR(inflater, err, "inflateInit");

        while(inflater.total_out<uncomprLen &&
                inflater.total_in<comprLen) {

            inflater.avail_in=inflater.avail_out=1; /* force small buffers */
            err=inflater.inflate(JZlib.Z_NO_FLUSH);
            if(err==JZlib.Z_STREAM_END)
                break;

            if (CHECK_ERR(inflater, err, "inflate"))
                return null;

        }

        System.out.println("Inflater: total uncompressed data length = " + inflater.total_out);

        return uncompr;

    }

    static boolean CHECK_ERR(ZStream z, int err, String msg) {
        if(err != JZlib.Z_OK)
        {
            if(z.msg!=null)
                System.out.print(z.msg+" ");
            System.out.println(msg+" error: "+err);
            return true;
        }
        return false;
    }

    public static byte[] UnCompressTranslated(byte[] compressedData) throws IOException
    {
        byte[] a0 = new byte[3145728];
        try
        {
            java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(compressedData);
            com.jcraft.jzlib.ZInputStream zIn = new com.jcraft.jzlib.ZInputStream((java.io.InputStream)in);
            java.io.DataInputStream dataIn = new java.io.DataInputStream((java.io.InputStream)zIn);
            int nextReadLength = 1024;
            int totalNumOfBytesRead = 0;
            while(true)
            {
                int numberOfBytesActuallyRead  = dataIn.read(a0, totalNumOfBytesRead, nextReadLength);
                if (numberOfBytesActuallyRead == -1)
                {
                    // end of stream
                    byte[] a4 = new byte[totalNumOfBytesRead];
                    System.arraycopy((Object)a0, 0, (Object)a4, 0, totalNumOfBytesRead);
                    dataIn.close();
                    zIn.close();
                    in.close();
                    return a4;
                }
                totalNumOfBytesRead += numberOfBytesActuallyRead;
                nextReadLength += totalNumOfBytesRead;
                while(nextReadLength > 3145728 - totalNumOfBytesRead)
                {
                    nextReadLength /= 2;
                }
            }
        }
        catch(java.io.IOException a5)
        {
            a5.printStackTrace();
            throw a5;
        }
    }

    public static byte[] UnCompressUNFINISHED(byte[] compressedData) throws IOException
    {
        byte[] a0 = new byte[3145728];
        try
        {
            java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(compressedData);
            com.jcraft.jzlib.ZInputStream zIn = new com.jcraft.jzlib.ZInputStream((java.io.InputStream)in);
            java.io.DataInputStream dataIn = new java.io.DataInputStream((java.io.InputStream)zIn);
            int i = 1024;
            int i0 = 0;
            while(true)
            {
                // http://stackoverflow.com/questions/7475611/unknown-buffer-size-to-be-read-from-a-datainputstream-in-java
                int numberOfBytesActuallyRead  = dataIn.read(a0, i0, i);
                if (numberOfBytesActuallyRead == -1)
                {
                    // end of stream
                    byte[] a4 = new byte[i0];
                    System.arraycopy((Object)a0, 0, (Object)a4, 0, i0);
                    dataIn.close();
                    zIn.close();
                    in.close();
                    return a4;
                }
                i0 = i0 + numberOfBytesActuallyRead;
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

    public static byte[] UnCompressSLOW(byte[] a) throws IOException
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
