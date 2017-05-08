package note.lym.org.noteproject.fragment;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.adapter.BelleListAdapter;
import note.lym.org.noteproject.base.BaseFragment;
import note.lym.org.noteproject.model.bean.Belle;
import note.lym.org.noteproject.presenter.note.belle.BellePresenter;
import note.lym.org.noteproject.presenter.note.belle.IBelleView;
import note.lym.org.noteproject.ui.home.BigBelleActivity;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.listener.OnItemClickListener;

/**
 * 妹子列表
 *
 * @author yaoming.li
 * @since 2017-05-03 21:02
 */
public class BelleListFragment extends BaseFragment<BellePresenter> implements IBelleView, BaseFastAdapter.RequestLoadMoreListener {

    private static final String TAG = BelleListFragment.class.getSimpleName();

    @BindView(R.id.rv_belle_list)
    RecyclerView mRvBelleList;
    BelleListAdapter mAdapter;
    private int page = 1;

    @Override
    protected void loadLazyData() {
        Log.i(TAG, "loadLazyData");
        mPresenter.getBelleData(page);
    }

    @Override
    protected void updateViews() {
        mAdapter = new BelleListAdapter(R.layout.belle_list_item, null);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvBelleList.setLayoutManager(manager);
        mRvBelleList.setHasFixedSize(true);
        mRvBelleList.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this);
        mRvBelleList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseFastAdapter adapter, View view, int position) {
                String url = ((Belle.ResultsBean)adapter.getData().get(position)).getUrl();
                BigBelleActivity.action(BelleListFragment.this,url);
            }
        });
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(BelleListFragment.this);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_belle_list;
    }

    @Override
    public void getBelleList(List<Belle.ResultsBean> list) {
        mAdapter.addData(list);
        if (page > 1) {
            mAdapter.loadMoreComplete();
        }
        Log.i(TAG, list.size() + "");
    }

    @Override
    public void showError(String msg) {
        mBar.setVisibility(View.GONE);
        Snackbar.make(mRvBelleList, msg, Snackbar.LENGTH_LONG).setAction(R.string.again, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getBelleData(page);
            }
        }).show();
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        mPresenter.getBelleData(page);
    }
}
