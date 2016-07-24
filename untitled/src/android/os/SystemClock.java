package android.os;

/**
 * Created by it on 16.07.2016.
 */
public class SystemClock {

    public static long uptimeMillis () {
        return (long) (System.nanoTime() * 0.001);
    }
}

