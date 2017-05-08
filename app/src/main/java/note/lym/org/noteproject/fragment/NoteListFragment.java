package note.lym.org.noteproject.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.NoteListAdapter;
import note.lym.org.noteproject.base.BaseFragment;
import note.lym.org.noteproject.model.bean.Note;
import note.lym.org.noteproject.presenter.note.INoteView;
import note.lym.org.noteproject.presenter.note.NoteListPresenter;
import note.lym.org.noteproject.ui.home.InsertNoteActivity;
import note.lym.org.noteproject.ui.home.detail.NoteDetailActivity;
import note.lym.org.noteproject.view.InsertNoteDialog;
import note.lym.org.noteproject.view.PopupUtils;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.listener.OnItemClickListener;
import project.recyclerview.lym.org.recyclerviewlibrary.listener.OnItemLongClickListener;

import static android.app.Activity.RESULT_OK;

/**
 *
 * @author yaoming.li
 * @since 2017-05-04 18:01
 */
public class NoteListFragment extends BaseFragment<NoteListPresenter> implements SwipeRefreshLayout.OnRefreshListener, INoteView {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.layout_no_note)
    View mNoNoteView;
    private NoteListAdapter mAdapter;
    private static final long DELAYED_TIME = 1500;

    @Override
    protected void loadLazyData() {
        mPresenter.getData(true,10);
        mPresenter.getNoteList();
        Log.i(TAG,"load data");
    }

    @Override
    protected void updateViews() {
        initToolBar(mToolBar,true,getString(R.string.title));
        setHasOptionsMenu(true);
        mBar.setVisibility(View.GONE);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(manager);
        mRvList.setHasFixedSize(true);
        mAdapter = new NoteListAdapter(R.layout.item_note_list, null);
        mRvList.setAdapter(mAdapter);
        initRecyclerViewItemClickListener();
        initSwipeRefreshListener();
    }


    private void initRecyclerViewItemClickListener() {
        mRvList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseFastAdapter adapter, View view, int position) {
                String noteName = ((Note) adapter.getData().get(position)).noteName;
                String noteTime = ((Note) adapter.getData().get(position)).date;
                String noteContent = ((Note) adapter.getData().get(position)).content;
                hideSoftInput();
                NoteDetailActivity.action(NoteListFragment.this, noteName, noteTime, noteContent);
            }
        });

        mRvList.addOnItemTouchListener(new OnItemLongClickListener() {
            @Override
            public void onSimpleItemLongClick(BaseFastAdapter adapter, View view, final int position) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.warm_toast);
                builder.setMessage(R.string.are_you_sure_delete_this_note);
                builder.setNegativeButton(R.string.negative, null);
                builder.setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteNote(position, mAdapter.getData().get(position), mAdapter);
                    }
                });
                builder.show();
            }
        });
    }

    private void initSwipeRefreshListener() {
        mSwipe.setOnRefreshListener(this);
        mSwipe.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(NoteListFragment.this);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_homepager;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(mPresenter.getNoteListData());
                mSwipe.setRefreshing(false);
            }
        }, DELAYED_TIME);
    }

    @Override
    public void showDateView() {
        Log.i(TAG,"view gone");
        mNoNoteView.setVisibility(View.GONE);
    }

    @Override
    public void noDataView() {
        Log.i(TAG,"no data view");
        mNoNoteView.setVisibility(View.VISIBLE);
    }

    @Override
    public void initHeaderView() {
        if (mAdapter.getHeaderLayoutCount() == 0) {
            mAdapter.addHeaderView(getHeaderView());
        }
    }

    private View getHeaderView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.header_search_view, (ViewGroup) mRvList.getParent(), false);
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
                    mAdapter.setNewData(mPresenter.getNoteListData());
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

    @Override
    public void removeHeaderView() {
        mAdapter.removeAllHeaderView();
    }

    @Override
    public void updateNoteList(List<Note> notes) {
        mAdapter.setNewData(notes);
        Log.i(TAG,notes.size()+"我有这么多");
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_note, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_note) {
            PopupUtils.showInsertNoteDialog(getActivity(), new InsertNoteDialog.OnButtonClickListener() {
                @Override
                public void onClick(String note) {
                    InsertNoteActivity.action(NoteListFragment.this,getActivity(), note);
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == InsertNoteActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            mPresenter.getNoteList();
        }
    }
}
