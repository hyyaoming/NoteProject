package note.lym.org.noteproject.dagger.modul;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import note.lym.org.noteproject.dagger.ActivityScope;


@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
