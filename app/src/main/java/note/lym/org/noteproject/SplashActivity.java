package note.lym.org.noteproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import note.lym.org.noteproject.ui.home.HomePagerActivity;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.iv_svg)
    SVGAImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mIv.setLoops(1);
        mIv.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                HomePagerActivity.action(SplashActivity.this);
            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int frame, double percentage) {

            }
        });
    }


}
