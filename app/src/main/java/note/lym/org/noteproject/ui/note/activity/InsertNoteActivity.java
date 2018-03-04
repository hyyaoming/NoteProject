package note.lym.org.noteproject.ui.note.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.activity.SimpleActivity;
import note.lym.org.noteproject.model.bean.Note;
import note.lym.org.noteproject.model.dao.NoteDao;
import note.lym.org.noteproject.utils.ToastUtils;
import project.recyclerview.lym.org.recyclerviewlibrary.util.DateUtils;

/**
 * @author yaoming.li
 * @since 2017-04-26 10:38
 */
public class InsertNoteActivity extends SimpleActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
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
    protected int getLayout() {
        return R.layout.activity_insert_note;
    }

    @Override
    protected void initEventAndData() {
        initActionBar();
        initEditListener();
        initData();
        bindView();
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
        initToolBar(mToolBar, true, getString(R.string.add_note));
    }

    protected void initData() {
        getIntentData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_note) {
            saveNote();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }

    protected void bindView() {
        mTvNoteName.setText(mNoteName);
        mTvNoteTime.setText(DateUtils.getCurrentDate());
    }

    private void getIntentData() {
        mNoteName = getIntent().getStringExtra(NOTE_NAME);
    }

    private void saveNote() {
        if (getContentSize() > 0) {
            hideSoftInput();
            NoteDao.insertNote(getInsertNote());
            setResult(RESULT_OK);
            finish();
        } else {
            ToastUtils.showToast(R.string.please_insert_note);
        }
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

    public static void action(Fragment fragment, String noteName) {
        Intent intent = new Intent(fragment.getActivity(), InsertNoteActivity.class);
        intent.putExtra(NOTE_NAME, noteName);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void finish() {
        super.finish();
    }
}
