package note.lym.org.noteproject.vlayout;

import android.view.View;

/**
 * description:
 *
 * @author yaoming.li
 * @version 1.0.0
 * @since 2017/11/15
 */
public interface OnItemListener<T> {
    void onItemClickListener(View view, T data, int position);
}
