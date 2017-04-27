package project.recyclerview.lym.org.recyclerviewlibrary.BaseAnimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Desp.
 *
 * @author yaoming.li
 * @version 8.1.0
 * @since 2017-03-14 19:19
 */
public class AlphaInAnimation implements BaseAnimation {
    private static final float DEFAULT_ALPHA_FROM = 0f;
    private final float mFrom;

    public AlphaInAnimation() {
        this(DEFAULT_ALPHA_FROM);
    }

    public AlphaInAnimation(float from) {
        mFrom = from;
    }

    @Override
    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", mFrom, 1f)};
    }
}
