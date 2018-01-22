package note.lym.org.noteproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import note.lym.org.noteproject.ui.home.HomePagerActivity;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.iv_svg)
    SVGAImageView mIvSVG;
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mIvSVG.setCallback(new SVGACallback() {
            @Override
            public void onPause() {
                Log.d(TAG, "svg：onPause");
            }

            @Override
            public void onFinished() {
                Log.d(TAG, "svg：onFinished");
                HomePagerActivity.action(SplashActivity.this);
            }

            @Override
            public void onRepeat() {
                Log.d(TAG, "svg：onRepeat");
            }

            @Override
            public void onStep(int frame, double percentage) {
                Log.d(TAG, "svg：onStep");
            }
        });
    }

}
