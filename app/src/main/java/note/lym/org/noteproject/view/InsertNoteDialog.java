package note.lym.org.noteproject.view;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.utils.SoftInputUtil;
import note.lym.org.noteproject.utils.ToastUtils;

/**
 *
 * @author yaoming.li
 * @since 2017-04-26 20:30
 */
public class InsertNoteDialog extends Dialog {

    @BindView(R.id.edit_dialog)
    EditText mEditNoteName;
    private View mRootView;

    public InsertNoteDialog(Activity context) {
        this(context, R.style.Theme_Dialog);
    }

    public InsertNoteDialog(Activity context, int themeResId) {
        super(context, themeResId);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        onCreateView(context);
    }

    private void onCreateView(Activity activity) {
        mRootView =  LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout_insert_note, null);
        setContentView(mRootView,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
        ButterKnife.bind(this);
        setLayoutParams(activity);
    }

    private void setLayoutParams(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = (int) (dm.widthPixels - (dm.widthPixels / 3.5));
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        lp.width = screenWidth; // 宽度
        dialogWindow.setAttributes(lp);
    }


    @OnClick(R.id.btn_dialog_sure)
    public void click() {
        String noteName = mEditNoteName.getText().toString().trim();
        if (null != mListener && !TextUtils.isEmpty(noteName)) {
            mListener.onClick(noteName);
            SoftInputUtil.hideShow(mEditNoteName);
            dismiss();
        }else{
            ToastUtils.showToast(R.string.please_insert_note_name);
        }
    }

    private OnButtonClickListener mListener;

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        mListener = listener;
    }

    public interface OnButtonClickListener {
        void onClick(String note);
    }

}
