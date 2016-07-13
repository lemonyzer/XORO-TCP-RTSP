package mktvsmart.screen.channel.krakatau;

public class GsChannelListActivity extends android.app.Activity implements mktvsmart.screen.OnTabActivityResultListener {
    final private static int ALL_ADJACENT_MATCH_CHANNEL_NAME_PRIORITY = -1;
    final private static int MATCH_CHANNEL_DISP_INDEX_PRIORITY = -2;
    final private static int NO_ADJACENT_MATCH_CHANNEL_NAME_PRIORITY = 1;
    final private static int SEARCH_BY_DISP_INDEX_AND_PROG_NAME = 0;
    final private static int SEARCH_BY_PROG_NAME = 1;
    public static boolean enable_edit;
    private static com.voicetechnology.rtspclient.test.Sat2IP_Rtsp sRtsp;
    final private static java.util.Collection targetApplications;
    private android.widget.ListView ChannelListView;
    private android.widget.Button DoneBtn;
    final private double M_BIG_SEPARATION_DISTANCE;
    final private int M_DOUBLE_CLICK_TIME_INTERVAL;
    final private double M_EDIT_SEARCH_FRAGMENT_WIDTH_PROPORTION;
    final private int M_FIRST_CLICK;
    final private double M_SEARCH_CANCEL_LAYOUT_WIDTH_PROPORTION;
    final private double M_SEARCH_FRAGMENT_WIDTH_PROPORTION;
    final private int M_SECOND_CLICK;
    final private double M_SMALL_SEPARATION_DISTANCE;
    private String NetAddress;
    private int NetPort;
    final private int PLAY_FAILURE;
    final private int PLAY_TIMEOUT;
    final private int PLAY_WITH_OTHER_PLAYER;
    private android.widget.Button TypeSwitch;
    private android.widget.ImageView allSelectedBtn;
    private android.widget.LinearLayout allSelectedBtnLayout;
    private boolean allSelectedBtn_selected;
    private boolean bPlayWithOherPlayer;
    android.app.AlertDialog$Builder build;
    private mktvsmart.screen.channel.GsChannelListActivity$list_single_button_adapter channelListAdapter;
    private android.util.SparseIntArray channelListTypeMap;
    private android.widget.ImageView channelTypeArrow;
    int channelTypeFavMark;
    private android.widget.PopupWindow channelTypePopupWindow;
    android.widget.CheckBox childFav;
    private java.util.List controlModels;
    android.widget.CheckBox cultureFav;
    private int currentChannelListType;
    private String currentSat2ipChannelProgramId;
    private byte[] dataBuff;
    android.app.Dialog deleteChannelDialog;
    private android.widget.Button editBtn;
    private android.widget.ImageView editDeleteIcon;
    private android.widget.LinearLayout editDeleteMenu;
    private android.widget.TextView editDeleteText;
    private android.widget.ImageView editFavorIcon;
    private android.widget.LinearLayout editFavorMenu;
    private android.widget.TextView editFavorText;
    final private int editLockChannel;
    private android.widget.ImageView editLockIcon;
    private android.widget.LinearLayout editLockMenu;
    private android.widget.TextView editLockText;
    private android.widget.LinearLayout editMenu;
    private android.widget.ImageView editMoveIcon;
    private android.widget.LinearLayout editMoveMenu;
    private android.widget.TextView editMoveText;
    private android.widget.ImageView editRenameIcon;
    private android.widget.LinearLayout editRenameMenu;
    private android.widget.TextView editRenameText;
    android.widget.CheckBox eduaFav;
    private String epg_program_name;
    private String epg_program_sat_tp_id;
    private int expandPosition;
    int favMark;
    private java.util.List favorModels;
    java.io.InputStream in;
    private android.view.inputmethod.InputMethodManager inputManager;
    private boolean isEnableMove;
    boolean isFavorChange;
    private boolean isInForeground;
    boolean isSat2ipStarted;
    private int lastItem;
    private int longClickPos;
    int loop;
    private mktvsmart.screen.channel.ChannelData mChannelData;
    private boolean mChannelListChangeFlag;
    private int mChannelListClickCount;
    private android.widget.ImageView mClearSearchKeyword;
    private java.util.List mCurrentChannelList;
    private mktvsmart.screen.CommonCofirmDialog$OnButtonClickListener mDeleteMenuOnClickListener;
    private mktvsmart.screen.CommonCofirmDialog$OnButtonClickListener mDownDialogOnClickListener;
    private android.widget.ImageView mEditSortIcon;
    private android.widget.LinearLayout mEditSortMenu;
    private android.widget.TextView mEditSortText;
    private boolean mEnterSearchFlag;
    private mktvsmart.screen.channel.GsChannelListActivity$grid_adapter mFavGridAdater;
    private int mFavValue;
    private int[] mFavValueArray;
    private android.app.Dialog mFavorRenameDialog;
    private int mFirstChannelListClickTime;
    private int mFirstVisibleChannelIdex;
    private boolean mGetChannelListWhenLogin;
    private boolean[] mIsChoice;
    private int mLastVisibleChannelIndex;
    private android.os.Handler$Callback mMsgHandle;
    private java.util.List mOriginalChannelListModels;
    private mktvsmart.screen.channel.GsChannelListActivity$MyPhoneStateListener mPhoneListener;
    private mktvsmart.screen.FindPlayerAndPlayChannel$PlayByDesignatedPlayer mPlayByDesignatedPlayer;
    private android.content.Intent mPlayIntent;
    private android.view.View$OnClickListener mRenameMenuOnClickListener;
    private android.content.ContentResolver mResolver;
    private mktvsmart.screen.channel.GsChannelListActivity$SMSContentObserver mSMSContentObserver;
    private android.widget.Button mSearchCancelBtn;
    private android.widget.EditText mSearchChannelEdit;
    private String mSearchChannelKeywords;
    private android.widget.LinearLayout mSearchChannelLayout;
    private java.util.List mSearchChannelListModels;
    private android.widget.LinearLayout mSearchFailedPrompt;
    private int mSearchMode;
    private int mSecChannelListClickTime;
    private mktvsmart.screen.channel.GsChannelListActivity$sort_adapter mSortAdapter;
    private android.app.Dialog mSortTypePopupWindow;
    private mktvsmart.screen.CommonCofirmDialog$OnButtonClickListener mStbInStandbyOnClickListener;
    private android.net.Uri mUri;
    private android.os.Handler mainHandler;
    final private int mobilePlayLockChannel;
    android.widget.CheckBox moviesFav;
    private mktvsmart.screen.message.process.MessageProcessor msgProc;
    android.widget.CheckBox musFav;
    android.widget.CheckBox newsFav;
    private mktvsmart.screen.dataconvert.parser.DataParser parser;
    private int passwordType;
    final private int playingLockChannel;
    private mktvsmart.screen.message.process.MessageProcessor$PerformOnBackground post;
    private int preChannelListType;
    android.app.Dialog pswInputDialog;
    android.app.Dialog renameInputDialog;
    private boolean repeatPassword;
    private mktvsmart.screen.message.process.MessageProcessor$PerformOnForeground requestAllChannelWhenSTBChannelListChanged;
    int sat2ipPlayPosition;
    android.widget.CheckBox sportsFav;
    private android.widget.TabHost tabHost;
    private android.widget.TabWidget tabWidget;
    private java.net.Socket tcpSocket;
    Runnable timeOutRun;
    private android.widget.TextView titleText;
    private mktvsmart.screen.view.ListviewAdapter typeAdapter;
    private int visibleItemCount;
    private mktvsmart.screen.util.ADSProgressDialog waitDialog;
    android.widget.CheckBox weatherFav;

    static
    {
        String[] a = new String[1];
        a[0] = "com.mxtech.videoplayer.ad";
        targetApplications = mktvsmart.screen.channel.GsChannelListActivity.list(a);
        enable_edit = false;
    }

    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 30, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 401, in addCasts_sub
    //    expr = makeCastExpr(left.dtype, right, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/os/Handler$Callback
    public GsChannelListActivity();


    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 136, in _preorder
    //    _preorder(sub, func)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 136, in _preorder
    //    _preorder(sub, func)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 136, in _preorder
    //    _preorder(sub, func)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 136, in _preorder
    //    _preorder(sub, func)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 30, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 341, in addCasts
    //    param.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 457, in addCasts_sub
    //    def addCasts_sub(self, env): self.fix(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/widget/BaseAdapter
    private void ChangeChannelListType(int arg, boolean arg0);


    private int GetSelectedNum(java.util.List a)
    {
        Object a0 = a.iterator();
        int i = 0;
        while(((java.util.Iterator)a0).hasNext())
        {
            if (((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a0).next()).getSelectedFlag())
            {
                i = i + 1;
            }
        }
        return i;
    }

    private void LoadData()
    {
        this.ChannelListView.setSelection(this.lastItem - this.visibleItemCount + 1);
    }

    private void abtainPlayUrl(int i, android.content.Intent a)
    {
        new Thread((Runnable)(Object)new mktvsmart.screen.channel.GsChannelListActivity$14(this, i, a)).start();
    }

    static void access$0(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.stopStream();
    }

    static mktvsmart.screen.util.ADSProgressDialog access$1(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.waitDialog;
    }

    static java.util.List access$10(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mCurrentChannelList;
    }

    static mktvsmart.screen.channel.GsChannelListActivity$MyPhoneStateListener access$100(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mPhoneListener;
    }

    static mktvsmart.screen.channel.GsChannelListActivity$SMSContentObserver access$101(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mSMSContentObserver;
    }

    static boolean access$102(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.bPlayWithOherPlayer;
    }

    static android.content.Intent access$103(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mPlayIntent;
    }

    static void access$104(mktvsmart.screen.channel.GsChannelListActivity a, android.view.View a0)
    {
        a.showChannelTypePopupWindow(a0);
    }

    static void access$105(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.enterEditMode();
    }

    static void access$106(mktvsmart.screen.channel.GsChannelListActivity a, boolean b)
    {
        a.isEnableMove = b;
    }

    static int access$107(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mChannelListClickCount;
    }

    static void access$108(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.mChannelListClickCount = i;
    }

    static void access$109(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.mFirstChannelListClickTime = i;
    }

    static mktvsmart.screen.dataconvert.parser.DataParser access$11(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.parser;
    }

    static void access$110(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.mSecChannelListClickTime = i;
    }

    static int access$111(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mSecChannelListClickTime;
    }

    static int access$112(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mFirstChannelListClickTime;
    }

    static void access$113(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.trueNewChannelInStb(i);
    }

    static void access$114(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.setSearchMenuDisable();
    }

    static android.widget.TabWidget access$115(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.tabWidget;
    }

    static java.util.List access$116(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mOriginalChannelListModels;
    }

    static android.widget.EditText access$117(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mSearchChannelEdit;
    }

    static void access$118(mktvsmart.screen.channel.GsChannelListActivity a, String s)
    {
        a.mSearchChannelKeywords = s;
    }

    static void access$119(mktvsmart.screen.channel.GsChannelListActivity a, java.util.List a0)
    {
        a.mSearchChannelListModels = a0;
    }

    static void access$12(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.initItemChecked();
    }

    static void access$120(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.setSearchMenuEnable();
    }

    static android.widget.Button access$121(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editBtn;
    }

    static android.widget.Button access$122(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.DoneBtn;
    }

    static void access$123(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.exitEditMode();
    }

    static boolean access$124(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.allSelectedBtn_selected;
    }

    static void access$125(mktvsmart.screen.channel.GsChannelListActivity a, java.util.List a0)
    {
        a.favorModels = a0;
    }

    static java.util.List access$126(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.favorModels;
    }

    static void access$127(mktvsmart.screen.channel.GsChannelListActivity a, boolean[] a0)
    {
        a.mIsChoice = a0;
    }

    static void access$128(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.setDisplayFavValue();
    }

    static void access$129(mktvsmart.screen.channel.GsChannelListActivity a, mktvsmart.screen.channel.GsChannelListActivity$grid_adapter a0)
    {
        a.mFavGridAdater = a0;
    }

    static android.widget.ImageView access$13(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.allSelectedBtn;
    }

    static mktvsmart.screen.channel.GsChannelListActivity$grid_adapter access$130(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mFavGridAdater;
    }

    static android.app.Dialog access$131(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mFavorRenameDialog;
    }

    static void access$132(mktvsmart.screen.channel.GsChannelListActivity a, android.app.Dialog a0)
    {
        a.mFavorRenameDialog = a0;
    }

    static int[] access$133(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mFavValueArray;
    }

    static int access$134(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mFavValue;
    }

    static void access$135(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.LoadData();
    }

    static void access$136(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.visibleItemCount = i;
    }

    static void access$14(mktvsmart.screen.channel.GsChannelListActivity a, boolean b)
    {
        a.allSelectedBtn_selected = b;
    }

    static mktvsmart.screen.channel.GsChannelListActivity$list_single_button_adapter access$15(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.channelListAdapter;
    }

    static void access$16(mktvsmart.screen.channel.GsChannelListActivity a, mktvsmart.screen.util.ADSProgressDialog a0)
    {
        a.waitDialog = a0;
    }

    static android.view.inputmethod.InputMethodManager access$17(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.inputManager;
    }

    static void access$18(mktvsmart.screen.channel.GsChannelListActivity a, android.view.inputmethod.InputMethodManager a0)
    {
        a.inputManager = a0;
    }

    static boolean[] access$19(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mIsChoice;
    }

    static android.os.Handler access$2(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mainHandler;
    }

    static boolean access$20(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mEnterSearchFlag;
    }

    static android.widget.LinearLayout access$21(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editMoveMenu;
    }

    static android.widget.ImageView access$22(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editMoveIcon;
    }

    static android.widget.TextView access$23(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editMoveText;
    }

    static android.widget.LinearLayout access$24(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editDeleteMenu;
    }

    static android.widget.ImageView access$25(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editDeleteIcon;
    }

    static android.widget.TextView access$26(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editDeleteText;
    }

    static android.widget.LinearLayout access$27(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editLockMenu;
    }

    static android.widget.ImageView access$28(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editLockIcon;
    }

    static android.widget.TextView access$29(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editLockText;
    }

    static void access$3(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.otherPlatformPlay(i);
    }

    static android.widget.LinearLayout access$30(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editRenameMenu;
    }

    static android.widget.ImageView access$31(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editRenameIcon;
    }

    static android.widget.TextView access$32(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editRenameText;
    }

    static android.widget.LinearLayout access$33(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editFavorMenu;
    }

    static android.widget.ImageView access$34(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editFavorIcon;
    }

    static android.widget.TextView access$35(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.editFavorText;
    }

    static android.widget.LinearLayout access$36(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mEditSortMenu;
    }

    static android.widget.ImageView access$37(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mEditSortIcon;
    }

    static android.widget.TextView access$38(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mEditSortText;
    }

    static int access$39(mktvsmart.screen.channel.GsChannelListActivity a, java.util.List a0)
    {
        return a.GetSelectedNum(a0);
    }

    static void access$4(mktvsmart.screen.channel.GsChannelListActivity a, int i, android.content.Intent a0)
    {
        a.startPlayStream(i, a0);
    }

    static void access$40(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.expandPosition = i;
    }

    static android.widget.ListView access$41(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.ChannelListView;
    }

    static void access$42(mktvsmart.screen.channel.GsChannelListActivity a, String s)
    {
        a.epg_program_sat_tp_id = s;
    }

    static void access$43(mktvsmart.screen.channel.GsChannelListActivity a, String s)
    {
        a.epg_program_name = s;
    }

    static void access$44(mktvsmart.screen.channel.GsChannelListActivity a, mktvsmart.screen.dataconvert.parser.DataParser a0)
    {
        a.parser = a0;
    }

    static String access$45(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.epg_program_sat_tp_id;
    }

    static java.util.List access$46(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.controlModels;
    }

    static mktvsmart.screen.CommonCofirmDialog$OnButtonClickListener access$47(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mStbInStandbyOnClickListener;
    }

    static void access$48(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.playStream(i);
    }

    static void access$49(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.passwordType = i;
    }

    static mktvsmart.screen.CommonCofirmDialog$OnButtonClickListener access$5(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mDownDialogOnClickListener;
    }

    static void access$50(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.inputPermissionPassword();
    }

    static android.view.View$OnClickListener access$51(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mRenameMenuOnClickListener;
    }

    static void access$52(mktvsmart.screen.channel.GsChannelListActivity a, boolean b)
    {
        a.repeatPassword = b;
    }

    static mktvsmart.screen.CommonCofirmDialog$OnButtonClickListener access$53(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mDeleteMenuOnClickListener;
    }

    static int access$54(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.longClickPos;
    }

    static void access$55(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.longClickPos = i;
    }

    static boolean access$56(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.isEnableMove;
    }

    static void access$57(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.mFirstVisibleChannelIdex = i;
    }

    static void access$58(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.mLastVisibleChannelIndex = i;
    }

    static android.content.ContentResolver access$59(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mResolver;
    }

    static java.net.Socket access$6(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.tcpSocket;
    }

    static android.net.Uri access$60(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mUri;
    }

    static void access$61(mktvsmart.screen.channel.GsChannelListActivity a, boolean b)
    {
        a.adjustSelectionOfChannelListView(b);
    }

    static int access$62(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.currentChannelListType;
    }

    static void access$63(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.preChannelListType = i;
    }

    static int access$64(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        return a.clickPosition2ListType(i);
    }

    static void access$65(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.currentChannelListType = i;
    }

    static void access$66(mktvsmart.screen.channel.GsChannelListActivity a, int i, boolean b)
    {
        a.ChangeChannelListType(i, b);
    }

    static android.widget.PopupWindow access$67(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.channelTypePopupWindow;
    }

    static android.widget.ImageView access$68(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.channelTypeArrow;
    }

    static void access$69(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.synchronizeSTBChannelType(i);
    }

    static mktvsmart.screen.channel.ChannelData access$7(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mChannelData;
    }

    static void access$70(com.voicetechnology.rtspclient.test.Sat2IP_Rtsp a)
    {
        sRtsp = a;
    }

    static com.voicetechnology.rtspclient.test.Sat2IP_Rtsp access$71()
    {
        return sRtsp;
    }

    static void access$72(mktvsmart.screen.channel.GsChannelListActivity a, int i)
    {
        a.sendSat2ipChannelRequestToStb(i);
    }

    static void access$73(mktvsmart.screen.channel.GsChannelListActivity a, android.content.Intent a0)
    {
        a.mPlayIntent = a0;
    }

    static void access$74(mktvsmart.screen.channel.GsChannelListActivity a, boolean b)
    {
        a.bPlayWithOherPlayer = b;
    }

    static void access$75(mktvsmart.screen.channel.GsChannelListActivity a, java.util.List a0)
    {
        a.mCurrentChannelList = a0;
    }

    static android.widget.TextView access$76(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.titleText;
    }

    static boolean access$77(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mGetChannelListWhenLogin;
    }

    static android.widget.Button access$78(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.TypeSwitch;
    }

    static void access$79(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.setCurrentChannelListDispIndex();
    }

    static void access$8(mktvsmart.screen.channel.GsChannelListActivity a, int i, int i0)
    {
        a.requestProgramListFromTo(i, i0);
    }

    static void access$80(mktvsmart.screen.channel.GsChannelListActivity a, java.util.List a0)
    {
        a.mOriginalChannelListModels = a0;
    }

    static void access$81(mktvsmart.screen.channel.GsChannelListActivity a, boolean b)
    {
        a.mChannelListChangeFlag = b;
    }

    static void access$82(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.findChannel();
    }

    static void access$83(mktvsmart.screen.channel.GsChannelListActivity a, mktvsmart.screen.channel.GsChannelListActivity$list_single_button_adapter a0)
    {
        a.channelListAdapter = a0;
    }

    static boolean access$84(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.isInForeground;
    }

    static void access$85(mktvsmart.screen.channel.GsChannelListActivity a, boolean b)
    {
        a.mGetChannelListWhenLogin = b;
    }

    static mktvsmart.screen.message.process.MessageProcessor access$86(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.msgProc;
    }

    static String access$87(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.epg_program_name;
    }

    static String access$88(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.currentSat2ipChannelProgramId;
    }

    static void access$89(mktvsmart.screen.channel.GsChannelListActivity a, java.util.List a0)
    {
        a.controlModels = a0;
    }

    static int access$9(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.expandPosition;
    }

    static java.util.ArrayList access$90(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.getSortTypeArray();
    }

    static void access$91(mktvsmart.screen.channel.GsChannelListActivity a, mktvsmart.screen.channel.GsChannelListActivity$sort_adapter a0)
    {
        a.mSortAdapter = a0;
    }

    static mktvsmart.screen.channel.GsChannelListActivity$sort_adapter access$92(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mSortAdapter;
    }

    static android.app.Dialog access$93(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.mSortTypePopupWindow;
    }

    static void access$94(mktvsmart.screen.channel.GsChannelListActivity a, byte[] a0)
    {
        a.dataBuff = a0;
    }

    static byte[] access$95(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.dataBuff;
    }

    static void access$96(mktvsmart.screen.channel.GsChannelListActivity a, android.app.Dialog a0)
    {
        a.mSortTypePopupWindow = a0;
    }

    static int access$97(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        return a.passwordType;
    }

    static void access$98(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.enableSomeFucitonWhenBecomeMaster();
    }

    static void access$99(mktvsmart.screen.channel.GsChannelListActivity a)
    {
        a.setPhoneAndSmsListener();
    }

    private void adjustSelectionOfChannelListView(boolean b)
    {
        java.util.Iterator a = this.mCurrentChannelList.iterator();
        int i = 0;
        Object a0 = a;
        while(true)
        {
            label0: if (((java.util.Iterator)a0).hasNext())
            {
                if (((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a0).next()).getIsPlaying() != 1)
                {
                    i = i + 1;
                    continue;
                }
                if (b)
                {
                    if (i <= 5)
                    {
                        this.ChannelListView.setSelection(0);
                    }
                    else
                    {
                        this.ChannelListView.setSelection(i - 3);
                    }
                }
                else
                {
                    int i0 = this.mFirstVisibleChannelIdex;
                    label1: {
                        if (i < i0)
                        {
                            break label1;
                        }
                        if (i <= this.mLastVisibleChannelIndex)
                        {
                            break label0;
                        }
                    }
                    if (i <= this.mLastVisibleChannelIndex - this.mFirstVisibleChannelIdex)
                    {
                        this.ChannelListView.setSelection(0);
                    }
                    else
                    {
                        int i1 = (this.mLastVisibleChannelIndex - this.mFirstVisibleChannelIdex) / 2;
                        this.ChannelListView.setSelection(i - i1);
                    }
                }
            }
            if (i == this.mCurrentChannelList.size())
            {
                this.ChannelListView.setSelection(0);
            }
            return;
        }
    }

    private int calculateChannelInsertPosition(mktvsmart.screen.dataconvert.model.DataConvertChannelModel a, java.util.List a0)
    {
        Object a1 = a0;
        int i = 0;
        int i0 = 0;
        while(true)
        {
            label0: if (i0 < ((java.util.List)a1).size())
            {
                int i1 = a.getSearchChannelSortPriority();
                label2: {
                    mktvsmart.screen.dataconvert.model.DataConvertChannelModel a2 = null;
                    switch(i1){
                        case 1: {
                            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a3 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(((java.util.List)a1).size() - i0 - 1);
                            if (1 != a3.getSearchChannelSortPriority())
                            {
                                return ((java.util.List)a1).size() - i;
                            }
                            int i2 = 0;
                            while(i2 < a.getMatchChannelNameIndexArray().length)
                            {
                                if ((a.getMatchChannelNameIndexArray()[i2] != 0) > (a3.getMatchChannelNameIndexArray()[i2] != 0))
                                {
                                    return ((java.util.List)a1).size() - i;
                                }
                                {
                                    if ((a.getMatchChannelNameIndexArray()[i2] != 0) >= (a3.getMatchChannelNameIndexArray()[i2] != 0))
                                    {
                                        i2 = i2 + 1;
                                        continue;
                                    }
                                    i = i + 1;
                                    if (i0 == ((java.util.List)a1).size() - 1)
                                    {
                                        return ((java.util.List)a1).size() - i;
                                    }
                                    break;
                                }
                            }
                            if (i2 == a.getMatchChannelNameIndexArray().length)
                            {
                                return ((java.util.List)a1).size() - i;
                            }
                            break label2;
                        }
                        case -1: {
                            a2 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i0);
                            break;
                        }
                        case -2: {
                            if (-2 != ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i0)).getSearchChannelSortPriority())
                            {
                                return i;
                            }
                            i = i0 + 1;
                            break label2;
                        }
                        default: {
                            int i3 = ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i0)).getSearchChannelSortPriority();
                            label3: {
                                label4: {
                                    if (-2 == i3)
                                    {
                                        break label4;
                                    }
                                    if (-1 != ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i0)).getSearchChannelSortPriority())
                                    {
                                        break label3;
                                    }
                                }
                                i = i + 1;
                                break label2;
                            }
                            if (a.getSearchChannelSortPriority() > ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i0)).getSearchChannelSortPriority())
                            {
                                return i;
                            }
                            if (a.getSearchChannelSortPriority() != ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i0)).getSearchChannelSortPriority())
                            {
                                i = i + 1;
                                break label2;
                            }
                            else
                            {
                                if ((a.getMatchChannelNameIndexArray()[0] != 0) < (((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i0)).getMatchChannelNameIndexArray()[0] != 0))
                                {
                                    return i;
                                }
                                i = i + 1;
                                break label2;
                            }
                        }
                    }
                    int i4 = a2.getSearchChannelSortPriority();
                    label1: {
                        if (-2 != i4)
                        {
                            break label1;
                        }
                        i = i + 1;
                        break label2;
                    }
                    if (-1 != ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i0)).getSearchChannelSortPriority())
                    {
                        return i;
                    }
                    if (a.getMatchChannelNameIndexArray()[0] < ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i0)).getMatchChannelNameIndexArray()[0])
                    {
                        i = i0;
                        break label0;
                    }
                    i = i0 + 1;
                }
                i0 = i0 + 1;
                continue;
            }
            return i;
        }
    }

    private int clickPosition2ListType(int i)
    {
        int i0 = this.channelTypeFavMark;
        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbPlatform()){
            case 30: case 31: case 32: case 71: case 72: case 74: {
                return this.channelListTypeMap.get(i, 0);
            }
            default: {
                label1: if (mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() != 0)
                {
                    if (i >= 0 && i < 3)
                    {
                        return i;
                    }
                    if (i < 3)
                    {
                        i = -1;
                    }
                    else
                    {
                        int i1 = i - 2;
                        int i2 = 3;
                        while(i1 > 0)
                        {
                            if ((i0 & 1) > 0)
                            {
                                i1 = i1 - 1;
                            }
                            i0 = i0 >>> 1;
                            i2 = i2 + 1;
                        }
                        i = i2;
                    }
                }
                else
                {
                    label0: {
                        if (i < 0)
                        {
                            break label0;
                        }
                        if (i > 3)
                        {
                            break label0;
                        }
                        break label1;
                    }
                    if (i < 4)
                    {
                        i = -1;
                    }
                    else
                    {
                        int i3 = i - 3;
                        int i4 = 3;
                        while(i3 > 0)
                        {
                            if ((i0 & 1) > 0)
                            {
                                i3 = i3 - 1;
                            }
                            i0 = i0 >>> 1;
                            i4 = i4 + 1;
                        }
                        i = i4;
                    }
                }
                return i;
            }
        }
    }

    public static int dip2px(android.content.Context a, float f)
    {
        return (int)(f * a.getResources().getDisplayMetrics().density + 0.5f);
    }

    private void disableSomeFunctionWhenSlave()
    {
        this.editBtn.setEnabled(false);
        this.TypeSwitch.setEnabled(false);
        this.titleText.setClickable(false);
        this.channelTypeArrow.setVisibility(4);
        this.ChannelListView.setLongClickable(false);
    }

    private void displayOrDimissLayout(android.widget.LinearLayout a, android.widget.ImageView a0, int i, int i0)
    {
        if (a != null)
        {
            a.setVisibility(i);
        }
        if (a0 != null)
        {
            a0.setVisibility(i0);
        }
        this.channelListAdapter.notifyDataSetChanged();
    }

    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 30, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 341, in addCasts
    //    param.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 478, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/content/Context
    private void enableSomeFucitonWhenBecomeMaster();


    private void enterEditMode()
    {
        this.editMenu.setVisibility(0);
        this.tabWidget.setVisibility(8);
        this.allSelectedBtn.setImageResource(2130837969);
        this.allSelectedBtn.setVisibility(0);
        this.ChannelListView.setLongClickable(false);
        enable_edit = true;
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

    private void exitEditMode()
    {
        this.editMenu.setVisibility(8);
        this.tabWidget.setVisibility(0);
        this.allSelectedBtn.setVisibility(8);
        this.ChannelListView.setLongClickable(true);
        enable_edit = false;
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

    private void findChannel()
    {
        java.util.ArrayList a = new java.util.ArrayList();
        String s = this.mSearchChannelEdit.getText().toString();
        this.mSearchMode = 0;
        int i = s.length();
        if (s.equals((Object)this.mSearchChannelKeywords) && !this.mChannelListChangeFlag)
        {
            return;
        }
        if (i == 0)
        {
            this.mSearchChannelKeywords = "";
            this.mCurrentChannelList = this.mOriginalChannelListModels;
            this.mSearchChannelListModels = this.mCurrentChannelList;
            this.displayOrDimissLayout(this.mSearchFailedPrompt, this.mClearSearchKeyword, 8, 8);
            this.adjustSelectionOfChannelListView(true);
            return;
        }
        if (this.mSearchChannelKeywords.length() >= i)
        {
            this.mSearchChannelListModels = this.mOriginalChannelListModels;
        }
        if (this.mSearchChannelListModels.size() == 0)
        {
            this.displayOrDimissLayout(this.mSearchFailedPrompt, this.mClearSearchKeyword, 0, 0);
            return;
        }
        this.mSearchChannelKeywords = s;
        this.displayOrDimissLayout((android.widget.LinearLayout)null, this.mClearSearchKeyword, 0, 0);
        this.judgeSearchMode(i, s);
        Object a0 = this.mSearchChannelListModels.iterator();
        while(((java.util.Iterator)a0).hasNext())
        {
            boolean b = false;
            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a1 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a0).next();
            int[] a2 = new int[i];
            String s0 = new StringBuilder(String.valueOf(a1.getCurrentChannelListDispIndex())).toString();
            if (this.mSearchMode != 0)
            {
                b = false;
            }
            else
            {
                b = this.isChannelDispIndexMatched(s0, s);
                if (b)
                {
                    a1.setSearchChannelSortPriority(-2);
                }
            }
            if (!b)
            {
                int i0 = a1.getProgramName().toUpperCase().indexOf(s.toUpperCase());
                if (-1 != i0)
                {
                    a2[0] = i0;
                    a1.setMatchChannelNameIndexArray(a2);
                    a1.setSearchChannelSortPriority(-1);
                    b = true;
                }
                if (!b && 1 != i)
                {
                    b = this.hasMatchStringIndexFound(a1.getProgramName(), s, a2);
                    if (b)
                    {
                        a1.setMatchChannelNameIndexArray(a2);
                        a1.setSearchChannelSortPriority(this.getSearchChannelSortPriority(a2));
                    }
                }
            }
            if (b)
            {
                ((java.util.List)(Object)a).add(this.calculateChannelInsertPosition(a1, (java.util.List)(Object)a), (Object)a1);
            }
        }
        this.mSearchChannelListModels = (java.util.List)(Object)a;
        this.mCurrentChannelList = this.mSearchChannelListModels;
        if (this.mCurrentChannelList.isEmpty())
        {
            this.displayOrDimissLayout(this.mSearchFailedPrompt, (android.widget.ImageView)null, 0, 0);
            return;
        }
        this.displayOrDimissLayout(this.mSearchFailedPrompt, (android.widget.ImageView)null, 8, 0);
        this.ChannelListView.setSelection(0);
    }

    private java.util.ArrayList getChannelTypeData()
    {
        java.util.ArrayList a = new java.util.ArrayList();
        String[] a0 = this.getResources().getStringArray(2131558412);
        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbPlatform()){
            case 30: case 31: case 32: case 71: case 72: case 74: {
                int i = 0;
                if (this.channelListTypeMap == null)
                {
                    this.channelListTypeMap = new android.util.SparseIntArray();
                }
                this.channelListTypeMap.clear();
                if (mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() != 0)
                {
                    i = 0;
                    while(i < a0.length - 1)
                    {
                        a.add((Object)a0[i]);
                        this.channelListTypeMap.append(i, i);
                        i = i + 1;
                    }
                }
                else
                {
                    i = 0;
                    while(i < a0.length)
                    {
                        a.add((Object)a0[i]);
                        this.channelListTypeMap.append(i, i);
                        i = i + 1;
                    }
                }
                int i0 = 0;
                while(i0 < mktvsmart.screen.GMScreenGlobalInfo.favGroups.size())
                {
                    Object a1 = this.mChannelData.getChannelListByTvRadioType().iterator();
                    while(true)
                    {
                        if (((java.util.Iterator)a1).hasNext())
                        {
                            if (!((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a1).next()).mfavGroupIDs.contains((Object)Integer.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertFavorModel)mktvsmart.screen.GMScreenGlobalInfo.favGroups.get(i0)).getFavorTypeID())))
                            {
                                continue;
                            }
                            a.add((Object)((mktvsmart.screen.dataconvert.model.DataConvertFavorModel)mktvsmart.screen.GMScreenGlobalInfo.favGroups.get(i0)).GetFavorName());
                            this.channelListTypeMap.append(i, ((mktvsmart.screen.dataconvert.model.DataConvertFavorModel)mktvsmart.screen.GMScreenGlobalInfo.favGroups.get(i0)).getFavorTypeID() + 4);
                            i = i + 1;
                        }
                        i0 = i0 + 1;
                        break;
                    }
                }
                break;
            }
            default: {
                if (mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() != 0)
                {
                    int i1 = 0;
                    while(i1 < a0.length - 1)
                    {
                        a.add((Object)a0[i1]);
                        i1 = i1 + 1;
                    }
                }
                else
                {
                    int i2 = 0;
                    while(i2 < a0.length)
                    {
                        a.add((Object)a0[i2]);
                        i2 = i2 + 1;
                    }
                }
                this.channelTypeFavMark = 0;
                Object a2 = this.mChannelData.getChannelListByTvRadioType().iterator();
                while(((java.util.Iterator)a2).hasNext())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertChannelModel a3 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a2).next();
                    this.channelTypeFavMark = this.channelTypeFavMark | a3.GetFavMark();
                }
                int i3 = this.channelTypeFavMark;
                int i4 = 0;
                while(i3 > 0)
                {
                    if ((i3 & 1) > 0)
                    {
                        a.add((Object)(String)mktvsmart.screen.GMScreenGlobalInfo.favType.get(i4));
                    }
                    i3 = i3 >>> 1;
                    i4 = i4 + 1;
                }
            }
        }
        return a;
    }

    private int getSearchChannelSortPriority(int[] a)
    {
        int[] a0 = new int[a.length - 1];
        int i = 0;
        int i0 = 0;
        while(i < a.length - 1)
        {
            if (a[i] != a[i + 1] - 1)
            {
                i0 = i0 + 1;
            }
            else
            {
                a0[i0] = a0[i0] + 1;
            }
            i = i + 1;
        }
        int i1 = 0;
        int i2 = 0;
        while(i1 < a0.length - 1)
        {
            if ((a0[i2] != 0) >= (a0[i1 + 1] != 0))
            {
                i1 = i1 + 1;
            }
            else
            {
                i2 = i1 + 1;
                i1 = i2;
            }
        }
        return a0[i2] + 1;
    }

    private java.util.ArrayList getSortTypeArray()
    {
        java.util.ArrayList a = new java.util.ArrayList();
        if (mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().getPlatform_id() != 30)
        {
            String[] a0 = this.getResources().getStringArray(2131558400);
            int i = 0;
            while(i < a0.length)
            {
                a.add((Object)a0[i]);
                i = i + 1;
            }
        }
        else
        {
            String[] a1 = this.getResources().getStringArray(2131558401);
            int i0 = 0;
            while(i0 < a1.length)
            {
                a.add((Object)a1[i0]);
                i0 = i0 + 1;
            }
        }
        return a;
    }

    private boolean hasMatchStringIndexFound(String s, String s0, int[] a)
    {
        int i = 0;
        int i0 = 0;
        while(i0 < s0.length())
        {
            int i1 = i;
            while(true)
            {
                boolean b = false;
                label4: if (i < s.length())
                {
                    int i2 = s0.charAt(i0);
                    label3: {
                        label0: {
                            label1: {
                                label2: {
                                    if (48 > i2)
                                    {
                                        break label2;
                                    }
                                    int i3 = s0.charAt(i0);
                                    if (57 < i3)
                                    {
                                        break label2;
                                    }
                                    int i4 = s0.charAt(i0);
                                    int i5 = s.charAt(i);
                                    if (i4 != i5)
                                    {
                                        break label1;
                                    }
                                    break label3;
                                }
                                int i6 = s0.charAt(i0);
                                int i7 = s.charAt(i);
                                if (i6 == i7)
                                {
                                    break label0;
                                }
                                int i8 = s0.charAt(i0);
                                int i9 = (char)(i8 - 32);
                                int i10 = s.charAt(i);
                                if (i9 == i10)
                                {
                                    break label0;
                                }
                                int i11 = s0.charAt(i0);
                                int i12 = (char)(i11 + 32);
                                int i13 = s.charAt(i);
                                if (i12 == i13)
                                {
                                    break label0;
                                }
                            }
                            i = i + 1;
                            continue;
                        }
                        a[i0] = i;
                        i = i + 1;
                        b = true;
                        break label4;
                    }
                    a[i0] = i;
                    i = i + 1;
                    b = true;
                }
                else
                {
                    b = false;
                    i = i1;
                }
                if (!b)
                {
                    return false;
                }
                i0 = i0 + 1;
                break;
            }
        }
        return true;
    }

    private void initItemChecked()
    {
        Object a = this.mCurrentChannelList.iterator();
        while(((java.util.Iterator)a).hasNext())
        {
            ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a).next()).setSelectedFlag(false);
        }
    }

    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 54, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 341, in addCasts
    //    param.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 341, in addCasts
    //    param.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 642, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/content/Context
    private void inputPermissionPassword();


    private boolean isChannelDispIndexMatched(String s, String s0)
    {
        if (s0.length() <= s.length())
        {
            int i = 0;
            while(true)
            {
                if (i >= s0.length())
                {
                    return true;
                }
                int i0 = s0.charAt(i);
                int i1 = s.charAt(i);
                if (i0 != i1)
                {
                    break;
                }
                i = i + 1;
            }
        }
        return false;
    }

    private void judgeSearchMode(int i, String s)
    {
        int i0 = 0;
        while(i0 < i)
        {
            int i1 = s.charAt(i0);
            {
                if (48 <= i1)
                {
                    int i2 = s.charAt(i0);
                    if (57 >= i2)
                    {
                        i0 = i0 + 1;
                        continue;
                    }
                }
                this.mSearchMode = 1;
                return;
            }
        }
        int i3 = Integer.parseInt(s);
        int i4 = this.mOriginalChannelListModels.size();
        label0: {
            label1: {
                if (i3 > i4)
                {
                    break label1;
                }
                if (i3 != 0)
                {
                    break label0;
                }
            }
            this.mSearchMode = 1;
        }
    }

    private static java.util.Collection list(String[] a)
    {
        return java.util.Collections.unmodifiableCollection((java.util.Collection)(Object)java.util.Arrays.asList((Object[])a));
    }

    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 30, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 642, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/content/Context
    private void otherPlatformPlay(int arg);


    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 54, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 478, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/content/Context
    private void playStream(int arg);


    private void requestProgramListFromTo(int i, int i0)
    {
        try
        {
            java.util.ArrayList a = new java.util.ArrayList();
            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a0 = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a1 = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
            a0.SetProgramIndex(i);
            a1.SetProgramIndex(i0);
            a.add((Object)a0);
            a.add((Object)a1);
            this.parser = mktvsmart.screen.dataconvert.parser.ParserFactory.getParser();
            this.dataBuff = this.parser.serialize((java.util.List)(Object)a, 0).getBytes("UTF-8");
            mktvsmart.screen.GsSendSocket.sendSocketToStb(this.dataBuff, this.tcpSocket, 0, this.dataBuff.length, 0);
        }
        catch(Exception a2)
        {
            a2.printStackTrace();
            return;
        }
    }

    private void sendSat2ipChannelRequestToStb(int i)
    {
        java.util.ArrayList a = new java.util.ArrayList();
        this.currentSat2ipChannelProgramId = ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)this.channelListAdapter.getItem(i)).GetProgramId();
        label0: {
            mktvsmart.screen.exception.ProgramNotFoundException a0 = null;
            try
            {
                try
                {
                    a.add((Object)(mktvsmart.screen.dataconvert.model.DataConvertChannelModel)this.channelListAdapter.getItem(i));
                    byte[] a1 = this.parser.serialize((java.util.List)(Object)a, 1009).getBytes("UTF-8");
                    this.tcpSocket.setSoTimeout(3000);
                    mktvsmart.screen.GsSendSocket.sendSocketToStb(a1, this.tcpSocket, 0, a1.length, 1009);
                    break label0;
                }
                catch(mktvsmart.screen.exception.ProgramNotFoundException a2)
                {
                    a0 = a2;
                }
            }
            catch(Exception a3)
            {
                a3.printStackTrace();
                return;
            }
            a0.printStackTrace();
            return;
        }
    }

    private void setCurrentChannelListDispIndex()
    {
        int i = 0;
        while(i < this.mCurrentChannelList.size())
        {
            ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)this.mCurrentChannelList.get(i)).setCurrentChannelListDispIndex(i + 1);
            i = i + 1;
        }
    }

    private void setDisplayFavValue()
    {
        java.util.Iterator a = this.mCurrentChannelList.iterator();
        int i = 0;
        Object a0 = a;
        while(((java.util.Iterator)a0).hasNext())
        {
            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a1 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a0).next();
            if (a1.getSelectedFlag())
            {
                this.favorModels.add((Object)a1);
                i = i + 1;
            }
        }
        int[] a2 = new int[i];
        int i0 = 0;
        while(i0 < this.favorModels.size())
        {
            a2[i0] = ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)this.favorModels.get(i0)).GetFavMark();
            i0 = i0 + 1;
        }
        int i1 = 0;
        while(i1 < this.mIsChoice.length)
        {
            int i2 = 0;
            while(true)
            {
                if (i2 < i)
                {
                    if ((a2[i2] != 0 & this.mFavValueArray[i1] != 0) != (this.mFavValueArray[i1] != 0))
                    {
                        this.mIsChoice[i1] = false;
                    }
                    else
                    {
                        if (i2 != i - 1)
                        {
                            i2 = i2 + 1;
                            continue;
                        }
                        this.mIsChoice[i1] = true;
                    }
                }
                if (this.mIsChoice[i1])
                {
                    this.mFavValue = this.mFavValue | this.mFavValueArray[i1];
                }
                i1 = i1 + 1;
                break;
            }
        }
    }

    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 30, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 642, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/app/Activity
    private void setMessageProcess();


    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 30, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 642, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/telephony/PhoneStateListener
    private void setPhoneAndSmsListener();


    private void setSearchMenuDisable()
    {
        this.mSearchChannelEdit.setVisibility(8);
        this.mSearchCancelBtn.setVisibility(8);
        this.mClearSearchKeyword.setVisibility(8);
        this.mSearchFailedPrompt.setVisibility(8);
        this.mEnterSearchFlag = false;
    }

    private void setSearchMenuEnable()
    {
        this.mSearchChannelEdit.setText((CharSequence)(Object)"");
        this.mSearchChannelEdit.setVisibility(0);
        this.mSearchChannelEdit.setFocusable(true);
        this.mSearchChannelEdit.setFocusableInTouchMode(true);
        this.mSearchChannelEdit.requestFocus();
        this.mSearchCancelBtn.setVisibility(0);
        this.mEnterSearchFlag = true;
    }

    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 54, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 457, in addCasts_sub
    //    def addCasts_sub(self, env): self.fix(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/widget/ListView
    private void showChannelTypePopupWindow(android.view.View arg);


    private void startPlayStream(int i, android.content.Intent a)
    {
        if (this.waitDialog.isShowing())
        {
            this.waitDialog.dismiss();
        }
        this.waitDialog = mktvsmart.screen.util.DialogBuilder.showProgressDialog(this.getParent(), 2131427641, 2131427520, false, (long)mktvsmart.screen.GMScreenGlobalInfo.getmWaitDialogTimeOut(), this.timeOutRun);
        this.abtainPlayUrl(i, a);
    }

    private void stopStream()
    {
        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbPlatform()){
            case 8: case 9: {
                if (sRtsp != null)
                {
                    sRtsp.teardown();
                    sRtsp = null;
                    com.alitech.dvbtoip.DVBtoIP.destroyResourceForPlayer();
                    this.isSat2ipStarted = false;
                    this.currentSat2ipChannelProgramId = "";
                    mktvsmart.screen.GMScreenGlobalInfo.playType = 0;
                    mktvsmart.screen.GsSendSocket.sendOnlyCommandSocketToStb(this.tcpSocket, 1012);
                    return;
                }
                break;
            }
            default: {
                mktvsmart.screen.GMScreenGlobalInfo.playType = 0;
                mktvsmart.screen.GsSendSocket.sendOnlyCommandSocketToStb(this.tcpSocket, 1012);
            }
        }
    }

    private void synchronizeSTBChannelType(int i)
    {
        label3: {
            label0: {
                java.io.UnsupportedEncodingException a = null;
                try
                {
                    try
                    {
                        this.parser = mktvsmart.screen.dataconvert.parser.ParserFactory.getParser();
                        java.util.ArrayList a0 = new java.util.ArrayList();
                        mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel a1 = new mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel();
                        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbPlatform()){
                            case 30: case 31: case 32: case 71: case 72: case 74: {
                                label2: {
                                    if (i < 0)
                                    {
                                        break label2;
                                    }
                                    if (i > 3)
                                    {
                                        break label2;
                                    }
                                    a1.setIsFavList(0);
                                    a1.setSelectListType(i);
                                    break;
                                }
                                if (i < 4)
                                {
                                    break label0;
                                }
                                a1.setIsFavList(1);
                                a1.setSelectListType(i - 4);
                                break;
                            }
                            default: {
                                {
                                    label1: {
                                        if (i < 0)
                                        {
                                            break label1;
                                        }
                                        if (i > 3)
                                        {
                                            break label1;
                                        }
                                        a1.setIsFavList(0);
                                        a1.setSelectListType(i);
                                        break;
                                    }
                                    if (i < 4)
                                    {
                                        break label0;
                                    }
                                    if (i > 11)
                                    {
                                        break label0;
                                    }
                                    a1.setIsFavList(1);
                                    a1.setSelectListType(i - 4);
                                }
                            }
                        }
                        ((java.util.List)(Object)a0).add((Object)a1);
                        this.dataBuff = this.parser.serialize((java.util.List)(Object)a0, 1007).getBytes("UTF-8");
                        mktvsmart.screen.GsSendSocket.sendSocketToStb(this.dataBuff, this.tcpSocket, 0, this.dataBuff.length, 1007);
                        break label3;
                    }
                    catch(java.io.UnsupportedEncodingException a2)
                    {
                        a = a2;
                    }
                }
                catch(Exception a3)
                {
                    a3.printStackTrace();
                    return;
                }
                a.printStackTrace();
                return;
            }
            return;
        }
    }

    private void trueNewChannelInStb(int i)
    {
        try
        {
            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)this.channelListAdapter.getItem(i);
            String s = a.getProgramName();
            String s0 = a.GetProgramId();
            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a0 = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
            java.util.ArrayList a1 = new java.util.ArrayList();
            this.parser = mktvsmart.screen.dataconvert.parser.ParserFactory.getParser();
            a0.SetProgramIndex(i);
            a0.setProgramName(s);
            a0.SetProgramId(s0);
            a0.setChannelTpye(a.getChannelTpye());
            ((java.util.List)(Object)a1).add((Object)a0);
            byte[] a2 = this.parser.serialize((java.util.List)(Object)a1, 1000).getBytes("UTF-8");
            this.tcpSocket.setSoTimeout(3000);
            mktvsmart.screen.GsSendSocket.sendSocketToStb(a2, this.tcpSocket, 0, a2.length, 1000);
        }
        catch(Exception a3)
        {
            a3.printStackTrace();
            return;
        }
    }

    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 70, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 642, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/app/Activity
    public boolean dispatchKeyEvent(android.view.KeyEvent arg);


    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 30, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 642, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/app/Activity
    public void onBackPressed();


    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 30, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 642, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/app/Activity
    public void onConfigurationChanged(android.content.res.Configuration arg);


    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 37, in _getMethod
    //    graph = cb(method) if method.code is not None else None
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\decompile.py", line 45, in makeGraph
    //    v = verifyBytecode(m.code)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\verifier\inference_verifier.py", line 493, in verifyBytecode
    //    node.update(iNodeLookup, exceptions)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\verifier\inference_verifier.py", line 436, in update
    //    self._mergeSingleSuccessor(iNodes[k], newstate, iNodes, False)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\verifier\inference_verifier.py", line 408, in _mergeSingleSuccessor
    //    changed = other.state.merge(newstate, self.env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\verifier\inference_verifier.py", line 71, in merge
    //    self.locals = [mergeTypes(env, new, old) for old, new in zip(self.locals, other.locals)]
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\verifier\verifier_types.py", line 127, in mergeTypes
    //    if 'INTERFACE' in env.getFlags(t2.extra):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 53, in getFlags
    //    raise e
    //ClassLoaderError:
    //ClassNotFoundException: android/content/Intent
    protected void onCreate(android.os.Bundle arg);


    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 30, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 642, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/app/Activity
    protected void onDestroy();


    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 30, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 642, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/app/Activity
    protected void onPause();


    //Traceback (most recent call last):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javaclass.py", line 39, in _getMethod
    //    code_ast = javamethod.generateAST(method, graph, forbidden_identifiers)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 864, in generateAST
    //    _preorder(ast_root, partial(_addCastsAndParens, env=env))
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 138, in _preorder
    //    val = func(scope, item)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\javamethod.py", line 723, in _addCastsAndParens
    //    item.addCastsAndParens(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 30, in addCastsAndParens
    //    self.expr.addCasts(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 342, in addCasts
    //    self.addCasts_sub(env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 642, in addCasts_sub
    //    expr = makeCastExpr(tt, expr, fixEnv=env)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 304, in makeCastExpr
    //    ret = ret.fix(fixEnv)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 451, in fix
    //    if not isJavaAssignable(env, tt, expr.dtype):
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\java\ast.py", line 277, in isJavaAssignable
    //    return objtypes.isSubtype(env, fromt, to)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\ssa\objtypes.py", line 55, in isSubtype
    //    return isBaseTClass(x) and isBaseTClass(y) and env.isSubclass(xname, yname)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 33, in isSubclass
    //    name1 = self.getClass(name1).supername
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 21, in getClass
    //    result = self._loadClass(name)
    //  File "C:\Users\it\.Bytecode-Viewer\krakatau_9\Krakatau-master\Krakatau\environment.py", line 79, in _loadClass
    //    raise ClassLoaderError('ClassNotFoundException', name)
    //ClassLoaderError:
    //ClassNotFoundException: android/app/Activity
    protected void onResume();


    public void onTabActivityResult(int i, int i0, android.content.Intent a)
    {
        if (i0 != 20)
        {
            return;
        }
        this.isFavorChange = a.getBooleanExtra("favorChange", true);
        if (this.isFavorChange)
        {
            this.favMark = a.getIntExtra("favorValue", 0);
            Object a0 = this.favorModels.iterator();
            while(((java.util.Iterator)a0).hasNext())
            {
                ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a0).next()).SetFavMark(this.favMark);
            }
            this.favMark = 0;
            label0: {
                java.io.UnsupportedEncodingException a1 = null;
                try
                {
                    try
                    {
                        byte[] a2 = this.parser.serialize(this.favorModels, 1004).getBytes("UTF-8");
                        this.tcpSocket.setSoTimeout(3000);
                        mktvsmart.screen.GsSendSocket.sendSocketToStb(a2, this.tcpSocket, 0, a2.length, 1004);
                        break label0;
                    }
                    catch(java.io.UnsupportedEncodingException a3)
                    {
                        a1 = a3;
                    }
                }
                catch(Exception a4)
                {
                    a4.printStackTrace();
                    break label0;
                }
                a1.printStackTrace();
            }
            if (this.waitDialog.isShowing())
            {
                this.waitDialog.dismiss();
            }
            this.waitDialog = mktvsmart.screen.util.DialogBuilder.showProgressDialog(this.getParent(), 2131427517, 2131427520, false, (long)mktvsmart.screen.GMScreenGlobalInfo.getmWaitDialogTimeOut(), 2131427638);
        }
        this.initItemChecked();
        this.channelListAdapter.notifyDataSetChanged();
    }
}
