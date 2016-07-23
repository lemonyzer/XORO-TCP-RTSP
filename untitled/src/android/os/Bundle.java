package android.os;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by it on 20.07.2016.
 */
public class Bundle {

    private HashMap<String, Object> mMap;

    public Bundle()
    {
        mMap = new HashMap<String, Object>();
    }

    public void putByteArray(String key, byte[] array) {
        mMap.put(key, array);
    }

    public byte[] getByteArray(String key) {
        return (byte[]) mMap.get(key);
    }

}
