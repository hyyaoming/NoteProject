package note.lym.org.noteproject.ui.home.detail;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.BaseActivity;
import note.lym.org.noteproject.view.BaseActionBar;

/**
 * 便签详情页
 *
 * @author yaoming.li
 * @since 2017-04-25 12:44
 */
public class NoteDetailActivity extends BaseActivity {

    @BindView(R.id.tv_detail_note_name)
    TextView mTvDetailName;
    @BindView(R.id.tv_detail_note_time)
    TextView mTvDetailTime;
    @BindView(R.id.tv_detail_note_content)
    TextView mTvDetailContent;
    @BindView(R.id.action_bar_base)
    BaseActionBar mActionBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activiyt_note_detail;
    }

    @Override
    protected void initListener() {
        initActionBar();
    }

    private void initActionBar() {
        mActionBar.setTextTitle(R.string.note_detail);
        mActionBar.setRightInsertClickListener(false,null);
        mActionBar.setLeftBackListener(new BaseActionBar.LeftBackListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        getIntentData();
    }

    private void getIntentData() {
        mDetailNoteTime = getIntent().getStringExtra(NOTE_TIME);
        mDetailNoteName = getIntent().getStringExtra(NOTE_NAME);
        mDetailNoteContent = getIntent().getStringExtra(NOTE_CONTENT);
    }

    @Override
    protected void bindView() {
        mTvDetailName.setText(new StringBuffer(getString(R.string.note_detail_name)).append(mDetailNoteName).toString());
        mTvDetailTime.setText(new StringBuffer(getString(R.string.note_detail_time)).append(mDetailNoteTime).toString());
        mTvDetailContent.setText(new StringBuffer(getString(R.string.note_detail_content)).append(mDetailNoteContent).toString());
    }

    public static final String NOTE_NAME = "note_name";
    public static final String NOTE_TIME = "note_time";
    public static final String NOTE_CONTENT = "note_content";

    private String mDetailNoteTime = "";
    private String mDetailNoteName = "";
    private String mDetailNoteContent = "";

    public static void action(Activity activity, String noteName, String noteTime, String noteContent) {
        Intent intent = new Intent(activity, NoteDetailActivity.class);
        intent.putExtra(NOTE_NAME, noteName);
        intent.putExtra(NOTE_TIME, noteTime);
        intent.putExtra(NOTE_CONTENT, noteContent);
        activity.startActivity(intent);
    }
}
