package note.lym.org.noteproject.app;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

import org.litepal.LitePalApplication;

import note.lym.org.noteproject.app.constant.Constants;
import note.lym.org.noteproject.dagger.component.AppComponent;
import note.lym.org.noteproject.dagger.component.DaggerAppComponent;
import note.lym.org.noteproject.dagger.modul.AppModule;
import note.lym.org.noteproject.dagger.modul.HttpModule;
import note.lym.org.noteproject.utils.ConstantUtil;
import note.lym.org.noteproject.utils.PreferencesUtils;
import note.lym.org.noteproject.utils.Static;
import note.lym.org.noteproject.utils.ToastUtils;

/**
 * application
 *
 * @author yaoming.li
 * @since 2017-04-25 15:08
 */
public class NoteApplication extends LitePalApplication {

    private static final String TAG = NoteApplication.class.getSimpleName();
    private static NoteApplication instance;
    public static AppComponent appComponent;

    public static synchronized NoteApplication getInstance() {
        return instance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initStyle();
        initHotFix();
        ToastUtils.init(this);
        Static.CONTEXT = this;
        Static.INFLATER = LayoutInflater.from(this);
    }

    private void initStyle() {
        boolean isNight = PreferencesUtils.getBoolean(ConstantUtil.SWITCH_MODE_KEY, false);
        AppCompatDelegate.setDefaultNightMode(isNight ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    private void initHotFix() {
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
            Log.d("TAG", "appVersion:" + appVersion);
        } catch (PackageManager.NameNotFoundException e) {
            appVersion = "1.0.0";
        }
        // initialize最好放在attachBaseContext最前面
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                .setEnableDebug(true)
                .setSecretMetaData(Constants.HOT_FIX_APP_ID, Constants.HOT_FIX_APP_SECRET, Constants.HOT_FIX_RSA_PASSWORD)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.d(TAG, "patch load success");
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            Log.d(TAG, "patch load success,bug must restart  gift effect");
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            Log.d(TAG, "patch load fail,essential clear patch");
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            SophixManager.getInstance().cleanPatches();
                        } else {
                            Log.d(TAG, "unknown error" + code);
                            // 其它错误信息, 查看PatchStatus类说明
                        } //test
                    }
                }).initialize();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
    }

}
