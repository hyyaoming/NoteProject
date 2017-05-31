package note.lym.org.noteproject.fragment;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.TextJokeListAdapter;
import note.lym.org.noteproject.base.BaseFragment;
import note.lym.org.noteproject.model.bean.TextJoke;
import note.lym.org.noteproject.presenter.note.joke.ITextJokeView;
import note.lym.org.noteproject.presenter.note.joke.TextJokePersenter;
import note.lym.org.noteproject.view.LoadStateView;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.util.FullSpanUtil;

/**
 * 文本笑话大全
 *
 * @author yaoming.li
 * @since 2017-05-11 23:12
 */
public class TextJokeListFragment extends BaseFragment<TextJokePersenter> implements ITextJokeView, BaseFastAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_news_list)
    RecyclerView mRvList;
    private TextJokeListAdapter mAdapter;

    private static final int ITEM_COUNT = 10;
    private int mPage = 1;

    @Override
    protected void loadLazyData() {
            mPresenter.getTextJokeData(ITEM_COUNT,mPage);
    }

    @Override
    protected void updateViews() {
        mAdapter = new TextJokeListAdapter(R.layout.layout_text_joke_item,null);
        FullSpanUtil.setLinearLayoutManage(mRvList,mAdapter,LinearLayoutManager.VERTICAL);
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
    public void getTextJokeList(List<TextJoke.ShowapiResBodyBean.ContentlistBean> list) {
        mAdapter.addData(list);
        if(mPage>1){
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void showError(LoadStateView.OnRequestListener listener) {
        if(mPage==1){
            super.showError(listener);
        }else{
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void onLoadMoreRequested() {
            mPage++;
        mPresenter.getTextJokeData(ITEM_COUNT,mPage);
    }
}
