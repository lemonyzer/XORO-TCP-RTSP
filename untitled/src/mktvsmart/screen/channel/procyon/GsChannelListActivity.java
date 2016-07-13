package mktvsmart.screen.channel.procyon;

import com.voicetechnology.rtspclient.test.*;
import android.app.*;
import android.view.inputmethod.*;
import android.net.*;
import mktvsmart.screen.message.process.*;
import java.net.*;
import mktvsmart.screen.view.*;
import mktvsmart.screen.dataconvert.model.*;
import android.text.*;
import android.content.*;
import java.util.*;
import mktvsmart.screen.vlc.*;
import mktvsmart.screen.dataconvert.parser.*;
import mktvsmart.screen.*;
import mktvsmart.screen.exception.*;
import android.telephony.*;
import android.database.*;
import android.widget.*;
import android.graphics.drawable.*;
import android.graphics.*;
import mktvsmart.screen.util.*;
import com.alitech.dvbtoip.*;
import java.io.*;
import android.view.*;
import android.content.res.*;
import android.util.*;
import android.os.*;

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
    private GsChannelListActivity.list_single_button_adapter channelListAdapter;
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
    private CommonCofirmDialog$OnButtonClickListener mDeleteMenuOnClickListener;
    private CommonCofirmDialog$OnButtonClickListener mDownDialogOnClickListener;
    private ImageView mEditSortIcon;
    private LinearLayout mEditSortMenu;
    private TextView mEditSortText;
    private boolean mEnterSearchFlag;
    private GsChannelListActivity.grid_adapter mFavGridAdater;
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
    private GsChannelListActivity.MyPhoneStateListener mPhoneListener;
    private FindPlayerAndPlayChannel$PlayByDesignatedPlayer mPlayByDesignatedPlayer;
    private Intent mPlayIntent;
    private View$OnClickListener mRenameMenuOnClickListener;
    private ContentResolver mResolver;
    private GsChannelListActivity.SMSContentObserver mSMSContentObserver;
    private Button mSearchCancelBtn;
    private EditText mSearchChannelEdit;
    private String mSearchChannelKeywords;
    private LinearLayout mSearchChannelLayout;
    private List<DataConvertChannelModel> mSearchChannelListModels;
    private LinearLayout mSearchFailedPrompt;
    private int mSearchMode;
    private int mSecChannelListClickTime;
    private GsChannelListActivity.sort_adapter mSortAdapter;
    private Dialog mSortTypePopupWindow;
    private CommonCofirmDialog$OnButtonClickListener mStbInStandbyOnClickListener;
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
    private MessageProcessor$PerformOnBackground post;
    private int preChannelListType;
    Dialog pswInputDialog;
    Dialog renameInputDialog;
    private boolean repeatPassword;
    private MessageProcessor$PerformOnForeground requestAllChannelWhenSTBChannelListChanged;
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
        this.mPhoneListener = new GsChannelListActivity.MyPhoneStateListener(this, (GsChannelListActivity.MyPhoneStateListener)null);
        this.mSMSContentObserver = new GsChannelListActivity.SMSContentObserver(this);
        this.mMsgHandle = (Handler$Callback)new GsChannelListActivity.GsChannelListActivity$1(this);
        this.timeOutRun = (Runnable)new GsChannelListActivity.GsChannelListActivity$2(this);
        this.mDownDialogOnClickListener = (CommonCofirmDialog$OnButtonClickListener)new GsChannelListActivity.GsChannelListActivity$3(this);
        this.mPlayByDesignatedPlayer = (FindPlayerAndPlayChannel$PlayByDesignatedPlayer)new GsChannelListActivity.GsChannelListActivity$4(this);
        this.post = (MessageProcessor$PerformOnBackground)new GsChannelListActivity.GsChannelListActivity$5(this);
        this.requestAllChannelWhenSTBChannelListChanged = (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$6(this);
        this.mStbInStandbyOnClickListener = (CommonCofirmDialog$OnButtonClickListener)new GsChannelListActivity.GsChannelListActivity$7(this);
        this.mDeleteMenuOnClickListener = (CommonCofirmDialog$OnButtonClickListener)new GsChannelListActivity.GsChannelListActivity$8(this);
        this.mRenameMenuOnClickListener = (View$OnClickListener)new GsChannelListActivity.GsChannelListActivity$9(this);
    }

    private void ChangeChannelListType(final int n, final boolean b) {
        if (n != this.preChannelListType || b) {
            final List channelListByProgramType = this.mChannelData.getChannelListByProgramType(this.mChannelData.getChannelListByTvRadioType(), n);
            if (channelListByProgramType != null) {
                this.mCurrentChannelList = (List<DataConvertChannelModel>)channelListByProgramType;
                this.setCurrentChannelListDispIndex();
                this.mOriginalChannelListModels = this.mCurrentChannelList;
                if (this.mEnterSearchFlag) {
                    this.mChannelListChangeFlag = true;
                    this.findChannel();
                }
                else {
                    if (this.ChannelListView.getAdapter() != null && this.ChannelListView.getAdapter() instanceof GsChannelListActivity.list_single_button_adapter) {
                        ((GsChannelListActivity.list_single_button_adapter)this.ChannelListView.getAdapter()).notifyDataSetChanged();
                    }
                    this.ChannelListView.post((Runnable)new GsChannelListActivity.GsChannelListActivity$10(this));
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
        new Thread((Runnable)new GsChannelListActivity.GsChannelListActivity$14(this, n, intent)).start();
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
        this.channelListAdapter = new GsChannelListActivity.list_single_button_adapter(this, (Context)this);
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
                final Iterator<DataConvertChannelModel> iterator = (Iterator<DataConvertChannelModel>)this.mChannelData.getChannelListByTvRadioType().iterator();
                while (iterator.hasNext()) {
                    this.channelTypeFavMark |= iterator.next().GetFavMark();
                }
                for (int k = this.channelTypeFavMark, n = 0; k > 0; k >>>= 1, ++n) {
                    if ((k & 0x1) > 0) {
                        list.add((String)GMScreenGlobalInfo.favType.get(n));
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
                    final Iterator<DataConvertChannelModel> iterator2 = (Iterator<DataConvertChannelModel>)this.mChannelData.getChannelListByTvRadioType().iterator();
                    while (iterator2.hasNext()) {
                        if (iterator2.next().mfavGroupIDs.contains(GMScreenGlobalInfo.favGroups.get(n5).getFavorTypeID())) {
                            list.add(((DataConvertFavorModel)GMScreenGlobalInfo.favGroups.get(n5)).GetFavorName());
                            this.channelListTypeMap.append(n4, ((DataConvertFavorModel)GMScreenGlobalInfo.favGroups.get(n5)).getFavorTypeID() + 4);
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
        editText.addTextChangedListener((TextWatcher)new GsChannelListActivity.GsChannelListActivity$56(this, editText));
        button.setOnClickListener((View$OnClickListener)new GsChannelListActivity.GsChannelListActivity$57(this));
        if (this.pswInputDialog != null && this.pswInputDialog.isShowing()) {
            this.pswInputDialog.dismiss();
        }
        (this.pswInputDialog = new Dialog((Context)this.getParent(), 2131493009)).setContentView((View)contentView);
        this.pswInputDialog.setOnCancelListener((DialogInterface$OnCancelListener)new GsChannelListActivity.GsChannelListActivity$58(this));
        this.pswInputDialog.setCanceledOnTouchOutside(false);
        this.pswInputDialog.show();
        new Timer().schedule((TimerTask)new GsChannelListActivity.GsChannelListActivity$59(this, editText), 200L);
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
            GsSendSocket.sendSocketToStb(this.dataBuff = this.parser.serialize((List)list, 0).getBytes("UTF-8"), this.tcpSocket, 0, this.dataBuff.length, 0);
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
            final byte[] bytes = this.parser.serialize((List)list, 1009).getBytes("UTF-8");
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
        this.msgProc.setOnMessageProcess(0, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$15(this));
        this.msgProc.setOnMessageProcess(5, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$16(this));
        this.msgProc.setOnMessageProcess(3, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$17(this));
        this.msgProc.setOnMessageProcess(4112, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$18(this));
        this.msgProc.setOnMessageProcess(20, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$19(this));
        this.msgProc.setOnMessageProcess(18, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$20(this));
        this.msgProc.setOnMessageProcess(16, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$21(this));
        this.msgProc.setOnMessageProcess(73, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$22(this));
        this.msgProc.setOnMessageProcess(4104, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$23(this));
        this.msgProc.setOnMessageProcess(17, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$24(this));
        this.msgProc.setOnMessageProcess(2001, this.post);
        this.msgProc.setOnMessageProcess(2004, this.post);
        this.msgProc.setOnMessageProcess(2002, (Activity)this, this.requestAllChannelWhenSTBChannelListChanged);
        this.msgProc.setOnMessageProcess(2025, (Activity)this, this.requestAllChannelWhenSTBChannelListChanged);
        this.msgProc.setOnMessageProcess(2026, (Activity)this, this.requestAllChannelWhenSTBChannelListChanged);
        this.msgProc.setOnMessageProcess(1056, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$25(this));
        this.msgProc.setOnMessageProcess(2006, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$26(this));
        this.msgProc.setOnMessageProcess(2007, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$27(this));
        this.msgProc.setOnMessageProcess(22, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$28(this));
        this.msgProc.setOnMessageProcess(24, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$29(this));
        this.msgProc.setOnMessageProcess(23, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$30(this));
        this.msgProc.setOnMessageProcess(2015, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$31(this));
        this.msgProc.setOnMessageProcess(1000, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$32(this));
        this.msgProc.setOnMessageProcess(2009, this.post);
        this.msgProc.setOnMessageProcess(2013, this.post);
        this.msgProc.setOnMessageProcess(2019, this.post);
        this.msgProc.setOnMessageProcess(4114, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$33(this));
        this.msgProc.setOnMessageProcess(1002, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$34(this));
        this.msgProc.setOnMessageProcess(2014, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$35(this));
        this.msgProc.setOnMessageProcess(4118, (Activity)this, (MessageProcessor$PerformOnBackground)new GsChannelListActivity.GsChannelListActivity$36(this));
        this.msgProc.setOnMessageProcess(1100, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$37(this));
        switch (GMScreenGlobalInfo.getCurStbPlatform()) {
            default: {
                this.msgProc.setOnMessageProcess(1009, (Activity)this, (MessageProcessor$PerformOnForeground)new GsChannelListActivity.GsChannelListActivity$38(this));
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
        (this.typeAdapter = new ListviewAdapter(inflate.getContext(), (ArrayList)this.getChannelTypeData())).setTextColor(this.getResources().getColor(2131165211));
        this.typeAdapter.setItemBackgroundResource(2130837999);
        listView.setAdapter((ListAdapter)this.typeAdapter);
        listView.setOnItemClickListener((AdapterView$OnItemClickListener)new GsChannelListActivity.GsChannelListActivity$11(this));
        (this.channelTypePopupWindow = new PopupWindow(inflate, -2, -2, true)).setFocusable(true);
        this.channelTypePopupWindow.setOutsideTouchable(true);
        this.channelTypePopupWindow.setOnDismissListener((PopupWindow$OnDismissListener)new GsChannelListActivity.GsChannelListActivity$12(this));
        this.channelTypePopupWindow.setBackgroundDrawable((Drawable)new BitmapDrawable());
        this.channelTypePopupWindow.setTouchInterceptor((View$OnTouchListener)new GsChannelListActivity.GsChannelListActivity$13(this));
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
        this.waitDialog = DialogBuilder.showProgressDialog(this.getParent(), 2131427641, 2131427520, false, (long)GMScreenGlobalInfo.getmWaitDialogTimeOut(), this.timeOutRun);
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
                                        GsSendSocket.sendSocketToStb(this.dataBuff = this.parser.serialize((List)list, 1007).getBytes("UTF-8"), this.tcpSocket, 0, this.dataBuff.length, 1007);
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
            final byte[] bytes = this.parser.serialize((List)list, 1000).getBytes("UTF-8");
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
        //     0: aload_0
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
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
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
        //     at the.bytecode.club.bytecodeviewer.decompilers.ProcyonDecompiler.decompileClassNode(ProcyonDecompiler.java:120)
        //     at the.bytecode.club.bytecodeviewer.gui.ClassViewer$14.doShit(ClassViewer.java:879)
        //     at the.bytecode.club.bytecodeviewer.gui.PaneUpdaterThread.run(PaneUpdaterThread.java:16)
        //
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }

    protected void onDestroy() {
        super.onDestroy();
        this.waitDialog.dismiss();
        this.msgProc.recycle();
        this.msgProc.removeProcessCallback((Activity)null);
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
                                    final byte[] bytes = this.parser.serialize((List)this.favorModels, 1004).getBytes("UTF-8");
                                    this.tcpSocket.setSoTimeout(3000);
                                    GsSendSocket.sendSocketToStb(bytes, this.tcpSocket, 0, bytes.length, 1004);
                                    if (this.waitDialog.isShowing()) {
                                        this.waitDialog.dismiss();
                                    }
                                    this.waitDialog = DialogBuilder.showProgressDialog(this.getParent(), 2131427517, 2131427520, false, (long)GMScreenGlobalInfo.getmWaitDialogTimeOut(), 2131427638);
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
}
