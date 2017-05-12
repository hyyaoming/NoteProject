package note.lym.org.noteproject.Dagger.Component;

import android.app.Activity;

import dagger.Component;
import note.lym.org.noteproject.Dagger.ActivityScope;
import note.lym.org.noteproject.Dagger.Modul.ActivityModule;
import note.lym.org.noteproject.ui.home.HomePagerActivity;
import note.lym.org.noteproject.ui.home.detail.HealthDetailActivity;
import note.lym.org.noteproject.ui.home.detail.NewsDetailActivity;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();


    void inject(HomePagerActivity activity);

    void inject(NewsDetailActivity activity);

    void inject(HealthDetailActivity activity);
}
