package mktvsmart.screen.dataconvert.parser;

import android.os.Message;

public class JsonParser implements mktvsmart.screen.dataconvert.parser.DataParser {
    final private static String ARRAY = "array";

    public JsonParser()
    {
        super();
    }

//    public java.util.List parse(java.io.InputStream a, int i)
    public java.util.List parse(Message a, int i)
    {
        /*java.util.ArrayList a0 = null;
        com.alibaba.fastjson.JSONArray a1 = com.alibaba.fastjson.JSON.parseArray(mktvsmart.screen.common.tools.CommonHelper.getStrFromInputSteam(a));
        switch(i){
            case 27: {
                java.util.ArrayList a2 = new java.util.ArrayList();
                int i0 = 0;
                while(i0 < a1.size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertUsernameModel a3 = new mktvsmart.screen.dataconvert.model.DataConvertUsernameModel();
                    a3.setUsername(a1.getJSONObject(i0).getString("Username"));
                    ((java.util.List)(Object)a2).add((Object)a3);
                    i0 = i0 + 1;
                }
                return (java.util.List)(Object)a2;
            }
            case 26: {
                java.util.ArrayList a4 = new java.util.ArrayList();
                int i1 = 0;
                while(i1 < a1.size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertGChatChannelInfoModel a5 = new mktvsmart.screen.dataconvert.model.DataConvertGChatChannelInfoModel();
                    com.alibaba.fastjson.JSONObject a6 = a1.getJSONObject(i1);
                    a5.setSatAngle(a6.getString("Angle"));
                    a5.setTp(Integer.parseInt(a6.getString("Tp")));
                    a5.setServiceId(Integer.parseInt(a6.getString("ServiceId")));
                    a5.setEpg(a6.getString("EPG"));
                    ((java.util.List)(Object)a4).add((Object)a5);
                    i1 = i1 + 1;
                }
                return (java.util.List)(Object)a4;
            }
            case 25: {
                java.util.ArrayList a7 = new java.util.ArrayList();
                int i2 = 0;
                while(i2 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a8 = a1.getJSONObject(i2);
                    mktvsmart.screen.gchat.bean.GsChatSetting a9 = mktvsmart.screen.gchat.bean.GsChatSetting.getInstance();
                    a9.setShowWindow(Integer.parseInt(a8.getString("ShowWindow")));
                    a9.setWindowSize(Integer.parseInt(a8.getString("WindowSize")));
                    a9.setWindowPosition(Integer.parseInt(a8.getString("WindowPosition")));
                    a9.setWindowTransparency(Integer.parseInt(a8.getString("WindowTransparency")));
                    ((java.util.List)(Object)a7).add((Object)a9);
                    i2 = i2 + 1;
                }
                return (java.util.List)(Object)a7;
            }
            case 24: {
                java.util.ArrayList a10 = new java.util.ArrayList();
                int i3 = 0;
                while(i3 < a1.size())
                {
                    mktvsmart.screen.gchat.bean.GsChatUser a11 = new mktvsmart.screen.gchat.bean.GsChatUser();
                    com.alibaba.fastjson.JSONObject a12 = a1.getJSONObject(i3);
                    a11.setUserID(Integer.parseInt(a12.getString("USERID")));
                    a11.setUsername(a12.getString("Username"));
                    ((java.util.List)(Object)a10).add((Object)a11);
                    i3 = i3 + 1;
                }
                return (java.util.List)(Object)a10;
            }
            case 23: {
                java.util.ArrayList a13 = new java.util.ArrayList();
                int i4 = 0;
                while(i4 < a1.size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel a14 = new mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel();
                    com.alibaba.fastjson.JSONObject a15 = a1.getJSONObject(i4);
                    a14.setTimestamp(Long.parseLong(a15.getString("Timestamp")));
                    int i5 = Integer.parseInt(a15.getString("USERID"));
                    a14.setUserID(i5);
                    if (i5 != mktvsmart.screen.gchat.bean.GsChatSetting.getInstance().getUserId())
                    {
                        a14.setMsgType(0);
                    }
                    else
                    {
                        a14.setMsgType(1);
                    }
                    a14.setUsername(a15.getString("Username"));
                    a14.setContent(a15.getString("Content"));
                    ((java.util.List)(Object)a13).add((Object)a14);
                    i4 = i4 + 1;
                }
                return (java.util.List)(Object)a13;
            }
            case 22: {
                java.util.ArrayList a16 = new java.util.ArrayList();
                mktvsmart.screen.gchat.bean.GsChatUser a17 = null;
                int i6 = 0;
                while(i6 < a1.size())
                {
                    mktvsmart.screen.gchat.bean.GsChatRoomInfo a18 = new mktvsmart.screen.gchat.bean.GsChatRoomInfo();
                    com.alibaba.fastjson.JSONObject a19 = a1.getJSONObject(i6);
                    a18.setEventTitle(a19.getString("EventTitle"));
                    a18.setOnlineNum(Integer.parseInt(a19.getString("OnlineUserNum")));
                    a18.setRoomID(Integer.parseInt(a19.getString("RoomId")));
                    if (a19.containsKey((Object)"UserInfo"))
                    {
                        a17 = new mktvsmart.screen.gchat.bean.GsChatUser();
                        com.alibaba.fastjson.JSONArray a20 = new com.alibaba.fastjson.JSONArray();
                        com.alibaba.fastjson.JSONArray a21 = a19.getJSONArray("UserInfo");
                        int i7 = 0;
                        while(i7 < a21.size())
                        {
                            com.alibaba.fastjson.JSONObject a22 = new com.alibaba.fastjson.JSONObject(true);
                            com.alibaba.fastjson.JSONObject a23 = a21.getJSONObject(i7);
                            a17.setUserID(Integer.parseInt(a23.getString("USERID")));
                            a17.setUsername(a23.getString("Username"));
                            i7 = i7 + 1;
                        }
                    }
                    a18.getUserList().add((Object)a17);
                    ((java.util.List)(Object)a16).add((Object)a18);
                    i6 = i6 + 1;
                }
                return (java.util.List)(Object)a16;
            }
            case 21: {
                java.util.ArrayList a24 = new java.util.ArrayList();
                int i8 = 0;
                while(i8 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a25 = a1.getJSONObject(i8);
                    mktvsmart.screen.gchat.bean.GsChatSetting a26 = mktvsmart.screen.gchat.bean.GsChatSetting.getInstance();
                    a26.setSerialNumber(a25.getString("MySN"));
                    a26.setUsername(a25.getString("MyUsername"));
                    a26.setUserId(Integer.parseInt(a25.getString("USERID")));
                    ((java.util.List)(Object)a24).add((Object)a26);
                    i8 = i8 + 1;
                }
                return (java.util.List)(Object)a24;
            }
            case 20: {
                java.util.ArrayList a27 = new java.util.ArrayList();
                int i9 = 0;
                while(i9 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a28 = a1.getJSONObject(i9);
                    mktvsmart.screen.dataconvert.model.DataConvertPvrInfoModel a29 = new mktvsmart.screen.dataconvert.model.DataConvertPvrInfoModel();
                    a29.setProgramName(a28.getString("pvrname"));
                    a29.setmPvrId(a28.getIntValue("pvr_uid"));
                    a29.setmPvrDuration(a28.getString("pvr_duration"));
                    a29.setmPvrTime(a28.getString("pvr_time"));
                    a29.setmPvrType(a28.getIntValue("pvr_type"));
                    a29.setmPvrCrypto(a28.getIntValue("crypto"));
                    ((java.util.List)(Object)a27).add((Object)a29);
                    i9 = i9 + 1;
                }
                return (java.util.List)(Object)a27;
            }
            case 19: {
                java.util.ArrayList a30 = new java.util.ArrayList();
                int i10 = 0;
                while(i10 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a31 = a1.getJSONObject(i10);
                    mktvsmart.screen.dataconvert.model.DataConvertTpModel a32 = new mktvsmart.screen.dataconvert.model.DataConvertTpModel();
                    a32.setTpIndex(a31.getIntValue("TpIndex"));
                    a32.setSatIndex(a31.getIntValue("SatIndex"));
                    a32.setSymRate(a31.getIntValue("SystemRate"));
                    int i11 = a31.getIntValue("Pol");
                    a32.setPol((char)((i11 != 0) ? (i11 != 1) ? (i11 != 2) ? (i11 != 3) ? 104 : 114 : 108 : 118 : 104));
                    a32.setFec(a31.getIntValue("Fec"));
                    a32.setFreq(a31.getIntValue("Freq"));
                    ((java.util.List)(Object)a30).add((Object)a32);
                    i10 = i10 + 1;
                }
                return (java.util.List)(Object)a30;
            }
            case 18: {
                java.util.ArrayList a33 = new java.util.ArrayList();
                int i12 = 0;
                while(i12 < a1.size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertSatModel a34 = new mktvsmart.screen.dataconvert.model.DataConvertSatModel();
                    com.alibaba.fastjson.JSONObject a35 = a1.getJSONObject(i12);
                    a34.setmSatName(a35.getString("SatName"));
                    a34.setmSatIndex(a35.getIntValue("SatNo"));
                    a34.setmSatAngle(a35.getIntValue("SatAngle"));
                    a34.setmSatDir(a35.getIntValue("SatDir"));
                    ((java.util.List)(Object)a33).add((Object)a34);
                    i12 = i12 + 1;
                }
                return (java.util.List)(Object)a33;
            }
            case 17: {
                java.util.ArrayList a36 = new java.util.ArrayList();
                int i13 = 0;
                while(i13 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a37 = a1.getJSONObject(i13);
                    String s = new String(a37.getString("cur_channel_list_type"));
                    mktvsmart.screen.GMScreenGlobalInfo.setmMaxPasswordNum(a37.getIntValue("max_password_num"));
                    mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.setCurrent_channel_tv_radio_type(a37.getIntValue("cur_channel_type"));
                    mktvsmart.screen.GMScreenGlobalInfo.setmPvr2ipServerSupport(a37.getIntValue("is_support_pvr2ip_server"));
                    mktvsmart.screen.GMScreenGlobalInfo.setSdsOpen(a37.getIntValue("is_sds_open"));
                    ((java.util.List)(Object)a36).add((Object)s);
                    i13 = i13 + 1;
                }
                return (java.util.List)(Object)a36;
            }
            case 16: {
                java.util.ArrayList a38 = new java.util.ArrayList();
                int i14 = 0;
                while(i14 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a39 = a1.getJSONObject(i14);
                    java.util.HashMap a40 = new java.util.HashMap();
                    ((java.util.Map)(Object)a40).put((Object)"success", (Object)Integer.valueOf(a39.getIntValue("success")));
                    ((java.util.Map)(Object)a40).put((Object)"url", (Object)Integer.valueOf(a39.getIntValue("url")));
                    ((java.util.Map)(Object)a40).put((Object)"errormsg", (Object)a39.getString("errormsg"));
                    ((java.util.List)(Object)a38).add((Object)a40);
                    i14 = i14 + 1;
                }
                return (java.util.List)(Object)a38;
            }
            case 15: {
                java.util.ArrayList a41 = new java.util.ArrayList();
                int i15 = 0;
                while(i15 < a1.size())
                {
                    String s0 = a1.getJSONObject(i15).getString("Data");
                    if (s0 != null)
                    {
                        ((java.util.List)(Object)a41).add((Object)s0);
                    }
                    i15 = i15 + 1;
                }
                return (java.util.List)(Object)a41;
            }
            case 14: {
                java.util.ArrayList a42 = new java.util.ArrayList();
                int i16 = 0;
                while(i16 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a43 = a1.getJSONObject(i16);
                    mktvsmart.screen.dataconvert.model.DataConvertStbInfoModel a44 = new mktvsmart.screen.dataconvert.model.DataConvertStbInfoModel();
                    a44.setmStbStatus(a43.getIntValue("StbStatus"));
                    a44.setmProductName(a43.getString("ProductName"));
                    a44.setmSoftwareVersion(a43.getString("SoftwareVersion"));
                    a44.setmSerialNumber(a43.getString("SerialNumber"));
                    a44.setmChannelNum(a43.getIntValue("ChannelNum"));
                    a44.setmMaxNumOfPrograms(a43.getIntValue("MaxNumOfPrograms"));
                    ((java.util.List)(Object)a42).add((Object)a44);
                    i16 = i16 + 1;
                }
                return (java.util.List)(Object)a42;
            }
            case 13: {
                java.util.ArrayList a45 = new java.util.ArrayList();
                int i17 = 0;
                while(i17 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a46 = a1.getJSONObject(i17);
                    mktvsmart.screen.dataconvert.model.DataConvertSortModel a47 = new mktvsmart.screen.dataconvert.model.DataConvertSortModel();
                    a47.setmSortType(a46.getIntValue("SortType"));
                    a47.setmMacroFlag(a46.getIntValue("MacroFlag"));
                    if (a46.containsKey((Object)"SortTypeList"))
                    {
                        String[] a48 = a46.get((Object)"SortTypeList").toString().split(",");
                        if (a48 != null && a48.length > 0)
                        {
                            java.util.ArrayList a49 = new java.util.ArrayList();
                            int i18 = 0;
                            while(i18 < a48.length)
                            {
                                a49.add((Object)a48[i18]);
                                i18 = i18 + 1;
                            }
                            a47.setSortTypeList(a49);
                        }
                    }
                    ((java.util.List)(Object)a45).add((Object)a47);
                    i17 = i17 + 1;
                }
                return (java.util.List)(Object)a45;
            }
            case 12: {
                java.util.ArrayList a50 = new java.util.ArrayList();
                int i19 = 0;
                while(i19 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a51 = a1.getJSONObject(i19);
                    mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel a52 = new mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel();
                    mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.setCurrent_channel_tv_radio_type(a51.getIntValue("CurChannelType"));
                    a52.setTvRadioKeyPress(a51.getIntValue("tv_radio_key_press"));
                    ((java.util.List)(Object)a50).add((Object)a52);
                    i19 = i19 + 1;
                }
                return (java.util.List)(Object)a50;
            }
            case 11: {
                java.util.ArrayList a53 = new java.util.ArrayList();
                int i20 = 0;
                while(i20 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a54 = a1.getJSONObject(i20);
                    mktvsmart.screen.dataconvert.model.DataConvertControlModel a55 = new mktvsmart.screen.dataconvert.model.DataConvertControlModel();
                    a55.setSleepSwitch(a54.getIntValue("SleepSwitch"));
                    a55.setSleepTime(a54.getIntValue("SleepTime"));
                    a55.SetIsLockScreen(a54.getIntValue("ScreenLock"));
                    a55.SetPowerOff(a54.getIntValue("PowerMode"));
                    ((java.util.List)(Object)a53).add((Object)a55);
                    i20 = i20 + 1;
                }
                return (java.util.List)(Object)a53;
            }
            case 10: {
                java.util.ArrayList a56 = new java.util.ArrayList();
                int i21 = 0;
                while(i21 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a57 = a1.getJSONObject(i21);
                    mktvsmart.screen.dataconvert.model.DataConvertFavorModel.favorNum = a57.getIntValue("favMaxNum");
                    if (a57.containsKey((Object)"favGroupNames"))
                    {
                        com.alibaba.fastjson.JSONArray a58 = a57.getJSONArray("favGroupNames");
                        int i22 = 0;
                        while(i22 < a58.size())
                        {
                            mktvsmart.screen.dataconvert.model.DataConvertFavorModel a59 = new mktvsmart.screen.dataconvert.model.DataConvertFavorModel();
                            a59.SetFavorName(a58.getString(i22));
                            ((java.util.List)(Object)a56).add((Object)a59);
                            i22 = i22 + 1;
                        }
                    }
                    if (a57.containsKey((Object)"favGroupIds"))
                    {
                        com.alibaba.fastjson.JSONArray a60 = a57.getJSONArray("favGroupIds");
                        int i23 = 0;
                        while(i23 < a60.size())
                        {
                            int i24 = a60.getIntValue(i23);
                            ((mktvsmart.screen.dataconvert.model.DataConvertFavorModel)((java.util.List)(Object)a56).get(i23)).setFavorTypeID(i24);
                            i23 = i23 + 1;
                        }
                    }
                    i21 = i21 + 1;
                }
                return (java.util.List)(Object)a56;
            }
            case 9: {
                a0 = new java.util.ArrayList();
                int i25 = 0;
                while(i25 < a1.size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertTimeModel a61 = new mktvsmart.screen.dataconvert.model.DataConvertTimeModel();
                    com.alibaba.fastjson.JSONObject a62 = a1.getJSONObject(i25);
                    mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbMonth = a62.getIntValue("StbMonth");
                    mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbDay = a62.getIntValue("StbDay");
                    mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbHour = a62.getIntValue("StbHour");
                    mktvsmart.screen.dataconvert.model.DataConvertTimeModel.stbMin = a62.getIntValue("StbMin");
                    ((java.util.List)(Object)a0).add((Object)a61);
                    i25 = i25 + 1;
                }
                break;
            }
            case 8: {
                java.util.ArrayList a63 = new java.util.ArrayList();
                int i26 = 0;
                while(i26 < a1.size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertChannelModel a64 = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
                    com.alibaba.fastjson.JSONObject a65 = a1.getJSONObject(i26);
                    a64.setIsProgramHd(a65.getIntValue("LockedChannelIndex"));
                    a64.setChannelTpye(a65.getIntValue("TVState"));
                    ((java.util.List)(Object)a63).add((Object)a64);
                    i26 = i26 + 1;
                }
                return (java.util.List)(Object)a63;
            }
            case 7: {
                a0 = new java.util.ArrayList();
                int i27 = 0;
                while(i27 < a1.size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertTimeModel a66 = new mktvsmart.screen.dataconvert.model.DataConvertTimeModel();
                    com.alibaba.fastjson.JSONObject a67 = a1.getJSONObject(i27);
                    mktvsmart.screen.dataconvert.model.DataConvertTimeModel.isConfirm = Integer.parseInt(a67.getString("Confirm"));
                    a66.SetTimerIndex(a67.getIntValue("TimerIndex"));
                    ((java.util.List)(Object)a0).add((Object)a66);
                    i27 = i27 + 1;
                }
                break;
            }
            case 6: {
                java.util.ArrayList a68 = new java.util.ArrayList();
                int i28 = 0;
                int i29 = 0;
                while(i28 < a1.size())
                {
                    com.alibaba.fastjson.JSONObject a69 = a1.getJSONObject(i28);
                    mktvsmart.screen.GsEPGTableChannel a70 = new mktvsmart.screen.GsEPGTableChannel();
                    a70.setProgNo(a69.getIntValue("prog_no"));
                    a70.setOriginalNetworkID(a69.getIntValue("original_net_id"));
                    a70.setTransportStreamID(a69.getIntValue("transport_stream_id"));
                    int i30 = (byte)a69.getIntValue("today_date");
                    a70.setTodayDate((byte)i30);
                    a70.setCurrentEpgTime(a69.getIntValue("current_epg_time"));
                    com.alibaba.fastjson.JSONArray a71 = a69.getJSONArray("daily_epg_list");
                    int i31 = 0;
                    while(i31 < a71.size())
                    {
                        com.alibaba.fastjson.JSONObject a72 = a71.getJSONObject(i31);
                        a70.setArrayEventFieldByIndex(i29, a72.getIntValue("event_valid_num"));
                        com.alibaba.fastjson.JSONArray a73 = a72.getJSONArray("event_list");
                        int i32 = 0;
                        while(i32 < a73.size())
                        {
                            mktvsmart.screen.GsEPGEvent a74 = new mktvsmart.screen.GsEPGEvent();
                            com.alibaba.fastjson.JSONObject a75 = a73.getJSONObject(i32);
                            if (a75.containsKey((Object)"event_start_time"))
                            {
                                a74.setStartTime(a75.getString("event_start_time"));
                            }
                            if (a75.containsKey((Object)"event_end_time"))
                            {
                                a74.setEndTime(a75.getString("event_end_time"));
                            }
                            if (a75.containsKey((Object)"event_age_rating"))
                            {
                                a74.setAgeRating(a75.getIntValue("event_age_rating"));
                            }
                            if (a75.containsKey((Object)"event_timer_type"))
                            {
                                a74.setEpgTimerType(a75.getIntValue("event_timer_type"));
                            }
                            if (a75.containsKey((Object)"event_total_language"))
                            {
                                a74.setTotalEpgLanguage(a75.getIntValue("event_total_language"));
                            }
                            if (a75.containsKey((Object)"event_content_list"))
                            {
                                com.alibaba.fastjson.JSONArray a76 = a75.getJSONArray("event_content_list");
                                int i33 = 0;
                                int i34 = 0;
                                while(i34 < a76.size())
                                {
                                    com.alibaba.fastjson.JSONObject a77 = a76.getJSONObject(i34);
                                    if (a77.containsKey((Object)"event_titile_lang_code"))
                                    {
                                        a74.setTitleLanCode(i33, a77.getIntValue("event_titile_lang_code"));
                                    }
                                    if (a77.containsKey((Object)"event_sub_titile_lang_code"))
                                    {
                                        a74.setSubtitleLanCode(i33, a77.getIntValue("event_sub_titile_lang_code"));
                                    }
                                    if (a77.containsKey((Object)"event_desc_lang_code"))
                                    {
                                        a74.setDescLanCode(i33, a77.getIntValue("event_desc_lang_code"));
                                    }
                                    if (a77.containsKey((Object)"event_titile_len"))
                                    {
                                        a74.setTitleLen(i33, a77.getIntValue("event_titile_len"));
                                    }
                                    if (a77.containsKey((Object)"event_sub_titile_len"))
                                    {
                                        a74.setSubtitleLen(i33, a77.getIntValue("event_sub_titile_len"));
                                    }
                                    if (a77.containsKey((Object)"event_desc_len"))
                                    {
                                        a74.setDescLen(i33, a77.getIntValue("event_desc_len"));
                                    }
                                    if (a77.containsKey((Object)"event_title"))
                                    {
                                        a74.setEventTitle(i33, a77.getString("event_title"));
                                    }
                                    if (a77.containsKey((Object)"event_sub_title"))
                                    {
                                        a74.setEventSubTitle(i33, a77.getString("event_sub_title"));
                                    }
                                    if (a77.containsKey((Object)"event_desc"))
                                    {
                                        a74.setEventDesc(i33, a77.getString("event_desc"));
                                        i33 = i33 + 1;
                                    }
                                    i34 = i34 + 1;
                                }
                            }
                            a70.getEpgDayByIndex(i29).getArrayList().add((Object)a74);
                            i32 = i32 + 1;
                        }
                        i29 = i29 + 1;
                        i31 = i31 + 1;
                    }
                    ((java.util.List)(Object)a68).add((Object)a70);
                    i28 = i28 + 1;
                }
                return (java.util.List)(Object)a68;
            }
            case 3: {
                java.util.ArrayList a78 = new java.util.ArrayList();
                int i35 = 0;
                while(i35 < a1.size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertUpdateModel a79 = new mktvsmart.screen.dataconvert.model.DataConvertUpdateModel();
                    com.alibaba.fastjson.JSONObject a80 = a1.getJSONObject(i35);
                    a79.SetCustomerId(a80.getIntValue("CustomerId"));
                    a79.SetModelId(a80.getIntValue("HardwareId"));
                    a79.SetVersionId(a80.getIntValue("VersionId"));
                    ((java.util.List)(Object)a78).add((Object)a79);
                    i35 = i35 + 1;
                }
                return (java.util.List)(Object)a78;
            }
            case 2: {
                java.util.ArrayList a81 = new java.util.ArrayList();
                int i36 = 0;
                while(i36 < a1.size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertControlModel a82 = new mktvsmart.screen.dataconvert.model.DataConvertControlModel();
                    a82.SetPowerOff(-1);
                    com.alibaba.fastjson.JSONObject a83 = a1.getJSONObject(i36);
                    if (a83.containsKey((Object)"Password"))
                    {
                        a82.SetPassword(a83.getString("Password"));
                    }
                    a82.SetPswLockSwitch(a83.getIntValue("PasswordLock"));
                    if (a83.containsKey((Object)"ServiceLock"))
                    {
                        a82.SetServiceLockSwitch(a83.getIntValue("ServiceLock"));
                    }
                    a82.SetInstallLockSwitch(a83.getIntValue("InstallationLock"));
                    a82.SetEditChannelLockSwitch(a83.getIntValue("EditChannelLock"));
                    a82.SetSettingsLockSwitch(a83.getIntValue("SettingsLock"));
                    if (a83.containsKey((Object)"NetworkLock"))
                    {
                        a82.SetNetworkLockSwitch(a83.getIntValue("NetworkLock"));
                    }
                    a82.SetAgeRatingSwitch(a83.getIntValue("AgeRating"));
                    if (a83.containsKey((Object)"IsLockScreen"))
                    {
                        a82.SetIsLockScreen(a83.getIntValue("IsLockScreen"));
                    }
                    a82.SetPowerOff(a83.getIntValue("PowerMode"));
                    ((java.util.List)(Object)a81).add((Object)a82);
                    i36 = i36 + 1;
                }
                return (java.util.List)(Object)a81;
            }
            case 1: case 4: {
                a0 = new java.util.ArrayList();
                int i37 = 0;
                while(i37 < a1.size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertTimeModel a84 = new mktvsmart.screen.dataconvert.model.DataConvertTimeModel();
                    com.alibaba.fastjson.JSONObject a85 = a1.getJSONObject(i37);
                    if (a85.containsKey((Object)"Confirm"))
                    {
                        mktvsmart.screen.dataconvert.model.DataConvertTimeModel.isConfirm = Integer.parseInt(a85.getString("Confirm"));
                    }
                    a84.SetTimeProgramName(a85.getString("TimerProgramName"));
                    a84.setProgramId(a85.getString("TimerProgramSatTpId"));
                    a84.SetTimeMonth(a85.getIntValue("TimerMonth"));
                    a84.SetTimeDay(a85.getIntValue("TimerDay"));
                    a84.SetStartHour(a85.getIntValue("TimerStartHour"));
                    a84.SetStartMin(a85.getIntValue("TimerStartMin"));
                    a84.SetEndHour(a85.getIntValue("TimerEndHour"));
                    a84.SetEndMin(a85.getIntValue("TimerEndMin"));
                    a84.SetTimerRepeat(a85.getIntValue("TimerRepeat"));
                    a84.SetTimerStatus(a85.getIntValue("TimerStatus"));
                    if (a85.containsKey((Object)"TimerEventID"))
                    {
                        a84.setEventId(a85.getIntValue("TimerEventID"));
                    }
                    ((java.util.List)(Object)a0).add((Object)a84);
                    i37 = i37 + 1;
                }
                break;
            }
            case 0: {
                java.util.ArrayList a86 = new java.util.ArrayList();
                int i38 = 0;
                while(i38 < a1.size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertChannelModel a87 = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
                    com.alibaba.fastjson.JSONObject a88 = a1.getJSONObject(i38);
                    if (a88.containsKey((Object)"SatIndexSelected"))
                    {
                        mktvsmart.screen.GMScreenGlobalInfo.setmSatIndexSelected(a88.getIntValue("SatIndexSelected"));
                    }
                    a87.SetProgramId(a88.getString("ProgramId"));
                    a87.setProgramName(a88.getString("ProgramName"));
                    a87.SetProgramIndex(a88.getIntValue("ProgramIndex"));
                    if (a88.containsKey((Object)"SatName"))
                    {
                        a87.SetSatName(a88.getString("SatName"));
                    }
                    if (a88.containsKey((Object)"ProgramType"))
                    {
                        a87.SetIsProgramScramble(a88.getIntValue("ProgramType"));
                    }
                    a87.setIsProgramHd(a88.getIntValue("IsProgramHD"));
                    a87.SetFavMark(a88.getIntValue("FavMark"));
                    a87.setLockMark(a88.getIntValue("LockMark"));
                    a87.SetHaveEPG(a88.getIntValue("HaveEPG"));
                    a87.setIsPlaying(a88.getIntValue("IsPlaying"));
                    a87.setmWillBePlayed(a88.getIntValue("WillBePlayed"));
                    a87.setChannelTpye(a88.getIntValue("ChannelType"));
                    if (a88.containsKey((Object)"Frequency"))
                    {
                        a87.setFreq(a88.getIntValue("Frequency"));
                    }
                    if (a88.containsKey((Object)"Polar"))
                    {
                        String s1 = a88.getString("Polar");
                        a87.setPol((char)((s1.equals((Object)"0")) ? 104 : (s1.equals((Object)"1")) ? 118 : (s1.equals((Object)"2")) ? 108 : (s1.equals((Object)"3")) ? 114 : 104));
                    }
                    a87.setModulationSystem(a88.getIntValue("ModulationSystem"));
                    a87.setModulationType(a88.getIntValue("ModulationType"));
                    a87.setRollOff(a88.getIntValue("RollOff"));
                    a87.setPilotTones(a88.getIntValue("PilotTones"));
                    if (a88.containsKey((Object)"SymbolRate"))
                    {
                        a87.setSymRate(a88.getIntValue("SymbolRate"));
                    }
                    if (a88.containsKey((Object)"Fec"))
                    {
                        a87.setFec(a88.getIntValue("Fec"));
                    }
                    a87.setVideoPid(a88.getIntValue("VideoPid"));
                    a87.setAudioPid(a88.getString("AudioPid"));
                    a87.setTtxPid(a88.getIntValue("TtxPid"));
                    String s2 = a88.getString("SubPid");
                    if (s2.equals((Object)""))
                    {
                        s2 = null;
                    }
                    a87.setSubPid(s2);
                    a87.setPmtPid(a88.getIntValue("PmtPid"));
                    int i39 = (short)a88.getIntValue("IsTuner2");
                    a87.setIsTuner2((short)i39);
                    if (a88.containsKey((Object)"FavorGroupID"))
                    {
                        com.alibaba.fastjson.JSONArray a89 = a88.getJSONArray("FavorGroupID");
                        int i40 = 0;
                        while(i40 < a89.size())
                        {
                            a87.mfavGroupIDs.add((Object)Integer.valueOf(a89.getIntValue(i38)));
                            i40 = i40 + 1;
                        }
                    }
                    int i41 = mktvsmart.screen.GMScreenGlobalInfo.isSdsOpen();
                    label0: {
                        label1: {
                            if (i41 == 0)
                            {
                                break label1;
                            }
                            int i42 = a87.getIsTuner2();
                            if (i42 != 0)
                            {
                                break label0;
                            }
                        }
                        ((java.util.List)(Object)a86).add((Object)a87);
                    }
                    i38 = i38 + 1;
                }
                return (java.util.List)(Object)a86;
            }
            default: {
                a0 = null;
            }
        }
        return (java.util.List)(Object)a0;*/
        return null;
    }

    public SerializedDataModel serialize(java.util.List a, int i)
    { return null;}

    //public String serialize(java.util.List a, int i)
    //{
        /*com.alibaba.fastjson.JSONObject a0 = new com.alibaba.fastjson.JSONObject(true);
        com.alibaba.fastjson.JSONArray a1 = new com.alibaba.fastjson.JSONArray();
        label0: if (a != null)
        {
            a0.put("request", (Object)new StringBuilder(String.valueOf(i)).toString());
            Object a2 = a.get(0);
            if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertChannelModel.class.getName())
            {
                if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel.class.getName())
                {
                    if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertEditChannelLockModel.class.getName())
                    {
                        if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertTimeModel.class.getName())
                        {
                            if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertControlModel.class.getName())
                            {
                                if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertUpdateModel.class.getName())
                                {
                                    if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertDebugModel.class.getName())
                                    {
                                        if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertRcuModel.class.getName())
                                        {
                                            if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertFavorModel.class.getName())
                                            {
                                                if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.class.getName())
                                                {
                                                    if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertInputMethodModel.class.getName())
                                                    {
                                                        if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertSortModel.class.getName())
                                                        {
                                                            if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertOneDataModel.class.getName())
                                                            {
                                                                if (a2.getClass().getName() != mktvsmart.screen.dataconvert.model.DataConvertSatModel.class.getName())
                                                                {
                                                                    if (a2 instanceof java.util.Map)
                                                                    {
                                                                        Object a3 = a.iterator();
                                                                        while(((java.util.Iterator)a3).hasNext())
                                                                        {
                                                                            java.util.Map a4 = (java.util.Map)((java.util.Iterator)a3).next();
                                                                            java.util.Iterator a5 = a4.keySet().iterator();
                                                                            Object a6 = a4;
                                                                            Object a7 = a5;
                                                                            while(((java.util.Iterator)a7).hasNext())
                                                                            {
                                                                                String s = (String)((java.util.Iterator)a7).next();
                                                                                com.alibaba.fastjson.JSONObject a8 = new com.alibaba.fastjson.JSONObject(true);
                                                                                a8.put(s, ((java.util.Map)a6).get((Object)s));
                                                                                a1.add((Object)a8);
                                                                            }
                                                                        }
                                                                        a0.put("array", (Object)a1);
                                                                    }
                                                                    else if (a2 instanceof mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel)
                                                                    {
                                                                        if (i == 1102)
                                                                        {
                                                                            mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel a9 = (mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel)a.get(0);
                                                                            a0.put("Timestamp", (Object)Long.toString(a9.getTimestamp()));
                                                                            a0.put("Content", (Object)a9.getContent());
                                                                        }
                                                                    }
                                                                    else if (a2 instanceof mktvsmart.screen.gchat.bean.GsChatSetting)
                                                                    {
                                                                        if (i == 1104)
                                                                        {
                                                                            mktvsmart.screen.gchat.bean.GsChatSetting a10 = (mktvsmart.screen.gchat.bean.GsChatSetting)a.get(0);
                                                                            a0.put("ShowWindow", (Object)String.valueOf(a10.getSHowWindow()));
                                                                            a0.put("WindowSize", (Object)String.valueOf(a10.getWindowSize()));
                                                                            a0.put("WindowPosition", (Object)String.valueOf(a10.getWindowPosition()));
                                                                            a0.put("WindowTransparency", (Object)String.valueOf(a10.getWindowTransparency()));
                                                                        }
                                                                    }
                                                                    else if (a2 instanceof mktvsmart.screen.gchat.bean.GsChatUser)
                                                                    {
                                                                        if (i == 1103)
                                                                        {
                                                                            Object a11 = a.iterator();
                                                                            while(((java.util.Iterator)a11).hasNext())
                                                                            {
                                                                                mktvsmart.screen.gchat.bean.GsChatUser a12 = (mktvsmart.screen.gchat.bean.GsChatUser)((java.util.Iterator)a11).next();
                                                                                com.alibaba.fastjson.JSONObject a13 = new com.alibaba.fastjson.JSONObject(true);
                                                                                a13.put("USERID", (Object)Integer.toString(a12.getUserID()));
                                                                                a13.put("Username", (Object)a12.getUsername());
                                                                                if (a12.getBlock())
                                                                                {
                                                                                    a13.put("Action", (Object)"1");
                                                                                }
                                                                                else
                                                                                {
                                                                                    a13.put("Action", (Object)"0");
                                                                                }
                                                                                a1.add((Object)a13);
                                                                            }
                                                                            a0.put("array", (Object)a1);
                                                                        }
                                                                    }
                                                                    else if (a2 instanceof mktvsmart.screen.dataconvert.model.DataConvertUsernameModel && i == 1105)
                                                                    {
                                                                        a0.put("Username", (Object)String.valueOf((Object)((mktvsmart.screen.dataconvert.model.DataConvertUsernameModel)a.get(0)).getUsername()));
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    if (i == 1060)
                                                                    {
                                                                        com.alibaba.fastjson.JSONObject a14 = new com.alibaba.fastjson.JSONObject(true);
                                                                        a14.put("SatIndexSelected", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertSatModel)a.get(0)).getmSatIndex())).toString());
                                                                        a1.add((Object)a14);
                                                                    }
                                                                    a0.put("array", (Object)a1);
                                                                }
                                                            }
                                                            else
                                                            {
                                                                a0.put("data", (Object)((mktvsmart.screen.dataconvert.model.DataConvertOneDataModel)a.get(0)).getData());
                                                            }
                                                        }
                                                        else if (i == 1006)
                                                        {
                                                            a0.put("SortType", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertSortModel)a.get(0)).getmSortType())).toString());
                                                            a0.put("TvState", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertSortModel)a.get(0)).getmTvState())).toString());
                                                        }
                                                    }
                                                    else if (i == 1059)
                                                    {
                                                        a0.put("KeyCode", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertInputMethodModel)a.get(0)).getKeyCode())).toString());
                                                    }
                                                }
                                                else if (i == 1007)
                                                {
                                                    a0.put("IsFavList", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel)a.get(0)).getIsFavList())).toString());
                                                    a0.put("SelectListType", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel)a.get(0)).getSelectListType())).toString());
                                                }
                                            }
                                            else if (i == 1055)
                                            {
                                                a0.put("FavorRenamePos", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertFavorModel)a.get(0)).GetFavorIndex())).toString());
                                                a0.put("FavorNewName", (Object)new StringBuilder(String.valueOf((Object)((mktvsmart.screen.dataconvert.model.DataConvertFavorModel)a.get(0)).GetFavorName())).toString());
                                                a0.put("FavorGroupID", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertFavorModel)a.get(0)).getFavorTypeID())).toString());
                                            }
                                        }
                                        else if (i == 1040)
                                        {
                                            Object a15 = a.iterator();
                                            while(((java.util.Iterator)a15).hasNext())
                                            {
                                                mktvsmart.screen.dataconvert.model.DataConvertRcuModel a16 = (mktvsmart.screen.dataconvert.model.DataConvertRcuModel)((java.util.Iterator)a15).next();
                                                com.alibaba.fastjson.JSONObject a17 = new com.alibaba.fastjson.JSONObject(true);
                                                a17.put("KeyValue", (Object)new StringBuilder(String.valueOf(a16.getKeyValue())).toString());
                                                a1.add((Object)a17);
                                            }
                                            a0.put("array", (Object)a1);
                                        }
                                    }
                                    else
                                    {
                                        label2: {
                                            if (i == 1054)
                                            {
                                                break label2;
                                            }
                                            if (i != 9)
                                            {
                                                break label0;
                                            }
                                        }
                                        Object a18 = a.iterator();
                                        while(((java.util.Iterator)a18).hasNext())
                                        {
                                            mktvsmart.screen.dataconvert.model.DataConvertDebugModel a19 = (mktvsmart.screen.dataconvert.model.DataConvertDebugModel)((java.util.Iterator)a18).next();
                                            com.alibaba.fastjson.JSONObject a20 = new com.alibaba.fastjson.JSONObject(true);
                                            a20.put("EnableDebug", (Object)new StringBuilder(String.valueOf(a19.getDebugValue())).toString());
                                            a20.put("RequestDataFrom", (Object)new StringBuilder(String.valueOf(a19.getRequestDataFrom())).toString());
                                            a20.put("RequestDataTo", (Object)new StringBuilder(String.valueOf(a19.getRequestDataTo())).toString());
                                            a1.add((Object)a20);
                                        }
                                        a0.put("array", (Object)a1);
                                    }
                                }
                                else if (i == 1010)
                                {
                                    Object a21 = a.iterator();
                                    while(((java.util.Iterator)a21).hasNext())
                                    {
                                        mktvsmart.screen.dataconvert.model.DataConvertUpdateModel a22 = (mktvsmart.screen.dataconvert.model.DataConvertUpdateModel)((java.util.Iterator)a21).next();
                                        com.alibaba.fastjson.JSONObject a23 = new com.alibaba.fastjson.JSONObject(true);
                                        a23.put("ChannelFileLen", (Object)new StringBuilder(String.valueOf(a22.GetDataLen())).toString());
                                        a1.add((Object)a23);
                                    }
                                    a0.put("array", (Object)a1);
                                }
                            }
                            else if (i != 1051)
                            {
                                if (i != 1052)
                                {
                                    if (i == 1050)
                                    {
                                        mktvsmart.screen.dataconvert.model.DataConvertControlModel a24 = (mktvsmart.screen.dataconvert.model.DataConvertControlModel)a.get(0);
                                        a0.put("SleepSwitch", (Object)new StringBuilder(String.valueOf(a24.getSleepSwitch())).toString());
                                        if (a24.getSleepSwitch() == 1)
                                        {
                                            a0.put("SleepTime", (Object)new StringBuilder(String.valueOf(a24.getSleepTime())).toString());
                                        }
                                    }
                                }
                                else
                                {
                                    a0.put("OldPassword", (Object)((mktvsmart.screen.dataconvert.model.DataConvertControlModel)a.get(0)).GetPassword());
                                    a0.put("NewPassword", (Object)((mktvsmart.screen.dataconvert.model.DataConvertControlModel)a.get(1)).GetPassword());
                                }
                            }
                            else
                            {
                                Object a25 = a.iterator();
                                while(((java.util.Iterator)a25).hasNext())
                                {
                                    mktvsmart.screen.dataconvert.model.DataConvertControlModel a26 = (mktvsmart.screen.dataconvert.model.DataConvertControlModel)((java.util.Iterator)a25).next();
                                    com.alibaba.fastjson.JSONObject a27 = new com.alibaba.fastjson.JSONObject(true);
                                    a27.put("PasswordLock", (Object)new StringBuilder(String.valueOf(a26.GetPswLockSwitch())).toString());
                                    a27.put("ServiceLock", (Object)new StringBuilder(String.valueOf(a26.GetServiceLockSwitch())).toString());
                                    a27.put("InstallLock", (Object)new StringBuilder(String.valueOf(a26.GetInstallLockSwitch())).toString());
                                    a27.put("EditLock", (Object)new StringBuilder(String.valueOf(a26.GetEditChannelLockSwitch())).toString());
                                    a27.put("SettingsLock", (Object)new StringBuilder(String.valueOf(a26.GetSettingsLockSwitch())).toString());
                                    a27.put("NetworkLock", (Object)new StringBuilder(String.valueOf(a26.GetNetworkLockSwitch())).toString());
                                    a27.put("AgeRating", (Object)new StringBuilder(String.valueOf(a26.GetAgeRatingSwitch())).toString());
                                    a1.add((Object)a27);
                                }
                                a0.put("array", (Object)a1);
                            }
                        }
                        else
                        {
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
                                    break label0;
                                }
                            }
                            Object a28 = a.iterator();
                            while(((java.util.Iterator)a28).hasNext())
                            {
                                mktvsmart.screen.dataconvert.model.DataConvertTimeModel a29 = (mktvsmart.screen.dataconvert.model.DataConvertTimeModel)((java.util.Iterator)a28).next();
                                com.alibaba.fastjson.JSONObject a30 = new com.alibaba.fastjson.JSONObject(true);
                                a30.put("TimerIndex", (Object)new StringBuilder(String.valueOf(a29.GetTimerIndex())).toString());
                                a30.put("TimerProgramId", (Object)a29.getProgramId());
                                a30.put("TimerMonth", (Object)new StringBuilder(String.valueOf(a29.GetTimeMonth())).toString());
                                a30.put("TimerDay", (Object)new StringBuilder(String.valueOf(a29.GetTimeDay())).toString());
                                a30.put("TimerStartHour", (Object)new StringBuilder(String.valueOf(a29.GetStartHour())).toString());
                                a30.put("TimerStartMin", (Object)new StringBuilder(String.valueOf(a29.GetStartMin())).toString());
                                a30.put("TimerEndHour", (Object)new StringBuilder(String.valueOf(a29.GetEndHour())).toString());
                                a30.put("TimerEndMin", (Object)new StringBuilder(String.valueOf(a29.GetEndMin())).toString());
                                a30.put("TimerRepeat", (Object)new StringBuilder(String.valueOf(a29.GetTimerRepeat())).toString());
                                a30.put("TimerStatus", (Object)new StringBuilder(String.valueOf(a29.GetTimerStatus())).toString());
                                a30.put("TimerEventID", (Object)new StringBuilder(String.valueOf(a29.getEventId())).toString());
                                a1.add((Object)a30);
                            }
                            a0.put("array", (Object)a1);
                        }
                    }
                    else if (i == 1003)
                    {
                        Object a31 = a.iterator();
                        int i0 = 0;
                        boolean b = true;
                        while(((java.util.Iterator)a31).hasNext())
                        {
                            mktvsmart.screen.dataconvert.model.DataConvertEditChannelLockModel a32 = (mktvsmart.screen.dataconvert.model.DataConvertEditChannelLockModel)((java.util.Iterator)a31).next();
                            com.alibaba.fastjson.JSONObject a33 = new com.alibaba.fastjson.JSONObject(true);
                            if (b)
                            {
                                a33.put("TvState", (Object)new StringBuilder(String.valueOf(a32.getmChannelType())).toString());
                                b = false;
                            }
                            a33.put("ProgramId", (Object)a32.getProgramId());
                            a1.add((Object)a33);
                            i0 = i0 + 1;
                        }
                        a0.put("array", (Object)a1);
                        a0.put("TotalNum", (Object)new StringBuilder(String.valueOf(i0)).toString());
                    }
                }
                else if (i == 1011)
                {
                    a0.put("TvState", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel)a.get(0)).getChannelTpye())).toString());
                    a0.put("SelectListType", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel)a.get(0)).getSelectListType())).toString());
                    Object a34 = a.iterator();
                    while(((java.util.Iterator)a34).hasNext())
                    {
                        mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel a35 = (mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel)((java.util.Iterator)a34).next();
                        com.alibaba.fastjson.JSONObject a36 = new com.alibaba.fastjson.JSONObject(true);
                        a36.put("ProgramId", (Object)a35.GetProgramId());
                        a1.add((Object)a36);
                    }
                    a0.put("array", (Object)a1);
                }
            }
            else
            {
                switch(i){
                    case 1100: {
                        mktvsmart.screen.dataconvert.model.DataConvertChannelModel a37 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0);
                        a0.put("ProgramId", (Object)a37.GetProgramId());
                        a0.put("TvState", (Object)String.valueOf(a37.getChannelTpye()));
                        break;
                    }
                    case 1005: {
                        Object a38 = a.iterator();
                        int i1 = 0;
                        boolean b0 = true;
                        while(((java.util.Iterator)a38).hasNext())
                        {
                            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a39 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a38).next();
                            com.alibaba.fastjson.JSONObject a40 = new com.alibaba.fastjson.JSONObject(true);
                            if (b0)
                            {
                                a40.put("TvState", (Object)new StringBuilder(String.valueOf(a39.getChannelTpye())).toString());
                                a40.put("MoveToPosition", (Object)a39.getMoveToPosition());
                                b0 = false;
                            }
                            a40.put("ProgramId", (Object)new StringBuilder(String.valueOf((Object)a39.GetProgramId())).toString());
                            a1.add((Object)a40);
                            i1 = i1 + 1;
                        }
                        a0.put("TotalNum", (Object)new StringBuilder(String.valueOf(i1)).toString());
                        a0.put("array", (Object)a1);
                        break;
                    }
                    case 1004: {
                        a0.put("TvState", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).getChannelTpye())).toString());
                        a0.put("FavMark", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).GetFavMark())).toString());
                        java.util.Iterator a41 = ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).mfavGroupIDs.iterator();
                        Object a42 = a;
                        String s0 = "";
                        Object a43 = a41;
                        while(((java.util.Iterator)a43).hasNext())
                        {
                            int i2 = ((Integer)((java.util.Iterator)a43).next()).intValue();
                            s0 = new StringBuilder(String.valueOf((Object)s0)).append(i2).append(":").toString();
                        }
                        if (s0.length() >= 0)
                        {
                            a0.put("FavorGroupID", (Object)s0);
                        }
                        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbPlatform()){
                            case 30: case 31: case 32: case 71: case 72: case 74: {
                                Object a44 = ((java.util.List)a42).iterator();
                                while(((java.util.Iterator)a44).hasNext())
                                {
                                    mktvsmart.screen.dataconvert.model.DataConvertChannelModel a45 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a44).next();
                                    com.alibaba.fastjson.JSONObject a46 = new com.alibaba.fastjson.JSONObject(true);
                                    a46.put("ProgramId", (Object)a45.GetProgramId());
                                    a1.add((Object)a46);
                                }
                                a0.put("array", (Object)a1);
                                break label0;
                            }
                            default: {
                                Object a47 = ((java.util.List)a42).iterator();
                                int i3 = 0;
                                while(((java.util.Iterator)a47).hasNext())
                                {
                                    mktvsmart.screen.dataconvert.model.DataConvertChannelModel a48 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a47).next();
                                    com.alibaba.fastjson.JSONObject a49 = new com.alibaba.fastjson.JSONObject(true);
                                    a49.put("ProgramId", (Object)a48.GetProgramId());
                                    a1.add((Object)a49);
                                    i3 = i3 + 1;
                                }
                                a0.put("array", (Object)a1);
                                a0.put("TotalNum", (Object)new StringBuilder(String.valueOf(i3)).toString());
                                break label0;
                            }
                        }
                    }
                    case 1002: {
                        a0.put("TvState", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).getChannelTpye())).toString());
                        Object a50 = a.iterator();
                        int i4 = 0;
                        while(((java.util.Iterator)a50).hasNext())
                        {
                            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a51 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a50).next();
                            com.alibaba.fastjson.JSONObject a52 = new com.alibaba.fastjson.JSONObject(true);
                            a52.put("ProgramId", (Object)a51.GetProgramId());
                            a1.add((Object)a52);
                            i4 = i4 + 1;
                        }
                        a0.put("array", (Object)a1);
                        a0.put("TotalNum", (Object)new StringBuilder(String.valueOf(i4)).toString());
                        break;
                    }
                    case 1001: {
                        Object a53 = a.iterator();
                        while(((java.util.Iterator)a53).hasNext())
                        {
                            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a54 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a53).next();
                            com.alibaba.fastjson.JSONObject a55 = new com.alibaba.fastjson.JSONObject(true);
                            a55.put("TvState", (Object)new StringBuilder(String.valueOf(a54.getChannelTpye())).toString());
                            a55.put("ProgramId", (Object)a54.GetProgramId());
                            a55.put("ProgramName", (Object)a54.getProgramName());
                            a1.add((Object)a55);
                        }
                        a0.put("array", (Object)a1);
                        break;
                    }
                    case 1000: case 1009: {
                        Object a56 = a.iterator();
                        while(((java.util.Iterator)a56).hasNext())
                        {
                            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a57 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a56).next();
                            com.alibaba.fastjson.JSONObject a58 = new com.alibaba.fastjson.JSONObject(true);
                            a58.put("TvState", (Object)new StringBuilder(String.valueOf(a57.getChannelTpye())).toString());
                            a58.put("ProgramId", (Object)a57.GetProgramId());
                            switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbPlatform()){
                                case 32: case 71: case 72: case 74: {
                                    if (i == 1009)
                                    {
                                        a58.put("iResolutionRatio", (Object)new StringBuilder(String.valueOf(mktvsmart.screen.vlc.TranscodeConstants.iCurResolution)).toString());
                                        a58.put("iBitrate", (Object)new StringBuilder(String.valueOf(mktvsmart.screen.vlc.TranscodeConstants.iCurBitrate)).toString());
                                    }
                                }
                                default: {
                                    a1.add((Object)a58);
                                }
                            }
                        }
                        a0.put("array", (Object)a1);
                        break;
                    }
                    case 104: {
                        a0.put("ProgramId", (Object)((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).GetProgramId());
                        break;
                    }
                    case 5: {
                        Object a59 = a.iterator();
                        while(((java.util.Iterator)a59).hasNext())
                        {
                            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a60 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a59).next();
                            com.alibaba.fastjson.JSONObject a61 = new com.alibaba.fastjson.JSONObject();
                            a61.put("ProgramId", (Object)a60.GetProgramId());
                            a1.add((Object)a61);
                        }
                        a0.put("array", (Object)a1);
                        break;
                    }
                    case 0: {
                        a0.put("FromIndex", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).GetProgramIndex() + i)).toString());
                        a0.put("ToIndex", (Object)new StringBuilder(String.valueOf(((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(1)).GetProgramIndex())).toString());
                        break;
                    }
                }
            }
        }
        else
        {
            a0.put("request", (Object)new StringBuilder(String.valueOf(i)).toString());
        }
        return a0.toString();*/
    //    return null;
    //}
}
