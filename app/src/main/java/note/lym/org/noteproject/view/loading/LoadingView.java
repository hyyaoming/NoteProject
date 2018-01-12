package note.lym.org.noteproject.view.loading;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import note.lym.org.noteproject.R;

/**
 * description: 加载动画
 *
 * @author yaoming.li
 * @version 1.0.0
 * @since 2018/1/12/012
 */
public class LoadingView extends FrameLayout {

    @BindView(R.id.img_progress)
    ImageView mLoadingImage;
    private AnimationDrawable mDrawable;

    /**
     * 构造器
     *
     * @param context 上下文
     * @param attrs   资源文件
     */
    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.layout_progress, this);
        ButterKnife.bind(this);
        initDrawableAnimation();
        showLoading();
    }

    /**
     * 开启动画
     */
    public void showLoading() {
        setVisibility(VISIBLE);
        starAnim();
    }

    /**
     * 关闭动画
     */
    public void stopLoading() {
        setVisibility(GONE);
        stopAnim();
    }

    private void starAnim() {
        if (!mDrawable.isRunning()) {
            mDrawable.start();
        }
    }

    private void stopAnim() {
        if (mDrawable.isRunning()) {
            mDrawable.stop();
        }
    }

    private void initDrawableAnimation() {
        mDrawable = (AnimationDrawable) mLoadingImage.getDrawable();
    }
}
