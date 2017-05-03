package note.lym.org.noteproject.model.http;

import android.widget.Toast;

import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.app.Constants;
import note.lym.org.noteproject.app.NoteApplication;


public abstract class MyResourceSubscriber<T> extends ResourceSubscriber<T> implements Callback<T>{


    @Override
    public void onNext(T data) {
        onNextData(data);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();

        Toast.makeText(NoteApplication.getInstance().getApplicationContext(), Constants.NET_ERROR, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
    }

}
