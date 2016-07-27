package mktvsmart.screen.pvr2small;

import android.os.Message;
import mktvsmart.screen.dataconvert.model.*;
import java.util.*;
import mktvsmart.screen.channel.*;
import java.io.*;
import mktvsmart.screen.dataconvert.parser.*;

public class Pvr2smallData
{
    private List<DataConvertPvrInfoModel> mPvr2smallList;

    private Pvr2smallData() {
        super();
        this.mPvr2smallList = new ArrayList<DataConvertPvrInfoModel>();
    }

    Pvr2smallData(final Pvr2smallData pvr2smallData) {
        this();
    }

    public static Pvr2smallData getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Object readResolve() {
        return getInstance();
    }

    public void clearPvr2smallList() {
        if (this.mPvr2smallList != null) {
            this.mPvr2smallList.clear();
        }
    }

    public String getPlayUrlBase(final int n, final String s) {
        return String.valueOf(Sat2ipUtil.getRtspUriBase(s)) + "record=" + this.mPvr2smallList.get(n).getmPvrId();
    }

    public String getPlayUrlQuery() {
        return "";
    }

    public List<DataConvertPvrInfoModel> getPvr2smallList() {
        return this.mPvr2smallList;
    }

    public void initPvr2SmallList(final Message message) {
        byte[] array = message.getData().getByteArray("ReceivedData");
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array, 0, array.length);
        final DataParser parser = ParserFactory.getParser();
        try {
            this.mPvr2smallList = (List<DataConvertPvrInfoModel>)parser.parse(message, 20);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public void initPvr2SmallList(final byte[] array) {
//        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array, 0, array.length);
//        final DataParser parser = ParserFactory.getParser();
//        try {
//            this.mPvr2smallList = (List<DataConvertPvrInfoModel>)parser.parse(byteArrayInputStream, 20);
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public void setPvr2smallList(final List<DataConvertPvrInfoModel> mPvr2smallList) {
        this.mPvr2smallList = mPvr2smallList;
    }

    private static class SingletonHolder
    {
        static final Pvr2smallData INSTANCE;

        static {
            INSTANCE = new Pvr2smallData(null);
        }

        private SingletonHolder() {
            super();
        }
    }
}
