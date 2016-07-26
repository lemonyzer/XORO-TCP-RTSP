package br.com.voicetechnology.rtspclient.test;

import br.com.voicetechnology.rtspclient.concepts.Request;

import java.io.IOException;
import java.net.URISyntaxException;

public class Sat2IP_Rtsp implements br.com.voicetechnology.rtspclient.concepts.ClientListener {
    private static int[] SWITCH_TABLE;
    private static boolean isTearDown;
    private boolean _isSucc;
    br.com.voicetechnology.rtspclient.RTSPClient mClient;
    private br.com.voicetechnology.rtspclient.test.Sat2IP_Rtsp.EndOfFileListener eofListener;
    String mBaseUrl;
    String mQueryStr;
    private int port;
    private int rtp_port;
    private int streamId;

    abstract public interface EndOfFileListener {
        abstract public void onEndOfFile();
    }


    static int[] SWITCH_TABLE()
    {
        int[] a = SWITCH_TABLE;
        if (a != null)
        {
            return a;
        }
        int[] a0 = new int[br.com.voicetechnology.rtspclient.concepts.Request.Method.values().length];
        br.com.voicetechnology.rtspclient.concepts.Request.Method a1 = br.com.voicetechnology.rtspclient.concepts.Request.Method.DESCRIBE;
        try
        {
            a0[a1.ordinal()] = 2;
        }
        catch(NoSuchFieldError ignoredException)
        {
        }
        try
        {
            a0[br.com.voicetechnology.rtspclient.concepts.Request.Method.OPTIONS.ordinal()] = 1;
        }
        catch(NoSuchFieldError ignoredException0)
        {
        }
        try
        {
            a0[br.com.voicetechnology.rtspclient.concepts.Request.Method.PLAY.ordinal()] = 4;
        }
        catch(NoSuchFieldError ignoredException1)
        {
        }
        try
        {
            a0[br.com.voicetechnology.rtspclient.concepts.Request.Method.RECORD.ordinal()] = 5;
        }
        catch(NoSuchFieldError ignoredException2)
        {
        }
        try
        {
            a0[br.com.voicetechnology.rtspclient.concepts.Request.Method.SETUP.ordinal()] = 3;
        }
        catch(NoSuchFieldError ignoredException3)
        {
        }
        try
        {
            a0[br.com.voicetechnology.rtspclient.concepts.Request.Method.TEARDOWN.ordinal()] = 6;
        }
        catch(NoSuchFieldError ignoredException4)
        {
        }
        SWITCH_TABLE = a0;
        return a0;
    }

    public Sat2IP_Rtsp()
    {
        super();
        try
        {
            this.mClient = new br.com.voicetechnology.rtspclient.RTSPClient();
            this.mClient.setTransport((br.com.voicetechnology.rtspclient.concepts.Transport)(Object)new br.com.voicetechnology.rtspclient.transport.PlainTCP());
            this.mClient.setClientListener((br.com.voicetechnology.rtspclient.concepts.ClientListener)(Object)this);
            this.port = 10022;
            this.rtp_port = 0;
            isTearDown = true;
            this.eofListener = null;
        }
        catch(Exception ignoredException)
        {
            return;
        }
    }

    private void handleRequestFailed(br.com.voicetechnology.rtspclient.concepts.Client a) throws Throwable {
        a.teardown();
        this._isSucc = false;
        //monenter(this);
        try
        {
            ((Object)this).notify();
            //monexit(this);
        }
        catch(Throwable a0)
        {
            Throwable a1 = a0;
            while(true)
            {
                try
                {
                    //monexit(this);
                }
                catch(IllegalMonitorStateException | NullPointerException a2)
                {
                    a1 = a2;
                    continue;
                }
                throw a1;
            }
        }
    }

    private void handleRequestFailed_NoTearDown(br.com.voicetechnology.rtspclient.concepts.Client a) throws Throwable {
        this._isSucc = false;
        //monenter(this);
        try
        {
            ((Object)this).notify();
            //monexit(this);
        }
        catch(Throwable a0)
        {
            Throwable a1 = a0;
            while(true)
            {
                try
                {
                    //monexit(this);
                }
                catch(IllegalMonitorStateException | NullPointerException a2)
                {
                    a1 = a2;
                    continue;
                }
                throw a1;
            }
        }
    }

    private void handleSessionNotFound()
    {
        if (this.eofListener != null)
        {
            this.eofListener.onEndOfFile();
        }
    }

    public static void main(String[] a)
    {
        br.com.voicetechnology.rtspclient.test.Sat2IP_Rtsp a0 = new br.com.voicetechnology.rtspclient.test.Sat2IP_Rtsp();
        a0.setup("rtsp://192.168.0.101:554/", "?src=1&fe=1&freq=3840&pol=h&msys=dvbs2&mtype=8psk&ro=0.35&plts=on&sr=27500&fec=2&pids=0,16,17,20,257,512,8190,8191,8191,650");
        System.out.println(new StringBuilder("get rtp port: ").append(a0.get_rtp_port()).toString());
    }

    private int nextPort()
    {
        int i = this.port + 2;
        this.port = i;
        return i - 2;
    }

    public void generalError(br.com.voicetechnology.rtspclient.concepts.Client a, Throwable a0)
    {
        a0.printStackTrace();
    }

    public int get_rtp_port()
    {
        return this.rtp_port;
    }

    public void mediaDescriptor(br.com.voicetechnology.rtspclient.concepts.Client a, String s)
    {
    }

    public void requestFailed(br.com.voicetechnology.rtspclient.concepts.Client a, br.com.voicetechnology.rtspclient.concepts.Request a0, Throwable a1)
    {
        System.out.println(new StringBuilder("Request failed \n").append((Object)a0).toString());
        a1.printStackTrace();
        if (a0.getMethod() != br.com.voicetechnology.rtspclient.concepts.Request.Method.TEARDOWN)
        {
            try {
                this.handleRequestFailed(a);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public void response(br.com.voicetechnology.rtspclient.concepts.Client a, br.com.voicetechnology.rtspclient.concepts.Request a0, br.com.voicetechnology.rtspclient.concepts.Response a1)
    {
        label5: {
            label4: {
                label13: {
                    label0: {
                        label6: {
                            label12: {
                                label8: {
                                    Object a2 = null;
                                    label10: try
                                    {
                                        a2 = a;
                                        if (a0.getMethod() == br.com.voicetechnology.rtspclient.concepts.Request.Method.OPTIONS)
                                        {
                                            java.io.PrintStream a3 = System.out;
                                            a2 = a;
                                            a3.println("Got response for OPTIONS\n");
                                        }
                                        else
                                        {
                                            java.io.PrintStream a4 = System.out;
                                            a2 = a;
                                            a4.println(new StringBuilder("Got response: ").append((Object)a1).toString());
                                            System.out.println(new StringBuilder("for the request: \n").append((Object)a0).toString());
                                        }
                                        a2 = a;
                                        int i = a1.getStatusCode();
                                        label11: {
                                            if (i == 200)
                                            {
                                                break label11;
                                            }
                                            a2 = a;
                                            int i0 = a1.getStatusCode();
                                            label9: {
                                                if (i0 == 454)
                                                {
                                                    break label9;
                                                }
                                                a2 = a;
                                                this.handleRequestFailed(a);
                                                break label10;
                                            }
                                            a2 = a;
                                            br.com.voicetechnology.rtspclient.concepts.Request.Method a5 = a0.getMethod();
                                            br.com.voicetechnology.rtspclient.concepts.Request.Method a6 = br.com.voicetechnology.rtspclient.concepts.Request.Method.OPTIONS;
                                            label7: {
                                                if (a5 == a6)
                                                {
                                                    break label7;
                                                }
                                                a2 = a;
                                                this.handleRequestFailed_NoTearDown(a);
                                                break label8;
                                            }
                                            a2 = a;
                                            this.handleSessionNotFound();
                                            break label12;
                                        }
                                        a2 = a;
                                        int i1 = br.com.voicetechnology.rtspclient.test.Sat2IP_Rtsp.SWITCH_TABLE()[a0.getMethod().ordinal()];
                                        label2: {
                                            label3: {
                                                label1: {
                                                    switch(i1){
                                                        case 4: {
                                                            a2 = a;
                                                            this._isSucc = true;
                                                            //monenter(this);
                                                            break label3;
                                                        }
                                                        case 3: {
                                                            if (mktvsmart.screen.GMScreenGlobalInfo.playType != 2)
                                                            {
                                                                break label2;
                                                            }
                                                            break;
                                                        }
                                                        case 1: {
                                                            if (isTearDown)
                                                            {
                                                                break label0;
                                                            }
                                                            break label1;
                                                        }
                                                        default: {
                                                            break label0;
                                                        }
                                                    }
                                                    a2 = a;
                                                    this.streamId = Integer.parseInt(a1.getHeader("com.ses.streamID").getRawValue());
                                                    a.play(new StringBuilder(String.valueOf((Object)this.mBaseUrl)).append("stream=").append(this.streamId).toString());
                                                    break label4;
                                                }
                                                a2 = a;
                                                Thread.sleep(10000L);
                                                a.options("*", new java.net.URI(this.mBaseUrl));
                                                break label5;
                                            }
                                            try
                                            {
                                                ((Object)this).notify();
                                                //monexit(this);
                                            }
                                            catch(Throwable a7)
                                            {
                                                Throwable a8 = a7;
                                                a2 = a;
                                                while(true)
                                                {
                                                    try
                                                    {
                                                        //monexit(this);
                                                    }
                                                    catch(IllegalMonitorStateException | NullPointerException a9)
                                                    {
                                                        Object a10 = a2;
                                                        a8 = a9;
                                                        a2 = a10;
                                                        continue;
                                                    }
                                                    throw a8;
                                                }
                                            }
                                            a2 = a;
                                            Thread.sleep(10000L);
                                            a.options("*", new java.net.URI(this.mBaseUrl));
                                            break label6;
                                        }
                                        if (mktvsmart.screen.GMScreenGlobalInfo.playType != 1)
                                        {
                                            break label0;
                                        }
                                        a2 = a;
                                        a.play(this.mBaseUrl);
                                        break label13;
                                    }
                                    catch(Throwable a11)
                                    {
                                        this.generalError((br.com.voicetechnology.rtspclient.concepts.Client)a2, a11);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
    }

    public void set_eof_listener(br.com.voicetechnology.rtspclient.test.Sat2IP_Rtsp.EndOfFileListener a)
    {
        this.eofListener = a;
    }

    public void setup(String rtspUriBase, String rtspUriQuery)
    {
        this.rtp_port = this.port;
        this.mBaseUrl = rtspUriBase;
        this.mQueryStr = rtspUriQuery;
        android.util.Log.v("sat2ip", new StringBuilder(String.valueOf((Object)rtspUriBase)).append(" query: ").append(rtspUriQuery).toString());
        try {
            this.mClient.setup(new java.net.URI(new StringBuilder(String.valueOf((Object)this.mBaseUrl)).append(this.mQueryStr).toString()), this.nextPort());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        isTearDown = false;
    }

    public boolean setup_blocked(String rtspUriBase, String rtspUriQuery)
    {
        boolean b = false;
        //monenter(this);
        try
        {
            try
            {
                this._isSucc = false;
                this.setup(rtspUriBase, rtspUriQuery);
                ((Object)this).wait(5000L);
            }
            catch(Exception ignoredException)
            {
            }
        }
        catch(Throwable ignoredException0)
        {
        }
        try
        {
            b = this._isSucc;
        }
        catch(NullPointerException a)
        {
            //monexit(this);
            throw a;
        }
        //monexit(this);
        return b;
    }

    public void teardown()
    {
        isTearDown = true;
        this.mClient.teardown();
    }
}
