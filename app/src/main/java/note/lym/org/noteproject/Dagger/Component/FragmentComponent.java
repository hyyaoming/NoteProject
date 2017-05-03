package note.lym.org.noteproject.Dagger.Component;

import android.app.Activity;

import dagger.Component;
import note.lym.org.noteproject.Dagger.FragmentScope;
import note.lym.org.noteproject.Dagger.Modul.FragmentModule;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();


}