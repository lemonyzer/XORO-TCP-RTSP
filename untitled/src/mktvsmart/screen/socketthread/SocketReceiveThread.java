package mktvsmart.screen.socketthread;

public class SocketReceiveThread extends Thread {
    final private static String TAG;
    final private int SOCKET_KEEP_ALIVE_TIMEOUT;
    private boolean enableRecvUsefulData;
    private java.io.InputStream inStream;
    private boolean interruptFlag;
    private mktvsmart.screen.message.process.MessageProcessor msgProc;
    private int totalDataCount;

    static
    {
        TAG = mktvsmart.screen.socketthread.SocketReceiveThread.class.getSimpleName();
    }

    public SocketReceiveThread(java.io.InputStream a)
    {
        super("SocketReceiveThread");
        this.interruptFlag = false;
        this.totalDataCount = 0;
        this.SOCKET_KEEP_ALIVE_TIMEOUT = 30000;
        this.inStream = a;
    }

    public void interrupt()
    {
        this.interruptFlag = true;
        ((Thread)this).interrupt();
    }

    public void run()
    {
        java.net.Socket a = null;
        byte[] a0 = new byte[2048];
        this.msgProc = mktvsmart.screen.message.process.MessageProcessor.obtain();
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
                                            this.msgProc.postMessage(a9);
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
                                                a11 = com.jcraft.jzlib.GsZilb.UnCompress(a5);
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
                                            this.msgProc.postMessage(a10);
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
                                                this.msgProc.postEmptyMessage(4112);
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
                                this.msgProc.postEmptyMessage(4112);
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
                                this.msgProc.postEmptyMessage(4112);
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
}
