package note.lym.org.noteproject.dagger.modul;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.dagger.ContextLife;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.model.http.helper.ApiHelper;
import note.lym.org.noteproject.model.http.helper.HttpHelper;


@Module
public class AppModule {
    private final NoteApplication application;

    public AppModule(NoteApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    NoteApplication provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideApiHelper(ApiHelper apiHelper) {
        return apiHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }

}
