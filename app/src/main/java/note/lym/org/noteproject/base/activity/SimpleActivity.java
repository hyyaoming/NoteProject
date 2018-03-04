package note.lym.org.noteproject.base.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import note.lym.org.noteproject.utils.manage.ActivityManage;

/**
 * 无MVP的activity
 */
public abstract class SimpleActivity extends BaseRunTimePermission {


    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        //竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        mContext = this;
        initEventAndData();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();

    /**
     * 退出程序
     */
    protected void exitApp() {
        ActivityManage.getInstance().backAllActivityToStack();
        android.os.Process.killProcess(Process.myPid());
    }



}
