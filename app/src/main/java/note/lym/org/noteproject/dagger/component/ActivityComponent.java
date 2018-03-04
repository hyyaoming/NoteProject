package note.lym.org.noteproject.dagger.component;

import android.app.Activity;

import dagger.Component;
import note.lym.org.noteproject.dagger.ActivityScope;
import note.lym.org.noteproject.dagger.modul.ActivityModule;
import note.lym.org.noteproject.ui.home.activity.HomePagerActivity;
import note.lym.org.noteproject.ui.girl.activity.LookerGirlActivity;
import note.lym.org.noteproject.ui.detail.activity.HealthDetailActivity;
import note.lym.org.noteproject.ui.detail.activity.NewsDetailActivity;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();


    void inject(HomePagerActivity activity);

    void inject(NewsDetailActivity activity);

    void inject(HealthDetailActivity activity);

    void inject(LookerGirlActivity activity);

}
