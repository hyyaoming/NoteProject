package note.lym.org.noteproject.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import note.lym.org.noteproject.R;
import project.recyclerview.lym.org.recyclerviewlibrary.util.ScreenUtils;

/**
 * @author yaoming.li
 * @since 2017-04-26 14:14
 */
public class NotePopupWindow extends PopupWindow {


    private EditText mEditNoteName;
    private TextView mBtnSure;

    public NotePopupWindow(Context context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.note_popup_layout, null);
        // 设置PopupWindow的View
        this.setContentView(view);
        // 设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setWidth(ScreenUtils.getScreenWidth(context) * 2 / 3);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setOutsideTouchable(true);
        bindView(view);
    }

    private void bindView(View view) {
        mEditNoteName = (EditText) view.findViewById(R.id.edit_note_name);
        mBtnSure = (TextView) view.findViewById(R.id.tv_sure);
        mBtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onClick();
                }
            }
        });

    }

    /**
     * 显示或隐藏View
     *
     * @param parent View parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

    public String getEditNoteName() {
        return mEditNoteName.getText().toString().trim();
    }

    private OnSureClickListener mListener;

    public void setOnSureClickListener(OnSureClickListener listener) {
        this.mListener = listener;
    }

    public interface OnSureClickListener {
        void onClick();
    }

}
