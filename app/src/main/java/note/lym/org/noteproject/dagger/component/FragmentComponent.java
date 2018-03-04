package note.lym.org.noteproject.dagger.component;

import android.app.Activity;

import dagger.Component;
import note.lym.org.noteproject.dagger.FragmentScope;
import note.lym.org.noteproject.dagger.modul.FragmentModule;
import note.lym.org.noteproject.ui.girl.fragment.BelleListFragment;
import note.lym.org.noteproject.ui.news.fragment.ClassifyListFragment;
import note.lym.org.noteproject.ui.news.fragment.HealthMessageFragment;
import note.lym.org.noteproject.ui.news.fragment.JokeListFragment;
import note.lym.org.noteproject.ui.news.fragment.MaySisterFragment;
import note.lym.org.noteproject.ui.news.fragment.NewsListFragment;
import note.lym.org.noteproject.ui.note.fragment.NoteListFragment;
import note.lym.org.noteproject.ui.news.fragment.PersonToLifeFragment;
import note.lym.org.noteproject.ui.girl.fragment.SisterClassifyFragment;
import note.lym.org.noteproject.ui.girl.fragment.SisterClassifyListFragment;
import note.lym.org.noteproject.ui.news.fragment.TextJokeListFragment;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(NoteListFragment fragment);

    void inject(BelleListFragment fragment);

    void inject(NewsListFragment fragment);

    void inject(JokeListFragment fragment);

    void inject(TextJokeListFragment fragment);

    void inject(HealthMessageFragment fragment);

    void inject(ClassifyListFragment fragment);

    void inject(SisterClassifyFragment fragment);

    void inject(SisterClassifyListFragment fragment);

    void inject(MaySisterFragment fragment);

    void inject(PersonToLifeFragment fragment);


}