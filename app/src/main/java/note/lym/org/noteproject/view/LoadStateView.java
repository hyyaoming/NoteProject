package note.lym.org.noteproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import note.lym.org.noteproject.R;

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
    @BindView(R.id.loading)
    LoadingView mLoadingView;
    @BindView(R.id.fl_no_network)
    FrameLayout mNetWorkErrorLayout;
    @BindView(R.id.frameNoData)
    FrameLayout mNoDataView;

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
                hideOrShowLoading(false);
                break;
            case LOADING_NO_DATE:
                mNoDataView.setVisibility(VISIBLE);
                mNetWorkErrorLayout.setVisibility(GONE);
                hideOrShowLoading(false);
                break;
            case LOADING_NO_NETWORK:
                hideOrShowLoading(false);
                mNoDataView.setVisibility(GONE);
                mNetWorkErrorLayout.setVisibility(VISIBLE);
                break;
            case LOADING_SHOW:
                hideOrShowLoading(true);
                mNetWorkErrorLayout.setVisibility(GONE);
                mNoDataView.setVisibility(GONE);
                break;
            default:
                break;
        }
    }

    private void hideOrShowLoading(boolean show) {
        mLoadingView.setVisibility(show ? VISIBLE : GONE);
    }
}
