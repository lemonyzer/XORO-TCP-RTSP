package mktvsmart.screen.channel;

public class Sat2ipUtil {
    public Sat2ipUtil()
    {
        super();
    }

    public static String getRtspUriBase(String s)
    {
        return new StringBuilder("rtsp:/").append(s).append(":554/").toString();
    }

    public static String getRtspUriQuery(mktvsmart.screen.dataconvert.model.DataConvertChannelModel a)
    {
        int i = Integer.parseInt(mktvsmart.screen.channel.ChannelData.GetTpSubStringByPrgoramId(a.GetProgramId()));
        int i0 = Integer.parseInt(mktvsmart.screen.channel.ChannelData.GetSatSubStringByPrgoramId(a.GetProgramId()));
        mktvsmart.screen.dataconvert.model.DataConvertTpModel a0 = new mktvsmart.screen.dataconvert.model.DataConvertTpModel();
        Object a1 = mktvsmart.screen.channel.ChannelData.getInstance().getmAllTpList().iterator();
        while(true)
        {
            if (((java.util.Iterator)a1).hasNext())
            {
                mktvsmart.screen.dataconvert.model.DataConvertTpModel a2 = (mktvsmart.screen.dataconvert.model.DataConvertTpModel)((java.util.Iterator)a1).next();
                if (a2.getTpIndex() != i)
                {
                    continue;
                }
                if (a2.getSatIndex() != i0)
                {
                    continue;
                }
                a0.setTpIndex(i);
                a0.setSatIndex(i0);
                a0.setFec(a2.getFec());
                a0.setFreq(a2.getFreq());
                int i1 = a2.getPol();
                a0.setPol((char)i1);
                a0.setSymRate(a2.getSymRate());
            }
            StringBuilder a3 = new StringBuilder("?alisatid=").append(a0.getSatIndex()).append("&").append("freq=").append(a0.getFreq()).append("&").append("pol=");
            int i2 = a0.getPol();
            StringBuilder a4 = a3.append((char)i2).append("&").append("msys=").append((a.getModulationSystem() != 0) ? "dvbs2" : "dvbs").append("&").append("mtype=").append((a.getModulationType() != 0) ? "8psk" : "qpsk").append("&").append("ro=").append((float)a.getRollOff() / 100f).append("&").append("plts=").append((a.getPilotTones() != 0) ? "on" : "off").append("&").append("sr=").append(a0.getSymRate()).append("&").append("fec=").append(a0.getFec()).append("&").append("camode=").append(a.GetIsProgramScramble()).append("&").append("vpid=").append(a.getVideoPid()).append("&").append("apid=").append(a.getAudioPid()).append("&").append("ttxpid=").append(a.getTtxPid()).append("&").append("subtpid=");
            Object a5 = (a.getSubPid() != null) ? a.getSubPid() : Integer.valueOf(0);
            StringBuilder a6 = a4.append(a5).append("&").append("pmt=").append(a.getPmtPid()).append("&").append("prognumber=").append(mktvsmart.screen.channel.ChannelData.GetProgSubStringByPrgoramId(a.GetProgramId())).append("&").append("pids=").append(a.getVideoPid()).append(",").append(a.getAudioPid()).append(",").append(a.getTtxPid()).append(",");
            Object a7 = (a.getSubPid() != null) ? a.getSubPid() : Integer.valueOf(0);
            return a6.append(a7).append(",").append(a.getPmtPid()).toString();
        }
    }
}
