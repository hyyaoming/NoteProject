package note.lym.org.noteproject.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 播放音乐
 *
 * @author yaoming.li
 * @since 2017-06-19 17:52
 */
public class MusicPlayService extends Service {

    private MediaPlayer mediaPlayer = new MediaPlayer();       //媒体播放器对象
    private String path = null;                        //音乐文件路径
    private static final String TAG = MusicPlayService.class.getSimpleName();
    private static final String URL = "music_path";
    private static final String WAIT_CALL_PHONE = "android.intent.action.PHONE_STATE";
    private static final String CALL_PHONE = "android.intent.action.NEW_OUTGOING_CALL";
    private CallPhoneBroadCastReceiver receiver = new CallPhoneBroadCastReceiver();
    private boolean isPause = false;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null != intent) {
            if (mediaPlayer.isPlaying()) {
                stop();
            }
            registerReceiver();
            path = intent.getStringExtra(URL);
            playSong();
        }
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 播放音乐
     */
    private void playSong() {
        if (!TextUtils.isEmpty(path)) {
            if (isPause) {
                mediaPlayer.start();
                return;
            }
            try {
                mediaPlayer.reset();//把各项参数恢复到初始状态
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();  //进行缓冲
                mediaPlayer.setOnPreparedListener(new PreparedListener());//注册一个监听器
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 停止音乐
     */
    private void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            try {
                // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
                mediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 暂停播放
     */
    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPause = true;
        }
    }

    @Override
    public boolean stopService(Intent name) {
        release();
        return super.stopService(name);
    }

    /**
     * 释放资源
     */
    private void release() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * 当服务销毁时需要释放资源
     */
    @Override
    public void onDestroy() {
        release();
        unregisterReceiver(receiver);
    }

    /**
     * 实现一个OnPrepareLister接口,当音乐准备好的时候开始播放
     */
    private final class PreparedListener implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            //开始播放
            mediaPlayer.start();
        }
    }

    /**
     * 开启服务
     *
     * @param context 上下文
     * @param path    音乐播放路径
     */
    public static void startService(Context context, String path) {
        Intent intent = new Intent(context, MusicPlayService.class);
        intent.putExtra(URL, path);
        context.startService(intent);
    }

    /**
     * 停止服务
     *
     * @param context 上下文
     */
    public static void stopService(Context context) {
        Intent intent = new Intent(context, MusicPlayService.class);
        context.stopService(intent);
    }

    /**
     * 注册广播
     */
    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WAIT_CALL_PHONE);
        intentFilter.addAction(CALL_PHONE);
        registerReceiver(receiver, intentFilter);
    }

    /**
     * 监听来去电状态
     */
    private class CallPhoneBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            /**
             * 这里只有两种，要么播出电话，要么来电。
             */
            if (action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
                stop();
            } else {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
                tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
            }
        }
    }

    /**
     * 手机来电监听
     */
    private PhoneStateListener listener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING: //我就在这安静的等着海哥给我打电话
                case TelephonyManager.CALL_STATE_OFFHOOK: //海哥终于打电话给我了
                    pause();
                    break;
                case TelephonyManager.CALL_STATE_IDLE: //直接挂掉海哥的电话
                    playSong();
                    break;
                default:
                    break;
            }
        }
    };

}
