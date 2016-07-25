package mktvsmart.screen.dataconvert.parser;

import java.io.InputStream;
import java.util.List;

public abstract interface DataParser
{
    public abstract List<?> parse(InputStream paramInputStream, int paramInt)
            throws Exception;

    public abstract SerializedDataModel serialize(List<?> paramList, int paramInt)
            throws Exception;
}

