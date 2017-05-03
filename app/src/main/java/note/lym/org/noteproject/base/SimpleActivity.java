package note.lym.org.noteproject.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import note.lym.org.noteproject.manage.ActivityManage;
import note.lym.org.noteproject.utils.SoftInputUtil;

/**
 * 无MVP的activity
 */
public abstract class SimpleActivity extends AppCompatActivity {


    protected Activity mContext;
    private Unbinder mUnBinder;
    private String mPageName = "com.onlyhiedu.mobile";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        //竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        ActivityManage.getInstance().addActivityToStack(this);
        initEventAndData();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManage.getInstance().removeActivityBackStack(this);
        mUnBinder.unbind();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();

    /**
     * 隐藏系统软键盘
     */
    protected void hideSoftInput(){
        if (SoftInputUtil.isOpen()) {
            SoftInputUtil.hideSysSoftInput(this);
        }
    }

}
