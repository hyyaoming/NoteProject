package note.lym.org.noteproject.fragment;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.JokeListAdapter;
import note.lym.org.noteproject.base.BaseFragment;
import note.lym.org.noteproject.model.bean.Joke;
import note.lym.org.noteproject.presenter.note.joke.IJokeView;
import note.lym.org.noteproject.presenter.note.joke.JokePresenter;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;

/**
 * 轻松一刻
 *
 * @author yaoming.li
 * @since 2017-05-11 16:13
 */
public class JokeListFragment extends BaseFragment<JokePresenter> implements IJokeView, BaseFastAdapter.RequestLoadMoreListener {


    @BindView(R.id.rv_news_list)
    RecyclerView mRvList;
    private JokeListAdapter mAdapter;

    private static final int ITEM_COUNT = 10;
    private int mPage = 1;

    @Override
    protected void loadLazyData() {
        mPresenter.getJokeData(ITEM_COUNT, mPage);
    }

    @Override
    protected void updateViews() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new JokeListAdapter(R.layout.layout_joke_item,null);
        mRvList.setHasFixedSize(true);
        mRvList.setLayoutManager(manager);
        mRvList.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this);
    }


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);

    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_news_list;
    }

    @Override
    public void getJokeList(List<Joke.ShowapiResBodyBean.ContentlistBean> list) {
        mAdapter.addData(list);
        if(mPage>1){
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void showError(String msg) {
        mBar.setVisibility(View.GONE);
        Snackbar.make(mRvList, msg, Snackbar.LENGTH_LONG).setAction(R.string.again, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getJokeData(ITEM_COUNT,mPage);
            }
        }).show();
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        mPresenter.getJokeData(ITEM_COUNT,mPage);
    }
}