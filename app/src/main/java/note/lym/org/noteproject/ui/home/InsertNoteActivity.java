package note.lym.org.noteproject.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.BaseActivity;
import note.lym.org.noteproject.model.Note;
import note.lym.org.noteproject.utils.ToastUtils;
import note.lym.org.noteproject.view.BaseActionBar;
import project.recyclerview.lym.org.recyclerviewlibrary.util.DateUtils;

/**
 * @author yaoming.li
 * @since 2017-04-26 10:38
 */
public class InsertNoteActivity extends BaseActivity {

    @BindView(R.id.insert_action_bar)
    BaseActionBar mActionBar;
    @BindView(R.id.tv_note_name)
    TextView mTvNoteName;
    @BindView(R.id.tv_note_time)
    TextView mTvNoteTime;
    @BindView(R.id.tv_content_size)
    TextView mTvNoteSize;
    @BindView(R.id.edit_note_content)
    EditText mEditTextNote;


    /***  ------- 常量  ------****/
    /**
     * 便签名字
     */
    public static final String NOTE_NAME = "note_name";
    public static final int REQUEST_CODE = 1000;
    /***
     * --- 获取赋值 ----
     ***/
    private String mNoteName = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_insert_note;
    }

    @Override
    protected void initListener() {
        initActionBar();
        initEditListener();
    }

    private void initEditListener() {
        mEditTextNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTvNoteSize.setText(String.valueOf(s.length()));
            }
        });
    }

    private void initActionBar() {
        mActionBar.setTextTitle(R.string.add_note);
        mActionBar.setRightImageResources(R.drawable.insert_done);
        mActionBar.setLeftBackListener(true,new BaseActionBar.LeftBackListener() {
            @Override
            public void onClick() {
                saveNote();
            }
        });
        mActionBar.setRightInsertClickListener(true,new BaseActionBar.RightInsertClickListener() {
            @Override
            public void onClick() {
                if (!saveNote()) {
                    ToastUtils.showToast(R.string.please_insert_note);
                }
            }
        });
    }

    @Override
    protected void initData() {
        getIntentData();
    }

    @Override
    protected void bindView() {
        mTvNoteName.setText(mNoteName);
        mTvNoteTime.setText(DateUtils.getCurrentDate());
    }

    private void getIntentData() {
        mNoteName = getIntent().getStringExtra(NOTE_NAME);
    }

    private boolean saveNote() {
        if (getContentSize() > 0) {
            hideSoftInput();
            getInsertNote().save();
            setResult(RESULT_OK);
            finish();
            return true;
        }
        return false;
    }

    private Note getInsertNote() {
        Note note = new Note();
        note.content = getNoteContent();
        note.noteName = mNoteName;
        note.date = DateUtils.getCurrentDate();
        note.noteId = new Random().nextInt(100);
        return note;
    }

    private String getNoteContent() {
        return mEditTextNote.getText().toString().trim();
    }

    private int getContentSize() {
        return getNoteContent().length();
    }

    public static void action(Activity context, String noteName) {
        Intent intent = new Intent(context, InsertNoteActivity.class);
        intent.putExtra(NOTE_NAME, noteName);
        context.startActivityForResult(intent, REQUEST_CODE);
    }

}
