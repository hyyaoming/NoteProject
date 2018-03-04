package note.lym.org.noteproject.delay.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.bean.Music;
import note.lym.org.noteproject.utils.ToastUtils;

/**
 * 播放音乐
 *
 * @author yaoming.li
 * @since 2017-06-19 17:52
 */
public class MusicPlayService extends Service {
    private static final String TAG = MusicPlayService.class.getSimpleName();
    /**
     * 音乐播放器对象
     */
    private MediaPlayer mediaPlayer = new MediaPlayer();
    /**
     * 音乐播放路径
     */
    private static final String URL = "music_path";
    /**
     * 监听电话状态
     */
    private static final String WAIT_CALL_PHONE = "android.intent.action.PHONE_STATE";
    /**
     * 监听拨出电话广播
     */
    private static final String CALL_PHONE = "android.intent.action.NEW_OUTGOING_CALL";
    /**
     * 监听耳机插拔广播
     */
    private static final String HEADSET_STATE = AudioManager.ACTION_AUDIO_BECOMING_NOISY;
    /**
     * 监听电话状态广播
     */
    private CallPhoneBroadCastReceiver receiver = new CallPhoneBroadCastReceiver();
    /**
     * 监听蓝牙耳机断开连接
     */
    private static final String BLUETOOTH_HEAD_SET_STATE = BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED;
    /**
     * 当前是否为暂停播放
     */
    private boolean isPause = false;
    /**
     * 播放音乐的讯息
     */
    private ArrayList<Music> musics = new ArrayList<>();
    /**
     * 当前需要播放音乐的角标
     */
    private int position = 0;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //注册监听手机状态广播
        registerReceiver();
        getMusicSoundSize();
    }

    private void getMusicSoundSize() {
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //获取当前媒体播放最大音量
        int max = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //获取当前媒体播放音量
        int current = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.d(TAG, "current:" + current + "max :" + max);
        if (current == 0) {
            ToastUtils.showToast(R.string.maybe_you_should_enlarge_music_sound);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null != intent) {
            musics = (ArrayList<Music>) intent.getSerializableExtra(URL);
            nextSong();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 随机播放音乐
     */
    private void nextSong() {
        Random random = new Random();
        position = random.nextInt(musics.size());
        playSong();
    }

    /**
     * 播放音乐
     */
    private void playSong() {
        if (isPause) {
            mediaPlayer.start();
            return;
        }
        try {
            //把各项参数恢复到初始状态
            mediaPlayer.reset();
            //设置音乐播放的路径
            mediaPlayer.setDataSource(musics.get(position).musicpath);
            //进行缓冲
            mediaPlayer.prepare();
            //添加一个缓冲监听
            mediaPlayer.setOnPreparedListener(new PreparedListener());
            //当音乐播放完毕时的监听
            mediaPlayer.setOnCompletionListener(completionListener);
        } catch (Exception e) {
            e.printStackTrace();
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
            stop();
            mediaPlayer.release();
            mediaPlayer = null;
            unregisterReceiver(receiver);
        }
    }

    /**
     * 当服务销毁时需要释放资源
     */
    @Override
    public void onDestroy() {
        release();
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
     * 当前音乐播放完毕时的监听
     */
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            //当播放完毕时继续随机播放下一首
            nextSong();
        }
    };

    /**
     * 开启服务
     *
     * @param context  上下文
     * @param listPath 音乐播放路径
     */
    public static void startService(Context context, ArrayList<Music> listPath) {
        Intent intent = new Intent(context, MusicPlayService.class);
        intent.putExtra(URL, listPath);
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
        intentFilter.addAction(HEADSET_STATE);
        intentFilter.addAction(BLUETOOTH_HEAD_SET_STATE);
        registerReceiver(receiver, intentFilter);
    }

    /**
     * 监听来去电状态
     * 这里说明一下，只有两种可能一种播出电话，一种接听电话,还有一种当耳机拔出时也需要停止播放
     */
    private class CallPhoneBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                //拨出电话，此时需要暂停播放音乐
                case Intent.ACTION_NEW_OUTGOING_CALL:
                    pause();
                    break;
                case HEADSET_STATE:
                    pause();
                    break;
                case BLUETOOTH_HEAD_SET_STATE:
                    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                    if (BluetoothProfile.STATE_DISCONNECTED == adapter.getProfileConnectionState(BluetoothProfile.HEADSET)) {
                        //此时蓝牙耳机断开连接
                        pause();
                    }
                    break;
                default:
                    //另外情况则视为有电话拨进来，只有挂断电话才恢复音乐播放，其他情况都暂停播放音乐。
                    TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
                    tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
                    break;
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
