package android.util;

/**
 * Created by it on 16.07.2016.
 */
public class Log {


    private static Log instance = null;
    protected Log() {
        // Exists only to defeat instantiation.
    }
    public static Log getInstance() {
        if(instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public static void d(String tag, String msg) {
        System.out.println(tag + " " + msg);
    }

    public static void e(String tag, String msg) {
        System.out.println("ERROR: " + tag + " " + msg);
    }

}
