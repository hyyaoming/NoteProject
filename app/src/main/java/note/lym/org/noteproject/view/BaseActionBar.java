package note.lym.org.noteproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import note.lym.org.noteproject.R;

/**
 * 头部工具类
 *
 * @author yaoming.li
 * @since 2017-04-25 18:20
 */
public class BaseActionBar extends LinearLayout {
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.iv_back)
    ImageView mIvBack;

    @BindView(R.id.iv_insert)
    ImageView mIvInsert;

    public BaseActionBar(Context context) {
        this(context, null);
    }

    public BaseActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_action_bar, this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_title, R.id.iv_back, R.id.iv_insert})
    public void jump(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (mLeftBackListener != null) {
                    mLeftBackListener.onClick();
                }
                break;
            case R.id.tv_title:
                if (mTitleClickListener != null) {
                    mTitleClickListener.onClick();
                }
                break;
            case R.id.iv_insert:
                if (mRightInsertClickListener != null) {
                    mRightInsertClickListener.onClick();
                }
                break;
            default:
                break;
        }
    }


    public interface LeftBackListener {
        void onClick();
    }

    private LeftBackListener mLeftBackListener;

    public void setLeftBackListener(LeftBackListener listener) {
        mLeftBackListener = listener;
    }

    public interface TitleClickListener {
        void onClick();
    }

    private TitleClickListener mTitleClickListener;

    public void setTitleClickListener(TitleClickListener listener) {
        mTitleClickListener = listener;
    }

    public interface RightInsertClickListener {
        void onClick();
    }

    private RightInsertClickListener mRightInsertClickListener;

    public void setRightInsertClickListener(boolean visible,RightInsertClickListener listener) {
        mRightInsertClickListener = listener;
        if(visible){
            mIvInsert.setVisibility(VISIBLE);
        }else{
            mIvInsert.setVisibility(GONE);
        }
    }

    public void setTextTitle(String content) {
        mTvTitle.setText(content);
    }

    public void setTextTitle(int res) {
        mTvTitle.setText(getContext().getString(res));
    }

    public void setLeftImageResources(int resources) {
        mIvBack.setImageResource(resources);
    }

    public void setRightImageResources(int resources) {
        mIvInsert.setImageResource(resources);
    }

    public ImageView getRightImageView(){
        return mIvInsert;
    }
}
