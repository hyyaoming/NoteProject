package note.lym.org.noteproject.dagger.component;

import android.app.Activity;

import dagger.Component;
import note.lym.org.noteproject.dagger.FragmentScope;
import note.lym.org.noteproject.dagger.modul.FragmentModule;
import note.lym.org.noteproject.fragment.BelleListFragment;
import note.lym.org.noteproject.fragment.ClassifyListFragment;
import note.lym.org.noteproject.fragment.HealthMessageFragment;
import note.lym.org.noteproject.fragment.JokeListFragment;
import note.lym.org.noteproject.fragment.MaySisterFragment;
import note.lym.org.noteproject.fragment.NewsListFragment;
import note.lym.org.noteproject.fragment.NoteListFragment;
import note.lym.org.noteproject.fragment.PersonToLifeFragment;
import note.lym.org.noteproject.fragment.SisterClassifyFragment;
import note.lym.org.noteproject.fragment.SisterClassifyListFragment;
import note.lym.org.noteproject.fragment.TextJokeListFragment;

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