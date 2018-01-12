package note.lym.org.noteproject.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.view.loading.LoadingView;

/**
 * 加载状态处理类
 *
 * @author yaoming.li
 * @since 2017-05-18 14:27
 */
public class LoadStateView extends FrameLayout {
    public static final int LOADING_HIDE = 0xff011; //请求完毕
    public static final int LOADING_SHOW = 0xff022;  //正在请求
    public static final int LOADING_NO_NETWORK = 0xff033; //没网了
    public static final int LOADING_NO_DATE = 0xff044; //没有数据
    private OnRequestListener mRequestListener;
    private int mState = LOADING_SHOW;
    @BindView(R.id.loading_view)
    LoadingView mLoadingView;
    @BindView(R.id.fl_no_network)
    FrameLayout mLayout;
    private AnimationDrawable mAnimationDrawable;

    /**
     * 设置接口
     *
     * @param requestListener 再次请求接口
     */
    public void setRequestListener(OnRequestListener requestListener) {
        this.mRequestListener = requestListener;
    }

    /**
     * 再次请求
     */
    public interface OnRequestListener {
        void onRequest();
    }

    @OnClick(R.id.fl_no_network)
    public void click() {
        if (mRequestListener != null) {
            mRequestListener.onRequest();
        }
    }

    /**
     * 设置当前加载的状态
     *
     * @param state 状态
     */
    public void setLoadingState(int state) {
        this.mState = state;
        setLoadingStatus();
    }

    /***
     * 返回当前加载的状态
     *
     * @return int
     */
    public int getLoadingState() {
        return mState;
    }

    public LoadStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreateView(context);
    }

    private void onCreateView(Context context) {
        View.inflate(context, R.layout.layout_loading_view, this);
        ButterKnife.bind(this);
        setLoadingStatus();
    }

    private void setLoadingStatus() {
        switch (mState) {
            case LOADING_HIDE:
                setVisibility(GONE);
                break;
            case LOADING_NO_DATE:
            case LOADING_NO_NETWORK:
                setVisibility(VISIBLE);
                mLoadingView.stopLoading();
                mLayout.setVisibility(VISIBLE);
                break;
            case LOADING_SHOW:
                setVisibility(VISIBLE);
                mLoadingView.showLoading();
                mLayout.setVisibility(GONE);
                break;
            default:
                break;
        }
    }
}
