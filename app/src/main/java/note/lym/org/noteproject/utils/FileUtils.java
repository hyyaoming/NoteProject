package note.lym.org.noteproject.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

import note.lym.org.noteproject.app.NoteApplication;

/**
 * 文件操作相关类
 *
 * @author yaoming.li
 * @since 2017-05-10 14:03
 */
public class FileUtils {
    private FileUtils() {
        //no instance
    }

    /**
     * 根据一个url创建一个文件
     *
     * @param url url
     * @return 返回一个文件
     */
    public static File createFileFrom(String url) {
        String fileName = getFileSuffixFrom(url);
        File file = new File(getAppAlbumDir(), fileName);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private static String getFileSuffixFrom(String url) {
        if (url.contains(".")) {
            String fileSuffix = url.substring(url.lastIndexOf("."), url.length());
            if (fileSuffix.length() < 7) {
                return fileSuffix;
            } else {
                return System.currentTimeMillis() + ".jpg";
            }
        }
        return System.currentTimeMillis() + ".jpg";
    }

    private static File getAppAlbumDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return NoteApplication.getInstance().getExternalFilesDir("Album");
        } else {
            return new File(NoteApplication.getInstance().getFilesDir(), "Album/");
        }
    }
}
