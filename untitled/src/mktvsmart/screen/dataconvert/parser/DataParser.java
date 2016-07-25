package mktvsmart.screen.dataconvert.parser;

import android.os.Message;

import java.io.InputStream;
import java.util.List;

public abstract interface DataParser
{
    public abstract List<?> parse(Message msg, int paramInt)
            throws Exception;

    public abstract SerializedDataModel serialize(List<?> paramList, int paramInt)
            throws Exception;
}

