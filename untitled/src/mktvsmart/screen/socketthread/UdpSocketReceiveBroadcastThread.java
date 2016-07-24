package mktvsmart.screen.socketthread;

import com.bugtech.Client;

/**
 * Created by it on 16.07.2016.
 */
public class UdpSocketReceiveBroadcastThread extends Thread {

    final private static int TIMEOUT_5S = 5000;
    private String TAG;
    private boolean interruptFlag;
    //private mktvsmart.screen.message.process.MessageProcessor msgProc;
    private java.util.ArrayList stbInfoList;
    private long timeMark;
    private java.net.DatagramSocket udpBroadcastSocket;
    private java.net.DatagramPacket udpPacket;

    private Client client;

    public UdpSocketReceiveBroadcastThread(Client client)
    {
        super("UdpSocketReceiveBroadcastThread");
        this.TAG = mktvsmart.screen.socketthread.UdpSocketReceiveBroadcastThread.class.getSimpleName();
        this.interruptFlag = false;
        this.stbInfoList = new java.util.ArrayList();

        this.client = client;
    }

    public static void scramble_stb_info_for_broadcast(byte[] a, int i)
    {
        int i0 = 0;
        while(i0 < i / 2)
        {
            int i1 = a[i - 1 - i0];
            int i2 = i - 1 - i0;
            int i3 = a[i0];
            a[i2] = (byte)i3;
            a[i0] = (byte)i1;
            int i4 = a[i0];
            int i5 = (byte)(i4 ^ 91);
            a[i0] = (byte)i5;
            int i6 = i - 1 - i0;
            int i7 = a[i - 1 - i0];
            int i8 = (byte)(i7 ^ 91);
            a[i6] = (byte)i8;
            i0 = i0 + 1;
        }
        if (i % 2 != 0)
        {
            int i9 = i / 2;
            int i10 = a[i / 2];
            int i11 = (byte)(i10 ^ 91);
            a[i9] = (byte)i11;
        }
    }

    private void update_stb_info_to_login_list()
    {
        android.os.Message a = android.os.Message.obtain();
        a.what = 4113;
        a.obj = this.stbInfoList;
        //this.msgProc.postMessage(a);
        //client.AddDiscoveredStbDeviceToList(stbInfoList);
        client.UpdateDiscoveredStbList(stbInfoList);
    }

    public void interrupt()
    {
        android.util.Log.d(this.TAG, "recv interrupt2");
        this.interruptFlag = true;
        if (this.udpBroadcastSocket != null)
        {
            this.udpBroadcastSocket.disconnect();
            this.udpBroadcastSocket.close();
        }
        ((Thread)this).interrupt();
    }

    public void run()
    {
        //((Thread)this).run();
        //this.msgProc = mktvsmart.screen.message.process.MessageProcessor.obtain();
        try
        {
            this.udpBroadcastSocket = new java.net.DatagramSocket(25860);
            this.udpBroadcastSocket.setSoTimeout(5000);
            byte[] a = new byte[2048];
            this.udpPacket = new java.net.DatagramPacket(a, a.length);
            android.util.Log.d(this.TAG, "Thread  BroadcastThread onStart");
            this.timeMark = android.os.SystemClock.uptimeMillis();
            while(!this.interruptFlag)
            {
                {
                    label0: {
                        try
                        {
                            try
                            {
                                this.udpBroadcastSocket.receive(this.udpPacket);
                                String s = this.udpPacket.getAddress().getHostAddress();
                                byte[] dummy = new byte[this.udpPacket.getLength()];
                                byte[] a0 = this.udpPacket.getData();
                                if (this.udpPacket.getLength() != 108)
                                {
                                    continue;
                                }
                                mktvsmart.screen.socketthread.UdpSocketReceiveBroadcastThread.scramble_stb_info_for_broadcast(a0, this.udpPacket.getLength());
                                String s0 = new String(a0, 0, 12);
                                if (s0.equals((Object)"39WwijOog54a"))
                                {
                                    boolean b = false;
                                    mktvsmart.screen.GsMobileLoginInfo a1 = new mktvsmart.screen.GsMobileLoginInfo(a0);
                                    a1.setLastFoundTime(android.os.SystemClock.uptimeMillis());
                                    if (!mktvsmart.screen.GMScreenGlobalInfo.check_is_apk_match_platform(a1.getPlatform_id()))
                                    {
                                        break label0;
                                    }
                                    int i = 0;
                                    while(true)
                                    {
                                        if (i < this.stbInfoList.size())
                                        {
                                            mktvsmart.screen.GsMobileLoginInfo a2 = (mktvsmart.screen.GsMobileLoginInfo)this.stbInfoList.get(i);
//                                            System.out.println(a2.getStb_sn_disp() + " == " + a1.getStb_sn_disp() + " ???? ");
                                            if (!a2.getStb_sn_disp().equals((Object)a1.getStb_sn_disp()))
                                            {
//                                                System.out.println("false");

                                                i = i + 1;
                                                continue;
                                            }
//                                            System.out.println("true");

                                            a2.setLastFoundTime(a1.getLastFoundTime());
                                            if (a1.getIs_current_stb_connected_full() != 1)
                                            {
                                                this.stbInfoList.set(i, (Object)a1);
                                                if (!a2.getStb_ip_address_disp().equals((Object)a1.getStb_ip_address_disp()))
                                                {
                                                    this.update_stb_info_to_login_list();
                                                }
                                            }
                                            else
                                            {
                                                this.stbInfoList.remove(i);
                                                this.update_stb_info_to_login_list();
                                                android.util.Log.d(this.TAG, new StringBuilder("stb is full remove it ").append(s).toString());
                                            }
                                            b = false;
                                            break;
                                        }
                                        else
                                        {
                                            b = true;
                                            break;
                                        }
                                    }
                                    if (!b)
                                    {
                                        break label0;
                                    }
                                    if (a1.getIs_current_stb_connected_full() != 0)
                                    {
                                        break label0;
                                    }
                                    this.stbInfoList.add((Object)a1);
                                    this.update_stb_info_to_login_list();
                                    break label0;
                                }
                                else
                                {
                                    android.util.Log.d(this.TAG, new StringBuilder("Thread  BroadcastThread receive error data. magic code wrong!: ").append(s0).toString());
                                    break label0;
                                }
                            }
                            catch(java.net.SocketTimeoutException ignoredException)
                            {
                            }
                        }
                        catch(java.io.IOException a3)
                        {
                            a3.printStackTrace();
                            break label0;
                        }
                        this.stbInfoList.clear();
                        android.util.Log.d(this.TAG, "SocketTimeoutException, no device found");
                        this.update_stb_info_to_login_list();
                        this.timeMark = android.os.SystemClock.uptimeMillis();
                    }
                    if (android.os.SystemClock.uptimeMillis() - this.timeMark >= 5000L)
                    {
                        int i0 = 0;
                        boolean b0 = false;
                        while(i0 < this.stbInfoList.size())
                        {
                            mktvsmart.screen.GsMobileLoginInfo a4 = (mktvsmart.screen.GsMobileLoginInfo)this.stbInfoList.get(i0);
                            System.out.println("Time difference = " + (android.os.SystemClock.uptimeMillis() - a4.getLastFoundTime()));
                            if (android.os.SystemClock.uptimeMillis() - a4.getLastFoundTime() <= 5000L)
                            {
                                i0 = i0 + 1;
                            }
                            else
                            {
                                this.stbInfoList.remove(i0);
                                android.util.Log.d(this.TAG, new StringBuilder("remove ").append(a4.getStb_ip_address_disp()).append(" because stb no response").toString());
                                b0 = true;
                            }
                        }
                        if (b0)
                        {
                            this.update_stb_info_to_login_list();
                        }
                        this.timeMark = android.os.SystemClock.uptimeMillis();
                    }
                }
            }
            android.util.Log.d(this.TAG, "run interrupt1");
            return;
        }
        catch(java.net.SocketException a5)
        {
            a5.printStackTrace();
            return;
        }
    }


}
