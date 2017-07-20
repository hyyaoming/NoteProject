package note.lym.org.noteproject.ui.home.detail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.BaseActivity;
import note.lym.org.noteproject.model.bean.NewsDetailBean;
import note.lym.org.noteproject.presenter.note.news.INewsDetailView;
import note.lym.org.noteproject.presenter.note.news.NewsDetailPresenter;

/**
 *
 * @author yaoming.li
 * @since 2017-05-09 11:34
 */
public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter> implements INewsDetailView {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tv_news_title)
    TextView mTvNewsName;
    @BindView(R.id.tv_source)
    TextView mTvSource;
    @BindView(R.id.tv_time)
    TextView mTvNewsTime;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    private String mNewsDetailId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_detail;
    }

    @Override
    protected void initListener() {
        initToolBar(mToolBar,true,"新闻详情");
    }

    @Override
    protected boolean enableBar() {
        return false;
    }

    @Override
    protected void initData() {
        mNewsDetailId = getIntent().getStringExtra("news_id");
        mPresenter.getNewsDetailId(mNewsDetailId);
    }

    @Override
    protected void bindView() {

    }

    public static void launch(Activity activity,String detailNewsId){
        Intent intent = new Intent(activity,NewsDetailActivity.class);
        intent.putExtra("news_id",detailNewsId);
        activity.startActivity(intent);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void getNewsDetail(NewsDetailBean newsDetail) {
        mTvNewsName.setText(newsDetail.getTitle());
        mTvSource.setText(newsDetail.getSource());
        mTvNewsTime.setText(newsDetail.getPtime());
        RichText.from(newsDetail.getBody())
                .into(mTvContent);
    }
}
