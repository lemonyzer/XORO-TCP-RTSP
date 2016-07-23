package android.os;

/**
 * Created by it on 16.07.2016.
 */
public class Message {
    public int what;
    public Object obj;
    public int arg1;
    public int arg2;

    Bundle data;

    public Message() {

    }

    public Message(int what, Object obj) {
        this.what = what;
        this.obj = obj;
    }

    public Message(Bundle bundle) {
        data = bundle;
    }

    public static Message obtain() {

        return new Message();
    }

    public void setData(Bundle data) {
        this.data = data;
    }

    public Bundle getData() {
        return data;
    }
}
