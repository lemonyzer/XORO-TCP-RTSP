package mktvsmart.screen.pvr2small;

import com.voicetechnology.rtspclient.test.*;
import mktvsmart.screen.message.process.*;
import java.net.*;
import mktvsmart.screen.vlc.*;
import android.net.*;
import android.content.*;
import mktvsmart.screen.util.*;
import android.app.*;
import android.view.*;
import mktvsmart.screen.dataconvert.model.*;
import com.alitech.dvbtoip.*;
import android.os.*;
import mktvsmart.screen.*;
import android.widget.*;

public class GsPvr2SmallActivity extends Activity implements EndOfFileListener
{
    private static Sat2IP_Rtsp sRtsp;
    private final int PLAY_TIMEOUT;
    private CommonCofirmDialog.OnButtonClickListener mDownDialogOnClickListener;
    private Handler$Callback mMsgHandle;
    private FindPlayerAndPlayChannel.PlayByDesignatedPlayer mPlayByDesignatedPlayer;
    private Pvr2smallData mPvr2SmallData;
    private ListView mPvr2SmallMenu;
    private pvr_list_adapter mPvrListAdapter;
    private Handler mainHandler;
    private MessageProcessor msgProc;
    private MessageProcessor.PerformOnBackground post;
    private Socket tcpSocket;
    Runnable timeOutRun;
    private ADSProgressDialog waitDialog;

    public GsPvr2SmallActivity() {
        super();
        this.mPvrListAdapter = null;
        this.PLAY_TIMEOUT = 2;
        this.mMsgHandle = (Handler$Callback)new Handler$Callback() {
            final /* synthetic */ GsPvr2SmallActivity this$0;

            GsPvr2SmallActivity$1() {
                this.this$0 = this$0;
                super();
            }

            public boolean handleMessage(final Message message) {
                switch (message.what) {
                    case 2: {
                        GsPvr2SmallActivity.this.stopStream();
                        GsPvr2SmallActivity.this.promptDialog(2131427657, 2131427637);
                        break;
                    }
                }
                return true;
            }
        };
        this.post = new MessageProcessor.PerformOnBackground() {
            final /* synthetic */ GsPvr2SmallActivity this$0;

            GsPvr2SmallActivity$2() {
                this.this$0 = this$0;
                super();
            }

            @Override
            public void doInBackground(final Message message) {
                int n = 9999;
                switch (message.what) {
                    case 2023: {
                        this.this$0.mPvr2SmallData.clearPvr2smallList();
                    }
                    case 2020:
                    case 2021:
                    case 2022: {
                        n = 27;
                        break;
                    }
                }
                GsSendSocket.sendOnlyCommandSocketToStb(this.this$0.tcpSocket, n);
            }
        };
        this.timeOutRun = new Runnable() {
            final /* synthetic */ GsPvr2SmallActivity this$0;

            GsPvr2SmallActivity$3() {
                this.this$0 = this$0;
                super();
            }

            @Override
            public void run() {
                this.this$0.mainHandler.sendEmptyMessage(2);
            }
        };
        this.mPlayByDesignatedPlayer = new FindPlayerAndPlayChannel.PlayByDesignatedPlayer() {
            final /* synthetic */ GsPvr2SmallActivity this$0;

            GsPvr2SmallActivity$4() {
                this.this$0 = this$0;
                super();
            }

            @Override
            public void designatedBuiltInPlayer(final int n) {
                GMScreenGlobalInfo.playType = 1;
                final Intent intent = new Intent("android.intent.action.VIEW");
                intent.setClass((Context)this.this$0, (Class)LivePlayActivity.class);
                intent.putExtra("position", n);
                intent.setFlags(268435456);
                this.this$0.getApplication().startActivity(intent);
            }

            @Override
            public void designatedExternalPlayer(final int n, final Intent intent) {
                GsPvr2SmallActivity.this.startPlayStream(n, intent);
            }

            @Override
            public void playerNotExist() {
                final CommonCofirmDialog commonCofirmDialog = new CommonCofirmDialog((Context)this.this$0);
                commonCofirmDialog.setmTitle(this.this$0.getResources().getString(2131427589));
                commonCofirmDialog.setmContent(this.this$0.getResources().getString(2131427590));
                commonCofirmDialog.setOnButtonClickListener(this.this$0.mDownDialogOnClickListener);
                commonCofirmDialog.show();
            }
        };
        this.mDownDialogOnClickListener = new CommonCofirmDialog.OnButtonClickListener() {
            final /* synthetic */ GsPvr2SmallActivity this$0;

            GsPvr2SmallActivity$5() {
                this.this$0 = this$0;
                super();
            }

            @Override
            public void onClickedCancel() {
            }

            @Override
            public void onClickedConfirm() {
                final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.mxtech.videoplayer.ad"));
                try {
                    this.this$0.startActivity(intent);
                }
                catch (ActivityNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    static /* synthetic */ void access$0(final GsPvr2SmallActivity gsPvr2SmallActivity) {
        gsPvr2SmallActivity.stopStream();
    }

    static /* synthetic */ void access$1(final GsPvr2SmallActivity gsPvr2SmallActivity, final int n, final int n2) {
        gsPvr2SmallActivity.promptDialog(n, n2);
    }

    static /* synthetic */ pvr_list_adapter access$10(final GsPvr2SmallActivity gsPvr2SmallActivity) {
        return gsPvr2SmallActivity.mPvrListAdapter;
    }

    static /* synthetic */ void access$11(final GsPvr2SmallActivity gsPvr2SmallActivity, final int n, final int n2) {
        gsPvr2SmallActivity.displayDialog(n, n2);
    }

    static /* synthetic */ void access$12(final GsPvr2SmallActivity gsPvr2SmallActivity, final int n) {
        gsPvr2SmallActivity.play(n);
    }

    static /* synthetic */ void access$13(final Sat2IP_Rtsp sRtsp) {
        GsPvr2SmallActivity.sRtsp = sRtsp;
    }

    static /* synthetic */ Sat2IP_Rtsp access$14() {
        return GsPvr2SmallActivity.sRtsp;
    }

    static /* synthetic */ Pvr2smallData access$2(final GsPvr2SmallActivity gsPvr2SmallActivity) {
        return gsPvr2SmallActivity.mPvr2SmallData;
    }

    static /* synthetic */ Socket access$3(final GsPvr2SmallActivity gsPvr2SmallActivity) {
        return gsPvr2SmallActivity.tcpSocket;
    }

    static /* synthetic */ Handler access$4(final GsPvr2SmallActivity gsPvr2SmallActivity) {
        return gsPvr2SmallActivity.mainHandler;
    }

    static /* synthetic */ void access$5(final GsPvr2SmallActivity gsPvr2SmallActivity, final int n, final Intent intent) {
        gsPvr2SmallActivity.startPlayStream(n, intent);
    }

    static /* synthetic */ CommonCofirmDialog.OnButtonClickListener access$6(final GsPvr2SmallActivity gsPvr2SmallActivity) {
        return gsPvr2SmallActivity.mDownDialogOnClickListener;
    }

    static /* synthetic */ ADSProgressDialog access$7(final GsPvr2SmallActivity gsPvr2SmallActivity) {
        return gsPvr2SmallActivity.waitDialog;
    }

    static /* synthetic */ void access$8(final GsPvr2SmallActivity gsPvr2SmallActivity, final pvr_list_adapter mPvrListAdapter) {
        gsPvr2SmallActivity.mPvrListAdapter = mPvrListAdapter;
    }

    static /* synthetic */ ListView access$9(final GsPvr2SmallActivity gsPvr2SmallActivity) {
        return gsPvr2SmallActivity.mPvr2SmallMenu;
    }

    private void displayDialog(final int n, final int n2) {
        if (this.waitDialog != null && this.waitDialog.isShowing()) {
            this.waitDialog.dismiss();
        }
        this.waitDialog = DialogBuilder.showProgressDialog(this, n, n2, false, GMScreenGlobalInfo.getmWaitDialogTimeOut(), this.timeOutRun);
    }

    private void play(final int n) {
        final FindPlayerAndPlayChannel findPlayerAndPlayChannel = new FindPlayerAndPlayChannel((Context)this);
        findPlayerAndPlayChannel.implementPlayByDesignatedPlayer(this.mPlayByDesignatedPlayer);
        findPlayerAndPlayChannel.selectPlayer(n);
    }

    private void promptDialog(final int text, final int text2) {
        final Dialog dialog = new Dialog((Context)this, 2131493009);
        final View inflate = LayoutInflater.from((Context)this).inflate(2130903146, (ViewGroup)null);
        final TextView textView = (TextView)inflate.findViewById(2131362290);
        final TextView textView2 = (TextView)inflate.findViewById(2131362291);
        final Button button = (Button)inflate.findViewById(2131362292);
        textView.setText(text);
        textView2.setText(text2);
        button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            final /* synthetic */ GsPvr2SmallActivity this$0;
            private final /* synthetic */ Dialog val$promptDialog;

            GsPvr2SmallActivity$12() {
                this.this$0 = this$0;
                super();
            }

            public void onClick(final View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(inflate);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void setMessageProcess() {
        (this.msgProc = MessageProcessor.obtain()).setOnMessageProcess(27, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            final /* synthetic */ GsPvr2SmallActivity this$0;

            GsPvr2SmallActivity$6() {
                this.this$0 = this$0;
                super();
            }

            @Override
            public void doInForeground(final Message message) {
                if (this.this$0.waitDialog.isShowing()) {
                    this.this$0.waitDialog.dismiss();
                }
                if (message.arg1 > 0) {
                    this.this$0.mPvr2SmallData.initPvr2SmallList(message.getData().getByteArray("ReceivedData"));
                    GsPvr2SmallActivity.access$8(this.this$0, this.this$0.new pvr_list_adapter((Context)this.this$0));
                    this.this$0.mPvr2SmallMenu.setAdapter((ListAdapter)this.this$0.mPvrListAdapter);
                    return;
                }
                if (this.this$0.mPvrListAdapter != null) {
                    this.this$0.mPvrListAdapter.notifyDataSetChanged();
                }
                if (message.arg2 == 18) {
                    GsPvr2SmallActivity.this.promptDialog(2131427654, 2131427658);
                    return;
                }
                if (message.arg2 == 19) {
                    GsPvr2SmallActivity.this.promptDialog(2131427655, 2131427659);
                    return;
                }
                if (message.arg2 == 20) {
                    GsPvr2SmallActivity.this.promptDialog(2131427656, 2131427660);
                    return;
                }
                GsPvr2SmallActivity.this.promptDialog(2131427657, 2131427661);
            }
        });
        this.msgProc.setOnMessageProcess(4112, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            final /* synthetic */ GsPvr2SmallActivity this$0;

            GsPvr2SmallActivity$7() {
                this.this$0 = this$0;
                super();
            }

            @Override
            public void doInForeground(final Message message) {
                Toast.makeText((Context)this.this$0, 2131427619, 0).show();
                final Intent intent = new Intent();
                intent.setClass((Context)this.this$0, (Class)GsLoginListActivity.class);
                this.this$0.startActivity(intent);
                this.this$0.finish();
            }
        });
        this.msgProc.setOnMessageProcess(2020, this.post);
        this.msgProc.setOnMessageProcess(2021, this.post);
        this.msgProc.setOnMessageProcess(2022, this.post);
        this.msgProc.setOnMessageProcess(2023, this.post);
    }

    private void startPlayStream(final int n, final Intent intent) {
        this.displayDialog(2131427641, 2131427520);
        new Thread(new Runnable() {
            final /* synthetic */ GsPvr2SmallActivity this$0;
            private final /* synthetic */ int val$location;
            private final /* synthetic */ Intent val$playIntent;

            GsPvr2SmallActivity$11() {
                this.this$0 = this$0;
                super();
            }

            static /* synthetic */ GsPvr2SmallActivity access$0(final GsPvr2SmallActivity$11 runnable) {
                return runnable.this$0;
            }

            @Override
            public void run() {
                GsPvr2SmallActivity.access$13(new Sat2IP_Rtsp());
                GsPvr2SmallActivity.sRtsp.set_eof_listener((Sat2IP_Rtsp.EndOfFileListener)this.this$0);
                if (this.this$0.mPvr2SmallData.getPvr2smallList().get(n).getmPvrCrypto() == 1) {
                    DVBtoIP.getChannelUserKey(this.this$0.tcpSocket.getInetAddress().toString().substring(1));
                }
                final String playUrlBase = Pvr2smallData.getInstance().getPlayUrlBase(n, this.this$0.tcpSocket.getInetAddress().toString());
                final String playUrlQuery = Pvr2smallData.getInstance().getPlayUrlQuery();
                GMScreenGlobalInfo.playType = 1;
                if (!GsPvr2SmallActivity.sRtsp.setup_blocked(playUrlBase, playUrlQuery)) {
                    if (this.this$0.waitDialog.isShowing()) {
                        this.this$0.waitDialog.dismiss();
                    }
                    final CommonErrorDialog commonErrorDialog = new CommonErrorDialog((Context)this.this$0.getParent());
                    commonErrorDialog.setmContent(this.this$0.getResources().getString(2131427592));
                    commonErrorDialog.show();
                    GsPvr2SmallActivity.access$13(null);
                    return;
                }
                DVBtoIP.initResourceForPlayer(GsPvr2SmallActivity.sRtsp.get_rtp_port(), FindPlayerAndPlayChannel.getRtspPipeFilePath((Context)this.this$0), 1);
                this.this$0.runOnUiThread((Runnable)new Runnable() {
                    final /* synthetic */ GsPvr2SmallActivity$11 this$1;
                    private final /* synthetic */ Intent val$playIntent;

                    GsPvr2SmallActivity$11$1() {
                        this.this$1 = this$1;
                        super();
                    }

                    @Override
                    public void run() {
                        if (this.this$1.this$0.waitDialog.isShowing()) {
                            this.this$1.this$0.waitDialog.dismiss();
                        }
                        while (true) {
                            try {
                                this.this$1.this$0.startActivity(intent);
                                Toast.makeText((Context)this.this$1.this$0, 2131427593, 1).show();
                            }
                            catch (ActivityNotFoundException ex) {
                                System.out.println("MX Player activity not found");
                                GsPvr2SmallActivity.this.stopStream();
                                continue;
                            }
                            break;
                        }
                    }
                });
            }
        }).start();
    }

    private void stopStream() {
        if (GsPvr2SmallActivity.sRtsp != null) {
            GsPvr2SmallActivity.sRtsp.teardown();
            GsPvr2SmallActivity.sRtsp = null;
            DVBtoIP.destroyResourceForPlayer();
            GMScreenGlobalInfo.playType = 0;
        }
    }

    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Intent intent = this.getIntent();
        final String stringExtra = intent.getStringExtra("Address");
        final int intExtra = intent.getIntExtra("Port", 20000);
        while (true) {
            try {
                this.tcpSocket = new CreateSocket(stringExtra, intExtra).GetSocket();
                this.setContentView(2130903148);
                this.mPvr2SmallMenu = (ListView)this.findViewById(2131362303);
                final Button button = (Button)this.findViewById(2131362301);
                final Button button2 = (Button)this.findViewById(2131362302);
                this.mPvr2SmallData = Pvr2smallData.getInstance();
                this.setMessageProcess();
                button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    final /* synthetic */ GsPvr2SmallActivity this$0;

                    GsPvr2SmallActivity$8() {
                        this.this$0 = this$0;
                        super();
                    }

                    public void onClick(final View view) {
                        this.this$0.onBackPressed();
                    }
                });
                button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    final /* synthetic */ GsPvr2SmallActivity this$0;

                    GsPvr2SmallActivity$9() {
                        this.this$0 = this$0;
                        super();
                    }

                    public void onClick(final View view) {
                        GsSendSocket.sendOnlyCommandSocketToStb(this.this$0.tcpSocket, 27);
                        GsPvr2SmallActivity.this.displayDialog(2131427514, 2131427520);
                    }
                });
                this.mPvr2SmallMenu.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                    final /* synthetic */ GsPvr2SmallActivity this$0;

                    GsPvr2SmallActivity$10() {
                        this.this$0 = this$0;
                        super();
                    }

                    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                        GsPvr2SmallActivity.this.play(n);
                    }
                });
                this.mainHandler = new Handler(this.mMsgHandle);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.stopStream();
    }

    public void onEndOfFile() {
        this.stopStream();
    }

    protected void onResume() {
        super.onResume();
        this.stopStream();
        GsSendSocket.sendOnlyCommandSocketToStb(this.tcpSocket, 27);
        this.displayDialog(2131427514, 2131427520);
    }

    private class pvr_list_adapter extends BaseAdapter
    {
        LayoutInflater inflater;
        final /* synthetic */ GsPvr2SmallActivity this$0;

        public pvr_list_adapter(final GsPvr2SmallActivity this$0, final Context context) {
            this.this$0 = this$0;
            super();
            this.inflater = LayoutInflater.from(context);
        }

        public int getCount() {
            if (this.this$0.mPvr2SmallData.getPvr2smallList() != null) {
                return this.this$0.mPvr2SmallData.getPvr2smallList().size();
            }
            return 0;
        }

        public Object getItem(final int n) {
            return this.this$0.mPvr2SmallData.getPvr2smallList().get(n);
        }

        public long getItemId(final int n) {
            return n;
        }

        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            View inflate = view;
            if (view == null) {
                inflate = this.inflater.inflate(2130903147, viewGroup, false);
            }
            final TextView textView = (TextView)inflate.findViewById(2131362294);
            final TextView textView2 = (TextView)inflate.findViewById(2131362296);
            final TextView textView3 = (TextView)inflate.findViewById(2131362298);
            final TextView textView4 = (TextView)inflate.findViewById(2131362300);
            textView.setText((CharSequence)new StringBuilder(String.valueOf(n + 1)).toString());
            if (this.this$0.mPvr2SmallData.getPvr2smallList().get(n).getmPvrCrypto() == 1) {
                textView2.setText((CharSequence)("$" + this.this$0.mPvr2SmallData.getPvr2smallList().get(n).getProgramName()));
            }
            else {
                textView2.setText((CharSequence)this.this$0.mPvr2SmallData.getPvr2smallList().get(n).getProgramName());
            }
            final int int1 = Integer.parseInt(this.this$0.mPvr2SmallData.getPvr2smallList().get(n).getmPvrDuration());
            final String format = String.format("%02d:%02d:%02d", int1 / 3600, int1 % 3600 / 60, int1 % 60);
            textView3.setText((CharSequence)this.this$0.mPvr2SmallData.getPvr2smallList().get(n).getmPvrTime());
            textView4.setText((CharSequence)format);
            return inflate;
        }
    }
}
