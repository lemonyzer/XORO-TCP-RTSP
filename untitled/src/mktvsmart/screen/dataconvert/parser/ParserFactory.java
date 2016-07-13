package mktvsmart.screen.dataconvert.parser;

public class ParserFactory {
    final private static int JSON_DATA = 1;
    private static mktvsmart.screen.dataconvert.parser.DataParser JsonParser;
    final private static int XML_DATA = 0;
    private static mktvsmart.screen.dataconvert.parser.DataParser XmlParser;
    private static int mDataType;

    static
    {
        mDataType = 0;
        XmlParser = null;
        JsonParser = null;
    }

    public ParserFactory()
    {
        super();
    }

    public static int getDataType()
    {
        return mDataType;
    }

    public static mktvsmart.screen.dataconvert.parser.DataParser getParser()
    {
        //android.util.Log.d("benson", new StringBuilder("mDataType = ").append(mDataType).toString());
        // Log
        if (mDataType == 1)
        {
            if (JsonParser == null)
            {
                JsonParser = (mktvsmart.screen.dataconvert.parser.DataParser)(Object)new mktvsmart.screen.dataconvert.parser.JsonParser();
            }
            return JsonParser;
        }
        else
        {
            if (XmlParser == null)
            {
                XmlParser = (mktvsmart.screen.dataconvert.parser.DataParser)(Object)new mktvsmart.screen.dataconvert.parser.XmlParser();
            }
            return XmlParser;
        }
    }

    public static void setDataType(int i)
    {
        mDataType = i;
    }
}
