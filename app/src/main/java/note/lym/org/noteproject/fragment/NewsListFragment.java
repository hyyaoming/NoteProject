package note.lym.org.noteproject.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.NewsListAdapter;
import note.lym.org.noteproject.base.BaseFragment;
import note.lym.org.noteproject.model.bean.NewsList;
import note.lym.org.noteproject.presenter.note.news.INewsView;
import note.lym.org.noteproject.presenter.note.news.NewsListPresenter;
import note.lym.org.noteproject.ui.home.detail.NewsDetailActivity;
import note.lym.org.noteproject.view.LoadStateView;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.listener.OnItemClickListener;
import project.recyclerview.lym.org.recyclerviewlibrary.util.FullSpanUtil;
import project.recyclerview.lym.org.recyclerviewlibrary.util.ItemMoveAndRemoveHelper;

/**
 * 网易新闻列表
 *
 * @author yaoming.li
 * @since 2017-05-08 18:33
 */
public class NewsListFragment extends BaseFragment<NewsListPresenter> implements INewsView, BaseFastAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_news_list)
    RecyclerView mRvList;
    private int page = 0;
    private NewsListAdapter mAdapter;


    @Override
    protected void loadLazyData() {
        mPresenter.getNewsData(page);
    }

    @Override
    protected void updateViews() {
        mAdapter = new NewsListAdapter(R.layout.item_news_list, null);
        FullSpanUtil.setLinearLayoutManage(mRvList, mAdapter, LinearLayoutManager.VERTICAL);
        ItemMoveAndRemoveHelper.openItemMove(mRvList, mAdapter);
        mRvList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseFastAdapter adapter, View view, int position) {
                String newsId = ((NewsList.NewsBean) adapter.getData().get(position)).getDocid();
                NewsDetailActivity.launch(getActivity(), newsId);
            }
        });
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
    public void getNewsList(List<NewsList.NewsBean> list) {
        mAdapter.addData(list);
        if (page > 0) {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void showError(LoadStateView.OnRequestListener listener) {
        if (page == 0) {
            super.showError(listener);
        } else {
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void onLoadMoreRequested() {
        page += 20;
        mPresenter.getNewsData(page);
    }

}
