package note.lym.org.noteproject.base;

public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();

}