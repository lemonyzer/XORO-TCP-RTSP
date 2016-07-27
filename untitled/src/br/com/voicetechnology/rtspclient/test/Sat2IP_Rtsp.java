package br.com.voicetechnology.rtspclient.test;

import android.util.Log;
import br.com.voicetechnology.rtspclient.concepts.Client;
import br.com.voicetechnology.rtspclient.concepts.Request;
import br.com.voicetechnology.rtspclient.concepts.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class Sat2IP_Rtsp implements br.com.voicetechnology.rtspclient.concepts.ClientListener {
    private static int[] mSWITCH_TABLE;
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
        if (mSWITCH_TABLE != null)
        {
            return mSWITCH_TABLE;
        }

        int[] mSWITCH_TABLE = new int[Request.Method.values().length];
        mSWITCH_TABLE[Request.Method.OPTIONS.ordinal()] = 1;
        mSWITCH_TABLE[Request.Method.DESCRIBE.ordinal()] = 2;
        mSWITCH_TABLE[Request.Method.SETUP.ordinal()] = 3;
        mSWITCH_TABLE[Request.Method.PLAY.ordinal()] = 4;
        mSWITCH_TABLE[Request.Method.RECORD.ordinal()] = 5;
        mSWITCH_TABLE[Request.Method.TEARDOWN.ordinal()] = 6;

//        Request.Method a1 = Request.Method.DESCRIBE;
//        try
//        {
//            a0[a1.ordinal()] = 2;
//        }
//        catch(NoSuchFieldError ignoredException)
//        {
//        }
//        try
//        {
//            a0[Request.Method.OPTIONS.ordinal()] = 1;
//        }
//        catch(NoSuchFieldError ignoredException0)
//        {
//        }
//        try
//        {
//            a0[Request.Method.PLAY.ordinal()] = 4;
//        }
//        catch(NoSuchFieldError ignoredException1)
//        {
//        }
//        try
//        {
//            a0[Request.Method.RECORD.ordinal()] = 5;
//        }
//        catch(NoSuchFieldError ignoredException2)
//        {
//        }
//        try
//        {
//            a0[Request.Method.SETUP.ordinal()] = 3;
//        }
//        catch(NoSuchFieldError ignoredException3)
//        {
//        }
//        try
//        {
//            a0[Request.Method.TEARDOWN.ordinal()] = 6;
//        }
//        catch(NoSuchFieldError ignoredException4)
//        {
//        }
//        SWITCH_TABLE = a0;
        return mSWITCH_TABLE;
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
        Log.e("RTSPClient","handleRequestFailed");
        a.teardown();
        this._isSucc = false;
        synchronized (this) {
            this.notify();
        }

//        a.teardown();
//        this._isSucc = false;
//        //monenter(this);
//        try
//        {
//            ((Object)this).notify();
//            //monexit(this);
//        }
//        catch(Throwable a0)
//        {
//            Throwable a1 = a0;
//            while(true)
//            {
//                try
//                {
//                    //monexit(this);
//                }
//                catch(IllegalMonitorStateException | NullPointerException a2)
//                {
//                    a1 = a2;
//                    continue;
//                }
//                throw a1;
//            }
//        }
    }

    private void handleRequestFailed_NoTearDown(br.com.voicetechnology.rtspclient.concepts.Client a) throws Throwable {
        Log.e("RTSPClient","handleRequestFailed_NoTearDown");
        this._isSucc = false;
        synchronized (this) {
            this.notify();
        }

//        this._isSucc = false;
//        //monenter(this);
//        try
//        {
//            ((Object)this).notify();
//            //monexit(this);
//        }
//        catch(Throwable a0)
//        {
//            Throwable a1 = a0;
//            while(true)
//            {
//                try
//                {
//                    //monexit(this);
//                }
//                catch(IllegalMonitorStateException | NullPointerException a2)
//                {
//                    a1 = a2;
//                    continue;
//                }
//                throw a1;
//            }
//        }
    }

    private void handleSessionNotFound()
    {
        Log.e("RTSPClient","handleSessionNotFound");

        if (this.eofListener != null)
        {
            this.eofListener.onEndOfFile();
        }
    }

    public static void main(String[] a)
    {

        /*
        0/6 : SWITCH_TABLE[0] = 1
        1/6 : SWITCH_TABLE[1] = 2
        2/6 : SWITCH_TABLE[2] = 3
        3/6 : SWITCH_TABLE[3] = 4
        4/6 : SWITCH_TABLE[4] = 5
        5/6 : SWITCH_TABLE[5] = 6
         */
        if(SWITCH_TABLE() != null) {
            for (int i = 0; i < SWITCH_TABLE().length; i++) {
                System.out.println(i + "/" + SWITCH_TABLE().length + " : SWITCH_TABLE["+i+"] = " + SWITCH_TABLE()[i]);
            }
        }
        System.out.println("--");

        /*
        0/6 = OPTIONS ordinal = 0
        1/6 = DESCRIBE ordinal = 1
        2/6 = SETUP ordinal = 2
        3/6 = PLAY ordinal = 3
        4/6 = RECORD ordinal = 4
        5/6 = TEARDOWN ordinal = 5
         */

        for (int i=0; i< Request.Method.values().length; i++) {
            System.out.println(i+"/"+Request.Method.values().length + " : " + (Request.Method.values()[i]) + " ordinal = " + (Request.Method.values()[i]).ordinal() );
        }
//        br.com.voicetechnology.rtspclient.test.Sat2IP_Rtsp a0 = new br.com.voicetechnology.rtspclient.test.Sat2IP_Rtsp();
//        a0.setup("rtsp://192.168.0.101:554/", "?src=1&fe=1&freq=3840&pol=h&msys=dvbs2&mtype=8psk&ro=0.35&plts=on&sr=27500&fec=2&pids=0,16,17,20,257,512,8190,8191,8191,650");
//        System.out.println(new StringBuilder("get rtp port: ").append(a0.get_rtp_port()).toString());
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
        if (a0.getMethod() != Request.Method.TEARDOWN)
        {
            try {
                this.handleRequestFailed(a);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public void response(Client client, Request request, Response response)
    {
        label5: {
            label4: {
                label13: {
                    label0: {
                        label6: {
                            label12: {
                                label8: {
                                    label10: try
                                    {
                                        if (request.getMethod() == Request.Method.OPTIONS)
                                        {
                                            java.io.PrintStream a3 = System.out;
                                            a3.println("Got response for OPTIONS\n");
                                        }
                                        else
                                        {
                                            java.io.PrintStream a4 = System.out;
                                            a4.println(new StringBuilder("Got response: ").append((Object)response).toString());
                                            System.out.println(new StringBuilder("for the request: \n").append((Object)request).toString());
                                        }
//                                        int i = response.getStatusCode();
                                        label11: {
                                            if (response.getStatusCode() == 200)
                                            {
                                                break label11;
                                            }
//                                            int i0 = response.getStatusCode();
                                            label9: {
                                                if (response.getStatusCode() == 454)
                                                {
                                                    break label9;
                                                }
//                                                a2 = client;
                                                this.handleRequestFailed(client);
                                                break label10;
                                            }
//                                            Request.Method a5 = request.getMethod();
//                                            Request.Method a6 = Request.Method.OPTIONS;
                                            label7: {
                                                if (request.getMethod() == Request.Method.OPTIONS)
                                                {
                                                    break label7;
                                                }
                                                this.handleRequestFailed_NoTearDown(client);
                                                break label8;
                                            }
                                            this.handleSessionNotFound();
                                            break label12;
                                        }
                                        int i1 = Sat2IP_Rtsp.SWITCH_TABLE()[request.getMethod().ordinal()];
                                        label2: {
                                            label3: {
                                                label1: {
                                                    switch(i1){
                                                        case 4: {
                                                            // request.getMethod() == 3 == PLAY
                                                            this._isSucc = true;
                                                            //monenter(this);
                                                            break label3;
                                                        }
                                                        case 3: {
                                                            // request.getMethod() == 2 == SETUP
                                                            if (mktvsmart.screen.GMScreenGlobalInfo.playType != 2)
                                                            {
                                                                break label2;
                                                            }
                                                            break;
                                                        }
                                                        case 1: {
                                                            // request.getMethod() == 0 == OPTIONS
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

                                                    // request SETUP
                                                    this.streamId = Integer.parseInt(response.getHeader("com.ses.streamID").getRawValue());
                                                    client.play(new StringBuilder(String.valueOf((Object)this.mBaseUrl)).append("stream=").append(this.streamId).toString());
                                                    break label4;
                                                    // ende label 1
                                                }
                                                // request OPTIONS
                                                Thread.sleep(10000L);
                                                client.options("*", new java.net.URI(this.mBaseUrl));
                                                break label5;

                                                // ende label 5
                                            }
                                            // request PLAY
                                            synchronized (this) {
                                                try {
                                                    this.notify();
                                                } catch (IllegalMonitorStateException a9) {
                                                    System.out.println(this.toString() + ": loop @ IllegalMonitorStateException ");
                                                } catch (NullPointerException a9) {
                                                    System.out.println(this.toString() + ": loop @ NullPointerException ");
                                                }
                                            }
//                                            try
//                                            {
//                                                ((Object)this).notify();
//                                                //monexit(this);
//                                            }
//                                            catch(Throwable a7)
//                                            {
//                                                Throwable a8 = a7;
////                                                a2 = client;
//                                                while(true)
//                                                {
//                                                    System.out.println(this.toString() + ": loop @ IllegalMonitorStateException ");
//                                                    try
//                                                    {
//                                                        //monexit(this);
//                                                    }
//                                                    catch(IllegalMonitorStateException | NullPointerException a9)
//                                                    {
////                                                        Object a10 = a2;
//                                                        a8 = a9;
////                                                        a2 = a10;
//                                                        continue;
//                                                    }
//                                                    throw a8;
//                                                }
//                                            }
                                            Thread.sleep(10000L);
                                            client.options("*", new java.net.URI(this.mBaseUrl));
                                            break label6;
                                        }
                                        if (mktvsmart.screen.GMScreenGlobalInfo.playType != 1)
                                        {
                                            break label0;
                                        }
//                                        a2 = client;
                                        client.play(this.mBaseUrl);
                                        break label13;
                                    }
                                    catch(Throwable a11)
                                    {
                                        this.generalError(client, a11);
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

    public void setup_not_blocked(String rtspUriBase, String rtspUriQuery)
    {
        this._isSucc = false;
        this.setup(rtspUriBase, rtspUriQuery);
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
                long begin = System.currentTimeMillis();
                ((Object)this).wait(5000L);
                long end = System.currentTimeMillis();
                long duration = end-begin;
                Log.d("setup_blocked:"," wait in Seconds= " + TimeUnit.MILLISECONDS.toSeconds(duration));

//                ((Object)this).wait(5000L);
            }
            catch(Exception ignoredException)
            {
                ignoredException.printStackTrace();
            }
        }
        catch(Throwable ignoredException0)
        {
            ignoredException0.printStackTrace();
        }
        try
        {
            b = this._isSucc;
        }
        catch(NullPointerException a)
        {
            //monexit(this);
            a.printStackTrace();
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
