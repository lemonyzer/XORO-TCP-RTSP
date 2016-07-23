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

    Client client;
    InputStream inStream;
    Socket getSocket;
    private boolean enableRecvUsefulData;
    private boolean interruptFlag;
    MessageHeader currentMsgHeader;

    long runBegin;
    long dotStep = 500000000;
    long nextDot;

    long n4;
    boolean b = true;

    public ReceiveSocketThread(final InputStream inStream, Client client) {

        this.inStream = inStream;
        this.client = client;
    }

    @Override
    public void run() {
        super.run();

        runBegin = SystemClock.uptimeMillis();
        nextDot = runBegin;

        getSocket = null;
        final CreateSocket createSocket = new CreateSocket("", 0);

        while(true)
        {

            WaitingForSocket (createSocket);

            try {
                if (!this.enableRecvUsefulData) {

                    byte[] headerData = ReceiveMessageHeader();
                    if(headerData == null) {
                        // fange von vorne an....
                        enableRecvUsefulData = false;    // lese als nächstes MessageHeader

                        continue;
                    }
                    else
                    {
                        // header empfangen, auswerten
                        currentMsgHeader = new MessageHeader(headerData);

                        if (!currentMsgHeader.isValid) {
                            // fange von vorne an...
                            System.out.println("currentMsgHeader is INVALID");
                            enableRecvUsefulData = false;    // lese als nächstes MessageHeader
                            continue;
                        }
                        else
                        {
                            enableRecvUsefulData = true;    // lese als nächstes MessageData
                            System.out.println("currentMsgHeader is VALID");
                        }

                        continue;

                    }
                }

                // message empfangen
                byte[] rawMsgData = ReceiveMessageData(currentMsgHeader);

                long uptimeMillis = n4;

                if(rawMsgData != null) {

                    Label_1271:
                    {
                        if (currentMsgHeader.getN2() > 2000) {
                            uptimeMillis = n4;
                            if (currentMsgHeader.getN2() < 2999) {
                                break Label_1271;
                            }

                        }
                        uptimeMillis = SystemClock.uptimeMillis();
                    }
                    if (currentMsgHeader.getN2() == 2015) {
                        GMScreenGlobalInfo.getCurStbInfo().setClient_type(0);
                    }


                    Message msg = Message.obtain();
                    msg.arg1 = 0;
                    msg.arg2 = currentMsgHeader.getArg2();

                    byte[] uncompressedMsg = null;
                    if (currentMsgHeader.getN() != 0) {

                        // message deflate (aufblasen) .... entpacken
                        uncompressedMsg = UncompressCompleteMsgData(rawMsgData);
                        msg.arg1 = uncompressedMsg.length;
                    }

                    msg.what = currentMsgHeader.getN2();
                    Bundle bundle = new Bundle();
                    bundle.putByteArray("ReceivedData", uncompressedMsg);
                    msg.setData(bundle);
                    //this.msgProc.postMessage(msg);
                    client.DisplayMesssage(msg);

                    client.HandleResponse(currentMsgHeader, msg);
                }


            } catch (Exception e)
            {
                e.printStackTrace();
            }

            enableRecvUsefulData = false;

        }

    }

    private void WaitingForSocket(CreateSocket createSocket) {
        System.out.print(this.toString() + " WaitingForSocket");
        while (true) {
            try {
                getSocket = createSocket.GetSocket();
                //this.enableRecvUsefulData = false;
                n4 = SystemClock.uptimeMillis();

                if( n4 - nextDot > dotStep ) {
                    nextDot += dotStep;
                    System.out.print(".");
                }

                if (this.interruptFlag) {
                    System.out.println("run interrupt");
                    return;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                //continue Label_0059_Outer;
                return;
            }
            break;
        }
        System.out.print("\n");
    }

    private byte[] ReceiveMessageHeader() {
        System.out.print(this.toString() + " ReceiveMessageHeader");
        int receivedBytes = 0;
        byte[] headerData = new byte[2048];
        try {
            receivedBytes = inStream.read(headerData, 0, MessageHeader.messageHeadLength);
            if (receivedBytes == -1) {
                // error
                //this.msgProc.postEmptyMessage(4112);
                this.interruptFlag = true;
                //AppDebug.writeLog("App return login menu, because receive data is empty.");
                System.out.println(" App return login menu, because receive data is empty.");
                return null;
            }
            else
            {
                // header received
                System.out.println(" header received");
                return headerData;
            }
        }
        catch (SocketTimeoutException ex4) {
            // TIME OUT - heart beat
            if(!b) {
                b = false;
                boolean sendOnlyCommandSocketToStb = GsSendSocket.sendOnlyCommandSocketToStb(getSocket, 26);
                final String tag = this.toString();
                final StringBuilder sb = new StringBuilder("send heart run ");
                String s;
                if (sendOnlyCommandSocketToStb) {
                    s = "ok";
                } else {
                    s = "fail";
                }
                Log.d(tag, sb.append(s).toString());
                return null;
            }

//            if (SystemClock.uptimeMillis() - n4 > 30000L) {
//                this.msgProc.postEmptyMessage(4112);
//                this.interruptFlag = true;
//                Log.d(SocketReceiveThread.TAG, "send heartrun over 30 seconds, nothing reveive");
//                AppDebug.writeLog("App return login menu, kepp alive msg timeout beyond 3 times");
//                n = n5;
//                n2 = n6;
//                arg2 = n7;
//                return null;
//            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if( SystemClock.uptimeMillis() - nextDot > dotStep ) {
                nextDot += dotStep;
                System.out.print(",");
            }
        }
        return null;
    }

    private byte[] ReceiveMessageData(MessageHeader msgHeader) {
        System.out.print(this.toString() + " ReceiveMessageData");

        int messageDataLength = msgHeader.getN();
        int totalReceivedBytes = 0;
        int numOfBytesLeftToReceive = msgHeader.getN();
        byte[] completeMsgData = new byte[messageDataLength + 8];
        byte[] currentMsgData = new byte[2048];
        while (totalReceivedBytes < messageDataLength) {

            if( SystemClock.uptimeMillis() - nextDot > dotStep ) {
                nextDot += dotStep;
                System.out.print("#");
            }

            try {
                int receivedBytes = inStream.read(currentMsgData, 0, Math.min(numOfBytesLeftToReceive, 2048));
                if (receivedBytes != -1) {
                    System.arraycopy(currentMsgData, 0, completeMsgData, totalReceivedBytes, receivedBytes);
                    totalReceivedBytes += receivedBytes;
                    numOfBytesLeftToReceive -= receivedBytes;
                    Log.d(this.toString(), " RM: receivedBytes=" + receivedBytes + " numOfBytesLeft=" + numOfBytesLeftToReceive);
                }
                else
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(totalReceivedBytes == messageDataLength)
        {
            return completeMsgData;
        }

        return null;
    }

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

