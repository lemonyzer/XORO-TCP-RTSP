package mktvsmart.screen.channel.procyon.;

import mktvsmart.screen.dataconvert.model.*;
import java.util.*;
import java.io.*;

public class Sat2ipUtil
{
    public static String getRtspUriBase(final String s) {
        return "rtsp:/" + s + ":554/";
    }

    public static String getRtspUriQuery(final DataConvertChannelModel dataConvertChannelModel) {
        final int int1 = Integer.parseInt(ChannelData.GetTpSubStringByPrgoramId(dataConvertChannelModel.GetProgramId()));
        final int int2 = Integer.parseInt(ChannelData.GetSatSubStringByPrgoramId(dataConvertChannelModel.GetProgramId()));
        final DataConvertTpModel dataConvertTpModel = new DataConvertTpModel();
        for (final DataConvertTpModel dataConvertTpModel2 : ChannelData.getInstance().getmAllTpList()) {
            if (dataConvertTpModel2.getTpIndex() == int1 && dataConvertTpModel2.getSatIndex() == int2) {
                dataConvertTpModel.setTpIndex(int1);
                dataConvertTpModel.setSatIndex(int2);
                dataConvertTpModel.setFec(dataConvertTpModel2.getFec());
                dataConvertTpModel.setFreq(dataConvertTpModel2.getFreq());
                dataConvertTpModel.setPol(dataConvertTpModel2.getPol());
                dataConvertTpModel.setSymRate(dataConvertTpModel2.getSymRate());
                break;
            }
        }
        final StringBuilder append = new StringBuilder("?alisatid=").append(dataConvertTpModel.getSatIndex()).append("&").append("freq=").append(dataConvertTpModel.getFreq()).append("&").append("pol=").append(dataConvertTpModel.getPol()).append("&").append("msys=");
        String s;
        if (dataConvertChannelModel.getModulationSystem() == 0) {
            s = "dvbs";
        }
        else {
            s = "dvbs2";
        }
        final StringBuilder append2 = append.append(s).append("&").append("mtype=");
        String s2;
        if (dataConvertChannelModel.getModulationType() == 0) {
            s2 = "qpsk";
        }
        else {
            s2 = "8psk";
        }
        final StringBuilder append3 = append2.append(s2).append("&").append("ro=").append(dataConvertChannelModel.getRollOff() / 100.0f).append("&").append("plts=");
        String s3;
        if (dataConvertChannelModel.getPilotTones() == 0) {
            s3 = "off";
        }
        else {
            s3 = "on";
        }
        final StringBuilder append4 = append3.append(s3).append("&").append("sr=").append(dataConvertTpModel.getSymRate()).append("&").append("fec=").append(dataConvertTpModel.getFec()).append("&").append("camode=").append(dataConvertChannelModel.GetIsProgramScramble()).append("&").append("vpid=").append(dataConvertChannelModel.getVideoPid()).append("&").append("apid=").append(dataConvertChannelModel.getAudioPid()).append("&").append("ttxpid=").append(dataConvertChannelModel.getTtxPid()).append("&").append("subtpid=");
        Serializable s4;
        if (dataConvertChannelModel.getSubPid() == null) {
            s4 = 0;
        }
        else {
            s4 = dataConvertChannelModel.getSubPid();
        }
        final StringBuilder append5 = append4.append(s4).append("&").append("pmt=").append(dataConvertChannelModel.getPmtPid()).append("&").append("prognumber=").append(ChannelData.GetProgSubStringByPrgoramId(dataConvertChannelModel.GetProgramId())).append("&").append("pids=").append(dataConvertChannelModel.getVideoPid()).append(",").append(dataConvertChannelModel.getAudioPid()).append(",").append(dataConvertChannelModel.getTtxPid()).append(",");
        Serializable s5;
        if (dataConvertChannelModel.getSubPid() == null) {
            s5 = 0;
        }
        else {
            s5 = dataConvertChannelModel.getSubPid();
        }
        return append5.append(s5).append(",").append(dataConvertChannelModel.getPmtPid()).toString();
    }
}
