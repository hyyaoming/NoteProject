package note.lym.org.noteproject.ui.home.detail;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.BaseActivity;
import note.lym.org.noteproject.model.bean.HealthDetail;
import note.lym.org.noteproject.presenter.note.healthdetail.HealthDetailPresenter;
import note.lym.org.noteproject.presenter.note.healthdetail.IHealthDetailView;
import note.lym.org.noteproject.utils.DefIconFactory;
import note.lym.org.noteproject.utils.GlideUtils;
import note.lym.org.noteproject.view.image.BadgedFourThreeImageView;

/**
 * 健康资讯详情页
 *
 * @author yaoming.li
 * @since 2017-05-12 14:49
 */
public class HealthDetailActivity extends BaseActivity<HealthDetailPresenter> implements IHealthDetailView {

    public static final String HEALTH_ID = "health_id";
    @BindView(R.id.tv_health_detail_content)
    TextView mTvHealthContent;
    @BindView(R.id.tv_health_detail_title)
    TextView mTvHealthTitle;
    @BindView(R.id.tv_health_detail_date)
    TextView mTvHealthDate;
    @BindView(R.id.tv_health_detail_source)
    TextView mTvHealthSource;
    @BindView(R.id.iv_health_detail_iv)
    BadgedFourThreeImageView mIvHealthImage;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_health_detail;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.getHealthDetail(getIntent().getStringExtra(HEALTH_ID));
    }

    @Override
    protected void bindView() {

    }

    public static void launch(Fragment fragment,String id){
        Intent intent = new Intent(fragment.getActivity(),HealthDetailActivity.class);
        intent.putExtra(HEALTH_ID,id);
        fragment.startActivity(intent);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void getHealthDetail(HealthDetail detail) {
        HealthDetail.ShowapiResBodyBean.ItemBean bean = detail.getShowapi_res_body().getItem();
        initToolBar(mToolBar,true,bean.getTname());
        RichText.from(bean.getContent())
                .into(mTvHealthContent);
        GlideUtils.load(this, bean.getImg(),DefIconFactory.iconDefault(),DefIconFactory.iconDefault(),mIvHealthImage);
        mTvHealthDate.setText(bean.getTime());
        mTvHealthSource.setText(bean.getAuthor());
        mTvHealthTitle.setText(bean.getTitle());
    }
}
