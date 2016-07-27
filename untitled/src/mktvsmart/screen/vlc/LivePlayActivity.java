package mktvsmart.screen.vlc;

import com.voicetechnology.rtspclient.test.*;
import mktvsmart.screen.gchat.*;
import android.media.*;
import mktvsmart.screen.gchat.ui.*;
import mktvsmart.screen.message.process.*;
import mktvsmart.screen.widget.*;
import java.net.*;
import android.util.*;
import mktvsmart.screen.pvr2small.*;
import com.alitech.dvbtoip.*;
import android.net.*;
import android.widget.*;
import mktvsmart.screen.dataconvert.parser.*;
import android.os.*;
import android.preference.*;
import org.videolan.libvlc.*;
import android.content.*;
import android.provider.*;
import java.io.*;
import android.support.v4.widget.*;
import com.google.android.gms.ads.*;
import android.annotation.*;
import java.util.regex.*;
import mktvsmart.screen.channel.*;
import mktvsmart.screen.*;
import mktvsmart.screen.exception.*;
import android.view.animation.*;
import mktvsmart.screen.dataconvert.model.*;
import android.view.*;
import android.content.res.*;
import mktvsmart.screen.util.*;
import android.app.*;
import java.text.*;
import java.util.*;
import org.videolan.vlc.util.*;

public class LivePlayActivity extends BaseVLCPlayActivity implements IVideoPlayer, View$OnClickListener, TranscodeListenner, EndOfFileListener, DanmakuManager
{
    public static final int ERROR_PLAY_TIMEOUT = -5;
    public static final int ERROR_STB_INX_TMS_MODE = -2;
    public static final int ERROR_STB_IN_REC_MODE = -1;
    public static final int ERROR_STB_LIVE_STOPED = -3;
    public static final int ERROR_UNKNOWN = 0;
    public static final int ERROR_VIDEO_CANNOT_PLAY = -4;
    private static final int FADE_OUT = 1;
    private static final int FADE_OUT_INFO = 3;
    private static final int HIDE_VOLUME_BRIGHTNESS_BAR = 4;
    public static final int PLAY_SUCCESS = 1;
    private static final String REC_PATH;
    private static final int SHOW_INTERSTITIALAD = 6;
    private static final int SHOW_RECORD_TIP = 5;
    private static final String SNAPSHOT_PATH;
    private static final int SUB_MSG_INIT_SAT2IP_PIPE = 11;
    private static final int SUB_MSG_SAVE_TRANSCODE_SETTING = 12;
    private static final int SURFACE_SIZE = 2;
    private static final String TAG;
    private static boolean bEnableFullAds;
    private static int sAcessPlayCount;
    private static Sat2IP_Rtsp sRtsp;
    private boolean bOnCreateFinish;
    private boolean bOnErrorHappen;
    private boolean bPlayEnd;
    private final Handler eventHandler;
    private boolean isPauseByAd;
    private AdView mAdView;
    private ProgramListDrawer.ProgramListAdapter mAdapter;
    private AudioManager$OnAudioFocusChangeListener mAudioFocusListener;
    private AudioManager mAudioManager;
    public GChatByMobileFragment mChatFragment;
    public FrameLayout mChatView;
    private GChatControllor mChatViewControllor;
    private DataConvertChannelModel mCurChannel;
    private float mCurrentVolumn;
    private boolean mDisabledHardwareAcceleration;
    private GestureDetector mGestureDetector;
    private final Handler mHandler;
    private boolean mHasAudioFocus;
    private TextView mInfo;
    private boolean mIsFirstBrightnessGesture;
    private int mLastAudioTrack;
    private int mLastSpuTrack;
    private LibVLC mLibVLC;
    private ProgramListDrawer mLiveList;
    private boolean mLostFocus;
    private View mMainView;
    GestureDetector$OnGestureListener mOnGestureListener;
    private ImageView mOperationBg;
    private ImageView mOperationPercent;
    private Runnable mPlayEOFOccurred;
    private List<?> mPlayList;
    private int mPlayProgIndex;
    private int mPreviousHardwareAccelerationMode;
    private BroadcastReceiver mReceiver;
    private int mScreenOrientation;
    private int mSelectChannelPosition;
    private SharedPreferences mSettings;
    private Handler mSubHandler;
    private final Handler$Callback mSubMsgCallback;
    private HandlerThread mSubThread;
    private Surface mSubtitleSurface;
    private final SurfaceHolder$Callback mSubtitlesSurfaceCallback;
    private SurfaceHolder mSubtitlesSurfaceHolder;
    private SurfaceView mSubtitlesSurfaceView;
    private Surface mSurface;
    private final SurfaceHolder$Callback mSurfaceCallback;
    private FrameLayout mSurfaceFrame;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private int mSurfaceYDisplayRange;
    private PlayViewControllor mViewControl;
    private View mVolumeBrightnessLayout;
    private View mWaittingView;
    private AlertDialog mWarningDialog;
    private TextView mWattingText;
    private MessageProcessor msgProc;
    MessageProcessor.PerformOnForeground onServerReturn;
    private DataParser parser;
    private PasswordDialog pswInputDialog;
    private String serverAddr;
    private Socket tcpSocket;
    private String url;

    static {
        TAG = LivePlayActivity.class.getSimpleName();
        LivePlayActivity.sRtsp = null;
        REC_PATH = String.valueOf(BitmapUtils.getSDPath()) + "/G-MScreen/video/";
        SNAPSHOT_PATH = String.valueOf(BitmapUtils.getSDPath()) + "/G-MScreen/capture/";
        LivePlayActivity.bEnableFullAds = false;
        LivePlayActivity.sAcessPlayCount = 0;
    }

    public LivePlayActivity() {
        Object mAudioFocusListener = null;
        super();
        this.mSurface = null;
        this.mSubtitleSurface = null;
        this.serverAddr = "";
        this.bOnErrorHappen = false;
        this.mSelectChannelPosition = 0;
        this.mDisabledHardwareAcceleration = false;
        this.mCurrentVolumn = -1.0f;
        this.mSurfaceYDisplayRange = 0;
        this.mIsFirstBrightnessGesture = true;
        this.bOnCreateFinish = false;
        this.isPauseByAd = false;
        this.eventHandler = new VLCEventHandler(this);
        this.bPlayEnd = false;
        this.mLostFocus = false;
        this.mHasAudioFocus = false;
        this.mLastAudioTrack = -1;
        this.mLastSpuTrack = -2;
        this.onServerReturn = new MessageProcessor.PerformOnForeground() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$1() {
                this.this$0 = this$0;
                super();
            }

            @Override
            public void doInForeground(final Message message) {
                if (this.this$0.bOnErrorHappen) {
                    if (this.this$0.mWarningDialog != null && this.this$0.mWarningDialog.isShowing()) {
                        this.this$0.mWarningDialog.dismiss();
                    }
                    LivePlayActivity.access$2(this.this$0, false);
                }
                switch (message.what) {
                    default: {}
                    case 1009: {
                        Log.d(LivePlayActivity.TAG, "GMS_MSG_DO_SAT2IP_CHANNEL_PLAY");
                        LivePlayActivity.this.handleSat2TpReturn(message);
                    }
                    case 3: {
                        Log.d(LivePlayActivity.TAG, "GMS_MSG_REQUEST_PLAYING_CHANNEL");
                        this.this$0.handleSTBChannelPlayChange(message);
                    }
                    case 1056: {
                        LivePlayActivity.this.handlePasswordCheckBack(message);
                    }
                    case 4112: {
                        Log.d(LivePlayActivity.TAG, "GSCMD_NOTIFY_SOCKET_CLOSED");
                        this.this$0.finish();
                    }
                    case 2016:
                    case 2017:
                    case 2018: {
                        LivePlayActivity.this.handleFactoryReset(message);
                    }
                }
            }
        };
        this.mOnGestureListener = (GestureDetector$OnGestureListener)new GestureDetector$SimpleOnGestureListener() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$2() {
                this.this$0 = this$0;
                super();
            }

            public boolean onDown(final MotionEvent motionEvent) {
                LivePlayActivity.access$8(this.this$0, this.this$0.mAudioManager.getStreamVolume(3));
                return false;
            }

            public boolean onScroll(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
                Log.d(LivePlayActivity.TAG, "onScroll");
                final float rawY = motionEvent2.getRawY();
                final float rawY2 = motionEvent.getRawY();
                final float rawX = motionEvent2.getRawX();
                final float rawX2 = motionEvent.getRawX();
                final DisplayMetrics displayMetrics = new DisplayMetrics();
                this.this$0.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                if (this.this$0.mSurfaceYDisplayRange == 0) {
                    LivePlayActivity.access$10(this.this$0, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels));
                }
                if (motionEvent.getRawX() > displayMetrics.widthPixels * 3 / 4) {
                    this.this$0.onVolumeSlide(rawY - rawY2);
                    return true;
                }
                if (motionEvent.getRawY() < displayMetrics.heightPixels / 3) {
                    this.this$0.onBrightnessSlide(-(rawX - rawX2));
                    return true;
                }
                return super.onScroll(motionEvent, motionEvent2, n, n2);
            }
        };
        this.mSurfaceCallback = (SurfaceHolder$Callback)new SurfaceHolder$Callback() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$3() {
                this.this$0 = this$0;
                super();
            }

            public void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
                if (this.this$0.mLibVLC != null) {
                    final Surface surface = surfaceHolder.getSurface();
                    Log.d(LivePlayActivity.TAG, "mSurface != newSurface: " + (this.this$0.mSurface != surface));
                    if (this.this$0.mSurface != surface) {
                        Log.d(LivePlayActivity.TAG, "attachSurface");
                        LivePlayActivity.access$13(this.this$0, surface);
                        this.this$0.mLibVLC.attachSurface(this.this$0.mSurface, this.this$0);
                    }
                }
            }

            public void surfaceCreated(final SurfaceHolder surfaceHolder) {
            }

            public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
                if (this.this$0.mLibVLC != null) {
                    LivePlayActivity.access$13(this.this$0, null);
                    Log.d(LivePlayActivity.TAG, "LibVLC.detachSurface");
                    this.this$0.mLibVLC.detachSurface();
                }
            }
        };
        this.mSubtitlesSurfaceCallback = (SurfaceHolder$Callback)new SurfaceHolder$Callback() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$4() {
                this.this$0 = this$0;
                super();
            }

            public void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
                if (this.this$0.mLibVLC != null) {
                    final Surface surface = surfaceHolder.getSurface();
                    if (this.this$0.mSubtitleSurface != surface) {
                        LivePlayActivity.access$15(this.this$0, surface);
                        this.this$0.mLibVLC.attachSubtitlesSurface(this.this$0.mSubtitleSurface);
                    }
                }
            }

            public void surfaceCreated(final SurfaceHolder surfaceHolder) {
            }

            public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
                if (this.this$0.mLibVLC != null) {
                    LivePlayActivity.access$15(this.this$0, null);
                    this.this$0.mLibVLC.detachSubtitlesSurface();
                }
            }
        };
        this.mPlayEOFOccurred = new Runnable() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$5() {
                this.this$0 = this$0;
                super();
            }

            static /* synthetic */ LivePlayActivity access$0(final LivePlayActivity$5 runnable) {
                return runnable.this$0;
            }

            @Override
            public void run() {
                this.this$0.mViewControl.updateOverlayPausePlay();
                if (this.this$0.bPlayEnd) {
                    new AlertDialog$Builder((Context)this.this$0).setPositiveButton(17039370, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                        final /* synthetic */ LivePlayActivity$5 this$1;

                        LivePlayActivity$5$1() {
                            this.this$1 = this$1;
                            super();
                        }

                        public void onClick(final DialogInterface dialogInterface, final int n) {
                            this.this$1.this$0.finish();
                        }
                    }).setTitle(2131427631).setMessage((CharSequence)"The connection has been disconnected").create().show();
                }
            }
        };
        if (LibVlcUtil.isFroyoOrLater()) {
            mAudioFocusListener = new AudioManager$OnAudioFocusChangeListener() {
                final /* synthetic */ LivePlayActivity this$0;

                LivePlayActivity$6() {
                    this.this$0 = this$0;
                    super();
                }

                public void onAudioFocusChange(final int n) {
                    switch (n) {
                        case -1: {
                            LivePlayActivity.this.changeAudioFocus(false);
                        }
                        case -3:
                        case -2: {
                            if (this.this$0.mLibVLC.isPlaying()) {
                                LivePlayActivity.access$19(this.this$0, true);
                                this.this$0.mLibVLC.pause();
                                return;
                            }
                            break;
                        }
                        case 1:
                        case 2:
                        case 3: {
                            if (!this.this$0.mLibVLC.isPlaying() && this.this$0.mLostFocus) {
                                this.this$0.mLibVLC.play();
                                LivePlayActivity.access$19(this.this$0, false);
                                return;
                            }
                            break;
                        }
                    }
                }
            };
        }
        this.mAudioFocusListener = (AudioManager$OnAudioFocusChangeListener)mAudioFocusListener;
        this.mHandler = new UIHandler(this);
        this.mSubMsgCallback = (Handler$Callback)new Handler$Callback() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$7() {
                this.this$0 = this$0;
                super();
            }

            static /* synthetic */ LivePlayActivity access$0(final LivePlayActivity$7 handler$Callback) {
                return handler$Callback.this$0;
            }

            public boolean handleMessage(final Message message) {
                switch (message.what) {
                    default: {
                        return true;
                    }
                    case 11: {
                        LivePlayActivity.access$21(new Sat2IP_Rtsp());
                        String s = "";
                        String s2 = "";
                        if (GMScreenGlobalInfo.playType == 1) {
                            LivePlayActivity.sRtsp.set_eof_listener((Sat2IP_Rtsp.EndOfFileListener)this.this$0);
                            s = Pvr2smallData.getInstance().getPlayUrlBase(this.this$0.mSelectChannelPosition, this.this$0.tcpSocket.getInetAddress().toString());
                            s2 = Pvr2smallData.getInstance().getPlayUrlQuery();
                        }
                        else if (GMScreenGlobalInfo.playType == 2) {
                            s = Sat2ipUtil.getRtspUriBase(this.this$0.tcpSocket.getInetAddress().toString());
                            s2 = Sat2ipUtil.getRtspUriQuery(this.this$0.mPlayList.get((int)message.obj));
                        }
                        final boolean setup_blocked = LivePlayActivity.sRtsp.setup_blocked(s, s2);
                        Log.d(LivePlayActivity.TAG, "isSetupOk = " + setup_blocked);
                        if (!setup_blocked) {
                            LivePlayActivity.access$21(null);
                            return true;
                        }
                        if (GMScreenGlobalInfo.playType == 2) {
                            LivePlayActivity.this.sendSat2ipChannelIdToStb((int)message.obj);
                        }
                        DVBtoIP.initResourceForPlayer(LivePlayActivity.sRtsp.get_rtp_port(), LivePlayActivity.this.getRtspPipeFilePath(), GMScreenGlobalInfo.playType);
                        LivePlayActivity.access$28(this.this$0, Uri.parse(LibVLC.PathToURI(LivePlayActivity.this.getRtspPipeFilePath())).toString());
                        this.this$0.mHandler.post((Runnable)new Runnable() {
                            final /* synthetic */ LivePlayActivity$7 this$1;

                            LivePlayActivity$7$1() {
                                this.this$1 = this$1;
                                super();
                            }

                            @Override
                            public void run() {
                                LivePlayActivity.this.playChannel();
                            }
                        });
                        return true;
                    }
                    case 12: {
                        TranscodeConstants.saveTranscodeSetting(message.arg1, message.arg2);
                        return true;
                    }
                }
            }
        };
    }

    static /* synthetic */ boolean access$0(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.bOnErrorHappen;
    }

    static /* synthetic */ AlertDialog access$1(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mWarningDialog;
    }

    static /* synthetic */ void access$10(final LivePlayActivity livePlayActivity, final int mSurfaceYDisplayRange) {
        livePlayActivity.mSurfaceYDisplayRange = mSurfaceYDisplayRange;
    }

    static /* synthetic */ LibVLC access$11(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mLibVLC;
    }

    static /* synthetic */ Surface access$12(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mSurface;
    }

    static /* synthetic */ void access$13(final LivePlayActivity livePlayActivity, final Surface mSurface) {
        livePlayActivity.mSurface = mSurface;
    }

    static /* synthetic */ Surface access$14(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mSubtitleSurface;
    }

    static /* synthetic */ void access$15(final LivePlayActivity livePlayActivity, final Surface mSubtitleSurface) {
        livePlayActivity.mSubtitleSurface = mSubtitleSurface;
    }

    static /* synthetic */ PlayViewControllor access$16(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mViewControl;
    }

    static /* synthetic */ boolean access$17(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.bPlayEnd;
    }

    static /* synthetic */ int access$18(final LivePlayActivity livePlayActivity, final boolean b) {
        return livePlayActivity.changeAudioFocus(b);
    }

    static /* synthetic */ void access$19(final LivePlayActivity livePlayActivity, final boolean mLostFocus) {
        livePlayActivity.mLostFocus = mLostFocus;
    }

    static /* synthetic */ void access$2(final LivePlayActivity livePlayActivity, final boolean bOnErrorHappen) {
        livePlayActivity.bOnErrorHappen = bOnErrorHappen;
    }

    static /* synthetic */ boolean access$20(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mLostFocus;
    }

    static /* synthetic */ void access$21(final Sat2IP_Rtsp sRtsp) {
        LivePlayActivity.sRtsp = sRtsp;
    }

    static /* synthetic */ Sat2IP_Rtsp access$22() {
        return LivePlayActivity.sRtsp;
    }

    static /* synthetic */ int access$23(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mSelectChannelPosition;
    }

    static /* synthetic */ Socket access$24(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.tcpSocket;
    }

    static /* synthetic */ List access$25(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mPlayList;
    }

    static /* synthetic */ boolean access$26(final LivePlayActivity livePlayActivity, final int n) {
        return livePlayActivity.sendSat2ipChannelIdToStb(n);
    }

    static /* synthetic */ String access$27(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.getRtspPipeFilePath();
    }

    static /* synthetic */ void access$28(final LivePlayActivity livePlayActivity, final String url) {
        livePlayActivity.url = url;
    }

    static /* synthetic */ Handler access$29(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mHandler;
    }

    static /* synthetic */ String access$3() {
        return LivePlayActivity.TAG;
    }

    static /* synthetic */ void access$30(final LivePlayActivity livePlayActivity) {
        livePlayActivity.playChannel();
    }

    static /* synthetic */ ProgramListDrawer access$31(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mLiveList;
    }

    static /* synthetic */ View access$32(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mVolumeBrightnessLayout;
    }

    static /* synthetic */ void access$33(final LivePlayActivity livePlayActivity, final Intent intent) {
        livePlayActivity.handleBroadcast(intent);
    }

    static /* synthetic */ GestureDetector access$34(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mGestureDetector;
    }

    static /* synthetic */ void access$35(final LivePlayActivity livePlayActivity, final int n) {
        livePlayActivity.requestPlayUrl(n);
    }

    static /* synthetic */ void access$36(final LivePlayActivity livePlayActivity, final DataParser parser) {
        livePlayActivity.parser = parser;
    }

    static /* synthetic */ DataParser access$37(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.parser;
    }

    static /* synthetic */ PasswordDialog access$38(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.pswInputDialog;
    }

    static /* synthetic */ SurfaceView access$39(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mSurfaceView;
    }

    static /* synthetic */ void access$4(final LivePlayActivity livePlayActivity, final Message message) {
        livePlayActivity.handleSat2TpReturn(message);
    }

    static /* synthetic */ void access$40(final LivePlayActivity livePlayActivity) {
        livePlayActivity.stopLoadingAnimation();
    }

    static /* synthetic */ void access$41(final LivePlayActivity livePlayActivity, final boolean mDisabledHardwareAcceleration) {
        livePlayActivity.mDisabledHardwareAcceleration = mDisabledHardwareAcceleration;
    }

    static /* synthetic */ void access$42(final LivePlayActivity livePlayActivity, final int mPreviousHardwareAccelerationMode) {
        livePlayActivity.mPreviousHardwareAccelerationMode = mPreviousHardwareAccelerationMode;
    }

    static /* synthetic */ SurfaceHolder access$43(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mSurfaceHolder;
    }

    static /* synthetic */ SurfaceHolder access$44(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mSubtitlesSurfaceHolder;
    }

    static /* synthetic */ void access$5(final LivePlayActivity livePlayActivity, final Message message) {
        livePlayActivity.handlePasswordCheckBack(message);
    }

    static /* synthetic */ void access$6(final LivePlayActivity livePlayActivity, final Message message) {
        livePlayActivity.handleFactoryReset(message);
    }

    static /* synthetic */ AudioManager access$7(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mAudioManager;
    }

    static /* synthetic */ void access$8(final LivePlayActivity livePlayActivity, final float mCurrentVolumn) {
        livePlayActivity.mCurrentVolumn = mCurrentVolumn;
    }

    static /* synthetic */ int access$9(final LivePlayActivity livePlayActivity) {
        return livePlayActivity.mSurfaceYDisplayRange;
    }

    @TargetApi(8)
    private int changeAudioFocus(final boolean b) {
        if (LibVlcUtil.isFroyoOrLater()) {
            if (this.mAudioManager == null) {
                return 0;
            }
            if (b) {
                if (!this.mHasAudioFocus) {
                    final int requestAudioFocus = this.mAudioManager.requestAudioFocus(this.mAudioFocusListener, 3, 1);
                    this.mAudioManager.setParameters("bgm_state=true");
                    this.mHasAudioFocus = true;
                    return requestAudioFocus;
                }
            }
            else if (this.mHasAudioFocus) {
                final int abandonAudioFocus = this.mAudioManager.abandonAudioFocus(this.mAudioFocusListener);
                this.mAudioManager.setParameters("bgm_state=false");
                this.mHasAudioFocus = true;
                return abandonAudioFocus;
            }
        }
        return 1;
    }

    private String getRtspPipeFilePath() {
        return String.valueOf(this.getCacheDir().getAbsolutePath()) + "/" + this.getString(2131427377) + ".ts";
    }

    private void handleBroadcast(final Intent intent) {
        if (intent.getAction().equalsIgnoreCase("android.intent.action.BATTERY_CHANGED")) {
            this.mViewControl.setBatteryLevel(intent.getIntExtra("level", 0));
        }
    }

    private void handleFactoryReset(final Message message) {
        if (message.arg2 != 0) {
            return;
        }
        final String s = "";
        String s2 = null;
        switch (message.what) {
            default: {
                s2 = s;
                break;
            }
            case 2017: {
                s2 = s;
                if (((DataConvertChannelModel)this.mPlayList.get(this.mSelectChannelPosition)).getChannelTpye() == 0) {
                    s2 = this.getString(2131427649);
                    break;
                }
                break;
            }
            case 2018: {
                s2 = s;
                if (((DataConvertChannelModel)this.mPlayList.get(this.mSelectChannelPosition)).getChannelTpye() == 1) {
                    s2 = this.getString(2131427650);
                    break;
                }
                break;
            }
            case 2016: {
                s2 = this.getString(2131427651);
                break;
            }
        }
        this.finish();
        Toast.makeText((Context)this, (CharSequence)s2, 0).show();
    }

    private void handlePasswordCheckBack(final Message message) {
        try {
            final byte[] byteArray = message.getData().getByteArray("ReceivedData");
            this.parser = ParserFactory.getParser();
            if (Integer.parseInt((String)this.parser.parse(new ByteArrayInputStream(byteArray, 0, byteArray.length), 15).get(0)) == 0) {
                this.inputPermissionPassword(this.mSelectChannelPosition);
                return;
            }
            this.askPlayUrl(this.mSelectChannelPosition);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleSat2TpReturn(final Message message) {
        final byte[] byteArray = message.getData().getByteArray("ReceivedData");
        if (byteArray == null) {
            return;
        }
        Log.d(LivePlayActivity.TAG, new String(byteArray));
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray, 0, message.arg1);
        Map map;
        try {
            map = (Map)this.parser.parse(byteArrayInputStream, 16).get(0);
            if (map.get("success") == null) {
                this.showDialog(0);
                return;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        final int intValue = map.get("success");
        if (intValue != 1) {
            Bundle bundle = null;
            if (map.get("errormsg") != null) {
                final String s = (String)map.get("errormsg");
                bundle = new Bundle();
                bundle.putString("message", s);
            }
            else if (map.get("url") == null || ((String)map.get("url")).length() <= 0) {
                bundle = new Bundle();
                bundle.putString("message", "Get play url fail");
            }
            this.showDialog(intValue, bundle);
            return;
        }
        this.url = (String)map.get("url");
        if (this.url == null) {
            Toast.makeText((Context)this, 2131427634, 2000).show();
            return;
        }
        Log.d(LivePlayActivity.TAG, "play url : " + this.url);
        this.playChannel();
    }

    private boolean init() {
        this.mLibVLC = VLCInstance.getLibVlcInstance();
        this.mSettings = PreferenceManager.getDefaultSharedPreferences((Context)this);
        this.initView();
        this.initPath();
        EventHandler.getInstance().addHandler(this.eventHandler);
        if (!this.initData()) {
            return false;
        }
        final Intent intent = new Intent("com.android.music.musicservicecommand");
        intent.putExtra("command", "pause");
        this.sendBroadcast(intent);
        this.setVolumeControlStream(3);
        this.mAudioManager = (AudioManager)this.getSystemService("audio");
        this.mReceiver = new BroadcastReceiver() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$8() {
                this.this$0 = this$0;
                super();
            }

            public void onReceive(final Context context, final Intent intent) {
                LivePlayActivity.this.handleBroadcast(intent);
            }
        };
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        this.registerReceiver(this.mReceiver, intentFilter);
        this.mLibVLC.eventVideoPlayerActivityCreated(true);
        return true;
    }

    private void initBrightnessTouch() {
        float screenBrightness = 0.01f;
        while (true) {
            try {
                screenBrightness = Settings$System.getInt(this.getContentResolver(), "screen_brightness") / 255.0f;
                final WindowManager$LayoutParams attributes = this.getWindow().getAttributes();
                attributes.screenBrightness = screenBrightness;
                this.getWindow().setAttributes(attributes);
                this.mIsFirstBrightnessGesture = false;
            }
            catch (Settings$SettingNotFoundException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }

    private boolean initData() {
        while (true) {
            Block_4: {
                while (true) {
                    this.setMessageProcess();
                    final CreateSocket createSocket = new CreateSocket("", 0);
                    while (true) {
                        try {
                            this.tcpSocket = createSocket.GetSocket();
                            this.serverAddr = this.tcpSocket.getInetAddress().getHostAddress();
                            Log.d(LivePlayActivity.TAG, "serverAddr = " + this.serverAddr);
                            this.parser = ParserFactory.getParser();
                            this.mPlayProgIndex = this.getIntent().getIntExtra("position", -1);
                            if (this.mPlayProgIndex == -1) {
                                return false;
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            continue;
                        }
                        break;
                    }
                    if (GMScreenGlobalInfo.playType == 2) {
                        this.initSameTpChannes(this.mPlayProgIndex);
                        break;
                    }
                    if (GMScreenGlobalInfo.playType != 1) {
                        break;
                    }
                    this.mPlayList = Pvr2smallData.getInstance().getPvr2smallList();
                    if (this.mPlayList != null) {
                        break Block_4;
                    }
                    return false;
                }
                this.requestChannelInfoForChat();
                return true;
            }
            this.mSelectChannelPosition = this.mPlayProgIndex;
            this.mAdapter.setListData(this.mPlayList);
            this.mAdapter.notifyDataSetChanged();
            this.askPlayUrl(this.mSelectChannelPosition);
            continue;
        }
    }

    private void initPath() {
        final File file = new File(LivePlayActivity.REC_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        final File file2 = new File(LivePlayActivity.SNAPSHOT_PATH);
        if (!file2.exists()) {
            file2.mkdirs();
        }
    }

    @SuppressLint({ "NewApi" })
    private void initView() {
        this.setContentView(this.mMainView = LayoutInflater.from((Context)this).inflate(2130903074, (ViewGroup)null));
        this.findViewById(2131361926).setOnClickListener((View$OnClickListener)this);
        this.mGestureDetector = new GestureDetector(this.mOnGestureListener);
        this.findViewById(2131361926).setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$9() {
                this.this$0 = this$0;
                super();
            }

            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                return this.this$0.mGestureDetector.onTouchEvent(motionEvent);
            }
        });
        this.mSurfaceView = (SurfaceView)this.findViewById(2131362165);
        this.mSurfaceHolder = this.mSurfaceView.getHolder();
        this.mSubtitlesSurfaceView = (SurfaceView)this.findViewById(2131362166);
        this.mSubtitlesSurfaceHolder = this.mSubtitlesSurfaceView.getHolder();
        this.mSurfaceFrame = (FrameLayout)this.findViewById(2131362164);
        this.mSubtitlesSurfaceView.setZOrderMediaOverlay(true);
        this.mSubtitlesSurfaceHolder.setFormat(-3);
        final String string = this.mSettings.getString("chroma_format", "");
        if (LibVlcUtil.isGingerbreadOrLater() && string.equals("YV12")) {
            this.mSurfaceHolder.setFormat(842094169);
        }
        else if (string.equals("RV16")) {
            this.mSurfaceHolder.setFormat(4);
        }
        else {
            this.mSurfaceHolder.setFormat(2);
        }
        this.mSurfaceHolder.addCallback(this.mSurfaceCallback);
        this.mSubtitlesSurfaceHolder.addCallback(this.mSubtitlesSurfaceCallback);
        if (this.mLibVLC.useCompatSurface()) {
            this.mSubtitlesSurfaceView.setVisibility(8);
        }
        this.mInfo = (TextView)this.findViewById(2131362170);
        this.mVolumeBrightnessLayout = this.findViewById(2131362178);
        this.mOperationBg = (ImageView)this.findViewById(2131362179);
        this.mOperationPercent = (ImageView)this.findViewById(2131362181);
        this.mWaittingView = this.findViewById(2131362171);
        this.mWattingText = (TextView)this.findViewById(2131362173);
        (this.mLiveList = (ProgramListDrawer)this.getSupportFragmentManager().findFragmentById(2131361894)).setUp(2131361894, (DrawerLayout)this.findViewById(2131361892));
        this.mAdapter = new ProgramListDrawer.ProgramListAdapter((Context)this, this.mPlayList);
        this.mLiveList.setAdapter(this.mAdapter);
        this.mScreenOrientation = Integer.valueOf(this.mSettings.getString("screen_orientation_value", "4"));
        this.mChatViewControllor = new GChatControllor(this);
        this.mChatFragment = (GChatByMobileFragment)this.getSupportFragmentManager().findFragmentById(2131361928);
        this.mChatView = (FrameLayout)this.findViewById(2131361927);
        if (GMScreenGlobalInfo.isChatSupport()) {
            this.setRequestedOrientation(1);
        }
        else {
            this.mChatView.setVisibility(8);
            int requestedOrientation;
            if (this.mScreenOrientation != 100) {
                requestedOrientation = this.mScreenOrientation;
            }
            else {
                requestedOrientation = ScreenUtil.getScreenOrientation((Context)this);
            }
            this.setRequestedOrientation(requestedOrientation);
        }
        (this.mViewControl = new PlayViewControllor(this)).setOnTranscodeListenner((PlayViewControllor.TranscodeListenner)this);
        (this.mAdView = (AdView)this.findViewById(2131362169)).loadAd(new AdRequest.Builder().build());
        this.mAdView.setVisibility(8);
        if (LibVlcUtil.isHoneycombOrLater()) {
            this.mSurfaceFrame.addOnLayoutChangeListener((View$OnLayoutChangeListener)new View$OnLayoutChangeListener() {
                final /* synthetic */ LivePlayActivity this$0;

                LivePlayActivity$10() {
                    this.this$0 = this$0;
                    super();
                }

                public void onLayoutChange(final View view, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
                    if (n != n5 || n2 != n6 || n3 != n7 || n4 != n8) {
                        this.this$0.setSurfaceLayout(this.this$0.mViewControl.mVideoWidth, this.this$0.mViewControl.mVideoHeight, this.this$0.mViewControl.mVideoVisibleWidth, this.this$0.mViewControl.mVideoVisibleHeight, this.this$0.mViewControl.mSarNum, this.this$0.mViewControl.mSarDen);
                    }
                }
            });
        }
    }

    private void playChannel() {
        if (this.url != null && this.url.length() > 0 && !this.url.startsWith("file://") && !this.url.contains(this.serverAddr)) {
            this.url = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(this.url).replaceAll(this.serverAddr);
        }
        this.mSurfaceView.setKeepScreenOn(true);
        this.mLibVLC.playMRL(this.url);
        String title = "";
        if (GMScreenGlobalInfo.playType == 2) {
            title = this.mCurChannel.getProgramName();
        }
        else if (GMScreenGlobalInfo.playType == 1) {
            title = ((DataConvertPvrInfoModel)this.mPlayList.get(this.mSelectChannelPosition)).getProgramName();
        }
        this.mViewControl.setTitle(title);
    }

    private void requestChannelInfoForChat() {
        final List<DataConvertChannelModel> channelListByTvRadioType = ChannelData.getInstance().getChannelListByTvRadioType();
        if (this.mPlayProgIndex < 0 || this.mPlayProgIndex >= channelListByTvRadioType.size()) {
            return;
        }
        final DataConvertChannelModel dataConvertChannelModel = channelListByTvRadioType.get(this.mPlayProgIndex);
        final ArrayList<DataConvertChannelModel> list = new ArrayList<DataConvertChannelModel>();
        list.add(dataConvertChannelModel);
        final DataParser parser = ParserFactory.getParser();
        try {
            final byte[] bytes = parser.serialize(list, 104).getBytes("UTF-8");
            this.tcpSocket.setSoTimeout(3000);
            GsSendSocket.sendSocketToStb(bytes, this.tcpSocket, 0, bytes.length, 104);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void requestPlayUrl(final int mSelectChannelPosition) {
        if (this.mLibVLC.isPlaying()) {
            this.mLibVLC.stop();
        }
        this.startLoadingAnimation(this.getString(2131427520));
        this.mSelectChannelPosition = mSelectChannelPosition;
        this.mLiveList.setSelectedItem(this.mSelectChannelPosition);
        switch (GMScreenGlobalInfo.getCurStbInfo().getPlatform_id()) {
            default: {
                this.sendSat2ipChannelIdToStb(this.mSelectChannelPosition);
            }
            case 8:
            case 9: {
                this.stopStream();
                if (this.mSubThread == null) {
                    (this.mSubThread = new HandlerThread("live_play_work_thread")).start();
                }
                if (this.mSubHandler == null) {
                    this.mSubHandler = new Handler(this.mSubThread.getLooper(), this.mSubMsgCallback);
                }
                this.mSubHandler.sendMessage(this.mSubHandler.obtainMessage(11, (Object)this.mSelectChannelPosition));
            }
        }
    }

    private boolean sendSat2ipChannelIdToStb(final int n) {
        final ArrayList<DataConvertChannelModel> list = new ArrayList<DataConvertChannelModel>();
        try {
            list.add(this.mCurChannel = (DataConvertChannelModel)this.mPlayList.get(n));
            final byte[] bytes = this.parser.serialize(list, 1009).getBytes("UTF-8");
            this.tcpSocket.setSoTimeout(3000);
            GsSendSocket.sendSocketToStb(bytes, this.tcpSocket, 0, bytes.length, 1009);
            return true;
        }
        catch (ProgramNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            return false;
        }
    }

    private void setESTracks() {
        if (this.mLastAudioTrack >= 0) {
            this.mLibVLC.setAudioTrack(this.mLastAudioTrack);
            this.mLastAudioTrack = -1;
        }
        if (this.mLastSpuTrack >= -1) {
            this.mLibVLC.setSpuTrack(this.mLastSpuTrack);
            this.mLastSpuTrack = -2;
        }
    }

    private void setMessageProcess() {
        (this.msgProc = MessageProcessor.obtain()).recycle();
        this.msgProc.setOnMessageProcess(1009, this, this.onServerReturn);
        this.msgProc.setOnMessageProcess(3, this, this.onServerReturn);
        this.msgProc.setOnMessageProcess(1056, this, this.onServerReturn);
        this.msgProc.setOnMessageProcess(4112, this, this.onServerReturn);
        this.msgProc.setOnMessageProcess(2017, this, this.onServerReturn);
        this.msgProc.setOnMessageProcess(2018, this, this.onServerReturn);
        this.msgProc.setOnMessageProcess(2016, this, this.onServerReturn);
    }

    private void startLoadingAnimation(final String text) {
        if (this.mWaittingView.getVisibility() != 0) {
            this.mWaittingView.setVisibility(0);
        }
        this.mWattingText.setText((CharSequence)text);
    }

    private void stopLoadingAnimation() {
        this.mWaittingView.setVisibility(4);
    }

    private void stopStream() {
        if (LivePlayActivity.sRtsp != null) {
            LivePlayActivity.sRtsp.teardown();
            LivePlayActivity.sRtsp = null;
            DVBtoIP.destroyResourceForPlayer();
            GsSendSocket.sendOnlyCommandSocketToStb(this.tcpSocket, 1012);
        }
    }

    public void OnTranscodeChange(final int n, final int n2) {
        if (this.mSubThread == null) {
            (this.mSubThread = new HandlerThread("live_play_work_thread")).start();
        }
        if (this.mSubHandler == null) {
            this.mSubHandler = new Handler(this.mSubThread.getLooper(), this.mSubMsgCallback);
        }
        this.mSubHandler.sendMessage(this.mSubHandler.obtainMessage(12, n, n2));
        this.askPlayUrl(this.mSelectChannelPosition);
    }

    public void addDanmaku(final boolean b, final String s) {
        this.getChatViewControllor().addDanmaku(b, s);
    }

    public void askPlayUrl(final int n) {
        if (this.mLibVLC.videoIsRecording()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(5, (Object)new Runnable() {
                final /* synthetic */ LivePlayActivity this$0;
                private final /* synthetic */ int val$iPosition;

                LivePlayActivity$11() {
                    this.this$0 = this$0;
                    super();
                }

                @Override
                public void run() {
                    LivePlayActivity.this.requestPlayUrl(n);
                }
            }));
            return;
        }
        this.requestPlayUrl(n);
    }

    public boolean checkChannelLock(final int n) {
        boolean b = false;
        if (((DataConvertChannelModel)this.mPlayList.get(n)).getLockMark() == 1) {
            b = true;
        }
        return b;
    }

    @Override
    public int configureSurface(Surface surface, final int n, final int n2, final int n3) {
        Log.d(LivePlayActivity.TAG, "configureSurface:" + surface + "  " + n + "x" + n2);
        Log.d(LivePlayActivity.TAG, "LibVlcUtil.isICSOrLater():" + LibVlcUtil.isICSOrLater());
        if (LibVlcUtil.isICSOrLater() || surface == null) {
            return -1;
        }
        if (n * n2 == 0) {
            return 0;
        }
        surface = (Surface)new ConfigureSurfaceHolder(surface);
        this.mHandler.post((Runnable)new Runnable() {
            final /* synthetic */ LivePlayActivity this$0;
            private final /* synthetic */ int val$hal;
            private final /* synthetic */ int val$height;
            private final /* synthetic */ ConfigureSurfaceHolder val$holder;
            private final /* synthetic */ int val$width;

            LivePlayActivity$20() {
                this.this$0 = this$0;
                super();
            }

            @Override
            public void run() {
                Log.d(LivePlayActivity.TAG, "setFixedSize:" + n + "x" + n2 + " hal = " + n3);
                Label_0145: {
                    if (this.this$0.mSurface != ((ConfigureSurfaceHolder)surface).surface || this.this$0.mSurfaceHolder == null) {
                        break Label_0145;
                    }
                    if (n3 != 0) {
                        this.this$0.mSurfaceHolder.setFormat(n3);
                    }
                    this.this$0.mSurfaceHolder.setFixedSize(n, n2);
                    Block_8_Outer:
                    while (true) {
                        synchronized (surface) {
                            ((ConfigureSurfaceHolder)surface).configured = true;
                            surface.notifyAll();
                            return;
                            while (true) {
                                this.this$0.mSubtitlesSurfaceHolder.setFormat(n3);
                                Block_7: {
                                    Label_0195: {
                                        break Label_0195;
                                        break Block_7;
                                    }
                                    this.this$0.mSubtitlesSurfaceHolder.setFixedSize(n, n2);
                                    continue Block_8_Outer;
                                }
                                continue;
                            }
                        }
                        // iftrue(Label_0120:, LivePlayActivity.access$14(this.this$0) != this.val$holder.surface || LivePlayActivity.access$44(this.this$0) == null)
                        // iftrue(Label_0195:, this.val$hal == 0)
                    }
                }
            }
        });
        try {
            synchronized (surface) {
                if (!((ConfigureSurfaceHolder)surface).configured) {
                    Log.d(LivePlayActivity.TAG, "holder.wait()");
                    surface.wait();
                }
                return 1;
            }
        }
        catch (InterruptedException ex) {
            return 0;
        }
    }

    @Override
    public void eventHardwareAccelerationError() {
        EventHandler.getInstance().callback(12288, new Bundle());
    }

    public void fadeOutInfo() {
        if (this.mInfo.getVisibility() == 0) {
            this.mInfo.startAnimation(AnimationUtils.loadAnimation((Context)this, 17432577));
        }
        this.mInfo.setVisibility(4);
    }

    public void finish() {
        super.finish();
        GMScreenGlobalInfo.playType = 0;
    }

    public GChatControllor getChatViewControllor() {
        return this.mChatViewControllor;
    }

    public String getSTBReturnMessage(final int n, final Bundle bundle) {
        if (bundle != null && bundle.getString("message") != null) {
            return bundle.getString("message");
        }
        switch (n) {
            default: {
                return this.getString(2131427626);
            }
            case -3: {
                return this.getString(2131427628);
            }
            case -2: {
                return this.getString(2131427629);
            }
            case -1: {
                return this.getString(2131427630);
            }
        }
    }

    public int getScreenOrientation() {
        return this.mScreenOrientation;
    }

    public SurfaceView getSubtitleSurfaceView() {
        return this.mSubtitlesSurfaceView;
    }

    public FrameLayout getSurfaceFrame() {
        return this.mSurfaceFrame;
    }

    public SurfaceHolder getSurfaceHolder() {
        return this.mSurfaceHolder;
    }

    public SurfaceView getSurfaceView() {
        return this.mSurfaceView;
    }

    public void handleSTBChannelPlayChange(final Message p0) {
        //
        // This method could not be decompiled.
        //
        // Original Bytecode:
        //
        //     1: getfield        android/os/Message.arg1:I
        //     4: ifle            159
        //     7: aload_1
        //     8: invokevirtual   android/os/Message.getData:()Landroid/os/Bundle;
        //    11: ldc_w           "ReceivedData"
        //    14: invokevirtual   android/os/Bundle.getByteArray:(Ljava/lang/String;)[B
        //    17: astore_3
        //    18: invokestatic    mktvsmart/screen/dataconvert/parser/ParserFactory.getParser:()Lmktvsmart/screen/dataconvert/parser/DataParser;
        //    21: astore          4
        //    23: aconst_null
        //    24: astore_2
        //    25: aload           4
        //    27: new             Ljava/io/ByteArrayInputStream;
        //    30: dup
        //    31: aload_3
        //    32: iconst_0
        //    33: aload_1
        //    34: getfield        android/os/Message.arg1:I
        //    37: invokespecial   java/io/ByteArrayInputStream.<init>:([BII)V
        //    40: bipush          15
        //    42: invokeinterface mktvsmart/screen/dataconvert/parser/DataParser.parse:(Ljava/io/InputStream;I)Ljava/util/List;
        //    47: astore_1
        //    48: aload_1
        //    49: iconst_0
        //    50: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    55: checkcast       Ljava/lang/String;
        //    58: astore_3
        //    59: aconst_null
        //    60: astore_2
        //    61: aload_0
        //    62: getfield        mktvsmart/screen/vlc/LivePlayActivity.mPlayList:Ljava/util/List;
        //    65: aload_0
        //    66: getfield        mktvsmart/screen/vlc/LivePlayActivity.mSelectChannelPosition:I
        //    69: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    74: checkcast       Lmktvsmart/screen/dataconvert/model/DataConvertChannelModel;
        //    77: astore          4
        //    79: invokestatic    mktvsmart/screen/channel/ChannelData.getInstance:()Lmktvsmart/screen/channel/ChannelData;
        //    82: aload_3
        //    83: invokevirtual   mktvsmart/screen/channel/ChannelData.getProgramByProgramId:(Ljava/lang/String;)Lmktvsmart/screen/dataconvert/model/DataConvertChannelModel;
        //    86: astore_3
        //    87: aload_3
        //    88: astore_2
        //    89: invokestatic    mktvsmart/screen/channel/ChannelData.getInstance:()Lmktvsmart/screen/channel/ChannelData;
        //    92: aload_2
        //    93: aload           4
        //    95: invokevirtual   mktvsmart/screen/channel/ChannelData.canSat2ipChannelPlay:(Lmktvsmart/screen/dataconvert/model/DataConvertChannelModel;Lmktvsmart/screen/dataconvert/model/DataConvertChannelModel;)Z
        //    98: ifne            159
        //   101: invokestatic    mktvsmart/screen/GMScreenGlobalInfo.getCurStbInfo:()Lmktvsmart/screen/GsMobileLoginInfo;
        //   104: invokevirtual   mktvsmart/screen/GsMobileLoginInfo.getPlatform_id:()I
        //   107: tableswitch {
        //               16: 178
        //               17: 178
        //          default: 128
        //        }
        //   128: aload_0
        //   129: invokestatic    mktvsmart/screen/channel/ChannelData.getInstance:()Lmktvsmart/screen/channel/ChannelData;
        //   132: aload_1
        //   133: iconst_0
        //   134: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   139: checkcast       Ljava/lang/String;
        //   142: invokevirtual   mktvsmart/screen/channel/ChannelData.getIndexByProgIdInCurTvRadioProgList:(Ljava/lang/String;)I
        //   145: invokevirtual   mktvsmart/screen/vlc/LivePlayActivity.initSameTpChannes:(I)V
        //   148: aload_0
        //   149: ldc_w           2131427625
        //   152: iconst_0
        //   153: invokestatic    android/widget/Toast.makeText:(Landroid/content/Context;II)Landroid/widget/Toast;
        //   156: invokevirtual   android/widget/Toast.show:()V
        //   159: return
        //   160: astore_1
        //   161: aload_1
        //   162: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   165: aload_2
        //   166: astore_1
        //   167: goto            48
        //   170: astore_3
        //   171: aload_3
        //   172: invokevirtual   mktvsmart/screen/exception/ProgramNotFoundException.printStackTrace:()V
        //   175: goto            89
        //   178: aload_0
        //   179: invokevirtual   mktvsmart/screen/vlc/LivePlayActivity.finish:()V
        //   182: goto            148
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  -----------------------------------------------------
        //  25     48     160    170    Ljava/lang/Exception;
        //  79     87     170    178    Lmktvsmart/screen/exception/ProgramNotFoundException;
        //
        // The error that occurred was:
        //
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0089:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.DecompilerLinkProvider.generateContent(DecompilerLinkProvider.java:96)
        //     at us.deathmarine.luyten.OpenFile.decompileWithNavigationLinks(OpenFile.java:271)
        //     at us.deathmarine.luyten.OpenFile.decompile(OpenFile.java:244)
        //     at us.deathmarine.luyten.Model.extractClassToTextPane(Model.java:347)
        //     at us.deathmarine.luyten.Model.access$2300(Model.java:65)
        //     at us.deathmarine.luyten.Model$7.run(Model.java:940)
        //     at java.lang.Thread.run(Unknown Source)
        //
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }

    public void hideChannelList() {
        if (this.mLiveList.isShowing()) {
            this.mLiveList.hide();
        }
    }

    public void hideInfo() {
        this.hideInfo(0);
    }

    public void hideInfo(final int n) {
        this.mHandler.sendEmptyMessageDelayed(3, (long)n);
    }

    public void hideOverlay() {
        this.mHandler.removeMessages(1);
        this.mViewControl.hide();
    }

    public void initSameTpChannes(final int n) {
        this.bPlayEnd = false;
        this.mHandler.removeCallbacks(this.mPlayEOFOccurred);
        final List<DataConvertChannelModel> channelListByTvRadioType = ChannelData.getInstance().getChannelListByTvRadioType();
        final ArrayList<Object> mPlayList = new ArrayList<Object>();
        final String substring = channelListByTvRadioType.get(n).GetProgramId().substring(0, 9);
        for (int i = 0; i < channelListByTvRadioType.size(); ++i) {
            final DataConvertChannelModel dataConvertChannelModel = channelListByTvRadioType.get(i);
            if (dataConvertChannelModel.GetProgramId().startsWith(substring)) {
                mPlayList.add(dataConvertChannelModel);
                if (dataConvertChannelModel.GetProgramId().equals(channelListByTvRadioType.get(n).GetProgramId())) {
                    this.mSelectChannelPosition = mPlayList.size() - 1;
                }
            }
        }
        this.mPlayList = mPlayList;
        this.mAdapter.setListData(this.mPlayList);
        this.mAdapter.notifyDataSetChanged();
        if (this.checkChannelLock(this.mSelectChannelPosition)) {
            this.inputPermissionPassword(this.mSelectChannelPosition);
            return;
        }
        this.askPlayUrl(this.mSelectChannelPosition);
    }

    public void inputPermissionPassword(final int mSelectChannelPosition) {
        this.mSelectChannelPosition = mSelectChannelPosition;
        if (this.pswInputDialog != null && this.pswInputDialog.isShowing()) {
            this.pswInputDialog.dismiss();
        }
        (this.pswInputDialog = new PasswordDialog((Context)this)).setOnTextChangeListener((PasswordDialog.OnTextChangeListener)new PasswordDialog.OnTextChangeListener() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$12() {
                this.this$0 = this$0;
                super();
            }

            @Override
            public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                if (charSequence == null || charSequence.length() != GMScreenGlobalInfo.getmMaxPasswordNum()) {
                    return;
                }
                final ArrayList<DataConvertOneDataModel> list = new ArrayList<DataConvertOneDataModel>();
                final DataConvertOneDataModel dataConvertOneDataModel = new DataConvertOneDataModel();
                dataConvertOneDataModel.setData(charSequence.toString());
                list.add(dataConvertOneDataModel);
                while (true) {
                    try {
                        LivePlayActivity.access$36(this.this$0, ParserFactory.getParser());
                        final byte[] bytes = this.this$0.parser.serialize(list, 1056).getBytes("UTF-8");
                        this.this$0.tcpSocket.setSoTimeout(3000);
                        GsSendSocket.sendSocketToStb(bytes, this.this$0.tcpSocket, 0, bytes.length, 1056);
                        this.this$0.pswInputDialog.dismiss();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        continue;
                    }
                    break;
                }
            }
        });
        this.pswInputDialog.setTitle(((DataConvertChannelModel)this.mPlayList.get(this.mSelectChannelPosition)).getProgramName());
        this.pswInputDialog.setCanceledOnTouchOutside(false);
        this.pswInputDialog.show();
    }

    public boolean isPlaying() {
        return this.mLibVLC.isPlaying();
    }

    public boolean isPlaying(final int n) {
        return this.mSelectChannelPosition == n && this.mLibVLC.isPlaying();
    }

    public boolean isRecordable() {
        return this.mLibVLC.videoIsRecordable();
    }

    public boolean isRecording() {
        return this.mLibVLC.videoIsRecording();
    }

    public void onBackPressed() {
        if (this.mLiveList.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    public void onBrightnessSlide(float n) {
        if (this.mIsFirstBrightnessGesture) {
            this.initBrightnessTouch();
        }
        n = -n / this.mSurfaceYDisplayRange;
        final WindowManager$LayoutParams attributes = this.getWindow().getAttributes();
        attributes.screenBrightness = Math.min(Math.max(attributes.screenBrightness + n * 0.07f, 0.01f), 1.0f);
        this.getWindow().setAttributes(attributes);
        if (this.mVolumeBrightnessLayout.getVisibility() != 0) {
            this.mVolumeBrightnessLayout.setVisibility(0);
        }
        this.mOperationBg.setImageResource(2130838213);
        final ViewGroup$LayoutParams layoutParams = this.mOperationPercent.getLayoutParams();
        layoutParams.width = (int)(this.findViewById(2131362180).getLayoutParams().width * attributes.screenBrightness);
        this.mOperationPercent.setLayoutParams(layoutParams);
        this.mHandler.removeMessages(4);
        this.mHandler.sendEmptyMessageDelayed(4, 1000L);
    }

    public void onClick(final View view) {
        if (view.equals(this.mMainView) || view.getId() == 2131361926) {
            if (this.mLiveList.isShowing()) {
                Log.d(LivePlayActivity.TAG, "hide mLiveList");
                this.mLiveList.hide();
            }
            else {
                Log.d(LivePlayActivity.TAG, "mViewControl.isShowing() = " + this.mViewControl.isShowing());
                if (this.mViewControl.isShowing()) {
                    this.hideOverlay();
                    return;
                }
                this.showOverlay();
            }
        }
    }

    public void onConfigurationChanged(final Configuration configuration) {
        Log.d(LivePlayActivity.TAG, "onConfigurationChanged");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
        super.onConfigurationChanged(configuration);
    }

    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final boolean checkVlcLibs = VLCInstance.checkVlcLibs(this);
        Log.d(LivePlayActivity.TAG, "bVLCInit = " + checkVlcLibs);
        if (!checkVlcLibs) {
            return;
        }
        if (!this.init()) {
            this.finish();
            return;
        }
        if (LivePlayActivity.bEnableFullAds) {
            if (LivePlayActivity.sAcessPlayCount / 3 == 0) {
                this.mHandler.sendEmptyMessage(6);
            }
            if (AdsControllor.obtain().isAdLoaded()) {
                ++LivePlayActivity.sAcessPlayCount;
            }
        }
        Log.d(LivePlayActivity.TAG, "Hardware acceleration mode: " + Integer.toString(this.mLibVLC.getHardwareAcceleration()));
        this.bOnCreateFinish = true;
    }

    protected Dialog onCreateDialog(final int n, final Bundle bundle) {
        switch (n) {
            default: {
                return null;
            }
            case -5:
            case -4:
            case -3:
            case -2:
            case -1:
            case 0: {
                return (Dialog)(this.mWarningDialog = new AlertDialog$Builder((Context)this).setTitle(2131427631).setMessage((CharSequence)this.getSTBReturnMessage(n, bundle)).setPositiveButton(2131427633, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    final /* synthetic */ LivePlayActivity this$0;

                    LivePlayActivity$16() {
                        this.this$0 = this$0;
                        super();
                    }

                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        LivePlayActivity.access$2(this.this$0, false);
                        LivePlayActivity.this.stopLoadingAnimation();
                        if (!this.this$0.mLiveList.isShowing()) {
                            this.this$0.mLiveList.show();
                        }
                    }
                }).setCancelable(false).create());
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(LivePlayActivity.TAG, "onDestroy111");
        if (this.bOnCreateFinish) {
            this.mHandler.removeCallbacksAndMessages((Object)null);
            if (this.mReceiver != null) {
                this.unregisterReceiver(this.mReceiver);
            }
            switch (GMScreenGlobalInfo.getCurStbInfo().getPlatform_id()) {
                case 8:
                case 9: {
                    this.stopStream();
                    break;
                }
            }
            EventHandler.getInstance().removeHandler(this.eventHandler);
            this.mLibVLC.eventVideoPlayerActivityCreated(false);
            if (this.mDisabledHardwareAcceleration) {
                this.mLibVLC.setHardwareAcceleration(this.mPreviousHardwareAccelerationMode);
            }
            this.msgProc.removeMessages(1009);
            this.msgProc.removeMessages(3);
            this.msgProc.recycle();
            if (this.mSubHandler != null) {
                this.mSubHandler.removeCallbacksAndMessages((Object)null);
                this.mSubHandler.getLooper().quit();
            }
        }
        Log.d(LivePlayActivity.TAG, "onDestroy2222");
    }

    public void onEndOfFile() {
        this.stopStream();
        this.finish();
    }

    @Override
    public void onHardwareAccelerationError(final Bundle bundle) {
        this.mLibVLC.stop();
        final AlertDialog create = new AlertDialog$Builder((Context)this).setPositiveButton(17039370, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$18() {
                this.this$0 = this$0;
                super();
            }

            public void onClick(final DialogInterface dialogInterface, final int n) {
                LivePlayActivity.access$41(this.this$0, true);
                LivePlayActivity.access$42(this.this$0, this.this$0.mLibVLC.getHardwareAcceleration());
                this.this$0.mLibVLC.setHardwareAcceleration(0);
                this.this$0.askPlayUrl(this.this$0.mSelectChannelPosition);
            }
        }).setNegativeButton(17039360, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$19() {
                this.this$0 = this$0;
                super();
            }

            public void onClick(final DialogInterface dialogInterface, final int n) {
                this.this$0.finish();
            }
        }).setTitle((CharSequence)"Would you like to disable it and try again?").setMessage((CharSequence)"An error occurred with hardware acceleration.").create();
        if (!this.isFinishing()) {
            create.show();
        }
        this.mViewControl.updateOverlayPausePlay();
    }

    @Override
    public void onMediaPlayerBuffering(final Bundle bundle) {
        final int n = (int)bundle.getFloat("data");
        if (n > 0 && n < 100) {
            this.startLoadingAnimation("Buffering..." + n + "%");
            if (this.mAdView.isShown()) {
                this.mAdView.setVisibility(8);
            }
        }
        else if (n >= 100) {
            this.stopLoadingAnimation();
        }
    }

    @Override
    public void onMediaPlayerEncounteredError(final Bundle bundle) {
        this.stopLoadingAnimation();
        this.bOnErrorHappen = true;
        final AlertDialog create = new AlertDialog$Builder((Context)this).setPositiveButton(17039370, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$17() {
                this.this$0 = this$0;
                super();
            }

            public void onClick(final DialogInterface dialogInterface, final int n) {
                LivePlayActivity.access$2(this.this$0, false);
                LivePlayActivity.this.stopLoadingAnimation();
                if (!this.this$0.mLiveList.isShowing()) {
                    this.this$0.mLiveList.show();
                }
            }
        }).setTitle(2131427631).setMessage(2131427632).create();
        if (!this.isFinishing()) {
            create.show();
        }
        this.mViewControl.updateOverlayPausePlay();
    }

    @Override
    public void onMediaPlayerEndReached(final Bundle bundle) {
        this.mLibVLC.stop();
        this.bPlayEnd = true;
        this.mHandler.postDelayed(this.mPlayEOFOccurred, 3000L);
        this.changeAudioFocus(false);
    }

    @Override
    public void onMediaPlayerPaused(final Bundle bundle) {
        this.showOverlay();
        this.mViewControl.updateOverlayPausePlay();
        this.mHandler.removeMessages(1);
        if (!this.mAdView.isShown() && !this.isPauseByAd && !this.mWaittingView.isShown()) {
            this.mAdView.setVisibility(0);
        }
    }

    @Override
    public void onMediaPlayerPlaying(final Bundle bundle) {
        this.stopLoadingAnimation();
        this.showOverlay();
        if (AdsControllor.obtain().isOpen()) {
            this.pause();
            this.isPauseByAd = true;
        }
        else {
            this.setESTracks();
            this.changeAudioFocus(true);
        }
        this.mViewControl.updateOverlayPausePlay();
        if (this.mAdView.isShown()) {
            this.mAdView.setVisibility(8);
        }
    }

    @Override
    public void onMediaPlayerPositionChanged(final Bundle bundle) {
        this.mViewControl.updateOverlayPausePlay();
    }

    @Override
    public void onMediaPlayerRecordableChanged(final Bundle bundle) {
        this.mViewControl.setRecordVisible(bundle.getBoolean("recordable"));
    }

    @Override
    public void onMediaPlayerStopped(final Bundle bundle) {
        this.showOverlay();
        this.mViewControl.updateOverlayPausePlay();
        this.mHandler.removeMessages(1);
        if (!this.mAdView.isShown() && !this.mWaittingView.isShown()) {
            this.mAdView.setVisibility(0);
        }
        this.changeAudioFocus(false);
    }

    @Override
    public void onMediaPlayerVout(final Bundle bundle) {
        Log.d(LivePlayActivity.TAG, "Vout count " + bundle.getInt("data"));
        if (bundle.getInt("data") == 0) {
            this.startLoadingAnimation(this.getString(2131427520));
        }
    }

    protected void onPause() {
        super.onPause();
        Log.d(LivePlayActivity.TAG, "onPause111");
        if (this.mLibVLC.isPlaying()) {
            if (this.mLibVLC.videoIsRecording()) {
                this.mLibVLC.videoRecordStop();
            }
            Log.d(LivePlayActivity.TAG, "onPause121");
            this.mLibVLC.stop();
            Log.d(LivePlayActivity.TAG, "onPause212");
            this.mSurfaceView.setKeepScreenOn(false);
        }
        if (this.mLiveList.isShowing()) {
            Log.d(LivePlayActivity.TAG, "hide mLiveList");
            this.mLiveList.hide();
        }
        Log.d(LivePlayActivity.TAG, "onPause222");
    }

    @Deprecated
    protected void onPrepareDialog(final int n, final Dialog dialog) {
        super.onPrepareDialog(n, dialog);
        switch (n) {
            default: {}
            case -5:
            case -4:
            case -3:
            case -2:
            case -1:
            case 0: {
                this.bOnErrorHappen = true;
            }
        }
    }

    protected void onResume() {
        super.onResume();
        Log.d(LivePlayActivity.TAG, "onResume");
        if (AdsControllor.obtain().getmStatus() == AdsControllor.AdStatus.CLOSE && this.isPauseByAd) {
            this.play();
            this.isPauseByAd = false;
            this.changeAudioFocus(true);
            this.setESTracks();
        }
    }

    protected void onStart() {
        super.onStart();
        if (AdsControllor.obtain().getmStatus() == AdsControllor.AdStatus.LEFT_APP) {
            AdsControllor.obtain().hideInterstitialAd();
        }
        Log.d(LivePlayActivity.TAG, "onStart");
    }

    protected void onStop() {
        super.onStop();
        Log.d(LivePlayActivity.TAG, "onStop");
    }

    public void onVolumeSlide(final float n) {
        final int streamMaxVolume = this.mAudioManager.getStreamMaxVolume(3);
        final int n2 = -(int)(n / this.mSurfaceYDisplayRange * streamMaxVolume);
        final int n3 = (int)Math.min(Math.max(this.mCurrentVolumn + n2, 0.0f), streamMaxVolume);
        if (this.mVolumeBrightnessLayout.getVisibility() != 0) {
            this.mVolumeBrightnessLayout.setVisibility(0);
        }
        this.mOperationBg.setImageResource(2130838219);
        if (n2 != 0) {
            this.mAudioManager.setStreamVolume(3, n3, 0);
        }
        final ViewGroup$LayoutParams layoutParams = this.mOperationPercent.getLayoutParams();
        layoutParams.width = this.findViewById(2131362180).getLayoutParams().width * n3 / streamMaxVolume;
        this.mOperationPercent.setLayoutParams(layoutParams);
        this.mHandler.removeMessages(4);
        this.mHandler.sendEmptyMessageDelayed(4, 1000L);
    }

    public void pause() {
        if (this.isRecording()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(5, (Object)new Runnable() {
                final /* synthetic */ LivePlayActivity this$0;

                LivePlayActivity$13() {
                    this.this$0 = this$0;
                    super();
                }

                @Override
                public void run() {
                    this.this$0.mLibVLC.stop();
                    this.this$0.mSurfaceView.setKeepScreenOn(false);
                }
            }));
            return;
        }
        this.mLibVLC.pause();
        this.mSurfaceView.setKeepScreenOn(false);
    }

    public void play() {
        this.mLibVLC.play();
        this.mSurfaceView.setKeepScreenOn(true);
    }

    public void recordStart() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm", Locale.US);
        String s = "";
        if (GMScreenGlobalInfo.playType == 2) {
            s = String.valueOf(this.mCurChannel.getProgramName()) + "_" + simpleDateFormat.format(new Date());
        }
        else if (GMScreenGlobalInfo.playType == 1) {
            s = String.valueOf(Pvr2smallData.getInstance().getPvr2smallList().get(this.mSelectChannelPosition).getProgramName()) + "_" + simpleDateFormat.format(new Date());
        }
        final String string = String.valueOf(LivePlayActivity.REC_PATH) + s;
        Log.d(LivePlayActivity.TAG, "send record path : " + string);
        if (this.mLibVLC.videoRecordStart(string)) {
            this.showInfo("Recording started", 3000);
            this.mViewControl.setRecordFlagVisible(true);
            return;
        }
        this.showInfo("Recording start failed", 3000);
    }

    public void recordStop() {
        if (this.mLibVLC.videoRecordStop()) {
            this.showInfo("Recording stoped", 3000);
            this.mViewControl.setRecordFlagVisible(false);
            return;
        }
        this.showInfo("Recording stop failed", 3000);
    }

    @Override
    public void setSurfaceLayout(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        Log.d(LivePlayActivity.TAG, "setSurfaceSize width * height = " + n + "* " + n2);
        if (n * n2 == 0) {
            return;
        }
        this.mViewControl.setSurfaceLayout(n, n2, n3, n4, n5, n6);
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
    }

    public void showChannelList() {
        if (!this.mLiveList.isShowing()) {
            this.mLiveList.show();
        }
    }

    public void showInfo(final int text, final int n) {
        this.mInfo.setVisibility(0);
        this.mInfo.setText(text);
        this.mHandler.removeMessages(3);
        this.mHandler.sendEmptyMessageDelayed(3, (long)n);
    }

    public void showInfo(final String text) {
        this.mInfo.setVisibility(0);
        this.mInfo.setText((CharSequence)text);
        this.mHandler.removeMessages(3);
    }

    public void showInfo(final String text, final int n) {
        this.mInfo.setVisibility(0);
        this.mInfo.setText((CharSequence)text);
        this.mHandler.removeMessages(3);
        this.mHandler.sendEmptyMessageDelayed(3, (long)n);
    }

    public void showOverlay() {
        this.showOverlay(4000);
    }

    public void showOverlay(final int n) {
        this.mViewControl.show();
        if (n != 0) {
            this.mHandler.removeMessages(1);
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1), (long)n);
        }
    }

    public void showRecordStopTips(final Runnable runnable) {
        (this.mWarningDialog = new AlertDialog$Builder((Context)this).setTitle((CharSequence)"Stop Record").setMessage((CharSequence)"Do you want stop recording ?").setPositiveButton((CharSequence)"Yes", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            final /* synthetic */ LivePlayActivity this$0;
            private final /* synthetic */ Runnable val$positiveRun;

            LivePlayActivity$14() {
                this.this$0 = this$0;
                super();
            }

            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
                this.this$0.recordStop();
                if (runnable != null) {
                    runnable.run();
                }
            }
        }).setNegativeButton((CharSequence)"No", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            final /* synthetic */ LivePlayActivity this$0;

            LivePlayActivity$15() {
                this.this$0 = this$0;
                super();
            }

            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
            }
        }).setCancelable(false).create()).show();
    }

    public void snapShot() {
        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm", Locale.US);
            String s = "";
            if (GMScreenGlobalInfo.playType == 2) {
                s = String.valueOf(this.mCurChannel.getProgramName()) + "_" + simpleDateFormat.format(new Date());
            }
            else if (GMScreenGlobalInfo.playType == 1) {
                s = String.valueOf(Pvr2smallData.getInstance().getPvr2smallList().get(this.mSelectChannelPosition).getProgramName()) + "_" + simpleDateFormat.format(new Date());
            }
            final String string = String.valueOf(LivePlayActivity.SNAPSHOT_PATH) + s + ".png";
            final File file = new File(string);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (this.mLibVLC.takeSnapShot(string, 640, 360)) {
                this.showInfo("Save snapshot", 3000);
                return;
            }
            this.showInfo("Save snapshot fail", 3000);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class UIHandler extends WeakHandler<LivePlayActivity>
    {
        public UIHandler(final LivePlayActivity livePlayActivity) {
            super(livePlayActivity);
        }

        public void handleMessage(final Message message) {
            final LivePlayActivity livePlayActivity = this.getOwner();
            if (livePlayActivity == null) {
                return;
            }
            switch (message.what) {
                default: {}
                case 1: {
                    livePlayActivity.hideOverlay();
                }
                case 2: {
                    livePlayActivity.mViewControl.changeSurfaceSize();
                    livePlayActivity.mLiveList.setViewSize();
                }
                case 3: {
                    livePlayActivity.fadeOutInfo();
                }
                case 4: {
                    livePlayActivity.mVolumeBrightnessLayout.setVisibility(8);
                }
                case 5: {
                    livePlayActivity.showRecordStopTips((Runnable)message.obj);
                }
                case 6: {
                    AdsControllor.obtain().showInterstitialAd(5);
                }
            }
        }
    }
}
