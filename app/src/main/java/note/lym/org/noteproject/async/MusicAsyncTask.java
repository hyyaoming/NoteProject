package note.lym.org.noteproject.async;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.bean.Music;
import note.lym.org.noteproject.service.MusicPlayService;
import note.lym.org.noteproject.utils.MediaUtils;
import note.lym.org.noteproject.utils.ToastUtils;

/**
 * 扫描音乐异步任务
 *
 * @author yaoming.li
 * @since 2017-06-20 09:44
 */
public class MusicAsyncTask extends AsyncTask<Void, Void, Music[]> {

    private Context mContext;

    public MusicAsyncTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected Music[] doInBackground(Void... params) {
        ArrayList<Music> list = MediaUtils.queryMusic(mContext);
        return list.toArray(new Music[list.size()]);
    }

    @Override
    protected void onPostExecute(Music[] musics) {
        if (musics.length > 0) {
            ArrayList<Music> musicList = new ArrayList<>();
            for (Music music : musics) {
                musicList.add(music);
            }
            MusicPlayService.startService(mContext, musicList);
        } else {
            ToastUtils.showToast(R.string.i_think_you_must_down_music);
        }
    }
}
