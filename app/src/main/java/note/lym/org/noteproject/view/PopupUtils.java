package note.lym.org.noteproject.view;

import android.app.Activity;
import android.content.Context;

/**
 * 弹框管理类
 *
 * @author yaoming.li
 * @version 8.3.0
 * @since 2017-04-26 20:57
 */
public class PopupUtils {
    private PopupUtils() {

    }

    /**
     * show insert note dialog
     *
     * @param activity activity
     * @param listener click result listener
     */
    public static void showInsertNoteDialog(Activity activity, InsertNoteDialog.OnButtonClickListener listener) {
        InsertNoteDialog dialog = new InsertNoteDialog(activity);
        dialog.setOnButtonClickListener(listener);
        dialog.show();
    }

}
