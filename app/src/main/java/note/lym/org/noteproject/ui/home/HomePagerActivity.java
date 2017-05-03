package note.lym.org.noteproject.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.NoteListAdapter;
import note.lym.org.noteproject.base.BaseActivity;
import note.lym.org.noteproject.model.Note;
import note.lym.org.noteproject.presenter.note.INotePresenter;
import note.lym.org.noteproject.presenter.note.NoteListPresenter;
import note.lym.org.noteproject.ui.home.detail.NoteDetailActivity;
import note.lym.org.noteproject.view.BaseActionBar;
import note.lym.org.noteproject.view.InsertNoteDialog;
import note.lym.org.noteproject.view.PopupUtils;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.listener.OnItemClickListener;
import project.recyclerview.lym.org.recyclerviewlibrary.listener.OnItemLongClickListener;

/**
 * 主页
 *
 * @author yaoming.li
 * @since 2017-04-25 11:35
 */
public class HomePagerActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, INotePresenter {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.title_bar)
    BaseActionBar mActionBar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.layout_no_note)
    View mNoNoteView;
    private NoteListAdapter mAdapter;
    private static final long DELAYED_TIME = 1500;
    private NoteListPresenter mNotePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_homepager;
    }

    @Override
    protected void initListener() {
        initActionBar();
        initRecyclerViewList();
    }

    private void initRecyclerViewList() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(manager);
        mRvList.setHasFixedSize(true);
        initAdapter();
        initRecyclerViewItemClickListener();
        initSwipeRefreshListener();
    }

    private void initSwipeRefreshListener() {
        mSwipe.setOnRefreshListener(this);
        mSwipe.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    private void initRecyclerViewItemClickListener() {
        mRvList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseFastAdapter adapter, View view, int position) {
                String noteName = ((Note) adapter.getData().get(position)).noteName;
                String noteTime = ((Note) adapter.getData().get(position)).date;
                String noteContent = ((Note) adapter.getData().get(position)).content;
                hideSoftInput();
                NoteDetailActivity.action(HomePagerActivity.this, noteName, noteTime, noteContent);
            }
        });

        mRvList.addOnItemTouchListener(new OnItemLongClickListener() {
            @Override
            public void onSimpleItemLongClick(BaseFastAdapter adapter, View view, final int position) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomePagerActivity.this);
                builder.setTitle(R.string.warm_toast);
                builder.setMessage(R.string.are_you_sure_delete_this_note);
                builder.setNegativeButton(R.string.negative, null);
                builder.setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mNotePresenter.deleteNote(position, mAdapter.getData().get(position), mAdapter);
                    }
                });
                builder.show();
            }
        });
    }

    private void initAdapter() {
        mAdapter = new NoteListAdapter(R.layout.item_note_list, null);
        mRvList.setAdapter(mAdapter);
    }

    private View getHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.header_search_view, (ViewGroup) mRvList.getParent(), false);
        EditText mEditSearch = (EditText) view.findViewById(R.id.edit_search_view);
        mEditSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mAdapter.setNewData(filterSearch(s.toString()));
                } else {
                    mAdapter.setNewData(mNotePresenter.getNoteListData());
                }
                mAdapter.filterKey(s.toString());
            }
        });
        return view;
    }

    private ArrayList<Note> filterSearch(String text) {
        ArrayList<Note> data = new ArrayList<>();
        for (Note note : mAdapter.getData()) {
            if (note.date.contains(text) || note.noteName.contains(text) || note.content.contains(text)) {
                data.add(note);
            }
        }
        return data;
    }

    private void initActionBar() {
        mActionBar.setTextTitle(R.string.title);
        mActionBar.setLeftImageResources(R.drawable.ic_nav);
        mActionBar.setLeftBackListener(false, null);
        mActionBar.setRightInsertClickListener(true, new BaseActionBar.RightInsertClickListener() {
            @Override
            public void onClick() {
                showDialog();
            }
        });
    }

    private void showDialog() {
        PopupUtils.showInsertNoteDialog(HomePagerActivity.this, new InsertNoteDialog.OnButtonClickListener() {
            @Override
            public void onClick(String note) {
                hideSoftInput();
                InsertNoteActivity.action(HomePagerActivity.this, note);
            }
        });
    }

    @Override
    protected void initData() {
        mNotePresenter = new NoteListPresenter(this);
        mNotePresenter.getNoteList();
    }


    @Override
    protected void bindView() {

    }

    /**
     * 启动当前的Activity
     *
     * @param activity activity
     */
    public static void action(Activity activity) {
        Intent intent = new Intent(activity, HomePagerActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }


    private static final int EXIT_TIME = 1500;
    private long mExit_Time = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApplication();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitApplication() {
        if (System.currentTimeMillis() - mExit_Time > EXIT_TIME) {
            Snackbar.make(mRvList, R.string.exit_app, Snackbar.LENGTH_SHORT).setAction(R.string.exit_application, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitApp();
                }
            }).show();
            mExit_Time = System.currentTimeMillis();
        } else {
            exitApp();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == InsertNoteActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            mNotePresenter.getNoteList();
        }
    }

    @Override
    public void onRefresh() {
        mAdapter.openLoadAnimation(BaseFastAdapter.SCALEIN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(mNotePresenter.getNoteListData());
                mSwipe.setRefreshing(false);
            }
        }, DELAYED_TIME);
    }

    @Override
    public void updateNoteList(List<Note> notes) {
        mAdapter.setNewData(notes);
    }

    @Override
    public void showDateView() {
        mNoNoteView.setVisibility(View.GONE);
    }

    @Override
    public void noDataView() {
        mNoNoteView.setVisibility(View.VISIBLE);
    }

    @Override
    public void initHeaderView() {
        if (mAdapter.getHeaderLayoutCount() == 0) {
            mAdapter.addHeaderView(getHeaderView());
        }
    }

    @Override
    public void removeHeaderView() {
        mAdapter.removeAllHeaderView();
    }
}
