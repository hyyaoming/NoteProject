package note.lym.org.noteproject.Dagger.Component;

import android.app.Activity;

import dagger.Component;
import note.lym.org.noteproject.Dagger.FragmentScope;
import note.lym.org.noteproject.Dagger.Modul.FragmentModule;
import note.lym.org.noteproject.fragment.BelleListFragment;
import note.lym.org.noteproject.fragment.NoteListFragment;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();


    void inject(NoteListFragment fragment);


    void inject(BelleListFragment fragment);



}