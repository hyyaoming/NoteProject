package note.lym.org.noteproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.BaseFragment;
import note.lym.org.noteproject.eventbus.FlbEvent;
import note.lym.org.noteproject.model.bean.BannerModel;
import note.lym.org.noteproject.model.bean.HealthList;
import note.lym.org.noteproject.model.bean.Note;
import note.lym.org.noteproject.model.dao.Collect;
import note.lym.org.noteproject.presenter.note.health.HelthListPresenter;
import note.lym.org.noteproject.presenter.note.health.IHealthListView;
import note.lym.org.noteproject.ui.home.BigBelleActivity;
import note.lym.org.noteproject.ui.home.detail.HealthDetailActivity;
import note.lym.org.noteproject.vlayout.BannerViewHolder;
import note.lym.org.noteproject.vlayout.ClassifyListHolder;
import note.lym.org.noteproject.vlayout.GoodsMessageViewHolder;
import note.lym.org.noteproject.vlayout.OnItemListener;
import note.lym.org.noteproject.vlayout.RequestLoadMoreListener;
import note.lym.org.noteproject.vlayout.VLayoutBaseAdapter;

/**
 * 健康资讯列表
 *
 * @author yaoming.li
 * @since 2017-05-12 10:46
 */
public class ClassifyListFragment extends BaseFragment<HelthListPresenter> implements IHealthListView, RequestLoadMoreListener {

    public static final String HEALTH_ID = "health_id";
    private String mHealthId;
    @BindView(R.id.rv_news_list)
    RecyclerView mRvList;
    private int mPage = 1;
    private VLayoutBaseAdapter mBannerAdapter, mVLayoutAdapter, mGoodsMesaage;

    @Override
    protected void loadLazyData() {
        mPresenter.getHealthListData(mHealthId, mPage);
    }

    @Override
    protected void updateViews() {
        EventBus.getDefault().register(this);
        VirtualLayoutManager manager = new VirtualLayoutManager(mContext);
        DelegateAdapter adapter = new DelegateAdapter(manager, false);
        mRvList.setLayoutManager(manager);
        //第二个布局
        mVLayoutAdapter = new VLayoutBaseAdapter(mContext)
                .setLayoutId(R.layout.item_news_list)
                .setHolder(ClassifyListHolder.class)
                .setData(null)
                .setLayoutHelper(new LinearLayoutHelper())
                .setListener(new OnItemListener<HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean>() {
                    @Override
                    public void onItemClickListener(View view, HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean data, int position) {
                        HealthDetailActivity.launch(ClassifyListFragment.this, data.getId());
                    }
                }).setLoadMoreListener(mRvList, this);
        adapter.addAdapter(mVLayoutAdapter);
        mRvList.setAdapter(adapter);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHealthId = getArguments().getString(HEALTH_ID);
        }
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_news_list;
    }

    public static ClassifyListFragment newInstance(String id) {
        ClassifyListFragment fragment = new ClassifyListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(HEALTH_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void getHealthListData(List<HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list) {
        mVLayoutAdapter.addData(list);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFlbEvent(FlbEvent event) {
        mRvList.getLayoutManager().scrollToPosition(0);
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        mPresenter.getHealthListData(mHealthId, mPage);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
