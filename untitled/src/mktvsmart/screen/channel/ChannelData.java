package mktvsmart.screen.channel;

import android.os.Message;
import android.util.Log;
import mktvsmart.screen.exception.ProgramNotFoundException;

public class ChannelData {
    private static mktvsmart.screen.channel.ChannelData INSTANCE;
    private java.util.List mAllSatList;
    private java.util.List mAllTpList;
    private java.util.List mRadioChannelList;
    private java.util.List mTvChannelList;

    static
    {
        INSTANCE = null;
    }

    private ChannelData()
    {
        super();
        this.mTvChannelList = (java.util.List)(Object)new java.util.ArrayList();
        this.mRadioChannelList = (java.util.List)(Object)new java.util.ArrayList();
        this.mAllTpList = (java.util.List)(Object)new java.util.ArrayList();
        this.mAllSatList = (java.util.List)(Object)new java.util.ArrayList();
    }

    private java.util.List GetChannelListByCHListType(java.util.List a, int i)
    {
        java.util.ArrayList a0 = null;
        if (a != null)
        {
            boolean b = (mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() != 0) ? i > 2 : i > 3;
            a0 = new java.util.ArrayList();
            if (b)
            {
                Object a1 = a;
                int i0 = 0;
                while(i0 < ((java.util.List)a1).size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertChannelModel a2 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i0);
                    if (this.isChannelBelongToSelectSat(a2) && a2.mfavGroupIDs.contains((Object)Integer.valueOf(i - 4)))
                    {
                        ((java.util.List)(Object)a0).add((Object)a2);
                    }
                    i0 = i0 + 1;
                }
            }
            else
            {
                Object a3 = a;
                int i1 = 0;
                while(i1 < ((java.util.List)a3).size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertChannelModel a4 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a3).get(i1);
                    if (this.isChannelBelongToSelectSat(a4))
                    {
                        boolean b0 = false;
                        switch(i){
                            case 3: {
                                b0 = a4.getIsProgramHd() == 1;
                                break;
                            }
                            case 2: {
                                b0 = a4.GetIsProgramScramble() == 1;
                                break;
                            }
                            case 1: {
                                b0 = a4.GetIsProgramScramble() != 1;
                                break;
                            }
                            case 0: {
                                b0 = true;
                                break;
                            }
                            default: {
                                b0 = false;
                            }
                        }
                        if (b0)
                        {
                            ((java.util.List)(Object)a0).add((Object)a4);
                        }
                    }
                    i1 = i1 + 1;
                }
            }
        }
        else
        {
            a0 = null;
        }
        return (java.util.List)(Object)a0;
    }

    public static String GetProgSubStringByPrgoramId(String s)
    {
        if (!mktvsmart.screen.channel.ChannelData.IsProgramIdValid(s))
        {
            return "";
        }
        return s.substring(9, 14);
    }

    public static String GetSatSubStringByPrgoramId(String s)
    {
        if (!mktvsmart.screen.channel.ChannelData.IsProgramIdValid(s))
        {
            return "";
        }
        return s.substring(0, 4);
    }

    public static String GetSatTpSubStringByPrgoramId(String s)
    {
        if (!mktvsmart.screen.channel.ChannelData.IsProgramIdValid(s))
        {
            return "";
        }
        return s.substring(0, 9);
    }

    public static String GetTpSubStringByPrgoramId(String s)
    {
        if (!mktvsmart.screen.channel.ChannelData.IsProgramIdValid(s))
        {
            return "";
        }
        return s.substring(4, 9);
    }

    public static boolean IsProgramIdValid(String s)
    {
        if (s.length() == 14)
        {
            return true;
        }
        return false;
    }

    private void addChannelToRadioChannelList(mktvsmart.screen.dataconvert.model.DataConvertChannelModel a, java.util.List a0)
    {
        if (a.GetProgramIndex() != mktvsmart.screen.GMScreenGlobalInfo.getmMaxProgramNumber() - 1)
        {
            if (a0.size() > 0)
            {
                if (a.GetProgramIndex() == ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a0.get(a0.size() - 1)).GetProgramIndex() - 1)
                {
                    a0.add((Object)a);
                    return;
                }
                if (a.GetProgramIndex() >= ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a0.get(a0.size() - 1)).GetProgramIndex())
                {
                    a0.set(this.getIndexByModel(a, a0), (Object)a);
                    return;
                }
            }
        }
        else
        {
            a0.clear();
            a0.add((Object)a);
        }
    }

    private void addChannelToTvChannelList(mktvsmart.screen.dataconvert.model.DataConvertChannelModel a, java.util.List a0)
    {
        if (a.GetProgramIndex() != 0)
        {
            if (a0.size() > 0)
            {
                if (a.GetProgramIndex() == ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a0.get(a0.size() - 1)).GetProgramIndex() + 1)
                {
                    a0.add((Object)a);
                    return;
                }
                if (a.GetProgramIndex() <= ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a0.get(a0.size() - 1)).GetProgramIndex())
                {
                    a0.set(this.getIndexByModel(a, a0), (Object)a);
                    return;
                }
            }
        }
        else
        {
            a0.clear();
            a0.add((Object)a);
        }
    }

    private int getIndexByModel(mktvsmart.screen.dataconvert.model.DataConvertChannelModel a, java.util.List a0)
    {
        if (a0 == null)
        {
            return 0;
        }
        Object a1 = a0;
        int i = 0;
        while(true)
        {
            if (i < ((java.util.List)a1).size())
            {
                if (a.GetProgramIndex() != ((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i)).GetProgramIndex())
                {
                    i = i + 1;
                    continue;
                }
            }
            else
            {
                i = 0;
            }
            return i;
        }
    }

    public static mktvsmart.screen.channel.ChannelData getInstance()
    {
        //monenter(mktvsmart.screen.channel.ChannelData.class);
        mktvsmart.screen.channel.ChannelData a = INSTANCE;
        label0: {
            Throwable a0 = null;
            if (a != null)
            {
                break label0;
            }
            try
            {
                INSTANCE = new mktvsmart.screen.channel.ChannelData();
                break label0;
            }
            catch(Throwable a1)
            {
                a0 = a1;
            }
            //monexit(mktvsmart.screen.channel.ChannelData.class);
            try {
                throw a0;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        mktvsmart.screen.channel.ChannelData a2 = INSTANCE;
        //monexit(mktvsmart.screen.channel.ChannelData.class);
        return a2;
    }

    public static java.util.List getSameTpListByProg(mktvsmart.screen.dataconvert.model.DataConvertChannelModel a, java.util.List a0)
    {
        java.util.ArrayList a1 = new java.util.ArrayList();
        Object a2 = a0.iterator();
        while(((java.util.Iterator)a2).hasNext())
        {
            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a3 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a2).next();
            if (mktvsmart.screen.channel.ChannelData.GetSatTpSubStringByPrgoramId(a3.GetProgramId()).equals((Object)mktvsmart.screen.channel.ChannelData.GetSatTpSubStringByPrgoramId(a.GetProgramId())))
            {
                ((java.util.List)(Object)a1).add((Object)a3);
            }
        }
        return (java.util.List)(Object)a1;
    }

    /**
     * Param a == satList
     * Param a0 == chanList
     * Param s == Fav Groupe Name | SatList Name
     * @param a
     * @param a0
     * @param s
     * @return
     */
    public static java.util.List getSatList(java.util.List a, java.util.List a0, String s)
    {
        java.util.ArrayList a1 = new java.util.ArrayList();
        if (a0 != null)
        {
            mktvsmart.screen.dataconvert.model.DataConvertSatModel a2 = new mktvsmart.screen.dataconvert.model.DataConvertSatModel();
            a2.setmSatIndex(mktvsmart.screen.GMScreenGlobalInfo.getIndexOfAllSat());
            a2.setmSatName(s);
            ((java.util.List)(Object)a1).add((Object)a2);
            java.util.Iterator a3 = a0.iterator();
            Object a4 = a;
            Object a5 = a3;
            label0: while(((java.util.Iterator)a5).hasNext())
            {
                int i = Integer.parseInt(mktvsmart.screen.channel.ChannelData.GetSatSubStringByPrgoramId(((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a5).next()).GetProgramId()));
                int i0 = 0;
                label1: while(true)
                {
                    if (i0 < ((java.util.List)(Object)a1).size() && i != ((mktvsmart.screen.dataconvert.model.DataConvertSatModel)((java.util.List)(Object)a1).get(i0)).getmSatIndex())
                    {
                        i0 = i0 + 1;
                        continue label1;
                    }
                    if (i0 != ((java.util.List)(Object)a1).size())
                    {
                        continue label0;
                    }
                    Object a6 = ((java.util.List)a4).iterator();
                    while(((java.util.Iterator)a6).hasNext())
                    {
                        mktvsmart.screen.dataconvert.model.DataConvertSatModel a7 = (mktvsmart.screen.dataconvert.model.DataConvertSatModel)((java.util.Iterator)a6).next();
                        if (a7.getmSatIndex() == i)
                        {
                            mktvsmart.screen.dataconvert.model.DataConvertSatModel a8 = new mktvsmart.screen.dataconvert.model.DataConvertSatModel();
                            a8.setmSatName(a7.getmSatName());
                            a8.setmSatIndex(i);
                            a8.setmSatAngle(a7.getmSatAngle());
                            a8.setmSatDir(a7.getmSatDir());
                            ((java.util.List)(Object)a1).add((Object)a8);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        else
        {
            a1 = null;
        }
        return (java.util.List)(Object)a1;
    }

    private boolean isChannelBelongToSelectSat(mktvsmart.screen.dataconvert.model.DataConvertChannelModel a)
    {
        return mktvsmart.screen.GMScreenGlobalInfo.getCurStbInfo().getmSatEnable() != 1 || mktvsmart.screen.GMScreenGlobalInfo.getmSatIndexSelected() == mktvsmart.screen.GMScreenGlobalInfo.getIndexOfAllSat() || Integer.parseInt(mktvsmart.screen.channel.ChannelData.GetSatSubStringByPrgoramId(a.GetProgramId())) == mktvsmart.screen.GMScreenGlobalInfo.getmSatIndexSelected();
    }

    private boolean isRadioProgramNumOrderOfSmallestToLargest()
    {
        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbPlatform()){
            case 12: case 30: case 31: case 32: case 71: case 72: case 74: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    private Object readResolve()
    {
        return mktvsmart.screen.channel.ChannelData.getInstance();
    }


    // a received, xml-parsed mixed list
    // a0 Tv Channel List
    // a1 Radio Channel List
    private void separateRadioAndTv(java.util.List a, java.util.List a0, java.util.List a1)
    {
        if (a != null && a.size() != 0)
        {
            if (this.isRadioProgramNumOrderOfSmallestToLargest())
            {
                // XORO platform == 8 => isRadioProgramNumOrderOfSmallestToLargest = FALSE
                if (((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)a.get(0)).GetProgramIndex() == 0)
                {
                    a1.clear();
                    a0.clear();
                }
                Object a2 = a;
                Object a3 = a0;
                Object a4 = a1;
                int i = 0;
                while(i < ((java.util.List)a2).size())
                {
                    if (((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a2).get(i)).getChannelTpye() != 1)
                    {
                        ((java.util.List)a3).add((Object)(mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a2).get(i));
                    }
                    else
                    {
                        ((java.util.List)a4).add((Object)(mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a2).get(i));
                    }
                    i = i + 1;
                }
            }
            else
            {
                // XORO
                Object a5 = a;
                Object a6 = a0;
                Object a7 = a1;
                int i0 = 0;
                while(i0 < ((java.util.List)a5).size())
                {
                    mktvsmart.screen.dataconvert.model.DataConvertChannelModel a8 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a5).get(i0);
                    if (a8.getChannelTpye() != 1)
                    {
                        this.addChannelToTvChannelList(a8, (java.util.List)a6);
                    }
                    else
                    {
                        this.addChannelToRadioChannelList(a8, (java.util.List)a7);
                    }
                    i0 = i0 + 1;
                }
                System.out.println("separateRadioAndTv separated " + i0 + " elements");
            }
            System.out.println("separateRadioAndTv results");
            System.out.println("separateRadioAndTv mTvChannelList.size() =" + mTvChannelList.size());
            System.out.println("separateRadioAndTv mTvChannelList.size() =" + mRadioChannelList.size());
            Log.d("separateRadioAndTv", "");
            Log.d("separateRadioAndTv", "");
            Log.d("separateRadioAndTv", "");

        }
    }

    public boolean canSat2ipChannelPlay(mktvsmart.screen.dataconvert.model.DataConvertChannelModel a, mktvsmart.screen.dataconvert.model.DataConvertChannelModel a0)
    {
        Boolean a1 = Boolean.valueOf(true);
        if (a != null && a0 != null)
        {
            int i = a.getIsTuner2();
            int i0 = a0.getIsTuner2();
            if (i == i0)
            {
                a1 = (mktvsmart.screen.channel.ChannelData.GetSatTpSubStringByPrgoramId(a0.GetProgramId()).equals((Object)mktvsmart.screen.channel.ChannelData.GetSatTpSubStringByPrgoramId(a.GetProgramId()))) ? Boolean.valueOf(true) : Boolean.valueOf(false);
            }
            return a1.booleanValue();
        }
        return false;
    }

    public void channelListOfTvOrRadioChanged(java.util.List a)
    {
        if (mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() != 0)
        {
            this.mRadioChannelList = a;
            return;
        }
        this.mTvChannelList = a;
    }

    public void clearTVRadioProgramList()
    {
        this.mTvChannelList.clear();
        this.mRadioChannelList.clear();
    }

    public java.util.List getChannelListByProgramType(java.util.List a, int i)
    {
        switch(mktvsmart.screen.GMScreenGlobalInfo.getCurStbPlatform()){
            case 30: case 31: case 32: case 71: case 72: case 74: {
                return this.GetChannelListByCHListType(a, i);
            }
            default: {
                java.util.ArrayList a0 = null;
                label2: {
                    label0: {
                        label1: {
                            if (a == null)
                            {
                                break label1;
                            }
                            if (i < 12)
                            {
                                break label0;
                            }
                        }
                        a0 = null;
                        break label2;
                    }
                    int i0 = i - 4;
                    a0 = new java.util.ArrayList();
                    Object a1 = a;
                    int i1 = 0;
                    while(i1 < ((java.util.List)a1).size())
                    {
                        mktvsmart.screen.dataconvert.model.DataConvertChannelModel a2 = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a1).get(i1);
                        if (this.isChannelBelongToSelectSat(a2))
                        {
                            boolean b = false;
                            switch(i){
                                case 11: {
                                    b = (a2.GetFavMark() & 1 << i0) > 0;
                                    break;
                                }
                                case 10: {
                                    b = (a2.GetFavMark() & 1 << i0) > 0;
                                    break;
                                }
                                case 9: {
                                    b = (a2.GetFavMark() & 1 << i0) > 0;
                                    break;
                                }
                                case 8: {
                                    b = (a2.GetFavMark() & 1 << i0) > 0;
                                    break;
                                }
                                case 7: {
                                    b = (a2.GetFavMark() & 1 << i0) > 0;
                                    break;
                                }
                                case 6: {
                                    b = (a2.GetFavMark() & 1 << i0) > 0;
                                    break;
                                }
                                case 5: {
                                    b = (a2.GetFavMark() & 1 << i0) > 0;
                                    break;
                                }
                                case 4: {
                                    b = (a2.GetFavMark() & 1 << i0) > 0;
                                    break;
                                }
                                case 3: {
                                    b = a2.getIsProgramHd() == 1;
                                    break;
                                }
                                case 2: {
                                    b = a2.GetIsProgramScramble() == 1;
                                    break;
                                }
                                case 1: {
                                    b = a2.GetIsProgramScramble() != 1;
                                    break;
                                }
                                case 0: {
                                    b = true;
                                    break;
                                }
                                default: {
                                    b = false;
                                }
                            }
                            if (b)
                            {
                                ((java.util.List)(Object)a0).add((Object)a2);
                            }
                        }
                        i1 = i1 + 1;
                    }
                }
                return (java.util.List)(Object)a0;
            }
        }
    }

    public java.util.List getChannelListByTvRadioType()
    {
        java.util.ArrayList a = new java.util.ArrayList();
        if (mktvsmart.screen.dataconvert.model.DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() != 0)
        {
            return this.mRadioChannelList;
        }
        return this.mTvChannelList;
    }

    public mktvsmart.screen.dataconvert.model.DataConvertChannelModel getCurrentPlayingProgram()
    {
        int i = 0;
        mktvsmart.screen.dataconvert.model.DataConvertChannelModel a = null;
        while(true)
        {
            if (i < this.getTotalProgramNum())
            {
                a = this.getProgramByIndex(i);
                if (a.getIsPlaying() != 1)
                {
                    i = i + 1;
                    continue;
                }
            }
            return a;
        }
    }

    public int getIndexByProgIdInCurTvRadioProgList(String s)
    {
        int i = this.getChannelListByTvRadioType().size();
        java.util.List a = this.getChannelListByTvRadioType();
        int i0 = 0;
        Object a0 = a;
        while(true)
        {
            if (i0 < i && !s.equals((Object)((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.List)a0).get(i0)).GetProgramId()))
            {
                i0 = i0 + 1;
                continue;
            }
            if (i0 == i)
            {
                i0 = -1;
            }
            return i0;
        }
    }

    public mktvsmart.screen.dataconvert.model.DataConvertChannelModel getProgramByIndex(int i)
    {
        if (i < this.mTvChannelList.size())
        {
            return (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)this.mTvChannelList.get(i);
        }
        return (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)this.mRadioChannelList.get(i - this.mTvChannelList.size());
    }

    public mktvsmart.screen.dataconvert.model.DataConvertChannelModel getProgramByProgramId(String s) throws ProgramNotFoundException {
        int i = 0;
        mktvsmart.screen.dataconvert.model.DataConvertChannelModel a = null;
        while(true)
        {
            if (i < this.getTotalProgramNum())
            {
                a = this.getProgramByIndex(i);
                if (!a.GetProgramId().equals((Object)s))
                {
                    i = i + 1;
                    continue;
                }
            }
            if (s != null && a != null)
            {
                return a;
            }
            throw new mktvsmart.screen.exception.ProgramNotFoundException(new StringBuilder("Not found the program: ").append(s).toString());
        }
    }

    public int getTotalProgramNum()
    {
        return this.mTvChannelList.size() + this.mRadioChannelList.size();
    }

    public java.util.List getmAllSatList()
    {
        return this.mAllSatList;
    }

    public java.util.List getmAllTpList()
    {
        return this.mAllTpList;
    }

    public java.util.List getmRadioChannelList()
    {
        return this.mRadioChannelList;
    }

    public java.util.List getmTvChannelList()
    {
        return this.mTvChannelList;
    }

    public java.util.List initChannelListData(Message msg)
    {
        byte[] a = msg.getData().getByteArray("ReceivedData");

        Object a0 = null;
        mktvsmart.screen.dataconvert.parser.DataParser a1 = mktvsmart.screen.dataconvert.parser.ParserFactory.getParser();
        java.util.ArrayList a2 = new java.util.ArrayList();
        try
        {
            a0 = a1.parse(msg, 0);
//            a0 = a1.parse((java.io.InputStream)new java.io.ByteArrayInputStream(a, 0, a.length), 0);
        }
        catch(Exception a3)
        {
            a3.printStackTrace();
            a0 = a2;
        }
        System.out.println("initChannelListData parsed " + ((java.util.List)a0).size() + " elements");
        this.separateRadioAndTv((java.util.List)a0, this.mTvChannelList, this.mRadioChannelList);
        return (java.util.List)a0;
    }

    public void initSatList(Message msg)
    {
        byte[] a = msg.getData().getByteArray("ReceivedData");

        mktvsmart.screen.dataconvert.parser.DataParser a0 = mktvsmart.screen.dataconvert.parser.ParserFactory.getParser();
        try
        {
            this.mAllSatList = a0.parse(msg, 18);
//            this.mAllSatList = a0.parse((java.io.InputStream)new java.io.ByteArrayInputStream(a, 0, a.length), 18);
        }
        catch(Exception a1)
        {
            a1.printStackTrace();
            return;
        }
    }

    public void initTpList(Message msg)
    {
        byte[] a = msg.getData().getByteArray("ReceivedData");


        mktvsmart.screen.dataconvert.parser.DataParser a0 = mktvsmart.screen.dataconvert.parser.ParserFactory.getParser();
        try
        {
            this.mAllTpList = a0.parse(msg, 19);
//            this.mAllTpList = a0.parse((java.io.InputStream)new java.io.ByteArrayInputStream(a, 0, a.length), 19);
        }
        catch(Exception a1)
        {
            a1.printStackTrace();
            return;
        }
    }

    public void releaseData()
    {
        if (this.mTvChannelList != null)
        {
            this.mTvChannelList.clear();
        }
        if (this.mRadioChannelList != null)
        {
            this.mRadioChannelList.clear();
        }
        if (this.mAllTpList != null)
        {
            this.mAllTpList.clear();
        }
        if (this.mAllSatList != null)
        {
            this.mAllSatList.clear();
        }
    }

    public void setmAllSatList(java.util.List a)
    {
        this.mAllSatList = a;
    }

    public void setmAllTpList(java.util.List a)
    {
        this.mAllTpList = a;
    }

    public void setmRadioChannelList(java.util.List a)
    {
        this.mRadioChannelList = a;
    }

    public void setmTvChannelList(java.util.List a)
    {
        this.mTvChannelList = a;
    }
}
