package note.lym.org.noteproject.app;

import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;

import org.litepal.LitePalApplication;

import note.lym.org.noteproject.Dagger.Component.AppComponent;
import note.lym.org.noteproject.Dagger.Component.DaggerAppComponent;
import note.lym.org.noteproject.Dagger.Modul.AppModule;
import note.lym.org.noteproject.utils.Static;
import note.lym.org.noteproject.utils.ToastUtils;

/**
 *
 * application
 * @author yaoming.li
 * @since 2017-04-25 15:08
 */
public class NoteApplication extends LitePalApplication {

    private static NoteApplication instance;

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
        ToastUtils.init(this);
        Static.CONTEXT = this;
        Static.INFLATER = LayoutInflater.from(this);
        instance = this;
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }

}
