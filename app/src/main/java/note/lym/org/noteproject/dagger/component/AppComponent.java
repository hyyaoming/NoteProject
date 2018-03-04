package note.lym.org.noteproject.dagger.component;


import javax.inject.Singleton;

import dagger.Component;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.dagger.ContextLife;
import note.lym.org.noteproject.dagger.modul.AppModule;
import note.lym.org.noteproject.dagger.modul.HttpModule;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.model.http.helper.ApiHelper;


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    @ContextLife("Application")
    NoteApplication getContext();  // 提供App的Context

    ApiHelper getApiHelper();

    DataManager getDataManager(); //数据中心

}
