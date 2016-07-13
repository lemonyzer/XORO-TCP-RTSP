package mktvsmart.screen.channel.procyon;

import mktvsmart.screen.dataconvert.model.*;
import mktvsmart.screen.*;
import java.util.*;
import mktvsmart.screen.exception.*;
import java.io.*;
import mktvsmart.screen.dataconvert.parser.*;

public class ChannelData
{
    private static ChannelData INSTANCE;
    private List<DataConvertSatModel> mAllSatList;
    private List<DataConvertTpModel> mAllTpList;
    private List<DataConvertChannelModel> mRadioChannelList;
    private List<DataConvertChannelModel> mTvChannelList;

    static {
        ChannelData.INSTANCE = null;
    }

    private ChannelData() {
        this.mTvChannelList = new ArrayList<DataConvertChannelModel>();
        this.mRadioChannelList = new ArrayList<DataConvertChannelModel>();
        this.mAllTpList = new ArrayList<DataConvertTpModel>();
        this.mAllSatList = new ArrayList<DataConvertSatModel>();
    }

    private List<DataConvertChannelModel> GetChannelListByCHListType(final List<DataConvertChannelModel> list, final int n) {
        List<DataConvertChannelModel> list2;
        if (list == null) {
            list2 = null;
        }
        else {
            int n2 = 0;
            if (DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() == 0) {
                if (n > 3) {
                    n2 = 1;
                }
            }
            else if (n > 2) {
                n2 = 1;
            }
            final ArrayList<DataConvertChannelModel> list3 = new ArrayList<DataConvertChannelModel>();
            if (n2 != 0) {
                int n3 = 0;
                while (true) {
                    list2 = list3;
                    if (n3 >= list.size()) {
                        break;
                    }
                    final DataConvertChannelModel dataConvertChannelModel = list.get(n3);
                    if (this.isChannelBelongToSelectSat(dataConvertChannelModel) && dataConvertChannelModel.mfavGroupIDs.contains(n - 4)) {
                        list3.add(dataConvertChannelModel);
                    }
                    ++n3;
                }
            }
            else {
                int n4 = 0;
                while (true) {
                    list2 = list3;
                    if (n4 >= list.size()) {
                        break;
                    }
                    int n5 = 0;
                    final DataConvertChannelModel dataConvertChannelModel2 = list.get(n4);
                    if (this.isChannelBelongToSelectSat(dataConvertChannelModel2)) {
                        switch (n) {
                            case 0: {
                                n5 = 1;
                                break;
                            }
                            case 1: {
                                if (dataConvertChannelModel2.GetIsProgramScramble() != 1) {
                                    n5 = 1;
                                    break;
                                }
                                break;
                            }
                            case 2: {
                                if (dataConvertChannelModel2.GetIsProgramScramble() == 1) {
                                    n5 = 1;
                                    break;
                                }
                                break;
                            }
                            case 3: {
                                if (dataConvertChannelModel2.getIsProgramHd() == 1) {
                                    n5 = 1;
                                    break;
                                }
                                break;
                            }
                        }
                        if (n5 != 0) {
                            list3.add(dataConvertChannelModel2);
                        }
                    }
                    ++n4;
                }
            }
        }
        return list2;
    }

    public static String GetProgSubStringByPrgoramId(final String s) {
        if (!IsProgramIdValid(s)) {
            return "";
        }
        return s.substring(9, 14);
    }

    public static String GetSatSubStringByPrgoramId(final String s) {
        if (!IsProgramIdValid(s)) {
            return "";
        }
        return s.substring(0, 4);
    }

    public static String GetSatTpSubStringByPrgoramId(final String s) {
        if (!IsProgramIdValid(s)) {
            return "";
        }
        return s.substring(0, 9);
    }

    public static String GetTpSubStringByPrgoramId(final String s) {
        if (!IsProgramIdValid(s)) {
            return "";
        }
        return s.substring(4, 9);
    }

    public static boolean IsProgramIdValid(final String s) {
        return s.length() == 14;
    }

    private void addChannelToRadioChannelList(final DataConvertChannelModel dataConvertChannelModel, final List<DataConvertChannelModel> list) {
        if (dataConvertChannelModel.GetProgramIndex() == GMScreenGlobalInfo.getmMaxProgramNumber() - 1) {
            list.clear();
            list.add(dataConvertChannelModel);
        }
        else if (list.size() > 0) {
            if (dataConvertChannelModel.GetProgramIndex() == list.get(list.size() - 1).GetProgramIndex() - 1) {
                list.add(dataConvertChannelModel);
                return;
            }
            if (dataConvertChannelModel.GetProgramIndex() >= list.get(list.size() - 1).GetProgramIndex()) {
                list.set(this.getIndexByModel(dataConvertChannelModel, list), dataConvertChannelModel);
            }
        }
    }

    private void addChannelToTvChannelList(final DataConvertChannelModel dataConvertChannelModel, final List<DataConvertChannelModel> list) {
        if (dataConvertChannelModel.GetProgramIndex() == 0) {
            list.clear();
            list.add(dataConvertChannelModel);
        }
        else if (list.size() > 0) {
            if (dataConvertChannelModel.GetProgramIndex() == list.get(list.size() - 1).GetProgramIndex() + 1) {
                list.add(dataConvertChannelModel);
                return;
            }
            if (dataConvertChannelModel.GetProgramIndex() <= list.get(list.size() - 1).GetProgramIndex()) {
                list.set(this.getIndexByModel(dataConvertChannelModel, list), dataConvertChannelModel);
            }
        }
    }

    private int getIndexByModel(final DataConvertChannelModel dataConvertChannelModel, final List<DataConvertChannelModel> list) {
        final int n = 0;
        if (list == null) {
            return 0;
        }
        for (int i = 0; i < list.size(); ++i) {
            if (dataConvertChannelModel.GetProgramIndex() == list.get(i).GetProgramIndex()) {
                return i;
            }
        }
        return n;
    }

    public static ChannelData getInstance() {
        synchronized (ChannelData.class) {
            if (ChannelData.INSTANCE == null) {
                ChannelData.INSTANCE = new ChannelData();
            }
            return ChannelData.INSTANCE;
        }
    }

    public static List<DataConvertChannelModel> getSameTpListByProg(final DataConvertChannelModel dataConvertChannelModel, final List<DataConvertChannelModel> list) {
        final ArrayList<DataConvertChannelModel> list2 = new ArrayList<DataConvertChannelModel>();
        for (final DataConvertChannelModel dataConvertChannelModel2 : list) {
            if (GetSatTpSubStringByPrgoramId(dataConvertChannelModel2.GetProgramId()).equals(GetSatTpSubStringByPrgoramId(dataConvertChannelModel.GetProgramId()))) {
                list2.add(dataConvertChannelModel2);
            }
        }
        return list2;
    }

    public static List<DataConvertSatModel> getSatList(final List<DataConvertSatModel> list, final List<DataConvertChannelModel> list2, final String s) {
        final ArrayList<DataConvertSatModel> list3 = new ArrayList<DataConvertSatModel>();
        List<DataConvertSatModel> list4;
        if (list2 == null) {
            list4 = null;
        }
        else {
            final DataConvertSatModel dataConvertSatModel = new DataConvertSatModel();
            dataConvertSatModel.setmSatIndex(GMScreenGlobalInfo.getIndexOfAllSat());
            dataConvertSatModel.setmSatName(s);
            list3.add(dataConvertSatModel);
            final Iterator<DataConvertChannelModel> iterator = list2.iterator();
            while (true) {
                list4 = (List<DataConvertSatModel>)list3;

                if (!iterator.hasNext()) {
                    break;
                }
                int int1;
                int n;
                for (int1 = Integer.parseInt(GetSatSubStringByPrgoramId(iterator.next().GetProgramId())), n = 0; n < list3.size() && int1 != list3.get(n).getmSatIndex(); ++n) {}
                if (n != list3.size()) {
                    continue;
                }
                for (final DataConvertSatModel dataConvertSatModel2 : list) {
                    if (dataConvertSatModel2.getmSatIndex() == int1) {
                        final DataConvertSatModel dataConvertSatModel3 = new DataConvertSatModel();
                        dataConvertSatModel3.setmSatName(dataConvertSatModel2.getmSatName());
                        dataConvertSatModel3.setmSatIndex(int1);
                        dataConvertSatModel3.setmSatAngle(dataConvertSatModel2.getmSatAngle());
                        dataConvertSatModel3.setmSatDir(dataConvertSatModel2.getmSatDir());
                        list3.add(dataConvertSatModel3);
                        break;
                    }
                }
            }
        }
        return list4;
    }

    private boolean isChannelBelongToSelectSat(final DataConvertChannelModel dataConvertChannelModel) {
        boolean b2;
        final boolean b = b2 = true;
        if (GMScreenGlobalInfo.getCurStbInfo().getmSatEnable() == 1) {
            b2 = b;
            if (GMScreenGlobalInfo.getmSatIndexSelected() != GMScreenGlobalInfo.getIndexOfAllSat()) {
                b2 = b;
                if (Integer.parseInt(GetSatSubStringByPrgoramId(dataConvertChannelModel.GetProgramId())) != GMScreenGlobalInfo.getmSatIndexSelected()) {
                    b2 = false;
                }
            }
        }
        return b2;
    }

    private boolean isRadioProgramNumOrderOfSmallestToLargest() {
        switch (GMScreenGlobalInfo.getCurStbPlatform()) {
            default: {
                return false;
            }
            case 12:
            case 30:
            case 31:
            case 32:
            case 71:
            case 72:
            case 74: {
                return true;
            }
        }
    }

    private Object readResolve() {
        return getInstance();
    }

    private void separateRadioAndTv(final List<DataConvertChannelModel> list, final List<DataConvertChannelModel> list2, final List<DataConvertChannelModel> list3) {
        if (list != null && list.size() != 0) {
            if (this.isRadioProgramNumOrderOfSmallestToLargest()) {
                if (list.get(0).GetProgramIndex() == 0) {
                    list3.clear();
                    list2.clear();
                }
                for (int i = 0; i < list.size(); ++i) {
                    if (list.get(i).getChannelTpye() == 1) {
                        list3.add(list.get(i));
                    }
                    else {
                        list2.add(list.get(i));
                    }
                }
            }
            else {
                for (int j = 0; j < list.size(); ++j) {
                    final DataConvertChannelModel dataConvertChannelModel = list.get(j);
                    if (dataConvertChannelModel.getChannelTpye() == 1) {
                        this.addChannelToRadioChannelList(dataConvertChannelModel, list3);
                    }
                    else {
                        this.addChannelToTvChannelList(dataConvertChannelModel, list2);
                    }
                }
            }
        }
    }

    public boolean canSat2ipChannelPlay(final DataConvertChannelModel dataConvertChannelModel, final DataConvertChannelModel dataConvertChannelModel2) {
        Boolean b = true;
        if (dataConvertChannelModel == null || dataConvertChannelModel2 == null) {
            return false;
        }
        if (dataConvertChannelModel.getIsTuner2() == dataConvertChannelModel2.getIsTuner2()) {
            if (GetSatTpSubStringByPrgoramId(dataConvertChannelModel2.GetProgramId()).equals(GetSatTpSubStringByPrgoramId(dataConvertChannelModel.GetProgramId()))) {
                b = true;
            }
            else {
                b = false;
            }
        }
        return b;
    }

    public void channelListOfTvOrRadioChanged(final List<DataConvertChannelModel> list) {
        if (DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() == 0) {
            this.mTvChannelList = list;
            return;
        }
        this.mRadioChannelList = list;
    }

    public void clearTVRadioProgramList() {
        this.mTvChannelList.clear();
        this.mRadioChannelList.clear();
    }

    public List<DataConvertChannelModel> getChannelListByProgramType(final List<DataConvertChannelModel> list, final int n) {
        switch (GMScreenGlobalInfo.getCurStbPlatform()) {
            default: {
                List<DataConvertChannelModel> list2;
                if (list == null || n >= 12) {
                    list2 = null;
                }
                else {
                    final int n2 = n - 4;
                    final ArrayList<DataConvertChannelModel> list3 = new ArrayList<DataConvertChannelModel>();
                    int n3 = 0;
                    while (true) {
                        list2 = list3;
                        if (n3 >= list.size()) {
                            break;
                        }
                        int n4 = 0;
                        final DataConvertChannelModel dataConvertChannelModel = list.get(n3);
                        if (this.isChannelBelongToSelectSat(dataConvertChannelModel)) {
                            switch (n) {
                                case 0: {
                                    n4 = 1;
                                    break;
                                }
                                case 1: {
                                    if (dataConvertChannelModel.GetIsProgramScramble() != 1) {
                                        n4 = 1;
                                        break;
                                    }
                                    break;
                                }
                                case 2: {
                                    if (dataConvertChannelModel.GetIsProgramScramble() == 1) {
                                        n4 = 1;
                                        break;
                                    }
                                    break;
                                }
                                case 3: {
                                    if (dataConvertChannelModel.getIsProgramHd() == 1) {
                                        n4 = 1;
                                        break;
                                    }
                                    break;
                                }
                                case 4: {
                                    if ((dataConvertChannelModel.GetFavMark() & 1 << n2) > 0) {
                                        n4 = 1;
                                        break;
                                    }
                                    break;
                                }
                                case 5: {
                                    if ((dataConvertChannelModel.GetFavMark() & 1 << n2) > 0) {
                                        n4 = 1;
                                        break;
                                    }
                                    break;
                                }
                                case 6: {
                                    if ((dataConvertChannelModel.GetFavMark() & 1 << n2) > 0) {
                                        n4 = 1;
                                        break;
                                    }
                                    break;
                                }
                                case 7: {
                                    if ((dataConvertChannelModel.GetFavMark() & 1 << n2) > 0) {
                                        n4 = 1;
                                        break;
                                    }
                                    break;
                                }
                                case 8: {
                                    if ((dataConvertChannelModel.GetFavMark() & 1 << n2) > 0) {
                                        n4 = 1;
                                        break;
                                    }
                                    break;
                                }
                                case 9: {
                                    if ((dataConvertChannelModel.GetFavMark() & 1 << n2) > 0) {
                                        n4 = 1;
                                        break;
                                    }
                                    break;
                                }
                                case 10: {
                                    if ((dataConvertChannelModel.GetFavMark() & 1 << n2) > 0) {
                                        n4 = 1;
                                        break;
                                    }
                                    break;
                                }
                                case 11: {
                                    if ((dataConvertChannelModel.GetFavMark() & 1 << n2) > 0) {
                                        n4 = 1;
                                        break;
                                    }
                                    break;
                                }
                            }
                            if (n4 != 0) {
                                list3.add(dataConvertChannelModel);
                            }
                        }
                        ++n3;
                    }
                }
                return list2;
            }
            case 30:
            case 31:
            case 32:
            case 71:
            case 72:
            case 74: {
                return this.GetChannelListByCHListType(list, n);
            }
        }
    }

    public List<DataConvertChannelModel> getChannelListByTvRadioType() {
        new ArrayList();
        if (DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type() == 0) {
            return this.mTvChannelList;
        }
        return this.mRadioChannelList;
    }

    public DataConvertChannelModel getCurrentPlayingProgram() throws ProgramNotFoundException {
        DataConvertChannelModel programByIndex = null;
        DataConvertChannelModel dataConvertChannelModel;
        for (int i = 0; i < this.getTotalProgramNum(); ++i, programByIndex = dataConvertChannelModel) {
            dataConvertChannelModel = (programByIndex = this.getProgramByIndex(i));
            if (dataConvertChannelModel.getIsPlaying() == 1) {
                break;
            }
        }
        return programByIndex;
    }

    public int getIndexByProgIdInCurTvRadioProgList(final String s) {
        int size;
        List<DataConvertChannelModel> channelListByTvRadioType;
        int n;
        for (size = this.getChannelListByTvRadioType().size(), channelListByTvRadioType = this.getChannelListByTvRadioType(), n = 0; n < size && !s.equals(channelListByTvRadioType.get(n).GetProgramId()); ++n) {}
        int n2;
        if ((n2 = n) == size) {
            n2 = -1;
        }
        return n2;
    }

    public DataConvertChannelModel getProgramByIndex(final int n) {
        if (n >= this.mTvChannelList.size()) {
            return this.mRadioChannelList.get(n - this.mTvChannelList.size());
        }
        return this.mTvChannelList.get(n);
    }

    public DataConvertChannelModel getProgramByProgramId(final String s) throws ProgramNotFoundException {
        DataConvertChannelModel programByIndex = null;
        DataConvertChannelModel dataConvertChannelModel;
        for (int i = 0; i < this.getTotalProgramNum(); ++i, programByIndex = dataConvertChannelModel) {
            dataConvertChannelModel = (programByIndex = this.getProgramByIndex(i));
            if (dataConvertChannelModel.GetProgramId().equals(s)) {
                break;
            }
        }
        if (s == null || programByIndex == null) {
            throw new ProgramNotFoundException("Not found the program: " + s);
        }
        return programByIndex;
    }

    public int getTotalProgramNum() {
        return this.mTvChannelList.size() + this.mRadioChannelList.size();
    }

    public List<DataConvertSatModel> getmAllSatList() {
        return this.mAllSatList;
    }

    public List<DataConvertTpModel> getmAllTpList() {
        return this.mAllTpList;
    }

    public List<DataConvertChannelModel> getmRadioChannelList() {
        return this.mRadioChannelList;
    }

    public List<DataConvertChannelModel> getmTvChannelList() {
        return this.mTvChannelList;
    }

    public List<DataConvertChannelModel> initChannelListData(final byte[] array) {
        final DataParser parser = ParserFactory.getParser();
        final ArrayList<DataConvertChannelModel> list = new ArrayList<DataConvertChannelModel>();
        while (true) {
            try {
                final List<DataConvertChannelModel> parse = (List<DataConvertChannelModel>)parser.parse((InputStream)new ByteArrayInputStream(array, 0, array.length), 0);
                this.separateRadioAndTv(parse, this.mTvChannelList, this.mRadioChannelList);
                return parse;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                final List<DataConvertChannelModel> parse = list;
                continue;
            }
            //break;
        }
    }

    public void initSatList(final byte[] array) {
        final DataParser parser = ParserFactory.getParser();
        try {
            this.mAllSatList = (List<DataConvertSatModel>)parser.parse((InputStream)new ByteArrayInputStream(array, 0, array.length), 18);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void initTpList(final byte[] array) {
        final DataParser parser = ParserFactory.getParser();
        try {
            this.mAllTpList = (List<DataConvertTpModel>)parser.parse((InputStream)new ByteArrayInputStream(array, 0, array.length), 19);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void releaseData() {
        if (this.mTvChannelList != null) {
            this.mTvChannelList.clear();
        }
        if (this.mRadioChannelList != null) {
            this.mRadioChannelList.clear();
        }
        if (this.mAllTpList != null) {
            this.mAllTpList.clear();
        }
        if (this.mAllSatList != null) {
            this.mAllSatList.clear();
        }
    }

    public void setmAllSatList(final List<DataConvertSatModel> mAllSatList) {
        this.mAllSatList = mAllSatList;
    }

    public void setmAllTpList(final List<DataConvertTpModel> mAllTpList) {
        this.mAllTpList = mAllTpList;
    }

    public void setmRadioChannelList(final List<DataConvertChannelModel> mRadioChannelList) {
        this.mRadioChannelList = mRadioChannelList;
    }

    public void setmTvChannelList(final List<DataConvertChannelModel> mTvChannelList) {
        this.mTvChannelList = mTvChannelList;
    }
}
