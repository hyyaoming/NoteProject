package project.recyclerview.lym.org.recyclerviewlibrary.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Desp.
 *
 * @author yaoming.li
 * @version 8.1.0
 * @since 2017-03-17 17:11
 */
public class TimerUtils {

    private static TimerUtils mShowTv;
    private static final int INTERVAL_TIME = 1000;
    private Timer mTimer;
    private int count_time = 0;
    private WeakHandler handler;
    private TextView mTv;

    private TimerUtils() {
    }

    public static TimerUtils getInstance() {
        //对象实例化时判断(如果不为空直接返回提高运行效率)
        if (mShowTv == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (TimerUtils.class) {
                if (mShowTv == null) {
                    mShowTv = new TimerUtils();
                }
            }
        }
        return mShowTv;
    }


    public TimerUtils setTime(int time, Activity activity, TextView tv) {
        count_time = time;
        handler = new WeakHandler(activity, Looper.myLooper());
        mTv = tv;
        return this;
    }

    public void startTimer() {
        if (mTimer!=null) {
            return;
        }
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (count_time >= INTERVAL_TIME) {
                    count_time -= INTERVAL_TIME;
                } else {
                    count_time = 0;
                }
                Message msg = Message.obtain();
                msg.obj = mTv;
                msg.arg1 = count_time;
                msg.what = 0;
                handler.sendMessage(msg);
            }
        }, 0, INTERVAL_TIME);
    }

    public void cancelTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    class WeakHandler extends Handler {
        WeakReference<Activity> act;

        public WeakHandler(Activity activity, Looper looper) {
            super(looper);
            act = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Activity activity = act.get();
            if (activity != null) {
                if (msg.what == 0) {
                    TextView tv = (TextView) msg.obj;
                    String time = getPreciseTime.getLeftTime(msg.arg1);
                    tv.setText(time);
                    if (msg.arg1 == 0) {
                        if(mTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                        }
                    }
                }
            }
        }
    }

}
