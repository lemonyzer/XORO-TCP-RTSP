package mktvsmart.screen.channel.Luyten;

import com.voicetechnology.rtspclient.test.*;
import android.app.*;
import android.view.inputmethod.*;
import android.net.*;
import mktvsmart.screen.message.process.*;
import java.net.*;
import mktvsmart.screen.view.*;
import mktvsmart.screen.util.*;
import com.alitech.dvbtoip.*;
import android.text.*;
import mktvsmart.screen.dataconvert.parser.*;
import android.content.*;
import mktvsmart.screen.vlc.*;
import mktvsmart.screen.exception.*;
import android.util.*;
import mktvsmart.screen.dataconvert.model.*;
import mktvsmart.screen.*;
import android.telephony.*;
import mktvsmart.screen.gchat.ui.*;
import android.os.*;
import java.util.*;
import android.graphics.drawable.*;
import android.graphics.*;
import java.io.*;
import android.view.*;
import android.content.res.*;
import android.text.format.*;
import android.database.*;
import android.widget.*;

public class GsChannelListActivity extends Activity implements OnTabActivityResultListener
{
    private static final int ALL_ADJACENT_MATCH_CHANNEL_NAME_PRIORITY = -1;
    private static final int MATCH_CHANNEL_DISP_INDEX_PRIORITY = -2;
    private static final int NO_ADJACENT_MATCH_CHANNEL_NAME_PRIORITY = 1;
    private static final int SEARCH_BY_DISP_INDEX_AND_PROG_NAME = 0;
    private static final int SEARCH_BY_PROG_NAME = 1;
    public static boolean enable_edit;
    private static Sat2IP_Rtsp sRtsp;
    private static final Collection<String> targetApplications;
    private ListView ChannelListView;
    private Button DoneBtn;
    private final double M_BIG_SEPARATION_DISTANCE;
    private final int M_DOUBLE_CLICK_TIME_INTERVAL;
    private final double M_EDIT_SEARCH_FRAGMENT_WIDTH_PROPORTION;
    private final int M_FIRST_CLICK;
    private final double M_SEARCH_CANCEL_LAYOUT_WIDTH_PROPORTION;
    private final double M_SEARCH_FRAGMENT_WIDTH_PROPORTION;
    private final int M_SECOND_CLICK;
    private final double M_SMALL_SEPARATION_DISTANCE;
    private String NetAddress;
    private int NetPort;
    private final int PLAY_FAILURE;
    private final int PLAY_TIMEOUT;
    private final int PLAY_WITH_OTHER_PLAYER;
    private Button TypeSwitch;
    private ImageView allSelectedBtn;
    private LinearLayout allSelectedBtnLayout;
    private boolean allSelectedBtn_selected;
    private boolean bPlayWithOherPlayer;
    AlertDialog$Builder build;
    private list_single_button_adapter channelListAdapter;
    private SparseIntArray channelListTypeMap;
    private ImageView channelTypeArrow;
    int channelTypeFavMark;
    private PopupWindow channelTypePopupWindow;
    CheckBox childFav;
    private List<DataConvertControlModel> controlModels;
    CheckBox cultureFav;
    private int currentChannelListType;
    private String currentSat2ipChannelProgramId;
    private byte[] dataBuff;
    Dialog deleteChannelDialog;
    private Button editBtn;
    private ImageView editDeleteIcon;
    private LinearLayout editDeleteMenu;
    private TextView editDeleteText;
    private ImageView editFavorIcon;
    private LinearLayout editFavorMenu;
    private TextView editFavorText;
    private final int editLockChannel;
    private ImageView editLockIcon;
    private LinearLayout editLockMenu;
    private TextView editLockText;
    private LinearLayout editMenu;
    private ImageView editMoveIcon;
    private LinearLayout editMoveMenu;
    private TextView editMoveText;
    private ImageView editRenameIcon;
    private LinearLayout editRenameMenu;
    private TextView editRenameText;
    CheckBox eduaFav;
    private String epg_program_name;
    private String epg_program_sat_tp_id;
    private int expandPosition;
    int favMark;
    private List<DataConvertChannelModel> favorModels;
    InputStream in;
    private InputMethodManager inputManager;
    private boolean isEnableMove;
    boolean isFavorChange;
    private boolean isInForeground;
    boolean isSat2ipStarted;
    private int lastItem;
    private int longClickPos;
    int loop;
    private ChannelData mChannelData;
    private boolean mChannelListChangeFlag;
    private int mChannelListClickCount;
    private ImageView mClearSearchKeyword;
    private List<DataConvertChannelModel> mCurrentChannelList;
    private CommonCofirmDialog.OnButtonClickListener mDeleteMenuOnClickListener;
    private CommonCofirmDialog.OnButtonClickListener mDownDialogOnClickListener;
    private ImageView mEditSortIcon;
    private LinearLayout mEditSortMenu;
    private TextView mEditSortText;
    private boolean mEnterSearchFlag;
    private grid_adapter mFavGridAdater;
    private int mFavValue;
    private int[] mFavValueArray;
    private Dialog mFavorRenameDialog;
    private int mFirstChannelListClickTime;
    private int mFirstVisibleChannelIdex;
    private boolean mGetChannelListWhenLogin;
    private boolean[] mIsChoice;
    private int mLastVisibleChannelIndex;
    private Handler$Callback mMsgHandle;
    private List<DataConvertChannelModel> mOriginalChannelListModels;
    private MyPhoneStateListener mPhoneListener;
    private FindPlayerAndPlayChannel.PlayByDesignatedPlayer mPlayByDesignatedPlayer;
    private Intent mPlayIntent;
    private View$OnClickListener mRenameMenuOnClickListener;
    private ContentResolver mResolver;
    private SMSContentObserver mSMSContentObserver;
    private Button mSearchCancelBtn;
    private EditText mSearchChannelEdit;
    private String mSearchChannelKeywords;
    private LinearLayout mSearchChannelLayout;
    private List<DataConvertChannelModel> mSearchChannelListModels;
    private LinearLayout mSearchFailedPrompt;
    private int mSearchMode;
    private int mSecChannelListClickTime;
    private sort_adapter mSortAdapter;
    private Dialog mSortTypePopupWindow;
    private CommonCofirmDialog.OnButtonClickListener mStbInStandbyOnClickListener;
    private Uri mUri;
    private Handler mainHandler;
    private final int mobilePlayLockChannel;
    CheckBox moviesFav;
    private MessageProcessor msgProc;
    CheckBox musFav;
    CheckBox newsFav;
    private DataParser parser;
    private int passwordType;
    private final int playingLockChannel;
    private MessageProcessor.PerformOnBackground post;
    private int preChannelListType;
    Dialog pswInputDialog;
    Dialog renameInputDialog;
    private boolean repeatPassword;
    private MessageProcessor.PerformOnForeground requestAllChannelWhenSTBChannelListChanged;
    int sat2ipPlayPosition;
    CheckBox sportsFav;
    private TabHost tabHost;
    private TabWidget tabWidget;
    private Socket tcpSocket;
    Runnable timeOutRun;
    private TextView titleText;
    private ListviewAdapter typeAdapter;
    private int visibleItemCount;
    private ADSProgressDialog waitDialog;
    CheckBox weatherFav;

    static {
        targetApplications = list("com.mxtech.videoplayer.ad");
        GsChannelListActivity.enable_edit = false;
    }

    public GsChannelListActivity() {
        this.M_DOUBLE_CLICK_TIME_INTERVAL = 1000;
        this.M_FIRST_CLICK = 1;
        this.M_SECOND_CLICK = 2;
        this.M_EDIT_SEARCH_FRAGMENT_WIDTH_PROPORTION = 0.72;
        this.M_SEARCH_CANCEL_LAYOUT_WIDTH_PROPORTION = 0.18;
        this.M_SEARCH_FRAGMENT_WIDTH_PROPORTION = 0.9;
        this.M_BIG_SEPARATION_DISTANCE = 0.05;
        this.M_SMALL_SEPARATION_DISTANCE = 0.03;
        this.mSearchChannelKeywords = "";
        this.channelListAdapter = null;
        this.typeAdapter = null;
        this.favorModels = null;
        this.controlModels = null;
        this.mCurrentChannelList = null;
        this.mOriginalChannelListModels = null;
        this.mSearchChannelListModels = null;
        this.currentChannelListType = 0;
        this.preChannelListType = 0;
        this.expandPosition = -1;
        this.favMark = 0;
        this.channelTypeFavMark = 0;
        this.isFavorChange = true;
        this.isSat2ipStarted = false;
        this.currentSat2ipChannelProgramId = "";
        this.loop = 0;
        this.sat2ipPlayPosition = 0;
        this.lastItem = 0;
        this.isEnableMove = false;
        this.longClickPos = -1;
        this.allSelectedBtn_selected = false;
        this.inputManager = null;
        this.repeatPassword = false;
        this.editLockChannel = 1;
        this.playingLockChannel = 2;
        this.mobilePlayLockChannel = 3;
        this.passwordType = 0;
        this.isInForeground = false;
        this.mSortAdapter = null;
        this.mGetChannelListWhenLogin = true;
        this.mFirstVisibleChannelIdex = 0;
        this.mLastVisibleChannelIndex = 0;
        this.mSearchMode = 0;
        this.mEnterSearchFlag = false;
        this.mChannelListChangeFlag = false;
        this.mFavGridAdater = null;
        this.mFavValueArray = new int[] { 1, 2, 4, 8, 16, 32, 64, 128 };
        this.mFavValue = 0;
        this.PLAY_TIMEOUT = 2;
        this.PLAY_WITH_OTHER_PLAYER = 3;
        this.PLAY_FAILURE = 4;
        this.bPlayWithOherPlayer = false;
        this.mFirstChannelListClickTime = 0;
        this.mSecChannelListClickTime = 0;
        this.mChannelListClickCount = 0;
        this.mPhoneListener = new MyPhoneStateListener((MyPhoneStateListener)null);
        this.mSMSContentObserver = new SMSContentObserver();
        this.mMsgHandle = (Handler$Callback)new Handler$Callback() {
            public boolean handleMessage(final Message message) {
                switch (message.what) {
                    default: {
                        return true;
                    }
                    case 2: {
                        GsChannelListActivity.this.stopStream();
                        Toast.makeText((Context)GsChannelListActivity.this, 2131427637, 0).show();
                        return true;
                    }
                    case 3: {
                        if (GsChannelListActivity.this.waitDialog.isShowing()) {
                            GsChannelListActivity.this.waitDialog.dismiss();
                        }
                        while (true) {
                            try {
                                GsChannelListActivity.this.startActivity((Intent)message.obj);
                                Toast.makeText((Context)GsChannelListActivity.this.getParent(), 2131427593, 1).show();
                                return GsChannelListActivity.this.isSat2ipStarted = true;
                            }
                            catch (ActivityNotFoundException ex) {
                                System.out.println("Player activity not found");
                                GsChannelListActivity.this.stopStream();
                                GsChannelListActivity.this.mainHandler.sendMessage(GsChannelListActivity.this.mainHandler.obtainMessage(4, (Object)"Player not found"));
                                continue;
                            }
                            break;
                        }
                    }
                    case 4: {
                        if (GsChannelListActivity.this.waitDialog.isShowing()) {
                            GsChannelListActivity.this.waitDialog.dismiss();
                        }
                        final CommonErrorDialog commonErrorDialog = new CommonErrorDialog((Context)GsChannelListActivity.this.getParent());
                        commonErrorDialog.setmContent((String)message.obj);
                        commonErrorDialog.show();
                        return true;
                    }
                }
            }
        };
        this.timeOutRun = new Runnable() {
            @Override
            public void run() {
                GsChannelListActivity.this.mainHandler.sendEmptyMessage(2);
            }
        };
        this.mDownDialogOnClickListener = new CommonCofirmDialog.OnButtonClickListener() {
            @Override
            public void onClickedCancel() {
            }

            @Override
            public void onClickedConfirm() {
                final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.mxtech.videoplayer.ad"));
                try {
                    GsChannelListActivity.this.startActivity(intent);
                }
                catch (ActivityNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };
        this.mPlayByDesignatedPlayer = new FindPlayerAndPlayChannel.PlayByDesignatedPlayer() {
            @Override
            public void designatedBuiltInPlayer(final int n) {
                GsChannelListActivity.this.otherPlatformPlay(n);
            }

            @Override
            public void designatedExternalPlayer(final int n, final Intent intent) {
                GsChannelListActivity.this.startPlayStream(n, intent);
            }

            @Override
            public void playerNotExist() {
                final CommonCofirmDialog commonCofirmDialog = new CommonCofirmDialog((Context)GsChannelListActivity.this.getParent());
                commonCofirmDialog.setmTitle(GsChannelListActivity.this.getResources().getString(2131427589));
                commonCofirmDialog.setmContent(GsChannelListActivity.this.getResources().getString(2131427590));
                commonCofirmDialog.setOnButtonClickListener(GsChannelListActivity.this.mDownDialogOnClickListener);
                commonCofirmDialog.show();
            }
        };
        this.post = new MessageProcessor.PerformOnBackground() {
            @Override
            public void doInBackground(final Message message) {
                int n = 9999;
                switch (message.what) {
                    case 2001: {
                        n = 3;
                        break;
                    }
                    case 2004: {
                        n = 17;
                        break;
                    }
                    case 2009: {
                        n = 23;
                        break;
                    }
                    case 2013: {
                        n = 12;
                        break;
                    }
                    case 2019: {
                        n = 22;
                        break;
                    }
                }
                GsSendSocket.sendOnlyCommandSocketToStb(GsChannelListActivity.this.tcpSocket, n);
            }
        };
        this.requestAllChannelWhenSTBChannelListChanged = new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                switch (message.what) {
                    case 2025: {
                        if (GMScreenGlobalInfo.isSdsOpen() != 1) {
                            GMScreenGlobalInfo.setSdsOpen(1);
                            break;
                        }
                        return;
                    }
                    case 2026: {
                        if (GMScreenGlobalInfo.isSdsOpen() != 0) {
                            GMScreenGlobalInfo.setSdsOpen(0);
                            break;
                        }
                        return;
                    }
                }
                GsChannelListActivity.this.mChannelData.clearTVRadioProgramList();
                GsChannelListActivity.this.requestProgramListFromTo(0, GMScreenGlobalInfo.getMaxProgramNumPerRequest() - 1);
            }
        };
        this.mStbInStandbyOnClickListener = new CommonCofirmDialog.OnButtonClickListener() {
            @Override
            public void onClickedCancel() {
            }

            @Override
            public void onClickedConfirm() {
                GsSendSocket.sendOnlyCommandSocketToStb(GsChannelListActivity.this.tcpSocket, 1041);
            }
        };
        this.mDeleteMenuOnClickListener = new CommonCofirmDialog.OnButtonClickListener() {
            @Override
            public void onClickedCancel() {
            }

            @Override
            public void onClickedConfirm() {
                while (true) {
                    try {
                        final ArrayList<DataConvertChannelModel> list = new ArrayList<DataConvertChannelModel>();
                        if (!GsChannelListActivity.enable_edit && GsChannelListActivity.this.expandPosition != -1) {
                            list.add(GsChannelListActivity.this.mCurrentChannelList.get(GsChannelListActivity.this.expandPosition));
                        }
                        else {
                            for (final DataConvertChannelModel dataConvertChannelModel : GsChannelListActivity.this.mCurrentChannelList) {
                                if (dataConvertChannelModel.getSelectedFlag()) {
                                    list.add(dataConvertChannelModel);
                                }
                            }
                        }
                        final byte[] bytes = GsChannelListActivity.this.parser.serialize(list, 1002).getBytes("UTF-8");
                        GsChannelListActivity.this.tcpSocket.setSoTimeout(3000);
                        GsSendSocket.sendSocketToStb(bytes, GsChannelListActivity.this.tcpSocket, 0, bytes.length, 1002);
                        GsChannelListActivity.this.initItemChecked();
                        GsChannelListActivity.this.allSelectedBtn.setImageResource(2130837969);
                        GsChannelListActivity.access$14(GsChannelListActivity.this, false);
                        GsChannelListActivity.this.channelListAdapter.notifyDataSetChanged();
                        if (GsChannelListActivity.this.waitDialog.isShowing()) {
                            GsChannelListActivity.this.waitDialog.dismiss();
                        }
                        GsChannelListActivity.access$16(GsChannelListActivity.this, DialogBuilder.showProgressDialog(GsChannelListActivity.this.getParent(), 2131427516, 2131427520, false, GMScreenGlobalInfo.getmWaitDialogTimeOut(), 2131427638));
                        GsChannelListActivity.this.allSelectedBtn.setImageResource(2130837969);
                        GsChannelListActivity.access$14(GsChannelListActivity.this, false);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        continue;
                    }
                    break;
                }
            }
        };
        this.mRenameMenuOnClickListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final DataConvertChannelModel dataConvertChannelModel = null;
                DataConvertChannelModel dataConvertChannelModel2;
                if (!GsChannelListActivity.enable_edit && GsChannelListActivity.this.expandPosition != -1) {
                    dataConvertChannelModel2 = GsChannelListActivity.this.mCurrentChannelList.get(GsChannelListActivity.this.expandPosition);
                }
                else {
                    final Iterator iterator = GsChannelListActivity.this.mCurrentChannelList.iterator();
                    do {
                        dataConvertChannelModel2 = dataConvertChannelModel;
                        if (!iterator.hasNext()) {
                            break;
                        }
                        dataConvertChannelModel2 = iterator.next();
                    } while (!dataConvertChannelModel2.getSelectedFlag());
                }
                final LinearLayout contentView = (LinearLayout)LayoutInflater.from(GsChannelListActivity.this.findViewById(2131362018).getRootView().getContext()).inflate(2130903118, (ViewGroup)null);
                final EditText editText = (EditText)contentView.findViewById(2131362148);
                final Button button = (Button)contentView.findViewById(2131362149);
                final Button button2 = (Button)contentView.findViewById(2131362150);
                editText.setText((CharSequence)dataConvertChannelModel2.getProgramName());
                Selection.selectAll((Spannable)editText.getText());
                button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        while (true) {
                            try {
                                final DataConvertChannelModel val$channelModel = dataConvertChannelModel2;
                                val$channelModel.setProgramName(editText.getText().toString());
                                final ArrayList<DataConvertChannelModel> list = new ArrayList<DataConvertChannelModel>();
                                list.add(val$channelModel);
                                final byte[] bytes = GsChannelListActivity.this.parser.serialize(list, 1001).getBytes("UTF-8");
                                GsChannelListActivity.this.tcpSocket.setSoTimeout(3000);
                                GsSendSocket.sendSocketToStb(bytes, GsChannelListActivity.this.tcpSocket, 0, bytes.length, 1001);
                                GsChannelListActivity.this.initItemChecked();
                                GsChannelListActivity.this.channelListAdapter.notifyDataSetChanged();
                                GsChannelListActivity.this.inputManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                                GsChannelListActivity.this.renameInputDialog.dismiss();
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                                continue;
                            }
                            break;
                        }
                    }
                });
                button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        GsChannelListActivity.this.inputManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        GsChannelListActivity.this.renameInputDialog.dismiss();
                    }
                });
                (GsChannelListActivity.this.renameInputDialog = new Dialog(GsChannelListActivity.this.findViewById(2131362016).getRootView().getContext(), 2131493009)).setContentView((View)contentView);
                GsChannelListActivity.this.renameInputDialog.setCanceledOnTouchOutside(false);
                GsChannelListActivity.this.renameInputDialog.show();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        GsChannelListActivity.access$18(GsChannelListActivity.this, (InputMethodManager)editText.getContext().getSystemService("input_method"));
                        GsChannelListActivity.this.inputManager.showSoftInput((View)editText, 0);
                    }
                }, 200L);
            }
        };
    }

    private void ChangeChannelListType(final int n, final boolean b) {
        if (n != this.preChannelListType || b) {
            final List<DataConvertChannelModel> channelListByProgramType = this.mChannelData.getChannelListByProgramType(this.mChannelData.getChannelListByTvRadioType(), n);
            if (channelListByProgramType != null) {
                this.mCurrentChannelList = channelListByProgramType;
                this.setCurrentChannelListDispIndex();
                this.mOriginalChannelListModels = this.mCurrentChannelList;
                if (this.mEnterSearchFlag) {
                    this.mChannelListChangeFlag = true;
                    this.findChannel();
                }
                else {
                    if (this.ChannelListView.getAdapter() != null && this.ChannelListView.getAdapter() instanceof list_single_button_adapter) {
                        ((list_single_button_adapter)this.ChannelListView.getAdapter()).notifyDataSetChanged();
                    }
                    this.ChannelListView.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            GsChannelListActivity.this.adjustSelectionOfChannelListView(true);
                        }
                    });
                }
                switch (GMScreenGlobalInfo.getCurStbPlatform()) {
                    default: {
                        if (n >= 0 && n <= 3) {
                            this.titleText.setText((CharSequence)this.getResources().getStringArray(2131558412)[n]);
                            return;
                        }
                        if (n >= 4 && n <= 11) {
                            this.titleText.setText((CharSequence)GMScreenGlobalInfo.favType.get(n - 4));
                            return;
                        }
                        break;
                    }
                    case 30:
                    case 31:
                    case 32:
                    case 71:
                    case 72:
                    case 74: {
                        if (n >= 0 && n <= 3) {
                            this.titleText.setText((CharSequence)this.getResources().getStringArray(2131558412)[n]);
                            return;
                        }
                        if (n >= 4) {
                            for (final DataConvertFavorModel dataConvertFavorModel : GMScreenGlobalInfo.favGroups) {
                                if (dataConvertFavorModel.getFavorTypeID() == n - 4) {
                                    this.titleText.setText((CharSequence)dataConvertFavorModel.GetFavorName());
                                }
                            }
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }

    private int GetSelectedNum(final List<DataConvertChannelModel> list) {
        int n = 0;
        final Iterator<DataConvertChannelModel> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getSelectedFlag()) {
                ++n;
            }
        }
        return n;
    }

    private void LoadData() {
        this.ChannelListView.setSelection(this.lastItem - this.visibleItemCount + 1);
    }

    private void abtainPlayUrl(final int n, final Intent intent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (GMScreenGlobalInfo.getCurStbPlatform()) {
                    default: {
                        GMScreenGlobalInfo.playType = 2;
                        GsChannelListActivity.this.sendSat2ipChannelRequestToStb(n);
                        GsChannelListActivity.access$73(GsChannelListActivity.this, intent);
                        break;
                    }
                    case 8:
                    case 9: {
                        GsChannelListActivity.access$70(new Sat2IP_Rtsp());
                        final String rtspUriBase = Sat2ipUtil.getRtspUriBase(GsChannelListActivity.this.tcpSocket.getInetAddress().toString());
                        final String rtspUriQuery = Sat2ipUtil.getRtspUriQuery(GsChannelListActivity.this.mCurrentChannelList.get(n));
                        GMScreenGlobalInfo.playType = 2;
                        if (!GsChannelListActivity.sRtsp.setup_blocked(rtspUriBase, rtspUriQuery)) {
                            if (((DataConvertChannelModel)GsChannelListActivity.this.channelListAdapter.getItem(n)).GetIsProgramScramble() == 0) {
                                GsChannelListActivity.this.mainHandler.sendMessage(GsChannelListActivity.this.mainHandler.obtainMessage(4, (Object)GsChannelListActivity.this.getString(2131427592)));
                            }
                            GsChannelListActivity.access$70(null);
                            return;
                        }
                        GsChannelListActivity.this.sendSat2ipChannelRequestToStb(n);
                        DVBtoIP.initResourceForPlayer(GsChannelListActivity.sRtsp.get_rtp_port(), FindPlayerAndPlayChannel.getRtspPipeFilePath((Context)GsChannelListActivity.this), 2);
                        intent.setDataAndType(Uri.parse("file://" + FindPlayerAndPlayChannel.getRtspPipeFilePath((Context)GsChannelListActivity.this)), "video/*");
                        GsChannelListActivity.this.mainHandler.sendMessage(GsChannelListActivity.this.mainHandler.obtainMessage(3, (Object)intent));
                        break;
                    }
                }
                GsChannelListActivity.access$74(GsChannelListActivity.this, true);
            }
        }).start();
    }

    static /* synthetic */ void access$14(final GsChannelListActivity gsChannelListActivity, final boolean allSelectedBtn_selected) {
        gsChannelListActivity.allSelectedBtn_selected = allSelectedBtn_selected;
    }

    static /* synthetic */ void access$16(final GsChannelListActivity gsChannelListActivity, final ADSProgressDialog waitDialog) {
        gsChannelListActivity.waitDialog = waitDialog;
    }

    static /* synthetic */ void access$18(final GsChannelListActivity gsChannelListActivity, final InputMethodManager inputManager) {
        gsChannelListActivity.inputManager = inputManager;
    }

    static /* synthetic */ void access$40(final GsChannelListActivity gsChannelListActivity, final int expandPosition) {
        gsChannelListActivity.expandPosition = expandPosition;
    }

    static /* synthetic */ void access$42(final GsChannelListActivity gsChannelListActivity, final String epg_program_sat_tp_id) {
        gsChannelListActivity.epg_program_sat_tp_id = epg_program_sat_tp_id;
    }

    static /* synthetic */ void access$43(final GsChannelListActivity gsChannelListActivity, final String epg_program_name) {
        gsChannelListActivity.epg_program_name = epg_program_name;
    }

    static /* synthetic */ void access$44(final GsChannelListActivity gsChannelListActivity, final DataParser parser) {
        gsChannelListActivity.parser = parser;
    }

    static /* synthetic */ void access$49(final GsChannelListActivity gsChannelListActivity, final int passwordType) {
        gsChannelListActivity.passwordType = passwordType;
    }

    static /* synthetic */ void access$52(final GsChannelListActivity gsChannelListActivity, final boolean repeatPassword) {
        gsChannelListActivity.repeatPassword = repeatPassword;
    }

    static /* synthetic */ void access$55(final GsChannelListActivity gsChannelListActivity, final int longClickPos) {
        gsChannelListActivity.longClickPos = longClickPos;
    }

    static /* synthetic */ void access$57(final GsChannelListActivity gsChannelListActivity, final int mFirstVisibleChannelIdex) {
        gsChannelListActivity.mFirstVisibleChannelIdex = mFirstVisibleChannelIdex;
    }

    static /* synthetic */ void access$58(final GsChannelListActivity gsChannelListActivity, final int mLastVisibleChannelIndex) {
        gsChannelListActivity.mLastVisibleChannelIndex = mLastVisibleChannelIndex;
    }

    static /* synthetic */ void access$63(final GsChannelListActivity gsChannelListActivity, final int preChannelListType) {
        gsChannelListActivity.preChannelListType = preChannelListType;
    }

    static /* synthetic */ void access$65(final GsChannelListActivity gsChannelListActivity, final int currentChannelListType) {
        gsChannelListActivity.currentChannelListType = currentChannelListType;
    }

    static /* synthetic */ void access$70(final Sat2IP_Rtsp sRtsp) {
        GsChannelListActivity.sRtsp = sRtsp;
    }

    static /* synthetic */ void access$73(final GsChannelListActivity gsChannelListActivity, final Intent mPlayIntent) {
        gsChannelListActivity.mPlayIntent = mPlayIntent;
    }

    static /* synthetic */ void access$74(final GsChannelListActivity gsChannelListActivity, final boolean bPlayWithOherPlayer) {
        gsChannelListActivity.bPlayWithOherPlayer = bPlayWithOherPlayer;
    }

    static /* synthetic */ void access$75(final GsChannelListActivity gsChannelListActivity, final List mCurrentChannelList) {
        gsChannelListActivity.mCurrentChannelList = (List<DataConvertChannelModel>)mCurrentChannelList;
    }

    static /* synthetic */ void access$80(final GsChannelListActivity gsChannelListActivity, final List mOriginalChannelListModels) {
        gsChannelListActivity.mOriginalChannelListModels = (List<DataConvertChannelModel>)mOriginalChannelListModels;
    }

    static /* synthetic */ void access$81(final GsChannelListActivity gsChannelListActivity, final boolean mChannelListChangeFlag) {
        gsChannelListActivity.mChannelListChangeFlag = mChannelListChangeFlag;
    }

    static /* synthetic */ void access$83(final GsChannelListActivity gsChannelListActivity, final list_single_button_adapter channelListAdapter) {
        gsChannelListActivity.channelListAdapter = channelListAdapter;
    }

    static /* synthetic */ void access$85(final GsChannelListActivity gsChannelListActivity, final boolean mGetChannelListWhenLogin) {
        gsChannelListActivity.mGetChannelListWhenLogin = mGetChannelListWhenLogin;
    }

    static /* synthetic */ void access$89(final GsChannelListActivity gsChannelListActivity, final List controlModels) {
        gsChannelListActivity.controlModels = (List<DataConvertControlModel>)controlModels;
    }

    static /* synthetic */ void access$91(final GsChannelListActivity gsChannelListActivity, final sort_adapter mSortAdapter) {
        gsChannelListActivity.mSortAdapter = mSortAdapter;
    }

    static /* synthetic */ void access$94(final GsChannelListActivity gsChannelListActivity, final byte[] dataBuff) {
        gsChannelListActivity.dataBuff = dataBuff;
    }

    static /* synthetic */ void access$96(final GsChannelListActivity gsChannelListActivity, final Dialog mSortTypePopupWindow) {
        gsChannelListActivity.mSortTypePopupWindow = mSortTypePopupWindow;
    }

    private void adjustSelectionOfChannelListView(final boolean b) {
        int n = 0;
        final Iterator<DataConvertChannelModel> iterator = this.mCurrentChannelList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getIsPlaying() == 1) {
                if (b) {
                    if (n > 5) {
                        this.ChannelListView.setSelection(n - 3);
                        break;
                    }
                    this.ChannelListView.setSelection(0);
                    break;
                }
                else {
                    if (n >= this.mFirstVisibleChannelIdex && n <= this.mLastVisibleChannelIndex) {
                        break;
                    }
                    if (n > this.mLastVisibleChannelIndex - this.mFirstVisibleChannelIdex) {
                        this.ChannelListView.setSelection(n - (this.mLastVisibleChannelIndex - this.mFirstVisibleChannelIdex) / 2);
                        break;
                    }
                    this.ChannelListView.setSelection(0);
                    break;
                }
            }
            else {
                ++n;
            }
        }
        if (n == this.mCurrentChannelList.size()) {
            this.ChannelListView.setSelection(0);
        }
    }

    private int calculateChannelInsertPosition(final DataConvertChannelModel dataConvertChannelModel, final List<DataConvertChannelModel> list) {
        int n = 0;
        DataConvertChannelModel dataConvertChannelModel2;
        int j;
        int n3;
        int n2;
        Label_0016:Label_0093:
        for (int i = 0; i < list.size(); ++i) {
            switch (dataConvertChannelModel.getSearchChannelSortPriority()) {
                default: {
                    if (-2 == list.get(i).getSearchChannelSortPriority() || -1 == list.get(i).getSearchChannelSortPriority()) {
                        ++n;
                        break;
                    }
                    if (dataConvertChannelModel.getSearchChannelSortPriority() > list.get(i).getSearchChannelSortPriority()) {
                        return n;
                    }
                    if (dataConvertChannelModel.getSearchChannelSortPriority() != list.get(i).getSearchChannelSortPriority()) {
                        ++n;
                        break;
                    }
                    if (dataConvertChannelModel.getMatchChannelNameIndexArray()[0] < list.get(i).getMatchChannelNameIndexArray()[0]) {
                        return n;
                    }
                    ++n;
                    break;
                }
                case -2: {
                    if (-2 == list.get(i).getSearchChannelSortPriority()) {
                        n = i + 1;
                        break;
                    }
                    return n;
                }
                case -1: {
                    if (-2 == list.get(i).getSearchChannelSortPriority()) {
                        ++n;
                        break;
                    }
                    if (-1 != list.get(i).getSearchChannelSortPriority()) {
                        return n;
                    }
                    n = i;
                    if (dataConvertChannelModel.getMatchChannelNameIndexArray()[0] >= list.get(i).getMatchChannelNameIndexArray()[0]) {
                        n = i + 1;
                        break;
                    }
                    break Label_0016;
                }
                case 1: {
                    dataConvertChannelModel2 = list.get(list.size() - i - 1);
                    if (1 == dataConvertChannelModel2.getSearchChannelSortPriority()) {
                        j = 0;
                        while (true) {
                            while (j < dataConvertChannelModel.getMatchChannelNameIndexArray().length) {
                                if (dataConvertChannelModel.getMatchChannelNameIndexArray()[j] > dataConvertChannelModel2.getMatchChannelNameIndexArray()[j]) {
                                    return list.size() - n;
                                }
                                if (dataConvertChannelModel.getMatchChannelNameIndexArray()[j] < dataConvertChannelModel2.getMatchChannelNameIndexArray()[j]) {
                                    n2 = (n3 = n + 1);
                                    if (i == list.size() - 1) {
                                        return list.size() - n2;
                                    }
                                    n = n3;
                                    if (j == dataConvertChannelModel.getMatchChannelNameIndexArray().length) {
                                        return list.size() - n3;
                                    }
                                    continue Label_0093;
                                }
                                else {
                                    ++j;
                                }
                            }
                            n3 = n;
                            continue;
                        }
                    }
                    return list.size() - n;
                }
            }
        }
        return n;
    }

    private int clickPosition2ListType(int n) {
        int n2 = -1;
        int channelTypeFavMark = this.channelTypeFavMark;
        switch (GMScreenGlobalInfo.getCurStbPlatform()) {
            default: {
                if (DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() == 0) {
                    if (n >= 0 && n <= 3) {
                        n2 = n;
                    }
                    else if (n >= 4) {
                        int n3 = n - 3;
                        n = 3;
                        while (true) {
                            n2 = n;
                            if (n3 <= 0) {
                                break;
                            }
                            int n4 = n3;
                            if ((channelTypeFavMark & 0x1) > 0) {
                                n4 = n3 - 1;
                            }
                            channelTypeFavMark >>>= 1;
                            ++n;
                            n3 = n4;
                        }
                    }
                }
                else {
                    if (n >= 0 && n < 3) {
                        return n;
                    }
                    if (n >= 3) {
                        int n5 = n - 2;
                        n = 3;
                        while (true) {
                            n2 = n;
                            if (n5 <= 0) {
                                break;
                            }
                            int n6 = n5;
                            if ((channelTypeFavMark & 0x1) > 0) {
                                n6 = n5 - 1;
                            }
                            channelTypeFavMark >>>= 1;
                            ++n;
                            n5 = n6;
                        }
                    }
                }
                return n2;
            }
            case 30:
            case 31:
            case 32:
            case 71:
            case 72:
            case 74: {
                return this.channelListTypeMap.get(n, 0);
            }
        }
    }

    public static int dip2px(final Context context, final float n) {
        return (int)(n * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    private void disableSomeFunctionWhenSlave() {
        this.editBtn.setEnabled(false);
        this.TypeSwitch.setEnabled(false);
        this.titleText.setClickable(false);
        this.channelTypeArrow.setVisibility(4);
        this.ChannelListView.setLongClickable(false);
    }

    private void displayOrDimissLayout(final LinearLayout linearLayout, final ImageView imageView, final int visibility, final int visibility2) {
        if (linearLayout != null) {
            linearLayout.setVisibility(visibility);
        }
        if (imageView != null) {
            imageView.setVisibility(visibility2);
        }
        this.channelListAdapter.notifyDataSetChanged();
    }

    private void enableSomeFucitonWhenBecomeMaster() {
        this.editBtn.setEnabled(true);
        this.TypeSwitch.setEnabled(true);
        this.titleText.setClickable(true);
        this.channelTypeArrow.setVisibility(0);
        this.ChannelListView.setLongClickable(true);
        this.channelListAdapter = new list_single_button_adapter((Context)this);
        this.ChannelListView.setAdapter((ListAdapter)this.channelListAdapter);
        this.channelListAdapter.notifyDataSetChanged();
    }

    private void enterEditMode() {
        this.editMenu.setVisibility(0);
        this.tabWidget.setVisibility(8);
        this.allSelectedBtn.setImageResource(2130837969);
        this.allSelectedBtn.setVisibility(0);
        this.ChannelListView.setLongClickable(false);
        GsChannelListActivity.enable_edit = true;
        this.preChannelListType = this.currentChannelListType;
        this.ChangeChannelListType(0, false);
        this.currentChannelListType = 0;
        this.titleText.setClickable(false);
        this.channelTypeArrow.setVisibility(4);
        this.allSelectedBtn_selected = false;
        this.editBtn.setVisibility(8);
        this.DoneBtn.setVisibility(0);
        this.TypeSwitch.setVisibility(8);
        this.channelListAdapter.notifyDataSetChanged();
    }

    private void exitEditMode() {
        this.editMenu.setVisibility(8);
        this.tabWidget.setVisibility(0);
        this.allSelectedBtn.setVisibility(8);
        this.ChannelListView.setLongClickable(true);
        GsChannelListActivity.enable_edit = false;
        this.isEnableMove = false;
        this.ChangeChannelListType(this.preChannelListType, true);
        this.currentChannelListType = this.preChannelListType;
        this.titleText.setClickable(true);
        this.channelTypeArrow.setVisibility(0);
        this.editBtn.setVisibility(0);
        this.DoneBtn.setVisibility(8);
        this.TypeSwitch.setVisibility(0);
        this.initItemChecked();
        this.channelListAdapter.notifyDataSetChanged();
    }

    private void findChannel() {
        final ArrayList<DataConvertChannelModel> mSearchChannelListModels = new ArrayList<DataConvertChannelModel>();
        final String string = this.mSearchChannelEdit.getText().toString();
        this.mSearchMode = 0;
        final int length = string.length();
        if (string.equals(this.mSearchChannelKeywords) && !this.mChannelListChangeFlag) {
            return;
        }
        if (length == 0) {
            this.mSearchChannelKeywords = "";
            this.mCurrentChannelList = this.mOriginalChannelListModels;
            this.mSearchChannelListModels = this.mCurrentChannelList;
            this.displayOrDimissLayout(this.mSearchFailedPrompt, this.mClearSearchKeyword, 8, 8);
            this.adjustSelectionOfChannelListView(true);
            return;
        }
        if (this.mSearchChannelKeywords.length() >= length) {
            this.mSearchChannelListModels = this.mOriginalChannelListModels;
        }
        if (this.mSearchChannelListModels.size() == 0) {
            this.displayOrDimissLayout(this.mSearchFailedPrompt, this.mClearSearchKeyword, 0, 0);
            return;
        }
        this.mSearchChannelKeywords = string;
        this.displayOrDimissLayout(null, this.mClearSearchKeyword, 0, 0);
        this.judgeSearchMode(length, string);
        for (final DataConvertChannelModel dataConvertChannelModel : this.mSearchChannelListModels) {
            int n = 0;
            final int[] array = new int[length];
            final String string2 = new StringBuilder(String.valueOf(dataConvertChannelModel.getCurrentChannelListDispIndex())).toString();
            if (this.mSearchMode == 0) {
                final boolean channelDispIndexMatched = this.isChannelDispIndexMatched(string2, string);
                if ((n = (channelDispIndexMatched ? 1 : 0)) != 0) {
                    dataConvertChannelModel.setSearchChannelSortPriority(-2);
                    n = (channelDispIndexMatched ? 1 : 0);
                }
            }
            int n2;
            if ((n2 = n) == 0) {
                final int index = dataConvertChannelModel.getProgramName().toUpperCase().indexOf(string.toUpperCase());
                if (-1 != index) {
                    n = 1;
                    array[0] = index;
                    dataConvertChannelModel.setMatchChannelNameIndexArray(array);
                    dataConvertChannelModel.setSearchChannelSortPriority(-1);
                }
                if ((n2 = n) == 0) {
                    n2 = n;
                    if (length == 0) {
                        final boolean hasMatchStringIndexFound = this.hasMatchStringIndexFound(dataConvertChannelModel.getProgramName(), string, array);
                        if ((n2 = (hasMatchStringIndexFound ? 1 : 0)) != 0) {
                            dataConvertChannelModel.setMatchChannelNameIndexArray(array);
                            dataConvertChannelModel.setSearchChannelSortPriority(this.getSearchChannelSortPriority(array));
                            n2 = (hasMatchStringIndexFound ? 1 : 0);
                        }
                    }
                }
            }
            if (n2 != 0) {
                mSearchChannelListModels.add(this.calculateChannelInsertPosition(dataConvertChannelModel, mSearchChannelListModels), dataConvertChannelModel);
            }
        }
        this.mSearchChannelListModels = mSearchChannelListModels;
        this.mCurrentChannelList = this.mSearchChannelListModels;
        if (this.mCurrentChannelList.isEmpty()) {
            this.displayOrDimissLayout(this.mSearchFailedPrompt, null, 0, 0);
            return;
        }
        this.displayOrDimissLayout(this.mSearchFailedPrompt, null, 8, 0);
        this.ChannelListView.setSelection(0);
    }

    private ArrayList<String> getChannelTypeData() {
        final ArrayList<String> list = new ArrayList<String>();
        final String[] stringArray = this.getResources().getStringArray(2131558412);
        switch (GMScreenGlobalInfo.getCurStbPlatform()) {
            default: {
                if (DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() == 0) {
                    for (int i = 0; i < stringArray.length; ++i) {
                        list.add(stringArray[i]);
                    }
                }
                else {
                    for (int j = 0; j < stringArray.length - 1; ++j) {
                        list.add(stringArray[j]);
                    }
                }
                this.channelTypeFavMark = 0;
                final Iterator<DataConvertChannelModel> iterator = this.mChannelData.getChannelListByTvRadioType().iterator();
                while (iterator.hasNext()) {
                    this.channelTypeFavMark |= iterator.next().GetFavMark();
                }
                for (int k = this.channelTypeFavMark, n = 0; k > 0; k >>>= 1, ++n) {
                    if ((k & 0x1) > 0) {
                        list.add(GMScreenGlobalInfo.favType.get(n));
                    }
                }
                break;
            }
            case 30:
            case 31:
            case 32:
            case 71:
            case 72:
            case 74: {
                if (this.channelListTypeMap == null) {
                    this.channelListTypeMap = new SparseIntArray();
                }
                this.channelListTypeMap.clear();
                int l;
                if (DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() == 0) {
                    for (l = 0; l < stringArray.length; ++l) {
                        list.add(stringArray[l]);
                        this.channelListTypeMap.append(l, l);
                    }
                }
                else {
                    int n2 = 0;
                    while (true) {
                        l = n2;
                        if (n2 >= stringArray.length - 1) {
                            break;
                        }
                        list.add(stringArray[n2]);
                        this.channelListTypeMap.append(n2, n2);
                        ++n2;
                    }
                }
                final int n3 = 0;
                int n4 = l;
                for (int n5 = n3; n5 < GMScreenGlobalInfo.favGroups.size(); ++n5) {
                    final Iterator<DataConvertChannelModel> iterator2 = this.mChannelData.getChannelListByTvRadioType().iterator();
                    while (iterator2.hasNext()) {
                        if (iterator2.next().mfavGroupIDs.contains(GMScreenGlobalInfo.favGroups.get(n5).getFavorTypeID())) {
                            list.add(GMScreenGlobalInfo.favGroups.get(n5).GetFavorName());
                            this.channelListTypeMap.append(n4, GMScreenGlobalInfo.favGroups.get(n5).getFavorTypeID() + 4);
                            ++n4;
                            break;
                        }
                    }
                }
                break;
            }
        }
        return list;
    }

    private int getSearchChannelSortPriority(final int[] array) {
        final int[] array2 = new int[array.length - 1];
        final int n = 0;
        int i = 0;
        int n2 = 0;
        while (i < array.length - 1) {
            if (array[i] == array[i + 1] - 1) {
                ++array2[n2];
            }
            else {
                ++n2;
            }
            ++i;
        }
        int j = 0;
        int n3 = n;
        while (j < array2.length - 1) {
            if (array2[n3] < array2[j + 1]) {
                n3 = ++j;
            }
            else {
                ++j;
            }
        }
        return array2[n3] + 1;
    }

    private ArrayList<String> getSortTypeArray() {
        final ArrayList<String> list = new ArrayList<String>();
        if (GMScreenGlobalInfo.getCurStbInfo().getPlatform_id() == 30) {
            final String[] stringArray = this.getResources().getStringArray(2131558401);
            for (int i = 0; i < stringArray.length; ++i) {
                list.add(stringArray[i]);
            }
        }
        else {
            final String[] stringArray2 = this.getResources().getStringArray(2131558400);
            for (int j = 0; j < stringArray2.length; ++j) {
                list.add(stringArray2[j]);
            }
        }
        return list;
    }

    private boolean hasMatchStringIndexFound(final String s, final String s2, final int[] array) {
        int n = 0;
        int n2 = 0;
        Label_0041_Outer:
        while (true) {
            final boolean b = false;
            if (n2 >= s2.length()) {
                return true;
            }
            int i = n;
            Label_0041:
            while (true) {
                while (i < s.length()) {
                    if ('0' <= s2.charAt(n2) && '9' >= s2.charAt(n2)) {
                        if (s2.charAt(n2) == s.charAt(i)) {
                            array[n2] = i;
                            final int n3 = i + 1;
                            final int n4 = 1;
                            break Label_0041;
                        }
                    }
                    else if (s2.charAt(n2) == s.charAt(i) || (char)(s2.charAt(n2) - ' ') == s.charAt(i) || (char)(s2.charAt(n2) + ' ') == s.charAt(i)) {
                        array[n2] = i;
                        final int n3 = i + 1;
                        final int n4 = 1;
                        break Label_0041;
                    }
                    ++i;
                    continue Label_0041_Outer;
                    int n4 = 0;
                    if (n4 == 0) {
                        return false;
                    }
                    ++n2;
                    int n3 = 0;
                    n = n3;
                    continue Label_0041_Outer;
                }
                final int n3 = n;
                final int n4 = b ? 1 : 0;
                continue Label_0041;
            }
        }
    }

    private void initItemChecked() {
        final Iterator<DataConvertChannelModel> iterator = this.mCurrentChannelList.iterator();
        while (iterator.hasNext()) {
            iterator.next().setSelectedFlag(false);
        }
    }

    private void inputPermissionPassword() {
        final LinearLayout contentView = (LinearLayout)LayoutInflater.from((Context)this.getParent()).inflate(2130903117, (ViewGroup)null);
        final TextView textView = (TextView)contentView.findViewById(2131362145);
        final EditText editText = (EditText)contentView.findViewById(2131362146);
        final Button button = (Button)contentView.findViewById(2131362147);
        switch (this.passwordType) {
            case 1:
            case 2: {
                if (!this.repeatPassword) {
                    textView.setText(2131427557);
                    break;
                }
                this.repeatPassword = false;
                textView.setText(2131427560);
                break;
            }
            case 3: {
                textView.setText((CharSequence)this.mCurrentChannelList.get(this.sat2ipPlayPosition).getProgramName());
                break;
            }
        }
        editText.addTextChangedListener((TextWatcher)new TextWatcher() {
            public void afterTextChanged(final Editable editable) {
            }

            public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
            }

            public void onTextChanged(final CharSequence charSequence, int n, final int n2, final int n3) {
                if (editText.getText().toString().length() != GMScreenGlobalInfo.getmMaxPasswordNum()) {
                    return;
                }
                Label_0225: {
                    if (GsChannelListActivity.this.passwordType != 1 && GsChannelListActivity.this.passwordType != 3) {
                        break Label_0225;
                    }
                    n = 1056;
                    while (true) {
                        final ArrayList<DataConvertOneDataModel> list = new ArrayList<DataConvertOneDataModel>();
                        final DataConvertOneDataModel dataConvertOneDataModel = new DataConvertOneDataModel();
                        dataConvertOneDataModel.setData(editText.getText().toString());
                        list.add(dataConvertOneDataModel);
                        while (true) {
                            try {
                                GsChannelListActivity.access$44(GsChannelListActivity.this, ParserFactory.getParser());
                                final byte[] bytes = GsChannelListActivity.this.parser.serialize(list, n).getBytes("UTF-8");
                                GsChannelListActivity.this.tcpSocket.setSoTimeout(3000);
                                GsSendSocket.sendSocketToStb(bytes, GsChannelListActivity.this.tcpSocket, 0, bytes.length, n);
                                GsChannelListActivity.this.inputManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                                GsChannelListActivity.this.pswInputDialog.dismiss();
                                GsChannelListActivity.access$16(GsChannelListActivity.this, DialogBuilder.showProgressDialog((Context)GsChannelListActivity.this.getParent(), GsChannelListActivity.this.getString(2131427635), GsChannelListActivity.this.getString(2131427520), true));
                                GsChannelListActivity.this.pswInputDialog.dismiss();
                                return;
                                GsChannelListActivity.access$49(GsChannelListActivity.this, 0);
                                n = 1008;
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
        });
        button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                switch (GsChannelListActivity.this.passwordType) {
                    case 1: {
                        GsChannelListActivity.access$52(GsChannelListActivity.this, false);
                        break;
                    }
                    case 2: {
                        GsSendSocket.sendOnlyCommandSocketToStb(GsChannelListActivity.this.tcpSocket, 1057);
                        break;
                    }
                }
                GsChannelListActivity.access$49(GsChannelListActivity.this, 0);
                GsChannelListActivity.this.pswInputDialog.dismiss();
            }
        });
        if (this.pswInputDialog != null && this.pswInputDialog.isShowing()) {
            this.pswInputDialog.dismiss();
        }
        (this.pswInputDialog = new Dialog((Context)this.getParent(), 2131493009)).setContentView((View)contentView);
        this.pswInputDialog.setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
            public void onCancel(final DialogInterface dialogInterface) {
                switch (GsChannelListActivity.this.passwordType) {
                    case 1: {
                        GsChannelListActivity.access$52(GsChannelListActivity.this, false);
                        break;
                    }
                    case 2: {
                        GsSendSocket.sendOnlyCommandSocketToStb(GsChannelListActivity.this.tcpSocket, 1057);
                        break;
                    }
                }
                GsChannelListActivity.access$49(GsChannelListActivity.this, 0);
                GsChannelListActivity.this.pswInputDialog.dismiss();
            }
        });
        this.pswInputDialog.setCanceledOnTouchOutside(false);
        this.pswInputDialog.show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                GsChannelListActivity.access$18(GsChannelListActivity.this, (InputMethodManager)editText.getContext().getSystemService("input_method"));
                GsChannelListActivity.this.inputManager.showSoftInput((View)editText, 0);
            }
        }, 200L);
    }

    private boolean isChannelDispIndexMatched(final String s, final String s2) {
        if (s2.length() <= s.length()) {
            for (int i = 0; i < s2.length(); ++i) {
                if (s2.charAt(i) != s.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void judgeSearchMode(int int1, final String s) {
        for (int i = 0; i < int1; ++i) {
            if ('0' > s.charAt(i) || '9' < s.charAt(i)) {
                this.mSearchMode = 1;
                return;
            }
        }
        int1 = Integer.parseInt(s);
        if (int1 > this.mOriginalChannelListModels.size() || int1 == 0) {
            this.mSearchMode = 1;
        }
    }

    private static Collection<String> list(final String... array) {
        return Collections.unmodifiableCollection((Collection<? extends String>)Arrays.asList(array));
    }

    private void otherPlatformPlay(int indexByProgIdInCurTvRadioProgList) {
        indexByProgIdInCurTvRadioProgList = this.mChannelData.getIndexByProgIdInCurTvRadioProgList(((DataConvertChannelModel)this.channelListAdapter.getItem(indexByProgIdInCurTvRadioProgList)).GetProgramId());
        GMScreenGlobalInfo.playType = 2;
        this.bPlayWithOherPlayer = false;
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass((Context)this.getParent(), (Class)LivePlayActivity.class);
        intent.putExtra("position", indexByProgIdInCurTvRadioProgList);
        intent.setFlags(268435456);
        this.getApplication().startActivity(intent);
    }

    private void playStream(final int n) {
        final FindPlayerAndPlayChannel findPlayerAndPlayChannel = new FindPlayerAndPlayChannel((Context)this.getParent());
        findPlayerAndPlayChannel.implementPlayByDesignatedPlayer(this.mPlayByDesignatedPlayer);
        findPlayerAndPlayChannel.selectPlayer(n);
    }

    private void requestProgramListFromTo(final int n, final int n2) {
        try {
            final ArrayList<DataConvertChannelModel> list = new ArrayList<DataConvertChannelModel>();
            final DataConvertChannelModel dataConvertChannelModel = new DataConvertChannelModel();
            final DataConvertChannelModel dataConvertChannelModel2 = new DataConvertChannelModel();
            dataConvertChannelModel.SetProgramIndex(n);
            dataConvertChannelModel2.SetProgramIndex(n2);
            list.add(dataConvertChannelModel);
            list.add(dataConvertChannelModel2);
            this.parser = ParserFactory.getParser();
            GsSendSocket.sendSocketToStb(this.dataBuff = this.parser.serialize(list, 0).getBytes("UTF-8"), this.tcpSocket, 0, this.dataBuff.length, 0);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendSat2ipChannelRequestToStb(final int n) {
        final ArrayList<DataConvertChannelModel> list = new ArrayList<DataConvertChannelModel>();
        this.currentSat2ipChannelProgramId = ((DataConvertChannelModel)this.channelListAdapter.getItem(n)).GetProgramId();
        try {
            list.add((DataConvertChannelModel)this.channelListAdapter.getItem(n));
            final byte[] bytes = this.parser.serialize(list, 1009).getBytes("UTF-8");
            this.tcpSocket.setSoTimeout(3000);
            GsSendSocket.sendSocketToStb(bytes, this.tcpSocket, 0, bytes.length, 1009);
        }
        catch (ProgramNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }

    private void setCurrentChannelListDispIndex() {
        for (int i = 0; i < this.mCurrentChannelList.size(); ++i) {
            this.mCurrentChannelList.get(i).setCurrentChannelListDispIndex(i + 1);
        }
    }

    private void setDisplayFavValue() {
        int n = 0;
        for (final DataConvertChannelModel dataConvertChannelModel : this.mCurrentChannelList) {
            if (dataConvertChannelModel.getSelectedFlag()) {
                this.favorModels.add(dataConvertChannelModel);
                ++n;
            }
        }
        final int[] array = new int[n];
        for (int i = 0; i < this.favorModels.size(); ++i) {
            array[i] = this.favorModels.get(i).GetFavMark();
        }
        for (int j = 0; j < this.mIsChoice.length; ++j) {
            for (int k = 0; k < n; ++k) {
                if ((array[k] & this.mFavValueArray[j]) != this.mFavValueArray[j]) {
                    this.mIsChoice[j] = false;
                    break;
                }
                if (k == n - 1) {
                    this.mIsChoice[j] = true;
                    break;
                }
            }
            if (this.mIsChoice[j]) {
                this.mFavValue |= this.mFavValueArray[j];
            }
        }
    }

    private void setMessageProcess() {
        (this.msgProc = MessageProcessor.obtain()).recycle();
        this.msgProc.setOnMessageProcess(0, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (message.arg1 > 0) {
                    final int totalProgramNum = GsChannelListActivity.this.mChannelData.getTotalProgramNum();
                    final byte[] byteArray = message.getData().getByteArray("ReceivedData");
                    if (byteArray == null) {
                        Log.e("GSChannelListActivity", "recvData = " + byteArray);
                    }
                    else {
                        final List<DataConvertChannelModel> initChannelListData = GsChannelListActivity.this.mChannelData.initChannelListData(byteArray);
                        if (GsChannelListActivity.this.waitDialog.isShowing()) {
                            GsChannelListActivity.this.waitDialog.dismiss();
                        }
                        if (initChannelListData.size() != 0 || totalProgramNum != GsChannelListActivity.this.mChannelData.getTotalProgramNum()) {
                            GsChannelListActivity.access$75(GsChannelListActivity.this, GsChannelListActivity.this.mChannelData.getChannelListByProgramType(GsChannelListActivity.this.mChannelData.getChannelListByTvRadioType(), GsChannelListActivity.this.currentChannelListType));
                            if (GsChannelListActivity.this.mCurrentChannelList == null || GsChannelListActivity.this.mCurrentChannelList.isEmpty()) {
                                GsChannelListActivity.access$75(GsChannelListActivity.this, GsChannelListActivity.this.mChannelData.getChannelListByProgramType(GsChannelListActivity.this.mChannelData.getChannelListByTvRadioType(), 0));
                                GsChannelListActivity.access$65(GsChannelListActivity.this, 0);
                                GsChannelListActivity.this.titleText.setText((CharSequence)GsChannelListActivity.this.getResources().getStringArray(2131558412)[GsChannelListActivity.this.currentChannelListType]);
                            }
                            if (GsChannelListActivity.this.mGetChannelListWhenLogin) {
                                switch (DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type()) {
                                    case 0: {
                                        GsChannelListActivity.this.TypeSwitch.setText(GsChannelListActivity.this.getResources().getText(2131427585));
                                        break;
                                    }
                                    case 1: {
                                        GsChannelListActivity.this.TypeSwitch.setText(GsChannelListActivity.this.getResources().getText(2131427584));
                                        break;
                                    }
                                }
                                switch (GMScreenGlobalInfo.getCurStbPlatform()) {
                                    default: {
                                        if (GsChannelListActivity.this.currentChannelListType >= 0 && GsChannelListActivity.this.currentChannelListType <= 3) {
                                            GsChannelListActivity.this.titleText.setText((CharSequence)GsChannelListActivity.this.getResources().getStringArray(2131558412)[GsChannelListActivity.this.currentChannelListType]);
                                            break;
                                        }
                                        if (GsChannelListActivity.this.currentChannelListType >= 4 && GsChannelListActivity.this.currentChannelListType <= 11) {
                                            GsChannelListActivity.this.titleText.setText((CharSequence)GMScreenGlobalInfo.favType.get(GsChannelListActivity.this.currentChannelListType - 4));
                                            break;
                                        }
                                        break;
                                    }
                                    case 30:
                                    case 31:
                                    case 32:
                                    case 71:
                                    case 72:
                                    case 74: {
                                        if (GsChannelListActivity.this.currentChannelListType >= 0 && GsChannelListActivity.this.currentChannelListType <= 3) {
                                            GsChannelListActivity.this.titleText.setText((CharSequence)GsChannelListActivity.this.getResources().getStringArray(2131558412)[GsChannelListActivity.this.currentChannelListType]);
                                            break;
                                        }
                                        if (GsChannelListActivity.this.currentChannelListType >= 4) {
                                            for (final DataConvertFavorModel dataConvertFavorModel : GMScreenGlobalInfo.favGroups) {
                                                if (dataConvertFavorModel.getFavorTypeID() == GsChannelListActivity.this.currentChannelListType - 4) {
                                                    GsChannelListActivity.this.titleText.setText((CharSequence)dataConvertFavorModel.GetFavorName());
                                                }
                                            }
                                            break;
                                        }
                                        break;
                                    }
                                }
                            }
                            GsChannelListActivity.this.setCurrentChannelListDispIndex();
                            GsChannelListActivity.access$80(GsChannelListActivity.this, GsChannelListActivity.this.mCurrentChannelList);
                            if (GsChannelListActivity.this.mEnterSearchFlag) {
                                GsChannelListActivity.access$81(GsChannelListActivity.this, true);
                                GsChannelListActivity.this.findChannel();
                            }
                            else {
                                GsChannelListActivity.access$83(GsChannelListActivity.this, new list_single_button_adapter((Context)GsChannelListActivity.this));
                                GsChannelListActivity.this.ChannelListView.setAdapter((ListAdapter)GsChannelListActivity.this.channelListAdapter);
                                GsChannelListActivity.this.adjustSelectionOfChannelListView(true);
                            }
                            if (GsChannelListActivity.this.mGetChannelListWhenLogin) {
                                int n = 0;
                                for (final DataConvertChannelModel dataConvertChannelModel : GsChannelListActivity.this.mChannelData.getmTvChannelList()) {
                                    if (dataConvertChannelModel.getmWillBePlayed() == 1 && dataConvertChannelModel.getLockMark() == 1) {
                                        if (GsChannelListActivity.this.isInForeground) {
                                            GsChannelListActivity.access$49(GsChannelListActivity.this, 2);
                                            GsChannelListActivity.this.inputPermissionPassword();
                                            n = 1;
                                            break;
                                        }
                                        break;
                                    }
                                }
                                if (n == 0) {
                                    for (final DataConvertChannelModel dataConvertChannelModel2 : GsChannelListActivity.this.mChannelData.getmRadioChannelList()) {
                                        if (dataConvertChannelModel2.getmWillBePlayed() == 1 && dataConvertChannelModel2.getLockMark() == 1) {
                                            if (GsChannelListActivity.this.isInForeground) {
                                                GsChannelListActivity.access$49(GsChannelListActivity.this, 2);
                                                GsChannelListActivity.this.inputPermissionPassword();
                                                break;
                                            }
                                            break;
                                        }
                                    }
                                }
                                GsChannelListActivity.access$85(GsChannelListActivity.this, false);
                            }
                            if (initChannelListData != null && initChannelListData.size() == GMScreenGlobalInfo.getMaxProgramNumPerRequest()) {
                                GsChannelListActivity.this.requestProgramListFromTo(GsChannelListActivity.this.mChannelData.getTotalProgramNum(), GsChannelListActivity.this.mChannelData.getTotalProgramNum() + GMScreenGlobalInfo.getMaxProgramNumPerRequest() - 1);
                                return;
                            }
                            if (GMScreenGlobalInfo.getCurStbInfo().getmSatEnable() == 1) {
                                GsChannelListActivity.this.msgProc.postEmptyMessage(4115);
                            }
                        }
                        else if (initChannelListData.size() == 0) {
                            Toast.makeText((Context)GsChannelListActivity.this, 2131427648, 0).show();
                            if (!GsChannelListActivity.this.mGetChannelListWhenLogin) {
                                GsChannelListActivity.this.mCurrentChannelList.removeAll(GsChannelListActivity.this.mCurrentChannelList);
                                GsChannelListActivity.this.channelListAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
                else if (message.arg1 == 0 && message.arg2 == -1) {
                    GsChannelListActivity.this.requestProgramListFromTo(GsChannelListActivity.this.mChannelData.getTotalProgramNum(), GsChannelListActivity.this.mChannelData.getTotalProgramNum() + GMScreenGlobalInfo.getMaxProgramNumPerRequest() - 1);
                }
            }
        });
        this.msgProc.setOnMessageProcess(5, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (message.arg1 > 0) {
                    try {
                        final byte[] byteArray = message.getData().getByteArray("ReceivedData");
                        if (byteArray == null) {
                            Log.e("GSChannelListActivity", "recvData = " + byteArray);
                            return;
                        }
                        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray, 0, byteArray.length);
                        System.out.println("epg_program data length: " + byteArray.length);
                        final List<?> parse = GsChannelListActivity.this.parser.parse(byteArrayInputStream, 6);
                        System.out.println("todayDate:" + ((GsEPGTableChannel)parse.get(0)).getTodayDate());
                        ((GsEPGTableChannel)parse.get(0)).setProgramId(GsChannelListActivity.this.epg_program_sat_tp_id);
                        ((GsEPGTableChannel)parse.get(0)).setProgramName(GsChannelListActivity.this.epg_program_name);
                        GsSession.getSession().put("EPG_PROGRAM_TABLE", parse.get(0));
                        if (GsChannelListActivity.this.waitDialog.isShowing()) {
                            GsChannelListActivity.this.waitDialog.dismiss();
                        }
                        GsChannelListActivity.this.startActivityForResult(new Intent((Context)GsChannelListActivity.this, (Class)GsEPGMenuActivity.class), 0);
                        return;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                if (message.arg2 == 3) {
                    if (GsChannelListActivity.this.waitDialog.isShowing()) {
                        GsChannelListActivity.this.waitDialog.dismiss();
                    }
                    Toast.makeText((Context)GsChannelListActivity.this, 2131427647, 0).show();
                }
            }
        });
        this.msgProc.setOnMessageProcess(3, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message p0) {
                //
                // This method could not be decompiled.
                //
                // Original Bytecode:
                //
                //     1: getfield        android/os/Message.arg1:I
                //     4: ifle            43
                //     7: aload_1
                //     8: invokevirtual   android/os/Message.getData:()Landroid/os/Bundle;
                //    11: ldc             "ReceivedData"
                //    13: invokevirtual   android/os/Bundle.getByteArray:(Ljava/lang/String;)[B
                //    16: astore_2
                //    17: aload_2
                //    18: ifnonnull       44
                //    21: ldc             "GSChannelListActivity"
                //    23: new             Ljava/lang/StringBuilder;
                //    26: dup
                //    27: ldc             "recvData = "
                //    29: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
                //    32: aload_2
                //    33: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
                //    36: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //    39: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
                //    42: pop
                //    43: return
                //    44: invokestatic    mktvsmart/screen/dataconvert/parser/ParserFactory.getParser:()Lmktvsmart/screen/dataconvert/parser/DataParser;
                //    47: astore_3
                //    48: aconst_null
                //    49: astore_1
                //    50: aload_3
                //    51: new             Ljava/io/ByteArrayInputStream;
                //    54: dup
                //    55: aload_2
                //    56: iconst_0
                //    57: aload_2
                //    58: arraylength
                //    59: invokespecial   java/io/ByteArrayInputStream.<init>:([BII)V
                //    62: bipush          15
                //    64: invokeinterface mktvsmart/screen/dataconvert/parser/DataParser.parse:(Ljava/io/InputStream;I)Ljava/util/List;
                //    69: astore_2
                //    70: aload_2
                //    71: astore_1
                //    72: aload_1
                //    73: iconst_0
                //    74: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
                //    79: checkcast       Ljava/lang/String;
                //    82: astore_2
                //    83: aload_0
                //    84: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //    87: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$7:(Lmktvsmart/screen/channel/GsChannelListActivity;)Lmktvsmart/screen/channel/ChannelData;
                //    90: invokevirtual   mktvsmart/screen/channel/ChannelData.getmTvChannelList:()Ljava/util/List;
                //    93: ifnull          246
                //    96: aload_0
                //    97: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   100: getfield        mktvsmart/screen/channel/GsChannelListActivity.isSat2ipStarted:Z
                //   103: ifeq            180
                //   106: aload_0
                //   107: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   110: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$7:(Lmktvsmart/screen/channel/GsChannelListActivity;)Lmktvsmart/screen/channel/ChannelData;
                //   113: aload_0
                //   114: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   117: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$88:(Lmktvsmart/screen/channel/GsChannelListActivity;)Ljava/lang/String;
                //   120: invokevirtual   mktvsmart/screen/channel/ChannelData.getProgramByProgramId:(Ljava/lang/String;)Lmktvsmart/screen/dataconvert/model/DataConvertChannelModel;
                //   123: astore_3
                //   124: aload_0
                //   125: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   128: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$7:(Lmktvsmart/screen/channel/GsChannelListActivity;)Lmktvsmart/screen/channel/ChannelData;
                //   131: aload_1
                //   132: iconst_0
                //   133: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
                //   138: checkcast       Ljava/lang/String;
                //   141: invokevirtual   mktvsmart/screen/channel/ChannelData.getProgramByProgramId:(Ljava/lang/String;)Lmktvsmart/screen/dataconvert/model/DataConvertChannelModel;
                //   144: astore_1
                //   145: aload_0
                //   146: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   149: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$7:(Lmktvsmart/screen/channel/GsChannelListActivity;)Lmktvsmart/screen/channel/ChannelData;
                //   152: aload_1
                //   153: aload_3
                //   154: invokevirtual   mktvsmart/screen/channel/ChannelData.canSat2ipChannelPlay:(Lmktvsmart/screen/dataconvert/model/DataConvertChannelModel;Lmktvsmart/screen/dataconvert/model/DataConvertChannelModel;)Z
                //   157: ifne            180
                //   160: aload_0
                //   161: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   164: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$0:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
                //   167: aload_0
                //   168: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   171: ldc             2131427600
                //   173: iconst_0
                //   174: invokestatic    android/widget/Toast.makeText:(Landroid/content/Context;II)Landroid/widget/Toast;
                //   177: invokevirtual   android/widget/Toast.show:()V
                //   180: aload_0
                //   181: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   184: iconst_0
                //   185: putfield        mktvsmart/screen/channel/GsChannelListActivity.loop:I
                //   188: aload_0
                //   189: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   192: getfield        mktvsmart/screen/channel/GsChannelListActivity.loop:I
                //   195: aload_0
                //   196: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   199: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$7:(Lmktvsmart/screen/channel/GsChannelListActivity;)Lmktvsmart/screen/channel/ChannelData;
                //   202: invokevirtual   mktvsmart/screen/channel/ChannelData.getmTvChannelList:()Ljava/util/List;
                //   205: invokeinterface java/util/List.size:()I
                //   210: if_icmplt       301
                //   213: aload_0
                //   214: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   217: iconst_0
                //   218: putfield        mktvsmart/screen/channel/GsChannelListActivity.loop:I
                //   221: aload_0
                //   222: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   225: getfield        mktvsmart/screen/channel/GsChannelListActivity.loop:I
                //   228: aload_0
                //   229: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   232: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$7:(Lmktvsmart/screen/channel/GsChannelListActivity;)Lmktvsmart/screen/channel/ChannelData;
                //   235: invokevirtual   mktvsmart/screen/channel/ChannelData.getmRadioChannelList:()Ljava/util/List;
                //   238: invokeinterface java/util/List.size:()I
                //   243: if_icmplt       369
                //   246: aload_0
                //   247: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   250: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$15:(Lmktvsmart/screen/channel/GsChannelListActivity;)Lmktvsmart/screen/channel/GsChannelListActivity$list_single_button_adapter;
                //   253: ifnull          43
                //   256: aload_0
                //   257: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   260: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$15:(Lmktvsmart/screen/channel/GsChannelListActivity;)Lmktvsmart/screen/channel/GsChannelListActivity$list_single_button_adapter;
                //   263: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity$list_single_button_adapter.notifyDataSetChanged:()V
                //   266: aload_0
                //   267: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   270: iconst_0
                //   271: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$61:(Lmktvsmart/screen/channel/GsChannelListActivity;Z)V
                //   274: return
                //   275: astore_2
                //   276: aload_2
                //   277: invokevirtual   java/lang/Exception.printStackTrace:()V
                //   280: goto            72
                //   283: astore_1
                //   284: ldc             "ProgramNotFoundException"
                //   286: aload_1
                //   287: invokevirtual   mktvsmart/screen/exception/ProgramNotFoundException.getMessage:()Ljava/lang/String;
                //   290: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   293: pop
                //   294: aload_1
                //   295: invokevirtual   mktvsmart/screen/exception/ProgramNotFoundException.printStackTrace:()V
                //   298: goto            180
                //   301: aload_0
                //   302: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   305: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$7:(Lmktvsmart/screen/channel/GsChannelListActivity;)Lmktvsmart/screen/channel/ChannelData;
                //   308: invokevirtual   mktvsmart/screen/channel/ChannelData.getmTvChannelList:()Ljava/util/List;
                //   311: aload_0
                //   312: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   315: getfield        mktvsmart/screen/channel/GsChannelListActivity.loop:I
                //   318: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
                //   323: checkcast       Lmktvsmart/screen/dataconvert/model/DataConvertChannelModel;
                //   326: astore_1
                //   327: aload_2
                //   328: aload_1
                //   329: invokevirtual   mktvsmart/screen/dataconvert/model/DataConvertChannelModel.GetProgramId:()Ljava/lang/String;
                //   332: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   335: ifeq            361
                //   338: aload_1
                //   339: iconst_1
                //   340: invokevirtual   mktvsmart/screen/dataconvert/model/DataConvertChannelModel.setIsPlaying:(I)V
                //   343: aload_0
                //   344: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   347: astore_1
                //   348: aload_1
                //   349: aload_1
                //   350: getfield        mktvsmart/screen/channel/GsChannelListActivity.loop:I
                //   353: iconst_1
                //   354: iadd
                //   355: putfield        mktvsmart/screen/channel/GsChannelListActivity.loop:I
                //   358: goto            188
                //   361: aload_1
                //   362: iconst_0
                //   363: invokevirtual   mktvsmart/screen/dataconvert/model/DataConvertChannelModel.setIsPlaying:(I)V
                //   366: goto            343
                //   369: aload_0
                //   370: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   373: invokestatic    mktvsmart/screen/channel/GsChannelListActivity.access$7:(Lmktvsmart/screen/channel/GsChannelListActivity;)Lmktvsmart/screen/channel/ChannelData;
                //   376: invokevirtual   mktvsmart/screen/channel/ChannelData.getmRadioChannelList:()Ljava/util/List;
                //   379: aload_0
                //   380: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   383: getfield        mktvsmart/screen/channel/GsChannelListActivity.loop:I
                //   386: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
                //   391: checkcast       Lmktvsmart/screen/dataconvert/model/DataConvertChannelModel;
                //   394: astore_1
                //   395: aload_2
                //   396: aload_1
                //   397: invokevirtual   mktvsmart/screen/dataconvert/model/DataConvertChannelModel.GetProgramId:()Ljava/lang/String;
                //   400: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   403: ifeq            429
                //   406: aload_1
                //   407: iconst_1
                //   408: invokevirtual   mktvsmart/screen/dataconvert/model/DataConvertChannelModel.setIsPlaying:(I)V
                //   411: aload_0
                //   412: getfield        mktvsmart/screen/channel/GsChannelListActivity$17.this$0:Lmktvsmart/screen/channel/GsChannelListActivity;
                //   415: astore_1
                //   416: aload_1
                //   417: aload_1
                //   418: getfield        mktvsmart/screen/channel/GsChannelListActivity.loop:I
                //   421: iconst_1
                //   422: iadd
                //   423: putfield        mktvsmart/screen/channel/GsChannelListActivity.loop:I
                //   426: goto            221
                //   429: aload_1
                //   430: iconst_0
                //   431: invokevirtual   mktvsmart/screen/dataconvert/model/DataConvertChannelModel.setIsPlaying:(I)V
                //   434: goto            411
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type
                //  -----  -----  -----  -----  -----------------------------------------------------
                //  50     70     275    283    Ljava/lang/Exception;
                //  106    180    283    301    Lmktvsmart/screen/exception/ProgramNotFoundException;
                //
                // The error that occurred was:
                //
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0180:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
                //     at us.deathmarine.luyten.Model.openEntryByTreePath(Model.java:266)
                //     at us.deathmarine.luyten.Model.access$500(Model.java:65)
                //     at us.deathmarine.luyten.Model$TreeListener$1.run(Model.java:222)
                //
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
        });
        this.msgProc.setOnMessageProcess(4112, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                Toast.makeText((Context)GsChannelListActivity.this, 2131427619, 0).show();
                GsChannelListActivity.enable_edit = false;
                final Intent intent = new Intent();
                intent.setClass((Context)GsChannelListActivity.this, (Class)GsLoginListActivity.class);
                GsChannelListActivity.this.startActivity(intent);
                GsChannelListActivity.this.finish();
            }
        });
        this.msgProc.setOnMessageProcess(20, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (message.arg2 == 5) {
                    GsSendSocket.sendOnlyCommandSocketToStb(GsChannelListActivity.this.tcpSocket, 20);
                    return;
                }
                final byte[] byteArray = message.getData().getByteArray("ReceivedData");
                if (byteArray == null) {
                    Log.e("GSChannelListActivity", "recvData = " + byteArray);
                    return;
                }
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray, 0, byteArray.length);
                try {
                    GsChannelListActivity.access$89(GsChannelListActivity.this, GsChannelListActivity.this.parser.parse(byteArrayInputStream, 2));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.msgProc.setOnMessageProcess(18, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(Message parse) {
                Label_0043: {
                    if (parse.arg1 > 0) {
                        final byte[] byteArray = parse.getData().getByteArray("ReceivedData");
                        if (byteArray == null) {
                            Log.e("GSChannelListActivity", "recvData = " + byteArray);
                        }
                        else {
                            int getmSortType;
                            View inflate;
                            ListView listView;
                            Button button;
                            ArrayList<String> list;
                            Label_0086_Outer:Label_0232_Outer:
                            while (true) {
                                parse = null;
                                GsChannelListActivity.access$44(GsChannelListActivity.this, ParserFactory.getParser());
                                while (true) {
                                    while (true) {
                                        try {
                                            parse = (Message)GsChannelListActivity.this.parser.parse(new ByteArrayInputStream(byteArray, 0, byteArray.length), 13);
                                            getmSortType = ((List<DataConvertSortModel>)parse).get(0).getmSortType();
                                            inflate = LayoutInflater.from((Context)GsChannelListActivity.this).inflate(2130903144, (ViewGroup)null);
                                            ((TextView)inflate.findViewById(2131362286)).setText(2131427558);
                                            listView = (ListView)inflate.findViewById(2131362287);
                                            button = (Button)inflate.findViewById(2131362288);
                                            switch (GMScreenGlobalInfo.getCurStbPlatform()) {
                                                default: {
                                                    list = GsChannelListActivity.this.getSortTypeArray();
                                                    GsChannelListActivity.access$91(GsChannelListActivity.this, new sort_adapter(inflate.getContext(), getmSortType, list, ((List<DataConvertSortModel>)parse).get(0).getmMacroFlag()));
                                                    GsChannelListActivity.this.mSortAdapter.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165211));
                                                    listView.setAdapter((ListAdapter)GsChannelListActivity.this.mSortAdapter);
                                                    button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                                                        public void onClick(final View view) {
                                                            GsChannelListActivity.this.mSortTypePopupWindow.dismiss();
                                                        }
                                                    });
                                                    listView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                                                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                                                            final ArrayList<DataConvertSortModel> list = new ArrayList<DataConvertSortModel>();
                                                            final DataConvertSortModel dataConvertSortModel = new DataConvertSortModel();
                                                            dataConvertSortModel.setmSortType(n);
                                                            dataConvertSortModel.setmTvState(DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type());
                                                            list.add(dataConvertSortModel);
                                                            while (true) {
                                                                try {
                                                                    GsChannelListActivity.access$94(GsChannelListActivity.this, GsChannelListActivity.this.parser.serialize(list, 1006).getBytes("UTF-8"));
                                                                    GsSendSocket.sendSocketToStb(GsChannelListActivity.this.dataBuff, GsChannelListActivity.this.tcpSocket, 0, GsChannelListActivity.this.dataBuff.length, 1006);
                                                                    GsChannelListActivity.this.mSortTypePopupWindow.dismiss();
                                                                    if (GsChannelListActivity.this.waitDialog.isShowing()) {
                                                                        GsChannelListActivity.this.waitDialog.dismiss();
                                                                    }
                                                                    GsChannelListActivity.access$16(GsChannelListActivity.this, DialogBuilder.showProgressDialog(GsChannelListActivity.this.getParent(), 2131427519, 2131427520, false, GMScreenGlobalInfo.getmWaitDialogTimeOut(), 2131427638));
                                                                }
                                                                catch (Exception ex) {
                                                                    ex.printStackTrace();
                                                                    continue Label_0086_Outer;
                                                                }
                                                                break;
                                                            }
                                                        }
                                                    });
                                                    GsChannelListActivity.access$96(GsChannelListActivity.this, new Dialog((Context)GsChannelListActivity.this.getParent(), 2131493009));
                                                    GsChannelListActivity.this.mSortTypePopupWindow.setContentView(inflate);
                                                    if (!GsChannelListActivity.this.mSortTypePopupWindow.isShowing()) {
                                                        GsChannelListActivity.this.mSortTypePopupWindow.show();
                                                        return;
                                                    }
                                                    break Label_0043;
                                                }
                                                case 20:
                                                case 21:
                                                case 30:
                                                case 32:
                                                case 71:
                                                case 72:
                                                case 74: {
                                                    break;
                                                }
                                            }
                                        }
                                        catch (Exception ex) {
                                            ex.printStackTrace();
                                            continue Label_0232_Outer;
                                        }
                                        break;
                                    }
                                    list = ((List<DataConvertSortModel>)parse).get(0).getSortTypeList();
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        });
        this.msgProc.setOnMessageProcess(16, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (message.arg1 > 0) {
                    final byte[] byteArray = message.getData().getByteArray("ReceivedData");
                    if (byteArray == null) {
                        Log.e("GSChannelListActivity", "recvData = " + byteArray);
                    }
                    else {
                        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray, 0, byteArray.length);
                        try {
                            GsChannelListActivity.access$65(GsChannelListActivity.this, Integer.parseInt((String)GsChannelListActivity.this.parser.parse(byteArrayInputStream, 17).get(0)));
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        this.msgProc.setOnMessageProcess(73, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (!GsChannelListActivity.this.waitDialog.isShowing()) {
                    GsChannelListActivity.access$16(GsChannelListActivity.this, DialogBuilder.showProgressDialog(GsChannelListActivity.this.getParent(), 2131427621, 2131427520, false, GMScreenGlobalInfo.getmWaitDialogTimeOut(), new Runnable() {
                        @Override
                        public void run() {
                            GsChannelListActivity.this.msgProc.postEmptyMessage(4104);
                        }
                    }));
                }
            }
        });
        this.msgProc.setOnMessageProcess(4104, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                Toast.makeText((Context)GsChannelListActivity.this, (CharSequence)"update failed!", 0).show();
            }
        });
        this.msgProc.setOnMessageProcess(17, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            List<String> tvRadioTypeModels = null;

            @Override
            public void doInForeground(final Message message) {
                byte[] byteArray;
                Label_0233_Outer:Label_0116_Outer:
                while (true) {
                    Label_0116:
                    while (true) {
                        Label_0365: {
                            while (true) {
                                Label_0343: {
                                    Label_0318: {
                                        Label_0293: {
                                            try {
                                                byteArray = message.getData().getByteArray("ReceivedData");
                                                if (byteArray == null) {
                                                    Log.e("GSChannelListActivity", "recvData = " + byteArray);
                                                    return;
                                                }
                                                this.tvRadioTypeModels = (List<String>)GsChannelListActivity.this.parser.parse(new ByteArrayInputStream(byteArray, 0, byteArray.length), 15);
                                                if (Integer.parseInt(this.tvRadioTypeModels.get(0)) != DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type()) {
                                                    switch (DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type()) {
                                                        case 1: {
                                                            break Label_0293;
                                                        }
                                                        case 0: {
                                                            break Label_0318;
                                                        }
                                                        default: {
                                                            break Label_0365;
                                                        }
                                                    }
                                                }
                                                while (true) {
                                                    GsChannelListActivity.this.setCurrentChannelListDispIndex();
                                                    GsChannelListActivity.access$80(GsChannelListActivity.this, GsChannelListActivity.this.mCurrentChannelList);
                                                    if (!GsChannelListActivity.this.mEnterSearchFlag) {
                                                        break Label_0343;
                                                    }
                                                    GsChannelListActivity.this.findChannel();
                                                    GsChannelListActivity.this.titleText.setText((CharSequence)GsChannelListActivity.this.getResources().getStringArray(2131558412)[GsChannelListActivity.this.currentChannelListType]);
                                                    if (GsChannelListActivity.this.waitDialog.isShowing()) {
                                                        GsChannelListActivity.this.waitDialog.dismiss();
                                                        return;
                                                    }
                                                    break;
                                                    DataConvertChannelTypeModel.setCurrent_channel_tv_radio_type(Integer.parseInt(this.tvRadioTypeModels.get(0)));
                                                    GsChannelListActivity.access$65(GsChannelListActivity.this, 0);
                                                    GsChannelListActivity.access$75(GsChannelListActivity.this, GsChannelListActivity.this.mChannelData.getChannelListByProgramType(GsChannelListActivity.this.mChannelData.getChannelListByTvRadioType(), GsChannelListActivity.this.currentChannelListType));
                                                    GsChannelListActivity.access$81(GsChannelListActivity.this, true);
                                                    continue Label_0233_Outer;
                                                }
                                            }
                                            // iftrue(Label_0195:, !GsChannelListActivity.access$20(this.this$0))
                                            catch (Exception ex) {
                                                ex.printStackTrace();
                                                return;
                                            }
                                        }
                                        GsChannelListActivity.this.TypeSwitch.setText(GsChannelListActivity.this.getResources().getText(2131427585));
                                        continue Label_0116;
                                    }
                                    GsChannelListActivity.this.TypeSwitch.setText(GsChannelListActivity.this.getResources().getText(2131427584));
                                    continue Label_0116;
                                }
                                GsChannelListActivity.this.channelListAdapter.notifyDataSetChanged();
                                GsChannelListActivity.this.adjustSelectionOfChannelListView(false);
                                continue Label_0116_Outer;
                            }
                        }
                        continue Label_0116;
                    }
                }
            }
        });
        this.msgProc.setOnMessageProcess(2001, this.post);
        this.msgProc.setOnMessageProcess(2004, this.post);
        this.msgProc.setOnMessageProcess(2002, this, this.requestAllChannelWhenSTBChannelListChanged);
        this.msgProc.setOnMessageProcess(2025, this, this.requestAllChannelWhenSTBChannelListChanged);
        this.msgProc.setOnMessageProcess(2026, this, this.requestAllChannelWhenSTBChannelListChanged);
        this.msgProc.setOnMessageProcess(1056, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            List<String> verifyResult = null;

            @Override
            public void doInForeground(final Message message) {
                if (!GsChannelListActivity.this.isInForeground) {
                    return;
                }
                try {
                    final byte[] byteArray = message.getData().getByteArray("ReceivedData");
                    if (byteArray == null) {
                        Log.e("GSChannelListActivity", "recvData = " + byteArray);
                        return;
                    }
                    this.verifyResult = (List<String>)GsChannelListActivity.this.parser.parse(new ByteArrayInputStream(byteArray, 0, byteArray.length), 15);
                    if (GsChannelListActivity.this.waitDialog.isShowing()) {
                        GsChannelListActivity.this.waitDialog.dismiss();
                    }
                    if (Integer.parseInt(this.verifyResult.get(0)) == 0) {
                        GsChannelListActivity.access$52(GsChannelListActivity.this, true);
                        GsChannelListActivity.this.inputPermissionPassword();
                        return;
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
                while (true) {
                    switch (GsChannelListActivity.this.passwordType) {
                        case 1: {
                            final ArrayList<DataConvertEditChannelLockModel> list = new ArrayList<DataConvertEditChannelLockModel>();
                            Label_0380: {
                                if (GsChannelListActivity.this.expandPosition == -1 || GsChannelListActivity.enable_edit) {
                                    break Label_0380;
                                }
                                final DataConvertChannelModel dataConvertChannelModel = GsChannelListActivity.this.mCurrentChannelList.get(GsChannelListActivity.this.expandPosition);
                                final DataConvertEditChannelLockModel dataConvertEditChannelLockModel = new DataConvertEditChannelLockModel();
                                dataConvertEditChannelLockModel.setProgramId(dataConvertChannelModel.GetProgramId());
                                dataConvertEditChannelLockModel.setmChannelType(dataConvertChannelModel.getChannelTpye());
                                list.add(dataConvertEditChannelLockModel);
                                while (true) {
                                    try {
                                        final byte[] bytes;
                                        Label_0265: {
                                            bytes = GsChannelListActivity.this.parser.serialize(list, 1003).getBytes("UTF-8");
                                        }
                                        GsChannelListActivity.this.tcpSocket.setSoTimeout(3000);
                                        GsSendSocket.sendSocketToStb(bytes, GsChannelListActivity.this.tcpSocket, 0, bytes.length, 1003);
                                        if (GsChannelListActivity.this.waitDialog.isShowing()) {
                                            GsChannelListActivity.this.waitDialog.dismiss();
                                        }
                                        GsChannelListActivity.access$16(GsChannelListActivity.this, DialogBuilder.showProgressDialog((Context)GsChannelListActivity.this.getParent(), GsChannelListActivity.this.getString(2131427518), GsChannelListActivity.this.getString(2131427520), true));
                                        break Label_0176;
                                        while (true) {
                                            final Iterator<DataConvertChannelModel> iterator;
                                            final DataConvertChannelModel dataConvertChannelModel2 = iterator.next();
                                            Label_0393: {
                                                Block_12: {
                                                    break Block_12;
                                                    iterator = GsChannelListActivity.this.mCurrentChannelList.iterator();
                                                    break Label_0393;
                                                }
                                                final DataConvertEditChannelLockModel dataConvertEditChannelLockModel2 = new DataConvertEditChannelLockModel();
                                                dataConvertEditChannelLockModel2.setProgramId(dataConvertChannelModel2.GetProgramId());
                                                dataConvertEditChannelLockModel2.setmChannelType(dataConvertChannelModel2.getChannelTpye());
                                                list.add(dataConvertEditChannelLockModel2);
                                            }
                                            continue;
                                        }
                                    }
                                    // iftrue(Label_0393:, !dataConvertChannelModel2.getSelectedFlag())
                                    // iftrue(Label_0265:, !iterator.hasNext())
                                    catch (Exception ex2) {
                                        ex2.printStackTrace();
                                        continue;
                                    }
                                    break;
                                }
                            }
                        }
                        case 3: {
                            GsChannelListActivity.this.playStream(GsChannelListActivity.this.sat2ipPlayPosition);
                        }
                        case 2: {
                            GsChannelListActivity.access$49(GsChannelListActivity.this, 0);
                        }
                        default: {
                            continue;
                        }
                    }
                    break;
                }
            }
        });
        this.msgProc.setOnMessageProcess(2006, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (GsChannelListActivity.this.isInForeground) {
                    GsChannelListActivity.access$49(GsChannelListActivity.this, 2);
                    if (GsChannelListActivity.this.pswInputDialog != null && GsChannelListActivity.this.pswInputDialog.isShowing()) {
                        GsChannelListActivity.this.pswInputDialog.dismiss();
                    }
                    if (GsChannelListActivity.this.waitDialog != null && GsChannelListActivity.this.waitDialog.isShowing()) {
                        GsChannelListActivity.this.waitDialog.dismiss();
                    }
                    GsChannelListActivity.this.inputPermissionPassword();
                }
            }
        });
        this.msgProc.setOnMessageProcess(2007, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (GsChannelListActivity.this.isInForeground) {
                    GsChannelListActivity.access$49(GsChannelListActivity.this, 0);
                    if (GsChannelListActivity.this.pswInputDialog != null && GsChannelListActivity.this.pswInputDialog.isShowing()) {
                        GsChannelListActivity.this.pswInputDialog.dismiss();
                    }
                    if (GsChannelListActivity.this.waitDialog != null && GsChannelListActivity.this.waitDialog.isShowing()) {
                        GsChannelListActivity.this.waitDialog.dismiss();
                    }
                }
            }
        });
        this.msgProc.setOnMessageProcess(22, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (message.arg1 > 0) {
                    final byte[] byteArray = message.getData().getByteArray("ReceivedData");
                    if (byteArray != null) {
                        GsChannelListActivity.this.mChannelData.initSatList(byteArray);
                        return;
                    }
                    Log.e("GSChannelListActivity", "recvData = " + byteArray);
                }
            }
        });
        this.msgProc.setOnMessageProcess(24, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (message.arg1 > 0) {
                    final byte[] byteArray = message.getData().getByteArray("ReceivedData");
                    if (byteArray != null) {
                        GsChannelListActivity.this.mChannelData.initTpList(byteArray);
                        return;
                    }
                    Log.e("GSChannelListActivity", "recvData = " + byteArray);
                }
            }
        });
        this.msgProc.setOnMessageProcess(23, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (message.arg1 > 0) {
                    final byte[] byteArray = message.getData().getByteArray("ReceivedData");
                    if (byteArray == null) {
                        Log.e("GSChannelListActivity", "recvData = " + byteArray);
                    }
                    else {
                        GsChannelListActivity.access$44(GsChannelListActivity.this, ParserFactory.getParser());
                        new ArrayList();
                        try {
                            GMScreenGlobalInfo.setmSatIndexSelected(Integer.parseInt((String)GsChannelListActivity.this.parser.parse(new ByteArrayInputStream(byteArray, 0, byteArray.length), 15).get(0)));
                            GsChannelListActivity.access$75(GsChannelListActivity.this, GsChannelListActivity.this.mChannelData.getChannelListByProgramType(GsChannelListActivity.this.mChannelData.getChannelListByTvRadioType(), GsChannelListActivity.this.currentChannelListType));
                            GsChannelListActivity.this.setCurrentChannelListDispIndex();
                            GsChannelListActivity.access$80(GsChannelListActivity.this, GsChannelListActivity.this.mCurrentChannelList);
                            if (GsChannelListActivity.this.mEnterSearchFlag) {
                                GsChannelListActivity.this.findChannel();
                                return;
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            return;
                        }
                        if (GsChannelListActivity.this.channelListAdapter != null) {
                            GsChannelListActivity.this.channelListAdapter.notifyDataSetChanged();
                            GsChannelListActivity.this.adjustSelectionOfChannelListView(false);
                        }
                    }
                }
            }
        });
        this.msgProc.setOnMessageProcess(2015, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                Toast.makeText((Context)GsChannelListActivity.this, 2131427644, 1).show();
                GsChannelListActivity.this.enableSomeFucitonWhenBecomeMaster();
            }
        });
        this.msgProc.setOnMessageProcess(1000, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                switch (message.arg2) {
                    default: {}
                    case 17: {
                        Toast.makeText((Context)GsChannelListActivity.this, 2131427645, 0).show();
                    }
                    case 16: {
                        Toast.makeText((Context)GsChannelListActivity.this, 2131427646, 0).show();
                    }
                }
            }
        });
        this.msgProc.setOnMessageProcess(2009, this.post);
        this.msgProc.setOnMessageProcess(2013, this.post);
        this.msgProc.setOnMessageProcess(2019, this.post);
        this.msgProc.setOnMessageProcess(4114, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                Toast.makeText((Context)GsChannelListActivity.this, 2131427597, 2000).show();
            }
        });
        this.msgProc.setOnMessageProcess(1002, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (message.arg1 > 0) {
                    final byte[] byteArray = message.getData().getByteArray("ReceivedData");
                    if (byteArray != null) {
                        GsChannelListActivity.access$44(GsChannelListActivity.this, ParserFactory.getParser());
                        new ArrayList();
                        if (GsChannelListActivity.this.waitDialog.isShowing()) {
                            GsChannelListActivity.this.waitDialog.dismiss();
                        }
                        int int1;
                        try {
                            int1 = Integer.parseInt((String)GsChannelListActivity.this.parser.parse(new ByteArrayInputStream(byteArray, 0, byteArray.length), 15).get(0));
                            if (int1 == 0) {
                                return;
                            }
                            if (int1 == 1) {
                                Toast.makeText((Context)GsChannelListActivity.this, (CharSequence)(String.valueOf(int1) + " channel is playing by the mobile, it can not be deleted! "), 0).show();
                                return;
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            return;
                        }
                        Toast.makeText((Context)GsChannelListActivity.this, (CharSequence)(String.valueOf(int1) + " channels are playing by the mobile, they can not be deleted! "), 0).show();
                        return;
                    }
                    Log.e("GSChannelListActivity", "recvData = " + byteArray);
                }
            }
        });
        this.msgProc.setOnMessageProcess(2014, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                GsSendSocket.sendOnlyCommandSocketToStb(GsChannelListActivity.this.tcpSocket, 20);
            }
        });
        this.msgProc.setOnMessageProcess(4118, this, (MessageProcessor.PerformOnBackground)new MessageProcessor.PerformOnBackground() {
            @Override
            public void doInBackground(final Message message) {
                if (new EditPhoneAndSmsRemindSettingFile((Context)GsChannelListActivity.this).getPhoneAndSmsRemindSetting()) {
                    GsChannelListActivity.this.setPhoneAndSmsListener();
                    return;
                }
                ((TelephonyManager)GsChannelListActivity.this.getSystemService("phone")).listen((PhoneStateListener)GsChannelListActivity.this.mPhoneListener, 0);
                GsChannelListActivity.this.mResolver.unregisterContentObserver((ContentObserver)GsChannelListActivity.this.mSMSContentObserver);
            }
        });
        this.msgProc.setOnMessageProcess(1100, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
            @Override
            public void doInForeground(final Message message) {
                if (GsChannelListActivity.this.waitDialog.isShowing()) {
                    GsChannelListActivity.this.waitDialog.dismiss();
                }
                if (message.arg1 > 0) {
                    try {
                        final Bundle data = message.getData();
                        GsChannelListActivity.access$44(GsChannelListActivity.this, ParserFactory.getParser());
                        final byte[] byteArray = data.getByteArray("ReceivedData");
                        if (byteArray == null) {
                            return;
                        }
                        GsChannelListActivity.this.parser.parse(new ByteArrayInputStream(byteArray, 0, byteArray.length), 21);
                        final Intent intent = new Intent();
                        intent.setClass((Context)GsChannelListActivity.this, (Class)GChatActivity.class);
                        GsChannelListActivity.this.startActivity(intent);
                        return;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                Toast.makeText((Context)GsChannelListActivity.this, 2131427712, 0).show();
            }
        });
        switch (GMScreenGlobalInfo.getCurStbPlatform()) {
            default: {
                this.msgProc.setOnMessageProcess(1009, this, (MessageProcessor.PerformOnForeground)new MessageProcessor.PerformOnForeground() {
                    @Override
                    public void doInForeground(final Message message) {
                        final byte[] byteArray = message.getData().getByteArray("ReceivedData");
                        if (byteArray != null && GsChannelListActivity.this.bPlayWithOherPlayer) {
                            GsChannelListActivity.access$44(GsChannelListActivity.this, ParserFactory.getParser());
                            Log.d("GsChannelListActivity", new String(byteArray));
                            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray, 0, message.arg1);
                            Map map;
                            try {
                                map = (Map)GsChannelListActivity.this.parser.parse(byteArrayInputStream, 16).get(0);
                                if (map.get("success") == null) {
                                    GsChannelListActivity.this.mainHandler.sendMessage(GsChannelListActivity.this.mainHandler.obtainMessage(4, (Object)GsChannelListActivity.this.getString(2131427592)));
                                    return;
                                }
                            }
                            catch (Exception ex) {
                                GsChannelListActivity.this.mainHandler.sendMessage(GsChannelListActivity.this.mainHandler.obtainMessage(4, (Object)GsChannelListActivity.this.getString(2131427592)));
                                ex.printStackTrace();
                                return;
                            }
                            if (map.get("success") != 1) {
                                String string = GsChannelListActivity.this.getString(2131427592);
                                if (map.get("errormsg") != null) {
                                    string = (String)map.get("errormsg");
                                }
                                else if (map.get("url") == null || ((String)map.get("url")).length() <= 0) {
                                    string = "Get play url fail";
                                }
                                GsChannelListActivity.this.mainHandler.sendMessage(GsChannelListActivity.this.mainHandler.obtainMessage(4, (Object)string));
                                return;
                            }
                            final String s = (String)map.get("url");
                            if (s == null) {
                                GsChannelListActivity.this.mainHandler.sendMessage(GsChannelListActivity.this.mainHandler.obtainMessage(4, (Object)GsChannelListActivity.this.getString(2131427592)));
                                return;
                            }
                            Log.d("GsChannelListActivity", "play url : " + s);
                            if (GsChannelListActivity.this.mPlayIntent != null) {
                                GsChannelListActivity.this.mPlayIntent.setDataAndType(Uri.parse(s), "video/*");
                                GsChannelListActivity.this.mainHandler.sendMessage(GsChannelListActivity.this.mainHandler.obtainMessage(3, (Object)GsChannelListActivity.this.mPlayIntent));
                            }
                        }
                    }
                });
            }
            case 8:
            case 9: {}
        }
    }

    private void setPhoneAndSmsListener() {
        ((TelephonyManager)this.getSystemService("phone")).listen((PhoneStateListener)this.mPhoneListener, 32);
        this.mUri = Uri.parse("content://sms");
        (this.mResolver = this.getContentResolver()).registerContentObserver(this.mUri, true, (ContentObserver)this.mSMSContentObserver);
    }

    private void setSearchMenuDisable() {
        this.mSearchChannelEdit.setVisibility(8);
        this.mSearchCancelBtn.setVisibility(8);
        this.mClearSearchKeyword.setVisibility(8);
        this.mSearchFailedPrompt.setVisibility(8);
        this.mEnterSearchFlag = false;
    }

    private void setSearchMenuEnable() {
        this.mSearchChannelEdit.setText((CharSequence)"");
        this.mSearchChannelEdit.setVisibility(0);
        this.mSearchChannelEdit.setFocusable(true);
        this.mSearchChannelEdit.setFocusableInTouchMode(true);
        this.mSearchChannelEdit.requestFocus();
        this.mSearchCancelBtn.setVisibility(0);
        this.mEnterSearchFlag = true;
    }

    private void showChannelTypePopupWindow(final View view) {
        final View inflate = ((LayoutInflater)this.getSystemService("layout_inflater")).inflate(2130903085, (ViewGroup)null);
        final ListView listView = (ListView)inflate.findViewById(2131362013);
        if (this.getChannelTypeData().size() < 4) {
            listView.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(dip2px((Context)this, 200.0f), dip2px((Context)this, 150.0f)));
        }
        else {
            listView.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(dip2px((Context)this, 200.0f), dip2px((Context)this, 205.0f)));
        }
        (this.typeAdapter = new ListviewAdapter(inflate.getContext(), this.getChannelTypeData())).setTextColor(this.getResources().getColor(2131165211));
        this.typeAdapter.setItemBackgroundResource(2130837999);
        listView.setAdapter((ListAdapter)this.typeAdapter);
        listView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                GsChannelListActivity.access$63(GsChannelListActivity.this, GsChannelListActivity.this.currentChannelListType);
                GsChannelListActivity.access$65(GsChannelListActivity.this, GsChannelListActivity.this.clickPosition2ListType(n));
                GsChannelListActivity.this.ChangeChannelListType(GsChannelListActivity.this.currentChannelListType, false);
                GsChannelListActivity.this.channelTypePopupWindow.dismiss();
                GsChannelListActivity.this.channelTypeArrow.setBackgroundResource(2130837669);
                GsChannelListActivity.this.synchronizeSTBChannelType(GsChannelListActivity.this.currentChannelListType);
            }
        });
        (this.channelTypePopupWindow = new PopupWindow(inflate, -2, -2, true)).setFocusable(true);
        this.channelTypePopupWindow.setOutsideTouchable(true);
        this.channelTypePopupWindow.setOnDismissListener((PopupWindow$OnDismissListener)new PopupWindow$OnDismissListener() {
            public void onDismiss() {
                GsChannelListActivity.this.channelTypeArrow.setBackgroundResource(2130837669);
            }
        });
        this.channelTypePopupWindow.setBackgroundDrawable((Drawable)new BitmapDrawable());
        this.channelTypePopupWindow.setTouchInterceptor((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                if (motionEvent.getAction() == 4) {
                    GsChannelListActivity.this.channelTypePopupWindow.dismiss();
                    GsChannelListActivity.this.channelTypeArrow.setBackgroundResource(2130837669);
                    return true;
                }
                return false;
            }
        });
        if (!this.channelTypePopupWindow.isShowing()) {
            final View viewById = this.findViewById(2131361979);
            final Rect rect = new Rect();
            this.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            this.channelTypePopupWindow.showAtLocation(view, 0, (this.getWindowManager().getDefaultDisplay().getWidth() - listView.getLayoutParams().width) / 2, rect.top + viewById.getHeight() + 1);
            this.channelTypeArrow.setBackgroundResource(2130837668);
        }
    }

    private void startPlayStream(final int n, final Intent intent) {
        if (this.waitDialog.isShowing()) {
            this.waitDialog.dismiss();
        }
        this.waitDialog = DialogBuilder.showProgressDialog(this.getParent(), 2131427641, 2131427520, false, GMScreenGlobalInfo.getmWaitDialogTimeOut(), this.timeOutRun);
        this.abtainPlayUrl(n, intent);
    }

    private void stopStream() {
        switch (GMScreenGlobalInfo.getCurStbPlatform()) {
            default: {
                GMScreenGlobalInfo.playType = 0;
                GsSendSocket.sendOnlyCommandSocketToStb(this.tcpSocket, 1012);
                break;
            }
            case 8:
            case 9: {
                if (GsChannelListActivity.sRtsp != null) {
                    GsChannelListActivity.sRtsp.teardown();
                    GsChannelListActivity.sRtsp = null;
                    DVBtoIP.destroyResourceForPlayer();
                    this.isSat2ipStarted = false;
                    this.currentSat2ipChannelProgramId = "";
                    GMScreenGlobalInfo.playType = 0;
                    GsSendSocket.sendOnlyCommandSocketToStb(this.tcpSocket, 1012);
                    return;
                }
                break;
            }
        }
    }

    private void synchronizeSTBChannelType(final int n) {
        Label_0084_Outer:
        while (true) {
            while (true) {
                Label_0236: {
                    Block_5_Outer:
                    while (true) {
                        DataConvertChannelTypeModel dataConvertChannelTypeModel;
                        try {
                            this.parser = ParserFactory.getParser();
                            final ArrayList<DataConvertChannelTypeModel> list = new ArrayList<DataConvertChannelTypeModel>();
                            dataConvertChannelTypeModel = new DataConvertChannelTypeModel();
                            switch (GMScreenGlobalInfo.getCurStbPlatform()) {
                                case 30:
                                case 31:
                                case 32:
                                case 71:
                                case 72:
                                case 74: {
                                    if (n >= 0 && n <= 3) {
                                        dataConvertChannelTypeModel.setIsFavList(0);
                                        dataConvertChannelTypeModel.setSelectListType(n);
                                        list.add(dataConvertChannelTypeModel);
                                        GsSendSocket.sendSocketToStb(this.dataBuff = this.parser.serialize(list, 1007).getBytes("UTF-8"), this.tcpSocket, 0, this.dataBuff.length, 1007);
                                        return;
                                    }
                                    goto Label_0184;
                                }
                                default: {
                                    break Label_0236;
                                }
                            }
                            while (true) {
                                dataConvertChannelTypeModel.setIsFavList(0);
                                dataConvertChannelTypeModel.setSelectListType(n);
                                continue Block_5_Outer;
                                continue Label_0084_Outer;
                            }
                        }
                        // iftrue(Label_0210:, n < 0 || n > 3)
                        catch (UnsupportedEncodingException ex) {
                            ex.printStackTrace();
                            return;
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                            return;
                        }
                        Label_0210: {
                            if (n >= 4 && n <= 11) {
                                dataConvertChannelTypeModel.setIsFavList(1);
                                dataConvertChannelTypeModel.setSelectListType(n - 4);
                                continue Label_0084_Outer;
                            }
                        }
                        break;
                    }
                    break;
                }
                continue;
            }
        }
    }

    private void trueNewChannelInStb(final int n) {
        try {
            final DataConvertChannelModel dataConvertChannelModel = (DataConvertChannelModel)this.channelListAdapter.getItem(n);
            final String programName = dataConvertChannelModel.getProgramName();
            final String getProgramId = dataConvertChannelModel.GetProgramId();
            final DataConvertChannelModel dataConvertChannelModel2 = new DataConvertChannelModel();
            final ArrayList<DataConvertChannelModel> list = new ArrayList<DataConvertChannelModel>();
            this.parser = ParserFactory.getParser();
            dataConvertChannelModel2.SetProgramIndex(n);
            dataConvertChannelModel2.setProgramName(programName);
            dataConvertChannelModel2.SetProgramId(getProgramId);
            dataConvertChannelModel2.setChannelTpye(dataConvertChannelModel.getChannelTpye());
            list.add(dataConvertChannelModel2);
            final byte[] bytes = this.parser.serialize(list, 1000).getBytes("UTF-8");
            this.tcpSocket.setSoTimeout(3000);
            GsSendSocket.sendSocketToStb(bytes, this.tcpSocket, 0, bytes.length, 1000);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1 && GsChannelListActivity.enable_edit) {
            if (this.isEnableMove) {
                this.editMoveMenu.setBackgroundResource(2130837717);
                this.isEnableMove = false;
                this.channelListAdapter.notifyDataSetChanged();
            }
            else {
                if (this.mEnterSearchFlag) {
                    this.setSearchMenuDisable();
                }
                this.exitEditMode();
                this.ChannelListView.setLongClickable(true);
                this.adjustSelectionOfChannelListView(true);
            }
            this.editBtn.setText(this.getResources().getText(2131427528));
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.d("GsChannelListActivity", "onConfigurationChanged");
    }

    protected void onCreate(final Bundle p0) {
        //
        // This method could not be decompiled.
        //
        // Original Bytecode:
        //
        //     1: aload_1
        //     2: invokespecial   android/app/Activity.onCreate:(Landroid/os/Bundle;)V
        //     5: aload_0
        //     6: aload_0
        //     7: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.getParent:()Landroid/app/Activity;
        //    10: invokestatic    android/view/LayoutInflater.from:(Landroid/content/Context;)Landroid/view/LayoutInflater;
        //    13: ldc_w           2130903084
        //    16: aconst_null
        //    17: invokevirtual   android/view/LayoutInflater.inflate:(ILandroid/view/ViewGroup;)Landroid/view/View;
        //    20: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.setContentView:(Landroid/view/View;)V
        //    23: aload_0
        //    24: invokespecial   mktvsmart/screen/channel/GsChannelListActivity.setMessageProcess:()V
        //    27: aload_0
        //    28: new             Landroid/os/Handler;
        //    31: dup
        //    32: aload_0
        //    33: getfield        mktvsmart/screen/channel/GsChannelListActivity.mMsgHandle:Landroid/os/Handler$Callback;
        //    36: invokespecial   android/os/Handler.<init>:(Landroid/os/Handler$Callback;)V
        //    39: putfield        mktvsmart/screen/channel/GsChannelListActivity.mainHandler:Landroid/os/Handler;
        //    42: aload_0
        //    43: invokestatic    mktvsmart/screen/channel/ChannelData.getInstance:()Lmktvsmart/screen/channel/ChannelData;
        //    46: putfield        mktvsmart/screen/channel/GsChannelListActivity.mChannelData:Lmktvsmart/screen/channel/ChannelData;
        //    49: new             Lmktvsmart/screen/EditPhoneAndSmsRemindSettingFile;
        //    52: dup
        //    53: aload_0
        //    54: invokespecial   mktvsmart/screen/EditPhoneAndSmsRemindSettingFile.<init>:(Landroid/content/Context;)V
        //    57: invokevirtual   mktvsmart/screen/EditPhoneAndSmsRemindSettingFile.getPhoneAndSmsRemindSetting:()Z
        //    60: ifeq            67
        //    63: aload_0
        //    64: invokespecial   mktvsmart/screen/channel/GsChannelListActivity.setPhoneAndSmsListener:()V
        //    67: invokestatic    mktvsmart/screen/GMScreenGlobalInfo.isClientTypeSlave:()Z
        //    70: ifeq            1039
        //    73: ldc_w           2131427642
        //    76: istore_2
        //    77: aload_0
        //    78: ldc_w           2131427643
        //    81: iconst_1
        //    82: invokestatic    android/widget/Toast.makeText:(Landroid/content/Context;II)Landroid/widget/Toast;
        //    85: invokevirtual   android/widget/Toast.show:()V
        //    88: aload_0
        //    89: aload_0
        //    90: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.getParent:()Landroid/app/Activity;
        //    93: iload_2
        //    94: ldc_w           2131427520
        //    97: iconst_1
        //    98: invokestatic    mktvsmart/screen/GMScreenGlobalInfo.getmWaitDialogTimeOut:()I
        //   101: i2l
        //   102: ldc_w           2131427637
        //   105: invokestatic    mktvsmart/screen/util/DialogBuilder.showProgressDialog:(Landroid/app/Activity;IIZJI)Lmktvsmart/screen/util/ADSProgressDialog;
        //   108: putfield        mktvsmart/screen/channel/GsChannelListActivity.waitDialog:Lmktvsmart/screen/util/ADSProgressDialog;
        //   111: aload_0
        //   112: aload_0
        //   113: ldc_w           2131361853
        //   116: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   119: checkcast       Landroid/widget/TextView;
        //   122: putfield        mktvsmart/screen/channel/GsChannelListActivity.titleText:Landroid/widget/TextView;
        //   125: aload_0
        //   126: getfield        mktvsmart/screen/channel/GsChannelListActivity.titleText:Landroid/widget/TextView;
        //   129: aload_0
        //   130: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.getResources:()Landroid/content/res/Resources;
        //   133: ldc_w           2131558412
        //   136: invokevirtual   android/content/res/Resources.getStringArray:(I)[Ljava/lang/String;
        //   139: aload_0
        //   140: getfield        mktvsmart/screen/channel/GsChannelListActivity.currentChannelListType:I
        //   143: aaload
        //   144: invokevirtual   android/widget/TextView.setText:(Ljava/lang/CharSequence;)V
        //   147: aload_0
        //   148: aload_0
        //   149: ldc_w           2131361992
        //   152: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   155: checkcast       Landroid/widget/ListView;
        //   158: putfield        mktvsmart/screen/channel/GsChannelListActivity.ChannelListView:Landroid/widget/ListView;
        //   161: aload_0
        //   162: aload_0
        //   163: ldc_w           2131361982
        //   166: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   169: checkcast       Landroid/widget/ImageView;
        //   172: putfield        mktvsmart/screen/channel/GsChannelListActivity.allSelectedBtn:Landroid/widget/ImageView;
        //   175: aload_0
        //   176: aload_0
        //   177: ldc_w           2131361981
        //   180: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   183: checkcast       Landroid/widget/LinearLayout;
        //   186: putfield        mktvsmart/screen/channel/GsChannelListActivity.allSelectedBtnLayout:Landroid/widget/LinearLayout;
        //   189: aload_0
        //   190: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.getIntent:()Landroid/content/Intent;
        //   193: astore_1
        //   194: aload_0
        //   195: aload_1
        //   196: ldc_w           "Address"
        //   199: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //   202: putfield        mktvsmart/screen/channel/GsChannelListActivity.NetAddress:Ljava/lang/String;
        //   205: aload_0
        //   206: aload_1
        //   207: ldc_w           "Port"
        //   210: sipush          20000
        //   213: invokevirtual   android/content/Intent.getIntExtra:(Ljava/lang/String;I)I
        //   216: putfield        mktvsmart/screen/channel/GsChannelListActivity.NetPort:I
        //   219: aload_0
        //   220: new             Lmktvsmart/screen/CreateSocket;
        //   223: dup
        //   224: aload_0
        //   225: getfield        mktvsmart/screen/channel/GsChannelListActivity.NetAddress:Ljava/lang/String;
        //   228: aload_0
        //   229: getfield        mktvsmart/screen/channel/GsChannelListActivity.NetPort:I
        //   232: invokespecial   mktvsmart/screen/CreateSocket.<init>:(Ljava/lang/String;I)V
        //   235: invokevirtual   mktvsmart/screen/CreateSocket.GetSocket:()Ljava/net/Socket;
        //   238: putfield        mktvsmart/screen/channel/GsChannelListActivity.tcpSocket:Ljava/net/Socket;
        //   241: aload_0
        //   242: aload_0
        //   243: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.getParent:()Landroid/app/Activity;
        //   246: ldc_w           2131362230
        //   249: invokevirtual   android/app/Activity.findViewById:(I)Landroid/view/View;
        //   252: checkcast       Landroid/widget/TabHost;
        //   255: putfield        mktvsmart/screen/channel/GsChannelListActivity.tabHost:Landroid/widget/TabHost;
        //   258: aload_0
        //   259: aload_0
        //   260: getfield        mktvsmart/screen/channel/GsChannelListActivity.tabHost:Landroid/widget/TabHost;
        //   263: invokevirtual   android/widget/TabHost.getTabWidget:()Landroid/widget/TabWidget;
        //   266: putfield        mktvsmart/screen/channel/GsChannelListActivity.tabWidget:Landroid/widget/TabWidget;
        //   269: aload_0
        //   270: aload_0
        //   271: ldc_w           2131361993
        //   274: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   277: checkcast       Landroid/widget/LinearLayout;
        //   280: putfield        mktvsmart/screen/channel/GsChannelListActivity.editMenu:Landroid/widget/LinearLayout;
        //   283: aload_0
        //   284: aload_0
        //   285: ldc_w           2131361994
        //   288: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   291: checkcast       Landroid/widget/LinearLayout;
        //   294: putfield        mktvsmart/screen/channel/GsChannelListActivity.editMoveMenu:Landroid/widget/LinearLayout;
        //   297: aload_0
        //   298: aload_0
        //   299: ldc_w           2131362000
        //   302: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   305: checkcast       Landroid/widget/LinearLayout;
        //   308: putfield        mktvsmart/screen/channel/GsChannelListActivity.editDeleteMenu:Landroid/widget/LinearLayout;
        //   311: aload_0
        //   312: aload_0
        //   313: ldc_w           2131362003
        //   316: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   319: checkcast       Landroid/widget/LinearLayout;
        //   322: putfield        mktvsmart/screen/channel/GsChannelListActivity.editLockMenu:Landroid/widget/LinearLayout;
        //   325: aload_0
        //   326: aload_0
        //   327: ldc_w           2131362006
        //   330: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   333: checkcast       Landroid/widget/LinearLayout;
        //   336: putfield        mktvsmart/screen/channel/GsChannelListActivity.editFavorMenu:Landroid/widget/LinearLayout;
        //   339: aload_0
        //   340: aload_0
        //   341: ldc_w           2131361997
        //   344: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   347: checkcast       Landroid/widget/LinearLayout;
        //   350: putfield        mktvsmart/screen/channel/GsChannelListActivity.editRenameMenu:Landroid/widget/LinearLayout;
        //   353: aload_0
        //   354: aload_0
        //   355: ldc_w           2131362009
        //   358: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   361: checkcast       Landroid/widget/LinearLayout;
        //   364: putfield        mktvsmart/screen/channel/GsChannelListActivity.mEditSortMenu:Landroid/widget/LinearLayout;
        //   367: aload_0
        //   368: aload_0
        //   369: ldc_w           2131361995
        //   372: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   375: checkcast       Landroid/widget/ImageView;
        //   378: putfield        mktvsmart/screen/channel/GsChannelListActivity.editMoveIcon:Landroid/widget/ImageView;
        //   381: aload_0
        //   382: aload_0
        //   383: ldc_w           2131362001
        //   386: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   389: checkcast       Landroid/widget/ImageView;
        //   392: putfield        mktvsmart/screen/channel/GsChannelListActivity.editDeleteIcon:Landroid/widget/ImageView;
        //   395: aload_0
        //   396: aload_0
        //   397: ldc_w           2131362004
        //   400: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   403: checkcast       Landroid/widget/ImageView;
        //   406: putfield        mktvsmart/screen/channel/GsChannelListActivity.editLockIcon:Landroid/widget/ImageView;
        //   409: aload_0
        //   410: aload_0
        //   411: ldc_w           2131362007
        //   414: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   417: checkcast       Landroid/widget/ImageView;
        //   420: putfield        mktvsmart/screen/channel/GsChannelListActivity.editFavorIcon:Landroid/widget/ImageView;
        //   423: aload_0
        //   424: aload_0
        //   425: ldc_w           2131361998
        //   428: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   431: checkcast       Landroid/widget/ImageView;
        //   434: putfield        mktvsmart/screen/channel/GsChannelListActivity.editRenameIcon:Landroid/widget/ImageView;
        //   437: aload_0
        //   438: aload_0
        //   439: ldc_w           2131362010
        //   442: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   445: checkcast       Landroid/widget/ImageView;
        //   448: putfield        mktvsmart/screen/channel/GsChannelListActivity.mEditSortIcon:Landroid/widget/ImageView;
        //   451: aload_0
        //   452: aload_0
        //   453: ldc_w           2131361996
        //   456: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   459: checkcast       Landroid/widget/TextView;
        //   462: putfield        mktvsmart/screen/channel/GsChannelListActivity.editMoveText:Landroid/widget/TextView;
        //   465: aload_0
        //   466: aload_0
        //   467: ldc_w           2131362002
        //   470: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   473: checkcast       Landroid/widget/TextView;
        //   476: putfield        mktvsmart/screen/channel/GsChannelListActivity.editDeleteText:Landroid/widget/TextView;
        //   479: aload_0
        //   480: aload_0
        //   481: ldc_w           2131362005
        //   484: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   487: checkcast       Landroid/widget/TextView;
        //   490: putfield        mktvsmart/screen/channel/GsChannelListActivity.editLockText:Landroid/widget/TextView;
        //   493: aload_0
        //   494: aload_0
        //   495: ldc_w           2131362008
        //   498: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   501: checkcast       Landroid/widget/TextView;
        //   504: putfield        mktvsmart/screen/channel/GsChannelListActivity.editFavorText:Landroid/widget/TextView;
        //   507: aload_0
        //   508: aload_0
        //   509: ldc_w           2131361999
        //   512: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   515: checkcast       Landroid/widget/TextView;
        //   518: putfield        mktvsmart/screen/channel/GsChannelListActivity.editRenameText:Landroid/widget/TextView;
        //   521: aload_0
        //   522: aload_0
        //   523: ldc_w           2131362011
        //   526: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   529: checkcast       Landroid/widget/TextView;
        //   532: putfield        mktvsmart/screen/channel/GsChannelListActivity.mEditSortText:Landroid/widget/TextView;
        //   535: aload_0
        //   536: aload_0
        //   537: ldc_w           2131361983
        //   540: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   543: checkcast       Landroid/widget/Button;
        //   546: putfield        mktvsmart/screen/channel/GsChannelListActivity.editBtn:Landroid/widget/Button;
        //   549: aload_0
        //   550: aload_0
        //   551: ldc_w           2131361984
        //   554: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   557: checkcast       Landroid/widget/Button;
        //   560: putfield        mktvsmart/screen/channel/GsChannelListActivity.DoneBtn:Landroid/widget/Button;
        //   563: aload_0
        //   564: aload_0
        //   565: ldc_w           2131361985
        //   568: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   571: checkcast       Landroid/widget/Button;
        //   574: putfield        mktvsmart/screen/channel/GsChannelListActivity.TypeSwitch:Landroid/widget/Button;
        //   577: aload_0
        //   578: aload_0
        //   579: ldc_w           2131361980
        //   582: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   585: checkcast       Landroid/widget/ImageView;
        //   588: putfield        mktvsmart/screen/channel/GsChannelListActivity.channelTypeArrow:Landroid/widget/ImageView;
        //   591: aload_0
        //   592: aload_0
        //   593: ldc_w           2131361986
        //   596: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   599: checkcast       Landroid/widget/LinearLayout;
        //   602: putfield        mktvsmart/screen/channel/GsChannelListActivity.mSearchChannelLayout:Landroid/widget/LinearLayout;
        //   605: aload_0
        //   606: aload_0
        //   607: ldc_w           2131361988
        //   610: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   613: checkcast       Landroid/widget/EditText;
        //   616: putfield        mktvsmart/screen/channel/GsChannelListActivity.mSearchChannelEdit:Landroid/widget/EditText;
        //   619: aload_0
        //   620: aload_0
        //   621: ldc_w           2131361989
        //   624: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   627: checkcast       Landroid/widget/ImageView;
        //   630: putfield        mktvsmart/screen/channel/GsChannelListActivity.mClearSearchKeyword:Landroid/widget/ImageView;
        //   633: aload_0
        //   634: aload_0
        //   635: ldc_w           2131361990
        //   638: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   641: checkcast       Landroid/widget/Button;
        //   644: putfield        mktvsmart/screen/channel/GsChannelListActivity.mSearchCancelBtn:Landroid/widget/Button;
        //   647: aload_0
        //   648: aload_0
        //   649: ldc_w           2131361991
        //   652: invokevirtual   mktvsmart/screen/channel/GsChannelListActivity.findViewById:(I)Landroid/view/View;
        //   655: checkcast       Landroid/widget/LinearLayout;
        //   658: putfield        mktvsmart/screen/channel/GsChannelListActivity.mSearchFailedPrompt:Landroid/widget/LinearLayout;
        //   661: aload_0
        //   662: invokespecial   mktvsmart/screen/channel/GsChannelListActivity.setSearchMenuDisable:()V
        //   665: aload_0
        //   666: invokestatic    mktvsmart/screen/dataconvert/parser/ParserFactory.getParser:()Lmktvsmart/screen/dataconvert/parser/DataParser;
        //   669: putfield        mktvsmart/screen/channel/GsChannelListActivity.parser:Lmktvsmart/screen/dataconvert/parser/DataParser;
        //   672: aload_0
        //   673: getfield        mktvsmart/screen/channel/GsChannelListActivity.titleText:Landroid/widget/TextView;
        //   676: new             Lmktvsmart/screen/channel/GsChannelListActivity$39;
        //   679: dup
        //   680: aload_0
        //   681: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$39.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   684: invokevirtual   android/widget/TextView.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   687: aload_0
        //   688: getfield        mktvsmart/screen/channel/GsChannelListActivity.tcpSocket:Ljava/net/Socket;
        //   691: sipush          8000
        //   694: invokevirtual   java/net/Socket.setSoTimeout:(I)V
        //   697: aload_0
        //   698: getfield        mktvsmart/screen/channel/GsChannelListActivity.ChannelListView:Landroid/widget/ListView;
        //   701: new             Lmktvsmart/screen/channel/GsChannelListActivity$40;
        //   704: dup
        //   705: aload_0
        //   706: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$40.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   709: invokevirtual   android/widget/ListView.setOnItemLongClickListener:(Landroid/widget/AdapterView$OnItemLongClickListener;)V
        //   712: aload_0
        //   713: getfield        mktvsmart/screen/channel/GsChannelListActivity.ChannelListView:Landroid/widget/ListView;
        //   716: new             Lmktvsmart/screen/channel/GsChannelListActivity$41;
        //   719: dup
        //   720: aload_0
        //   721: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$41.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   724: invokevirtual   android/widget/ListView.setOnItemClickListener:(Landroid/widget/AdapterView$OnItemClickListener;)V
        //   727: aload_0
        //   728: getfield        mktvsmart/screen/channel/GsChannelListActivity.mSearchChannelLayout:Landroid/widget/LinearLayout;
        //   731: new             Lmktvsmart/screen/channel/GsChannelListActivity$42;
        //   734: dup
        //   735: aload_0
        //   736: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$42.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   739: invokevirtual   android/widget/LinearLayout.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   742: aload_0
        //   743: getfield        mktvsmart/screen/channel/GsChannelListActivity.mSearchChannelEdit:Landroid/widget/EditText;
        //   746: new             Lmktvsmart/screen/channel/GsChannelListActivity$43;
        //   749: dup
        //   750: aload_0
        //   751: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$43.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   754: invokevirtual   android/widget/EditText.addTextChangedListener:(Landroid/text/TextWatcher;)V
        //   757: aload_0
        //   758: getfield        mktvsmart/screen/channel/GsChannelListActivity.mClearSearchKeyword:Landroid/widget/ImageView;
        //   761: new             Lmktvsmart/screen/channel/GsChannelListActivity$44;
        //   764: dup
        //   765: aload_0
        //   766: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$44.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   769: invokevirtual   android/widget/ImageView.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   772: aload_0
        //   773: getfield        mktvsmart/screen/channel/GsChannelListActivity.mSearchCancelBtn:Landroid/widget/Button;
        //   776: new             Lmktvsmart/screen/channel/GsChannelListActivity$45;
        //   779: dup
        //   780: aload_0
        //   781: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$45.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   784: invokevirtual   android/widget/Button.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   787: aload_0
        //   788: getfield        mktvsmart/screen/channel/GsChannelListActivity.editBtn:Landroid/widget/Button;
        //   791: new             Lmktvsmart/screen/channel/GsChannelListActivity$46;
        //   794: dup
        //   795: aload_0
        //   796: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$46.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   799: invokevirtual   android/widget/Button.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   802: aload_0
        //   803: getfield        mktvsmart/screen/channel/GsChannelListActivity.DoneBtn:Landroid/widget/Button;
        //   806: new             Lmktvsmart/screen/channel/GsChannelListActivity$47;
        //   809: dup
        //   810: aload_0
        //   811: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$47.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   814: invokevirtual   android/widget/Button.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   817: aload_0
        //   818: getfield        mktvsmart/screen/channel/GsChannelListActivity.TypeSwitch:Landroid/widget/Button;
        //   821: new             Lmktvsmart/screen/channel/GsChannelListActivity$48;
        //   824: dup
        //   825: aload_0
        //   826: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$48.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   829: invokevirtual   android/widget/Button.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   832: aload_0
        //   833: getfield        mktvsmart/screen/channel/GsChannelListActivity.allSelectedBtnLayout:Landroid/widget/LinearLayout;
        //   836: new             Lmktvsmart/screen/channel/GsChannelListActivity$49;
        //   839: dup
        //   840: aload_0
        //   841: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$49.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   844: invokevirtual   android/widget/LinearLayout.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   847: aload_0
        //   848: getfield        mktvsmart/screen/channel/GsChannelListActivity.editMoveMenu:Landroid/widget/LinearLayout;
        //   851: new             Lmktvsmart/screen/channel/GsChannelListActivity$50;
        //   854: dup
        //   855: aload_0
        //   856: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$50.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   859: invokevirtual   android/widget/LinearLayout.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   862: aload_0
        //   863: getfield        mktvsmart/screen/channel/GsChannelListActivity.editRenameMenu:Landroid/widget/LinearLayout;
        //   866: aload_0
        //   867: getfield        mktvsmart/screen/channel/GsChannelListActivity.mRenameMenuOnClickListener:Landroid/view/View$OnClickListener;
        //   870: invokevirtual   android/widget/LinearLayout.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   873: aload_0
        //   874: getfield        mktvsmart/screen/channel/GsChannelListActivity.editFavorMenu:Landroid/widget/LinearLayout;
        //   877: new             Lmktvsmart/screen/channel/GsChannelListActivity$51;
        //   880: dup
        //   881: aload_0
        //   882: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$51.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   885: invokevirtual   android/widget/LinearLayout.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   888: aload_0
        //   889: getfield        mktvsmart/screen/channel/GsChannelListActivity.editLockMenu:Landroid/widget/LinearLayout;
        //   892: new             Lmktvsmart/screen/channel/GsChannelListActivity$52;
        //   895: dup
        //   896: aload_0
        //   897: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$52.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   900: invokevirtual   android/widget/LinearLayout.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   903: aload_0
        //   904: getfield        mktvsmart/screen/channel/GsChannelListActivity.editDeleteMenu:Landroid/widget/LinearLayout;
        //   907: new             Lmktvsmart/screen/channel/GsChannelListActivity$53;
        //   910: dup
        //   911: aload_0
        //   912: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$53.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   915: invokevirtual   android/widget/LinearLayout.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   918: aload_0
        //   919: getfield        mktvsmart/screen/channel/GsChannelListActivity.mEditSortMenu:Landroid/widget/LinearLayout;
        //   922: new             Lmktvsmart/screen/channel/GsChannelListActivity$54;
        //   925: dup
        //   926: aload_0
        //   927: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$54.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   930: invokevirtual   android/widget/LinearLayout.setOnClickListener:(Landroid/view/View$OnClickListener;)V
        //   933: aload_0
        //   934: getfield        mktvsmart/screen/channel/GsChannelListActivity.ChannelListView:Landroid/widget/ListView;
        //   937: new             Lmktvsmart/screen/channel/GsChannelListActivity$55;
        //   940: dup
        //   941: aload_0
        //   942: invokespecial   mktvsmart/screen/channel/GsChannelListActivity$55.<init>:(Lmktvsmart/screen/channel/GsChannelListActivity;)V
        //   945: invokevirtual   android/widget/ListView.setOnScrollListener:(Landroid/widget/AbsListView$OnScrollListener;)V
        //   948: invokestatic    mktvsmart/screen/GMScreenGlobalInfo.isClientTypeSlave:()Z
        //   951: ifeq            958
        //   954: aload_0
        //   955: invokespecial   mktvsmart/screen/channel/GsChannelListActivity.disableSomeFunctionWhenSlave:()V
        //   958: aload_0
        //   959: getfield        mktvsmart/screen/channel/GsChannelListActivity.tcpSocket:Ljava/net/Socket;
        //   962: bipush          23
        //   964: invokestatic    mktvsmart/screen/GsSendSocket.sendOnlyCommandSocketToStb:(Ljava/net/Socket;I)Z
        //   967: pop
        //   968: aload_0
        //   969: getfield        mktvsmart/screen/channel/GsChannelListActivity.tcpSocket:Ljava/net/Socket;
        //   972: bipush          16
        //   974: invokestatic    mktvsmart/screen/GsSendSocket.sendOnlyCommandSocketToStb:(Ljava/net/Socket;I)Z
        //   977: pop
        //   978: aload_0
        //   979: getfield        mktvsmart/screen/channel/GsChannelListActivity.tcpSocket:Ljava/net/Socket;
        //   982: bipush          20
        //   984: invokestatic    mktvsmart/screen/GsSendSocket.sendOnlyCommandSocketToStb:(Ljava/net/Socket;I)Z
        //   987: pop
        //   988: invokestatic    mktvsmart/screen/GMScreenGlobalInfo.getCurStbInfo:()Lmktvsmart/screen/GsMobileLoginInfo;
        //   991: invokevirtual   mktvsmart/screen/GsMobileLoginInfo.getmSatEnable:()I
        //   994: iconst_1
        //   995: if_icmpne       1008
        //   998: aload_0
        //   999: getfield        mktvsmart/screen/channel/GsChannelListActivity.tcpSocket:Ljava/net/Socket;
        //  1002: bipush          22
        //  1004: invokestatic    mktvsmart/screen/GsSendSocket.sendOnlyCommandSocketToStb:(Ljava/net/Socket;I)Z
        //  1007: pop
        //  1008: aload_0
        //  1009: getfield        mktvsmart/screen/channel/GsChannelListActivity.tcpSocket:Ljava/net/Socket;
        //  1012: bipush          24
        //  1014: invokestatic    mktvsmart/screen/GsSendSocket.sendOnlyCommandSocketToStb:(Ljava/net/Socket;I)Z
        //  1017: pop
        //  1018: aload_0
        //  1019: iconst_0
        //  1020: invokestatic    mktvsmart/screen/GMScreenGlobalInfo.getMaxProgramNumPerRequest:()I
        //  1023: iconst_1
        //  1024: isub
        //  1025: invokespecial   mktvsmart/screen/channel/GsChannelListActivity.requestProgramListFromTo:(II)V
        //  1028: aload_0
        //  1029: getfield        mktvsmart/screen/channel/GsChannelListActivity.tcpSocket:Ljava/net/Socket;
        //  1032: bipush          12
        //  1034: invokestatic    mktvsmart/screen/GsSendSocket.sendOnlyCommandSocketToStb:(Ljava/net/Socket;I)Z
        //  1037: pop
        //  1038: return
        //  1039: ldc_w           2131427514
        //  1042: istore_2
        //  1043: goto            88
        //  1046: astore_1
        //  1047: aload_1
        //  1048: invokevirtual   java/lang/Exception.printStackTrace:()V
        //  1051: goto            241
        //  1054: astore_1
        //  1055: aload_1
        //  1056: invokevirtual   java/lang/Exception.printStackTrace:()V
        //  1059: goto            948
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ---------------------
        //  219    241    1046   1054   Ljava/lang/Exception;
        //  687    948    1054   1062   Ljava/lang/Exception;
        //
        // The error that occurred was:
        //
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0948:
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
        //     at us.deathmarine.luyten.Model.openEntryByTreePath(Model.java:266)
        //     at us.deathmarine.luyten.Model.access$500(Model.java:65)
        //     at us.deathmarine.luyten.Model$TreeListener$1.run(Model.java:222)
        //
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }

    protected void onDestroy() {
        super.onDestroy();
        this.waitDialog.dismiss();
        this.msgProc.recycle();
        this.msgProc.removeProcessCallback(null);
        this.stopStream();
        this.mChannelData.clearTVRadioProgramList();
    }

    protected void onPause() {
        super.onPause();
        this.isInForeground = false;
    }

    protected void onResume() {
        super.onResume();
        this.isInForeground = true;
        this.stopStream();
        GsSendSocket.sendOnlyCommandSocketToStb(this.tcpSocket, 20);
    }

    public void onTabActivityResult(final int n, final int n2, final Intent intent) {
        switch (n2) {
            default: {}
            case 20: {
                this.isFavorChange = intent.getBooleanExtra("favorChange", true);
                Label_0166: {
                    if (!this.isFavorChange) {
                        break Label_0166;
                    }
                    this.favMark = intent.getIntExtra("favorValue", 0);
                    final Iterator<DataConvertChannelModel> iterator = this.favorModels.iterator();
                    Label_0124_Outer:
                    while (true) {
                        Label_0178: {
                            if (iterator.hasNext()) {
                                break Label_0178;
                            }
                            this.favMark = 0;
                            while (true) {
                                try {
                                    final byte[] bytes = this.parser.serialize(this.favorModels, 1004).getBytes("UTF-8");
                                    this.tcpSocket.setSoTimeout(3000);
                                    GsSendSocket.sendSocketToStb(bytes, this.tcpSocket, 0, bytes.length, 1004);
                                    if (this.waitDialog.isShowing()) {
                                        this.waitDialog.dismiss();
                                    }
                                    this.waitDialog = DialogBuilder.showProgressDialog(this.getParent(), 2131427517, 2131427520, false, GMScreenGlobalInfo.getmWaitDialogTimeOut(), 2131427638);
                                    this.initItemChecked();
                                    this.channelListAdapter.notifyDataSetChanged();
                                    return;
                                    iterator.next().SetFavMark(this.favMark);
                                    continue Label_0124_Outer;
                                }
                                catch (UnsupportedEncodingException ex) {
                                    ex.printStackTrace();
                                    continue;
                                }
                                catch (Exception ex2) {
                                    ex2.printStackTrace();
                                    continue;
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
    }

    private class MyPhoneStateListener extends PhoneStateListener
    {
        private int mTimeMark;

        private MyPhoneStateListener() {
            this.mTimeMark = 0;
        }

        public void onCallStateChanged(final int n, final String data) {
            Log.e("PhoneCallState", "state " + n);
            Log.e("PhoneCallState", "Incoming number " + data);
            final Time time = new Time();
            time.setToNow();
            final int mTimeMark = time.hour * 60 * 60 + time.minute * 60 + time.second;
            if (n != 1 || data.length() <= 0 || mTimeMark - this.mTimeMark <= 2) {
                return;
            }
            final DataConvertOneDataModel dataConvertOneDataModel = new DataConvertOneDataModel();
            final ArrayList<DataConvertOneDataModel> list = new ArrayList<DataConvertOneDataModel>();
            dataConvertOneDataModel.setData(data);
            list.add(dataConvertOneDataModel);
            while (true) {
                try {
                    final byte[] bytes = GsChannelListActivity.this.parser.serialize(list, 1061).getBytes("UTF-8");
                    GsSendSocket.sendSocketToStb(bytes, GsChannelListActivity.this.tcpSocket, 0, bytes.length, 1061);
                    this.mTimeMark = mTimeMark;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
        }
    }

    private class SMSContentObserver extends ContentObserver
    {
        public SMSContentObserver() {
            super(new Handler());
        }

        public void onChange(final boolean b) {
            super.onChange(b);
            final Cursor query = GsChannelListActivity.this.mResolver.query(GsChannelListActivity.this.mUri, (String[])null, (String)null, (String[])null, "_id DESC LIMIT 1");
            while (query.moveToNext()) {
                final String string = query.getString(query.getColumnIndex("address"));
                if (query.getInt(query.getColumnIndex("type")) == 1) {
                    final DataConvertOneDataModel dataConvertOneDataModel = new DataConvertOneDataModel();
                    final ArrayList<DataConvertOneDataModel> list = new ArrayList<DataConvertOneDataModel>();
                    dataConvertOneDataModel.setData(string);
                    list.add(dataConvertOneDataModel);
                    try {
                        final byte[] bytes = GsChannelListActivity.this.parser.serialize(list, 1062).getBytes("UTF-8");
                        GsSendSocket.sendSocketToStb(bytes, GsChannelListActivity.this.tcpSocket, 0, bytes.length, 1062);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    private class grid_adapter extends BaseAdapter
    {
        private Integer[] favImage;
        LayoutInflater inflater;

        public grid_adapter(final Context context) {
            this.favImage = new Integer[] { 2130837968, 2130837966, 2130837967, 2130838181, 2130837723, 2130838222, 2130837672, 2130837703 };
            this.inflater = LayoutInflater.from(context);
        }

        public void chiceState(final int n) {
            if (GsChannelListActivity.this.mIsChoice[n]) {
                GsChannelListActivity.this.mIsChoice[n] = false;
            }
            else {
                GsChannelListActivity.this.mIsChoice[n] = true;
            }
            this.notifyDataSetChanged();
        }

        public int getCount() {
            return GMScreenGlobalInfo.favType.size();
        }

        public Object getItem(final int n) {
            return GMScreenGlobalInfo.favType.get(n);
        }

        public long getItemId(final int n) {
            return n;
        }

        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            View inflate = view;
            if (view == null) {
                inflate = this.inflater.inflate(2130903157, (ViewGroup)null);
            }
            final ImageView imageView = (ImageView)inflate.findViewById(2131362364);
            final ImageView imageView2 = (ImageView)inflate.findViewById(2131362363);
            final TextView textView = (TextView)inflate.findViewById(2131362365);
            if (n == viewGroup.getChildCount()) {
                if (GsChannelListActivity.this.mIsChoice[n]) {
                    imageView.setImageResource(2130837732);
                    textView.setBackgroundResource(2130837734);
                }
                else {
                    imageView.setImageResource(2130837733);
                    textView.setBackgroundResource(2130837735);
                }
                imageView2.setImageResource((int)this.favImage[n]);
                textView.setText((CharSequence)GMScreenGlobalInfo.favType.get(n));
            }
            return inflate;
        }
    }

    private class list_single_button_adapter extends BaseAdapter
    {
        LayoutInflater inflater;

        public list_single_button_adapter(final Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        private void controlButtomMenu() {
            final int access$39 = GsChannelListActivity.this.GetSelectedNum(GsChannelListActivity.this.mCurrentChannelList);
            if (access$39 == 0) {
                this.setAllSelectedBtndisplay(false);
                this.setEditMoveDisplay(false);
                this.setEditDeleteDisplay(false);
                this.setEditLockDisplay(false);
                this.setEditRenameDisplay(false);
                this.setEditFavDisplay(false);
                this.setEditSortDisplay(true);
                return;
            }
            if (access$39 == 1) {
                this.setAllSelectedBtndisplay(false);
                this.setEditMoveDisplay(true);
                this.setEditDeleteDisplay(true);
                this.setEditLockDisplay(true);
                this.setEditRenameDisplay(true);
                this.setEditFavDisplay(true);
                this.setEditSortDisplay(false);
                return;
            }
            if (access$39 == GsChannelListActivity.this.mCurrentChannelList.size()) {
                this.setAllSelectedBtndisplay(true);
                this.setEditMoveDisplay(false);
                this.setEditDeleteDisplay(true);
                this.setEditLockDisplay(true);
                this.setEditRenameDisplay(false);
                this.setEditFavDisplay(true);
                this.setEditSortDisplay(false);
                return;
            }
            this.setAllSelectedBtndisplay(false);
            this.setEditMoveDisplay(true);
            this.setEditDeleteDisplay(true);
            this.setEditLockDisplay(true);
            this.setEditRenameDisplay(false);
            this.setEditFavDisplay(true);
            this.setEditSortDisplay(false);
        }

        public int getCount() {
            if (GsChannelListActivity.enable_edit) {
                return GsChannelListActivity.this.mCurrentChannelList.size();
            }
            return GsChannelListActivity.this.mCurrentChannelList.size();
        }

        public Object getItem(final int n) {
            if (GsChannelListActivity.enable_edit) {
                return GsChannelListActivity.this.mCurrentChannelList.get(n);
            }
            return GsChannelListActivity.this.mCurrentChannelList.get(n);
        }

        public long getItemId(final int n) {
            return n;
        }

        public View getView(final int n, View inflate, ViewGroup tag) {
            while (true) {
                Label_0841_Outer:
                while (true) {
                    Object editSelectLayout = null;
                    Label_0841:
                    while (true) {
                        Label_0931:Label_1023_Outer:
                        while (true) {
                            Label_0488: {
                                Label_1010_Outer:
                                while (true) {
                                    Label_0456: {
                                        while (true) {
                                            Label_0425: {
                                                while (true) {
                                                    Label_0273: {
                                                        if (inflate == null) {
                                                            tag = (ViewGroup)new ViewHolder((ViewHolder)null);
                                                            inflate = this.inflater.inflate(2130903086, (ViewGroup)null);
                                                            ((ViewHolder)tag).linearLayoutProgIndex = (LinearLayout)inflate.findViewById(2131362015);
                                                            ((ViewHolder)tag).progIndex = (TextView)inflate.findViewById(2131362016);
                                                            ((ViewHolder)tag).progName = (TextView)inflate.findViewById(2131362018);
                                                            ((ViewHolder)tag).editSelectLayout = (LinearLayout)inflate.findViewById(2131362027);
                                                            ((ViewHolder)tag).editSelect = (ImageView)inflate.findViewById(2131362028);
                                                            ((ViewHolder)tag).relativeLayoutProgName = (RelativeLayout)inflate.findViewById(2131362017);
                                                            ((ViewHolder)tag).scrambleIcon = (ImageView)inflate.findViewById(2131362021);
                                                            ((ViewHolder)tag).favorIcon = (ImageView)inflate.findViewById(2131362022);
                                                            ((ViewHolder)tag).lockIcon = (ImageView)inflate.findViewById(2131362023);
                                                            ((ViewHolder)tag).moreButtonLayout = (LinearLayout)inflate.findViewById(2131362024);
                                                            ((ViewHolder)tag).moreBtnDemarcation = (ImageView)inflate.findViewById(2131362025);
                                                            editSelectLayout = ((ViewHolder)tag).moreButtonLayout.findViewById(2131362026);
                                                            ((ViewHolder)tag).moreMenuLayout = (LinearLayout)inflate.findViewById(2131362029);
                                                            ((ViewHolder)tag).moreEpgMenu = (TextView)inflate.findViewById(2131362031);
                                                            ((ViewHolder)tag).morePlayMenu = (TextView)inflate.findViewById(2131362030);
                                                            ((ViewHolder)tag).moreChatMenu = (TextView)inflate.findViewById(2131362032);
                                                            ((ViewHolder)tag).moreRenameMenu = (TextView)inflate.findViewById(2131362033);
                                                            ((ViewHolder)tag).moreDeleteMenu = (TextView)inflate.findViewById(2131362034);
                                                            ((ViewHolder)tag).moreLockMenu = (TextView)inflate.findViewById(2131362035);
                                                            break Label_0273;
                                                        }
                                                        Label_0972: {
                                                            break Label_0972;
                                                            while (true) {
                                                                Label_1166: {
                                                                    try {
                                                                        if (GMScreenGlobalInfo.getCurStbInfo().getmSat2ipEnable() != 2 && GsChannelListActivity.this.mChannelData.canSat2ipChannelPlay(GsChannelListActivity.this.mChannelData.getCurrentPlayingProgram(), GsChannelListActivity.this.mCurrentChannelList.get(n))) {
                                                                            ((ViewHolder)tag).morePlayMenu.setClickable(true);
                                                                            ((ViewHolder)tag).morePlayMenu.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165234));
                                                                            ((ViewHolder)tag).morePlayMenu.setCompoundDrawablesWithIntrinsicBounds(0, 2130837658, 0, 0);
                                                                        }
                                                                        else {
                                                                            ((ViewHolder)tag).morePlayMenu.setClickable(false);
                                                                            ((ViewHolder)tag).morePlayMenu.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165235));
                                                                            ((ViewHolder)tag).morePlayMenu.setCompoundDrawablesWithIntrinsicBounds(0, 2130837659, 0, 0);
                                                                        }
                                                                        if (GsChannelListActivity.this.mCurrentChannelList.get(n).getIsPlaying() == 1) {
                                                                            ((ViewHolder)tag).relativeLayoutProgName.setBackgroundResource(2130837923);
                                                                            ((ViewHolder)tag).linearLayoutProgIndex.setBackgroundResource(2130837924);
                                                                            ((ViewHolder)tag).progName.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165211));
                                                                            ((ImageView)editSelectLayout).setBackgroundResource(2130837662);
                                                                            ((ViewHolder)tag).moreButtonLayout.setBackgroundResource(2130837924);
                                                                            ((ViewHolder)tag).moreBtnDemarcation.setVisibility(8);
                                                                            inflate.setTag((Object)tag);
                                                                            GsChannelListActivity.access$57(GsChannelListActivity.this, GsChannelListActivity.this.ChannelListView.getFirstVisiblePosition());
                                                                            GsChannelListActivity.access$58(GsChannelListActivity.this, GsChannelListActivity.this.ChannelListView.getLastVisiblePosition());
                                                                            return inflate;
                                                                        }
                                                                        break Label_1166;
                                                                        ((ViewHolder)tag).scrambleIcon.setBackgroundResource(2130838157);
                                                                        break Label_0425;
                                                                        ((ViewHolder)tag).lockIcon.setBackgroundResource(2130837934);
                                                                        break Label_0488;
                                                                        tag = (ViewGroup)inflate.getTag();
                                                                        editSelectLayout = ((ViewHolder)tag).moreButtonLayout.findViewById(2131362026);
                                                                        break;
                                                                        ((ViewHolder)tag).favorIcon.setBackgroundResource(2130837741);
                                                                        break Label_0456;
                                                                        ((ViewHolder)tag).moreEpgMenu.setClickable(false);
                                                                        ((ViewHolder)tag).moreEpgMenu.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165235));
                                                                        ((ViewHolder)tag).moreEpgMenu.setCompoundDrawablesWithIntrinsicBounds(0, 2130837656, 0, 0);
                                                                        continue Label_0841_Outer;
                                                                    }
                                                                    catch (ProgramNotFoundException ex) {
                                                                        Log.d("ProgramNotFoundException", ex.getMessage());
                                                                        ex.printStackTrace();
                                                                        continue Label_0841;
                                                                    }
                                                                    continue Label_0841;
                                                                }
                                                                ((ViewHolder)tag).relativeLayoutProgName.setBackgroundResource(2130837710);
                                                                ((ViewHolder)tag).linearLayoutProgIndex.setBackgroundResource(2130837711);
                                                                ((ViewHolder)tag).progName.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165210));
                                                                ((ViewHolder)tag).moreButtonLayout.setBackgroundResource(GsChannelListActivity.this.getResources().getColor(2131165217));
                                                                ((ViewHolder)tag).moreBtnDemarcation.setVisibility(0);
                                                                continue Label_0931;
                                                            }
                                                        }
                                                    }
                                                    ((ViewHolder)tag).moreEpgMenu.setTag((Object)n);
                                                    ((ViewHolder)tag).morePlayMenu.setTag((Object)n);
                                                    ((ViewHolder)tag).moreChatMenu.setTag((Object)n);
                                                    ((ViewHolder)tag).moreButtonLayout.setTag((Object)n);
                                                    ((ViewHolder)tag).editSelectLayout.setTag((Object)n);
                                                    ((ViewHolder)tag).progIndex.setText((CharSequence)new StringBuilder(String.valueOf(GsChannelListActivity.this.mCurrentChannelList.get(n).getCurrentChannelListDispIndex())).toString());
                                                    ((ViewHolder)tag).progName.setText((CharSequence)GsChannelListActivity.this.mCurrentChannelList.get(n).getProgramName());
                                                    if (GsChannelListActivity.this.mCurrentChannelList.get(n).GetIsProgramScramble() != 1) {
                                                        continue Label_1023_Outer;
                                                    }
                                                    break;
                                                }
                                                ((ViewHolder)tag).scrambleIcon.setBackgroundResource(2130838156);
                                            }
                                            if (GsChannelListActivity.this.mCurrentChannelList.get(n).GetFavMark() == 0) {
                                                continue;
                                            }
                                            break;
                                        }
                                        ((ViewHolder)tag).favorIcon.setBackgroundResource(2130837740);
                                    }
                                    if (GsChannelListActivity.this.mCurrentChannelList.get(n).getLockMark() == 0) {
                                        continue Label_1010_Outer;
                                    }
                                    break;
                                }
                                ((ViewHolder)tag).lockIcon.setBackgroundResource(2130837933);
                            }
                            if (GsChannelListActivity.enable_edit) {
                                ((ViewHolder)tag).moreButtonLayout.setVisibility(8);
                                ((ViewHolder)tag).moreMenuLayout.setVisibility(8);
                                ((ViewHolder)tag).editSelect.setVisibility(0);
                                if (n == GsChannelListActivity.this.longClickPos) {
                                    ((ViewHolder)tag).editSelect.setImageResource(2130837670);
                                    GsChannelListActivity.this.mCurrentChannelList.get(GsChannelListActivity.this.longClickPos).setSelectedFlag(true);
                                    GsChannelListActivity.access$55(GsChannelListActivity.this, -1);
                                }
                                ((ViewHolder)tag).relativeLayoutProgName.setBackgroundResource(2130837710);
                                ((ViewHolder)tag).linearLayoutProgIndex.setBackgroundResource(2130837711);
                                ((ViewHolder)tag).progName.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165210));
                                if (GsChannelListActivity.this.mCurrentChannelList.get(n).getSelectedFlag()) {
                                    ((ViewHolder)tag).editSelect.setImageResource(2130837670);
                                }
                                else {
                                    ((ViewHolder)tag).editSelect.setImageResource(2130837671);
                                }
                                editSelectLayout = ((ViewHolder)tag).editSelectLayout;
                                ((LinearLayout)editSelectLayout).setEnabled(!GsChannelListActivity.this.isEnableMove);
                                this.controlButtomMenu();
                                ((ViewHolder)tag).editSelectLayout.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                                    public void onClick(final View view) {
                                        final int intValue = (int)view.getTag();
                                        if (((DataConvertChannelModel)GsChannelListActivity.this.mCurrentChannelList.get(intValue)).getSelectedFlag()) {
                                            ((DataConvertChannelModel)GsChannelListActivity.this.mCurrentChannelList.get(intValue)).setSelectedFlag(false);
                                        }
                                        else {
                                            ((DataConvertChannelModel)GsChannelListActivity.this.mCurrentChannelList.get(intValue)).setSelectedFlag(true);
                                        }
                                        list_single_button_adapter.this.controlButtomMenu();
                                        list_single_button_adapter.this.notifyDataSetChanged();
                                    }
                                });
                                continue Label_0931;
                            }
                            break;
                        }
                        ((ViewHolder)tag).editSelect.setVisibility(8);
                        ((ViewHolder)tag).moreButtonLayout.setVisibility(0);
                        ((ViewHolder)tag).editSelect.setImageResource(2130837671);
                        GsChannelListActivity.this.mCurrentChannelList.get(n).setSelectedFlag(false);
                        ((ViewHolder)tag).moreButtonLayout.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                            public void onClick(final View view) {
                                final int intValue = (int)view.getTag();
                                if (GsChannelListActivity.this.expandPosition != intValue) {
                                    GsChannelListActivity.access$40(GsChannelListActivity.this, intValue);
                                }
                                else {
                                    GsChannelListActivity.access$40(GsChannelListActivity.this, -1);
                                }
                                ((list_single_button_adapter)GsChannelListActivity.this.ChannelListView.getAdapter()).notifyDataSetChanged();
                            }
                        });
                        if (n != GsChannelListActivity.this.expandPosition) {
                            ((ImageView)editSelectLayout).setBackgroundResource(2130837652);
                            ((ViewHolder)tag).moreMenuLayout.setVisibility(8);
                            continue Label_0841;
                        }
                        break;
                    }
                    ((ViewHolder)tag).moreEpgMenu.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            final int intValue = (int)view.getTag();
                            if (GsChannelListActivity.this.waitDialog.isShowing()) {
                                GsChannelListActivity.this.waitDialog.dismiss();
                            }
                            GsChannelListActivity.access$16(GsChannelListActivity.this, DialogBuilder.showProgressDialog(GsChannelListActivity.this.getParent(), 2131427514, 2131427520, false, GMScreenGlobalInfo.getmWaitDialogTimeOut(), 2131427637));
                            final DataConvertChannelModel dataConvertChannelModel = (DataConvertChannelModel)GsChannelListActivity.this.channelListAdapter.getItem(intValue);
                            GsChannelListActivity.access$42(GsChannelListActivity.this, dataConvertChannelModel.GetProgramId());
                            GsChannelListActivity.access$43(GsChannelListActivity.this, dataConvertChannelModel.getProgramName());
                            final DataConvertChannelModel dataConvertChannelModel2 = new DataConvertChannelModel();
                            final ArrayList<DataConvertChannelModel> list = new ArrayList<DataConvertChannelModel>();
                            GsChannelListActivity.access$44(GsChannelListActivity.this, ParserFactory.getParser());
                            dataConvertChannelModel2.SetProgramId(GsChannelListActivity.this.epg_program_sat_tp_id);
                            list.add(dataConvertChannelModel2);
                            try {
                                final byte[] bytes = GsChannelListActivity.this.parser.serialize(list, 5).getBytes("UTF-8");
                                GsChannelListActivity.this.tcpSocket.setSoTimeout(3000);
                                GsChannelListActivity.this.in = GsChannelListActivity.this.tcpSocket.getInputStream();
                                GsSendSocket.sendSocketToStb(bytes, GsChannelListActivity.this.tcpSocket, 0, bytes.length, 5);
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    ((ViewHolder)tag).morePlayMenu.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            final int intValue = (int)view.getTag();
                            if (GsChannelListActivity.this.controlModels != null && GsChannelListActivity.this.controlModels.size() > 0) {
                                if (GsChannelListActivity.this.controlModels.get(0).GetPowerOff() == 0) {
                                    final CommonCofirmDialog commonCofirmDialog = new CommonCofirmDialog((Context)GsChannelListActivity.this.getParent());
                                    commonCofirmDialog.setmTitle(GsChannelListActivity.this.getResources().getString(2131427601));
                                    commonCofirmDialog.setmContent(GsChannelListActivity.this.getResources().getString(2131427699));
                                    commonCofirmDialog.setOnButtonClickListener(GsChannelListActivity.this.mStbInStandbyOnClickListener);
                                    commonCofirmDialog.show();
                                }
                                else {
                                    if (GsChannelListActivity.this.controlModels.get(0).GetPswLockSwitch() == 0 || ((DataConvertChannelModel)GsChannelListActivity.this.mCurrentChannelList.get(intValue)).getIsPlaying() == 1) {
                                        GsChannelListActivity.this.playStream(intValue);
                                        return;
                                    }
                                    if (((DataConvertChannelModel)GsChannelListActivity.this.channelListAdapter.getItem(intValue)).getLockMark() != 0) {
                                        GsChannelListActivity.this.sat2ipPlayPosition = intValue;
                                        GsChannelListActivity.access$49(GsChannelListActivity.this, 3);
                                        GsChannelListActivity.this.inputPermissionPassword();
                                        return;
                                    }
                                    GsChannelListActivity.this.playStream(intValue);
                                }
                            }
                        }
                    });
                    ((ViewHolder)tag).moreRenameMenu.setOnClickListener(GsChannelListActivity.this.mRenameMenuOnClickListener);
                    ((ViewHolder)tag).moreLockMenu.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            GsChannelListActivity.access$52(GsChannelListActivity.this, false);
                            GsChannelListActivity.this.inputPermissionPassword();
                            GsChannelListActivity.access$49(GsChannelListActivity.this, 1);
                        }
                    });
                    ((ViewHolder)tag).moreDeleteMenu.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            final CommonCofirmDialog commonCofirmDialog = new CommonCofirmDialog((Context)GsChannelListActivity.this.getParent());
                            commonCofirmDialog.setmTitle(GsChannelListActivity.this.getResources().getString(2131427594));
                            commonCofirmDialog.setmContent(GsChannelListActivity.this.getResources().getString(2131427596));
                            commonCofirmDialog.setOnButtonClickListener(GsChannelListActivity.this.mDeleteMenuOnClickListener);
                            commonCofirmDialog.show();
                        }
                    });
                    ((ViewHolder)tag).moreChatMenu.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                        public void onClick(final View view) {
                            final int intValue = (int)view.getTag();
                            if (GsChannelListActivity.this.waitDialog.isShowing()) {
                                GsChannelListActivity.this.waitDialog.dismiss();
                            }
                            GsChannelListActivity.access$16(GsChannelListActivity.this, DialogBuilder.showProgressDialog(GsChannelListActivity.this.getParent(), 2131427514, 2131427520, false, GMScreenGlobalInfo.getmWaitDialogTimeOut(), 2131427637));
                            final DataConvertChannelModel dataConvertChannelModel = (DataConvertChannelModel)GsChannelListActivity.this.channelListAdapter.getItem(intValue);
                            final DataConvertChannelModel dataConvertChannelModel2 = new DataConvertChannelModel();
                            final ArrayList<DataConvertChannelModel> list = new ArrayList<DataConvertChannelModel>();
                            dataConvertChannelModel2.SetProgramId(dataConvertChannelModel.GetProgramId());
                            dataConvertChannelModel2.setChannelTpye(dataConvertChannelModel.getChannelTpye());
                            list.add(dataConvertChannelModel2);
                            GsChannelListActivity.access$44(GsChannelListActivity.this, ParserFactory.getParser());
                            try {
                                final byte[] bytes = GsChannelListActivity.this.parser.serialize(list, 1100).getBytes("UTF-8");
                                GsChannelListActivity.this.tcpSocket.setSoTimeout(3000);
                                GsSendSocket.sendSocketToStb(bytes, GsChannelListActivity.this.tcpSocket, 0, bytes.length, 1100);
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    if (!GMScreenGlobalInfo.isChatSupport()) {
                        ((ViewHolder)tag).moreChatMenu.setVisibility(8);
                    }
                    ((ImageView)editSelectLayout).setBackgroundResource(2130837661);
                    ((ViewHolder)tag).moreMenuLayout.setVisibility(0);
                    if (GsChannelListActivity.this.mCurrentChannelList.get(n).GetHaveEPG() == 1) {
                        ((ViewHolder)tag).moreEpgMenu.setClickable(true);
                        ((ViewHolder)tag).moreEpgMenu.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165234));
                        ((ViewHolder)tag).moreEpgMenu.setCompoundDrawablesWithIntrinsicBounds(0, 2130837655, 0, 0);
                        continue Label_0841_Outer;
                    }
                    break;
                }
                continue;
            }
        }

        public boolean isEnabled(final int n) {
            return !GMScreenGlobalInfo.isClientTypeSlave() && super.isEnabled(n);
        }

        public void setAllSelectedBtndisplay(final boolean b) {
            if (b) {
                GsChannelListActivity.this.allSelectedBtn.setImageResource(2130837614);
                GsChannelListActivity.access$14(GsChannelListActivity.this, true);
                return;
            }
            GsChannelListActivity.this.allSelectedBtn.setImageResource(2130837969);
            GsChannelListActivity.access$14(GsChannelListActivity.this, false);
        }

        public void setEditDeleteDisplay(final boolean b) {
            if (b) {
                GsChannelListActivity.this.editDeleteMenu.setClickable(true);
                GsChannelListActivity.this.editDeleteIcon.setImageResource(2130837707);
                GsChannelListActivity.this.editDeleteText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165211));
                return;
            }
            GsChannelListActivity.this.editDeleteMenu.setClickable(false);
            GsChannelListActivity.this.editDeleteIcon.setImageResource(2130837708);
            GsChannelListActivity.this.editDeleteText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165227));
        }

        public void setEditFavDisplay(final boolean b) {
            if (b) {
                GsChannelListActivity.this.editFavorMenu.setClickable(true);
                GsChannelListActivity.this.editFavorIcon.setImageResource(2130837736);
                GsChannelListActivity.this.editFavorText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165211));
                return;
            }
            GsChannelListActivity.this.editFavorMenu.setClickable(false);
            GsChannelListActivity.this.editFavorIcon.setImageResource(2130837742);
            GsChannelListActivity.this.editFavorText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165227));
        }

        public void setEditLockDisplay(final boolean b) {
            if (b) {
                GsChannelListActivity.this.editLockMenu.setClickable(true);
                GsChannelListActivity.this.editLockIcon.setImageResource(2130837932);
                GsChannelListActivity.this.editLockText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165211));
                return;
            }
            GsChannelListActivity.this.editLockMenu.setClickable(false);
            GsChannelListActivity.this.editLockIcon.setImageResource(2130837935);
            GsChannelListActivity.this.editLockText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165227));
        }

        public void setEditMoveDisplay(final boolean b) {
            if (b && !GsChannelListActivity.this.mEnterSearchFlag) {
                GsChannelListActivity.this.editMoveMenu.setClickable(true);
                GsChannelListActivity.this.editMoveIcon.setImageResource(2130837964);
                GsChannelListActivity.this.editMoveText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165211));
                return;
            }
            GsChannelListActivity.this.editMoveMenu.setClickable(false);
            GsChannelListActivity.this.editMoveIcon.setImageResource(2130837965);
            GsChannelListActivity.this.editMoveText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165227));
        }

        public void setEditRenameDisplay(final boolean b) {
            if (b) {
                GsChannelListActivity.this.editRenameMenu.setClickable(true);
                GsChannelListActivity.this.editRenameIcon.setImageResource(2130838149);
                GsChannelListActivity.this.editRenameText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165211));
                return;
            }
            GsChannelListActivity.this.editRenameMenu.setClickable(false);
            GsChannelListActivity.this.editRenameIcon.setImageResource(2130838150);
            GsChannelListActivity.this.editRenameText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165227));
        }

        public void setEditSortDisplay(final boolean b) {
            if (b && !GsChannelListActivity.this.mEnterSearchFlag) {
                GsChannelListActivity.this.mEditSortMenu.setClickable(true);
                GsChannelListActivity.this.mEditSortIcon.setImageResource(2130838178);
                GsChannelListActivity.this.mEditSortText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165211));
                return;
            }
            GsChannelListActivity.this.mEditSortMenu.setClickable(false);
            GsChannelListActivity.this.mEditSortIcon.setImageResource(2130838177);
            GsChannelListActivity.this.mEditSortText.setTextColor(GsChannelListActivity.this.getResources().getColor(2131165227));
        }

        private class ViewHolder
        {
            public ImageView editSelect;
            public LinearLayout editSelectLayout;
            public ImageView favorIcon;
            public LinearLayout linearLayoutProgIndex;
            public ImageView lockIcon;
            public ImageView moreBtnDemarcation;
            public LinearLayout moreButtonLayout;
            public TextView moreChatMenu;
            public TextView moreDeleteMenu;
            public TextView moreEpgMenu;
            public TextView moreLockMenu;
            public LinearLayout moreMenuLayout;
            public TextView morePlayMenu;
            public TextView moreRenameMenu;
            public TextView progIndex;
            public TextView progName;
            public RelativeLayout relativeLayoutProgName;
            public ImageView scrambleIcon;

            private ViewHolder() {
                this.progName = null;
                this.editSelect = null;
            }
        }
    }

    private class sort_adapter extends BaseAdapter
    {
        private int commonTextColor;
        LayoutInflater inflater;
        private int mMacroFlag;
        private ArrayList<String> mSortList;
        private int mSortType;

        public sort_adapter(final Context context, final int mSortType, final ArrayList<String> mSortList, final int mMacroFlag) {
            this.inflater = LayoutInflater.from(context);
            this.mSortList = mSortList;
            this.mMacroFlag = mMacroFlag;
            this.mSortType = mSortType;
        }

        public int getCount() {
            switch (this.mMacroFlag) {
                default: {
                    return 0;
                }
                case 0: {
                    return this.mSortList.size() - 2;
                }
                case 1:
                case 2: {
                    return this.mSortList.size() - 1;
                }
                case 3: {
                    return this.mSortList.size();
                }
            }
        }

        public Object getItem(final int n) {
            int n2 = 0;
            switch (this.mMacroFlag) {
                default: {
                    n2 = n;
                    break;
                }
                case 0: {
                    n2 = n + 2;
                    break;
                }
                case 1: {
                    n2 = n;
                    if (n > 0) {
                        n2 = n + 1;
                        break;
                    }
                    break;
                }
                case 2: {
                    n2 = n + 1;
                    break;
                }
            }
            return this.mSortList.get(n2);
        }

        public long getItemId(final int n) {
            return n;
        }

        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            int n2 = 0;
            switch (this.mMacroFlag) {
                default: {
                    n2 = n;
                    break;
                }
                case 0: {
                    n2 = n + 2;
                    break;
                }
                case 1: {
                    if (n > 0) {
                        n2 = n + 1;
                        break;
                    }
                    n2 = n;
                    break;
                }
                case 2: {
                    n2 = n + 1;
                    break;
                }
            }
            View inflate = view;
            if (view == null) {
                inflate = this.inflater.inflate(2130903143, (ViewGroup)null);
            }
            final RelativeLayout relativeLayout = (RelativeLayout)inflate.findViewById(2131362282);
            final ImageView imageView = (ImageView)inflate.findViewById(2131362285);
            ((TextView)inflate.findViewById(2131362283)).setText((CharSequence)this.mSortList.get(n2));
            relativeLayout.setBackgroundResource(2130837710);
            if (n == this.mSortType) {
                imageView.setImageResource(2130837929);
                return inflate;
            }
            imageView.setImageResource(2130837930);
            return inflate;
        }

        public void setCommonTextColor(final int commonTextColor) {
            this.commonTextColor = commonTextColor;
        }

        public void setTextColor(final int commonTextColor) {
            this.setCommonTextColor(commonTextColor);
        }
    }
}
