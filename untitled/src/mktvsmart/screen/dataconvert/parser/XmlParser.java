package mktvsmart.screen.dataconvert.parser;

import android.os.Message;
import android.util.Xml;
import com.sun.deploy.xml.XMLEncoding;
import com.sun.org.apache.xerces.internal.xni.parser.XMLPullParserConfiguration;
import mktvsmart.screen.CreateSocket;
import mktvsmart.screen.GMScreenGlobalInfo;
import mktvsmart.screen.GsSendSocket;
import mktvsmart.screen.dataconvert.model.*;
import mktvsmart.screen.gchat.bean.GsChatRoomInfo;
import mktvsmart.screen.gchat.bean.GsChatSetting;
import mktvsmart.screen.gchat.bean.GsChatUser;
import org.xmlpull.v1.XmlPullParser;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XmlParser implements mktvsmart.screen.dataconvert.parser.DataParser {

    public static final int DATACONVERTSORTMODEL = 13;
    public static final int DATACONVERTCHANNELTYPPEMODEL = 12;
    public static final int DATACONVERTCHANNELMODEL = 0;
    public static final int DATACONVERTTIMEMODEL_1 = 7;
    public static final int DATACONVERTTIMEMODEL_2 = 9;
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


    public XmlParser() {
        super();
    }

    public byte[] IntToByteArray(int i) {
        byte[] a = new byte[8];
        int i0 = 0;
        while (i0 < 8) {
            int i1 = (byte) (i >> (7 - i0) * 8 & 65535);
            a[i0] = (byte) i1;
            i0 = i0 + 1;
        }
        return a;
    }

    public java.util.List parse(Message msg, int i)
    {
        byte[] aa = msg.getData().getByteArray("ReceivedData");
        java.io.InputStream a = (java.io.InputStream)new java.io.ByteArrayInputStream(aa, 0, aa.length);
        java.util.ArrayList a0 = null;
        switch(i){
            case 27: {
                org.xmlpull.v1.XmlPullParser a1 = android.util.Xml.newPullParser();
                a1.setInput(a, "UTF-8");
                int i0 = a1.getEventType();
                java.util.ArrayList a2 = null;
                mktvsmart.screen.dataconvert.model.DataConvertUsernameModel a3 = null;
                msg.obj = a3;
                Object a4 = a1;
                while(i0 != 1)
                {
                    switch(i0){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a4).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a2).add((Object)a3);
                            a3 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a4).getName().equals((Object)"parm"))
                            {
                                a3 = new mktvsmart.screen.dataconvert.model.DataConvertUsernameModel();
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a4).getName().equals((Object)"Username"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a4).next();
                                a3.setUsername(((org.xmlpull.v1.XmlPullParser)a4).getText());
                                break;
                            }
                        }
                        case 0: {
                            a2 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i0 = ((org.xmlpull.v1.XmlPullParser)a4).next();
                }
                return (java.util.List)(Object)a2;
            }
            case 26: {
                org.xmlpull.v1.XmlPullParser a5 = android.util.Xml.newPullParser();
                a5.setInput(a, "UTF-8");
                int i1 = a5.getEventType();
                java.util.ArrayList a6 = null;
                mktvsmart.screen.dataconvert.model.DataConvertGChatChannelInfoModel a7 = null;
                msg.obj = a7;
                Object a8 = a5;
                while(i1 != 1)
                {
                    switch(i1){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a6).add((Object)a7);
                            a7 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"parm"))
                            {
                                a7 = new mktvsmart.screen.dataconvert.model.DataConvertGChatChannelInfoModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"Angle"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a8).next();
                                a7.setSatAngle(((org.xmlpull.v1.XmlPullParser)a8).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"Tp"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a8).next();
                                a7.setTp(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a8).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"ServiceId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a8).next();
                                a7.setServiceId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a8).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"EPG"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a8).next();
                                a7.setEpg(((org.xmlpull.v1.XmlPullParser)a8).getText());
                                break;
                            }
                        }
                        case 0: {
                            a6 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i1 = ((org.xmlpull.v1.XmlPullParser)a8).next();
                }
                return (java.util.List)(Object)a6;
            }
            case 25: {
                org.xmlpull.v1.XmlPullParser a9 = android.util.Xml.newPullParser();
                a9.setInput(a, "UTF-8");
                int i2 = a9.getEventType();
                java.util.ArrayList a10 = null;
                mktvsmart.screen.gchat.bean.GsChatSetting a11 = null;
                msg.obj = a11;
                Object a12 = a9;
                while(i2 != 1)
                {
                    switch(i2){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a10).add((Object)a11);
                            a11 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"parm"))
                            {
                                a11 = mktvsmart.screen.gchat.bean.GsChatSetting.getInstance();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"ShowWindow"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a12).next();
                                a11.setShowWindow(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a12).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"WindowSize"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a12).next();
                                a11.setWindowSize(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a12).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"WindowPosition"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a12).next();
                                a11.setWindowPosition(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a12).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"WindowTransparency"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a12).next();
                                a11.setWindowTransparency(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a12).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a10 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i2 = ((org.xmlpull.v1.XmlPullParser)a12).next();
                }
                return (java.util.List)(Object)a10;
            }
            case 24: {
                org.xmlpull.v1.XmlPullParser a13 = android.util.Xml.newPullParser();
                a13.setInput(a, "UTF-8");
                int i3 = a13.getEventType();
                java.util.ArrayList a14 = null;
                mktvsmart.screen.gchat.bean.GsChatUser a15 = null;
                msg.obj = a15;
                Object a16 = a13;
                while(i3 != 1)
                {
                    switch(i3){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a16).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a14).add((Object)a15);
                            a15 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a16).getName().equals((Object)"parm"))
                            {
                                a15 = new mktvsmart.screen.gchat.bean.GsChatUser();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a16).getName().equals((Object)"USERID"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a16).next();
                                a15.setUserID(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a16).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a16).getName().equals((Object)"Username"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a16).next();
                                a15.setUsername(((org.xmlpull.v1.XmlPullParser)a16).getText());
                                break;
                            }
                        }
                        case 0: {
                            a14 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i3 = ((org.xmlpull.v1.XmlPullParser)a16).next();
                }
                return (java.util.List)(Object)a14;
            }
            case 23: {
                org.xmlpull.v1.XmlPullParser a17 = android.util.Xml.newPullParser();
                a17.setInput(a, "UTF-8");
                int i4 = a17.getEventType();
                java.util.ArrayList a18 = null;
                mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel a19 = null;
                msg.obj = new ArrayList<DataConvertChatMsgModel>();
                Object a20 = a17;
                while(i4 != 1)
                {
                    switch(i4){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a18).add((Object)a19);
                            a19 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"parm"))
                            {
                                a19 = new mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"Timestamp"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a20).next();
                                a19.setTimestamp(Long.parseLong(((org.xmlpull.v1.XmlPullParser)a20).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"USERID"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a20).next();
                                int i5 = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a20).getText());
                                a19.setUserID(i5);
                                if (i5 != mktvsmart.screen.gchat.bean.GsChatSetting.getInstance().getUserId())
                                {
                                    a19.setMsgType(0);
                                    break;
                                }
                                else
                                {
                                    a19.setMsgType(1);
                                    break;
                                }
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"Username"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a20).next();
                                a19.setUsername(((org.xmlpull.v1.XmlPullParser)a20).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"Content"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a20).next();
                                a19.setContent(((org.xmlpull.v1.XmlPullParser)a20).getText());
                                break;
                            }
                        }
                        case 0: {
                            a18 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i4 = ((org.xmlpull.v1.XmlPullParser)a20).next();
                }
                return (java.util.List)(Object)a18;
            }
            case 22: {
                org.xmlpull.v1.XmlPullParser a21 = android.util.Xml.newPullParser();
                a21.setInput(a, "UTF-8");
                int i6 = a21.getEventType();
                mktvsmart.screen.gchat.bean.GsChatRoomInfo a22 = null;
                java.util.ArrayList a23 = null;
                mktvsmart.screen.gchat.bean.GsChatUser a24 = null;
                List<Object> list = new ArrayList<Object>();
                list.add(new ArrayList<GsChatRoomInfo>());
                list.add(new GsChatUser());
                msg.obj = list;
                Object a25 = a21;
                while(i6 != 1)
                {
                    switch(i6){
                        case 3: {
                            if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"parm"))
                            {
                                ((java.util.List)(Object)a23).add((Object)a22);
                                a22 = null;
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"UserInfo"))
                                {
                                    break;
                                }
                                a22.getUserList().add(a24);
                                a24 = null;
                                break;
                            }
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"parm"))
                            {
                                a22 = new mktvsmart.screen.gchat.bean.GsChatRoomInfo();
                            }
                            if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"EventTitle"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a25).next();
                                a22.setEventTitle(((org.xmlpull.v1.XmlPullParser)a25).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"OnlineUserNum"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a25).next();
                                a22.setOnlineNum(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a25).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"RoomId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a25).next();
                                a22.setRoomID(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a25).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"UserInfo"))
                            {
                                a24 = new mktvsmart.screen.gchat.bean.GsChatUser();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"USERID"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a25).next();
                                a24.setUserID(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a25).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"Username"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a25).next();
                                a24.setUsername(((org.xmlpull.v1.XmlPullParser)a25).getText());
                                break;
                            }
                        }
                        case 0: {
                            a23 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i6 = ((org.xmlpull.v1.XmlPullParser)a25).next();
                }
                return (java.util.List)(Object)a23;
            }
            case 21: {
                org.xmlpull.v1.XmlPullParser a26 = android.util.Xml.newPullParser();
                a26.setInput(a, "UTF-8");
                int i7 = a26.getEventType();
                mktvsmart.screen.gchat.bean.GsChatSetting a27 = null;
                msg.obj = new GsChatSetting();
                java.util.ArrayList a28 = null;
                Object a29 = a26;
                while(i7 != 1)
                {
                    switch(i7){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a29).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a28).add((Object)a27);
                            a27 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a29).getName().equals((Object)"parm"))
                            {
                                a27 = mktvsmart.screen.gchat.bean.GsChatSetting.getInstance();
                            }
                            if (((org.xmlpull.v1.XmlPullParser)a29).getName().equals((Object)"MySN"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a29).next();
                                a27.setSerialNumber(((org.xmlpull.v1.XmlPullParser)a29).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a29).getName().equals((Object)"MyUsername"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a29).next();
                                a27.setUsername(((org.xmlpull.v1.XmlPullParser)a29).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a29).getName().equals((Object)"USERID"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a29).next();
                                a27.setUserId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a29).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a28 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i7 = ((org.xmlpull.v1.XmlPullParser)a29).next();
                }
                return (java.util.List)(Object)a28;
            }
            case 20: {
                org.xmlpull.v1.XmlPullParser a30 = android.util.Xml.newPullParser();
                a30.setInput(a, "UTF-8");
                int i8 = a30.getEventType();
                java.util.ArrayList a31 = null;
                mktvsmart.screen.dataconvert.model.DataConvertPvrInfoModel a32 = null;
                msg.obj = new ArrayList<DataConvertPvrInfoModel>();
                Object a33 = a30;
                while(i8 != 1)
                {
                    switch(i8){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a31).add((Object)a32);
                            a32 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"parm"))
                            {
                                a32 = new mktvsmart.screen.dataconvert.model.DataConvertPvrInfoModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"pvr_name"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setProgramName(((org.xmlpull.v1.XmlPullParser)a33).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"pvr_uid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setmPvrId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a33).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"pvr_duration"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setmPvrDuration(((org.xmlpull.v1.XmlPullParser)a33).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"Pvr_time"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setmPvrTime(((org.xmlpull.v1.XmlPullParser)a33).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"pvr_type"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setmPvrType(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a33).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"crypto"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setmPvrCrypto(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a33).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a31 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i8 = ((org.xmlpull.v1.XmlPullParser)a33).next();
                }
                return (java.util.List)(Object)a31;
            }
            case 19: {
                org.xmlpull.v1.XmlPullParser a34 = android.util.Xml.newPullParser();
                a34.setInput(a, "UTF-8");
                int i9 = a34.getEventType();
                java.util.ArrayList a35 = null;
                mktvsmart.screen.dataconvert.model.DataConvertTpModel a36 = null;
                msg.obj = new ArrayList<DataConvertTpModel>();
                Object a37 = a34;
                while(i9 != 1)
                {
                    switch(i9){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a35).add((Object)a36);
                            a36 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"parm"))
                            {
                                a36 = new mktvsmart.screen.dataconvert.model.DataConvertTpModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"TpIndex"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                a36.setTpIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"SatIndex"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                a36.setSatIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"SystemRate"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                a36.setSymRate(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"Pol"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                String s = ((org.xmlpull.v1.XmlPullParser)a37).getText();
                                a36.setPol((char)((s.equals((Object)"0")) ? 104 : (s.equals((Object)"1")) ? 118 : (s.equals((Object)"2")) ? 108 : (s.equals((Object)"3")) ? 114 : 104));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"Fec"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                a36.setFec(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"Freq"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                a36.setFreq(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a35 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i9 = ((org.xmlpull.v1.XmlPullParser)a37).next();
                }
                return (java.util.List)(Object)a35;
            }
            case 18: {
                org.xmlpull.v1.XmlPullParser a38 = android.util.Xml.newPullParser();
                a38.setInput(a, "UTF-8");
                int i10 = a38.getEventType();
                java.util.ArrayList a39 = null;
                mktvsmart.screen.dataconvert.model.DataConvertSatModel a40 = null;
                msg.obj = new DataConvertSatModel();
                Object a41 = a38;
                while(i10 != 1)
                {
                    switch(i10){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a39).add((Object)a40);
                            a40 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"parm"))
                            {
                                a40 = new mktvsmart.screen.dataconvert.model.DataConvertSatModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"SatName"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a41).next();
                                a40.setmSatName(((org.xmlpull.v1.XmlPullParser)a41).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"SatNo"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a41).next();
                                a40.setmSatIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a41).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"SatAngle"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a41).next();
                                a40.setmSatAngle(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a41).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"SatDir"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a41).next();
                                a40.setmSatDir(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a41).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a39 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i10 = ((org.xmlpull.v1.XmlPullParser)a41).next();
                }
                return (java.util.List)(Object)a39;
            }
            case 17: {
                org.xmlpull.v1.XmlPullParser a42 = android.util.Xml.newPullParser();
                a42.setInput(a, "UTF-8");
                int i11 = a42.getEventType();
                java.util.ArrayList a43 = null;
                List<Object> list = new ArrayList<Object>();
                list.add(new GMScreenGlobalInfo());
                list.add(new DataConvertChannelTypeModel());
                msg.obj = list;
                String s0 = null;
                Object a44 = a42;
                while(i11 != 1)
                {
                    switch(i11){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a43).add((Object)s0);
                            s0 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"cur_channel_list_type"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a44).next();
                                s0 = new String(((org.xmlpull.v1.XmlPullParser)a44).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"max_password_num"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a44).next();
                                mktvsmart.screen.GMScreenGlobalInfo.setmMaxPasswordNum(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a44).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"cur_channel_type"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a44).next();
                                mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.setCurrent_channel_tv_radio_type(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a44).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"is_support_pvr2ip_server"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a44).next();
                                mktvsmart.screen.GMScreenGlobalInfo.setmPvr2ipServerSupport(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a44).getText()));
                                System.out.println(new StringBuilder("GMScreenGlobalInfo.getmPvr2ipServerSupport()==").append(mktvsmart.screen.GMScreenGlobalInfo.getmPvr2ipServerSupport()).toString());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"is_sds_open"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a44).next();
                                mktvsmart.screen.GMScreenGlobalInfo.setSdsOpen(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a44).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a43 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i11 = ((org.xmlpull.v1.XmlPullParser)a44).next();
                }
                return (java.util.List)(Object)a43;
            }
            case 16: {
                org.xmlpull.v1.XmlPullParser a45 = android.util.Xml.newPullParser();
                a45.setInput(a, "UTF-8");
                int i12 = a45.getEventType();
                java.util.ArrayList a46 = null;
                java.util.HashMap a47 = null;
                msg.obj = a47;
                Object a48 = a45;
                while(i12 != 1)
                {
                    switch(i12){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a48).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a46).add((Object)a47);
                            a47 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a48).getName().equals((Object)"success"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a48).next();
                                a47 = new java.util.HashMap();
                                ((java.util.Map)(Object)a47).put((Object)"success", (Object)Integer.valueOf(((org.xmlpull.v1.XmlPullParser)a48).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a48).getName().equals((Object)"url"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a48).next();
                                ((java.util.Map)(Object)a47).put((Object)"url", (Object)((org.xmlpull.v1.XmlPullParser)a48).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a48).getName().equals((Object)"errormsg"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a48).next();
                                ((java.util.Map)(Object)a47).put((Object)"errormsg", (Object)((org.xmlpull.v1.XmlPullParser)a48).getText());
                                break;
                            }
                        }
                        case 0: {
                            a46 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i12 = ((org.xmlpull.v1.XmlPullParser)a48).next();
                }
                return (java.util.List)(Object)a46;
            }
            case 15: {
                org.xmlpull.v1.XmlPullParser a49 = android.util.Xml.newPullParser();
                a49.setInput(a, "UTF-8");
                int i13 = a49.getEventType();
                java.util.ArrayList a50 = null;
                msg.obj = new DataConvertOneDataModel();
                String s1 = null;
                Object a51 = a49;
                while(i13 != 1)
                {
                    switch(i13){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a51).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a50).add((Object)s1);
                            s1 = null;
                            break;
                        }
                        case 2: {
                            if (!((org.xmlpull.v1.XmlPullParser)a51).getName().equals((Object)"Data"))
                            {
                                break;
                            }
                            ((org.xmlpull.v1.XmlPullParser)a51).next();
                            s1 = new String(((org.xmlpull.v1.XmlPullParser)a51).getText());
                            break;
                        }
                        case 0: {
                            a50 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i13 = ((org.xmlpull.v1.XmlPullParser)a51).next();
                }
                return (java.util.List)(Object)a50;
            }
            case 14: {
                org.xmlpull.v1.XmlPullParser a52 = android.util.Xml.newPullParser();
                a52.setInput(a, "UTF-8");
                int i14 = a52.getEventType();
                java.util.ArrayList a53 = null;
                mktvsmart.screen.dataconvert.model.DataConvertStbInfoModel a54 = null;
                msg.obj = a54;
                Object a55 = a52;
                while(i14 != 1)
                {
                    switch(i14){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a53).add((Object)a54);
                            a54 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"parm"))
                            {
                                a54 = new mktvsmart.screen.dataconvert.model.DataConvertStbInfoModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"StbStatus"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmStbStatus(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a55).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"ProductName"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmProductName(((org.xmlpull.v1.XmlPullParser)a55).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"SoftwareVersion"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmSoftwareVersion(((org.xmlpull.v1.XmlPullParser)a55).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"SerialNumber"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmSerialNumber(((org.xmlpull.v1.XmlPullParser)a55).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"ChannelNum"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmChannelNum(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a55).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"MaxNumOfPrograms"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmMaxNumOfPrograms(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a55).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a53 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i14 = ((org.xmlpull.v1.XmlPullParser)a55).next();
                }
                return (java.util.List)(Object)a53;
            }
            case 13: {
                org.xmlpull.v1.XmlPullParser a56 = android.util.Xml.newPullParser();
                a56.setInput(a, "UTF-8");
                int i15 = a56.getEventType();
                java.util.ArrayList a57 = null;
                mktvsmart.screen.dataconvert.model.DataConvertSortModel a58 = null;
                msg.obj = a58;
                Object a59 = a56;
                while(i15 != 1)
                {
                    label3: switch(i15){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a59).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a57).add((Object)a58);
                            a58 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a59).getName().equals((Object)"parm"))
                            {
                                a58 = new mktvsmart.screen.dataconvert.model.DataConvertSortModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a59).getName().equals((Object)"SortType"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a59).next();
                                a58.setmSortType(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a59).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a59).getName().equals((Object)"MacroFlag"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a59).next();
                                a58.setmMacroFlag(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a59).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a59).getName().equals((Object)"SortTypeList"))
                                {
                                    break label3;
                                }
                                System.out.println("have SortTypeList");
                                ((org.xmlpull.v1.XmlPullParser)a59).next();
                                String[] a60 = ((org.xmlpull.v1.XmlPullParser)a59).getText().split(",");
                                if (a60 == null)
                                {
                                    break label3;
                                }
                                if (a60.length <= 0)
                                {
                                    break label3;
                                }
                                java.util.ArrayList a61 = new java.util.ArrayList();
                                int i16 = 0;
                                while(i16 < a60.length)
                                {
                                    a61.add((Object)a60[i16]);
                                    i16 = i16 + 1;
                                }
                                a58.setSortTypeList(a61);
                                break;
                            }
                        }
                        case 0: {
                            a57 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i15 = ((org.xmlpull.v1.XmlPullParser)a59).next();
                }
                return (java.util.List)(Object)a57;
            }
            case 12: {
                org.xmlpull.v1.XmlPullParser a62 = android.util.Xml.newPullParser();
                a62.setInput(a, "UTF-8");
                int i17 = a62.getEventType();
                java.util.ArrayList a63 = null;
                mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel a64 = null;
                msg.obj = a64;
                Object a65 = a62;
                while(i17 != 1)
                {
                    switch(i17){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a65).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a63).add((Object)a64);
                            a64 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a65).getName().equals((Object)"parm"))
                            {
                                a64 = new mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a65).getName().equals((Object)"CurChannelType"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a65).next();
                                mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.setCurrent_channel_tv_radio_type(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a65).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a65).getName().equals((Object)"tv_radio_key_press"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a65).next();
                                a64.setTvRadioKeyPress(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a65).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a63 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i17 = ((org.xmlpull.v1.XmlPullParser)a65).next();
                }
                return (java.util.List)(Object)a63;
            }
            case 11: {
                org.xmlpull.v1.XmlPullParser a66 = android.util.Xml.newPullParser();
                a66.setInput(a, "UTF-8");
                int i18 = a66.getEventType();
                java.util.ArrayList a67 = null;
                mktvsmart.screen.dataconvert.model.DataConvertControlModel a68 = null;
                msg.obj = a68;
                Object a69 = a66;
                while(i18 != 1)
                {
                    switch(i18){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a67).add((Object)a68);
                            a68 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"parm"))
                            {
                                a68 = new mktvsmart.screen.dataconvert.model.DataConvertControlModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"SleepSwitch"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a69).next();
                                a68.setSleepSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a69).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"SleepTime"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a69).next();
                                a68.setSleepTime(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a69).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"ScreenLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a69).next();
                                a68.SetIsLockScreen(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a69).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"PowerMode"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a69).next();
                                a68.SetPowerOff(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a69).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a67 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i18 = ((org.xmlpull.v1.XmlPullParser)a69).next();
                }
                return (java.util.List)(Object)a67;
            }
            case 10: {
                org.xmlpull.v1.XmlPullParser a70 = android.util.Xml.newPullParser();
                a70.setInput(a, "UTF-8");
                int i19 = a70.getEventType();
                java.util.ArrayList a71 = null;
                mktvsmart.screen.dataconvert.model.DataConvertFavorModel a72 = null;
                msg.obj = a72;
                Object a73 = a70;
                while(i19 != 1)
                {
                    switch(i19){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a73).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a71).add((Object)a72);
                            a72 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a73).getName().equals((Object)"favMaxNum"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a73).next();
                                mktvsmart.screen.dataconvert.model.DataConvertFavorModel.favorNum = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a73).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a73).getName().equals((Object)"parm"))
                            {
                                a72 = new mktvsmart.screen.dataconvert.model.DataConvertFavorModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a73).getName().equals((Object)"favorGroupName"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a73).next();
                                a72.SetFavorName(((org.xmlpull.v1.XmlPullParser)a73).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a73).getName().equals((Object)"FavorGroupID"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a73).next();
                                a72.setFavorTypeID(Integer.valueOf(((org.xmlpull.v1.XmlPullParser)a73).getText()).intValue());
                                break;
                            }
                        }
                        case 0: {
                            a71 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i19 = ((org.xmlpull.v1.XmlPullParser)a73).next();
                }
                return (java.util.List)(Object)a71;
            }
            case 9: {
                org.xmlpull.v1.XmlPullParser a74 = android.util.Xml.newPullParser();
                a74.setInput(a, "UTF-8");
                int i20 = a74.getEventType();
                a0 = null;
                msg.obj = new DataConvertTimeModel();
                Object a75 = a74;
                while(i20 != 1)
                {
                    switch(i20){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a75).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a0).add((Object)null);
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a75).getName().equals((Object)"StbMonth"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a75).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbMonth = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a75).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a75).getName().equals((Object)"StbDay"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a75).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbDay = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a75).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a75).getName().equals((Object)"StbHour"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a75).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbHour = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a75).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a75).getName().equals((Object)"StbMin"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a75).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbMin = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a75).getText());
                                break;
                            }
                        }
                        case 0: {
                            a0 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i20 = ((org.xmlpull.v1.XmlPullParser)a75).next();
                }
                break;
            }
            case 8: {
                org.xmlpull.v1.XmlPullParser a76 = android.util.Xml.newPullParser();
                a76.setInput(a, "UTF-8");
                int i21 = a76.getEventType();
                java.util.ArrayList a77 = null;
                mktvsmart.screen.dataconvert.model.DataConvertChannelModel a78 = null;
                msg.obj = a78;
                Object a79 = a76;
                while(i21 != 1)
                {
                    switch(i21){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a79).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a77).add((Object)a78);
                            a78 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a79).getName().equals((Object)"parm"))
                            {
                                a78 = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a79).getName().equals((Object)"LockedChannelIndex"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a79).next();
                                a78.SetProgramId(((org.xmlpull.v1.XmlPullParser)a79).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a79).getName().equals((Object)"TVState"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a79).next();
                                a78.setChannelTpye(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a79).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a77 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i21 = ((org.xmlpull.v1.XmlPullParser)a79).next();
                }
                return (java.util.List)(Object)a77;
            }
            case 7: {
                org.xmlpull.v1.XmlPullParser a80 = android.util.Xml.newPullParser();
                a80.setInput(a, "UTF-8");
                int i22 = a80.getEventType();
                a0 = null;
                mktvsmart.screen.dataconvert.model.DataConvertTimeModel a81 = null;
                msg.obj = a81;
                Object a82 = a80;
                while(i22 != 1)
                {
                    switch(i22){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a82).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a0).add((Object)a81);
                            a81 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a82).getName().equals((Object)"parm"))
                            {
                                a81 = new mktvsmart.screen.dataconvert.model.DataConvertTimeModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a82).getName().equals((Object)"Confirm"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a82).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.isConfirm = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a82).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a82).getName().equals((Object)"TimerIndex"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a82).next();
                                a81.SetTimerIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a82).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a0 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i22 = ((org.xmlpull.v1.XmlPullParser)a82).next();
                }
                break;
            }
            case 6: {
                org.xmlpull.v1.XmlPullParser a83 = android.util.Xml.newPullParser();
                a83.setInput(a, "UTF-8");
                int i23 = a83.getEventType();
                int i24 = 0;
                int i25 = 0;
                java.util.ArrayList a84 = null;
                mktvsmart.screen.GsEPGTableChannel a85 = null;
                mktvsmart.screen.GsEPGEvent a86 = null;
                List<Object> list = new ArrayList<Object>();
                list.add(a85);
                list.add(a86);
                msg.obj = list;
                Object a87 = a83;
                while(i23 != 1)
                {
                    switch(i23){
                        case 3: {
                            if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"prog_epg"))
                            {
                                ((java.util.List)(Object)a84).add((Object)a85);
                                a85 = null;
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"prog_day_epg"))
                            {
                                i24 = i24 + 1;
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"epg_event"))
                                {
                                    break;
                                }
                                a85.getEpgDayByIndex(i24).getArrayList().add(a86);
                                a86 = null;
                                i25 = 0;
                                break;
                            }
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"prog_epg"))
                            {
                                a85 = new mktvsmart.screen.GsEPGTableChannel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"prog_no"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a87).next();
                                a85.setProgNo(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"original_net_id"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a87).next();
                                a85.setOriginalNetworkID(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"transport_stream_id"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a87).next();
                                a85.setTransportStreamID(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"today_date"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a87).next();
                                int i26 = (byte)Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText());
                                a85.setTodayDate((byte)i26);
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"current_epg_time"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a87).next();
                                a85.setCurrentEpgTime(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                break;
                            }
                            else
                            {
                                if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"prog_day_epg"))
                                {
                                    break;
                                }
                                if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_valid_num"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a85.setArrayEventFieldByIndex(i24, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"epg_event"))
                                {
                                    a86 = new mktvsmart.screen.GsEPGEvent();
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_start_time"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setStartTime(((org.xmlpull.v1.XmlPullParser)a87).getText());
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_end_time"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setEndTime(((org.xmlpull.v1.XmlPullParser)a87).getText());
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_age_rating"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setAgeRating(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_timer_type"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setEpgTimerType(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_total_language"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setTotalEpgLanguage(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_titile_lang_code"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setTitleLanCode(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_sub_titile_lang_code"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setSubtitleLanCode(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_desc_lang_code"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setDescLanCode(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_titile_len"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setTitleLen(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_sub_titile_len"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setSubtitleLen(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_desc_len"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setDescLen(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_title"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setEventTitle(i25, ((org.xmlpull.v1.XmlPullParser)a87).getText());
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_sub_title"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setEventSubTitle(i25, ((org.xmlpull.v1.XmlPullParser)a87).getText());
                                    break;
                                }
                                else
                                {
                                    if (!((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_desc"))
                                    {
                                        break;
                                    }
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setEventDesc(i25, ((org.xmlpull.v1.XmlPullParser)a87).getText());
                                    i25 = i25 + 1;
                                    break;
                                }
                            }
                        }
                        case 0: {
                            a84 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i23 = ((org.xmlpull.v1.XmlPullParser)a87).next();
                }
                return (java.util.List)(Object)a84;
            }
            case 3: {
                org.xmlpull.v1.XmlPullParser a88 = android.util.Xml.newPullParser();
                a88.setInput(a, "UTF-8");
                int i27 = a88.getEventType();
                java.util.ArrayList a89 = null;
                mktvsmart.screen.dataconvert.model.DataConvertUpdateModel a90 = null;
                msg.obj = a90;
                Object a91 = a88;
                while(i27 != 1)
                {
                    switch(i27){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a91).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a89).add((Object)a90);
                            a90 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a91).getName().equals((Object)"parm"))
                            {
                                a90 = new mktvsmart.screen.dataconvert.model.DataConvertUpdateModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a91).getName().equals((Object)"CustomerId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a91).next();
                                a90.SetCustomerId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a91).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a91).getName().equals((Object)"HardwareId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a91).next();
                                a90.SetModelId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a91).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a91).getName().equals((Object)"VersionId"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a91).next();
                                a90.SetVersionId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a91).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a89 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i27 = ((org.xmlpull.v1.XmlPullParser)a91).next();
                }
                return (java.util.List)(Object)a89;
            }
            case 2: {
                org.xmlpull.v1.XmlPullParser a92 = android.util.Xml.newPullParser();
                a92.setInput(a, "UTF-8");
                int i28 = a92.getEventType();
                java.util.ArrayList a93 = null;
                mktvsmart.screen.dataconvert.model.DataConvertControlModel a94 = null;
                msg.obj = a94;
                Object a95 = a92;
                while(i28 != 1)
                {
                    switch(i28){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a93).add((Object)a94);
                            a94 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"parm"))
                            {
                                a94 = new mktvsmart.screen.dataconvert.model.DataConvertControlModel();
                                a94.SetPowerOff(-1);
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"Password"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetPassword(((org.xmlpull.v1.XmlPullParser)a95).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"PasswordLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetPswLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"ServiceLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetServiceLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"InstallationLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetInstallLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"EditChannelLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetEditChannelLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"SettingsLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetSettingsLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"NetworkLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetNetworkLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"AgeRating"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetAgeRatingSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"IsLockScreen"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetIsLockScreen(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"PowerMode"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetPowerOff(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a93 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i28 = ((org.xmlpull.v1.XmlPullParser)a95).next();
                }
                return (java.util.List)(Object)a93;
            }
            case 1: case 4: {
                org.xmlpull.v1.XmlPullParser a96 = android.util.Xml.newPullParser();
                a96.setInput(a, "UTF-8");
                int i29 = a96.getEventType();
                a0 = null;
                mktvsmart.screen.dataconvert.model.DataConvertTimeModel a97 = null;
                msg.obj = a97;
                Object a98 = a96;
                while(i29 != 1)
                {
                    switch(i29){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a0).add((Object)a97);
                            a97 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"Confirm"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.isConfirm = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"parm"))
                            {
                                a97 = new mktvsmart.screen.dataconvert.model.DataConvertTimeModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerProgramName"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetTimeProgramName(((org.xmlpull.v1.XmlPullParser)a98).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerProgramSatTpId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.setProgramId(((org.xmlpull.v1.XmlPullParser)a98).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerMonth"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetTimeMonth(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerDay"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetTimeDay(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerStartHour"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetStartHour(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerStartMin"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetStartMin(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerEndHour"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetEndHour(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerEndMin"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetEndMin(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerRepeat"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetTimerRepeat(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerStatus"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetTimerStatus(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerEventID"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.setEventId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a0 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i29 = ((org.xmlpull.v1.XmlPullParser)a98).next();
                }
                break;
            }
            case 0: {
                String currentProgramId = "";
                org.xmlpull.v1.XmlPullParser a99 = android.util.Xml.newPullParser();

                // http://destructor.de/charsets/index.htm
                // https://www.editpadlite.com/unicode.html

                // http://www.herongyang.com/Unicode/Java-charset-Supported-Character-Encodings-in-JDK.html

//                a99.setInput(a, "Named entities");      // http://www.torsten-horn.de/techdocs/encoding.htm

//                a99.setInput(a, "UTF-8");             // fail
//                a99.setInput(a, "UTF-16");            // fail
//                a99.setInput(a, "ASCII");
//                a99.setInput(a, "US-ASCII");            // fail

//                a99.setInput(a, "Windows-1250");      // works
//                a99.setInput(a, "Windows-1251");      // works
                a99.setInput(a, "Windows-1252");      // works
//                a99.setInput(a, "Windows-1253");      //
//                a99.setInput(a, "Windows-1254");      //
//                a99.setInput(a, "Windows-1255");      //
//                a99.setInput(a, "Windows-1256");      //
//                a99.setInput(a, "Windows-1257");      //
//                a99.setInput(a, "Windows-1258");      //
//                a99.setInput(a, "Windows-1259");      // fail

//                a99.setInput(a, "Windows-874");      //
//                a99.setInput(a, "Windows-932");      //
//                a99.setInput(a, "Windows-949");      //
//                a99.setInput(a, "Windows-936");      //
//                a99.setInput(a, "Windows-950");      //

//                a99.setInput(a, "ISO-8859-1");        // works
//                a99.setInput(a, "ISO-8859-2");        // works
//                a99.setInput(a, "ISO-8859-3");        // works
//                a99.setInput(a, "ISO-8859-4");        // works
//                a99.setInput(a, "ISO-8859-5");
//                a99.setInput(a, "ISO-8859-6");
//                a99.setInput(a, "ISO-8859-7");
//                a99.setInput(a, "ISO-8859-8");
//                a99.setInput(a, "ISO-8859-9");
//                a99.setInput(a, "ISO-8859-10");         // fail
//                a99.setInput(a, "ISO-8859-11");
//                a99.setInput(a, "ISO-8859-12");         // fail
//                a99.setInput(a, "ISO-8859-13");
//                a99.setInput(a, "ISO-8859-14");         // fail
//                a99.setInput(a, "ISO-8859-15");
//                a99.setInput(a, "ISO-8859-16");         // fail



                int i30 = a99.getEventType();
                java.util.ArrayList a100 = null;
                mktvsmart.screen.dataconvert.model.DataConvertChannelModel a101 = null;
                msg.obj = a101;
                Object a102 = a99;
                while(i30 != 1)
                {
                    label0: switch(i30){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            int i31 = mktvsmart.screen.GMScreenGlobalInfo.isSdsOpen();
                            label1: {
                                label2: {
                                    if (i31 == 0)
                                    {
                                        break label2;
                                    }
                                    int i32 = a101.getIsTuner2();
                                    if (i32 != 0)
                                    {
                                        break label1;
                                    }
                                }
                                ((java.util.List)(Object)a100).add((Object)a101);
                            }
                            a101 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"parm"))
                            {
                                a101 = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SatIndexSelected"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                mktvsmart.screen.GMScreenGlobalInfo.setmSatIndexSelected(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetProgramId(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                currentProgramId = a101.GetProgramId();
                                //System.out.println(a101.GetProgramId());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramName"))
                            {
//                                if(currentProgramId.equals("00030025030121") ) {
//                                    XMLStreamReader xmlPullParser;
//                                    xmlPullParser.enc
//                                    a99.next();
//                                }
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setProgramName(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramIndex"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetProgramIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SatName"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetSatName(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramType"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetIsProgramScramble(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"IsProgramHD"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setIsProgramHd(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"FavMark"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetFavMark(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"LockMark"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setLockMark(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"HaveEPG"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetHaveEPG(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"IsPlaying"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setIsPlaying(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"WillBePlayed"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setmWillBePlayed(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ChannelType"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setChannelTpye(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"Frequency"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setFreq(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"Polar"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                String s2 = ((org.xmlpull.v1.XmlPullParser)a102).getText();
                                a101.setPol((char)((s2.equals((Object)"0")) ? 104 : (s2.equals((Object)"1")) ? 118 : (s2.equals((Object)"2")) ? 108 : (s2.equals((Object)"3")) ? 114 : 104));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ModulationSystem"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setModulationSystem(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ModulationType"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setModulationType(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"RollOff"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setRollOff(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"PilotTones"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setPilotTones(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SymbolRate"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setSymRate(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"Fec"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setFec(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"VideoPid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setVideoPid(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"AudioPid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setAudioPid(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"TtxPid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setTtxPid(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SubPid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setSubPid(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"PmtPid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setPmtPid(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"IsTuner2"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                int i33 = (short)Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                a101.setIsTuner2((short)i33);
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"FavorGroupID"))
                                {
                                    break label0;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                String[] a103 = ((org.xmlpull.v1.XmlPullParser)a102).getText().split(":");
                                if (a103 == null)
                                {
                                    break label0;
                                }
                                int i34 = 0;
                                while(i34 < a103.length)
                                {
                                    a101.mfavGroupIDs.add((Object)Integer.valueOf(Integer.parseInt(a103[i34])));
                                    i34 = i34 + 1;
                                }
                                break;
                            }
                        }
                        case 0: {
                            a100 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i30 = ((org.xmlpull.v1.XmlPullParser)a102).next();
                }
                return (java.util.List)(Object)a100;
            }
            default: {
                a0 = null;
            }
        }
        return (java.util.List)(Object)a0;
    }


    public java.util.List parse2(java.io.InputStream a, int i) throws XMLStreamException {
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

        // http://openbook.rheinwerk-verlag.de/java7/1507_07_005.html#dodtp06853491-538f-41e2-a3a9-783fc902e115
        // http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_18_005.htm#mja0bb01e44ea2953efb971874596be219
        // Android: https://developer.android.com/reference/org/xmlpull/v1/XmlPullParser.html


        switch (i) {
            case 0: {
                /*****Channel List******/
                return ParseChannelList(a, i);
            }
            case 3: {

                return ParseUpdateModel(a, i);
            }

            case 18: {
                /*****Sat List******/
                return ParseSatList(a, i);
            }
            case 19: {
                /*****Transponder List******/
                return ParseTransponderList(a, i);
            }
            case 2001: {

                try {
                    Socket tcpSocket = new CreateSocket(null, 0).GetSocket();
                    GsSendSocket.sendOnlyCommandSocketToStb(tcpSocket, 3);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

    private List ParseUpdateModel(InputStream a, int i) {
        return ParseUpdateModelKrakatau(a, i);
    }

    private List ParseUpdateModelKrakatau(InputStream a, int i) {

        org.xmlpull.v1.XmlPullParser a88 = android.util.Xml.newPullParser();
        a88.setInput(a, "UTF-8");
        int i27 = a88.getEventType();
        java.util.ArrayList a89 = null;
        mktvsmart.screen.dataconvert.model.DataConvertUpdateModel a90 = null;
        Object a91 = a88;
        while (i27 != 1) {
            switch (i27) {
                case 3: {
                    if (!((org.xmlpull.v1.XmlPullParser) a91).getName().equals((Object) "parm")) {
                        break;
                    }
                    ((java.util.List) (Object) a89).add((Object) a90);
                    a90 = null;
                    break;
                }
                case 2: {
                    if (((org.xmlpull.v1.XmlPullParser) a91).getName().equals((Object) "parm")) {
                        a90 = new mktvsmart.screen.dataconvert.model.DataConvertUpdateModel();
                        break;
                    } else if (((org.xmlpull.v1.XmlPullParser) a91).getName().equals((Object) "CustomerId")) {
                        ((org.xmlpull.v1.XmlPullParser) a91).next();
                        a90.SetCustomerId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser) a91).getText()));
                        break;
                    } else if (((org.xmlpull.v1.XmlPullParser) a91).getName().equals((Object) "HardwareId")) {
                        ((org.xmlpull.v1.XmlPullParser) a91).next();
                        a90.SetModelId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser) a91).getText()));
                        break;
                    } else {
                        if (!((org.xmlpull.v1.XmlPullParser) a91).getName().equals((Object) "VersionId")) {
                            break;
                        }
                        ((org.xmlpull.v1.XmlPullParser) a91).next();
                        a90.SetVersionId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser) a91).getText()));
                        break;
                    }
                }
                case 0: {
                    a89 = new java.util.ArrayList();
                    break;
                }
                default: {
                    break;
                }
                case 1: {
                }
            }
            i27 = ((org.xmlpull.v1.XmlPullParser) a91).next();
        }
        return (java.util.List) (Object) a89;
    }


    private List ParseSatList(InputStream a, int i) {
        return ParseSatListKrakatau(a, i);
    }

    private List ParseSatListKrakatau(InputStream a, int i) {
        org.xmlpull.v1.XmlPullParser a38 = android.util.Xml.newPullParser();
        a38.setInput(a, "UTF-8");
        int i10 = a38.getEventType();
        java.util.ArrayList a39 = null;
        mktvsmart.screen.dataconvert.model.DataConvertSatModel a40 = null;
        Object a41 = a38;
        while (i10 != 1) {
            switch (i10) {
                case 3: {
                    if (!((org.xmlpull.v1.XmlPullParser) a41).getName().equals((Object) "parm")) {
                        break;
                    }
                    ((java.util.List) (Object) a39).add((Object) a40);
                    a40 = null;
                    break;
                }
                case 2: {
                    if (((org.xmlpull.v1.XmlPullParser) a41).getName().equals((Object) "parm")) {
                        a40 = new mktvsmart.screen.dataconvert.model.DataConvertSatModel();
                        break;
                    } else if (((org.xmlpull.v1.XmlPullParser) a41).getName().equals((Object) "SatName")) {
                        ((org.xmlpull.v1.XmlPullParser) a41).next();
                        a40.setmSatName(((org.xmlpull.v1.XmlPullParser) a41).getText());
                        break;
                    } else if (((org.xmlpull.v1.XmlPullParser) a41).getName().equals((Object) "SatNo")) {
                        ((org.xmlpull.v1.XmlPullParser) a41).next();
                        a40.setmSatIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser) a41).getText()));
                        break;
                    } else if (((org.xmlpull.v1.XmlPullParser) a41).getName().equals((Object) "SatAngle")) {
                        ((org.xmlpull.v1.XmlPullParser) a41).next();
                        a40.setmSatAngle(Integer.parseInt(((org.xmlpull.v1.XmlPullParser) a41).getText()));
                        break;
                    } else {
                        if (!((org.xmlpull.v1.XmlPullParser) a41).getName().equals((Object) "SatDir")) {
                            break;
                        }
                        ((org.xmlpull.v1.XmlPullParser) a41).next();
                        a40.setmSatDir(Integer.parseInt(((org.xmlpull.v1.XmlPullParser) a41).getText()));
                        break;
                    }
                }
                case 0: {
                    a39 = new java.util.ArrayList();
                    break;
                }
                default: {
                    break;
                }
                case 1: {
                }
            }
            i10 = ((org.xmlpull.v1.XmlPullParser) a41).next();
        }
        return (java.util.List) (Object) a39;
    }

    private List ParseSatList2(InputStream a, int i) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader xmlPullParser = factory.createXMLEventReader(a, "UTF-8");

        java.util.ArrayList a100 = null;
        DataConvertSatModel currentSatModel = null;

        int satCounter = 0;
        boolean isSat = false;

        while (xmlPullParser.hasNext()) {
            XMLEvent event = xmlPullParser.nextEvent();

            switch (event.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT:
                    // Begin der Satliste im XML Document Format
                    a100 = new java.util.ArrayList();
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    xmlPullParser.close();
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    StartElement element = event.asStartElement();
                    if (isSat) {
                        System.out.print("Channel " + satCounter + ": ");
                    } else {
                        System.out.println("START_ELEMENT " + element.getName());
                    }


                    if (element.getName().toString().equals("parm")) {
                        System.out.print(" begin parsing\n");
                        isSat = true;
                        satCounter++;
                        currentSatModel = new mktvsmart.screen.dataconvert.model.DataConvertSatModel();
                        break;
                    } else if (element.getName().toString().equals((Object) "SatName")) {
                        event = xmlPullParser.nextEvent();
                        currentSatModel.setmSatName(event.asCharacters().getData());
                        System.out.print(" SatName = " + event.asCharacters().getData() + "\n");
                        break;
                    } else if (element.getName().toString().equals((Object) "SatNo")) {
                        event = xmlPullParser.nextEvent();
                        currentSatModel.setmSatIndex(Integer.parseInt(event.asCharacters().getData()));
                        System.out.print(" SatNo = " + event.asCharacters().getData() + "\n");
                        break;
                    } else if (element.getName().toString().equals((Object) "SatAngle")) {
                        event = xmlPullParser.nextEvent();
                        currentSatModel.setmSatAngle(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "SatDir")) {
                        event = xmlPullParser.nextEvent();
                        currentSatModel.setmSatDir(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else
                        break;
                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    if (!characters.isWhiteSpace()) {

                    }
                    System.out.println("EVENT CHARACTERS: should not happen!!! Data=" + characters.getData());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();

                    // Ende von Channeldaten, speichere Channel in Liste
                    if (endElement.getName().toString().equals("parm")) {
                        System.out.println("Sat " + satCounter + "stop parsing");
                        isSat = false;
                        a100.add(currentSatModel);
                    }

                    break;
                case XMLStreamConstants.ATTRIBUTE:
                    System.out.println("EVENT ATTRIBUTE: should not happen!!!");

                    break;

                default:
                    break;
            }
        }

        return a100;
    }

    private List ParseTransponderList(InputStream a, int i) throws XMLStreamException {
        return ParseTransponderListKrakatau(a, i);
    }

    private List ParseTransponderListKrakatau(InputStream a, int i) throws XMLStreamException {
        org.xmlpull.v1.XmlPullParser a34 = android.util.Xml.newPullParser();
        a34.setInput(a, "UTF-8");
        int i9 = a34.getEventType();
        java.util.ArrayList a35 = null;
        mktvsmart.screen.dataconvert.model.DataConvertTpModel a36 = null;
        Object a37 = a34;
        while(i9 != 1)
        {
            switch(i9){
                case 3: {
                    if (!((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"parm"))
                    {
                        break;
                    }
                    ((java.util.List)(Object)a35).add((Object)a36);
                    a36 = null;
                    break;
                }
                case 2: {
                    if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"parm"))
                    {
                        a36 = new mktvsmart.screen.dataconvert.model.DataConvertTpModel();
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"TpIndex"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a37).next();
                        a36.setTpIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"SatIndex"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a37).next();
                        a36.setSatIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"SystemRate"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a37).next();
                        a36.setSymRate(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"Pol"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a37).next();
                        String s = ((org.xmlpull.v1.XmlPullParser)a37).getText();
                        a36.setPol((char)((s.equals((Object)"0")) ? 104 : (s.equals((Object)"1")) ? 118 : (s.equals((Object)"2")) ? 108 : (s.equals((Object)"3")) ? 114 : 104));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"Fec"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a37).next();
                        a36.setFec(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                        break;
                    }
                    else
                    {
                        if (!((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"Freq"))
                        {
                            break;
                        }
                        ((org.xmlpull.v1.XmlPullParser)a37).next();
                        a36.setFreq(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                        break;
                    }
                }
                case 0: {
                    a35 = new java.util.ArrayList();
                    break;
                }
                default: {
                    break;
                }
                case 1: {
                }
            }
            i9 = ((org.xmlpull.v1.XmlPullParser)a37).next();
        }
        return (java.util.List)(Object)a35;

    }

    private List ParseTransponderList2(InputStream a, int i) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader xmlPullParser = factory.createXMLEventReader(a, "UTF-8");

        java.util.ArrayList a100 = null;
        DataConvertTpModel currentTpModel = null;

        int tpCounter = 0;
        boolean isTp = false;

        while (xmlPullParser.hasNext()) {
            XMLEvent event = xmlPullParser.nextEvent();

            switch (event.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT:
                    // Begin der Satliste im XML Document Format
                    a100 = new java.util.ArrayList();
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    xmlPullParser.close();
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    StartElement element = event.asStartElement();
                    if(isTp) {
                        System.out.print("Channel " + tpCounter + ": ");
                    }
                    else {
                        System.out.println("START_ELEMENT " + element.getName());
                    }


                    if (element.getName().toString().equals("parm")) {
                        System.out.print(" begin parsing\n");
                        isTp = true;
                        tpCounter++;
                        currentTpModel = new mktvsmart.screen.dataconvert.model.DataConvertTpModel();
                        break;
                    } else if (element.getName().toString().equals((Object) "TpIndex")) {
                        event = xmlPullParser.nextEvent();
                        currentTpModel.setTpIndex(Integer.parseInt(event.asCharacters().getData()));
                        System.out.print(" TpIndex = " + event.asCharacters().getData() + "\n");
                        break;
                    } else if (element.getName().toString().equals((Object) "SatIndex")) {
                        event = xmlPullParser.nextEvent();
                        currentTpModel.setSatIndex(Integer.parseInt(event.asCharacters().getData()));
                        System.out.print(" SatIndex = " + event.asCharacters().getData() + "\n");
                        break;
                    } else if (element.getName().toString().equals((Object) "SystemRate")) {
                        event = xmlPullParser.nextEvent();
                        currentTpModel.setSymRate(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "Pol")) {
                        event = xmlPullParser.nextEvent();
                        String s = event.asCharacters().getData().toString();
                        currentTpModel.setPol((char)((s.equals((Object)"0")) ? 104 : (s.equals((Object)"1")) ? 118 : (s.equals((Object)"2")) ? 108 : (s.equals((Object)"3")) ? 114 : 104));
                        System.out.print(" Pol = " + event.asCharacters().getData() + " == " + currentTpModel.getPol()  + "\n");

                        break;
                    } else if (element.getName().toString().equals((Object) "Fec")) {
                        event = xmlPullParser.nextEvent();
                        currentTpModel.setFec(Integer.parseInt(event.asCharacters().getData()));
                        System.out.print(" SatIndex = " + event.asCharacters().getData() + "\n");
                        break;
                    } else if (element.getName().toString().equals((Object) "Freq")) {
                        event = xmlPullParser.nextEvent();
                        currentTpModel.setFreq(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else
                        break;
                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    if (!characters.isWhiteSpace()) {

                    }
                    System.out.println("EVENT CHARACTERS: should not happen!!! Data=" + characters.getData());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();

                    // Ende von Channeldaten, speichere Channel in Liste
                    if (endElement.getName().toString().equals("parm")) {
                        System.out.println("Sat " + tpCounter + "stop parsing");
                        isTp = false;
                        a100.add(currentTpModel);
                    }

                    break;
                case XMLStreamConstants.ATTRIBUTE:
                    System.out.println("EVENT ATTRIBUTE: should not happen!!!");

                    break;

                default:
                    break;
            }
        }

        return a100;
    }

    public java.util.List ParseChannelList(java.io.InputStream inputStream, int i) throws XMLStreamException {
        return ParseChannelListKrakatau(inputStream, i);
    }

    public java.util.List ParseChannelListKrakatau(java.io.InputStream a, int i) throws XMLStreamException {

        org.xmlpull.v1.XmlPullParser a99 = android.util.Xml.newPullParser();
        a99.setInput(a, "UTF-8");
        int i30 = a99.getEventType();
        java.util.ArrayList a100 = null;
        mktvsmart.screen.dataconvert.model.DataConvertChannelModel a101 = null;
        Object a102 = a99;
        while(i30 != 1)
        {
            label0: switch(i30){
                case 3: {
                    if (!((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"parm"))
                    {
                        break;
                    }
                    int i31 = mktvsmart.screen.GMScreenGlobalInfo.isSdsOpen();
                    label1: {
                        label2: {
                            if (i31 == 0)
                            {
                                break label2;
                            }
                            int i32 = a101.getIsTuner2();
                            if (i32 != 0)
                            {
                                break label1;
                            }
                        }
                        ((java.util.List)(Object)a100).add((Object)a101);
                    }
                    a101 = null;
                    break;
                }
                case 2: {
                    if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"parm"))
                    {
                        a101 = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SatIndexSelected"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        mktvsmart.screen.GMScreenGlobalInfo.setmSatIndexSelected(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramId"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.SetProgramId(((org.xmlpull.v1.XmlPullParser)a102).getText());
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramName"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setProgramName(((org.xmlpull.v1.XmlPullParser)a102).getText());
                        //System.out.println(this.toString() + " ---- " + ((org.xmlpull.v1.XmlPullParser)a102).getText());
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramIndex"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.SetProgramIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SatName"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.SetSatName(((org.xmlpull.v1.XmlPullParser)a102).getText());
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramType"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.SetIsProgramScramble(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"IsProgramHD"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setIsProgramHd(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"FavMark"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.SetFavMark(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"LockMark"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setLockMark(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"HaveEPG"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.SetHaveEPG(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"IsPlaying"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setIsPlaying(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"WillBePlayed"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setmWillBePlayed(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ChannelType"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setChannelTpye(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"Frequency"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setFreq(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"Polar"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        String s2 = ((org.xmlpull.v1.XmlPullParser)a102).getText();
                        a101.setPol((char)((s2.equals((Object)"0")) ? 104 : (s2.equals((Object)"1")) ? 118 : (s2.equals((Object)"2")) ? 108 : (s2.equals((Object)"3")) ? 114 : 104));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ModulationSystem"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setModulationSystem(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ModulationType"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setModulationType(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"RollOff"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setRollOff(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"PilotTones"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setPilotTones(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SymbolRate"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setSymRate(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"Fec"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setFec(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"VideoPid"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setVideoPid(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"AudioPid"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setAudioPid(((org.xmlpull.v1.XmlPullParser)a102).getText());
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"TtxPid"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setTtxPid(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SubPid"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setSubPid(((org.xmlpull.v1.XmlPullParser)a102).getText());
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"PmtPid"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        a101.setPmtPid(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                        break;
                    }
                    else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"IsTuner2"))
                    {
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        int i33 = (short)Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText());
                        a101.setIsTuner2((short)i33);
                        break;
                    }
                    else
                    {
                        if (!((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"FavorGroupID"))
                        {
                            break label0;
                        }
                        ((org.xmlpull.v1.XmlPullParser)a102).next();
                        String[] a103 = ((org.xmlpull.v1.XmlPullParser)a102).getText().split(":");
                        if (a103 == null)
                        {
                            break label0;
                        }
                        int i34 = 0;
                        while(i34 < a103.length)
                        {
                            a101.mfavGroupIDs.add((Object)Integer.valueOf(Integer.parseInt(a103[i34])));
                            i34 = i34 + 1;
                        }
                        break;
                    }
                }
                case 0: {
                    a100 = new java.util.ArrayList();
                    break;
                }
                default: {
                    break;
                }
                case 1: {
                }
            }
            i30 = ((org.xmlpull.v1.XmlPullParser)a102).next();
        }
        return (java.util.List)(Object)a100;

    }

    public java.util.List ParseChannelListProcyon(java.io.InputStream inputStream, int i) throws XMLStreamException {

        System.out.println("Start: ParseChannelList");
        /* ChannelList */

        List list6 = null;
        DataConvertChannelModel dataConvertChannelModel = null;
        final XmlPullParser pullParser3 = Xml.newPullParser();
        pullParser3.setInput(inputStream, "UTF-8");
        i = pullParser3.getEventType();
        Label_0911_Outer:
        while (i != 1) {
            DataConvertChannelModel dataConvertChannelModel2 = dataConvertChannelModel;
            List list7 = list6;
            Label_0911:
            while (true) {
                switch (i) {
                    default: {
                        System.out.println("i==" + i + " -> default");
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
                        //break;
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
                //break;
            }
        }
        System.out.println("DONE: ParseChannelList");
        return list6;
    }

    public java.util.List ParseChannelList2(java.io.InputStream a, int i) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader xmlPullParser = factory.createXMLEventReader(a, "UTF-8");

        //XmlPullParser xmlPullParser = android.util.Xml.newPullParser();
        //xmlPullParser.setInput(a, "UTF-8");
        //XMLEvent event = xmlPullParser.nextEvent();
        //int eventType = event.getEventType();
        java.util.ArrayList a100 = null;
        mktvsmart.screen.dataconvert.model.DataConvertChannelModel currentChannelModel = null;
        //Object xmlPullParser2 = xmlPullParser;

        int channelCounter = 0;
        boolean isChannel = false;

        while (xmlPullParser.hasNext()) {

            XMLEvent event = xmlPullParser.nextEvent();

            switch (event.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT:
                    // Begin der Channelliste im XML Document Format
                    a100 = new java.util.ArrayList();
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    xmlPullParser.close();
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    StartElement element = event.asStartElement();

//                    if(isChannel)
//                        System.out.print("Channel " + channelCounter + ": ");
//                    else
//                        System.out.println("START_ELEMENT " + element.getName());

                    if (element.getName().toString().equals("parm")) {
                        System.out.print(" begin parsing\n");
                        isChannel = true;
                        if(isChannel)
                            System.out.print("Channel " + channelCounter + ": ");
                        else
                            System.out.println("START_ELEMENT " + element.getName());
                        channelCounter++;
                        currentChannelModel = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
                        break;
                    } else if (element.getName().toString().equals((Object) "SatIndexSelected")) {
                        event = xmlPullParser.nextEvent();
                        mktvsmart.screen.GMScreenGlobalInfo.setmSatIndexSelected(Integer.parseInt(event.asCharacters().getData()));
                        //System.out.print(" SatIndexSelected = " + event.asCharacters().getData() + "\n");
                        break;
                    } else if (element.getName().toString().equals((Object) "ProgramId")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.SetProgramId(event.asCharacters().getData());
                        System.out.print(" ProgramId = " + event.asCharacters().getData() + "\n");
                        break;
                    } else if (element.getName().toString().equals((Object) "ProgramName")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setProgramName(event.asCharacters().getData());
                        break;
                    } else if (element.getName().toString().equals((Object) "ProgramIndex")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.SetProgramIndex(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "SatName")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.SetSatName(event.asCharacters().getData());
                        break;
                    } else if (element.getName().toString().equals((Object) "ProgramType")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.SetIsProgramScramble(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "IsProgramHD")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setIsProgramHd(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "FavMark")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.SetFavMark(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "LockMark")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setLockMark(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "HaveEPG")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.SetHaveEPG(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "IsPlaying")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setIsPlaying(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "WillBePlayed")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setmWillBePlayed(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "ChannelType")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setChannelTpye(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "Frequency")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setFreq(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "Polar")) {
                        event = xmlPullParser.nextEvent();
                        String s2 = event.asCharacters().getData();
                        currentChannelModel.setPol((char) ((s2.equals((Object) "0")) ? 104 : (s2.equals((Object) "1")) ? 118 : (s2.equals((Object) "2")) ? 108 : (s2.equals((Object) "3")) ? 114 : 104));
                        break;
                    } else if (element.getName().toString().equals((Object) "ModulationSystem")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setModulationSystem(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "ModulationType")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setModulationType(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "RollOff")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setRollOff(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "PilotTones")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setPilotTones(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "SymbolRate")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setSymRate(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "Fec")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setFec(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "VideoPid")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setVideoPid(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "AudioPid")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setAudioPid(event.asCharacters().getData());
                        break;
                    } else if (element.getName().toString().equals((Object) "TtxPid")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setTtxPid(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "SubPid")) {
                        event = xmlPullParser.nextEvent();
                        switch (event.getEventType()) {
                            case XMLStreamConstants.CHARACTERS: {
                                currentChannelModel.setSubPid(event.asCharacters().getData());
                                break;
                            }
                        }
                        break;
                    } else if (element.getName().toString().equals((Object) "PmtPid")) {
                        event = xmlPullParser.nextEvent();
                        currentChannelModel.setPmtPid(Integer.parseInt(event.asCharacters().getData()));
                        break;
                    } else if (element.getName().toString().equals((Object) "IsTuner2")) {
                        event = xmlPullParser.nextEvent();
                        int i33 = (short) Integer.parseInt(event.asCharacters().getData());
                        currentChannelModel.setIsTuner2((short) i33);
                        break;
                    } else {
                        if (!element.getName().toString().equals((Object) "FavorGroupID")) {
                            break;
                        }
                        event = xmlPullParser.nextEvent();
                        String[] a103 = event.asCharacters().getData().split(":");
                        if (a103 == null) {
                            break;
                        }
                        int i34 = 0;
                        while (i34 < a103.length) {
                            currentChannelModel.mfavGroupIDs.add((Object) Integer.valueOf(Integer.parseInt(a103[i34])));
                            i34 = i34 + 1;
                        }
                        break;
                    }
                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    if (!characters.isWhiteSpace()) {

                    }
                    System.out.println("EVENT CHARACTERS: should not happen!!! Data=" + characters.getData());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();

                    // Ende von Channeldaten, speichere Channel in Liste
                    if (endElement.getName().toString().equals("parm")) {
                        System.out.println("Channel " + channelCounter + "stop parsing");
                        isChannel = false;
                        if (GMScreenGlobalInfo.isSdsOpen() == 0 || currentChannelModel.getIsTuner2() == 0) {
                            a100.add(currentChannelModel);
                        }
                    }

                    break;
                case XMLStreamConstants.ATTRIBUTE:
                    System.out.println("EVENT ATTRIBUTE: should not happen!!!");

                    break;

                default:
                    break;
            }
        }
        return  a100;
    }

        /*java.util.ArrayList a0 = null;
        XMLPullParserConfiguration test;
        switch(i){
            case 27: {
                org.xmlpull.v1.XmlPullParser a1 = android.util.Xml.newPullParser();
                a1.setInput(a, "UTF-8");
                int i0 = a1.getEventType();
                java.util.ArrayList a2 = null;
                mktvsmart.screen.dataconvert.model.DataConvertUsernameModel a3 = null;
                Object a4 = a1;
                while(i0 != 1)
                {
                    switch(i0){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a4).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a2).add((Object)a3);
                            a3 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a4).getName().equals((Object)"parm"))
                            {
                                a3 = new mktvsmart.screen.dataconvert.model.DataConvertUsernameModel();
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a4).getName().equals((Object)"Username"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a4).next();
                                a3.setUsername(((org.xmlpull.v1.XmlPullParser)a4).getText());
                                break;
                            }
                        }
                        case 0: {
                            a2 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i0 = ((org.xmlpull.v1.XmlPullParser)a4).next();
                }
                return (java.util.List)(Object)a2;
            }
            case 26: {
                org.xmlpull.v1.XmlPullParser a5 = android.util.Xml.newPullParser();
                a5.setInput(a, "UTF-8");
                int i1 = a5.getEventType();
                java.util.ArrayList a6 = null;
                mktvsmart.screen.dataconvert.model.DataConvertGChatChannelInfoModel a7 = null;
                Object a8 = a5;
                while(i1 != 1)
                {
                    switch(i1){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a6).add((Object)a7);
                            a7 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"parm"))
                            {
                                a7 = new mktvsmart.screen.dataconvert.model.DataConvertGChatChannelInfoModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"Angle"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a8).next();
                                a7.setSatAngle(((org.xmlpull.v1.XmlPullParser)a8).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"Tp"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a8).next();
                                a7.setTp(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a8).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"ServiceId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a8).next();
                                a7.setServiceId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a8).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a8).getName().equals((Object)"EPG"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a8).next();
                                a7.setEpg(((org.xmlpull.v1.XmlPullParser)a8).getText());
                                break;
                            }
                        }
                        case 0: {
                            a6 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i1 = ((org.xmlpull.v1.XmlPullParser)a8).next();
                }
                return (java.util.List)(Object)a6;
            }
            case 25: {
                org.xmlpull.v1.XmlPullParser a9 = android.util.Xml.newPullParser();
                a9.setInput(a, "UTF-8");
                int i2 = a9.getEventType();
                java.util.ArrayList a10 = null;
                mktvsmart.screen.gchat.bean.GsChatSetting a11 = null;
                Object a12 = a9;
                while(i2 != 1)
                {
                    switch(i2){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a10).add((Object)a11);
                            a11 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"parm"))
                            {
                                a11 = mktvsmart.screen.gchat.bean.GsChatSetting.getInstance();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"ShowWindow"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a12).next();
                                a11.setShowWindow(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a12).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"WindowSize"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a12).next();
                                a11.setWindowSize(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a12).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"WindowPosition"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a12).next();
                                a11.setWindowPosition(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a12).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a12).getName().equals((Object)"WindowTransparency"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a12).next();
                                a11.setWindowTransparency(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a12).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a10 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i2 = ((org.xmlpull.v1.XmlPullParser)a12).next();
                }
                return (java.util.List)(Object)a10;
            }
            case 24: {
                org.xmlpull.v1.XmlPullParser a13 = android.util.Xml.newPullParser();
                a13.setInput(a, "UTF-8");
                int i3 = a13.getEventType();
                java.util.ArrayList a14 = null;
                mktvsmart.screen.gchat.bean.GsChatUser a15 = null;
                Object a16 = a13;
                while(i3 != 1)
                {
                    switch(i3){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a16).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a14).add((Object)a15);
                            a15 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a16).getName().equals((Object)"parm"))
                            {
                                a15 = new mktvsmart.screen.gchat.bean.GsChatUser();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a16).getName().equals((Object)"USERID"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a16).next();
                                a15.setUserID(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a16).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a16).getName().equals((Object)"Username"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a16).next();
                                a15.setUsername(((org.xmlpull.v1.XmlPullParser)a16).getText());
                                break;
                            }
                        }
                        case 0: {
                            a14 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i3 = ((org.xmlpull.v1.XmlPullParser)a16).next();
                }
                return (java.util.List)(Object)a14;
            }
            case 23: {
                org.xmlpull.v1.XmlPullParser a17 = android.util.Xml.newPullParser();
                a17.setInput(a, "UTF-8");
                int i4 = a17.getEventType();
                java.util.ArrayList a18 = null;
                mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel a19 = null;
                Object a20 = a17;
                while(i4 != 1)
                {
                    switch(i4){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a18).add((Object)a19);
                            a19 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"parm"))
                            {
                                a19 = new mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"Timestamp"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a20).next();
                                a19.setTimestamp(Long.parseLong(((org.xmlpull.v1.XmlPullParser)a20).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"USERID"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a20).next();
                                int i5 = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a20).getText());
                                a19.setUserID(i5);
                                if (i5 != mktvsmart.screen.gchat.bean.GsChatSetting.getInstance().getUserId())
                                {
                                    a19.setMsgType(0);
                                    break;
                                }
                                else
                                {
                                    a19.setMsgType(1);
                                    break;
                                }
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"Username"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a20).next();
                                a19.setUsername(((org.xmlpull.v1.XmlPullParser)a20).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a20).getName().equals((Object)"Content"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a20).next();
                                a19.setContent(((org.xmlpull.v1.XmlPullParser)a20).getText());
                                break;
                            }
                        }
                        case 0: {
                            a18 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i4 = ((org.xmlpull.v1.XmlPullParser)a20).next();
                }
                return (java.util.List)(Object)a18;
            }
            case 22: {
                org.xmlpull.v1.XmlPullParser a21 = android.util.Xml.newPullParser();
                a21.setInput(a, "UTF-8");
                int i6 = a21.getEventType();
                mktvsmart.screen.gchat.bean.GsChatRoomInfo a22 = null;
                java.util.ArrayList a23 = null;
                mktvsmart.screen.gchat.bean.GsChatUser a24 = null;
                Object a25 = a21;
                while(i6 != 1)
                {
                    switch(i6){
                        case 3: {
                            if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"parm"))
                            {
                                ((java.util.List)(Object)a23).add((Object)a22);
                                a22 = null;
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"UserInfo"))
                                {
                                    break;
                                }
                                a22.getUserList().add((Object)a24);
                                a24 = null;
                                break;
                            }
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"parm"))
                            {
                                a22 = new mktvsmart.screen.gchat.bean.GsChatRoomInfo();
                            }
                            if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"EventTitle"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a25).next();
                                a22.setEventTitle(((org.xmlpull.v1.XmlPullParser)a25).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"OnlineUserNum"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a25).next();
                                a22.setOnlineNum(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a25).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"RoomId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a25).next();
                                a22.setRoomID(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a25).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"UserInfo"))
                            {
                                a24 = new mktvsmart.screen.gchat.bean.GsChatUser();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"USERID"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a25).next();
                                a24.setUserID(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a25).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a25).getName().equals((Object)"Username"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a25).next();
                                a24.setUsername(((org.xmlpull.v1.XmlPullParser)a25).getText());
                                break;
                            }
                        }
                        case 0: {
                            a23 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i6 = ((org.xmlpull.v1.XmlPullParser)a25).next();
                }
                return (java.util.List)(Object)a23;
            }
            case 21: {
                org.xmlpull.v1.XmlPullParser a26 = android.util.Xml.newPullParser();
                a26.setInput(a, "UTF-8");
                int i7 = a26.getEventType();
                mktvsmart.screen.gchat.bean.GsChatSetting a27 = null;
                java.util.ArrayList a28 = null;
                Object a29 = a26;
                while(i7 != 1)
                {
                    switch(i7){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a29).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a28).add((Object)a27);
                            a27 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a29).getName().equals((Object)"parm"))
                            {
                                a27 = mktvsmart.screen.gchat.bean.GsChatSetting.getInstance();
                            }
                            if (((org.xmlpull.v1.XmlPullParser)a29).getName().equals((Object)"MySN"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a29).next();
                                a27.setSerialNumber(((org.xmlpull.v1.XmlPullParser)a29).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a29).getName().equals((Object)"MyUsername"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a29).next();
                                a27.setUsername(((org.xmlpull.v1.XmlPullParser)a29).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a29).getName().equals((Object)"USERID"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a29).next();
                                a27.setUserId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a29).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a28 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i7 = ((org.xmlpull.v1.XmlPullParser)a29).next();
                }
                return (java.util.List)(Object)a28;
            }
            case 20: {
                org.xmlpull.v1.XmlPullParser a30 = android.util.Xml.newPullParser();
                a30.setInput(a, "UTF-8");
                int i8 = a30.getEventType();
                java.util.ArrayList a31 = null;
                mktvsmart.screen.dataconvert.model.DataConvertPvrInfoModel a32 = null;
                Object a33 = a30;
                while(i8 != 1)
                {
                    switch(i8){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a31).add((Object)a32);
                            a32 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"parm"))
                            {
                                a32 = new mktvsmart.screen.dataconvert.model.DataConvertPvrInfoModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"pvr_name"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setProgramName(((org.xmlpull.v1.XmlPullParser)a33).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"pvr_uid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setmPvrId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a33).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"pvr_duration"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setmPvrDuration(((org.xmlpull.v1.XmlPullParser)a33).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"Pvr_time"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setmPvrTime(((org.xmlpull.v1.XmlPullParser)a33).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"pvr_type"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setmPvrType(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a33).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a33).getName().equals((Object)"crypto"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a33).next();
                                a32.setmPvrCrypto(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a33).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a31 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i8 = ((org.xmlpull.v1.XmlPullParser)a33).next();
                }
                return (java.util.List)(Object)a31;
            }
            case 19: {

                /***** Transponder List ******/ /*

                org.xmlpull.v1.XmlPullParser a34 = android.util.Xml.newPullParser();
                a34.setInput(a, "UTF-8");
                int i9 = a34.getEventType();
                java.util.ArrayList a35 = null;
                mktvsmart.screen.dataconvert.model.DataConvertTpModel a36 = null;
                Object a37 = a34;
                while(i9 != 1)
                {
                    switch(i9){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a35).add((Object)a36);
                            a36 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"parm"))
                            {
                                a36 = new mktvsmart.screen.dataconvert.model.DataConvertTpModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"TpIndex"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                a36.setTpIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"SatIndex"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                a36.setSatIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"SystemRate"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                a36.setSymRate(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"Pol"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                String s = ((org.xmlpull.v1.XmlPullParser)a37).getText();
                                a36.setPol((char)((s.equals((Object)"0")) ? 104 : (s.equals((Object)"1")) ? 118 : (s.equals((Object)"2")) ? 108 : (s.equals((Object)"3")) ? 114 : 104));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"Fec"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                a36.setFec(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a37).getName().equals((Object)"Freq"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a37).next();
                                a36.setFreq(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a37).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a35 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i9 = ((org.xmlpull.v1.XmlPullParser)a37).next();
                }
                return (java.util.List)(Object)a35;
            }
            case 18: {

                /***** Sat List ******/ /*

                org.xmlpull.v1.XmlPullParser a38 = android.util.Xml.newPullParser();
                a38.setInput(a, "UTF-8");
                int i10 = a38.getEventType();
                java.util.ArrayList a39 = null;
                mktvsmart.screen.dataconvert.model.DataConvertSatModel a40 = null;
                Object a41 = a38;
                while(i10 != 1)
                {
                    switch(i10){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a39).add((Object)a40);
                            a40 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"parm"))
                            {
                                a40 = new mktvsmart.screen.dataconvert.model.DataConvertSatModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"SatName"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a41).next();
                                a40.setmSatName(((org.xmlpull.v1.XmlPullParser)a41).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"SatNo"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a41).next();
                                a40.setmSatIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a41).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"SatAngle"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a41).next();
                                a40.setmSatAngle(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a41).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a41).getName().equals((Object)"SatDir"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a41).next();
                                a40.setmSatDir(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a41).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a39 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i10 = ((org.xmlpull.v1.XmlPullParser)a41).next();
                }
                return (java.util.List)(Object)a39;
            }
            case 17: {
                org.xmlpull.v1.XmlPullParser a42 = android.util.Xml.newPullParser();
                a42.setInput(a, "UTF-8");
                int i11 = a42.getEventType();
                java.util.ArrayList a43 = null;
                String s0 = null;
                Object a44 = a42;
                while(i11 != 1)
                {
                    switch(i11){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a43).add((Object)s0);
                            s0 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"cur_channel_list_type"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a44).next();
                                s0 = new String(((org.xmlpull.v1.XmlPullParser)a44).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"max_password_num"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a44).next();
                                mktvsmart.screen.GMScreenGlobalInfo.setmMaxPasswordNum(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a44).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"cur_channel_type"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a44).next();
                                mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.setCurrent_channel_tv_radio_type(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a44).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"is_support_pvr2ip_server"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a44).next();
                                mktvsmart.screen.GMScreenGlobalInfo.setmPvr2ipServerSupport(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a44).getText()));
                                System.out.println(new StringBuilder("GMScreenGlobalInfo.getmPvr2ipServerSupport()==").append(mktvsmart.screen.GMScreenGlobalInfo.getmPvr2ipServerSupport()).toString());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a44).getName().equals((Object)"is_sds_open"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a44).next();
                                mktvsmart.screen.GMScreenGlobalInfo.setSdsOpen(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a44).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a43 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i11 = ((org.xmlpull.v1.XmlPullParser)a44).next();
                }
                return (java.util.List)(Object)a43;
            }
            case 16: {
                org.xmlpull.v1.XmlPullParser a45 = android.util.Xml.newPullParser();
                a45.setInput(a, "UTF-8");
                int i12 = a45.getEventType();
                java.util.ArrayList a46 = null;
                java.util.HashMap a47 = null;
                Object a48 = a45;
                while(i12 != 1)
                {
                    switch(i12){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a48).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a46).add((Object)a47);
                            a47 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a48).getName().equals((Object)"success"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a48).next();
                                a47 = new java.util.HashMap();
                                ((java.util.Map)(Object)a47).put((Object)"success", (Object)Integer.valueOf(((org.xmlpull.v1.XmlPullParser)a48).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a48).getName().equals((Object)"url"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a48).next();
                                ((java.util.Map)(Object)a47).put((Object)"url", (Object)((org.xmlpull.v1.XmlPullParser)a48).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a48).getName().equals((Object)"errormsg"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a48).next();
                                ((java.util.Map)(Object)a47).put((Object)"errormsg", (Object)((org.xmlpull.v1.XmlPullParser)a48).getText());
                                break;
                            }
                        }
                        case 0: {
                            a46 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i12 = ((org.xmlpull.v1.XmlPullParser)a48).next();
                }
                return (java.util.List)(Object)a46;
            }
            case 15: {
                org.xmlpull.v1.XmlPullParser a49 = android.util.Xml.newPullParser();
                a49.setInput(a, "UTF-8");
                int i13 = a49.getEventType();
                java.util.ArrayList a50 = null;
                String s1 = null;
                Object a51 = a49;
                while(i13 != 1)
                {
                    switch(i13){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a51).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a50).add((Object)s1);
                            s1 = null;
                            break;
                        }
                        case 2: {
                            if (!((org.xmlpull.v1.XmlPullParser)a51).getName().equals((Object)"Data"))
                            {
                                break;
                            }
                            ((org.xmlpull.v1.XmlPullParser)a51).next();
                            s1 = new String(((org.xmlpull.v1.XmlPullParser)a51).getText());
                            break;
                        }
                        case 0: {
                            a50 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i13 = ((org.xmlpull.v1.XmlPullParser)a51).next();
                }
                return (java.util.List)(Object)a50;
            }
            case 14: {
                org.xmlpull.v1.XmlPullParser a52 = android.util.Xml.newPullParser();
                a52.setInput(a, "UTF-8");
                int i14 = a52.getEventType();
                java.util.ArrayList a53 = null;
                mktvsmart.screen.dataconvert.model.DataConvertStbInfoModel a54 = null;
                Object a55 = a52;
                while(i14 != 1)
                {
                    switch(i14){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a53).add((Object)a54);
                            a54 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"parm"))
                            {
                                a54 = new mktvsmart.screen.dataconvert.model.DataConvertStbInfoModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"StbStatus"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmStbStatus(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a55).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"ProductName"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmProductName(((org.xmlpull.v1.XmlPullParser)a55).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"SoftwareVersion"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmSoftwareVersion(((org.xmlpull.v1.XmlPullParser)a55).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"SerialNumber"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmSerialNumber(((org.xmlpull.v1.XmlPullParser)a55).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"ChannelNum"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmChannelNum(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a55).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a55).getName().equals((Object)"MaxNumOfPrograms"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a55).next();
                                a54.setmMaxNumOfPrograms(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a55).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a53 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i14 = ((org.xmlpull.v1.XmlPullParser)a55).next();
                }
                return (java.util.List)(Object)a53;
            }
            case 13: {
                org.xmlpull.v1.XmlPullParser a56 = android.util.Xml.newPullParser();
                a56.setInput(a, "UTF-8");
                int i15 = a56.getEventType();
                java.util.ArrayList a57 = null;
                mktvsmart.screen.dataconvert.model.DataConvertSortModel a58 = null;
                Object a59 = a56;
                while(i15 != 1)
                {
                    label3: switch(i15){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a59).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a57).add((Object)a58);
                            a58 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a59).getName().equals((Object)"parm"))
                            {
                                a58 = new mktvsmart.screen.dataconvert.model.DataConvertSortModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a59).getName().equals((Object)"SortType"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a59).next();
                                a58.setmSortType(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a59).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a59).getName().equals((Object)"MacroFlag"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a59).next();
                                a58.setmMacroFlag(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a59).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a59).getName().equals((Object)"SortTypeList"))
                                {
                                    break label3;
                                }
                                System.out.println("have SortTypeList");
                                ((org.xmlpull.v1.XmlPullParser)a59).next();
                                String[] a60 = ((org.xmlpull.v1.XmlPullParser)a59).getText().split(",");
                                if (a60 == null)
                                {
                                    break label3;
                                }
                                if (a60.length <= 0)
                                {
                                    break label3;
                                }
                                java.util.ArrayList a61 = new java.util.ArrayList();
                                int i16 = 0;
                                while(i16 < a60.length)
                                {
                                    a61.add((Object)a60[i16]);
                                    i16 = i16 + 1;
                                }
                                a58.setSortTypeList(a61);
                                break;
                            }
                        }
                        case 0: {
                            a57 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i15 = ((org.xmlpull.v1.XmlPullParser)a59).next();
                }
                return (java.util.List)(Object)a57;
            }
            case 12: {
                org.xmlpull.v1.XmlPullParser a62 = android.util.Xml.newPullParser();
                a62.setInput(a, "UTF-8");
                int i17 = a62.getEventType();
                java.util.ArrayList a63 = null;
                mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel a64 = null;
                Object a65 = a62;
                while(i17 != 1)
                {
                    switch(i17){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a65).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a63).add((Object)a64);
                            a64 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a65).getName().equals((Object)"parm"))
                            {
                                a64 = new mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a65).getName().equals((Object)"CurChannelType"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a65).next();
                                mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.setCurrent_channel_tv_radio_type(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a65).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a65).getName().equals((Object)"tv_radio_key_press"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a65).next();
                                a64.setTvRadioKeyPress(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a65).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a63 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i17 = ((org.xmlpull.v1.XmlPullParser)a65).next();
                }
                return (java.util.List)(Object)a63;
            }
            case 11: {
                org.xmlpull.v1.XmlPullParser a66 = android.util.Xml.newPullParser();
                a66.setInput(a, "UTF-8");
                int i18 = a66.getEventType();
                java.util.ArrayList a67 = null;
                mktvsmart.screen.dataconvert.model.DataConvertControlModel a68 = null;
                Object a69 = a66;
                while(i18 != 1)
                {
                    switch(i18){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a67).add((Object)a68);
                            a68 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"parm"))
                            {
                                a68 = new mktvsmart.screen.dataconvert.model.DataConvertControlModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"SleepSwitch"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a69).next();
                                a68.setSleepSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a69).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"SleepTime"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a69).next();
                                a68.setSleepTime(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a69).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"ScreenLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a69).next();
                                a68.SetIsLockScreen(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a69).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a69).getName().equals((Object)"PowerMode"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a69).next();
                                a68.SetPowerOff(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a69).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a67 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i18 = ((org.xmlpull.v1.XmlPullParser)a69).next();
                }
                return (java.util.List)(Object)a67;
            }
            case 10: {
                org.xmlpull.v1.XmlPullParser a70 = android.util.Xml.newPullParser();
                a70.setInput(a, "UTF-8");
                int i19 = a70.getEventType();
                java.util.ArrayList a71 = null;
                mktvsmart.screen.dataconvert.model.DataConvertFavorModel a72 = null;
                Object a73 = a70;
                while(i19 != 1)
                {
                    switch(i19){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a73).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a71).add((Object)a72);
                            a72 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a73).getName().equals((Object)"favMaxNum"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a73).next();
                                mktvsmart.screen.dataconvert.model.DataConvertFavorModel.favorNum = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a73).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a73).getName().equals((Object)"parm"))
                            {
                                a72 = new mktvsmart.screen.dataconvert.model.DataConvertFavorModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a73).getName().equals((Object)"favorGroupName"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a73).next();
                                a72.SetFavorName(((org.xmlpull.v1.XmlPullParser)a73).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a73).getName().equals((Object)"FavorGroupID"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a73).next();
                                a72.setFavorTypeID(Integer.valueOf(((org.xmlpull.v1.XmlPullParser)a73).getText()).intValue());
                                break;
                            }
                        }
                        case 0: {
                            a71 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i19 = ((org.xmlpull.v1.XmlPullParser)a73).next();
                }
                return (java.util.List)(Object)a71;
            }
            case 9: {
                org.xmlpull.v1.XmlPullParser a74 = android.util.Xml.newPullParser();
                a74.setInput(a, "UTF-8");
                int i20 = a74.getEventType();
                a0 = null;
                Object a75 = a74;
                while(i20 != 1)
                {
                    switch(i20){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a75).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a0).add((Object)null);
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a75).getName().equals((Object)"StbMonth"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a75).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbMonth = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a75).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a75).getName().equals((Object)"StbDay"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a75).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbDay = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a75).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a75).getName().equals((Object)"StbHour"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a75).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbHour = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a75).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a75).getName().equals((Object)"StbMin"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a75).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbMin = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a75).getText());
                                break;
                            }
                        }
                        case 0: {
                            a0 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i20 = ((org.xmlpull.v1.XmlPullParser)a75).next();
                }
                break;
            }
            case 8: {
                org.xmlpull.v1.XmlPullParser a76 = android.util.Xml.newPullParser();
                a76.setInput(a, "UTF-8");
                int i21 = a76.getEventType();
                java.util.ArrayList a77 = null;
                mktvsmart.screen.dataconvert.model.DataConvertChannelModel a78 = null;
                Object a79 = a76;
                while(i21 != 1)
                {
                    switch(i21){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a79).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a77).add((Object)a78);
                            a78 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a79).getName().equals((Object)"parm"))
                            {
                                a78 = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a79).getName().equals((Object)"LockedChannelIndex"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a79).next();
                                a78.SetProgramId(((org.xmlpull.v1.XmlPullParser)a79).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a79).getName().equals((Object)"TVState"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a79).next();
                                a78.setChannelTpye(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a79).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a77 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i21 = ((org.xmlpull.v1.XmlPullParser)a79).next();
                }
                return (java.util.List)(Object)a77;
            }
            case 7: {
                org.xmlpull.v1.XmlPullParser a80 = android.util.Xml.newPullParser();
                a80.setInput(a, "UTF-8");
                int i22 = a80.getEventType();
                a0 = null;
                mktvsmart.screen.dataconvert.model.DataConvertTimeModel a81 = null;
                Object a82 = a80;
                while(i22 != 1)
                {
                    switch(i22){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a82).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a0).add((Object)a81);
                            a81 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a82).getName().equals((Object)"parm"))
                            {
                                a81 = new mktvsmart.screen.dataconvert.model.DataConvertTimeModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a82).getName().equals((Object)"Confirm"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a82).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.isConfirm = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a82).getText());
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a82).getName().equals((Object)"TimerIndex"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a82).next();
                                a81.SetTimerIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a82).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a0 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i22 = ((org.xmlpull.v1.XmlPullParser)a82).next();
                }
                break;
            }
            case 6: {
                org.xmlpull.v1.XmlPullParser a83 = android.util.Xml.newPullParser();
                a83.setInput(a, "UTF-8");
                int i23 = a83.getEventType();
                int i24 = 0;
                int i25 = 0;
                java.util.ArrayList a84 = null;
                mktvsmart.screen.GsEPGTableChannel a85 = null;
                mktvsmart.screen.GsEPGEvent a86 = null;
                Object a87 = a83;
                while(i23 != 1)
                {
                    switch(i23){
                        case 3: {
                            if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"prog_epg"))
                            {
                                ((java.util.List)(Object)a84).add((Object)a85);
                                a85 = null;
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"prog_day_epg"))
                            {
                                i24 = i24 + 1;
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"epg_event"))
                                {
                                    break;
                                }
                                a85.getEpgDayByIndex(i24).getArrayList().add((Object)a86);
                                a86 = null;
                                i25 = 0;
                                break;
                            }
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"prog_epg"))
                            {
                                a85 = new mktvsmart.screen.GsEPGTableChannel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"prog_no"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a87).next();
                                a85.setProgNo(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"original_net_id"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a87).next();
                                a85.setOriginalNetworkID(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"transport_stream_id"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a87).next();
                                a85.setTransportStreamID(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"today_date"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a87).next();
                                int i26 = (byte)Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText());
                                a85.setTodayDate((byte)i26);
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"current_epg_time"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a87).next();
                                a85.setCurrentEpgTime(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                break;
                            }
                            else
                            {
                                if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"prog_day_epg"))
                                {
                                    break;
                                }
                                if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_valid_num"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a85.setArrayEventFieldByIndex(i24, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"epg_event"))
                                {
                                    a86 = new mktvsmart.screen.GsEPGEvent();
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_start_time"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setStartTime(((org.xmlpull.v1.XmlPullParser)a87).getText());
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_end_time"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setEndTime(((org.xmlpull.v1.XmlPullParser)a87).getText());
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_age_rating"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setAgeRating(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_timer_type"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setEpgTimerType(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_total_language"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setTotalEpgLanguage(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_titile_lang_code"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setTitleLanCode(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_sub_titile_lang_code"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setSubtitleLanCode(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_desc_lang_code"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setDescLanCode(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_titile_len"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setTitleLen(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_sub_titile_len"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setSubtitleLen(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_desc_len"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setDescLen(i25, Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a87).getText()));
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_title"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setEventTitle(i25, ((org.xmlpull.v1.XmlPullParser)a87).getText());
                                    break;
                                }
                                else if (((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_sub_title"))
                                {
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setEventSubTitle(i25, ((org.xmlpull.v1.XmlPullParser)a87).getText());
                                    break;
                                }
                                else
                                {
                                    if (!((org.xmlpull.v1.XmlPullParser)a87).getName().equals((Object)"event_desc"))
                                    {
                                        break;
                                    }
                                    ((org.xmlpull.v1.XmlPullParser)a87).next();
                                    a86.setEventDesc(i25, ((org.xmlpull.v1.XmlPullParser)a87).getText());
                                    i25 = i25 + 1;
                                    break;
                                }
                            }
                        }
                        case 0: {
                            a84 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i23 = ((org.xmlpull.v1.XmlPullParser)a87).next();
                }
                return (java.util.List)(Object)a84;
            }
            case 3: {
                org.xmlpull.v1.XmlPullParser a88 = android.util.Xml.newPullParser();
                a88.setInput(a, "UTF-8");
                int i27 = a88.getEventType();
                java.util.ArrayList a89 = null;
                mktvsmart.screen.dataconvert.model.DataConvertUpdateModel a90 = null;
                Object a91 = a88;
                while(i27 != 1)
                {
                    switch(i27){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a91).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a89).add((Object)a90);
                            a90 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a91).getName().equals((Object)"parm"))
                            {
                                a90 = new mktvsmart.screen.dataconvert.model.DataConvertUpdateModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a91).getName().equals((Object)"CustomerId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a91).next();
                                a90.SetCustomerId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a91).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a91).getName().equals((Object)"HardwareId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a91).next();
                                a90.SetModelId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a91).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a91).getName().equals((Object)"VersionId"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a91).next();
                                a90.SetVersionId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a91).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a89 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i27 = ((org.xmlpull.v1.XmlPullParser)a91).next();
                }
                return (java.util.List)(Object)a89;
            }
            case 2: {
                org.xmlpull.v1.XmlPullParser a92 = android.util.Xml.newPullParser();
                a92.setInput(a, "UTF-8");
                int i28 = a92.getEventType();
                java.util.ArrayList a93 = null;
                mktvsmart.screen.dataconvert.model.DataConvertControlModel a94 = null;
                Object a95 = a92;
                while(i28 != 1)
                {
                    switch(i28){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a93).add((Object)a94);
                            a94 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"parm"))
                            {
                                a94 = new mktvsmart.screen.dataconvert.model.DataConvertControlModel();
                                a94.SetPowerOff(-1);
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"Password"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetPassword(((org.xmlpull.v1.XmlPullParser)a95).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"PasswordLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetPswLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"ServiceLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetServiceLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"InstallationLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetInstallLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"EditChannelLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetEditChannelLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"SettingsLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetSettingsLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"NetworkLock"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetNetworkLockSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"AgeRating"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetAgeRatingSwitch(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"IsLockScreen"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetIsLockScreen(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a95).getName().equals((Object)"PowerMode"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a95).next();
                                a94.SetPowerOff(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a95).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a93 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i28 = ((org.xmlpull.v1.XmlPullParser)a95).next();
                }
                return (java.util.List)(Object)a93;
            }
            case 1: case 4: {
                org.xmlpull.v1.XmlPullParser a96 = android.util.Xml.newPullParser();
                a96.setInput(a, "UTF-8");
                int i29 = a96.getEventType();
                a0 = null;
                mktvsmart.screen.dataconvert.model.DataConvertTimeModel a97 = null;
                Object a98 = a96;
                while(i29 != 1)
                {
                    switch(i29){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            ((java.util.List)(Object)a0).add((Object)a97);
                            a97 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"Confirm"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel.isConfirm = Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"parm"))
                            {
                                a97 = new mktvsmart.screen.dataconvert.model.DataConvertTimeModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerProgramName"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetTimeProgramName(((org.xmlpull.v1.XmlPullParser)a98).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerProgramSatTpId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.setProgramId(((org.xmlpull.v1.XmlPullParser)a98).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerMonth"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetTimeMonth(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerDay"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetTimeDay(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerStartHour"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetStartHour(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerStartMin"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetStartMin(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerEndHour"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetEndHour(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerEndMin"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetEndMin(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerRepeat"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetTimerRepeat(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerStatus"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.SetTimerStatus(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a98).getName().equals((Object)"TimerEventID"))
                                {
                                    break;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a98).next();
                                a97.setEventId(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a98).getText()));
                                break;
                            }
                        }
                        case 0: {
                            a0 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i29 = ((org.xmlpull.v1.XmlPullParser)a98).next();
                }
                break;
            }
            case 0: {

                /*****Channel List******/ /*

                org.xmlpull.v1.XmlPullParser a99 = android.util.Xml.newPullParser();
                a99.setInput(a, "UTF-8");
                int i30 = a99.getEventType();
                java.util.ArrayList a100 = null;
                mktvsmart.screen.dataconvert.model.DataConvertChannelModel a101 = null;
                Object a102 = a99;
                while(i30 != 1)
                {
                    label0: switch(i30){
                        case 3: {
                            if (!((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"parm"))
                            {
                                break;
                            }
                            int i31 = mktvsmart.screen.GMScreenGlobalInfo.isSdsOpen();
                            label1: {
                                label2: {
                                    if (i31 == 0)
                                    {
                                        break label2;
                                    }
                                    int i32 = a101.getIsTuner2();
                                    if (i32 != 0)
                                    {
                                        break label1;
                                    }
                                }
                                ((java.util.List)(Object)a100).add((Object)a101);
                            }
                            a101 = null;
                            break;
                        }
                        case 2: {
                            if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"parm"))
                            {
                                a101 = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SatIndexSelected"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                mktvsmart.screen.GMScreenGlobalInfo.setmSatIndexSelected(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramId"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetProgramId(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramName"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setProgramName(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramIndex"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetProgramIndex(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SatName"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetSatName(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ProgramType"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetIsProgramScramble(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"IsProgramHD"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setIsProgramHd(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"FavMark"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetFavMark(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"LockMark"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setLockMark(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"HaveEPG"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.SetHaveEPG(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"IsPlaying"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setIsPlaying(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"WillBePlayed"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setmWillBePlayed(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ChannelType"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setChannelTpye(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"Frequency"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setFreq(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"Polar"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                String s2 = ((org.xmlpull.v1.XmlPullParser)a102).getText();
                                a101.setPol((char)((s2.equals((Object)"0")) ? 104 : (s2.equals((Object)"1")) ? 118 : (s2.equals((Object)"2")) ? 108 : (s2.equals((Object)"3")) ? 114 : 104));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ModulationSystem"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setModulationSystem(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"ModulationType"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setModulationType(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"RollOff"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setRollOff(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"PilotTones"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setPilotTones(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SymbolRate"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setSymRate(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"Fec"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setFec(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"VideoPid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setVideoPid(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"AudioPid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setAudioPid(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"TtxPid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setTtxPid(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"SubPid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setSubPid(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"PmtPid"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                a101.setPmtPid(Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText()));
                                break;
                            }
                            else if (((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"IsTuner2"))
                            {
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                int i33 = (short)Integer.parseInt(((org.xmlpull.v1.XmlPullParser)a102).getText());
                                a101.setIsTuner2((short)i33);
                                break;
                            }
                            else
                            {
                                if (!((org.xmlpull.v1.XmlPullParser)a102).getName().equals((Object)"FavorGroupID"))
                                {
                                    break label0;
                                }
                                ((org.xmlpull.v1.XmlPullParser)a102).next();
                                String[] a103 = ((org.xmlpull.v1.XmlPullParser)a102).getText().split(":");
                                if (a103 == null)
                                {
                                    break label0;
                                }
                                int i34 = 0;
                                while(i34 < a103.length)
                                {
                                    a101.mfavGroupIDs.add((Object)Integer.valueOf(Integer.parseInt(a103[i34])));
                                    i34 = i34 + 1;
                                }
                                break;
                            }
                        }
                        case 0: {
                            a100 = new java.util.ArrayList();
                            break;
                        }
                        default: {
                            break;
                        }
                        case 1: {
                        }
                    }
                    i30 = ((org.xmlpull.v1.XmlPullParser)a102).next();
                }
                return (java.util.List)(Object)a100;
            }
            default: {
                a0 = null;
            }
        }
        return (java.util.List)(Object)a0;


        return null;
    }*/

    public void saSpacerAndroid() {

    }


    /**
     *
     * Based on Krakatau Decompiler
     *
     */

//    java.util.List a;
//    Object a0 = null;
//    org.xmlpull.v1.XmlSerializer a1;
//    java.io.StringWriter a2;
//    Object a3;

    public void InstanceOfMap(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3 instanceof java.util.Map)
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            Object a4 = a.iterator();
            a0 = a1;
            while(((java.util.Iterator)a4).hasNext())
            {
                java.util.Map a5 = (java.util.Map)((java.util.Iterator)a4).next();
                java.util.Iterator a6 = a5.keySet().iterator();
                Object a7 = a5;
                Object a8 = a6;
                while(((java.util.Iterator)a8).hasNext())
                {
                    String s = (String)((java.util.Iterator)a8).next();
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", s);
                    ((org.xmlpull.v1.XmlSerializer)a0).text((String)((java.util.Map)a7).get((Object)s));
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", s);
                }
            }
        }
    }

    public void InstanceOfDataConvertChatMsgModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3 instanceof mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel)
        {
            if (i != 1102)
            {
                a0 = a1;
            }
            else
            {
                mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel a9 = (mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel)a.get(0);
                a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
                a1.startTag("", "parm");
                a1.startTag("", "Timestamp");
                a1.text(Long.toString(a9.getTimestamp()));
                a1.endTag("", "Timestamp");
                a1.startTag("", "Content");
                a1.text(a9.getContent());
                a1.endTag("", "Content");
                a1.endTag("", "parm");
                a0 = a1;
            }
        }
    }

    public void InstanceOfGsChatSetting(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3 instanceof mktvsmart.screen.gchat.bean.GsChatSetting)
        {
            if (i != 1104)
            {
                a0 = a1;
            }
            else
            {
                mktvsmart.screen.gchat.bean.GsChatSetting a10 = (mktvsmart.screen.gchat.bean.GsChatSetting)a.get(0);
                a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
                a1.startTag("", "parm");
                a1.startTag("", "ShowWindow");
                a1.text(String.valueOf(a10.getSHowWindow()));
                a1.endTag("", "ShowWindow");
                a1.startTag("", "WindowSize");
                a1.text(String.valueOf(a10.getWindowSize()));
                a1.endTag("", "WindowSize");
                a1.startTag("", "WindowPosition");
                a1.text(String.valueOf(a10.getWindowPosition()));
                a1.endTag("", "WindowPosition");
                a1.startTag("", "WindowTransparency");
                a1.text(String.valueOf(a10.getWindowTransparency()));
                a1.endTag("", "WindowTransparency");
                a1.endTag("", "parm");
                a0 = a1;
            }
        }
    }

    public void InstanceOfGsChatUser(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3 instanceof mktvsmart.screen.gchat.bean.GsChatUser)
        {
            if (i != 1103)
            {
                a0 = a1;
            }
            else
            {
                a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
                Object a11 = a.iterator();
                a0 = a1;
                while(((java.util.Iterator)a11).hasNext())
                {
                    mktvsmart.screen.gchat.bean.GsChatUser a12 = (mktvsmart.screen.gchat.bean.GsChatUser)((java.util.Iterator)a11).next();
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "parm");
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "USERID");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(Integer.toString(a12.getUserID()));
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "USERID");
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "Username");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(a12.getUsername());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "Username");
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "Action");
                    if (a12.getBlock())
                    {
                        ((org.xmlpull.v1.XmlSerializer)a0).text("1");
                    }
                    else
                    {
                        ((org.xmlpull.v1.XmlSerializer)a0).text("0");
                    }
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "Action");
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "parm");
                }
            }
        }
    }

    public void InstanceOfDataConvertUsernameModel (java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3 instanceof mktvsmart.screen.dataconvert.model.DataConvertUsernameModel)
        {
            if (i != 1105)
            {
                a0 = a1;
            }
            else
            {
                mktvsmart.screen.dataconvert.model.DataConvertUsernameModel a13 = (mktvsmart.screen.dataconvert.model.DataConvertUsernameModel)a.get(0);
                a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
                a1.startTag("", "parm");
                a1.startTag("", "Username");
                a1.text(a13.getUsername());
                a1.endTag("", "Username");
                a1.endTag("", "parm");
                a0 = a1;
            }
        }
        else
        {
            a0 = a1;
        }

    }

    public void InstanceOfDataConvertSatModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertSatModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            if (i != 1060)
            {
                a0 = a1;
            }
            else
            {
                a1.startTag("", "SatIndexSelected");
                a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertSatModel)a.get(0)).getmSatIndex())).toString());
                a1.endTag("", "SatIndexSelected");
                a0 = a1;
            }
        }

    }

    public void InstanceOfDataConvertOneDataModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertOneDataModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            a1.startTag("", "data");
            a1.text(((mktvsmart.screen.dataconvert.model.DataConvertOneDataModel)a.get(0)).getData());
            a1.endTag("", "data");
            a0 = a1;
        }
    }


    public void InstanceOfDataConvertSortModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertSortModel.class.getName())
        {

        }
        else
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            if (i != 1006)
            {
                a0 = a1;
            }
            else
            {
                a1.startTag("", "SortType");
                a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertSortModel)a.get(0)).getmSortType())).toString());
                a1.endTag("", "SortType");
                a1.startTag("", "TvState");
                a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertSortModel)a.get(0)).getmTvState())).toString());
                a1.endTag("", "TvState");
                a0 = a1;
            }
        }
    }

    public void InstanceOfDataConvertInputMethodModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertInputMethodModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            if (i != 1059)
            {
                a0 = a1;
            }
            else
            {
                a1.startTag("", "KeyCode");
                a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertInputMethodModel)a.get(0)).getKeyCode())).toString());
                a1.endTag("", "KeyCode");
                a0 = a1;
            }
        }
    }

    public void InstanceOfDataConvertChannelTypeModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            if (i != 1007)
            {
                a0 = a1;
            }
            else
            {
                a1.startTag("", "IsFavList");
                a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel)a.get(0)).getIsFavList())).toString());
                a1.endTag("", "IsFavList");
                a1.startTag("", "SelectListType");
                a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel)a.get(0)).getSelectListType())).toString());
                a1.endTag("", "SelectListType");
                a0 = a1;
            }
        }
    }

    public void InstanceOfDataConvertFavorModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertFavorModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            if (i != 1055)
            {
                a0 = a1;
            }
            else
            {
                a1.startTag("", "FavorRenamePos");
                a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertFavorModel)a.get(0)).GetFavorIndex())).toString());
                a1.endTag("", "FavorRenamePos");
                a1.startTag("", "FavorNewName");
                a1.text(((mktvsmart.screen.dataconvert.model.DataConvertFavorModel)a.get(0)).GetFavorName());
                a1.endTag("", "FavorNewName");
                a1.startTag("", "FavorGroupID");
                a1.text(new StringBuilder().append(((mktvsmart.screen.dataconvert.model.DataConvertFavorModel)a.get(0)).getFavorTypeID()).toString());
                a1.endTag("", "FavorGroupID");
                a0 = a1;
            }
        }
    }

    public void InstanceOfDataConvertRcuModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertRcuModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            if (i != 1040)
            {
                a0 = a1;
            }
            else
            {
                Object a14 = a.iterator();
                a0 = a1;
                while(((java.util.Iterator)a14).hasNext())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertRcuModel a15 = (mktvsmart.screen.dataconvert.model.DataConvertRcuModel)((java.util.Iterator)a14).next();
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "KeyValue");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a15.getKeyValue())).toString());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "KeyValue");
                }
            }
        }
    }

    public void InstanceOfDataConvertDebugModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertDebugModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            label2: {
                if (i == 1054)
                {
                    break label2;
                }
                if (i != 9)
                {
                    a0 = a1;
                    //break label0;   // write empty command request
                    return;
                }
            }
            Object a16 = a.iterator();
            a0 = a1;
            while(((java.util.Iterator)a16).hasNext())
            {
                mktvsmart.screen.dataconvert.model.DataConvertDebugModel a17 = (mktvsmart.screen.dataconvert.model.DataConvertDebugModel)((java.util.Iterator)a16).next();
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "EnableDebug");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a17.getDebugValue())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "EnableDebug");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "RequestDataFrom");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a17.getRequestDataFrom())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "RequestDataFrom");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "RequestDataTo");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a17.getRequestDataTo())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "RequestDataTo");
            }
        }
    }

    public void InstanceOfDataConvertUpdateModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertUpdateModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            if (i != 1010)
            {
                a0 = a1;
            }
            else
            {
                Object a18 = a.iterator();
                a0 = a1;
                while(((java.util.Iterator)a18).hasNext())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertUpdateModel a19 = (mktvsmart.screen.dataconvert.model.DataConvertUpdateModel)((java.util.Iterator)a18).next();
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ChannelFileLen");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a19.GetDataLen())).toString());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ChannelFileLen");
                }
            }
        }
    }
    public void InstanceOfDataConvertControlModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertControlModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            if (i != 1051)
            {
                if (i != 1052)
                {
                    if (i != 1050)
                    {
                        a0 = a1;
                    }
                    else
                    {
                        mktvsmart.screen.dataconvert.model.DataConvertControlModel a20 = (mktvsmart.screen.dataconvert.model.DataConvertControlModel)a.get(0);
                        a1.startTag("", "parm");
                        a1.startTag("", "SleepSwitch");
                        a1.text(new StringBuilder(String.valueOf(a20.getSleepSwitch())).toString());
                        a1.endTag("", "SleepSwitch");
                        if (a20.getSleepSwitch() == 1)
                        {
                            a1.startTag("", "SleepTime");
                            a1.text(new StringBuilder(String.valueOf(a20.getSleepTime())).toString());
                            a1.endTag("", "SleepTime");
                        }
                        a1.endTag("", "parm");
                        a0 = a1;
                    }
                }
                else
                {
                    a1.startTag("", "parm");
                    a1.startTag("", "OldPassword");
                    a1.text(((mktvsmart.screen.dataconvert.model.DataConvertControlModel)a.get(0)).GetPassword());
                    a1.endTag("", "OldPassword");
                    a1.endTag("", "parm");
                    a1.startTag("", "parm");
                    a1.startTag("", "NewPassword");
                    a1.text(((mktvsmart.screen.dataconvert.model.DataConvertControlModel)a.get(1)).GetPassword());
                    a1.endTag("", "NewPassword");
                    a1.endTag("", "parm");
                    a0 = a1;
                }
            }
            else
            {
                Object a21 = a.iterator();
                a0 = a1;
                while(((java.util.Iterator)a21).hasNext())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertControlModel a22 = (mktvsmart.screen.dataconvert.model.DataConvertControlModel)((java.util.Iterator)a21).next();
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "parm");
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "PasswordLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a22.GetPswLockSwitch())).toString());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "PasswordLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ServiceLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a22.GetServiceLockSwitch())).toString());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ServiceLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "InstallLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a22.GetInstallLockSwitch())).toString());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "InstallLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "EditLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a22.GetEditChannelLockSwitch())).toString());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "EditLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "SettingsLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a22.GetSettingsLockSwitch())).toString());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "SettingsLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "NetworkLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a22.GetNetworkLockSwitch())).toString());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "NetworkLock");
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "AgeRating");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a22.GetAgeRatingSwitch())).toString());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "AgeRating");
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "parm");
                }
            }
        }
    }
    public void InstanceOfDataConvertTimeModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertTimeModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            label1: {
                if (i == 1021)
                {
                    break label1;
                }
                if (i == 1022)
                {
                    break label1;
                }
                if (i == 1023)
                {
                    break label1;
                }
                if (i != 1020)
                {
                    a0 = a1;
                    //break label0;   // write empty command request
                    return;
                }
            }
            Object a23 = a.iterator();
            a0 = a1;
            while(((java.util.Iterator)a23).hasNext())
            {
                mktvsmart.screen.dataconvert.model.DataConvertTimeModel a24 = (mktvsmart.screen.dataconvert.model.DataConvertTimeModel)((java.util.Iterator)a23).next();
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "parm");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TimerIndex");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a24.GetTimerIndex())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TimerIndex");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TimerProgramId");
                ((org.xmlpull.v1.XmlSerializer)a0).text(a24.getProgramId());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TimerProgramId");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TimerMonth");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a24.GetTimeMonth())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TimerMonth");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TimerDay");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a24.GetTimeDay())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TimerDay");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TimerStartHour");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a24.GetStartHour())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TimerStartHour");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TimerStartMin");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a24.GetStartMin())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TimerStartMin");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TimerEndHour");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a24.GetEndHour())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TimerEndHour");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TimerEndMin");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a24.GetEndMin())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TimerEndMin");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TimerRepeat");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a24.GetTimerRepeat())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TimerRepeat");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TimerStatus");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a24.GetTimerStatus())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TimerStatus");
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TimerEventID");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a24.getEventId())).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TimerEventID");
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "parm");
            }
        }
    }
    public void InstanceOfDataConvertEditChannelLockModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertEditChannelLockModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            if (i != 1003)
            {
                a0 = a1;
            }
            else
            {
                a1.startTag("", "parm");
                Object a25 = a.iterator();
                int i0 = 0;
                boolean b = true;
                a0 = a1;
                while(((java.util.Iterator)a25).hasNext())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertEditChannelLockModel a26 = (mktvsmart.screen.dataconvert.model.DataConvertEditChannelLockModel)((java.util.Iterator)a25).next();
                    if (b)
                    {
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TvState");
                        ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a26.getmChannelType())).toString());
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TvState");
                        b = false;
                    }
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ProgramId");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(a26.getProgramId());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ProgramId");
                    i0 = i0 + 1;
                }
                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TotalNum");
                ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(i0)).toString());
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TotalNum");
                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "parm");
            }
        }
    }

    public void InstanceOfDataConvertFavChannelModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            if (i != 1011)
            {
                a0 = a1;
            }
            else
            {
                a1.startTag("", "TvState");
                a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel)a.get(0)).getChannelTpye())).toString());
                a1.endTag("", "TvState");
                a1.startTag("", "SelectListType");
                a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel)a.get(0)).getSelectListType())).toString());
                a1.endTag("", "SelectListType");
                Object a27 = a.iterator();
                a0 = a1;
                while(((java.util.Iterator)a27).hasNext())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel a28 = (mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel)((java.util.Iterator)a27).next();
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "parm");
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ProgramId");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(a28.GetProgramId());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ProgramId");
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "parm");
                }
            }
        }
    }

    public void InstanceOfDataConvertChannelModel(java.util.List a, Object a0, org.xmlpull.v1.XmlSerializer a1, Object a3, int i) {
        if (a3.getClass().getName() == mktvsmart.screen.dataconvert.model.DataConvertChannelModel.class.getName())
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            switch(i){
                case 1100: {
                    mktvsmart.screen.dataconvert.model.DataConvertChannelModel a29 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0);
                    a1.startTag("", "parm");
                    a1.startTag("", "TvState");
                    a1.text(String.valueOf(a29.getChannelTpye()));
                    a1.endTag("", "TvState");
                    a1.startTag("", "ProgramId");
                    a1.text(a29.GetProgramId());
                    a1.endTag("", "ProgramId");
                    a1.endTag("", "parm");
                    a0 = a1;
                    break;
                }
                case 1005: {
                    Object a30 = a.iterator();
                    int i1 = 0;
                    boolean b0 = true;
                    a0 = a1;
                    while(((java.util.Iterator)a30).hasNext())
                    {
                        mktvsmart.screen.dataconvert.model.DataConvertChannelModel a31 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a30).next();
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "parm");
                        if (b0)
                        {
                            ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TvState");
                            ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a31.getChannelTpye())).toString());
                            ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TvState");
                            ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "MoveToPosition");
                            ((org.xmlpull.v1.XmlSerializer)a0).text(a31.getMoveToPosition());
                            ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "MoveToPosition");
                            b0 = false;
                        }
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ProgramId");
                        ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf((Object)a31.GetProgramId())).toString());
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ProgramId");
                        i1 = i1 + 1;
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "parm");
                    }
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TotalNum");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(i1)).toString());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TotalNum");
                    break;
                }
                case 1004: {
                    a1.startTag("", "TvState");
                    a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).getChannelTpye())).toString());
                    a1.endTag("", "TvState");
                    a1.startTag("", "FavMark");
                    a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).GetFavMark())).toString());
                    a1.endTag("", "FavMark");
                    java.util.Iterator a32 = ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).mfavGroupIDs.iterator();
                    Object a33 = a;
                    String s0 = "";
                    a0 = a1;
                    Object a34 = a32;
                    while(((java.util.Iterator)a34).hasNext())
                    {
                        int i2 = ((Integer)((java.util.Iterator)a34).next()).intValue();
                        s0 = new StringBuilder(String.valueOf((Object)s0)).append(i2).append(":").toString();
                    }
                    if (s0.length() >= 0)
                    {
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "FavorGroupID");
                        ((org.xmlpull.v1.XmlSerializer)a0).text(s0);
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "FavorGroupID");
                    }
                    switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbPlatform()){
                        case 30: case 31: case 32: case 71: case 72: case 74: {
                            Object a35 = ((java.util.List)a33).iterator();
                            while(((java.util.Iterator)a35).hasNext())
                            {
                                mktvsmart.screen.dataconvert.model.DataConvertChannelModel a36 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a35).next();
                                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ProgramId");
                                ((org.xmlpull.v1.XmlSerializer)a0).text(a36.GetProgramId());
                                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ProgramId");
                            }
                            //break label0; // write full channelModelList done
                            return;
                        }
                        default: {
                            Object a37 = ((java.util.List)a33).iterator();
                            int i3 = 0;
                            while(((java.util.Iterator)a37).hasNext())
                            {
                                mktvsmart.screen.dataconvert.model.DataConvertChannelModel a38 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a37).next();
                                ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ProgramId");
                                ((org.xmlpull.v1.XmlSerializer)a0).text(a38.GetProgramId());
                                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ProgramId");
                                i3 = i3 + 1;
                            }
                            ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TotalNum");
                            ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(i3)).toString());
                            ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TotalNum");
                            //break label0; // write full channelModelList done
                            return;
                        }
                    }
                }
                case 1002: {
                    a1.startTag("", "TvState");
                    a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).getChannelTpye())).toString());
                    a1.endTag("", "TvState");
                    Object a39 = a.iterator();
                    int i4 = 0;
                    a0 = a1;
                    while(((java.util.Iterator)a39).hasNext())
                    {
                        mktvsmart.screen.dataconvert.model.DataConvertChannelModel a40 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a39).next();
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "parm");
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ProgramId");
                        ((org.xmlpull.v1.XmlSerializer)a0).text(a40.GetProgramId());
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ProgramId");
                        i4 = i4 + 1;
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "parm");
                    }
                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TotalNum");
                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(i4)).toString());
                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TotalNum");
                    break;
                }
                case 1001: {
                    Object a41 = a.iterator();
                    a0 = a1;
                    while(((java.util.Iterator)a41).hasNext())
                    {
                        mktvsmart.screen.dataconvert.model.DataConvertChannelModel a42 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a41).next();
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "parm");
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TvState");
                        ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a42.getChannelTpye())).toString());
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TvState");
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ProgramId");
                        ((org.xmlpull.v1.XmlSerializer)a0).text(a42.GetProgramId());
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ProgramId");
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ProgramName");
                        ((org.xmlpull.v1.XmlSerializer)a0).text(a42.getProgramName());
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ProgramName");
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "parm");
                    }
                    break;
                }
                case 1000: case 1009: {
                    Object a43 = a.iterator();
                    a0 = a1;
                    while(((java.util.Iterator)a43).hasNext())
                    {
                        mktvsmart.screen.dataconvert.model.DataConvertChannelModel a44 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a43).next();
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "parm");
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "TvState");
                        ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(a44.getChannelTpye())).toString());
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "TvState");
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ProgramId");
                        ((org.xmlpull.v1.XmlSerializer)a0).text(a44.GetProgramId());
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ProgramId");
                        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbPlatform()){
                            case 32: case 71: case 72: case 74: {
                                if (i == 1009)
                                {
                                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "iResolutionRatio");
                                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(mktvsmart.screen.vlc.TranscodeConstants.iCurResolution)).toString());
                                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "iResolutionRatio");
                                    ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "iBitrate");
                                    ((org.xmlpull.v1.XmlSerializer)a0).text(new StringBuilder(String.valueOf(mktvsmart.screen.vlc.TranscodeConstants.iCurBitrate)).toString());
                                    ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "iBitrate");
                                }
                            }
                            default: {
                                ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "parm");
                            }
                        }
                    }
                    break;
                }
                case 104: {
                    mktvsmart.screen.dataconvert.model.DataConvertChannelModel a45 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0);
                    a1.startTag("", "parm");
                    a1.startTag("", "ProgramId");
                    a1.text(a45.GetProgramId());
                    a1.endTag("", "ProgramId");
                    a1.endTag("", "parm");
                    a0 = a1;
                    break;
                }
                case 5: {
                    Object a46 = a.iterator();
                    a0 = a1;
                    while(((java.util.Iterator)a46).hasNext())
                    {
                        mktvsmart.screen.dataconvert.model.DataConvertChannelModel a47 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a46).next();
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "parm");
                        ((org.xmlpull.v1.XmlSerializer)a0).startTag("", "ProgramId");
                        ((org.xmlpull.v1.XmlSerializer)a0).text(a47.GetProgramId());
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "ProgramId");
                        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "parm");
                    }
                    break;
                }
                case 0: {
                    a1.startTag("", "parm");
                    a1.startTag("", "FromIndex");
                    a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).GetProgramIndex())).toString());
                    a1.endTag("", "FromIndex");
                    a1.startTag("", "ToIndex");
                    a1.text(new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(1)).GetProgramIndex())).toString());
                    a1.endTag("", "ToIndex");
                    a1.endTag("", "parm");
                    a0 = a1;
                    break;
                }
                default: {
                    a0 = a1;
                }
            }
        }
    }

//    java.util.List a;
//    Object a0 = null;
//    org.xmlpull.v1.XmlSerializer a1;
//    java.io.StringWriter a2;
//    Object a3;
//    int i;


    public String serializeAndroid(java.util.List a, int i) {



        Object a0 = null;
        org.xmlpull.v1.XmlSerializer a1 = new org.xmlpull.v1.XmlSerializer();
        java.io.StringWriter a2 = new java.io.StringWriter();
        a1.setOutput((java.io.Writer)a2);
        a1.startDocument("UTF-8", Boolean.valueOf(true));
        a1.startTag("", "Command");
        label0: if (a != null)
        {
            Object a3 = a.get(0);

        }
        else
        {
            a1.attribute("", "request", new StringBuilder(String.valueOf(i)).toString());
            a0 = a1;
        }
        ((org.xmlpull.v1.XmlSerializer)a0).endTag("", "Command");
        ((org.xmlpull.v1.XmlSerializer)a0).endDocument();
        return a2.toString();

    }



    public SerializedDataModel serialize(java.util.List list, int cmdId) {

        SerializedDataModel returnData = new SerializedDataModel();
        List a = list;
        int i = cmdId;

        // http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_18_005.htm#mj15d0d171e564cb7b811b0a17ddc1cc2b
        // cursor variante

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        StringWriter out2string = new StringWriter();
        //ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLStreamWriter writer = null;

        try {
            writer = factory.createXMLStreamWriter( out2string );
            // Android 2 JavaSE
            org.xmlpull.v1.XmlSerializer a1 = new org.xmlpull.v1.XmlSerializer();
            a1.setWriter(writer);

            // Der XML-Header wird erzeugt
            writer.writeStartDocument("UTF-8", "1.0");
            {
                // Zuerst wird das Wurzelelement mit Attribut geschrieben
                writer.writeStartElement("Command");
                {

                    if(list == null) {

                        // Empty Command Request
                        writer.writeAttribute("request", String.valueOf(cmdId));

                        returnData.isEmpty = true;
                        returnData.dataType = null;

                    }
                    else
                    {
                        Object a3 = list.get(0);
                        Object a0 = a1;

                        if (a3.getClass().getName() == DataConvertChannelModel.class.getName()) {
                            InstanceOfDataConvertChannelModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertFavChannelModel.class.getName()) {
                            InstanceOfDataConvertFavChannelModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertEditChannelLockModel.class.getName()) {
                            InstanceOfDataConvertEditChannelLockModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertTimeModel.class.getName()) {
                            InstanceOfDataConvertTimeModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertControlModel.class.getName()) {
                            InstanceOfDataConvertControlModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertUpdateModel.class.getName()) {
                            InstanceOfDataConvertUpdateModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertDebugModel.class.getName()) {
                            InstanceOfDataConvertDebugModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertRcuModel.class.getName()) {
                            InstanceOfDataConvertRcuModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertFavorModel.class.getName()) {
                            InstanceOfDataConvertFavorModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertChannelTypeModel.class.getName()) {
                            InstanceOfDataConvertChannelTypeModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertInputMethodModel.class.getName()) {
                            InstanceOfDataConvertInputMethodModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertSortModel.class.getName()) {
                            InstanceOfDataConvertSortModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertOneDataModel.class.getName()) {
                            InstanceOfDataConvertOneDataModel(a,a0,a1,a3,i);
                        }
                        else if (a3.getClass().getName() == DataConvertSatModel.class.getName()) {
                            InstanceOfDataConvertSatModel(a,a0,a1,a3,i);
                        }
                        else if (a3 instanceof Map) {
                            InstanceOfMap(a,a0,a1,a3,i);
                        }
                        else if (a3 instanceof DataConvertChatMsgModel) {
                            InstanceOfDataConvertChatMsgModel(a,a0,a1,a3,i);
                        }
                        else if (a3 instanceof GsChatSetting) {
                            InstanceOfGsChatSetting(a,a0,a1,a3,i);
                        }
                        else if (a3 instanceof GsChatUser) {
                            InstanceOfGsChatUser(a,a0,a1,a3,i);
                        }
                        else if (a3 instanceof DataConvertUsernameModel && i == 1105) {
                            InstanceOfDataConvertUsernameModel(a,a0,a1,a3,i);
                        }

                        returnData.dataType = a3;


                    }

                }
                writer.writeEndElement();
            }
            writer.writeEndDocument();
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        returnData.serializedDataAsString = out2string.toString();
        return returnData;
        //return out2string.toString();
    }
}
