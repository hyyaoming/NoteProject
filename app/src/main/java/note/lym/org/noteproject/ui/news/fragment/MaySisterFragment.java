package note.lym.org.noteproject.ui.news.fragment;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.fragment.BaseFragment;
import note.lym.org.noteproject.model.bean.MaySisterData;
import note.lym.org.noteproject.presenter.maysister.MaySisterContract;
import note.lym.org.noteproject.presenter.maysister.MaySisterPresenter;
import note.lym.org.noteproject.ui.news.adapter.MaySisterAdapter;
import note.lym.org.noteproject.view.LoadStateView;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.util.FullSpanUtil;

/**
 * 不得姐列表类
 *
 * @author yaoming.li
 * @since 2017-05-16 14:10
 */
public class MaySisterFragment extends BaseFragment<MaySisterPresenter> implements MaySisterContract.View, BaseFastAdapter.RequestLoadMoreListener {

    private int mPage = 1;
    @BindView(R.id.rv_news_list)
    RecyclerView mRvList;
    private MaySisterAdapter mAdapter;

    @Override
    protected void loadLazyData() {
        mPresenter.getMaySisterData(mPage);
    }

    @Override
    protected void updateViews() {
        mAdapter = new MaySisterAdapter(R.layout.layout_maysister_item, null);
        FullSpanUtil.setLinearLayoutManage(mRvList, mAdapter, 1);
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
    public void getMaySisterData(List<MaySisterData.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list) {
        if (mPage == 1) {
            mAdapter.loadMoreComplete();
        }
        mAdapter.addData(list);
    }

    @Override
    public void showError(LoadStateView.OnRequestListener listener) {
        if(mPage ==1){
            super.showError(listener);
        }else{
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        mPresenter.getMaySisterData(mPage);
    }
}
