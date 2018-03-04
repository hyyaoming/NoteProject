package note.lym.org.noteproject.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import note.lym.org.noteproject.model.bean.Music;

/**
 * 媒体工具类
 *
 * @author yaoming.li
 * @since 2017-06-19 17:59
 */
public class MediaUtils {
    private static final int MB = 1024;
    private static final int MILLISECOND = 1000;
    private static final int MINUTE = 60;
    private static final int FILTER_SIZE = 1 * MB * MB;// 1MB
    private static final int FILTER_DURATION = 1 * MINUTE * MILLISECOND;// 1分钟
    private static String[] project_music = new String[]{
            MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ARTIST_ID, MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.SIZE};

    public static ArrayList<Music> queryMusic(Context context) {
        ContentResolver cr = context.getContentResolver();
        StringBuilder select = new StringBuilder(" 1=1 and title != ''");
        // 查询语句：检索出.mp3为后缀名，时长大于1分钟，文件大小大于1MB的媒体文件
        select.append(" and " + MediaStore.Audio.Media.SIZE + " > " + FILTER_SIZE);
        select.append(" and " + MediaStore.Audio.Media.DURATION + " > " + FILTER_DURATION);
        return getMusicListCursor(cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, project_music,
                select.toString(), null,
                null));
    }

    /**
     * 返回本地所有的音乐
     *
     * @param cursor param is cursor
     * @return all local music
     */
    public static ArrayList<Music> getMusicListCursor(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        ArrayList<Music> musicList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Music music = new Music();
            music.musicId = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID));
            music.musicduration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));
            music.musicTitle = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE));
            music.musicartist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String filePath = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));
            music.musicpath = filePath;
            music.musicsize = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.SIZE));
            musicList.add(music);
        }
        cursor.close();
        return musicList;
    }

}
