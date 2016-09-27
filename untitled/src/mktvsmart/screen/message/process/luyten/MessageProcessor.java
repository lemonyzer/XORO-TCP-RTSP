package mktvsmart.screen.message.process.luyten;

import android.util.*;
import android.app.*;
import java.util.concurrent.*;
import android.os.*;
import java.util.*;

public class MessageProcessor
{
    private static final int CORE_WORKER_THREAD_NUM = 4;
    private static final String TAG;
    private static MessageProcessor sInstance;
    private static Handler sMessageHandler;
    private static final Object sMessageListSync;
    private ExecutorService mWorkerThreadPool;
    private SparseArray<Map<Activity, List<Object>>> messageMappingList;
    private HandlerThread messageReceiver;

    static {
        TAG = MessageProcessor.class.getSimpleName();
        sMessageListSync = new Object();
    }

    private MessageProcessor() {
        this.mWorkerThreadPool = Executors.newFixedThreadPool(4);
        this.messageMappingList = (SparseArray<Map<Activity, List<Object>>>)new SparseArray();
        (this.messageReceiver = new HandlerThread("Handler Thread")).start();
    }

    public static void destroy() {
        if (MessageProcessor.sInstance == null) {
            return;
        }
        MessageProcessor.sInstance.quitThread();
        synchronized (MessageProcessor.sMessageListSync) {
            MessageProcessor.sInstance.messageMappingList.clear();
            MessageProcessor.sInstance.messageMappingList = null;
            // monitorexit(MessageProcessor.sMessageListSync)
            MessageProcessor.sInstance = null;
        }
    }

    private void dispatchMessage(final Message message) {
        synchronized (MessageProcessor.sMessageListSync) {
            if (this.messageMappingList == null) {
                return;
            }
            if (this.messageMappingList.size() == 0) {
                return;
            }
        }
        final Message message2;
        final Map map = (Map)this.messageMappingList.get(message2.what);
        if (map != null) {
            for (final Activity activity : map.keySet()) {
                final List list = (List)map.get(activity);
                final PerformOnBackground performOnBackground = list.get(0);
                final PerformOnForeground performOnForeground = (PerformOnForeground)list.get(1);
                if (performOnBackground != null && (activity == null || (activity != null && !activity.isFinishing()))) {
                    this.mWorkerThreadPool.submit(new Runnable() {
                        @Override
                        public void run() {
                            performOnBackground.doInBackground(message2);
                        }
                    });
                }
                if (performOnForeground != null && activity != null && !activity.isFinishing()) {
                    activity.runOnUiThread((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            performOnForeground.doInForeground(message2);
                        }
                    });
                }
            }
        }
    }
    // monitorexit(o)

    public static MessageProcessor obtain() {
        synchronized (MessageProcessor.class) {
            if (MessageProcessor.sInstance == null) {
                MessageProcessor.sInstance = new MessageProcessor();
            }
            return MessageProcessor.sInstance;
        }
    }

    private void quitThread() {
        this.mWorkerThreadPool.shutdown();
        this.messageReceiver.quit();
        this.messageReceiver = null;
        MessageProcessor.sMessageHandler = null;
    }

    private void recycleMessageMap() {
        final Object sMessageListSync = MessageProcessor.sMessageListSync;
        // monitorenter(sMessageListSync)
        int n = 0;
        while (true) {
            int n2 = 0;
            Label_0211:
            while (true) {
                Map map = null;
                ArrayList<Activity> list = null;
                Label_0188: {
                    try {
                        if (n >= this.messageMappingList.size()) {
                            return;
                        }
                        final int key = this.messageMappingList.keyAt(n);
                        map = (Map)this.messageMappingList.get(key);
                        n2 = n;
                        if (map != null) {
                            list = new ArrayList<Activity>();
                            for (final Activity activity : map.keySet()) {
                                if (activity != null && activity.isFinishing()) {
                                    list.add(activity);
                                }
                            }
                            if (list.size() != 0) {
                                n2 = 0;
                                if (n2 < list.size()) {
                                    break Label_0188;
                                }
                            }
                            if (map != null) {
                                n2 = n;
                                if (map.size() != 0) {
                                    break Label_0211;
                                }
                            }
                            this.messageMappingList.remove(key);
                            n2 = n - 1;
                        }
                        break Label_0211;
                    }
                    finally {
                    }
                    // monitorexit(sMessageListSync)
                }
                map.remove(list.get(n2));
                ++n2;
                continue;
            }
            n = n2 + 1;
        }
    }

    public boolean postEmptyMessage(final int n) {
        return this.postEmptyMessageDelayed(n, 0L);
    }

    public boolean postEmptyMessageAtTime(final int what, final long n) {
        final Message obtain = Message.obtain();
        obtain.what = what;
        return this.postMessageAtTime(obtain, n);
    }

    public boolean postEmptyMessageDelayed(final int what, final long n) {
        final Message obtain = Message.obtain();
        obtain.what = what;
        return this.postMessageDelayed(obtain, n);
    }

    public boolean postMessage(final Message message) {
        return this.postMessageDelayed(message, 0L);
    }

    public boolean postMessageAtFrontOfQueue(final Message message) {
        if (message != null && this.messageReceiver != null) {
            if (MessageProcessor.sMessageHandler == null) {
                if (this.messageReceiver.getLooper() == null) {
                    return false;
                }
                MessageProcessor.sMessageHandler = new Handler(this.messageReceiver.getLooper());
            }
            final Message obtain = Message.obtain(MessageProcessor.sMessageHandler, (Runnable)new Runnable() {
                @Override
                public void run() {
                    MessageProcessor.this.dispatchMessage(message);
                }
            });
            obtain.what = message.what;
            obtain.arg1 = message.arg1;
            obtain.arg2 = message.arg2;
            obtain.obj = message.obj;
            obtain.replyTo = message.replyTo;
            if (message.getData() != null) {
                obtain.setData(new Bundle(message.getData()));
            }
            return MessageProcessor.sMessageHandler.sendMessageAtFrontOfQueue(obtain);
        }
        return false;
    }

    public boolean postMessageAtTime(final Message message, final long n) {
        return this.postMessageAtTime(message, null, n);
    }

    public boolean postMessageAtTime(final Message message, final Object obj, final long n) {
        if (message != null && this.messageReceiver != null) {
            if (MessageProcessor.sMessageHandler == null) {
                if (this.messageReceiver.getLooper() == null) {
                    return false;
                }
                MessageProcessor.sMessageHandler = new Handler(this.messageReceiver.getLooper());
            }
            final Message obtain = Message.obtain(MessageProcessor.sMessageHandler, (Runnable)new Runnable() {
                @Override
                public void run() {
                    MessageProcessor.this.dispatchMessage(message);
                }
            });
            obtain.what = message.what;
            obtain.arg1 = message.arg1;
            obtain.arg2 = message.arg2;
            obtain.obj = obj;
            obtain.replyTo = message.replyTo;
            if (message.getData() != null) {
                obtain.setData(new Bundle(message.getData()));
            }
            return MessageProcessor.sMessageHandler.sendMessageAtTime(obtain, n);
        }
        return false;
    }

    public boolean postMessageDelayed(final Message message, final long n) {
        long n2 = n;
        if (n < 0L) {
            n2 = 0L;
        }
        return this.postMessageAtTime(message, SystemClock.uptimeMillis() + n2);
    }

    public void recycle() {
        if (MessageProcessor.sInstance != null) {
            this.recycleMessageMap();
        }
    }

    public boolean removeMessages(final int n) {
        if (MessageProcessor.sMessageHandler != null && MessageProcessor.sMessageHandler.hasMessages(n)) {
            MessageProcessor.sMessageHandler.removeMessages(n);
            return true;
        }
        return false;
    }

    public boolean removeMessages(final int n, final Object o) {
        if (MessageProcessor.sMessageHandler != null && MessageProcessor.sMessageHandler.hasMessages(n, o)) {
            MessageProcessor.sMessageHandler.removeMessages(n, o);
            return true;
        }
        return false;
    }

    public void removeProcessCallback(final Activity activity) {
        final Object sMessageListSync = MessageProcessor.sMessageListSync;
        // monitorenter(sMessageListSync)
        int n = 0;
        Label_0094_Outer:
        while (true) {
            int n2 = 0;
            Label_0234: {
                while (true) {
                    Label_0229: {
                        int key = 0;
                        Label_0213: {
                            ArrayList<Activity> list = null;
                            Label_0178: {
                                try {
                                    if (n >= this.messageMappingList.size()) {
                                        return;
                                    }
                                    key = this.messageMappingList.keyAt(n);
                                    final Map map = (Map)this.messageMappingList.get(key);
                                    if (map == null) {
                                        break Label_0213;
                                    }
                                    list = new ArrayList<Activity>();
                                    if (activity != null) {
                                        for (final Activity activity2 : map.keySet()) {
                                            if (activity2 != null && activity2.equals(activity)) {
                                                list.add(activity2);
                                            }
                                        }
                                        break Label_0229;
                                    }
                                    break Label_0178;
                                    while (true) {
                                        n2 = n;
                                        this.messageMappingList.remove(key);
                                        n2 = n - 1;
                                        break Label_0234;
                                        continue Label_0094_Outer;
                                    }
                                }
                                // iftrue(Label_0234:, map.size() != 0)
                                // iftrue(Label_0190:, n2 < list.size())
                                finally {
                                }
                                // monitorexit(sMessageListSync)
                            }
                            list.add(activity);
                            break Label_0229;
                            Label_0190: {
                                final Map map;
                                map.remove(list.get(n2));
                            }
                            ++n2;
                            continue;
                        }
                        this.messageMappingList.remove(key);
                        n2 = n - 1;
                        break Label_0234;
                    }
                    n2 = 0;
                    continue;
                }
            }
            n = n2 + 1;
        }
    }

    public void removeProcessCallback(final Activity activity, final int n) {
        final Object sMessageListSync = MessageProcessor.sMessageListSync;
        // monitorenter(sMessageListSync)
        int n2 = 0;
        Label_0098_Outer:
        while (true) {
            int n3 = 0;
            Label_0244: {
                while (true) {
                    Label_0238: {
                        Label_0222: {
                            ArrayList<Activity> list = null;
                            Label_0184: {
                                try {
                                    if (n2 >= this.messageMappingList.size()) {
                                        return;
                                    }
                                    n3 = n2;
                                    if (this.messageMappingList.keyAt(n2) != n) {
                                        break Label_0244;
                                    }
                                    final Map map = (Map)this.messageMappingList.get(n);
                                    if (map == null) {
                                        break Label_0222;
                                    }
                                    list = new ArrayList<Activity>();
                                    if (activity != null) {
                                        for (final Activity activity2 : map.keySet()) {
                                            if (activity2 != null && activity2.equals(activity)) {
                                                list.add(activity2);
                                            }
                                        }
                                        break Label_0238;
                                    }
                                    break Label_0184;
                                    while (true) {
                                        n3 = n2;
                                        this.messageMappingList.remove(n);
                                        n3 = n2 - 1;
                                        break Label_0244;
                                        continue Label_0098_Outer;
                                    }
                                }
                                // iftrue(Label_0244:, map.size() != 0)
                                // iftrue(Label_0196:, n3 < list.size())
                                finally {
                                }
                                // monitorexit(sMessageListSync)
                            }
                            list.add(activity);
                            break Label_0238;
                            Label_0196: {
                                final Map map;
                                map.remove(list.get(n3));
                            }
                            ++n3;
                            continue;
                        }
                        this.messageMappingList.remove(n);
                        n3 = n2 - 1;
                        break Label_0244;
                    }
                    n3 = 0;
                    continue;
                }
            }
            n2 = n3 + 1;
        }
    }

    public void setOnMessageProcess(final int n, final Activity activity, final PerformOnBackground performOnBackground) {
        this.setOnMessageProcess(n, activity, performOnBackground, null);
    }

    public void setOnMessageProcess(final int n, final Activity activity, final PerformOnBackground performOnBackground, final PerformOnForeground performOnForeground) {
        final ArrayList<PerformOnBackground> list = new ArrayList<PerformOnBackground>();
        final Map map = (Map)this.messageMappingList.get(n);
        if (list.add(performOnBackground) && list.add((PerformOnBackground)performOnForeground)) {
            Map<Activity, ArrayList<PerformOnBackground>> map2;
            if ((map2 = (Map<Activity, ArrayList<PerformOnBackground>>)map) == null) {
                map2 = new HashMap<Activity, ArrayList<PerformOnBackground>>();
            }
            map2.put(activity, list);
            synchronized (MessageProcessor.sMessageListSync) {
                this.messageMappingList.put(n, (Object)map2);
            }
        }
    }

    public void setOnMessageProcess(final int n, final Activity activity, final PerformOnForeground performOnForeground) {
        this.setOnMessageProcess(n, activity, null, performOnForeground);
    }

    public void setOnMessageProcess(final int n, final PerformOnBackground performOnBackground) {
        this.setOnMessageProcess(n, null, performOnBackground);
    }

    public interface PerformOnBackground
    {
        void doInBackground(final Message p0);
    }

    public interface PerformOnForeground
    {
        void doInForeground(final Message p0);
    }
}
