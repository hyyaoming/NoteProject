package note.lym.org.noteproject.base.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import java.util.LinkedList;
import java.util.List;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;
import note.lym.org.noteproject.utils.manage.ActivityManage;

/**
 * 运行时权限处理类
 *
 * @author yaoming.li
 * @since 2017-04-25 10:31
 */
@SuppressLint("Registered")
public class BaseRunTimePermission extends SwipeBackActivity {

    private static RequestPermissionListener mListener;
    private static final int REQUEST_PERMISSION_CODE = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManage.getInstance().addActivityToStack(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManage.getInstance().removeActivityBackStack(this);
    }

    /**
     * 处理运行时权限
     *
     * @param arr      传入一个需要申请权限的数组
     * @param listener 回调
     */
    public static void requestRunTimePermission(String arr[], RequestPermissionListener listener) {
        mListener = listener;
        Activity activity = ActivityManage.getInstance().getStackTopActivity();
        if (null == activity) {
            return;
        }
        LinkedList<String> arrays = new LinkedList<>();
        for (String permission : arr) {
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                arrays.add(permission);
            }
        }
        if (arrays.isEmpty()) {
            listener.accredit();
        } else {
            ActivityCompat.requestPermissions(activity, arrays.toArray(new String[arrays.size()]), REQUEST_PERMISSION_CODE);
        }
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar         Toolbar
     * @param homeAsUpEnabled 是否显示menu按钮
     * @param title           toolbar标题
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle) {
        initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                LinkedList<String> requestArrays = new LinkedList<>();
                for (int x = 0; x < grantResults.length; x++) {
                    int grant = grantResults[x];
                    String mission = permissions[x];
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        requestArrays.add(mission);
                    }
                }
                if (requestArrays.isEmpty()) {
                    mListener.accredit();
                } else {
                    mListener.decline(requestArrays);
                }
                break;
        }
    }

    public interface RequestPermissionListener {

        void accredit();

        void decline(List<String> array);

    }

}
