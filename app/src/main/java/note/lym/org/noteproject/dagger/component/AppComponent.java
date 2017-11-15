package note.lym.org.noteproject.dagger.component;


import javax.inject.Singleton;

import dagger.Component;
import note.lym.org.noteproject.dagger.ContextLife;
import note.lym.org.noteproject.dagger.modul.AppModule;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.model.http.RetrofitHelper;


@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    NoteApplication getContext();  // 提供App的Context

    RetrofitHelper retrofitHelper();  //提供http的帮助类


}
