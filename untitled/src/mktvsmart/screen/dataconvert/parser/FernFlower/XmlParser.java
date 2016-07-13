package mktvsmart.screen.dataconvert.parser.FernFlower;

/**
 * Created by it on 11.07.2016.
 */
        import android.util.Xml;
        import java.io.InputStream;
        import java.io.StringWriter;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Iterator;
        import java.util.List;
        import java.util.Map;
        import mktvsmart.screen.GMScreenGlobalInfo;
        import mktvsmart.screen.GsEPGEvent;
        import mktvsmart.screen.GsEPGTableChannel;
        import mktvsmart.screen.dataconvert.model.DataConvertChannelModel;
        import mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel;
        import mktvsmart.screen.dataconvert.model.DataConvertChatMsgModel;
        import mktvsmart.screen.dataconvert.model.DataConvertControlModel;
        import mktvsmart.screen.dataconvert.model.DataConvertDebugModel;
        import mktvsmart.screen.dataconvert.model.DataConvertEditChannelLockModel;
        import mktvsmart.screen.dataconvert.model.DataConvertFavChannelModel;
        import mktvsmart.screen.dataconvert.model.DataConvertFavorModel;
        import mktvsmart.screen.dataconvert.model.DataConvertGChatChannelInfoModel;
        import mktvsmart.screen.dataconvert.model.DataConvertInputMethodModel;
        import mktvsmart.screen.dataconvert.model.DataConvertOneDataModel;
        import mktvsmart.screen.dataconvert.model.DataConvertPvrInfoModel;
        import mktvsmart.screen.dataconvert.model.DataConvertRcuModel;
        import mktvsmart.screen.dataconvert.model.DataConvertSatModel;
        import mktvsmart.screen.dataconvert.model.DataConvertSortModel;
        import mktvsmart.screen.dataconvert.model.DataConvertStbInfoModel;
        import mktvsmart.screen.dataconvert.model.DataConvertTimeModel;
        import mktvsmart.screen.dataconvert.model.DataConvertTpModel;
        import mktvsmart.screen.dataconvert.model.DataConvertUpdateModel;
        import mktvsmart.screen.dataconvert.model.DataConvertUsernameModel;
        import mktvsmart.screen.dataconvert.parser.DataParser;
        import mktvsmart.screen.gchat.bean.GsChatRoomInfo;
        import mktvsmart.screen.gchat.bean.GsChatSetting;
        import mktvsmart.screen.gchat.bean.GsChatUser;
        import mktvsmart.screen.vlc.TranscodeConstants;
        import org.xmlpull.v1.XmlPullParser;
        import org.xmlpull.v1.XmlSerializer;

public class XmlParser implements DataParser {
    public XmlParser() {
    }

    public byte[] IntToByteArray(int var1) {
        byte[] var3 = new byte[8];

        for(int var2 = 0; var2 < 8; ++var2) {
            var3[var2] = (byte)(var1 >> (7 - var2) * 8 & '\uffff');
        }

        return var3;
    }

    public List parse(InputStream var1, int var2) throws Exception {

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

        char var3;
        ArrayList var8;
        DataConvertUsernameModel var9;
        ArrayList var10;
        XmlPullParser var11;
        ArrayList var12;
        XmlPullParser var13;
        GsChatSetting var16;
        String[] var27;
        ArrayList var28;
        String var33;
        DataConvertControlModel var39;
        DataConvertChannelModel var41;
        DataConvertTimeModel var42;
        String var47;
        ArrayList var52;
        DataConvertControlModel var54;
        DataConvertChannelModel var56;
        DataConvertTimeModel var57;
        switch(var2) {
            case 0:

                /* Channel List */

                var8 = null;
                var41 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    var56 = var41;
                    var10 = var8;
                    label1076:
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var56 = var41;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var56 = new DataConvertChannelModel();
                                var10 = var8;
                            } else if(var11.getName().equals("SatIndexSelected")) {
                                var11.next();
                                GMScreenGlobalInfo.setmSatIndexSelected(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("ProgramId")) {
                                var11.next();
                                var41.SetProgramId(var11.getText());
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("ProgramName")) {
                                var11.next();
                                var41.setProgramName(var11.getText());
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("ProgramIndex")) {
                                var11.next();
                                var41.SetProgramIndex(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("SatName")) {
                                var11.next();
                                var41.SetSatName(var11.getText());
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("ProgramType")) {
                                var11.next();
                                var41.SetIsProgramScramble(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("IsProgramHD")) {
                                var11.next();
                                var41.setIsProgramHd(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("FavMark")) {
                                var11.next();
                                var41.SetFavMark(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("LockMark")) {
                                var11.next();
                                var41.setLockMark(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("HaveEPG")) {
                                var11.next();
                                var41.SetHaveEPG(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("IsPlaying")) {
                                var11.next();
                                var41.setIsPlaying(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("WillBePlayed")) {
                                var11.next();
                                var41.setmWillBePlayed(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("ChannelType")) {
                                var11.next();
                                var41.setChannelTpye(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("Frequency")) {
                                var11.next();
                                var41.setFreq(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("Polar")) {
                                var3 = 104;
                                var11.next();
                                var47 = var11.getText();
                                if(var47.equals("0")) {
                                    var3 = 104;
                                } else if(var47.equals("1")) {
                                    var3 = 118;
                                } else if(var47.equals("2")) {
                                    var3 = 108;
                                } else if(var47.equals("3")) {
                                    var3 = 114;
                                }

                                var41.setPol(var3);
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("ModulationSystem")) {
                                var11.next();
                                var41.setModulationSystem(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("ModulationType")) {
                                var11.next();
                                var41.setModulationType(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("RollOff")) {
                                var11.next();
                                var41.setRollOff(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("PilotTones")) {
                                var11.next();
                                var41.setPilotTones(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("SymbolRate")) {
                                var11.next();
                                var41.setSymRate(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("Fec")) {
                                var11.next();
                                var41.setFec(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("VideoPid")) {
                                var11.next();
                                var41.setVideoPid(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("AudioPid")) {
                                var11.next();
                                var41.setAudioPid(var11.getText());
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("TtxPid")) {
                                var11.next();
                                var41.setTtxPid(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("SubPid")) {
                                var11.next();
                                var41.setSubPid(var11.getText());
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("PmtPid")) {
                                var11.next();
                                var41.setPmtPid(Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else if(var11.getName().equals("IsTuner2")) {
                                var11.next();
                                var41.setIsTuner2((short)Integer.parseInt(var11.getText()));
                                var56 = var41;
                                var10 = var8;
                            } else {
                                var56 = var41;
                                var10 = var8;
                                if(!var11.getName().equals("FavorGroupID")) {
                                    break;
                                }

                                var11.next();
                                var27 = var11.getText().split(":");
                                var56 = var41;
                                var10 = var8;
                                if(var27 == null) {
                                    break;
                                }

                                var2 = 0;

                                while(true) {
                                    var56 = var41;
                                    var10 = var8;
                                    if(var2 >= var27.length) {
                                        break label1076;
                                    }

                                    var41.mfavGroupIDs.add(Integer.valueOf(Integer.parseInt(var27[var2])));
                                    ++var2;
                                }
                            }
                            break;
                        case 3:
                            var56 = var41;
                            var10 = var8;
                            if(!var11.getName().equals("parm")) {
                                break;
                            }

                            if(GMScreenGlobalInfo.isSdsOpen() == 0 || var41.getIsTuner2() == 0) {
                                var8.add(var41);
                            }

                            var56 = null;
                            var10 = var8;
                            break;
                        default:
                            var10 = var8;
                            var56 = var41;
                    }

                    var2 = var11.next();
                    var41 = var56;
                }

                return var8;
            case 1:
            case 4:
                var8 = null;
                var42 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var2 = var11.getEventType();

                while(true) {
                    var52 = var8;
                    if(var2 == 1) {
                        return var52;
                    }

                    var57 = var42;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var57 = var42;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("Confirm")) {
                                var11.next();
                                DataConvertTimeModel.isConfirm = Integer.parseInt(var11.getText());
                                var57 = var42;
                                var10 = var8;
                            } else if(var11.getName().equals("parm")) {
                                var57 = new DataConvertTimeModel();
                                var10 = var8;
                            } else if(var11.getName().equals("TimerProgramName")) {
                                var11.next();
                                var42.SetTimeProgramName(var11.getText());
                                var57 = var42;
                                var10 = var8;
                            } else if(var11.getName().equals("TimerProgramSatTpId")) {
                                var11.next();
                                var42.setProgramId(var11.getText());
                                var57 = var42;
                                var10 = var8;
                            } else if(var11.getName().equals("TimerMonth")) {
                                var11.next();
                                var42.SetTimeMonth(Integer.parseInt(var11.getText()));
                                var57 = var42;
                                var10 = var8;
                            } else if(var11.getName().equals("TimerDay")) {
                                var11.next();
                                var42.SetTimeDay(Integer.parseInt(var11.getText()));
                                var57 = var42;
                                var10 = var8;
                            } else if(var11.getName().equals("TimerStartHour")) {
                                var11.next();
                                var42.SetStartHour(Integer.parseInt(var11.getText()));
                                var57 = var42;
                                var10 = var8;
                            } else if(var11.getName().equals("TimerStartMin")) {
                                var11.next();
                                var42.SetStartMin(Integer.parseInt(var11.getText()));
                                var57 = var42;
                                var10 = var8;
                            } else if(var11.getName().equals("TimerEndHour")) {
                                var11.next();
                                var42.SetEndHour(Integer.parseInt(var11.getText()));
                                var57 = var42;
                                var10 = var8;
                            } else if(var11.getName().equals("TimerEndMin")) {
                                var11.next();
                                var42.SetEndMin(Integer.parseInt(var11.getText()));
                                var57 = var42;
                                var10 = var8;
                            } else if(var11.getName().equals("TimerRepeat")) {
                                var11.next();
                                var42.SetTimerRepeat(Integer.parseInt(var11.getText()));
                                var57 = var42;
                                var10 = var8;
                            } else if(var11.getName().equals("TimerStatus")) {
                                var11.next();
                                var42.SetTimerStatus(Integer.parseInt(var11.getText()));
                                var57 = var42;
                                var10 = var8;
                            } else {
                                var57 = var42;
                                var10 = var8;
                                if(var11.getName().equals("TimerEventID")) {
                                    var11.next();
                                    var42.setEventId(Integer.parseInt(var11.getText()));
                                    var57 = var42;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var57 = var42;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var42);
                                var57 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var57 = var42;
                    }

                    var2 = var11.next();
                    var42 = var57;
                    var8 = var10;
                }
            case 2:
                var8 = null;
                var39 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    var54 = var39;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var54 = var39;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var54 = new DataConvertControlModel();
                                var54.SetPowerOff(-1);
                                var10 = var8;
                            } else if(var11.getName().equals("Password")) {
                                var11.next();
                                var39.SetPassword(var11.getText());
                                var54 = var39;
                                var10 = var8;
                            } else if(var11.getName().equals("PasswordLock")) {
                                var11.next();
                                var39.SetPswLockSwitch(Integer.parseInt(var11.getText()));
                                var54 = var39;
                                var10 = var8;
                            } else if(var11.getName().equals("ServiceLock")) {
                                var11.next();
                                var39.SetServiceLockSwitch(Integer.parseInt(var11.getText()));
                                var54 = var39;
                                var10 = var8;
                            } else if(var11.getName().equals("InstallationLock")) {
                                var11.next();
                                var39.SetInstallLockSwitch(Integer.parseInt(var11.getText()));
                                var54 = var39;
                                var10 = var8;
                            } else if(var11.getName().equals("EditChannelLock")) {
                                var11.next();
                                var39.SetEditChannelLockSwitch(Integer.parseInt(var11.getText()));
                                var54 = var39;
                                var10 = var8;
                            } else if(var11.getName().equals("SettingsLock")) {
                                var11.next();
                                var39.SetSettingsLockSwitch(Integer.parseInt(var11.getText()));
                                var54 = var39;
                                var10 = var8;
                            } else if(var11.getName().equals("NetworkLock")) {
                                var11.next();
                                var39.SetNetworkLockSwitch(Integer.parseInt(var11.getText()));
                                var54 = var39;
                                var10 = var8;
                            } else if(var11.getName().equals("AgeRating")) {
                                var11.next();
                                var39.SetAgeRatingSwitch(Integer.parseInt(var11.getText()));
                                var54 = var39;
                                var10 = var8;
                            } else if(var11.getName().equals("IsLockScreen")) {
                                var11.next();
                                var39.SetIsLockScreen(Integer.parseInt(var11.getText()));
                                var54 = var39;
                                var10 = var8;
                            } else {
                                var54 = var39;
                                var10 = var8;
                                if(var11.getName().equals("PowerMode")) {
                                    var11.next();
                                    var39.SetPowerOff(Integer.parseInt(var11.getText()));
                                    var54 = var39;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var54 = var39;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var39);
                                var54 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var54 = var39;
                    }

                    var2 = var11.next();
                    var39 = var54;
                }

                return var8;
            case 3:
                var8 = null;
                DataConvertUpdateModel var45 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    DataConvertUpdateModel var60 = var45;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var60 = var45;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var60 = new DataConvertUpdateModel();
                                var10 = var8;
                            } else if(var11.getName().equals("CustomerId")) {
                                var11.next();
                                var45.SetCustomerId(Integer.parseInt(var11.getText()));
                                var60 = var45;
                                var10 = var8;
                            } else if(var11.getName().equals("HardwareId")) {
                                var11.next();
                                var45.SetModelId(Integer.parseInt(var11.getText()));
                                var60 = var45;
                                var10 = var8;
                            } else {
                                var60 = var45;
                                var10 = var8;
                                if(var11.getName().equals("VersionId")) {
                                    var11.next();
                                    var45.SetVersionId(Integer.parseInt(var11.getText()));
                                    var60 = var45;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var60 = var45;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var45);
                                var60 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var60 = var45;
                    }

                    var2 = var11.next();
                    var45 = var60;
                }

                return var8;
            case 5:
            default:
                var52 = null;
                return var52;
            case 6:
                int var4 = 0;
                int var5 = 0;
                var28 = null;
                GsEPGTableChannel var59 = null;
                GsEPGEvent var30 = null;
                var13 = Xml.newPullParser();
                var13.setInput(var1, "UTF-8");

                for(int var6 = var13.getEventType(); var6 != 1; var28 = var12) {
                    GsEPGEvent var58 = var30;
                    var2 = var5;
                    GsEPGTableChannel var44 = var59;
                    int var7 = var4;
                    var12 = var28;
                    switch(var6) {
                        case 0:
                            var12 = new ArrayList();
                            var58 = var30;
                            var2 = var5;
                            var44 = var59;
                            var7 = var4;
                        case 1:
                            break;
                        case 2:
                            if(var13.getName().equals("prog_epg")) {
                                var44 = new GsEPGTableChannel();
                                var58 = var30;
                                var2 = var5;
                                var7 = var4;
                                var12 = var28;
                            } else if(var13.getName().equals("prog_no")) {
                                var13.next();
                                var59.setProgNo(Integer.parseInt(var13.getText()));
                                var58 = var30;
                                var2 = var5;
                                var44 = var59;
                                var7 = var4;
                                var12 = var28;
                            } else if(var13.getName().equals("original_net_id")) {
                                var13.next();
                                var59.setOriginalNetworkID(Integer.parseInt(var13.getText()));
                                var58 = var30;
                                var2 = var5;
                                var44 = var59;
                                var7 = var4;
                                var12 = var28;
                            } else if(var13.getName().equals("transport_stream_id")) {
                                var13.next();
                                var59.setTransportStreamID(Integer.parseInt(var13.getText()));
                                var58 = var30;
                                var2 = var5;
                                var44 = var59;
                                var7 = var4;
                                var12 = var28;
                            } else if(var13.getName().equals("today_date")) {
                                var13.next();
                                var59.setTodayDate((byte)Integer.parseInt(var13.getText()));
                                var58 = var30;
                                var2 = var5;
                                var44 = var59;
                                var7 = var4;
                                var12 = var28;
                            } else if(var13.getName().equals("current_epg_time")) {
                                var13.next();
                                var59.setCurrentEpgTime(Integer.parseInt(var13.getText()));
                                var58 = var30;
                                var2 = var5;
                                var44 = var59;
                                var7 = var4;
                                var12 = var28;
                            } else {
                                var58 = var30;
                                var2 = var5;
                                var44 = var59;
                                var7 = var4;
                                var12 = var28;
                                if(!var13.getName().equals("prog_day_epg")) {
                                    if(var13.getName().equals("event_valid_num")) {
                                        var13.next();
                                        var59.setArrayEventFieldByIndex(var4, Integer.parseInt(var13.getText()));
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("epg_event")) {
                                        var58 = new GsEPGEvent();
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_start_time")) {
                                        var13.next();
                                        var30.setStartTime(var13.getText());
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_end_time")) {
                                        var13.next();
                                        var30.setEndTime(var13.getText());
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_age_rating")) {
                                        var13.next();
                                        var30.setAgeRating(Integer.parseInt(var13.getText()));
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_timer_type")) {
                                        var13.next();
                                        var30.setEpgTimerType(Integer.parseInt(var13.getText()));
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_total_language")) {
                                        var13.next();
                                        var30.setTotalEpgLanguage(Integer.parseInt(var13.getText()));
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_titile_lang_code")) {
                                        var13.next();
                                        var30.setTitleLanCode(var5, Integer.parseInt(var13.getText()));
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_sub_titile_lang_code")) {
                                        var13.next();
                                        var30.setSubtitleLanCode(var5, Integer.parseInt(var13.getText()));
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_desc_lang_code")) {
                                        var13.next();
                                        var30.setDescLanCode(var5, Integer.parseInt(var13.getText()));
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_titile_len")) {
                                        var13.next();
                                        var30.setTitleLen(var5, Integer.parseInt(var13.getText()));
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_sub_titile_len")) {
                                        var13.next();
                                        var30.setSubtitleLen(var5, Integer.parseInt(var13.getText()));
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_desc_len")) {
                                        var13.next();
                                        var30.setDescLen(var5, Integer.parseInt(var13.getText()));
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_title")) {
                                        var13.next();
                                        var30.setEventTitle(var5, var13.getText());
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else if(var13.getName().equals("event_sub_title")) {
                                        var13.next();
                                        var30.setEventSubTitle(var5, var13.getText());
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                    } else {
                                        var58 = var30;
                                        var2 = var5;
                                        var44 = var59;
                                        var7 = var4;
                                        var12 = var28;
                                        if(var13.getName().equals("event_desc")) {
                                            var13.next();
                                            var30.setEventDesc(var5, var13.getText());
                                            var2 = var5 + 1;
                                            var58 = var30;
                                            var44 = var59;
                                            var7 = var4;
                                            var12 = var28;
                                        }
                                    }
                                }
                            }
                            break;
                        case 3:
                            if(var13.getName().equals("prog_epg")) {
                                var28.add(var59);
                                var44 = null;
                                var58 = var30;
                                var2 = var5;
                                var7 = var4;
                                var12 = var28;
                            } else if(var13.getName().equals("prog_day_epg")) {
                                var7 = var4 + 1;
                                var58 = var30;
                                var2 = var5;
                                var44 = var59;
                                var12 = var28;
                            } else {
                                var58 = var30;
                                var2 = var5;
                                var44 = var59;
                                var7 = var4;
                                var12 = var28;
                                if(var13.getName().equals("epg_event")) {
                                    var59.getEpgDayByIndex(var4).getArrayList().add(var30);
                                    var2 = 0;
                                    var58 = null;
                                    var44 = var59;
                                    var7 = var4;
                                    var12 = var28;
                                }
                            }
                            break;
                        default:
                            var12 = var28;
                            var7 = var4;
                            var44 = var59;
                            var2 = var5;
                            var58 = var30;
                    }

                    var6 = var13.next();
                    var30 = var58;
                    var5 = var2;
                    var59 = var44;
                    var4 = var7;
                }

                return var28;
            case 7:
                var8 = null;
                var42 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var2 = var11.getEventType();

                while(true) {
                    var52 = var8;
                    if(var2 == 1) {
                        return var52;
                    }

                    var57 = var42;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var57 = var42;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var57 = new DataConvertTimeModel();
                                var10 = var8;
                            } else if(var11.getName().equals("Confirm")) {
                                var11.next();
                                DataConvertTimeModel.isConfirm = Integer.parseInt(var11.getText());
                                var57 = var42;
                                var10 = var8;
                            } else {
                                var57 = var42;
                                var10 = var8;
                                if(var11.getName().equals("TimerIndex")) {
                                    var11.next();
                                    var42.SetTimerIndex(Integer.parseInt(var11.getText()));
                                    var57 = var42;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var57 = var42;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var42);
                                var57 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var57 = var42;
                    }

                    var2 = var11.next();
                    var42 = var57;
                    var8 = var10;
                }
            case 8:
                var8 = null;
                var41 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    var56 = var41;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var56 = var41;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var56 = new DataConvertChannelModel();
                                var10 = var8;
                            } else if(var11.getName().equals("LockedChannelIndex")) {
                                var11.next();
                                var41.SetProgramId(var11.getText());
                                var56 = var41;
                                var10 = var8;
                            } else {
                                var56 = var41;
                                var10 = var8;
                                if(var11.getName().equals("TVState")) {
                                    var11.next();
                                    var41.setChannelTpye(Integer.parseInt(var11.getText()));
                                    var56 = var41;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var56 = var41;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var41);
                                var56 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var56 = var41;
                    }

                    var2 = var11.next();
                    var41 = var56;
                }

                return var8;
            case 9:
                var8 = null;
                var9 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var2 = var11.getEventType();

                while(true) {
                    var52 = var8;
                    if(var2 == 1) {
                        return var52;
                    }

                    var1 = var9;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var1 = var9;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("StbMonth")) {
                                var11.next();
                                DataConvertTimeModel.stbMonth = Integer.parseInt(var11.getText());
                                var1 = var9;
                                var10 = var8;
                            } else if(var11.getName().equals("StbDay")) {
                                var11.next();
                                DataConvertTimeModel.stbDay = Integer.parseInt(var11.getText());
                                var1 = var9;
                                var10 = var8;
                            } else if(var11.getName().equals("StbHour")) {
                                var11.next();
                                DataConvertTimeModel.stbHour = Integer.parseInt(var11.getText());
                                var1 = var9;
                                var10 = var8;
                            } else {
                                var1 = var9;
                                var10 = var8;
                                if(var11.getName().equals("StbMin")) {
                                    var11.next();
                                    DataConvertTimeModel.stbMin = Integer.parseInt(var11.getText());
                                    var1 = var9;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var1 = var9;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var9);
                                var1 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var1 = var9;
                    }

                    var2 = var11.next();
                    var9 = var1;
                    var8 = var10;
                }
            case 10:
                var8 = null;
                DataConvertFavorModel var40 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");

                DataConvertFavorModel var55;
                for(var2 = var11.getEventType(); var2 != 1; var40 = var55) {
                    var10 = var8;
                    var55 = var40;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var55 = var40;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("favMaxNum")) {
                                var11.next();
                                DataConvertFavorModel.favorNum = Integer.parseInt(var11.getText());
                                var10 = var8;
                                var55 = var40;
                            } else if(var11.getName().equals("parm")) {
                                var55 = new DataConvertFavorModel();
                                var10 = var8;
                            } else if(var11.getName().equals("favorGroupName")) {
                                var11.next();
                                var40.SetFavorName(var11.getText());
                                var10 = var8;
                                var55 = var40;
                            } else {
                                var10 = var8;
                                var55 = var40;
                                if(var11.getName().equals("FavorGroupID")) {
                                    var11.next();
                                    var40.setFavorTypeID(Integer.valueOf(var11.getText()).intValue());
                                    var10 = var8;
                                    var55 = var40;
                                }
                            }
                            break;
                        case 3:
                            var10 = var8;
                            var55 = var40;
                            if(var11.getName().equals("parm")) {
                                var8.add(var40);
                                var55 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var55 = var40;
                            var10 = var8;
                    }

                    var2 = var11.next();
                    var8 = var10;
                }

                return var8;
            case 11:
                var8 = null;
                var39 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");

                for(var2 = var11.getEventType(); var2 != 1; var39 = var54) {
                    var10 = var8;
                    var54 = var39;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var54 = var39;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var54 = new DataConvertControlModel();
                                var10 = var8;
                            } else if(var11.getName().equals("SleepSwitch")) {
                                var11.next();
                                var39.setSleepSwitch(Integer.parseInt(var11.getText()));
                                var10 = var8;
                                var54 = var39;
                            } else if(var11.getName().equals("SleepTime")) {
                                var11.next();
                                var39.setSleepTime(Integer.parseInt(var11.getText()));
                                var10 = var8;
                                var54 = var39;
                            } else if(var11.getName().equals("ScreenLock")) {
                                var11.next();
                                var39.SetIsLockScreen(Integer.parseInt(var11.getText()));
                                var10 = var8;
                                var54 = var39;
                            } else {
                                var10 = var8;
                                var54 = var39;
                                if(var11.getName().equals("PowerMode")) {
                                    var11.next();
                                    var39.SetPowerOff(Integer.parseInt(var11.getText()));
                                    var10 = var8;
                                    var54 = var39;
                                }
                            }
                            break;
                        case 3:
                            var10 = var8;
                            var54 = var39;
                            if(var11.getName().equals("parm")) {
                                var8.add(var39);
                                var54 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var54 = var39;
                            var10 = var8;
                    }

                    var2 = var11.next();
                    var8 = var10;
                }

                return var8;
            case 12:
                var8 = null;
                DataConvertChannelTypeModel var38 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    DataConvertChannelTypeModel var53 = var38;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var53 = var38;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var53 = new DataConvertChannelTypeModel();
                                var10 = var8;
                            } else if(var11.getName().equals("CurChannelType")) {
                                var11.next();
                                DataConvertChannelTypeModel.setCurrent_channel_tv_radio_type(Integer.parseInt(var11.getText()));
                                var53 = var38;
                                var10 = var8;
                            } else {
                                var53 = var38;
                                var10 = var8;
                                if(var11.getName().equals("tv_radio_key_press")) {
                                    var11.next();
                                    var38.setTvRadioKeyPress(Integer.parseInt(var11.getText()));
                                    var53 = var38;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var53 = var38;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var38);
                                var53 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var53 = var38;
                    }

                    var2 = var11.next();
                    var38 = var53;
                }

                return var8;
            case 13:
                var8 = null;
                DataConvertSortModel var37 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    DataConvertSortModel var51 = var37;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var51 = var37;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var51 = new DataConvertSortModel();
                                var10 = var8;
                            } else if(var11.getName().equals("SortType")) {
                                var11.next();
                                var37.setmSortType(Integer.parseInt(var11.getText()));
                                var51 = var37;
                                var10 = var8;
                            } else if(var11.getName().equals("MacroFlag")) {
                                var11.next();
                                var37.setmMacroFlag(Integer.parseInt(var11.getText()));
                                var51 = var37;
                                var10 = var8;
                            } else {
                                var51 = var37;
                                var10 = var8;
                                if(!var11.getName().equals("SortTypeList")) {
                                    break;
                                }

                                System.out.println("have SortTypeList");
                                var11.next();
                                var27 = var11.getText().split(",");
                                var51 = var37;
                                var10 = var8;
                                if(var27 == null) {
                                    break;
                                }

                                var51 = var37;
                                var10 = var8;
                                if(var27.length <= 0) {
                                    break;
                                }

                                var52 = new ArrayList();

                                for(var2 = 0; var2 < var27.length; ++var2) {
                                    var52.add(var27[var2]);
                                }

                                var37.setSortTypeList(var52);
                                var51 = var37;
                                var10 = var8;
                            }
                            break;
                        case 3:
                            var51 = var37;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var37);
                                var51 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var51 = var37;
                    }

                    var2 = var11.next();
                    var37 = var51;
                }

                return var8;
            case 14:
                var8 = null;
                DataConvertStbInfoModel var36 = null;
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    DataConvertStbInfoModel var50 = var36;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var50 = var36;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var50 = new DataConvertStbInfoModel();
                                var10 = var8;
                            } else if(var11.getName().equals("StbStatus")) {
                                var11.next();
                                var36.setmStbStatus(Integer.parseInt(var11.getText()));
                                var50 = var36;
                                var10 = var8;
                            } else if(var11.getName().equals("ProductName")) {
                                var11.next();
                                var36.setmProductName(var11.getText());
                                var50 = var36;
                                var10 = var8;
                            } else if(var11.getName().equals("SoftwareVersion")) {
                                var11.next();
                                var36.setmSoftwareVersion(var11.getText());
                                var50 = var36;
                                var10 = var8;
                            } else if(var11.getName().equals("SerialNumber")) {
                                var11.next();
                                var36.setmSerialNumber(var11.getText());
                                var50 = var36;
                                var10 = var8;
                            } else if(var11.getName().equals("ChannelNum")) {
                                var11.next();
                                var36.setmChannelNum(Integer.parseInt(var11.getText()));
                                var50 = var36;
                                var10 = var8;
                            } else {
                                var50 = var36;
                                var10 = var8;
                                if(var11.getName().equals("MaxNumOfPrograms")) {
                                    var11.next();
                                    var36.setmMaxNumOfPrograms(Integer.parseInt(var11.getText()));
                                    var50 = var36;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var50 = var36;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var36);
                                var50 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var50 = var36;
                    }

                    var2 = var11.next();
                    var36 = var50;
                }

                return var8;
            case 15:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var8 = null;
                var33 = null;

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    var47 = var33;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var47 = var33;
                        case 1:
                            break;
                        case 2:
                            var47 = var33;
                            var10 = var8;
                            if(var11.getName().equals("Data")) {
                                var11.next();
                                var47 = new String(var11.getText());
                                var10 = var8;
                            }
                            break;
                        case 3:
                            var47 = var33;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var33);
                                var47 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var47 = var33;
                    }

                    var2 = var11.next();
                    var33 = var47;
                }

                return var8;
            case 16:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var8 = null;
                HashMap var35 = null;

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    HashMap var49 = var35;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var49 = var35;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("success")) {
                                var11.next();
                                var49 = new HashMap();
                                var49.put("success", Integer.valueOf(var11.getText()));
                                var10 = var8;
                            } else if(var11.getName().equals("url")) {
                                var11.next();
                                var35.put("url", var11.getText());
                                var49 = var35;
                                var10 = var8;
                            } else {
                                var49 = var35;
                                var10 = var8;
                                if(var11.getName().equals("errormsg")) {
                                    var11.next();
                                    var35.put("errormsg", var11.getText());
                                    var49 = var35;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var49 = var35;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var35);
                                var49 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var49 = var35;
                    }

                    var2 = var11.next();
                    var35 = var49;
                }

                return var8;
            case 17:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var8 = null;
                var33 = null;

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    var47 = var33;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var47 = var33;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("cur_channel_list_type")) {
                                var11.next();
                                var47 = new String(var11.getText());
                                var10 = var8;
                            } else if(var11.getName().equals("max_password_num")) {
                                var11.next();
                                GMScreenGlobalInfo.setmMaxPasswordNum(Integer.parseInt(var11.getText()));
                                var47 = var33;
                                var10 = var8;
                            } else if(var11.getName().equals("cur_channel_type")) {
                                var11.next();
                                DataConvertChannelTypeModel.setCurrent_channel_tv_radio_type(Integer.parseInt(var11.getText()));
                                var47 = var33;
                                var10 = var8;
                            } else if(var11.getName().equals("is_support_pvr2ip_server")) {
                                var11.next();
                                GMScreenGlobalInfo.setmPvr2ipServerSupport(Integer.parseInt(var11.getText()));
                                System.out.println("GMScreenGlobalInfo.getmPvr2ipServerSupport()==" + GMScreenGlobalInfo.getmPvr2ipServerSupport());
                                var47 = var33;
                                var10 = var8;
                            } else {
                                var47 = var33;
                                var10 = var8;
                                if(var11.getName().equals("is_sds_open")) {
                                    var11.next();
                                    GMScreenGlobalInfo.setSdsOpen(Integer.parseInt(var11.getText()));
                                    var47 = var33;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var47 = var33;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var33);
                                var47 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var47 = var33;
                    }

                    var2 = var11.next();
                    var33 = var47;
                }

                return var8;
            case 18:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var8 = null;
                DataConvertSatModel var32 = null;

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    DataConvertSatModel var48 = var32;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var48 = var32;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var48 = new DataConvertSatModel();
                                var10 = var8;
                            } else if(var11.getName().equals("SatName")) {
                                var11.next();
                                var32.setmSatName(var11.getText());
                                var48 = var32;
                                var10 = var8;
                            } else if(var11.getName().equals("SatNo")) {
                                var11.next();
                                var32.setmSatIndex(Integer.parseInt(var11.getText()));
                                var48 = var32;
                                var10 = var8;
                            } else if(var11.getName().equals("SatAngle")) {
                                var11.next();
                                var32.setmSatAngle(Integer.parseInt(var11.getText()));
                                var48 = var32;
                                var10 = var8;
                            } else {
                                var48 = var32;
                                var10 = var8;
                                if(var11.getName().equals("SatDir")) {
                                    var11.next();
                                    var32.setmSatDir(Integer.parseInt(var11.getText()));
                                    var48 = var32;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var48 = var32;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var32);
                                var48 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var48 = var32;
                    }

                    var2 = var11.next();
                    var32 = var48;
                }

                return var8;
            case 19:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var8 = null;
                DataConvertTpModel var31 = null;

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    DataConvertTpModel var46 = var31;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var46 = var31;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var46 = new DataConvertTpModel();
                                var10 = var8;
                            } else if(var11.getName().equals("TpIndex")) {
                                var11.next();
                                var31.setTpIndex(Integer.parseInt(var11.getText()));
                                var46 = var31;
                                var10 = var8;
                            } else if(var11.getName().equals("SatIndex")) {
                                var11.next();
                                var31.setSatIndex(Integer.parseInt(var11.getText()));
                                var46 = var31;
                                var10 = var8;
                            } else if(var11.getName().equals("SystemRate")) {
                                var11.next();
                                var31.setSymRate(Integer.parseInt(var11.getText()));
                                var46 = var31;
                                var10 = var8;
                            } else if(var11.getName().equals("Pol")) {
                                var3 = 104;
                                var11.next();
                                var47 = var11.getText();
                                if(var47.equals("0")) {
                                    var3 = 104;
                                } else if(var47.equals("1")) {
                                    var3 = 118;
                                } else if(var47.equals("2")) {
                                    var3 = 108;
                                } else if(var47.equals("3")) {
                                    var3 = 114;
                                }

                                var31.setPol(var3);
                                var46 = var31;
                                var10 = var8;
                            } else if(var11.getName().equals("Fec")) {
                                var11.next();
                                var31.setFec(Integer.parseInt(var11.getText()));
                                var46 = var31;
                                var10 = var8;
                            } else {
                                var46 = var31;
                                var10 = var8;
                                if(var11.getName().equals("Freq")) {
                                    var11.next();
                                    var31.setFreq(Integer.parseInt(var11.getText()));
                                    var46 = var31;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var46 = var31;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var31);
                                var46 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var46 = var31;
                    }

                    var2 = var11.next();
                    var31 = var46;
                }

                return var8;
            case 20:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var8 = null;
                DataConvertPvrInfoModel var29 = null;

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    DataConvertPvrInfoModel var43 = var29;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var43 = var29;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var43 = new DataConvertPvrInfoModel();
                                var10 = var8;
                            } else if(var11.getName().equals("pvr_name")) {
                                var11.next();
                                var29.setProgramName(var11.getText());
                                var43 = var29;
                                var10 = var8;
                            } else if(var11.getName().equals("pvr_uid")) {
                                var11.next();
                                var29.setmPvrId(Integer.parseInt(var11.getText()));
                                var43 = var29;
                                var10 = var8;
                            } else if(var11.getName().equals("pvr_duration")) {
                                var11.next();
                                var29.setmPvrDuration(var11.getText());
                                var43 = var29;
                                var10 = var8;
                            } else if(var11.getName().equals("Pvr_time")) {
                                var11.next();
                                var29.setmPvrTime(var11.getText());
                                var43 = var29;
                                var10 = var8;
                            } else if(var11.getName().equals("pvr_type")) {
                                var11.next();
                                var29.setmPvrType(Integer.parseInt(var11.getText()));
                                var43 = var29;
                                var10 = var8;
                            } else {
                                var43 = var29;
                                var10 = var8;
                                if(var11.getName().equals("crypto")) {
                                    var11.next();
                                    var29.setmPvrCrypto(Integer.parseInt(var11.getText()));
                                    var43 = var29;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var43 = var29;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var29);
                                var43 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var43 = var29;
                    }

                    var2 = var11.next();
                    var29 = var43;
                }

                return var8;
            case 21:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var28 = null;
                GsChatSetting var26 = null;

                for(var2 = var11.getEventType(); var2 != 1; var28 = var10) {
                    var16 = var26;
                    var10 = var28;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var16 = var26;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var26 = GsChatSetting.getInstance();
                            }

                            if(var11.getName().equals("MySN")) {
                                var11.next();
                                var26.setSerialNumber(var11.getText());
                                var16 = var26;
                                var10 = var28;
                            } else if(var11.getName().equals("MyUsername")) {
                                var11.next();
                                var26.setUsername(var11.getText());
                                var16 = var26;
                                var10 = var28;
                            } else {
                                var16 = var26;
                                var10 = var28;
                                if(var11.getName().equals("USERID")) {
                                    var11.next();
                                    var26.setUserId(Integer.parseInt(var11.getText()));
                                    var16 = var26;
                                    var10 = var28;
                                }
                            }
                            break;
                        case 3:
                            var16 = var26;
                            var10 = var28;
                            if(var11.getName().equals("parm")) {
                                var28.add(var26);
                                var16 = null;
                                var10 = var28;
                            }
                            break;
                        default:
                            var10 = var28;
                            var16 = var26;
                    }

                    var2 = var11.next();
                    var26 = var16;
                }

                return var28;
            case 22:
                var13 = Xml.newPullParser();
                var13.setInput(var1, "UTF-8");
                var10 = null;
                GsChatRoomInfo var34 = null;
                GsChatUser var24 = null;

                for(var2 = var13.getEventType(); var2 != 1; var10 = var12) {
                    GsChatUser var20 = var24;
                    GsChatRoomInfo var22 = var34;
                    var12 = var10;
                    switch(var2) {
                        case 0:
                            var12 = new ArrayList();
                            var20 = var24;
                            var22 = var34;
                        case 1:
                            break;
                        case 2:
                            if(var13.getName().equals("parm")) {
                                var34 = new GsChatRoomInfo();
                            }

                            if(var13.getName().equals("EventTitle")) {
                                var13.next();
                                var34.setEventTitle(var13.getText());
                                var20 = var24;
                                var22 = var34;
                                var12 = var10;
                            } else if(var13.getName().equals("OnlineUserNum")) {
                                var13.next();
                                var34.setOnlineNum(Integer.parseInt(var13.getText()));
                                var20 = var24;
                                var22 = var34;
                                var12 = var10;
                            } else if(var13.getName().equals("RoomId")) {
                                var13.next();
                                var34.setRoomID(Integer.parseInt(var13.getText()));
                                var20 = var24;
                                var22 = var34;
                                var12 = var10;
                            } else if(var13.getName().equals("UserInfo")) {
                                var20 = new GsChatUser();
                                var22 = var34;
                                var12 = var10;
                            } else if(var13.getName().equals("USERID")) {
                                var13.next();
                                var24.setUserID(Integer.parseInt(var13.getText()));
                                var20 = var24;
                                var22 = var34;
                                var12 = var10;
                            } else {
                                var20 = var24;
                                var22 = var34;
                                var12 = var10;
                                if(var13.getName().equals("Username")) {
                                    var13.next();
                                    var24.setUsername(var13.getText());
                                    var20 = var24;
                                    var22 = var34;
                                    var12 = var10;
                                }
                            }
                            break;
                        case 3:
                            if(var13.getName().equals("parm")) {
                                var10.add(var34);
                                var22 = null;
                                var20 = var24;
                                var12 = var10;
                            } else {
                                var20 = var24;
                                var22 = var34;
                                var12 = var10;
                                if(var13.getName().equals("UserInfo")) {
                                    var34.getUserList().add(var24);
                                    var20 = null;
                                    var22 = var34;
                                    var12 = var10;
                                }
                            }
                            break;
                        default:
                            var12 = var10;
                            var22 = var34;
                            var20 = var24;
                    }

                    var2 = var13.next();
                    var24 = var20;
                    var34 = var22;
                }

                return var10;
            case 23:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var8 = null;
                DataConvertChatMsgModel var21 = null;

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    DataConvertChatMsgModel var25 = var21;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var25 = var21;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var25 = new DataConvertChatMsgModel();
                                var10 = var8;
                            } else if(var11.getName().equals("Timestamp")) {
                                var11.next();
                                var21.setTimestamp(Long.parseLong(var11.getText()));
                                var25 = var21;
                                var10 = var8;
                            } else if(var11.getName().equals("USERID")) {
                                var11.next();
                                var2 = Integer.parseInt(var11.getText());
                                var21.setUserID(var2);
                                if(var2 == GsChatSetting.getInstance().getUserId()) {
                                    var21.setMsgType(1);
                                    var25 = var21;
                                    var10 = var8;
                                } else {
                                    var21.setMsgType(0);
                                    var25 = var21;
                                    var10 = var8;
                                }
                            } else if(var11.getName().equals("Username")) {
                                var11.next();
                                var21.setUsername(var11.getText());
                                var25 = var21;
                                var10 = var8;
                            } else {
                                var25 = var21;
                                var10 = var8;
                                if(var11.getName().equals("Content")) {
                                    var11.next();
                                    var21.setContent(var11.getText());
                                    var25 = var21;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var25 = var21;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var21);
                                var25 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var25 = var21;
                    }

                    var2 = var11.next();
                    var21 = var25;
                }

                return var8;
            case 24:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var8 = null;
                GsChatUser var19 = null;

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    GsChatUser var23 = var19;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var23 = var19;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var23 = new GsChatUser();
                                var10 = var8;
                            } else if(var11.getName().equals("USERID")) {
                                var11.next();
                                var19.setUserID(Integer.parseInt(var11.getText()));
                                var23 = var19;
                                var10 = var8;
                            } else {
                                var23 = var19;
                                var10 = var8;
                                if(var11.getName().equals("Username")) {
                                    var11.next();
                                    var19.setUsername(var11.getText());
                                    var23 = var19;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var23 = var19;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var19);
                                var23 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var23 = var19;
                    }

                    var2 = var11.next();
                    var19 = var23;
                }

                return var8;
            case 25:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var8 = null;
                GsChatSetting var18 = null;

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    var16 = var18;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var16 = var18;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var16 = GsChatSetting.getInstance();
                                var10 = var8;
                            } else if(var11.getName().equals("ShowWindow")) {
                                var11.next();
                                var18.setShowWindow(Integer.parseInt(var11.getText()));
                                var16 = var18;
                                var10 = var8;
                            } else if(var11.getName().equals("WindowSize")) {
                                var11.next();
                                var18.setWindowSize(Integer.parseInt(var11.getText()));
                                var16 = var18;
                                var10 = var8;
                            } else if(var11.getName().equals("WindowPosition")) {
                                var11.next();
                                var18.setWindowPosition(Integer.parseInt(var11.getText()));
                                var16 = var18;
                                var10 = var8;
                            } else {
                                var16 = var18;
                                var10 = var8;
                                if(var11.getName().equals("WindowTransparency")) {
                                    var11.next();
                                    var18.setWindowTransparency(Integer.parseInt(var11.getText()));
                                    var16 = var18;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var16 = var18;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var18);
                                var16 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var16 = var18;
                    }

                    var2 = var11.next();
                    var18 = var16;
                }

                return var8;
            case 26:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var8 = null;
                DataConvertGChatChannelInfoModel var17 = null;

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    DataConvertGChatChannelInfoModel var15 = var17;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var15 = var17;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var15 = new DataConvertGChatChannelInfoModel();
                                var10 = var8;
                            } else if(var11.getName().equals("Angle")) {
                                var11.next();
                                var17.setSatAngle(var11.getText());
                                var15 = var17;
                                var10 = var8;
                            } else if(var11.getName().equals("Tp")) {
                                var11.next();
                                var17.setTp(Integer.parseInt(var11.getText()));
                                var15 = var17;
                                var10 = var8;
                            } else if(var11.getName().equals("ServiceId")) {
                                var11.next();
                                var17.setServiceId(Integer.parseInt(var11.getText()));
                                var15 = var17;
                                var10 = var8;
                            } else {
                                var15 = var17;
                                var10 = var8;
                                if(var11.getName().equals("EPG")) {
                                    var11.next();
                                    var17.setEpg(var11.getText());
                                    var15 = var17;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var15 = var17;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var17);
                                var15 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var15 = var17;
                    }

                    var2 = var11.next();
                    var17 = var15;
                }

                return var8;
            case 27:
                var11 = Xml.newPullParser();
                var11.setInput(var1, "UTF-8");
                var8 = null;
                var9 = null;

                for(var2 = var11.getEventType(); var2 != 1; var8 = var10) {
                    DataConvertUsernameModel var14 = var9;
                    var10 = var8;
                    switch(var2) {
                        case 0:
                            var10 = new ArrayList();
                            var14 = var9;
                        case 1:
                            break;
                        case 2:
                            if(var11.getName().equals("parm")) {
                                var14 = new DataConvertUsernameModel();
                                var10 = var8;
                            } else {
                                var14 = var9;
                                var10 = var8;
                                if(var11.getName().equals("Username")) {
                                    var11.next();
                                    var9.setUsername(var11.getText());
                                    var14 = var9;
                                    var10 = var8;
                                }
                            }
                            break;
                        case 3:
                            var14 = var9;
                            var10 = var8;
                            if(var11.getName().equals("parm")) {
                                var8.add(var9);
                                var14 = null;
                                var10 = var8;
                            }
                            break;
                        default:
                            var10 = var8;
                            var14 = var9;
                    }

                    var2 = var11.next();
                    var9 = var14;
                }

                return var8;
        }
    }

    public String serialize(List var1, int var2) throws Exception {
        XmlSerializer var6 = Xml.newSerializer();
        StringWriter var7 = new StringWriter();
        var6.setOutput(var7);
        var6.startDocument("UTF-8", Boolean.valueOf(true));
        var6.startTag("", "Command");
        if(var1 == null) {
            var6.attribute("", "request", String.valueOf(var2));
        } else {
            Object var5 = var1.get(0);
            boolean var3;
            boolean var4;
            Iterator var8;
            Iterator var11;
            if(var5.getClass().getName() == DataConvertChannelModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                DataConvertChannelModel var10;
                DataConvertChannelModel var13;
                label288:
                switch(var2) {
                    case 0:
                        var6.startTag("", "parm");
                        var6.startTag("", "FromIndex");
                        var6.text(String.valueOf(((DataConvertChannelModel)var1.get(0)).GetProgramIndex()));
                        var6.endTag("", "FromIndex");
                        var6.startTag("", "ToIndex");
                        var6.text(String.valueOf(((DataConvertChannelModel)var1.get(1)).GetProgramIndex()));
                        var6.endTag("", "ToIndex");
                        var6.endTag("", "parm");
                        break;
                    case 5:
                        var11 = var1.iterator();

                        while(true) {
                            if(!var11.hasNext()) {
                                break label288;
                            }

                            var13 = (DataConvertChannelModel)var11.next();
                            var6.startTag("", "parm");
                            var6.startTag("", "ProgramId");
                            var6.text(var13.GetProgramId());
                            var6.endTag("", "ProgramId");
                            var6.endTag("", "parm");
                        }
                    case 104:
                        var10 = (DataConvertChannelModel)var1.get(0);
                        var6.startTag("", "parm");
                        var6.startTag("", "ProgramId");
                        var6.text(var10.GetProgramId());
                        var6.endTag("", "ProgramId");
                        var6.endTag("", "parm");
                        break;
                    case 1000:
                    case 1009:
                        var11 = var1.iterator();

                        while(true) {
                            if(!var11.hasNext()) {
                                break label288;
                            }

                            var13 = (DataConvertChannelModel)var11.next();
                            var6.startTag("", "parm");
                            var6.startTag("", "TvState");
                            var6.text(String.valueOf(var13.getChannelTpye()));
                            var6.endTag("", "TvState");
                            var6.startTag("", "ProgramId");
                            var6.text(var13.GetProgramId());
                            var6.endTag("", "ProgramId");
                            switch(GMScreenGlobalInfo.getCurStbPlatform()) {
                                case 32:
                                case 71:
                                case 72:
                                case 74:
                                    if(var2 == 1009) {
                                        var6.startTag("", "iResolutionRatio");
                                        var6.text(String.valueOf(TranscodeConstants.iCurResolution));
                                        var6.endTag("", "iResolutionRatio");
                                        var6.startTag("", "iBitrate");
                                        var6.text(String.valueOf(TranscodeConstants.iCurBitrate));
                                        var6.endTag("", "iBitrate");
                                    }
                                default:
                                    var6.endTag("", "parm");
                            }
                        }
                    case 1001:
                        var11 = var1.iterator();

                        while(true) {
                            if(!var11.hasNext()) {
                                break label288;
                            }

                            var13 = (DataConvertChannelModel)var11.next();
                            var6.startTag("", "parm");
                            var6.startTag("", "TvState");
                            var6.text(String.valueOf(var13.getChannelTpye()));
                            var6.endTag("", "TvState");
                            var6.startTag("", "ProgramId");
                            var6.text(var13.GetProgramId());
                            var6.endTag("", "ProgramId");
                            var6.startTag("", "ProgramName");
                            var6.text(var13.getProgramName());
                            var6.endTag("", "ProgramName");
                            var6.endTag("", "parm");
                        }
                    case 1002:
                        var2 = 0;
                        var6.startTag("", "TvState");
                        var6.text(String.valueOf(((DataConvertChannelModel)var1.get(0)).getChannelTpye()));
                        var6.endTag("", "TvState");
                        var11 = var1.iterator();

                        while(var11.hasNext()) {
                            var13 = (DataConvertChannelModel)var11.next();
                            var6.startTag("", "parm");
                            var6.startTag("", "ProgramId");
                            var6.text(var13.GetProgramId());
                            var6.endTag("", "ProgramId");
                            ++var2;
                            var6.endTag("", "parm");
                        }

                        var6.startTag("", "TotalNum");
                        var6.text(String.valueOf(var2));
                        var6.endTag("", "TotalNum");
                        break;
                    case 1004:
                        var2 = 0;
                        var6.startTag("", "TvState");
                        var6.text(String.valueOf(((DataConvertChannelModel)var1.get(0)).getChannelTpye()));
                        var6.endTag("", "TvState");
                        var6.startTag("", "FavMark");
                        var6.text(String.valueOf(((DataConvertChannelModel)var1.get(0)).GetFavMark()));
                        var6.endTag("", "FavMark");
                        String var14 = "";

                        int var12;
                        for(var8 = ((DataConvertChannelModel)var1.get(0)).mfavGroupIDs.iterator(); var8.hasNext(); var14 = var14 + var12 + ":") {
                            var12 = ((Integer)var8.next()).intValue();
                        }

                        if(var14.length() >= 0) {
                            var6.startTag("", "FavorGroupID");
                            var6.text(var14);
                            var6.endTag("", "FavorGroupID");
                        }

                        switch(GMScreenGlobalInfo.getCurStbPlatform()) {
                            case 30:
                            case 31:
                            case 32:
                            case 71:
                            case 72:
                            case 74:
                                var11 = var1.iterator();

                                while(true) {
                                    if(!var11.hasNext()) {
                                        break label288;
                                    }

                                    var13 = (DataConvertChannelModel)var11.next();
                                    var6.startTag("", "ProgramId");
                                    var6.text(var13.GetProgramId());
                                    var6.endTag("", "ProgramId");
                                }
                            default:
                                for(var11 = var1.iterator(); var11.hasNext(); ++var2) {
                                    var13 = (DataConvertChannelModel)var11.next();
                                    var6.startTag("", "ProgramId");
                                    var6.text(var13.GetProgramId());
                                    var6.endTag("", "ProgramId");
                                }

                                var6.startTag("", "TotalNum");
                                var6.text(String.valueOf(var2));
                                var6.endTag("", "TotalNum");
                                break label288;
                        }
                    case 1005:
                        var3 = true;
                        var2 = 0;

                        for(var11 = var1.iterator(); var11.hasNext(); var3 = var4) {
                            var13 = (DataConvertChannelModel)var11.next();
                            var6.startTag("", "parm");
                            var4 = var3;
                            if(var3) {
                                var6.startTag("", "TvState");
                                var6.text(String.valueOf(var13.getChannelTpye()));
                                var6.endTag("", "TvState");
                                var6.startTag("", "MoveToPosition");
                                var6.text(var13.getMoveToPosition());
                                var6.endTag("", "MoveToPosition");
                                var4 = false;
                            }

                            var6.startTag("", "ProgramId");
                            var6.text(var13.GetProgramId());
                            var6.endTag("", "ProgramId");
                            ++var2;
                            var6.endTag("", "parm");
                        }

                        var6.startTag("", "TotalNum");
                        var6.text(String.valueOf(var2));
                        var6.endTag("", "TotalNum");
                        break;
                    case 1100:
                        var10 = (DataConvertChannelModel)var1.get(0);
                        var6.startTag("", "parm");
                        var6.startTag("", "TvState");
                        var6.text(String.valueOf(var10.getChannelTpye()));
                        var6.endTag("", "TvState");
                        var6.startTag("", "ProgramId");
                        var6.text(var10.GetProgramId());
                        var6.endTag("", "ProgramId");
                        var6.endTag("", "parm");
                }
            } else if(var5.getClass().getName() == DataConvertFavChannelModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1011) {
                    var6.startTag("", "TvState");
                    var6.text(String.valueOf(((DataConvertFavChannelModel)var1.get(0)).getChannelTpye()));
                    var6.endTag("", "TvState");
                    var6.startTag("", "SelectListType");
                    var6.text(String.valueOf(((DataConvertFavChannelModel)var1.get(0)).getSelectListType()));
                    var6.endTag("", "SelectListType");
                    var11 = var1.iterator();

                    while(var11.hasNext()) {
                        DataConvertFavChannelModel var18 = (DataConvertFavChannelModel)var11.next();
                        var6.startTag("", "parm");
                        var6.startTag("", "ProgramId");
                        var6.text(var18.GetProgramId());
                        var6.endTag("", "ProgramId");
                        var6.endTag("", "parm");
                    }
                }
            } else if(var5.getClass().getName() == DataConvertEditChannelLockModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1003) {
                    var2 = 0;
                    var3 = true;
                    var6.startTag("", "parm");

                    for(var11 = var1.iterator(); var11.hasNext(); var3 = var4) {
                        DataConvertEditChannelLockModel var20 = (DataConvertEditChannelLockModel)var11.next();
                        var4 = var3;
                        if(var3) {
                            var6.startTag("", "TvState");
                            var6.text(String.valueOf(var20.getmChannelType()));
                            var6.endTag("", "TvState");
                            var4 = false;
                        }

                        var6.startTag("", "ProgramId");
                        var6.text(var20.getProgramId());
                        var6.endTag("", "ProgramId");
                        ++var2;
                    }

                    var6.startTag("", "TotalNum");
                    var6.text(String.valueOf(var2));
                    var6.endTag("", "TotalNum");
                    var6.endTag("", "parm");
                }
            } else if(var5.getClass().getName() == DataConvertTimeModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1021 || var2 == 1022 || var2 == 1023 || var2 == 1020) {
                    var11 = var1.iterator();

                    while(var11.hasNext()) {
                        DataConvertTimeModel var21 = (DataConvertTimeModel)var11.next();
                        var6.startTag("", "parm");
                        var6.startTag("", "TimerIndex");
                        var6.text(String.valueOf(var21.GetTimerIndex()));
                        var6.endTag("", "TimerIndex");
                        var6.startTag("", "TimerProgramId");
                        var6.text(var21.getProgramId());
                        var6.endTag("", "TimerProgramId");
                        var6.startTag("", "TimerMonth");
                        var6.text(String.valueOf(var21.GetTimeMonth()));
                        var6.endTag("", "TimerMonth");
                        var6.startTag("", "TimerDay");
                        var6.text(String.valueOf(var21.GetTimeDay()));
                        var6.endTag("", "TimerDay");
                        var6.startTag("", "TimerStartHour");
                        var6.text(String.valueOf(var21.GetStartHour()));
                        var6.endTag("", "TimerStartHour");
                        var6.startTag("", "TimerStartMin");
                        var6.text(String.valueOf(var21.GetStartMin()));
                        var6.endTag("", "TimerStartMin");
                        var6.startTag("", "TimerEndHour");
                        var6.text(String.valueOf(var21.GetEndHour()));
                        var6.endTag("", "TimerEndHour");
                        var6.startTag("", "TimerEndMin");
                        var6.text(String.valueOf(var21.GetEndMin()));
                        var6.endTag("", "TimerEndMin");
                        var6.startTag("", "TimerRepeat");
                        var6.text(String.valueOf(var21.GetTimerRepeat()));
                        var6.endTag("", "TimerRepeat");
                        var6.startTag("", "TimerStatus");
                        var6.text(String.valueOf(var21.GetTimerStatus()));
                        var6.endTag("", "TimerStatus");
                        var6.startTag("", "TimerEventID");
                        var6.text(String.valueOf(var21.getEventId()));
                        var6.endTag("", "TimerEventID");
                        var6.endTag("", "parm");
                    }
                }
            } else if(var5.getClass().getName() == DataConvertControlModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1051) {
                    var11 = var1.iterator();

                    while(var11.hasNext()) {
                        DataConvertControlModel var22 = (DataConvertControlModel)var11.next();
                        var6.startTag("", "parm");
                        var6.startTag("", "PasswordLock");
                        var6.text(String.valueOf(var22.GetPswLockSwitch()));
                        var6.endTag("", "PasswordLock");
                        var6.startTag("", "ServiceLock");
                        var6.text(String.valueOf(var22.GetServiceLockSwitch()));
                        var6.endTag("", "ServiceLock");
                        var6.startTag("", "InstallLock");
                        var6.text(String.valueOf(var22.GetInstallLockSwitch()));
                        var6.endTag("", "InstallLock");
                        var6.startTag("", "EditLock");
                        var6.text(String.valueOf(var22.GetEditChannelLockSwitch()));
                        var6.endTag("", "EditLock");
                        var6.startTag("", "SettingsLock");
                        var6.text(String.valueOf(var22.GetSettingsLockSwitch()));
                        var6.endTag("", "SettingsLock");
                        var6.startTag("", "NetworkLock");
                        var6.text(String.valueOf(var22.GetNetworkLockSwitch()));
                        var6.endTag("", "NetworkLock");
                        var6.startTag("", "AgeRating");
                        var6.text(String.valueOf(var22.GetAgeRatingSwitch()));
                        var6.endTag("", "AgeRating");
                        var6.endTag("", "parm");
                    }
                } else if(var2 == 1052) {
                    var6.startTag("", "parm");
                    var6.startTag("", "OldPassword");
                    var6.text(((DataConvertControlModel)var1.get(0)).GetPassword());
                    var6.endTag("", "OldPassword");
                    var6.endTag("", "parm");
                    var6.startTag("", "parm");
                    var6.startTag("", "NewPassword");
                    var6.text(((DataConvertControlModel)var1.get(1)).GetPassword());
                    var6.endTag("", "NewPassword");
                    var6.endTag("", "parm");
                } else if(var2 == 1050) {
                    DataConvertControlModel var15 = (DataConvertControlModel)var1.get(0);
                    var6.startTag("", "parm");
                    var6.startTag("", "SleepSwitch");
                    var6.text(String.valueOf(var15.getSleepSwitch()));
                    var6.endTag("", "SleepSwitch");
                    if(var15.getSleepSwitch() == 1) {
                        var6.startTag("", "SleepTime");
                        var6.text(String.valueOf(var15.getSleepTime()));
                        var6.endTag("", "SleepTime");
                    }

                    var6.endTag("", "parm");
                }
            } else if(var5.getClass().getName() == DataConvertUpdateModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1010) {
                    var11 = var1.iterator();

                    while(var11.hasNext()) {
                        DataConvertUpdateModel var23 = (DataConvertUpdateModel)var11.next();
                        var6.startTag("", "ChannelFileLen");
                        var6.text(String.valueOf(var23.GetDataLen()));
                        var6.endTag("", "ChannelFileLen");
                    }
                }
            } else if(var5.getClass().getName() == DataConvertDebugModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1054 || var2 == 9) {
                    var11 = var1.iterator();

                    while(var11.hasNext()) {
                        DataConvertDebugModel var24 = (DataConvertDebugModel)var11.next();
                        var6.startTag("", "EnableDebug");
                        var6.text(String.valueOf(var24.getDebugValue()));
                        var6.endTag("", "EnableDebug");
                        var6.startTag("", "RequestDataFrom");
                        var6.text(String.valueOf(var24.getRequestDataFrom()));
                        var6.endTag("", "RequestDataFrom");
                        var6.startTag("", "RequestDataTo");
                        var6.text(String.valueOf(var24.getRequestDataTo()));
                        var6.endTag("", "RequestDataTo");
                    }
                }
            } else if(var5.getClass().getName() == DataConvertRcuModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1040) {
                    var11 = var1.iterator();

                    while(var11.hasNext()) {
                        DataConvertRcuModel var25 = (DataConvertRcuModel)var11.next();
                        var6.startTag("", "KeyValue");
                        var6.text(String.valueOf(var25.getKeyValue()));
                        var6.endTag("", "KeyValue");
                    }
                }
            } else if(var5.getClass().getName() == DataConvertFavorModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1055) {
                    var6.startTag("", "FavorRenamePos");
                    var6.text(String.valueOf(((DataConvertFavorModel)var1.get(0)).GetFavorIndex()));
                    var6.endTag("", "FavorRenamePos");
                    var6.startTag("", "FavorNewName");
                    var6.text(((DataConvertFavorModel)var1.get(0)).GetFavorName());
                    var6.endTag("", "FavorNewName");
                    var6.startTag("", "FavorGroupID");
                    var6.text("" + ((DataConvertFavorModel)var1.get(0)).getFavorTypeID());
                    var6.endTag("", "FavorGroupID");
                }
            } else if(var5.getClass().getName() == DataConvertChannelTypeModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1007) {
                    var6.startTag("", "IsFavList");
                    var6.text(String.valueOf(((DataConvertChannelTypeModel)var1.get(0)).getIsFavList()));
                    var6.endTag("", "IsFavList");
                    var6.startTag("", "SelectListType");
                    var6.text(String.valueOf(((DataConvertChannelTypeModel)var1.get(0)).getSelectListType()));
                    var6.endTag("", "SelectListType");
                }
            } else if(var5.getClass().getName() == DataConvertInputMethodModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1059) {
                    var6.startTag("", "KeyCode");
                    var6.text(String.valueOf(((DataConvertInputMethodModel)var1.get(0)).getKeyCode()));
                    var6.endTag("", "KeyCode");
                }
            } else if(var5.getClass().getName() == DataConvertSortModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1006) {
                    var6.startTag("", "SortType");
                    var6.text(String.valueOf(((DataConvertSortModel)var1.get(0)).getmSortType()));
                    var6.endTag("", "SortType");
                    var6.startTag("", "TvState");
                    var6.text(String.valueOf(((DataConvertSortModel)var1.get(0)).getmTvState()));
                    var6.endTag("", "TvState");
                }
            } else if(var5.getClass().getName() == DataConvertOneDataModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                var6.startTag("", "data");
                var6.text(((DataConvertOneDataModel)var1.get(0)).getData());
                var6.endTag("", "data");
            } else if(var5.getClass().getName() == DataConvertSatModel.class.getName()) {
                var6.attribute("", "request", String.valueOf(var2));
                if(var2 == 1060) {
                    var6.startTag("", "SatIndexSelected");
                    var6.text(String.valueOf(((DataConvertSatModel)var1.get(0)).getmSatIndex()));
                    var6.endTag("", "SatIndexSelected");
                }
            } else if(var5 instanceof Map) {
                var6.attribute("", "request", String.valueOf(var2));
                var11 = var1.iterator();

                while(var11.hasNext()) {
                    Map var26 = (Map)var11.next();
                    var8 = var26.keySet().iterator();

                    while(var8.hasNext()) {
                        String var9 = (String)var8.next();
                        var6.startTag("", var9);
                        var6.text((String)var26.get(var9));
                        var6.endTag("", var9);
                    }
                }
            } else if(var5 instanceof DataConvertChatMsgModel) {
                if(var2 == 1102) {
                    DataConvertChatMsgModel var16 = (DataConvertChatMsgModel)var1.get(0);
                    var6.attribute("", "request", String.valueOf(var2));
                    var6.startTag("", "parm");
                    var6.startTag("", "Timestamp");
                    var6.text(Long.toString(var16.getTimestamp()));
                    var6.endTag("", "Timestamp");
                    var6.startTag("", "Content");
                    var6.text(var16.getContent());
                    var6.endTag("", "Content");
                    var6.endTag("", "parm");
                }
            } else if(var5 instanceof GsChatSetting) {
                if(var2 == 1104) {
                    GsChatSetting var17 = (GsChatSetting)var1.get(0);
                    var6.attribute("", "request", String.valueOf(var2));
                    var6.startTag("", "parm");
                    var6.startTag("", "ShowWindow");
                    var6.text(String.valueOf(var17.getSHowWindow()));
                    var6.endTag("", "ShowWindow");
                    var6.startTag("", "WindowSize");
                    var6.text(String.valueOf(var17.getWindowSize()));
                    var6.endTag("", "WindowSize");
                    var6.startTag("", "WindowPosition");
                    var6.text(String.valueOf(var17.getWindowPosition()));
                    var6.endTag("", "WindowPosition");
                    var6.startTag("", "WindowTransparency");
                    var6.text(String.valueOf(var17.getWindowTransparency()));
                    var6.endTag("", "WindowTransparency");
                    var6.endTag("", "parm");
                }
            } else if(var5 instanceof GsChatUser) {
                if(var2 == 1103) {
                    var6.attribute("", "request", String.valueOf(var2));
                    var11 = var1.iterator();

                    while(var11.hasNext()) {
                        GsChatUser var27 = (GsChatUser)var11.next();
                        var6.startTag("", "parm");
                        var6.startTag("", "USERID");
                        var6.text(Integer.toString(var27.getUserID()));
                        var6.endTag("", "USERID");
                        var6.startTag("", "Username");
                        var6.text(var27.getUsername());
                        var6.endTag("", "Username");
                        var6.startTag("", "Action");
                        if(var27.getBlock()) {
                            var6.text("1");
                        } else {
                            var6.text("0");
                        }

                        var6.endTag("", "Action");
                        var6.endTag("", "parm");
                    }
                }
            } else if(var5 instanceof DataConvertUsernameModel && var2 == 1105) {
                DataConvertUsernameModel var19 = (DataConvertUsernameModel)var1.get(0);
                var6.attribute("", "request", String.valueOf(var2));
                var6.startTag("", "parm");
                var6.startTag("", "Username");
                var6.text(var19.getUsername());
                var6.endTag("", "Username");
                var6.endTag("", "parm");
            }
        }

        var6.endTag("", "Command");
        var6.endDocument();
        return var7.toString();
    }
}
