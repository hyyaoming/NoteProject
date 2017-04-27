package note.lym.org.noteproject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;

import butterknife.BindView;
import butterknife.ButterKnife;
import note.lym.org.noteproject.ui.home.HomePagerActivity;

public class SplashActivity extends AppCompatActivity implements Runnable {

    @BindView(R.id.flb)
    FloatingActionButton mFloatButton;
    private static final int POST_DELAYED_TIME = 200;
    private static final int ANIMATOR_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mFloatButton.postDelayed(this, POST_DELAYED_TIME);
    }

    @Override
    public void run() {
        final View parentView = (View) mFloatButton.getParent();
        float scale = (float) (Math.sqrt(parentView.getHeight() * parentView.getHeight() + parentView.getWidth() * parentView.getWidth()) / mFloatButton.getHeight());
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", scale);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", scale);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mFloatButton, scaleX, scaleY).setDuration(ANIMATOR_TIME);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                parentView.setBackgroundColor(ContextCompat.getColor(SplashActivity.this, R.color.colorPrimary));
                mFloatButton.setVisibility(View.GONE);
                HomePagerActivity.action(SplashActivity.this);
            }
        });
        objectAnimator.start();
    }

}
