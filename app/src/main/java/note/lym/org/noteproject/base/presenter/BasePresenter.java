package note.lym.org.noteproject.base.presenter;

public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();

}