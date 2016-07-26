package br.com.voicetechnology.rtspclient.test.Luyten;

import br.com.voicetechnology.rtspclient.*;
import br.com.voicetechnology.rtspclient.transport.*;
import br.com.voicetechnology.rtspclient.concepts.*;
import mktvsmart.screen.*;
import java.net.*;
import android.util.*;

public class Sat2IP_Rtsp implements ClientListener
{
    private static /* synthetic */ int[] $SWITCH_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method;
    private static boolean isTearDown;
    private boolean _isSucc;
    RTSPClient client;
    private EndOfFileListener eofListener;
    String mBaseUrl;
    String mQueryStr;
    private int port;
    private int rtp_port;
    private int streamId;

    static /* synthetic */ int[] $SWITCH_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method() {
        final int[] $switch_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method = Sat2IP_Rtsp.$SWITCH_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method;
        if ($switch_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method != null) {
            return $switch_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method;
        }
        final int[] $switch_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method2 = new int[Request.Method.values().length];
        while (true) {
            try {
                $switch_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method2[Request.Method.DESCRIBE.ordinal()] = 2;
                try {
                    $switch_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method2[Request.Method.OPTIONS.ordinal()] = 1;
                    try {
                        $switch_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method2[Request.Method.PLAY.ordinal()] = 4;
                        try {
                            $switch_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method2[Request.Method.RECORD.ordinal()] = 5;
                            try {
                                $switch_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method2[Request.Method.SETUP.ordinal()] = 3;
                                try {
                                    $switch_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method2[Request.Method.TEARDOWN.ordinal()] = 6;
                                    return Sat2IP_Rtsp.$SWITCH_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method = $switch_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method2;
                                }
                                catch (NoSuchFieldError noSuchFieldError) {}
                            }
                            catch (NoSuchFieldError noSuchFieldError2) {}
                        }
                        catch (NoSuchFieldError noSuchFieldError3) {}
                    }
                    catch (NoSuchFieldError noSuchFieldError4) {}
                }
                catch (NoSuchFieldError noSuchFieldError5) {}
            }
            catch (NoSuchFieldError noSuchFieldError6) {
                continue;
            }
            break;
        }
    }

    public Sat2IP_Rtsp() {
        super();
        try {
            (this.client = new RTSPClient()).setTransport(new PlainTCP());
            this.client.setClientListener(this);
            this.port = 10022;
            this.rtp_port = 0;
            Sat2IP_Rtsp.isTearDown = true;
            this.eofListener = null;
        }
        catch (Exception ex) {}
    }

    private void handleRequestFailed(final Client client) {
        client.teardown();
        this._isSucc = false;
        synchronized (this) {
            this.notify();
        }
    }

    private void handleRequestFailed_NoTearDown(final Client client) {
        this._isSucc = false;
        synchronized (this) {
            this.notify();
        }
    }

    private void handleSessionNotFound() {
        if (this.eofListener != null) {
            this.eofListener.onEndOfFile();
        }
    }

    public static void main(final String[] array) throws Throwable {
        final Sat2IP_Rtsp sat2IP_Rtsp = new Sat2IP_Rtsp();
        sat2IP_Rtsp.setup("rtsp://192.168.0.101:554/", "?src=1&fe=1&freq=3840&pol=h&msys=dvbs2&mtype=8psk&ro=0.35&plts=on&sr=27500&fec=2&pids=0,16,17,20,257,512,8190,8191,8191,650");
        System.out.println("get rtp port: " + sat2IP_Rtsp.get_rtp_port());
    }

    private int nextPort() {
        return (this.port += 2) - 2;
    }

    @Override
    public void generalError(final Client client, final Throwable t) {
        t.printStackTrace();
    }

    public int get_rtp_port() {
        return this.rtp_port;
    }

    @Override
    public void mediaDescriptor(final Client client, final String s) {
    }

    @Override
    public void requestFailed(final Client client, final Request request, final Throwable t) {
        System.out.println("Request failed \n" + request);
        t.printStackTrace();
        if (request.getMethod() != Request.Method.TEARDOWN) {
            this.handleRequestFailed(client);
        }
    }

    @Override
    public void response(final Client client, final Request request, final Response response) {
        Label_0286: {
            Label_0254: {
                Label_0210: {
                    try {
                        if (request.getMethod() != Request.Method.OPTIONS) {
                            System.out.println("Got response: " + response);
                            System.out.println("for the request: \n" + request);
                        }
                        else {
                            System.out.println("Got response for OPTIONS\n");
                        }
                        if (response.getStatusCode() != 200) {
                            break Label_0286;
                        }
                        switch ($SWITCH_TABLE$com$voicetechnology$rtspclient$concepts$Request$Method()[request.getMethod().ordinal()]) {
                            case 3: {
                                break;
                            }
                            case 4: {
                                break Label_0210;
                            }
                            case 1: {
                                break Label_0254;
                            }
                            default: {
                                return;
                            }
                        }
                    }
                    catch (Throwable t) {
                        this.generalError(client, t);
                        return;
                    }
                    if (GMScreenGlobalInfo.playType == 2) {
                        this.streamId = Integer.parseInt(response.getHeader("com.ses.streamID").getRawValue());
                        client.play(String.valueOf(this.mBaseUrl) + "stream=" + this.streamId);
                        return;
                    }
                    if (GMScreenGlobalInfo.playType == 1) {
                        client.play(this.mBaseUrl);
                        return;
                    }
                    return;
                }
                this._isSucc = true;
                synchronized (this) {
                    this.notify();
                    // monitorexit(this)
                    Thread.sleep(10000L);
                    client.options("*", new URI(this.mBaseUrl));
                    return;
                }
            }
            if (!Sat2IP_Rtsp.isTearDown) {
                Thread.sleep(10000L);
                client.options("*", new URI(this.mBaseUrl));
                return;
            }
            return;
        }
        if (response.getStatusCode() != 454) {
            this.handleRequestFailed(client);
            return;
        }
        if (request.getMethod() == Request.Method.OPTIONS) {
            this.handleSessionNotFound();
            return;
        }
        this.handleRequestFailed_NoTearDown(client);
    }

    public void set_eof_listener(final EndOfFileListener eofListener) {
        this.eofListener = eofListener;
    }

    public void setup(final String mBaseUrl, final String mQueryStr) throws Exception {
        this.rtp_port = this.port;
        this.mBaseUrl = mBaseUrl;
        this.mQueryStr = mQueryStr;
        Log.v("sat2ip", String.valueOf(mBaseUrl) + " query: " + mQueryStr);
        this.client.setup(new URI(String.valueOf(this.mBaseUrl) + this.mQueryStr), this.nextPort());
        Sat2IP_Rtsp.isTearDown = false;
    }

    public boolean setup_blocked(final String s, final String s2) {
        // monitorenter(this)
        while (true) {
            try {
                this._isSucc = false;
                this.setup(s, s2);
                this.wait(5000L);
                try {
                    return this._isSucc;
                }
                finally {
                }
                // monitorexit(this)
            }
            catch (Exception ex) {}
            finally {
                continue;
            }
            break;
        }
    }

    public void teardown() {
        Sat2IP_Rtsp.isTearDown = true;
        this.client.teardown();
    }

    public interface EndOfFileListener
    {
        void onEndOfFile();
    }
}
