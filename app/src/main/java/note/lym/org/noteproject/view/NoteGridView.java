package note.lym.org.noteproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 可以嵌套在其他里面的gridView
 *
 * @author yaoming.li
 * @since 2017-05-16 21:37
 */
public class NoteGridView extends GridView {
    public NoteGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoteGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NoteGridView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
