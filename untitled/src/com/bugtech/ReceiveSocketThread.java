package com.bugtech;

import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import mktvsmart.screen.CreateSocket;
import mktvsmart.screen.GMScreenGlobalInfo;
import mktvsmart.screen.GsSendSocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.zip.DataFormatException;

/**
 * Created by it on 10.07.2016.
 */
public class ReceiveSocketThread extends Thread {


    final private static String TAG;
    final private int SOCKET_KEEP_ALIVE_TIMEOUT;
    private boolean enableRecvUsefulData;
    private java.io.InputStream inStream;
    private boolean interruptFlag;
//    private mktvsmart.screen.message.process.MessageProcessor msgProc;
    private int totalDataCount;

    private Client client;

    static
    {
        TAG = com.bugtech.ReceiveSocketThread.class.getSimpleName();
    }

    public ReceiveSocketThread(java.io.InputStream a, Client client)
    {
        super("SocketReceiveThread");
        this.interruptFlag = false;
        this.totalDataCount = 0;
        this.SOCKET_KEEP_ALIVE_TIMEOUT = 30000;
        this.inStream = a;
        this.client = client;
    }

    public void interrupt()
    {
        this.interruptFlag = true;
//        ((Thread)this).interrupt();
    }

    public void run()
    {
        java.net.Socket a = null;
        byte[] a0 = new byte[2048];
//        this.msgProc = mktvsmart.screen.message.process.MessageProcessor.obtain();
        mktvsmart.screen.CreateSocket a1 = new mktvsmart.screen.CreateSocket("", 0);
        try
        {
            a = a1.GetSocket();
        }
        catch(Exception a2)
        {
            a2.printStackTrace();
            a = null;
        }
        this.enableRecvUsefulData = false;
        long j = android.os.SystemClock.uptimeMillis();
        boolean b = true;
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        label6: while(!this.interruptFlag)
        {
            java.io.IOException a3 = null;
            boolean b0 = this.enableRecvUsefulData;
            label0: {
                {
                    label7: {
                        int i2 = 0;
                        int i3 = 0;
                        int i4 = 0;
                        boolean b1 = false;
                        long j0 = 0L;
                        try
                        {
                            label2: {
                                java.net.SocketException a4 = null;
                                if (b0)
                                {
                                    byte[] a5 = new byte[i + 8];
                                    this.totalDataCount = 0;
                                    int i5 = i;
                                    while(true)
                                    {
                                        int i6 = this.totalDataCount;
                                        label5: {
                                            java.net.SocketTimeoutException a6 = null;
                                            if (i6 >= i5)
                                            {
                                                break label5;
                                            }
                                            try
                                            {
                                                i2 = i5;
                                                i3 = i0;
                                                i4 = i1;
                                                b1 = b;
                                                j0 = j;
                                                int i7 = this.inStream.read(a0, 0, Math.min(i, 2048));
                                                if (i7 == -1)
                                                {
                                                    break label5;
                                                }
                                                i2 = i5;
                                                i3 = i0;
                                                i4 = i1;
                                                b1 = b;
                                                j0 = j;
                                                System.arraycopy((Object)a0, 0, (Object)a5, this.totalDataCount, i7);
                                                this.totalDataCount = this.totalDataCount + i7;
                                                i = i - i7;
                                                continue;
                                            }
                                            catch(java.net.SocketTimeoutException a7)
                                            {
                                                a6 = a7;
                                            }
                                            java.io.PrintStream a8 = System.out;
                                            i2 = i5;
                                            i3 = i0;
                                            i4 = i1;
                                            b1 = b;
                                            j0 = j;
                                            a8.println(new StringBuilder("gmscreen SocketTimeoutException=totalDataCount=").append(this.totalDataCount).toString());
                                            android.os.Message a9 = android.os.Message.obtain();
                                            a9.arg1 = 0;
                                            a9.arg2 = -1;
                                            a9.what = i0;
                                            i2 = i5;
                                            i3 = i0;
                                            i4 = i1;
                                            b1 = b;
                                            j0 = j;
//                                            this.msgProc.postMessage(a9);
                                            i2 = i5;
                                            i3 = i0;
                                            i4 = i1;
                                            b1 = b;
                                            j0 = j;
                                            a6.printStackTrace();
                                        }
                                        int i8 = this.totalDataCount;
                                        boolean b2 = b;
                                        i = i5;
                                        long j1 = j;
                                        if (i8 == i5)
                                        {
                                            label3: {
                                                label4: {
                                                    if (i0 <= 2000)
                                                    {
                                                        break label4;
                                                    }
                                                    if (i0 < 2999)
                                                    {
                                                        break label3;
                                                    }
                                                }
                                                i2 = i;
                                                i3 = i0;
                                                i4 = i1;
                                                b1 = b2;
                                                j0 = j1;
                                                j1 = android.os.SystemClock.uptimeMillis();
                                                b2 = true;
                                            }
                                            if (i0 == 2015)
                                            {
                                                i2 = i;
                                                i3 = i0;
                                                i4 = i1;
                                                b1 = b2;
                                                j0 = j1;
                                                mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().setClient_type(0);
                                            }
                                            i2 = i;
                                            i3 = i0;
                                            i4 = i1;
                                            b1 = b2;
                                            j0 = j1;
                                            android.os.Message a10 = android.os.Message.obtain();
                                            a10.arg1 = 0;
                                            a10.arg2 = i1;
                                            b = b2;
                                            j = j1;
                                            byte[] a11 = null;
                                            if (i == 0)
                                            {
                                                b = b2;
                                                j = j1;
                                            }
                                            else
                                            {
                                                i2 = i;
                                                i3 = i0;
                                                i4 = i1;
                                                b1 = b;
                                                j0 = j;
                                                //a11 = com.jcraft.jzlib.GsZilb.UnCompress(a5);
                                                a11 = UncompressCompleteMsgData(a5);
                                                a10.arg1 = a11.length;
                                            }
                                            i2 = i;
                                            i3 = i0;
                                            i4 = i1;
                                            b1 = b;
                                            j0 = j;
                                            a10.what = i0;
                                            android.os.Bundle a12 = new android.os.Bundle();
                                            b1 = b;
                                            j0 = j;
                                            i2 = i;
                                            i3 = i0;
                                            i4 = i1;
                                            a12.putByteArray("ReceivedData", a11);
                                            b1 = b;
                                            j0 = j;
                                            i2 = i;
                                            i3 = i0;
                                            i4 = i1;
                                            a10.setData(a12);
                                            b1 = b;
                                            j0 = j;
                                            i2 = i;
                                            i3 = i0;
                                            i4 = i1;
                                            client.HandleResponse(a10);
//                                            this.msgProc.postMessage(a10);
                                        }
                                        this.enableRecvUsefulData = false;
                                        continue label6;
                                    }
                                }
                                else
                                {
                                    try
                                    {
                                        try
                                        {
                                            i2 = i;
                                            i3 = i0;
                                            i4 = i1;
                                            b1 = b;
                                            j0 = j;
                                            if (this.inStream.read(a0, 0, 16) == -1)
                                            {
                                                i2 = i;
                                                i3 = i0;
                                                i4 = i1;
                                                b1 = b;
                                                j0 = j;
//                                                this.msgProc.postEmptyMessage(4112);
                                                this.interruptFlag = true;
                                                mktvsmart.screen.exception.AppDebug.writeLog("App return login menu, because receive data is empty.");
                                                continue;
                                            }
                                            else
                                            {
                                                i2 = i;
                                                i3 = i0;
                                                i4 = i1;
                                                b1 = b;
                                                j0 = j;
                                                StringBuilder a13 = new StringBuilder();
                                                int i9 = a0[0];
                                                int i10 = (char)i9;
                                                StringBuilder a14 = a13.append((char)i10);
                                                int i11 = a0[1];
                                                int i12 = (char)i11;
                                                StringBuilder a15 = a14.append((char)i12);
                                                int i13 = a0[2];
                                                int i14 = (char)i13;
                                                StringBuilder a16 = a15.append((char)i14);
                                                int i15 = a0[3];
                                                int i16 = (char)i15;
                                                if (!new String(a16.append((char)i16).toString()).equals((Object)"GCDH"))
                                                {
                                                    continue;
                                                }
                                                int i17 = a0[7];
                                                int i18 = i17 << 24 & -16777216;
                                                int i19 = a0[6];
                                                int i20 = i18 | i19 << 16 & 16711680;
                                                int i21 = a0[5];
                                                int i22 = i20 | i21 << 8 & 65280;
                                                int i23 = a0[4];
                                                int i24 = i22 | i23 & 255;
                                                int i25 = a0[11];
                                                int i26 = i25 << 24 & -16777216;
                                                int i27 = a0[10];
                                                int i28 = i26 | i27 << 16 & 16711680;
                                                int i29 = a0[9];
                                                int i30 = i28 | i29 << 8 & 65280;
                                                int i31 = a0[8];
                                                int i32 = i30 | i31 & 255;
                                                int i33 = a0[15];
                                                int i34 = i33 << 24 & -16777216;
                                                int i35 = a0[14];
                                                int i36 = i34 | i35 << 16 & 16711680;
                                                int i37 = a0[13];
                                                int i38 = i36 | i37 << 8 & 65280;
                                                int i39 = a0[12];
                                                int i40 = i38 | i39 & 255;
                                                this.enableRecvUsefulData = true;
                                                String s = TAG;
                                                i2 = i24;
                                                i3 = i32;
                                                i4 = i40;
                                                b1 = b;
                                                j0 = j;
                                                i = i24;
                                                i0 = i32;
                                                i1 = i40;
                                                i = i24;
                                                i0 = i32;
                                                i1 = i40;
                                                android.util.Log.d(s, new StringBuilder("control data Type = ").append(i32).toString());
                                                b = true;
                                                i = i24;
                                                i0 = i32;
                                                i1 = i40;
                                                continue;
                                            }
                                        }
                                        catch(java.net.SocketTimeoutException ignoredException)
                                        {
                                            break label2;
                                        }
                                    }
                                    catch(java.net.SocketException a17)
                                    {
                                        a4 = a17;
                                    }
                                }
                                i2 = i;
                                i3 = i0;
                                i4 = i1;
                                b1 = b;
                                j0 = j;
//                                this.msgProc.postEmptyMessage(4112);
                                this.interruptFlag = true;
                                mktvsmart.screen.exception.AppDebug.writeLog(new StringBuilder("App return login menu, SocketException :\n").append(a4.getMessage()).toString());
                                continue;
                            }
                            b1 = b;
                            j0 = j;
                            label1: {
                                if (b)
                                {
                                    break label1;
                                }
                                i2 = i;
                                i3 = i0;
                                i4 = i1;
                                b1 = b;
                                j0 = j;
                                if (android.os.SystemClock.uptimeMillis() - j <= 30000L)
                                {
                                    continue;
                                }
                                i2 = i;
                                i3 = i0;
                                i4 = i1;
                                b1 = b;
                                j0 = j;
//                                this.msgProc.postEmptyMessage(4112);
                                this.interruptFlag = true;
                                android.util.Log.d(TAG, "send heartrun over 30 seconds, nothing reveive");
                                mktvsmart.screen.exception.AppDebug.writeLog("App return login menu, kepp alive msg timeout beyond 3 times");
                                continue;
                            }
                            i2 = i;
                            i3 = i0;
                            i4 = i1;
                            j = android.os.SystemClock.uptimeMillis();
                            i2 = i;
                            i3 = i0;
                            i4 = i1;
                            b1 = false;
                            j0 = j;
                            boolean b3 = mktvsmart.screen.GsSendSocket.sendOnlyCommandSocketToStb(a, 26);
                            String s0 = TAG;
                            i2 = i;
                            i3 = i0;
                            i4 = i1;
                            b1 = false;
                            j0 = j;
                            StringBuilder a18 = new StringBuilder("send heart run ");
                            String s1 = b3 ? "ok" : "fail";
                            i2 = i;
                            i3 = i0;
                            i4 = i1;
                            b1 = false;
                            j0 = j;
                            android.util.Log.d(s0, a18.append(s1).toString());
                            break label7;
                        }
                        catch(java.io.IOException a19)
                        {
                            a3 = a19;
                            i = i2;
                            i0 = i3;
                            i1 = i4;
                            b = b1;
                            j = j0;
                            break label0;
                        }
                    }
                    b = false;
                }
                continue;
            }
            a3.printStackTrace();
        }
        System.out.println("run interrupt");
    }



//    Client client;
//    InputStream inStream;
//    Socket getSocket;
//    private boolean enableRecvUsefulData;
//    private boolean interruptFlag;
//    MessageHeader currentMsgHeader;
//
//    long runBegin;
//    long dotStep = 500000000;
//    long nextDot;
//
//    long n4;
//    boolean b = true;
//
//    public ReceiveSocketThread(final InputStream inStream, Client client) {
//
//        this.inStream = inStream;
//        this.client = client;
//    }
//
//    @Override
//    public void run() {
//        super.run();
//
//        runBegin = SystemClock.uptimeMillis();
//        nextDot = runBegin;
//
//        getSocket = null;
//        final CreateSocket createSocket = new CreateSocket("", 0);
//
//        while(true)
//        {
//
//            WaitingForSocket (createSocket);
//
//            try {
//                if (!this.enableRecvUsefulData) {
//
//                    byte[] headerData = ReceiveMessageHeader();
//                    if(headerData == null) {
//                        // fange von vorne an....
//                        enableRecvUsefulData = false;    // lese als nächstes MessageHeader
//
//                        continue;
//                    }
//                    else
//                    {
//                        // header empfangen, auswerten
//                        currentMsgHeader = new MessageHeader(headerData);
//
//                        if (!currentMsgHeader.isValid) {
//                            // fange von vorne an...
//                            System.out.println("currentMsgHeader is INVALID");
//                            enableRecvUsefulData = false;    // lese als nächstes MessageHeader
//                            continue;
//                        }
//                        else
//                        {
//                            enableRecvUsefulData = true;    // lese als nächstes MessageData
//                            System.out.println("currentMsgHeader is VALID");
//                        }
//
//                        continue;
//
//                    }
//                }
//
//                // message empfangen
//                byte[] rawMsgData = ReceiveMessageData(currentMsgHeader);
//
//                long uptimeMillis = n4;
//
//                if(rawMsgData != null) {
//
//                    Label_1271:
//                    {
//                        if (currentMsgHeader.getN2() > 2000) {
//                            uptimeMillis = n4;
//                            if (currentMsgHeader.getN2() < 2999) {
//                                break Label_1271;
//                            }
//
//                        }
//                        uptimeMillis = SystemClock.uptimeMillis();
//                    }
//                    if (currentMsgHeader.getN2() == 2015) {
//                        GMScreenGlobalInfo.getCurStbInfo().setClient_type(0);
//                    }
//
//
//                    Message msg = Message.obtain();
//                    msg.arg1 = 0;
//                    msg.arg2 = currentMsgHeader.getArg2();
//
//                    byte[] uncompressedMsg = null;
//                    if (currentMsgHeader.getN() != 0) {
//
//                        // message deflate (aufblasen) .... entpacken
//                        uncompressedMsg = UncompressCompleteMsgData(rawMsgData);
//                        msg.arg1 = uncompressedMsg.length;
//                    }
//
//                    msg.what = currentMsgHeader.getN2();
//                    Bundle bundle = new Bundle();
//                    bundle.putByteArray("ReceivedData", uncompressedMsg);
//                    msg.setData(bundle);
//                    //this.msgProc.postMessage(msg);
//                    client.DisplayMesssage(msg);
//
//                    client.HandleResponse(currentMsgHeader, msg);
//                }
//
//
//            } catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//            enableRecvUsefulData = false;
//
//        }
//
//    }
//
//    private void WaitingForSocket(CreateSocket createSocket) {
//        System.out.print(this.toString() + " WaitingForSocket");
//        while (true) {
//            try {
//                getSocket = createSocket.GetSocket();
//                //this.enableRecvUsefulData = false;
//                n4 = SystemClock.uptimeMillis();
//
//                if( n4 - nextDot > dotStep ) {
//                    nextDot += dotStep;
//                    System.out.print(".");
//                }
//
//                if (this.interruptFlag) {
//                    System.out.println("run interrupt");
//                    return;
//                }
//            }
//            catch (Exception ex) {
//                ex.printStackTrace();
//                //continue Label_0059_Outer;
//                return;
//            }
//            break;
//        }
//        System.out.print("\n");
//    }
//
//    private byte[] ReceiveMessageHeader() {
//        System.out.print(this.toString() + " ReceiveMessageHeader");
//        int receivedBytes = 0;
//        byte[] headerData = new byte[2048];
//        try {
//            receivedBytes = inStream.read(headerData, 0, MessageHeader.messageHeadLength);
//            if (receivedBytes == -1) {
//                // error
//                //this.msgProc.postEmptyMessage(4112);
//                this.interruptFlag = true;
//                //AppDebug.writeLog("App return login menu, because receive data is empty.");
//                System.out.println(" App return login menu, because receive data is empty.");
//                return null;
//            }
//            else
//            {
//                // header received
//                System.out.println(" header received");
//                return headerData;
//            }
//        }
//        catch (SocketTimeoutException ex4) {
//            // TIME OUT - heart beat
//            if(!b) {
//                b = false;
//                boolean sendOnlyCommandSocketToStb = GsSendSocket.sendOnlyCommandSocketToStb(getSocket, 26);
//                final String tag = this.toString();
//                final StringBuilder sb = new StringBuilder("send heart run ");
//                String s;
//                if (sendOnlyCommandSocketToStb) {
//                    s = "ok";
//                } else {
//                    s = "fail";
//                }
//                Log.d(tag, sb.append(s).toString());
//                return null;
//            }
//
////            if (SystemClock.uptimeMillis() - n4 > 30000L) {
////                this.msgProc.postEmptyMessage(4112);
////                this.interruptFlag = true;
////                Log.d(SocketReceiveThread.TAG, "send heartrun over 30 seconds, nothing reveive");
////                AppDebug.writeLog("App return login menu, kepp alive msg timeout beyond 3 times");
////                n = n5;
////                n2 = n6;
////                arg2 = n7;
////                return null;
////            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            if( SystemClock.uptimeMillis() - nextDot > dotStep ) {
//                nextDot += dotStep;
//                System.out.print(",");
//            }
//        }
//        return null;
//    }
//
//    private byte[] ReceiveMessageData(MessageHeader msgHeader) {
//        System.out.print(this.toString() + " ReceiveMessageData");
//
//        int messageDataLength = msgHeader.getN();
//        int totalReceivedBytes = 0;
//        int numOfBytesLeftToReceive = msgHeader.getN();
//        byte[] completeMsgData = new byte[messageDataLength + 8];
//        byte[] currentMsgData = new byte[2048];
//        while (totalReceivedBytes < messageDataLength) {
//
//            if( SystemClock.uptimeMillis() - nextDot > dotStep ) {
//                nextDot += dotStep;
//                System.out.print("#");
//            }
//
//            try {
//                int receivedBytes = inStream.read(currentMsgData, 0, Math.min(numOfBytesLeftToReceive, 2048));
//                if (receivedBytes != -1) {
//                    System.arraycopy(currentMsgData, 0, completeMsgData, totalReceivedBytes, receivedBytes);
//                    totalReceivedBytes += receivedBytes;
//                    numOfBytesLeftToReceive -= receivedBytes;
//                    Log.d(this.toString(), " RM: receivedBytes=" + receivedBytes + " numOfBytesLeft=" + numOfBytesLeftToReceive);
//                }
//                else
//                    break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if(totalReceivedBytes == messageDataLength)
//        {
//            return completeMsgData;
//        }
//
//        return null;
//    }
//
    byte[] UncompressCompleteMsgData(byte[] recvRawMsgData)
    {
        Log.d(this.toString() , " Start uncompress " + recvRawMsgData.length + " Bytes " + recvRawMsgData.toString());

        byte[] unCompress = null;
        if (recvRawMsgData.length > 8) {
            try {
                unCompress = MyImplementations.UnCompress(recvRawMsgData);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DataFormatException e) {
                e.printStackTrace();
            }
            //obtain2.arg1 = unCompress.length;
        }

        Log.d(this.toString() , " Stop uncompress " + recvRawMsgData.length + " Bytes " + "resulting in " + unCompress.length + " Bytes");

        return unCompress;
    }

}

