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
import android.view.KeyEvent;
import android.view.View;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.NoteListAdapter;
import note.lym.org.noteproject.base.BaseActivity;
import note.lym.org.noteproject.model.Note;
import note.lym.org.noteproject.ui.home.detail.NoteDetailActivity;
import note.lym.org.noteproject.utils.ToastUtils;
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
 * @version 8.3.0
 * @since 2017-04-25 11:35
 */
public class HomePagerActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.title_bar)
    BaseActionBar mActionBar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.layout_no_note)
    View mNoNoteView;
    private ArrayList<Note> mArrays = new ArrayList<>();
    private NoteListAdapter mAdapter;
    private static final long DELAYED_TIME = 1500;

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
        mSwipe.setColorSchemeColors(ContextCompat.getColor(this,R.color.colorPrimaryDark));
    }

    private void initRecyclerViewItemClickListener() {
        mRvList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseFastAdapter adapter, View view, int position) {
                if (position > mArrays.size()) {
                    return;
                }
                String noteName = mArrays.get(position).noteName;
                String noteTime = mArrays.get(position).date;
                String noteContent = mArrays.get(position).content;
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
                        Note note = mArrays.get(position);
                        mArrays.remove(note);
                        DataSupport.deleteAll(Note.class,"date = ?",note.date);
                        mAdapter.notifyDataSetChanged();
                        showNoDataView(mArrays);
                    }
                });
                builder.show();
            }
        });
    }

    private void initAdapter() {
        mAdapter = new NoteListAdapter(R.layout.item_note_list, mArrays);
        mRvList.setAdapter(mAdapter);
    }

    private void initActionBar() {
        mActionBar.setTextTitle(R.string.title);
        mActionBar.setLeftBackListener(new BaseActionBar.LeftBackListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        mActionBar.setTitleClickListener(new BaseActionBar.TitleClickListener() {
            @Override
            public void onClick() {
                ToastUtils.showToast(R.string.title);
            }
        });
        mActionBar.setRightInsertClickListener(true,new BaseActionBar.RightInsertClickListener() {
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
                InsertNoteActivity.action(HomePagerActivity.this,note);
            }
        });
    }

    @Override
    protected void initData() {
        mArrays.clear();
        mArrays.addAll(findArray());
        showNoDataView(mArrays);
    }

    private void showNoDataView(ArrayList<Note> data){
        if (data.isEmpty()) {
            mNoNoteView.setVisibility(View.VISIBLE);
        } else {
            mNoNoteView.setVisibility(View.GONE);
        }
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
            initData();
            initAdapter();
        }
    }

    @Override
    public void onRefresh() {
        mAdapter.openLoadAnimation(BaseFastAdapter.SCALEIN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(findArray());
                mSwipe.setRefreshing(false);
            }
        },DELAYED_TIME);
    }

    private ArrayList<Note> findArray(){
        return (ArrayList<Note>) DataSupport.findAll(Note.class);
    }
}
