package mktvsmart.screen.dataconvert.parser.Procyon;

import android.util.*;
import mktvsmart.screen.*;
import mktvsmart.screen.dataconvert.parser.DataParser;
import mktvsmart.screen.gchat.bean.*;
import java.io.*;
import mktvsmart.screen.vlc.*;
import mktvsmart.screen.dataconvert.model.*;
import org.xmlpull.v1.*;
import java.util.*;

public class XmlParser implements DataParser
{

    public static final int DATACONVERTSORTMODEL = 13;
    public static final int DATACONVERTCHANNELTYPPEMODEL = 12;
    public static final int DATACONVERTCHANNELMODEL = 0;
    public static final int DATACONVERTTIMEMODEL_1 = 7;
    public static final int DATACONVERTTIMEMODEL_2 = 9 ;
    public static final int DATACONVERTTIMEMODEL_3 = 1;
    public static final int DATACONVERTTIMEMODEL_4 = 4;
    public static final int DATACONVERTCONTROLMODEL = 2;
    public static final int DATACONVERTUPDATEMODEL = 3;
    public static final int GSEPGEVENT = 6;
    public static final int DATACONVERTCHANNELMODEL_2 = 8;
    public static final int DATACONVERTFAVORMODEL = 10;
    public static final int DATACONVERTCONTROLMODEL_2 = 11;
    public static final int DATACONVERTSTBINFOMODEL = 14;
    public static final int STRING_DATACONVERTONEDATAMODEL = 15;
    public static final int GMSCREENGLOBALINFO_AND_CURRENT_CHANNEL_TV_RADIO_TYPE = 17;
    public static final int MAP = 16;
    public static final int DATACONVERTSATMODEL = 18;
    public static final int DATACONVERTTPMODEL = 19;
    public static final int DATACONVERTPVRINFOMODEL = 20;
    public static final int GSCHATSETTINGS = 21;
    public static final int GSCHATSETTINGS_2 = 25;
    public static final int DATACONVERTCHATMSGMODEL = 23;
    public static final int GSCHATROOMINFO_AND_GSCHATUSER = 22;
    public static final int GSCHATUSER = 24;
    public static final int DATACONVERTGCHATCHANNELINFOMODEL = 26;
    public static final int DATACONVERTUSERNAMEMODEL = 27;




    public byte[] IntToByteArray(final int n) {
        final byte[] array = new byte[8];
        for (int i = 0; i < 8; ++i) {
            array[i] = (byte)(n >> (7 - i) * 8 & 0xFFFF);
        }
        return array;
    }

    public List<?> parse(final InputStream inputStream, int i) throws Exception {

        //ChannelData localDataParser.parse (..., i)
        // cmd     : i == 0 : init Channel List
        // cmd     : i == 15 : CurrentActiveChannel, RadioTypeModels,
        // cmd     : i == 18 : init Sat List
        // cmd     : i == 19 : init TP list

        //GsChannelListActivity GsChannelListActivity.this.parser.parse (..., i)
        // cmd    5: i == 6 : EPG? GsEPGTableChannel List
        // cmd    3: i == 15 : update active channel
        // cmd 4112: GsChannelListActivity.enable_edit = false;
        // cmd   20: i == 2 : ControlModel
        // cmd   18: i == 13 : DataConvertSortModel
        // cmd   16: i == 17 : ... cur_channel_list_type .... search "msgProc.setOnMessageProcess(16"
        // cmd   17: i == 15 : List<String> tvRadioTypeModels
        // cmd 1056: i == 15 : Send Password for Locked Channel? -  List<String> verifyResult
        // cmd   23: i == 15 : GMScreenGlobalInfo.setmSatIndexSelected; GsChannelListActivity.this.mCurrentChannelList; GsChannelListActivity.this.setCurrentChannelListDispIndex();
        // cmd 1002: i == 15 : delete? i + " channel is playing by the mobile, it can not be deleted!
        // cmd 1100: i == 21 : chat?
        // cmd 1009: i == 16 : stream ??? ((Map)localObject).get("success")

        List<?> list = null;
        Label_0130: {
            switch (i) {
                default: {
                    list = null;
                    break;
                }
                case DATACONVERTSORTMODEL: {
                    List<?> list2 = null;
                    DataConvertSortModel dataConvertSortModel = null;
                    final XmlPullParser pullParser = Xml.newPullParser();
                    pullParser.setInput(inputStream, "UTF-8");
                    i = pullParser.getEventType();
                    Label_0215_Outer:
                    while (i != XmlPullParser.END_DOCUMENT) {
                        DataConvertSortModel dataConvertSortModel2 = dataConvertSortModel;
                        List<?> list3 = list2;
                        while (true) {
                            switch (i) {
                                default: {
                                    list3 = list2;
                                    dataConvertSortModel2 = dataConvertSortModel;
                                    break Label_0215;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list3 = new ArrayList<Object>();
                                    dataConvertSortModel2 = dataConvertSortModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser.next();
                                    dataConvertSortModel = dataConvertSortModel2;
                                    list2 = list3;
                                    continue Label_0215_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser.getName().equals("parm")) {
                                        dataConvertSortModel2 = new DataConvertSortModel();
                                        list3 = list2;
                                        continue;
                                    }
                                    if (pullParser.getName().equals("SortType")) {
                                        pullParser.next();
                                        dataConvertSortModel.setmSortType(Integer.parseInt(pullParser.getText()));
                                        dataConvertSortModel2 = dataConvertSortModel;
                                        list3 = list2;
                                        continue;
                                    }
                                    if (pullParser.getName().equals("MacroFlag")) {
                                        pullParser.next();
                                        dataConvertSortModel.setmMacroFlag(Integer.parseInt(pullParser.getText()));
                                        dataConvertSortModel2 = dataConvertSortModel;
                                        list3 = list2;
                                        continue;
                                    }
                                    dataConvertSortModel2 = dataConvertSortModel;
                                    list3 = list2;
                                    if (!pullParser.getName().equals("SortTypeList")) {
                                        continue;
                                    }
                                    System.out.println("have SortTypeList");
                                    pullParser.next();
                                    final String[] split = pullParser.getText().split(",");
                                    dataConvertSortModel2 = dataConvertSortModel;
                                    list3 = list2;
                                    if (split == null) {
                                        continue;
                                    }
                                    dataConvertSortModel2 = dataConvertSortModel;
                                    list3 = list2;
                                    if (split.length > 0) {
                                        final ArrayList<String> sortTypeList = new ArrayList<String>();
                                        for (i = 0; i < split.length; ++i) {
                                            sortTypeList.add(split[i]);
                                        }
                                        dataConvertSortModel.setSortTypeList((ArrayList)sortTypeList);
                                        dataConvertSortModel2 = dataConvertSortModel;
                                        list3 = list2;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertSortModel2 = dataConvertSortModel;
                                    list3 = list2;
                                    if (pullParser.getName().equals("parm")) {
                                        list2.add(dataConvertSortModel);
                                        dataConvertSortModel2 = null;
                                        list3 = list2;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list2;
                }
                case DATACONVERTCHANNELTYPPEMODEL: {
                    List<?> list4 = null;
                    DataConvertChannelTypeModel dataConvertChannelTypeModel = null;
                    final XmlPullParser pullParser2 = Xml.newPullParser();
                    pullParser2.setInput(inputStream, "UTF-8");
                    i = pullParser2.getEventType();
                    Label_0623_Outer:
                    while (i != 1) {
                        DataConvertChannelTypeModel dataConvertChannelTypeModel2 = dataConvertChannelTypeModel;
                        List<?> list5 = list4;
                        while (true) {
                            switch (i) {
                                default: {
                                    list5 = list4;
                                    dataConvertChannelTypeModel2 = dataConvertChannelTypeModel;
                                    break Label_0623;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list5 = new ArrayList<Object>();
                                    dataConvertChannelTypeModel2 = dataConvertChannelTypeModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser2.next();
                                    dataConvertChannelTypeModel = dataConvertChannelTypeModel2;
                                    list4 = list5;
                                    continue Label_0623_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser2.getName().equals("parm")) {
                                        dataConvertChannelTypeModel2 = new DataConvertChannelTypeModel();
                                        list5 = list4;
                                        continue;
                                    }
                                    if (pullParser2.getName().equals("CurChannelType")) {
                                        pullParser2.next();
                                        DataConvertChannelTypeModel.setCurrent_channel_tv_radio_type(Integer.parseInt(pullParser2.getText()));
                                        dataConvertChannelTypeModel2 = dataConvertChannelTypeModel;
                                        list5 = list4;
                                        continue;
                                    }
                                    dataConvertChannelTypeModel2 = dataConvertChannelTypeModel;
                                    list5 = list4;
                                    if (pullParser2.getName().equals("tv_radio_key_press")) {
                                        pullParser2.next();
                                        dataConvertChannelTypeModel.setTvRadioKeyPress(Integer.parseInt(pullParser2.getText()));
                                        dataConvertChannelTypeModel2 = dataConvertChannelTypeModel;
                                        list5 = list4;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertChannelTypeModel2 = dataConvertChannelTypeModel;
                                    list5 = list4;
                                    if (pullParser2.getName().equals("parm")) {
                                        list4.add(dataConvertChannelTypeModel);
                                        dataConvertChannelTypeModel2 = null;
                                        list5 = list4;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list4;
                }
                case DATACONVERTCHANNELMODEL: {

                    /* ChannelList */

                    List<?> list6 = null;
                    DataConvertChannelModel dataConvertChannelModel = null;
                    final XmlPullParser pullParser3 = Xml.newPullParser();
                    pullParser3.setInput(inputStream, "UTF-8");
                    i = pullParser3.getEventType();
                    Label_0911_Outer:
                    while (i != 1) {
                        DataConvertChannelModel dataConvertChannelModel2 = dataConvertChannelModel;
                        List<?> list7 = list6;
                        Label_0911:
                        while (true) {
                            switch (i) {
                                default: {
                                    list7 = list6;
                                    dataConvertChannelModel2 = dataConvertChannelModel;
                                    break Label_0911;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list7 = new ArrayList<Object>();
                                    dataConvertChannelModel2 = dataConvertChannelModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser3.next();
                                    dataConvertChannelModel = dataConvertChannelModel2;
                                    list6 = list7;
                                    continue Label_0911_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser3.getName().equals("parm")) {
                                        dataConvertChannelModel2 = new DataConvertChannelModel();
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("SatIndexSelected")) {
                                        pullParser3.next();
                                        GMScreenGlobalInfo.setmSatIndexSelected(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("ProgramId")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.SetProgramId(pullParser3.getText());
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("ProgramName")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setProgramName(pullParser3.getText());
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("ProgramIndex")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.SetProgramIndex(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("SatName")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.SetSatName(pullParser3.getText());
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("ProgramType")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.SetIsProgramScramble(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("IsProgramHD")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setIsProgramHd(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("FavMark")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.SetFavMark(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("LockMark")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setLockMark(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("HaveEPG")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.SetHaveEPG(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("IsPlaying")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setIsPlaying(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("WillBePlayed")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setmWillBePlayed(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("ChannelType")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setChannelTpye(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("Frequency")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setFreq(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("Polar")) {
                                        char pol = 'h';
                                        pullParser3.next();
                                        final String text = pullParser3.getText();
                                        if (text.equals("0")) {
                                            pol = 'h';
                                        }
                                        else if (text.equals("1")) {
                                            pol = 'v';
                                        }
                                        else if (text.equals("2")) {
                                            pol = 'l';
                                        }
                                        else if (text.equals("3")) {
                                            pol = 'r';
                                        }
                                        dataConvertChannelModel.setPol(pol);
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("ModulationSystem")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setModulationSystem(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("ModulationType")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setModulationType(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("RollOff")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setRollOff(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("PilotTones")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setPilotTones(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("SymbolRate")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setSymRate(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("Fec")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setFec(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("VideoPid")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setVideoPid(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("AudioPid")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setAudioPid(pullParser3.getText());
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("TtxPid")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setTtxPid(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("SubPid")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setSubPid(pullParser3.getText());
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("PmtPid")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setPmtPid(Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    if (pullParser3.getName().equals("IsTuner2")) {
                                        pullParser3.next();
                                        dataConvertChannelModel.setIsTuner2((short)Integer.parseInt(pullParser3.getText()));
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        continue Label_0911;
                                    }
                                    dataConvertChannelModel2 = dataConvertChannelModel;
                                    list7 = list6;
                                    if (!pullParser3.getName().equals("FavorGroupID")) {
                                        continue Label_0911;
                                    }
                                    pullParser3.next();
                                    final String[] split2 = pullParser3.getText().split(":");
                                    dataConvertChannelModel2 = dataConvertChannelModel;
                                    list7 = list6;
                                    if (split2 == null) {
                                        continue Label_0911;
                                    }
                                    i = 0;
                                    while (true) {
                                        dataConvertChannelModel2 = dataConvertChannelModel;
                                        list7 = list6;
                                        if (i >= split2.length) {
                                            continue Label_0911;
                                        }
                                        dataConvertChannelModel.mfavGroupIDs.add(Integer.parseInt(split2[i]));
                                        ++i;
                                    }
                                    break;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertChannelModel2 = dataConvertChannelModel;
                                    list7 = list6;
                                    if (pullParser3.getName().equals("parm")) {
                                        if (GMScreenGlobalInfo.isSdsOpen() == 0 || dataConvertChannelModel.getIsTuner2() == 0) {
                                            list6.add(dataConvertChannelModel);
                                        }
                                        dataConvertChannelModel2 = null;
                                        list7 = list6;
                                    }
                                    continue Label_0911;
                                }
                            }
                            break;
                        }
                    }
                    return list6;
                }
                case DATACONVERTTIMEMODEL_1: {
                    List<?> list8 = null;
                    DataConvertTimeModel dataConvertTimeModel = null;
                    final XmlPullParser pullParser4 = Xml.newPullParser();
                    pullParser4.setInput(inputStream, "UTF-8");
                    i = pullParser4.getEventType();
                    Label_2555_Outer:
                    while (true) {
                        list = list8;
                        if (i == 1) {
                            break Label_0130;
                        }
                        DataConvertTimeModel dataConvertTimeModel2 = dataConvertTimeModel;
                        List<?> list9 = list8;
                        while (true) {
                            switch (i) {
                                default: {
                                    list9 = list8;
                                    dataConvertTimeModel2 = dataConvertTimeModel;
                                    break Label_2555;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list9 = new ArrayList<Object>();
                                    dataConvertTimeModel2 = dataConvertTimeModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser4.next();
                                    dataConvertTimeModel = dataConvertTimeModel2;
                                    list8 = list9;
                                    continue Label_2555_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser4.getName().equals("parm")) {
                                        dataConvertTimeModel2 = new DataConvertTimeModel();
                                        list9 = list8;
                                        continue;
                                    }
                                    if (pullParser4.getName().equals("Confirm")) {
                                        pullParser4.next();
                                        DataConvertTimeModel.isConfirm = Integer.parseInt(pullParser4.getText());
                                        dataConvertTimeModel2 = dataConvertTimeModel;
                                        list9 = list8;
                                        continue;
                                    }
                                    dataConvertTimeModel2 = dataConvertTimeModel;
                                    list9 = list8;
                                    if (pullParser4.getName().equals("TimerIndex")) {
                                        pullParser4.next();
                                        dataConvertTimeModel.SetTimerIndex(Integer.parseInt(pullParser4.getText()));
                                        dataConvertTimeModel2 = dataConvertTimeModel;
                                        list9 = list8;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertTimeModel2 = dataConvertTimeModel;
                                    list9 = list8;
                                    if (pullParser4.getName().equals("parm")) {
                                        list8.add(dataConvertTimeModel);
                                        dataConvertTimeModel2 = null;
                                        list9 = list8;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
                case DATACONVERTTIMEMODEL_2: {
                    List<?> list10 = null;
                    Object o = null;
                    final XmlPullParser pullParser5 = Xml.newPullParser();
                    pullParser5.setInput(inputStream, "UTF-8");
                    i = pullParser5.getEventType();
                    Label_2843_Outer:
                    while (true) {
                        list = list10;
                        if (i == 1) {
                            break Label_0130;
                        }
                        Object o2 = o;
                        List<?> list11 = list10;
                        while (true) {
                            switch (i) {
                                default: {
                                    list11 = list10;
                                    o2 = o;
                                    break Label_2843;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list11 = new ArrayList<Object>();
                                    o2 = o;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser5.next();
                                    o = o2;
                                    list10 = list11;
                                    continue Label_2843_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser5.getName().equals("StbMonth")) {
                                        pullParser5.next();
                                        DataConvertTimeModel.stbMonth = Integer.parseInt(pullParser5.getText());
                                        o2 = o;
                                        list11 = list10;
                                        continue;
                                    }
                                    if (pullParser5.getName().equals("StbDay")) {
                                        pullParser5.next();
                                        DataConvertTimeModel.stbDay = Integer.parseInt(pullParser5.getText());
                                        o2 = o;
                                        list11 = list10;
                                        continue;
                                    }
                                    if (pullParser5.getName().equals("StbHour")) {
                                        pullParser5.next();
                                        DataConvertTimeModel.stbHour = Integer.parseInt(pullParser5.getText());
                                        o2 = o;
                                        list11 = list10;
                                        continue;
                                    }
                                    o2 = o;
                                    list11 = list10;
                                    if (pullParser5.getName().equals("StbMin")) {
                                        pullParser5.next();
                                        DataConvertTimeModel.stbMin = Integer.parseInt(pullParser5.getText());
                                        o2 = o;
                                        list11 = list10;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    o2 = o;
                                    list11 = list10;
                                    if (pullParser5.getName().equals("parm")) {
                                        list10.add(o);
                                        o2 = null;
                                        list11 = list10;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
                case DATACONVERTTIMEMODEL_3:
                case DATACONVERTTIMEMODEL_4: {
                    List<?> list12 = null;
                    DataConvertTimeModel dataConvertTimeModel3 = null;
                    final XmlPullParser pullParser6 = Xml.newPullParser();
                    pullParser6.setInput(inputStream, "UTF-8");
                    i = pullParser6.getEventType();
                    Label_3195_Outer:
                    while (true) {
                        list = list12;
                        if (i == 1) {
                            break Label_0130;
                        }
                        DataConvertTimeModel dataConvertTimeModel4 = dataConvertTimeModel3;
                        List<?> list13 = list12;
                        while (true) {
                            switch (i) {
                                default: {
                                    list13 = list12;
                                    dataConvertTimeModel4 = dataConvertTimeModel3;
                                    break Label_3195;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list13 = new ArrayList<Object>();
                                    dataConvertTimeModel4 = dataConvertTimeModel3;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser6.next();
                                    dataConvertTimeModel3 = dataConvertTimeModel4;
                                    list12 = list13;
                                    continue Label_3195_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser6.getName().equals("Confirm")) {
                                        pullParser6.next();
                                        DataConvertTimeModel.isConfirm = Integer.parseInt(pullParser6.getText());
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                        continue;
                                    }
                                    if (pullParser6.getName().equals("parm")) {
                                        dataConvertTimeModel4 = new DataConvertTimeModel();
                                        list13 = list12;
                                        continue;
                                    }
                                    if (pullParser6.getName().equals("TimerProgramName")) {
                                        pullParser6.next();
                                        dataConvertTimeModel3.SetTimeProgramName(pullParser6.getText());
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                        continue;
                                    }
                                    if (pullParser6.getName().equals("TimerProgramSatTpId")) {
                                        pullParser6.next();
                                        dataConvertTimeModel3.setProgramId(pullParser6.getText());
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                        continue;
                                    }
                                    if (pullParser6.getName().equals("TimerMonth")) {
                                        pullParser6.next();
                                        dataConvertTimeModel3.SetTimeMonth(Integer.parseInt(pullParser6.getText()));
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                        continue;
                                    }
                                    if (pullParser6.getName().equals("TimerDay")) {
                                        pullParser6.next();
                                        dataConvertTimeModel3.SetTimeDay(Integer.parseInt(pullParser6.getText()));
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                        continue;
                                    }
                                    if (pullParser6.getName().equals("TimerStartHour")) {
                                        pullParser6.next();
                                        dataConvertTimeModel3.SetStartHour(Integer.parseInt(pullParser6.getText()));
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                        continue;
                                    }
                                    if (pullParser6.getName().equals("TimerStartMin")) {
                                        pullParser6.next();
                                        dataConvertTimeModel3.SetStartMin(Integer.parseInt(pullParser6.getText()));
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                        continue;
                                    }
                                    if (pullParser6.getName().equals("TimerEndHour")) {
                                        pullParser6.next();
                                        dataConvertTimeModel3.SetEndHour(Integer.parseInt(pullParser6.getText()));
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                        continue;
                                    }
                                    if (pullParser6.getName().equals("TimerEndMin")) {
                                        pullParser6.next();
                                        dataConvertTimeModel3.SetEndMin(Integer.parseInt(pullParser6.getText()));
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                        continue;
                                    }
                                    if (pullParser6.getName().equals("TimerRepeat")) {
                                        pullParser6.next();
                                        dataConvertTimeModel3.SetTimerRepeat(Integer.parseInt(pullParser6.getText()));
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                        continue;
                                    }
                                    if (pullParser6.getName().equals("TimerStatus")) {
                                        pullParser6.next();
                                        dataConvertTimeModel3.SetTimerStatus(Integer.parseInt(pullParser6.getText()));
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                        continue;
                                    }
                                    dataConvertTimeModel4 = dataConvertTimeModel3;
                                    list13 = list12;
                                    if (pullParser6.getName().equals("TimerEventID")) {
                                        pullParser6.next();
                                        dataConvertTimeModel3.setEventId(Integer.parseInt(pullParser6.getText()));
                                        dataConvertTimeModel4 = dataConvertTimeModel3;
                                        list13 = list12;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertTimeModel4 = dataConvertTimeModel3;
                                    list13 = list12;
                                    if (pullParser6.getName().equals("parm")) {
                                        list12.add(dataConvertTimeModel3);
                                        dataConvertTimeModel4 = null;
                                        list13 = list12;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
                case DATACONVERTCONTROLMODEL: {
                    List<?> list14 = null;
                    DataConvertControlModel dataConvertControlModel = null;
                    final XmlPullParser pullParser7 = Xml.newPullParser();
                    pullParser7.setInput(inputStream, "UTF-8");
                    i = pullParser7.getEventType();
                    Label_3967_Outer:
                    while (i != 1) {
                        DataConvertControlModel dataConvertControlModel2 = dataConvertControlModel;
                        List<?> list15 = list14;
                        while (true) {
                            switch (i) {
                                default: {
                                    list15 = list14;
                                    dataConvertControlModel2 = dataConvertControlModel;
                                    break Label_3967;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list15 = new ArrayList<Object>();
                                    dataConvertControlModel2 = dataConvertControlModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser7.next();
                                    dataConvertControlModel = dataConvertControlModel2;
                                    list14 = list15;
                                    continue Label_3967_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser7.getName().equals("parm")) {
                                        dataConvertControlModel2 = new DataConvertControlModel();
                                        dataConvertControlModel2.SetPowerOff(-1);
                                        list15 = list14;
                                        continue;
                                    }
                                    if (pullParser7.getName().equals("Password")) {
                                        pullParser7.next();
                                        dataConvertControlModel.SetPassword(pullParser7.getText());
                                        dataConvertControlModel2 = dataConvertControlModel;
                                        list15 = list14;
                                        continue;
                                    }
                                    if (pullParser7.getName().equals("PasswordLock")) {
                                        pullParser7.next();
                                        dataConvertControlModel.SetPswLockSwitch(Integer.parseInt(pullParser7.getText()));
                                        dataConvertControlModel2 = dataConvertControlModel;
                                        list15 = list14;
                                        continue;
                                    }
                                    if (pullParser7.getName().equals("ServiceLock")) {
                                        pullParser7.next();
                                        dataConvertControlModel.SetServiceLockSwitch(Integer.parseInt(pullParser7.getText()));
                                        dataConvertControlModel2 = dataConvertControlModel;
                                        list15 = list14;
                                        continue;
                                    }
                                    if (pullParser7.getName().equals("InstallationLock")) {
                                        pullParser7.next();
                                        dataConvertControlModel.SetInstallLockSwitch(Integer.parseInt(pullParser7.getText()));
                                        dataConvertControlModel2 = dataConvertControlModel;
                                        list15 = list14;
                                        continue;
                                    }
                                    if (pullParser7.getName().equals("EditChannelLock")) {
                                        pullParser7.next();
                                        dataConvertControlModel.SetEditChannelLockSwitch(Integer.parseInt(pullParser7.getText()));
                                        dataConvertControlModel2 = dataConvertControlModel;
                                        list15 = list14;
                                        continue;
                                    }
                                    if (pullParser7.getName().equals("SettingsLock")) {
                                        pullParser7.next();
                                        dataConvertControlModel.SetSettingsLockSwitch(Integer.parseInt(pullParser7.getText()));
                                        dataConvertControlModel2 = dataConvertControlModel;
                                        list15 = list14;
                                        continue;
                                    }
                                    if (pullParser7.getName().equals("NetworkLock")) {
                                        pullParser7.next();
                                        dataConvertControlModel.SetNetworkLockSwitch(Integer.parseInt(pullParser7.getText()));
                                        dataConvertControlModel2 = dataConvertControlModel;
                                        list15 = list14;
                                        continue;
                                    }
                                    if (pullParser7.getName().equals("AgeRating")) {
                                        pullParser7.next();
                                        dataConvertControlModel.SetAgeRatingSwitch(Integer.parseInt(pullParser7.getText()));
                                        dataConvertControlModel2 = dataConvertControlModel;
                                        list15 = list14;
                                        continue;
                                    }
                                    if (pullParser7.getName().equals("IsLockScreen")) {
                                        pullParser7.next();
                                        dataConvertControlModel.SetIsLockScreen(Integer.parseInt(pullParser7.getText()));
                                        dataConvertControlModel2 = dataConvertControlModel;
                                        list15 = list14;
                                        continue;
                                    }
                                    dataConvertControlModel2 = dataConvertControlModel;
                                    list15 = list14;
                                    if (pullParser7.getName().equals("PowerMode")) {
                                        pullParser7.next();
                                        dataConvertControlModel.SetPowerOff(Integer.parseInt(pullParser7.getText()));
                                        dataConvertControlModel2 = dataConvertControlModel;
                                        list15 = list14;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertControlModel2 = dataConvertControlModel;
                                    list15 = list14;
                                    if (pullParser7.getName().equals("parm")) {
                                        list14.add(dataConvertControlModel);
                                        dataConvertControlModel2 = null;
                                        list15 = list14;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list14;
                }
                case DATACONVERTUPDATEMODEL: {
                    List<?> list16 = null;
                    DataConvertUpdateModel dataConvertUpdateModel = null;
                    final XmlPullParser pullParser8 = Xml.newPullParser();
                    pullParser8.setInput(inputStream, "UTF-8");
                    i = pullParser8.getEventType();
                    Label_4651_Outer:
                    while (i != 1) {
                        DataConvertUpdateModel dataConvertUpdateModel2 = dataConvertUpdateModel;
                        List<?> list17 = list16;
                        while (true) {
                            switch (i) {
                                default: {
                                    list17 = list16;
                                    dataConvertUpdateModel2 = dataConvertUpdateModel;
                                    break Label_4651;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list17 = new ArrayList<Object>();
                                    dataConvertUpdateModel2 = dataConvertUpdateModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser8.next();
                                    dataConvertUpdateModel = dataConvertUpdateModel2;
                                    list16 = list17;
                                    continue Label_4651_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser8.getName().equals("parm")) {
                                        dataConvertUpdateModel2 = new DataConvertUpdateModel();
                                        list17 = list16;
                                        continue;
                                    }
                                    if (pullParser8.getName().equals("CustomerId")) {
                                        pullParser8.next();
                                        dataConvertUpdateModel.SetCustomerId(Integer.parseInt(pullParser8.getText()));
                                        dataConvertUpdateModel2 = dataConvertUpdateModel;
                                        list17 = list16;
                                        continue;
                                    }
                                    if (pullParser8.getName().equals("HardwareId")) {
                                        pullParser8.next();
                                        dataConvertUpdateModel.SetModelId(Integer.parseInt(pullParser8.getText()));
                                        dataConvertUpdateModel2 = dataConvertUpdateModel;
                                        list17 = list16;
                                        continue;
                                    }
                                    dataConvertUpdateModel2 = dataConvertUpdateModel;
                                    list17 = list16;
                                    if (pullParser8.getName().equals("VersionId")) {
                                        pullParser8.next();
                                        dataConvertUpdateModel.SetVersionId(Integer.parseInt(pullParser8.getText()));
                                        dataConvertUpdateModel2 = dataConvertUpdateModel;
                                        list17 = list16;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertUpdateModel2 = dataConvertUpdateModel;
                                    list17 = list16;
                                    if (pullParser8.getName().equals("parm")) {
                                        list16.add(dataConvertUpdateModel);
                                        dataConvertUpdateModel2 = null;
                                        list17 = list16;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list16;
                }
                case GSEPGEVENT: {
                    int n = 0;
                    int n2 = 0;
                    List<?> list18 = null;
                    Object o3 = null;
                    GsEPGEvent gsEPGEvent = null;
                    final XmlPullParser pullParser9 = Xml.newPullParser();
                    pullParser9.setInput(inputStream, "UTF-8");
                    int j = pullParser9.getEventType();
                    Label_5026_Outer:
                    while (j != 1) {
                        GsEPGEvent gsEPGEvent2 = gsEPGEvent;
                        i = n2;
                        Object o4 = o3;
                        int n3 = n;
                        List<?> list19 = list18;
                        while (true) {
                            switch (j) {
                                default: {
                                    list19 = list18;
                                    n3 = n;
                                    o4 = o3;
                                    i = n2;
                                    gsEPGEvent2 = gsEPGEvent;
                                    break Label_5026;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list19 = new ArrayList<Object>();
                                    gsEPGEvent2 = gsEPGEvent;
                                    i = n2;
                                    o4 = o3;
                                    n3 = n;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    j = pullParser9.next();
                                    gsEPGEvent = gsEPGEvent2;
                                    n2 = i;
                                    o3 = o4;
                                    n = n3;
                                    list18 = list19;
                                    continue Label_5026_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser9.getName().equals("prog_epg")) {
                                        o4 = new GsEPGTableChannel();
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("prog_no")) {
                                        pullParser9.next();
                                        ((GsEPGTableChannel)o3).setProgNo(Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("original_net_id")) {
                                        pullParser9.next();
                                        ((GsEPGTableChannel)o3).setOriginalNetworkID(Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("transport_stream_id")) {
                                        pullParser9.next();
                                        ((GsEPGTableChannel)o3).setTransportStreamID(Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("today_date")) {
                                        pullParser9.next();
                                        ((GsEPGTableChannel)o3).setTodayDate((byte)Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("current_epg_time")) {
                                        pullParser9.next();
                                        ((GsEPGTableChannel)o3).setCurrentEpgTime(Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    gsEPGEvent2 = gsEPGEvent;
                                    i = n2;
                                    o4 = o3;
                                    n3 = n;
                                    list19 = list18;
                                    if (pullParser9.getName().equals("prog_day_epg")) {
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_valid_num")) {
                                        pullParser9.next();
                                        ((GsEPGTableChannel)o3).setArrayEventFieldByIndex(n, Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("epg_event")) {
                                        gsEPGEvent2 = new GsEPGEvent();
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_start_time")) {
                                        pullParser9.next();
                                        gsEPGEvent.setStartTime(pullParser9.getText());
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_end_time")) {
                                        pullParser9.next();
                                        gsEPGEvent.setEndTime(pullParser9.getText());
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_age_rating")) {
                                        pullParser9.next();
                                        gsEPGEvent.setAgeRating(Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_timer_type")) {
                                        pullParser9.next();
                                        gsEPGEvent.setEpgTimerType(Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_total_language")) {
                                        pullParser9.next();
                                        gsEPGEvent.setTotalEpgLanguage(Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_titile_lang_code")) {
                                        pullParser9.next();
                                        gsEPGEvent.setTitleLanCode(n2, Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_sub_titile_lang_code")) {
                                        pullParser9.next();
                                        gsEPGEvent.setSubtitleLanCode(n2, Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_desc_lang_code")) {
                                        pullParser9.next();
                                        gsEPGEvent.setDescLanCode(n2, Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_titile_len")) {
                                        pullParser9.next();
                                        gsEPGEvent.setTitleLen(n2, Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_sub_titile_len")) {
                                        pullParser9.next();
                                        gsEPGEvent.setSubtitleLen(n2, Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_desc_len")) {
                                        pullParser9.next();
                                        gsEPGEvent.setDescLen(n2, Integer.parseInt(pullParser9.getText()));
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_title")) {
                                        pullParser9.next();
                                        gsEPGEvent.setEventTitle(n2, pullParser9.getText());
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("event_sub_title")) {
                                        pullParser9.next();
                                        gsEPGEvent.setEventSubTitle(n2, pullParser9.getText());
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    gsEPGEvent2 = gsEPGEvent;
                                    i = n2;
                                    o4 = o3;
                                    n3 = n;
                                    list19 = list18;
                                    if (pullParser9.getName().equals("event_desc")) {
                                        pullParser9.next();
                                        gsEPGEvent.setEventDesc(n2, pullParser9.getText());
                                        i = n2 + 1;
                                        gsEPGEvent2 = gsEPGEvent;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    if (pullParser9.getName().equals("prog_epg")) {
                                        list18.add(o3);
                                        o4 = null;
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        n3 = n;
                                        list19 = list18;
                                        continue;
                                    }
                                    if (pullParser9.getName().equals("prog_day_epg")) {
                                        n3 = n + 1;
                                        gsEPGEvent2 = gsEPGEvent;
                                        i = n2;
                                        o4 = o3;
                                        list19 = list18;
                                        continue;
                                    }
                                    gsEPGEvent2 = gsEPGEvent;
                                    i = n2;
                                    o4 = o3;
                                    n3 = n;
                                    list19 = list18;
                                    if (pullParser9.getName().equals("epg_event")) {
                                        ((GsEPGTableChannel)o3).getEpgDayByIndex(n).getArrayList().add(gsEPGEvent);
                                        i = 0;
                                        gsEPGEvent2 = null;
                                        o4 = o3;
                                        n3 = n;
                                        list19 = list18;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list18;
                }
                case DATACONVERTCHANNELMODEL_2: {
                    List<?> list20 = null;
                    DataConvertChannelModel dataConvertChannelModel3 = null;
                    final XmlPullParser pullParser10 = Xml.newPullParser();
                    pullParser10.setInput(inputStream, "UTF-8");
                    i = pullParser10.getEventType();
                    Label_6663_Outer:
                    while (i != 1) {
                        DataConvertChannelModel dataConvertChannelModel4 = dataConvertChannelModel3;
                        List<?> list21 = list20;
                        while (true) {
                            switch (i) {
                                default: {
                                    list21 = list20;
                                    dataConvertChannelModel4 = dataConvertChannelModel3;
                                    break Label_6663;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list21 = new ArrayList<Object>();
                                    dataConvertChannelModel4 = dataConvertChannelModel3;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser10.next();
                                    dataConvertChannelModel3 = dataConvertChannelModel4;
                                    list20 = list21;
                                    continue Label_6663_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser10.getName().equals("parm")) {
                                        dataConvertChannelModel4 = new DataConvertChannelModel();
                                        list21 = list20;
                                        continue;
                                    }
                                    if (pullParser10.getName().equals("LockedChannelIndex")) {
                                        pullParser10.next();
                                        dataConvertChannelModel3.SetProgramId(pullParser10.getText());
                                        dataConvertChannelModel4 = dataConvertChannelModel3;
                                        list21 = list20;
                                        continue;
                                    }
                                    dataConvertChannelModel4 = dataConvertChannelModel3;
                                    list21 = list20;
                                    if (pullParser10.getName().equals("TVState")) {
                                        pullParser10.next();
                                        dataConvertChannelModel3.setChannelTpye(Integer.parseInt(pullParser10.getText()));
                                        dataConvertChannelModel4 = dataConvertChannelModel3;
                                        list21 = list20;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertChannelModel4 = dataConvertChannelModel3;
                                    list21 = list20;
                                    if (pullParser10.getName().equals("parm")) {
                                        list20.add(dataConvertChannelModel3);
                                        dataConvertChannelModel4 = null;
                                        list21 = list20;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list20;
                }
                case DATACONVERTFAVORMODEL: {
                    List<?> list22 = null;
                    DataConvertFavorModel dataConvertFavorModel = null;
                    final XmlPullParser pullParser11 = Xml.newPullParser();
                    pullParser11.setInput(inputStream, "UTF-8");
                    i = pullParser11.getEventType();
                    Label_6951_Outer:
                    while (i != 1) {
                        List<?> list23 = list22;
                        DataConvertFavorModel dataConvertFavorModel2 = dataConvertFavorModel;
                        while (true) {
                            switch (i) {
                                default: {
                                    dataConvertFavorModel2 = dataConvertFavorModel;
                                    list23 = list22;
                                    break Label_6951;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list23 = new ArrayList<Object>();
                                    dataConvertFavorModel2 = dataConvertFavorModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser11.next();
                                    list22 = list23;
                                    dataConvertFavorModel = dataConvertFavorModel2;
                                    continue Label_6951_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser11.getName().equals("favMaxNum")) {
                                        pullParser11.next();
                                        DataConvertFavorModel.favorNum = Integer.parseInt(pullParser11.getText());
                                        list23 = list22;
                                        dataConvertFavorModel2 = dataConvertFavorModel;
                                        continue;
                                    }
                                    if (pullParser11.getName().equals("parm")) {
                                        dataConvertFavorModel2 = new DataConvertFavorModel();
                                        list23 = list22;
                                        continue;
                                    }
                                    if (pullParser11.getName().equals("favorGroupName")) {
                                        pullParser11.next();
                                        dataConvertFavorModel.SetFavorName(pullParser11.getText());
                                        list23 = list22;
                                        dataConvertFavorModel2 = dataConvertFavorModel;
                                        continue;
                                    }
                                    list23 = list22;
                                    dataConvertFavorModel2 = dataConvertFavorModel;
                                    if (pullParser11.getName().equals("FavorGroupID")) {
                                        pullParser11.next();
                                        dataConvertFavorModel.setFavorTypeID((int)Integer.valueOf(pullParser11.getText()));
                                        list23 = list22;
                                        dataConvertFavorModel2 = dataConvertFavorModel;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    list23 = list22;
                                    dataConvertFavorModel2 = dataConvertFavorModel;
                                    if (pullParser11.getName().equals("parm")) {
                                        list22.add(dataConvertFavorModel);
                                        dataConvertFavorModel2 = null;
                                        list23 = list22;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list22;
                }
                case DATACONVERTCONTROLMODEL_2: {
                    List<?> list24 = null;
                    DataConvertControlModel dataConvertControlModel3 = null;
                    final XmlPullParser pullParser12 = Xml.newPullParser();
                    pullParser12.setInput(inputStream, "UTF-8");
                    i = pullParser12.getEventType();
                    Label_7291_Outer:
                    while (i != 1) {
                        List<?> list25 = list24;
                        DataConvertControlModel dataConvertControlModel4 = dataConvertControlModel3;
                        while (true) {
                            switch (i) {
                                default: {
                                    dataConvertControlModel4 = dataConvertControlModel3;
                                    list25 = list24;
                                    break Label_7291;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list25 = new ArrayList<Object>();
                                    dataConvertControlModel4 = dataConvertControlModel3;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser12.next();
                                    list24 = list25;
                                    dataConvertControlModel3 = dataConvertControlModel4;
                                    continue Label_7291_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser12.getName().equals("parm")) {
                                        dataConvertControlModel4 = new DataConvertControlModel();
                                        list25 = list24;
                                        continue;
                                    }
                                    if (pullParser12.getName().equals("SleepSwitch")) {
                                        pullParser12.next();
                                        dataConvertControlModel3.setSleepSwitch(Integer.parseInt(pullParser12.getText()));
                                        list25 = list24;
                                        dataConvertControlModel4 = dataConvertControlModel3;
                                        continue;
                                    }
                                    if (pullParser12.getName().equals("SleepTime")) {
                                        pullParser12.next();
                                        dataConvertControlModel3.setSleepTime(Integer.parseInt(pullParser12.getText()));
                                        list25 = list24;
                                        dataConvertControlModel4 = dataConvertControlModel3;
                                        continue;
                                    }
                                    if (pullParser12.getName().equals("ScreenLock")) {
                                        pullParser12.next();
                                        dataConvertControlModel3.SetIsLockScreen(Integer.parseInt(pullParser12.getText()));
                                        list25 = list24;
                                        dataConvertControlModel4 = dataConvertControlModel3;
                                        continue;
                                    }
                                    list25 = list24;
                                    dataConvertControlModel4 = dataConvertControlModel3;
                                    if (pullParser12.getName().equals("PowerMode")) {
                                        pullParser12.next();
                                        dataConvertControlModel3.SetPowerOff(Integer.parseInt(pullParser12.getText()));
                                        list25 = list24;
                                        dataConvertControlModel4 = dataConvertControlModel3;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    list25 = list24;
                                    dataConvertControlModel4 = dataConvertControlModel3;
                                    if (pullParser12.getName().equals("parm")) {
                                        list24.add(dataConvertControlModel3);
                                        dataConvertControlModel4 = null;
                                        list25 = list24;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list24;
                }
                case DATACONVERTSTBINFOMODEL: {
                    List<?> list26 = null;
                    DataConvertStbInfoModel dataConvertStbInfoModel = null;
                    final XmlPullParser pullParser13 = Xml.newPullParser();
                    pullParser13.setInput(inputStream, "UTF-8");
                    i = pullParser13.getEventType();
                    Label_7679_Outer:
                    while (i != 1) {
                        DataConvertStbInfoModel dataConvertStbInfoModel2 = dataConvertStbInfoModel;
                        List<?> list27 = list26;
                        while (true) {
                            switch (i) {
                                default: {
                                    list27 = list26;
                                    dataConvertStbInfoModel2 = dataConvertStbInfoModel;
                                    break Label_7679;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list27 = new ArrayList<Object>();
                                    dataConvertStbInfoModel2 = dataConvertStbInfoModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser13.next();
                                    dataConvertStbInfoModel = dataConvertStbInfoModel2;
                                    list26 = list27;
                                    continue Label_7679_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser13.getName().equals("parm")) {
                                        dataConvertStbInfoModel2 = new DataConvertStbInfoModel();
                                        list27 = list26;
                                        continue;
                                    }
                                    if (pullParser13.getName().equals("StbStatus")) {
                                        pullParser13.next();
                                        dataConvertStbInfoModel.setmStbStatus(Integer.parseInt(pullParser13.getText()));
                                        dataConvertStbInfoModel2 = dataConvertStbInfoModel;
                                        list27 = list26;
                                        continue;
                                    }
                                    if (pullParser13.getName().equals("ProductName")) {
                                        pullParser13.next();
                                        dataConvertStbInfoModel.setmProductName(pullParser13.getText());
                                        dataConvertStbInfoModel2 = dataConvertStbInfoModel;
                                        list27 = list26;
                                        continue;
                                    }
                                    if (pullParser13.getName().equals("SoftwareVersion")) {
                                        pullParser13.next();
                                        dataConvertStbInfoModel.setmSoftwareVersion(pullParser13.getText());
                                        dataConvertStbInfoModel2 = dataConvertStbInfoModel;
                                        list27 = list26;
                                        continue;
                                    }
                                    if (pullParser13.getName().equals("SerialNumber")) {
                                        pullParser13.next();
                                        dataConvertStbInfoModel.setmSerialNumber(pullParser13.getText());
                                        dataConvertStbInfoModel2 = dataConvertStbInfoModel;
                                        list27 = list26;
                                        continue;
                                    }
                                    if (pullParser13.getName().equals("ChannelNum")) {
                                        pullParser13.next();
                                        dataConvertStbInfoModel.setmChannelNum(Integer.parseInt(pullParser13.getText()));
                                        dataConvertStbInfoModel2 = dataConvertStbInfoModel;
                                        list27 = list26;
                                        continue;
                                    }
                                    dataConvertStbInfoModel2 = dataConvertStbInfoModel;
                                    list27 = list26;
                                    if (pullParser13.getName().equals("MaxNumOfPrograms")) {
                                        pullParser13.next();
                                        dataConvertStbInfoModel.setmMaxNumOfPrograms(Integer.parseInt(pullParser13.getText()));
                                        dataConvertStbInfoModel2 = dataConvertStbInfoModel;
                                        list27 = list26;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertStbInfoModel2 = dataConvertStbInfoModel;
                                    list27 = list26;
                                    if (pullParser13.getName().equals("parm")) {
                                        list26.add(dataConvertStbInfoModel);
                                        dataConvertStbInfoModel2 = null;
                                        list27 = list26;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list26;
                }
                case STRING_DATACONVERTONEDATAMODEL: {
                    final XmlPullParser pullParser14 = Xml.newPullParser();
                    pullParser14.setInput(inputStream, "UTF-8");
                    List<?> list28 = null;
                    Object o5 = null;
                    i = pullParser14.getEventType();
                    Label_8159_Outer:
                    while (i != 1) {
                        Object o6 = o5;
                        List<?> list29 = list28;
                        while (true) {
                            switch (i) {
                                default: {
                                    list29 = list28;
                                    o6 = o5;
                                    break Label_8159;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list29 = new ArrayList<Object>();
                                    o6 = o5;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser14.next();
                                    o5 = o6;
                                    list28 = list29;
                                    continue Label_8159_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    o6 = o5;
                                    list29 = list28;
                                    if (pullParser14.getName().equals("Data")) {
                                        pullParser14.next();
                                        o6 = new String(pullParser14.getText());
                                        list29 = list28;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    o6 = o5;
                                    list29 = list28;
                                    if (pullParser14.getName().equals("parm")) {
                                        list28.add(o5);
                                        o6 = null;
                                        list29 = list28;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list28;
                }
                case GMSCREENGLOBALINFO_AND_CURRENT_CHANNEL_TV_RADIO_TYPE: {
                    final XmlPullParser pullParser15 = Xml.newPullParser();
                    pullParser15.setInput(inputStream, "UTF-8");
                    List<?> list30 = null;
                    Object o7 = null;
                    i = pullParser15.getEventType();
                    Label_8367_Outer:
                    while (i != 1) {
                        Object o8 = o7;
                        List<?> list31 = list30;
                        while (true) {
                            switch (i) {
                                default: {
                                    list31 = list30;
                                    o8 = o7;
                                    break Label_8367;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list31 = new ArrayList<Object>();
                                    o8 = o7;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser15.next();
                                    o7 = o8;
                                    list30 = list31;
                                    continue Label_8367_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser15.getName().equals("cur_channel_list_type")) {
                                        pullParser15.next();
                                        o8 = new String(pullParser15.getText());
                                        list31 = list30;
                                        continue;
                                    }
                                    if (pullParser15.getName().equals("max_password_num")) {
                                        pullParser15.next();
                                        GMScreenGlobalInfo.setmMaxPasswordNum(Integer.parseInt(pullParser15.getText()));
                                        o8 = o7;
                                        list31 = list30;
                                        continue;
                                    }
                                    if (pullParser15.getName().equals("cur_channel_type")) {
                                        pullParser15.next();
                                        DataConvertChannelTypeModel.setCurrent_channel_tv_radio_type(Integer.parseInt(pullParser15.getText()));
                                        o8 = o7;
                                        list31 = list30;
                                        continue;
                                    }
                                    if (pullParser15.getName().equals("is_support_pvr2ip_server")) {
                                        pullParser15.next();
                                        GMScreenGlobalInfo.setmPvr2ipServerSupport(Integer.parseInt(pullParser15.getText()));
                                        System.out.println("GMScreenGlobalInfo.getmPvr2ipServerSupport()==" + GMScreenGlobalInfo.getmPvr2ipServerSupport());
                                        o8 = o7;
                                        list31 = list30;
                                        continue;
                                    }
                                    o8 = o7;
                                    list31 = list30;
                                    if (pullParser15.getName().equals("is_sds_open")) {
                                        pullParser15.next();
                                        GMScreenGlobalInfo.setSdsOpen(Integer.parseInt(pullParser15.getText()));
                                        o8 = o7;
                                        list31 = list30;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    o8 = o7;
                                    list31 = list30;
                                    if (pullParser15.getName().equals("parm")) {
                                        list30.add(o7);
                                        o8 = null;
                                        list31 = list30;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list30;
                }
                case MAP: {
                    final XmlPullParser pullParser16 = Xml.newPullParser();
                    pullParser16.setInput(inputStream, "UTF-8");
                    List<?> list32 = null;
                    Map<String, Integer> map = null;
                    i = pullParser16.getEventType();
                    Label_8791_Outer:
                    while (i != 1) {
                        Map<String, Integer> map2 = map;
                        List<?> list33 = list32;
                        while (true) {
                            switch (i) {
                                default: {
                                    list33 = list32;
                                    map2 = map;
                                    break Label_8791;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list33 = new ArrayList<Object>();
                                    map2 = map;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser16.next();
                                    map = map2;
                                    list32 = list33;
                                    continue Label_8791_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser16.getName().equals("success")) {
                                        pullParser16.next();
                                        map2 = new HashMap<String, Integer>();
                                        map2.put("success", Integer.valueOf(pullParser16.getText()));
                                        list33 = list32;
                                        continue;
                                    }
                                    if (pullParser16.getName().equals("url")) {
                                        pullParser16.next();
                                        map.put("url", (Integer)pullParser16.getText());
                                        map2 = map;
                                        list33 = list32;
                                        continue;
                                    }
                                    map2 = map;
                                    list33 = list32;
                                    if (pullParser16.getName().equals("errormsg")) {
                                        pullParser16.next();
                                        map.put("errormsg", (Integer)pullParser16.getText());
                                        map2 = map;
                                        list33 = list32;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    map2 = map;
                                    list33 = list32;
                                    if (pullParser16.getName().equals("parm")) {
                                        list32.add(map);
                                        map2 = null;
                                        list33 = list32;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list32;
                }
                case DATACONVERTSATMODEL: {
                    final XmlPullParser pullParser17 = Xml.newPullParser();
                    pullParser17.setInput(inputStream, "UTF-8");
                    List<?> list34 = null;
                    DataConvertSatModel dataConvertSatModel = null;
                    i = pullParser17.getEventType();
                    Label_9119_Outer:
                    while (i != 1) {
                        DataConvertSatModel dataConvertSatModel2 = dataConvertSatModel;
                        List<?> list35 = list34;
                        while (true) {
                            switch (i) {
                                default: {
                                    list35 = list34;
                                    dataConvertSatModel2 = dataConvertSatModel;
                                    break Label_9119;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list35 = new ArrayList<Object>();
                                    dataConvertSatModel2 = dataConvertSatModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser17.next();
                                    dataConvertSatModel = dataConvertSatModel2;
                                    list34 = list35;
                                    continue Label_9119_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser17.getName().equals("parm")) {
                                        dataConvertSatModel2 = new DataConvertSatModel();
                                        list35 = list34;
                                        continue;
                                    }
                                    if (pullParser17.getName().equals("SatName")) {
                                        pullParser17.next();
                                        dataConvertSatModel.setmSatName(pullParser17.getText());
                                        dataConvertSatModel2 = dataConvertSatModel;
                                        list35 = list34;
                                        continue;
                                    }
                                    if (pullParser17.getName().equals("SatNo")) {
                                        pullParser17.next();
                                        dataConvertSatModel.setmSatIndex(Integer.parseInt(pullParser17.getText()));
                                        dataConvertSatModel2 = dataConvertSatModel;
                                        list35 = list34;
                                        continue;
                                    }
                                    if (pullParser17.getName().equals("SatAngle")) {
                                        pullParser17.next();
                                        dataConvertSatModel.setmSatAngle(Integer.parseInt(pullParser17.getText()));
                                        dataConvertSatModel2 = dataConvertSatModel;
                                        list35 = list34;
                                        continue;
                                    }
                                    dataConvertSatModel2 = dataConvertSatModel;
                                    list35 = list34;
                                    if (pullParser17.getName().equals("SatDir")) {
                                        pullParser17.next();
                                        dataConvertSatModel.setmSatDir(Integer.parseInt(pullParser17.getText()));
                                        dataConvertSatModel2 = dataConvertSatModel;
                                        list35 = list34;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertSatModel2 = dataConvertSatModel;
                                    list35 = list34;
                                    if (pullParser17.getName().equals("parm")) {
                                        list34.add(dataConvertSatModel);
                                        dataConvertSatModel2 = null;
                                        list35 = list34;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list34;
                }
                case DATACONVERTTPMODEL: {
                    final XmlPullParser pullParser18 = Xml.newPullParser();
                    pullParser18.setInput(inputStream, "UTF-8");
                    List<?> list36 = null;
                    DataConvertTpModel dataConvertTpModel = null;
                    i = pullParser18.getEventType();
                    Label_9503_Outer:
                    while (i != 1) {
                        DataConvertTpModel dataConvertTpModel2 = dataConvertTpModel;
                        List<?> list37 = list36;
                        while (true) {
                            switch (i) {
                                default: {
                                    list37 = list36;
                                    dataConvertTpModel2 = dataConvertTpModel;
                                    break Label_9503;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list37 = new ArrayList<Object>();
                                    dataConvertTpModel2 = dataConvertTpModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser18.next();
                                    dataConvertTpModel = dataConvertTpModel2;
                                    list36 = list37;
                                    continue Label_9503_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser18.getName().equals("parm")) {
                                        dataConvertTpModel2 = new DataConvertTpModel();
                                        list37 = list36;
                                        continue;
                                    }
                                    if (pullParser18.getName().equals("TpIndex")) {
                                        pullParser18.next();
                                        dataConvertTpModel.setTpIndex(Integer.parseInt(pullParser18.getText()));
                                        dataConvertTpModel2 = dataConvertTpModel;
                                        list37 = list36;
                                        continue;
                                    }
                                    if (pullParser18.getName().equals("SatIndex")) {
                                        pullParser18.next();
                                        dataConvertTpModel.setSatIndex(Integer.parseInt(pullParser18.getText()));
                                        dataConvertTpModel2 = dataConvertTpModel;
                                        list37 = list36;
                                        continue;
                                    }
                                    if (pullParser18.getName().equals("SystemRate")) {
                                        pullParser18.next();
                                        dataConvertTpModel.setSymRate(Integer.parseInt(pullParser18.getText()));
                                        dataConvertTpModel2 = dataConvertTpModel;
                                        list37 = list36;
                                        continue;
                                    }
                                    if (pullParser18.getName().equals("Pol")) {
                                        char pol2 = 'h';
                                        pullParser18.next();
                                        final String text2 = pullParser18.getText();
                                        if (text2.equals("0")) {
                                            pol2 = 'h';
                                        }
                                        else if (text2.equals("1")) {
                                            pol2 = 'v';
                                        }
                                        else if (text2.equals("2")) {
                                            pol2 = 'l';
                                        }
                                        else if (text2.equals("3")) {
                                            pol2 = 'r';
                                        }
                                        dataConvertTpModel.setPol(pol2);
                                        dataConvertTpModel2 = dataConvertTpModel;
                                        list37 = list36;
                                        continue;
                                    }
                                    if (pullParser18.getName().equals("Fec")) {
                                        pullParser18.next();
                                        dataConvertTpModel.setFec(Integer.parseInt(pullParser18.getText()));
                                        dataConvertTpModel2 = dataConvertTpModel;
                                        list37 = list36;
                                        continue;
                                    }
                                    dataConvertTpModel2 = dataConvertTpModel;
                                    list37 = list36;
                                    if (pullParser18.getName().equals("Freq")) {
                                        pullParser18.next();
                                        dataConvertTpModel.setFreq(Integer.parseInt(pullParser18.getText()));
                                        dataConvertTpModel2 = dataConvertTpModel;
                                        list37 = list36;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertTpModel2 = dataConvertTpModel;
                                    list37 = list36;
                                    if (pullParser18.getName().equals("parm")) {
                                        list36.add(dataConvertTpModel);
                                        dataConvertTpModel2 = null;
                                        list37 = list36;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list36;
                }
                case DATACONVERTPVRINFOMODEL: {
                    final XmlPullParser pullParser19 = Xml.newPullParser();
                    pullParser19.setInput(inputStream, "UTF-8");
                    List<?> list38 = null;
                    DataConvertPvrInfoModel dataConvertPvrInfoModel = null;
                    i = pullParser19.getEventType();
                    Label_10047_Outer:
                    while (i != 1) {
                        DataConvertPvrInfoModel dataConvertPvrInfoModel2 = dataConvertPvrInfoModel;
                        List<?> list39 = list38;
                        while (true) {
                            switch (i) {
                                default: {
                                    list39 = list38;
                                    dataConvertPvrInfoModel2 = dataConvertPvrInfoModel;
                                    break Label_10047;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list39 = new ArrayList<Object>();
                                    dataConvertPvrInfoModel2 = dataConvertPvrInfoModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser19.next();
                                    dataConvertPvrInfoModel = dataConvertPvrInfoModel2;
                                    list38 = list39;
                                    continue Label_10047_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser19.getName().equals("parm")) {
                                        dataConvertPvrInfoModel2 = new DataConvertPvrInfoModel();
                                        list39 = list38;
                                        continue;
                                    }
                                    if (pullParser19.getName().equals("pvr_name")) {
                                        pullParser19.next();
                                        dataConvertPvrInfoModel.setProgramName(pullParser19.getText());
                                        dataConvertPvrInfoModel2 = dataConvertPvrInfoModel;
                                        list39 = list38;
                                        continue;
                                    }
                                    if (pullParser19.getName().equals("pvr_uid")) {
                                        pullParser19.next();
                                        dataConvertPvrInfoModel.setmPvrId(Integer.parseInt(pullParser19.getText()));
                                        dataConvertPvrInfoModel2 = dataConvertPvrInfoModel;
                                        list39 = list38;
                                        continue;
                                    }
                                    if (pullParser19.getName().equals("pvr_duration")) {
                                        pullParser19.next();
                                        dataConvertPvrInfoModel.setmPvrDuration(pullParser19.getText());
                                        dataConvertPvrInfoModel2 = dataConvertPvrInfoModel;
                                        list39 = list38;
                                        continue;
                                    }
                                    if (pullParser19.getName().equals("Pvr_time")) {
                                        pullParser19.next();
                                        dataConvertPvrInfoModel.setmPvrTime(pullParser19.getText());
                                        dataConvertPvrInfoModel2 = dataConvertPvrInfoModel;
                                        list39 = list38;
                                        continue;
                                    }
                                    if (pullParser19.getName().equals("pvr_type")) {
                                        pullParser19.next();
                                        dataConvertPvrInfoModel.setmPvrType(Integer.parseInt(pullParser19.getText()));
                                        dataConvertPvrInfoModel2 = dataConvertPvrInfoModel;
                                        list39 = list38;
                                        continue;
                                    }
                                    dataConvertPvrInfoModel2 = dataConvertPvrInfoModel;
                                    list39 = list38;
                                    if (pullParser19.getName().equals("crypto")) {
                                        pullParser19.next();
                                        dataConvertPvrInfoModel.setmPvrCrypto(Integer.parseInt(pullParser19.getText()));
                                        dataConvertPvrInfoModel2 = dataConvertPvrInfoModel;
                                        list39 = list38;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertPvrInfoModel2 = dataConvertPvrInfoModel;
                                    list39 = list38;
                                    if (pullParser19.getName().equals("parm")) {
                                        list38.add(dataConvertPvrInfoModel);
                                        dataConvertPvrInfoModel2 = null;
                                        list39 = list38;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list38;
                }
                case GSCHATSETTINGS: {
                    final XmlPullParser pullParser20 = Xml.newPullParser();
                    pullParser20.setInput(inputStream, "UTF-8");
                    List<?> list40 = null;
                    GsChatSetting instance = null;
                    i = pullParser20.getEventType();
                    Label_10527_Outer:
                    while (i != 1) {
                        GsChatSetting gsChatSetting = instance;
                        List<?> list41 = list40;
                        while (true) {
                            switch (i) {
                                default: {
                                    list41 = list40;
                                    gsChatSetting = instance;
                                    break Label_10527;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list41 = new ArrayList<Object>();
                                    gsChatSetting = instance;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser20.next();
                                    instance = gsChatSetting;
                                    list40 = list41;
                                    continue Label_10527_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser20.getName().equals("parm")) {
                                        instance = GsChatSetting.getInstance();
                                    }
                                    if (pullParser20.getName().equals("MySN")) {
                                        pullParser20.next();
                                        instance.setSerialNumber(pullParser20.getText());
                                        gsChatSetting = instance;
                                        list41 = list40;
                                        continue;
                                    }
                                    if (pullParser20.getName().equals("MyUsername")) {
                                        pullParser20.next();
                                        instance.setUsername(pullParser20.getText());
                                        gsChatSetting = instance;
                                        list41 = list40;
                                        continue;
                                    }
                                    gsChatSetting = instance;
                                    list41 = list40;
                                    if (pullParser20.getName().equals("USERID")) {
                                        pullParser20.next();
                                        instance.setUserId(Integer.parseInt(pullParser20.getText()));
                                        gsChatSetting = instance;
                                        list41 = list40;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    gsChatSetting = instance;
                                    list41 = list40;
                                    if (pullParser20.getName().equals("parm")) {
                                        list40.add(instance);
                                        gsChatSetting = null;
                                        list41 = list40;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list40;
                }
                case GSCHATSETTINGS_2: {
                    final XmlPullParser pullParser21 = Xml.newPullParser();
                    pullParser21.setInput(inputStream, "UTF-8");
                    List<?> list42 = null;
                    GsChatSetting gsChatSetting2 = null;
                    i = pullParser21.getEventType();
                    Label_10851_Outer:
                    while (i != 1) {
                        GsChatSetting instance2 = gsChatSetting2;
                        List<?> list43 = list42;
                        while (true) {
                            switch (i) {
                                default: {
                                    list43 = list42;
                                    instance2 = gsChatSetting2;
                                    break Label_10851;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list43 = new ArrayList<Object>();
                                    instance2 = gsChatSetting2;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser21.next();
                                    gsChatSetting2 = instance2;
                                    list42 = list43;
                                    continue Label_10851_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser21.getName().equals("parm")) {
                                        instance2 = GsChatSetting.getInstance();
                                        list43 = list42;
                                        continue;
                                    }
                                    if (pullParser21.getName().equals("ShowWindow")) {
                                        pullParser21.next();
                                        gsChatSetting2.setShowWindow(Integer.parseInt(pullParser21.getText()));
                                        instance2 = gsChatSetting2;
                                        list43 = list42;
                                        continue;
                                    }
                                    if (pullParser21.getName().equals("WindowSize")) {
                                        pullParser21.next();
                                        gsChatSetting2.setWindowSize(Integer.parseInt(pullParser21.getText()));
                                        instance2 = gsChatSetting2;
                                        list43 = list42;
                                        continue;
                                    }
                                    if (pullParser21.getName().equals("WindowPosition")) {
                                        pullParser21.next();
                                        gsChatSetting2.setWindowPosition(Integer.parseInt(pullParser21.getText()));
                                        instance2 = gsChatSetting2;
                                        list43 = list42;
                                        continue;
                                    }
                                    instance2 = gsChatSetting2;
                                    list43 = list42;
                                    if (pullParser21.getName().equals("WindowTransparency")) {
                                        pullParser21.next();
                                        gsChatSetting2.setWindowTransparency(Integer.parseInt(pullParser21.getText()));
                                        instance2 = gsChatSetting2;
                                        list43 = list42;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    instance2 = gsChatSetting2;
                                    list43 = list42;
                                    if (pullParser21.getName().equals("parm")) {
                                        list42.add(gsChatSetting2);
                                        instance2 = null;
                                        list43 = list42;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list42;
                }
                case DATACONVERTCHATMSGMODEL: {
                    final XmlPullParser pullParser22 = Xml.newPullParser();
                    pullParser22.setInput(inputStream, "UTF-8");
                    List<?> list44 = null;
                    DataConvertChatMsgModel dataConvertChatMsgModel = null;
                    i = pullParser22.getEventType();
                    Label_11235_Outer:
                    while (i != 1) {
                        DataConvertChatMsgModel dataConvertChatMsgModel2 = dataConvertChatMsgModel;
                        List<?> list45 = list44;
                        while (true) {
                            switch (i) {
                                default: {
                                    list45 = list44;
                                    dataConvertChatMsgModel2 = dataConvertChatMsgModel;
                                    break Label_11235;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list45 = new ArrayList<Object>();
                                    dataConvertChatMsgModel2 = dataConvertChatMsgModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser22.next();
                                    dataConvertChatMsgModel = dataConvertChatMsgModel2;
                                    list44 = list45;
                                    continue Label_11235_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser22.getName().equals("parm")) {
                                        dataConvertChatMsgModel2 = new DataConvertChatMsgModel();
                                        list45 = list44;
                                        continue;
                                    }
                                    if (pullParser22.getName().equals("Timestamp")) {
                                        pullParser22.next();
                                        dataConvertChatMsgModel.setTimestamp(Long.parseLong(pullParser22.getText()));
                                        dataConvertChatMsgModel2 = dataConvertChatMsgModel;
                                        list45 = list44;
                                        continue;
                                    }
                                    if (pullParser22.getName().equals("USERID")) {
                                        pullParser22.next();
                                        i = Integer.parseInt(pullParser22.getText());
                                        dataConvertChatMsgModel.setUserID(i);
                                        if (i == GsChatSetting.getInstance().getUserId()) {
                                            dataConvertChatMsgModel.setMsgType(1);
                                            dataConvertChatMsgModel2 = dataConvertChatMsgModel;
                                            list45 = list44;
                                            continue;
                                        }
                                        dataConvertChatMsgModel.setMsgType(0);
                                        dataConvertChatMsgModel2 = dataConvertChatMsgModel;
                                        list45 = list44;
                                        continue;
                                    }
                                    else {
                                        if (pullParser22.getName().equals("Username")) {
                                            pullParser22.next();
                                            dataConvertChatMsgModel.setUsername(pullParser22.getText());
                                            dataConvertChatMsgModel2 = dataConvertChatMsgModel;
                                            list45 = list44;
                                            continue;
                                        }
                                        dataConvertChatMsgModel2 = dataConvertChatMsgModel;
                                        list45 = list44;
                                        if (pullParser22.getName().equals("Content")) {
                                            pullParser22.next();
                                            dataConvertChatMsgModel.setContent(pullParser22.getText());
                                            dataConvertChatMsgModel2 = dataConvertChatMsgModel;
                                            list45 = list44;
                                        }
                                        continue;
                                    }
                                    break;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertChatMsgModel2 = dataConvertChatMsgModel;
                                    list45 = list44;
                                    if (pullParser22.getName().equals("parm")) {
                                        list44.add(dataConvertChatMsgModel);
                                        dataConvertChatMsgModel2 = null;
                                        list45 = list44;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list44;
                }
                case GSCHATROOMINFO_AND_GSCHATUSER: {
                    final XmlPullParser pullParser23 = Xml.newPullParser();
                    pullParser23.setInput(inputStream, "UTF-8");
                    List<?> list46 = null;
                    Object o9 = null;
                    GsChatUser gsChatUser = null;
                    i = pullParser23.getEventType();
                    Label_11663_Outer:
                    while (i != 1) {
                        GsChatUser gsChatUser2 = gsChatUser;
                        Object o10 = o9;
                        List<?> list47 = list46;
                        while (true) {
                            switch (i) {
                                default: {
                                    list47 = list46;
                                    o10 = o9;
                                    gsChatUser2 = gsChatUser;
                                    break Label_11663;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list47 = new ArrayList<Object>();
                                    gsChatUser2 = gsChatUser;
                                    o10 = o9;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser23.next();
                                    gsChatUser = gsChatUser2;
                                    o9 = o10;
                                    list46 = list47;
                                    continue Label_11663_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser23.getName().equals("parm")) {
                                        o9 = new GsChatRoomInfo();
                                    }
                                    if (pullParser23.getName().equals("EventTitle")) {
                                        pullParser23.next();
                                        ((GsChatRoomInfo)o9).setEventTitle(pullParser23.getText());
                                        gsChatUser2 = gsChatUser;
                                        o10 = o9;
                                        list47 = list46;
                                        continue;
                                    }
                                    if (pullParser23.getName().equals("OnlineUserNum")) {
                                        pullParser23.next();
                                        ((GsChatRoomInfo)o9).setOnlineNum(Integer.parseInt(pullParser23.getText()));
                                        gsChatUser2 = gsChatUser;
                                        o10 = o9;
                                        list47 = list46;
                                        continue;
                                    }
                                    if (pullParser23.getName().equals("RoomId")) {
                                        pullParser23.next();
                                        ((GsChatRoomInfo)o9).setRoomID(Integer.parseInt(pullParser23.getText()));
                                        gsChatUser2 = gsChatUser;
                                        o10 = o9;
                                        list47 = list46;
                                        continue;
                                    }
                                    if (pullParser23.getName().equals("UserInfo")) {
                                        gsChatUser2 = new GsChatUser();
                                        o10 = o9;
                                        list47 = list46;
                                        continue;
                                    }
                                    if (pullParser23.getName().equals("USERID")) {
                                        pullParser23.next();
                                        gsChatUser.setUserID(Integer.parseInt(pullParser23.getText()));
                                        gsChatUser2 = gsChatUser;
                                        o10 = o9;
                                        list47 = list46;
                                        continue;
                                    }
                                    gsChatUser2 = gsChatUser;
                                    o10 = o9;
                                    list47 = list46;
                                    if (pullParser23.getName().equals("Username")) {
                                        pullParser23.next();
                                        gsChatUser.setUsername(pullParser23.getText());
                                        gsChatUser2 = gsChatUser;
                                        o10 = o9;
                                        list47 = list46;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    if (pullParser23.getName().equals("parm")) {
                                        list46.add(o9);
                                        o10 = null;
                                        gsChatUser2 = gsChatUser;
                                        list47 = list46;
                                        continue;
                                    }
                                    gsChatUser2 = gsChatUser;
                                    o10 = o9;
                                    list47 = list46;
                                    if (pullParser23.getName().equals("UserInfo")) {
                                        ((GsChatRoomInfo)o9).getUserList().add(gsChatUser);
                                        gsChatUser2 = null;
                                        o10 = o9;
                                        list47 = list46;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list46;
                }
                case GSCHATUSER: {
                    final XmlPullParser pullParser24 = Xml.newPullParser();
                    pullParser24.setInput(inputStream, "UTF-8");
                    List<?> list48 = null;
                    GsChatUser gsChatUser3 = null;
                    i = pullParser24.getEventType();
                    Label_12203_Outer:
                    while (i != 1) {
                        GsChatUser gsChatUser4 = gsChatUser3;
                        List<?> list49 = list48;
                        while (true) {
                            switch (i) {
                                default: {
                                    list49 = list48;
                                    gsChatUser4 = gsChatUser3;
                                    break Label_12203;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list49 = new ArrayList<Object>();
                                    gsChatUser4 = gsChatUser3;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser24.next();
                                    gsChatUser3 = gsChatUser4;
                                    list48 = list49;
                                    continue Label_12203_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser24.getName().equals("parm")) {
                                        gsChatUser4 = new GsChatUser();
                                        list49 = list48;
                                        continue;
                                    }
                                    if (pullParser24.getName().equals("USERID")) {
                                        pullParser24.next();
                                        gsChatUser3.setUserID(Integer.parseInt(pullParser24.getText()));
                                        gsChatUser4 = gsChatUser3;
                                        list49 = list48;
                                        continue;
                                    }
                                    gsChatUser4 = gsChatUser3;
                                    list49 = list48;
                                    if (pullParser24.getName().equals("Username")) {
                                        pullParser24.next();
                                        gsChatUser3.setUsername(pullParser24.getText());
                                        gsChatUser4 = gsChatUser3;
                                        list49 = list48;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    gsChatUser4 = gsChatUser3;
                                    list49 = list48;
                                    if (pullParser24.getName().equals("parm")) {
                                        list48.add(gsChatUser3);
                                        gsChatUser4 = null;
                                        list49 = list48;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list48;
                }
                case DATACONVERTGCHATCHANNELINFOMODEL: {
                    final XmlPullParser pullParser25 = Xml.newPullParser();
                    pullParser25.setInput(inputStream, "UTF-8");
                    List<?> list50 = null;
                    DataConvertGChatChannelInfoModel dataConvertGChatChannelInfoModel = null;
                    i = pullParser25.getEventType();
                    Label_12491_Outer:
                    while (i != 1) {
                        DataConvertGChatChannelInfoModel dataConvertGChatChannelInfoModel2 = dataConvertGChatChannelInfoModel;
                        List<?> list51 = list50;
                        while (true) {
                            switch (i) {
                                default: {
                                    list51 = list50;
                                    dataConvertGChatChannelInfoModel2 = dataConvertGChatChannelInfoModel;
                                    break Label_12491;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list51 = new ArrayList<Object>();
                                    dataConvertGChatChannelInfoModel2 = dataConvertGChatChannelInfoModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser25.next();
                                    dataConvertGChatChannelInfoModel = dataConvertGChatChannelInfoModel2;
                                    list50 = list51;
                                    continue Label_12491_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser25.getName().equals("parm")) {
                                        dataConvertGChatChannelInfoModel2 = new DataConvertGChatChannelInfoModel();
                                        list51 = list50;
                                        continue;
                                    }
                                    if (pullParser25.getName().equals("Angle")) {
                                        pullParser25.next();
                                        dataConvertGChatChannelInfoModel.setSatAngle(pullParser25.getText());
                                        dataConvertGChatChannelInfoModel2 = dataConvertGChatChannelInfoModel;
                                        list51 = list50;
                                        continue;
                                    }
                                    if (pullParser25.getName().equals("Tp")) {
                                        pullParser25.next();
                                        dataConvertGChatChannelInfoModel.setTp(Integer.parseInt(pullParser25.getText()));
                                        dataConvertGChatChannelInfoModel2 = dataConvertGChatChannelInfoModel;
                                        list51 = list50;
                                        continue;
                                    }
                                    if (pullParser25.getName().equals("ServiceId")) {
                                        pullParser25.next();
                                        dataConvertGChatChannelInfoModel.setServiceId(Integer.parseInt(pullParser25.getText()));
                                        dataConvertGChatChannelInfoModel2 = dataConvertGChatChannelInfoModel;
                                        list51 = list50;
                                        continue;
                                    }
                                    dataConvertGChatChannelInfoModel2 = dataConvertGChatChannelInfoModel;
                                    list51 = list50;
                                    if (pullParser25.getName().equals("EPG")) {
                                        pullParser25.next();
                                        dataConvertGChatChannelInfoModel.setEpg(pullParser25.getText());
                                        dataConvertGChatChannelInfoModel2 = dataConvertGChatChannelInfoModel;
                                        list51 = list50;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertGChatChannelInfoModel2 = dataConvertGChatChannelInfoModel;
                                    list51 = list50;
                                    if (pullParser25.getName().equals("parm")) {
                                        list50.add(dataConvertGChatChannelInfoModel);
                                        dataConvertGChatChannelInfoModel2 = null;
                                        list51 = list50;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list50;
                }
                case DATACONVERTUSERNAMEMODEL: {
                    final XmlPullParser pullParser26 = Xml.newPullParser();
                    pullParser26.setInput(inputStream, "UTF-8");
                    List<?> list52 = null;
                    DataConvertUsernameModel dataConvertUsernameModel = null;
                    i = pullParser26.getEventType();
                    Label_12875_Outer:
                    while (i != 1) {
                        DataConvertUsernameModel dataConvertUsernameModel2 = dataConvertUsernameModel;
                        List<?> list53 = list52;
                        while (true) {
                            switch (i) {
                                default: {
                                    list53 = list52;
                                    dataConvertUsernameModel2 = dataConvertUsernameModel;
                                    break Label_12875;
                                }
                                case XmlPullParser.START_DOCUMENT: {
                                    list53 = new ArrayList<Object>();
                                    dataConvertUsernameModel2 = dataConvertUsernameModel;
                                }
                                case XmlPullParser.END_DOCUMENT: {
                                    i = pullParser26.next();
                                    dataConvertUsernameModel = dataConvertUsernameModel2;
                                    list52 = list53;
                                    continue Label_12875_Outer;
                                }
                                case XmlPullParser.START_TAG: {
                                    if (pullParser26.getName().equals("parm")) {
                                        dataConvertUsernameModel2 = new DataConvertUsernameModel();
                                        list53 = list52;
                                        continue;
                                    }
                                    dataConvertUsernameModel2 = dataConvertUsernameModel;
                                    list53 = list52;
                                    if (pullParser26.getName().equals("Username")) {
                                        pullParser26.next();
                                        dataConvertUsernameModel.setUsername(pullParser26.getText());
                                        dataConvertUsernameModel2 = dataConvertUsernameModel;
                                        list53 = list52;
                                    }
                                    continue;
                                }
                                case XmlPullParser.END_TAG: {
                                    dataConvertUsernameModel2 = dataConvertUsernameModel;
                                    list53 = list52;
                                    if (pullParser26.getName().equals("parm")) {
                                        list52.add(dataConvertUsernameModel);
                                        dataConvertUsernameModel2 = null;
                                        list53 = list52;
                                    }
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                    return list52;
                }
            }
        }
        return list;
    }

    /**
     * serialize cmd's with list == NULL
     */
    // serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());

    /**
     * serialize cmd's with first list element == DataConvertChannelModel.class
     */
    public static final int CHANNEL_DATA_REQUEST = 0;
    public static final int CHANGE_CHANNEL_REQUEST = 1000;
    public static final int START_STREAM_REQUEST = 1009;
    public static final int RENAME_CHANNEL_REQUEST = 1001;
    public static final int DELETE_CHANNEL_REQUEST = 1002;
    public static final int ADD_TO_FAVO_CHANNEL_REQUEST = 1004;
    public static final int MOVE_CHANNEL_REQUEST = 1005;
    public static final int EPG_REQUEST = 5;
    public static final int UNKNOWN = 1100;
    public static final int UNKNOWN_2 = 104;

    /**
     * serialize cmd's with first list element == DataConvertFavChannelModel.class
     */
    public static final int UNKNOWN_FAV = 1011;

    /**
     * serialize cmd's with first list element == DataConvertEditChannelLockModel.class
     */
    // 1003

    /**
     * serialize cmd's with first list element == DataConvertTimeModel.class
     */
    // 1020
    // 1021
    // 1022
    // 1023

    /**
     * serialize cmd's with first list element == DataConvertControlModel.class
     */
    // 1050
    // 1051
    // 1052

    /**
     * serialize cmd's with first list element == DataConvertUpdateModel.class
     */
    // 1010

    /**
     * serialize cmd's with first list element == DataConvertDebugModel.class
     */
    // 1054

    /**
     * serialize cmd's with first list element == DataConvertRcuModel.class
     */
    // 1040

    /**
     * serialize cmd's with first list element == DataConvertFavorModel.class
     */
    // 1055

    /**
     * serialize cmd's with first list element == DataConvertChannelTypeModel.class
     */
    // 1007


    /**
     * serialize cmd's with first list element == DataConvertInputMethodModel.class
     */
    // 1059


    /**
     * serialize cmd's with first list element == DataConvertSortModel.class
     */
    // 1006


    /**
     * serialize cmd's with first list element == DataConvertOneDataModel.class
     */
    // -


    /**
     * serialize cmd's with first list element == DataConvertSatModel.class
     */
    // 1060


    /**
     * serialize cmd's with first list element == instanceof Map
     */
    // -


    /**
     * serialize cmd's with first list element == instanceof DataConvertChatMsgModel
     */
    // 1102


    /**
     * serialize cmd's with first list element == instanceof GsChatSetting
     */
    // 1104


    /**
     * serialize cmd's with first list element == instanceof GsChatUser
     */
    // 1103


    /**
     * serialize cmd's with first list element == instanceof DataConvertUsernameModel
     */
    // 1105

    public String serialize(final List<?> list, int n) throws Exception {
        final XmlSerializer serializer = Xml.newSerializer();
        final StringWriter output = new StringWriter();
        serializer.setOutput((Writer)output);
        serializer.startDocument("UTF-8", true);
        serializer.startTag("", "Command");
        Label_0082: {
            if (list == null) {
                serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
            }
            else {
                final Object value = list.get(0);
                if (value.getClass().getName() == DataConvertChannelModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    switch (n) {
                        case 0: {
                            serializer.startTag("", "parm");
                            serializer.startTag("", "FromIndex");
                            serializer.text(new StringBuilder(String.valueOf(((DataConvertChannelModel)list.get(0)).GetProgramIndex())).toString());
                            serializer.endTag("", "FromIndex");
                            serializer.startTag("", "ToIndex");
                            serializer.text(new StringBuilder(String.valueOf(((DataConvertChannelModel)list.get(1)).GetProgramIndex())).toString());
                            serializer.endTag("", "ToIndex");
                            serializer.endTag("", "parm");
                            break;
                        }
                        case 1000:
                        case 1009: {
                            for (final DataConvertChannelModel dataConvertChannelModel : list) {
                                serializer.startTag("", "parm");
                                serializer.startTag("", "TvState");
                                serializer.text(new StringBuilder(String.valueOf(dataConvertChannelModel.getChannelTpye())).toString());
                                serializer.endTag("", "TvState");
                                serializer.startTag("", "ProgramId");
                                serializer.text(dataConvertChannelModel.GetProgramId());
                                serializer.endTag("", "ProgramId");
                                switch (GMScreenGlobalInfo.getCurStbPlatform()) {
                                    case 32:
                                    case 71:
                                    case 72:
                                    case 74: {
                                        if (n == 1009) {
                                            serializer.startTag("", "iResolutionRatio");
                                            serializer.text(new StringBuilder(String.valueOf(TranscodeConstants.iCurResolution)).toString());
                                            serializer.endTag("", "iResolutionRatio");
                                            serializer.startTag("", "iBitrate");
                                            serializer.text(new StringBuilder(String.valueOf(TranscodeConstants.iCurBitrate)).toString());
                                            serializer.endTag("", "iBitrate");
                                            break;
                                        }
                                        break;
                                    }
                                }
                                serializer.endTag("", "parm");
                            }
                            break;
                        }
                        case 1001: {
                            for (final DataConvertChannelModel dataConvertChannelModel2 : list) {
                                serializer.startTag("", "parm");
                                serializer.startTag("", "TvState");
                                serializer.text(new StringBuilder(String.valueOf(dataConvertChannelModel2.getChannelTpye())).toString());
                                serializer.endTag("", "TvState");
                                serializer.startTag("", "ProgramId");
                                serializer.text(dataConvertChannelModel2.GetProgramId());
                                serializer.endTag("", "ProgramId");
                                serializer.startTag("", "ProgramName");
                                serializer.text(dataConvertChannelModel2.getProgramName());
                                serializer.endTag("", "ProgramName");
                                serializer.endTag("", "parm");
                            }
                            break;
                        }
                        case 1002: {
                            n = 0;
                            serializer.startTag("", "TvState");
                            serializer.text(new StringBuilder(String.valueOf(((DataConvertChannelModel)list.get(0)).getChannelTpye())).toString());
                            serializer.endTag("", "TvState");
                            for (final DataConvertChannelModel dataConvertChannelModel3 : list) {
                                serializer.startTag("", "parm");
                                serializer.startTag("", "ProgramId");
                                serializer.text(dataConvertChannelModel3.GetProgramId());
                                serializer.endTag("", "ProgramId");
                                ++n;
                                serializer.endTag("", "parm");
                            }
                            serializer.startTag("", "TotalNum");
                            serializer.text(new StringBuilder(String.valueOf(n)).toString());
                            serializer.endTag("", "TotalNum");
                            break;
                        }
                        case 1004: {
                            n = 0;
                            serializer.startTag("", "TvState");
                            serializer.text(new StringBuilder(String.valueOf(((DataConvertChannelModel)list.get(0)).getChannelTpye())).toString());
                            serializer.endTag("", "TvState");
                            serializer.startTag("", "FavMark");
                            serializer.text(new StringBuilder(String.valueOf(((DataConvertChannelModel)list.get(0)).GetFavMark())).toString());
                            serializer.endTag("", "FavMark");
                            String string = "";
                            final Iterator<Integer> iterator4 = ((DataConvertChannelModel)list.get(0)).mfavGroupIDs.iterator();
                            while (iterator4.hasNext()) {
                                string = String.valueOf(string) + (int)iterator4.next() + ":";
                            }
                            if (string.length() >= 0) {
                                serializer.startTag("", "FavorGroupID");
                                serializer.text(string);
                                serializer.endTag("", "FavorGroupID");
                            }
                            switch (GMScreenGlobalInfo.getCurStbPlatform()) {
                                default: {
                                    for (final DataConvertChannelModel dataConvertChannelModel4 : list) {
                                        serializer.startTag("", "ProgramId");
                                        serializer.text(dataConvertChannelModel4.GetProgramId());
                                        serializer.endTag("", "ProgramId");
                                        ++n;
                                    }
                                    serializer.startTag("", "TotalNum");
                                    serializer.text(new StringBuilder(String.valueOf(n)).toString());
                                    serializer.endTag("", "TotalNum");
                                    break Label_0082;
                                }
                                case 30:
                                case 31:
                                case 32:
                                case 71:
                                case 72:
                                case 74: {
                                    for (final DataConvertChannelModel dataConvertChannelModel5 : list) {
                                        serializer.startTag("", "ProgramId");
                                        serializer.text(dataConvertChannelModel5.GetProgramId());
                                        serializer.endTag("", "ProgramId");
                                    }
                                    break Label_0082;
                                }
                            }
                            break;
                        }
                        case 1005: {
                            int n2 = 1;
                            n = 0;
                            for (final DataConvertChannelModel dataConvertChannelModel6 : list) {
                                serializer.startTag("", "parm");
                                int n3;
                                if ((n3 = n2) != 0) {
                                    serializer.startTag("", "TvState");
                                    serializer.text(new StringBuilder(String.valueOf(dataConvertChannelModel6.getChannelTpye())).toString());
                                    serializer.endTag("", "TvState");
                                    serializer.startTag("", "MoveToPosition");
                                    serializer.text(dataConvertChannelModel6.getMoveToPosition());
                                    serializer.endTag("", "MoveToPosition");
                                    n3 = 0;
                                }
                                serializer.startTag("", "ProgramId");
                                serializer.text(new StringBuilder(String.valueOf(dataConvertChannelModel6.GetProgramId())).toString());
                                serializer.endTag("", "ProgramId");
                                ++n;
                                serializer.endTag("", "parm");
                                n2 = n3;
                            }
                            serializer.startTag("", "TotalNum");
                            serializer.text(new StringBuilder(String.valueOf(n)).toString());
                            serializer.endTag("", "TotalNum");
                            break;
                        }
                        case 5: {
                            for (final DataConvertChannelModel dataConvertChannelModel7 : list) {
                                serializer.startTag("", "parm");
                                serializer.startTag("", "ProgramId");
                                serializer.text(dataConvertChannelModel7.GetProgramId());
                                serializer.endTag("", "ProgramId");
                                serializer.endTag("", "parm");
                            }
                            break;
                        }
                        case 1100: {
                            final DataConvertChannelModel dataConvertChannelModel8 = (DataConvertChannelModel)list.get(0);
                            serializer.startTag("", "parm");
                            serializer.startTag("", "TvState");
                            serializer.text(String.valueOf(dataConvertChannelModel8.getChannelTpye()));
                            serializer.endTag("", "TvState");
                            serializer.startTag("", "ProgramId");
                            serializer.text(dataConvertChannelModel8.GetProgramId());
                            serializer.endTag("", "ProgramId");
                            serializer.endTag("", "parm");
                            break;
                        }
                        case 104: {
                            final DataConvertChannelModel dataConvertChannelModel9 = (DataConvertChannelModel)list.get(0);
                            serializer.startTag("", "parm");
                            serializer.startTag("", "ProgramId");
                            serializer.text(dataConvertChannelModel9.GetProgramId());
                            serializer.endTag("", "ProgramId");
                            serializer.endTag("", "parm");
                            break;
                        }
                    }
                }
                else if (value.getClass().getName() == DataConvertFavChannelModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1011) {
                        serializer.startTag("", "TvState");
                        serializer.text(new StringBuilder(String.valueOf(((DataConvertFavChannelModel)list.get(0)).getChannelTpye())).toString());
                        serializer.endTag("", "TvState");
                        serializer.startTag("", "SelectListType");
                        serializer.text(new StringBuilder(String.valueOf(((DataConvertFavChannelModel)list.get(0)).getSelectListType())).toString());
                        serializer.endTag("", "SelectListType");
                        for (final DataConvertFavChannelModel dataConvertFavChannelModel : list) {
                            serializer.startTag("", "parm");
                            serializer.startTag("", "ProgramId");
                            serializer.text(dataConvertFavChannelModel.GetProgramId());
                            serializer.endTag("", "ProgramId");
                            serializer.endTag("", "parm");
                        }
                    }
                }
                else if (value.getClass().getName() == DataConvertEditChannelLockModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1003) {
                        n = 0;
                        int n4 = 1;
                        serializer.startTag("", "parm");
                        for (final DataConvertEditChannelLockModel dataConvertEditChannelLockModel : list) {
                            int n5;
                            if ((n5 = n4) != 0) {
                                serializer.startTag("", "TvState");
                                serializer.text(new StringBuilder(String.valueOf(dataConvertEditChannelLockModel.getmChannelType())).toString());
                                serializer.endTag("", "TvState");
                                n5 = 0;
                            }
                            serializer.startTag("", "ProgramId");
                            serializer.text(dataConvertEditChannelLockModel.getProgramId());
                            serializer.endTag("", "ProgramId");
                            ++n;
                            n4 = n5;
                        }
                        serializer.startTag("", "TotalNum");
                        serializer.text(new StringBuilder(String.valueOf(n)).toString());
                        serializer.endTag("", "TotalNum");
                        serializer.endTag("", "parm");
                    }
                }
                else if (value.getClass().getName() == DataConvertTimeModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1021 || n == 1022 || n == 1023 || n == 1020) {
                        for (final DataConvertTimeModel dataConvertTimeModel : list) {
                            serializer.startTag("", "parm");
                            serializer.startTag("", "TimerIndex");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertTimeModel.GetTimerIndex())).toString());
                            serializer.endTag("", "TimerIndex");
                            serializer.startTag("", "TimerProgramId");
                            serializer.text(dataConvertTimeModel.getProgramId());
                            serializer.endTag("", "TimerProgramId");
                            serializer.startTag("", "TimerMonth");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertTimeModel.GetTimeMonth())).toString());
                            serializer.endTag("", "TimerMonth");
                            serializer.startTag("", "TimerDay");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertTimeModel.GetTimeDay())).toString());
                            serializer.endTag("", "TimerDay");
                            serializer.startTag("", "TimerStartHour");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertTimeModel.GetStartHour())).toString());
                            serializer.endTag("", "TimerStartHour");
                            serializer.startTag("", "TimerStartMin");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertTimeModel.GetStartMin())).toString());
                            serializer.endTag("", "TimerStartMin");
                            serializer.startTag("", "TimerEndHour");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertTimeModel.GetEndHour())).toString());
                            serializer.endTag("", "TimerEndHour");
                            serializer.startTag("", "TimerEndMin");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertTimeModel.GetEndMin())).toString());
                            serializer.endTag("", "TimerEndMin");
                            serializer.startTag("", "TimerRepeat");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertTimeModel.GetTimerRepeat())).toString());
                            serializer.endTag("", "TimerRepeat");
                            serializer.startTag("", "TimerStatus");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertTimeModel.GetTimerStatus())).toString());
                            serializer.endTag("", "TimerStatus");
                            serializer.startTag("", "TimerEventID");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertTimeModel.getEventId())).toString());
                            serializer.endTag("", "TimerEventID");
                            serializer.endTag("", "parm");
                        }
                    }
                }
                else if (value.getClass().getName() == DataConvertControlModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1051) {
                        for (final DataConvertControlModel dataConvertControlModel : list) {
                            serializer.startTag("", "parm");
                            serializer.startTag("", "PasswordLock");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertControlModel.GetPswLockSwitch())).toString());
                            serializer.endTag("", "PasswordLock");
                            serializer.startTag("", "ServiceLock");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertControlModel.GetServiceLockSwitch())).toString());
                            serializer.endTag("", "ServiceLock");
                            serializer.startTag("", "InstallLock");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertControlModel.GetInstallLockSwitch())).toString());
                            serializer.endTag("", "InstallLock");
                            serializer.startTag("", "EditLock");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertControlModel.GetEditChannelLockSwitch())).toString());
                            serializer.endTag("", "EditLock");
                            serializer.startTag("", "SettingsLock");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertControlModel.GetSettingsLockSwitch())).toString());
                            serializer.endTag("", "SettingsLock");
                            serializer.startTag("", "NetworkLock");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertControlModel.GetNetworkLockSwitch())).toString());
                            serializer.endTag("", "NetworkLock");
                            serializer.startTag("", "AgeRating");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertControlModel.GetAgeRatingSwitch())).toString());
                            serializer.endTag("", "AgeRating");
                            serializer.endTag("", "parm");
                        }
                    }
                    else if (n == 1052) {
                        serializer.startTag("", "parm");
                        serializer.startTag("", "OldPassword");
                        serializer.text(((DataConvertControlModel)list.get(0)).GetPassword());
                        serializer.endTag("", "OldPassword");
                        serializer.endTag("", "parm");
                        serializer.startTag("", "parm");
                        serializer.startTag("", "NewPassword");
                        serializer.text(((DataConvertControlModel)list.get(1)).GetPassword());
                        serializer.endTag("", "NewPassword");
                        serializer.endTag("", "parm");
                    }
                    else if (n == 1050) {
                        final DataConvertControlModel dataConvertControlModel2 = (DataConvertControlModel)list.get(0);
                        serializer.startTag("", "parm");
                        serializer.startTag("", "SleepSwitch");
                        serializer.text(new StringBuilder(String.valueOf(dataConvertControlModel2.getSleepSwitch())).toString());
                        serializer.endTag("", "SleepSwitch");
                        if (dataConvertControlModel2.getSleepSwitch() == 1) {
                            serializer.startTag("", "SleepTime");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertControlModel2.getSleepTime())).toString());
                            serializer.endTag("", "SleepTime");
                        }
                        serializer.endTag("", "parm");
                    }
                }
                else if (value.getClass().getName() == DataConvertUpdateModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1010) {
                        for (final DataConvertUpdateModel dataConvertUpdateModel : list) {
                            serializer.startTag("", "ChannelFileLen");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertUpdateModel.GetDataLen())).toString());
                            serializer.endTag("", "ChannelFileLen");
                        }
                    }
                }
                else if (value.getClass().getName() == DataConvertDebugModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1054 || n == 9) {
                        for (final DataConvertDebugModel dataConvertDebugModel : list) {
                            serializer.startTag("", "EnableDebug");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertDebugModel.getDebugValue())).toString());
                            serializer.endTag("", "EnableDebug");
                            serializer.startTag("", "RequestDataFrom");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertDebugModel.getRequestDataFrom())).toString());
                            serializer.endTag("", "RequestDataFrom");
                            serializer.startTag("", "RequestDataTo");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertDebugModel.getRequestDataTo())).toString());
                            serializer.endTag("", "RequestDataTo");
                        }
                    }
                }
                else if (value.getClass().getName() == DataConvertRcuModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1040) {
                        for (final DataConvertRcuModel dataConvertRcuModel : list) {
                            serializer.startTag("", "KeyValue");
                            serializer.text(new StringBuilder(String.valueOf(dataConvertRcuModel.getKeyValue())).toString());
                            serializer.endTag("", "KeyValue");
                        }
                    }
                }
                else if (value.getClass().getName() == DataConvertFavorModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1055) {
                        serializer.startTag("", "FavorRenamePos");
                        serializer.text(new StringBuilder(String.valueOf(((DataConvertFavorModel)list.get(0)).GetFavorIndex())).toString());
                        serializer.endTag("", "FavorRenamePos");
                        serializer.startTag("", "FavorNewName");
                        serializer.text(((DataConvertFavorModel)list.get(0)).GetFavorName());
                        serializer.endTag("", "FavorNewName");
                        serializer.startTag("", "FavorGroupID");
                        serializer.text(new StringBuilder().append(((DataConvertFavorModel)list.get(0)).getFavorTypeID()).toString());
                        serializer.endTag("", "FavorGroupID");
                    }
                }
                else if (value.getClass().getName() == DataConvertChannelTypeModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1007) {
                        serializer.startTag("", "IsFavList");
                        serializer.text(new StringBuilder(String.valueOf(((DataConvertChannelTypeModel)list.get(0)).getIsFavList())).toString());
                        serializer.endTag("", "IsFavList");
                        serializer.startTag("", "SelectListType");
                        serializer.text(new StringBuilder(String.valueOf(((DataConvertChannelTypeModel)list.get(0)).getSelectListType())).toString());
                        serializer.endTag("", "SelectListType");
                    }
                }
                else if (value.getClass().getName() == DataConvertInputMethodModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1059) {
                        serializer.startTag("", "KeyCode");
                        serializer.text(new StringBuilder(String.valueOf(((DataConvertInputMethodModel)list.get(0)).getKeyCode())).toString());
                        serializer.endTag("", "KeyCode");
                    }
                }
                else if (value.getClass().getName() == DataConvertSortModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1006) {
                        serializer.startTag("", "SortType");
                        serializer.text(new StringBuilder(String.valueOf(((DataConvertSortModel)list.get(0)).getmSortType())).toString());
                        serializer.endTag("", "SortType");
                        serializer.startTag("", "TvState");
                        serializer.text(new StringBuilder(String.valueOf(((DataConvertSortModel)list.get(0)).getmTvState())).toString());
                        serializer.endTag("", "TvState");
                    }
                }
                else if (value.getClass().getName() == DataConvertOneDataModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    serializer.startTag("", "data");
                    serializer.text(((DataConvertOneDataModel)list.get(0)).getData());
                    serializer.endTag("", "data");
                }
                else if (value.getClass().getName() == DataConvertSatModel.class.getName()) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    if (n == 1060) {
                        serializer.startTag("", "SatIndexSelected");
                        serializer.text(new StringBuilder(String.valueOf(((DataConvertSatModel)list.get(0)).getmSatIndex())).toString());
                        serializer.endTag("", "SatIndexSelected");
                    }
                }
                else if (value instanceof Map) {
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    for (final Map<String, V> map : list) {
                        for (final String s : map.keySet()) {
                            serializer.startTag("", s);
                            serializer.text((String)map.get(s));
                            serializer.endTag("", s);
                        }
                    }
                }
                else if (value instanceof DataConvertChatMsgModel) {
                    if (n == 1102) {
                        final DataConvertChatMsgModel dataConvertChatMsgModel = (DataConvertChatMsgModel)list.get(0);
                        serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                        serializer.startTag("", "parm");
                        serializer.startTag("", "Timestamp");
                        serializer.text(Long.toString(dataConvertChatMsgModel.getTimestamp()));
                        serializer.endTag("", "Timestamp");
                        serializer.startTag("", "Content");
                        serializer.text(dataConvertChatMsgModel.getContent());
                        serializer.endTag("", "Content");
                        serializer.endTag("", "parm");
                    }
                }
                else if (value instanceof GsChatSetting) {
                    if (n == 1104) {
                        final GsChatSetting gsChatSetting = (GsChatSetting)list.get(0);
                        serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                        serializer.startTag("", "parm");
                        serializer.startTag("", "ShowWindow");
                        serializer.text(String.valueOf(gsChatSetting.getSHowWindow()));
                        serializer.endTag("", "ShowWindow");
                        serializer.startTag("", "WindowSize");
                        serializer.text(String.valueOf(gsChatSetting.getWindowSize()));
                        serializer.endTag("", "WindowSize");
                        serializer.startTag("", "WindowPosition");
                        serializer.text(String.valueOf(gsChatSetting.getWindowPosition()));
                        serializer.endTag("", "WindowPosition");
                        serializer.startTag("", "WindowTransparency");
                        serializer.text(String.valueOf(gsChatSetting.getWindowTransparency()));
                        serializer.endTag("", "WindowTransparency");
                        serializer.endTag("", "parm");
                    }
                }
                else if (value instanceof GsChatUser) {
                    if (n == 1103) {
                        serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                        for (final GsChatUser gsChatUser : list) {
                            serializer.startTag("", "parm");
                            serializer.startTag("", "USERID");
                            serializer.text(Integer.toString(gsChatUser.getUserID()));
                            serializer.endTag("", "USERID");
                            serializer.startTag("", "Username");
                            serializer.text(gsChatUser.getUsername());
                            serializer.endTag("", "Username");
                            serializer.startTag("", "Action");
                            if (gsChatUser.getBlock()) {
                                serializer.text("1");
                            }
                            else {
                                serializer.text("0");
                            }
                            serializer.endTag("", "Action");
                            serializer.endTag("", "parm");
                        }
                    }
                }
                else if (value instanceof DataConvertUsernameModel && n == 1105) {
                    final DataConvertUsernameModel dataConvertUsernameModel = (DataConvertUsernameModel)list.get(0);
                    serializer.attribute("", "request", new StringBuilder(String.valueOf(n)).toString());
                    serializer.startTag("", "parm");
                    serializer.startTag("", "Username");
                    serializer.text(dataConvertUsernameModel.getUsername());
                    serializer.endTag("", "Username");
                    serializer.endTag("", "parm");
                }
            }
        }
        serializer.endTag("", "Command");
        serializer.endDocument();
        return output.toString();
    }
}
