package note.lym.org.noteproject.model.http;


public interface Callback<T> {
    void onNextData(T data);
}
