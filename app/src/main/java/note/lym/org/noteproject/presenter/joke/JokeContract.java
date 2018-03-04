package note.lym.org.noteproject.presenter.joke;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.Joke;

/**
 * doc
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface JokeContract {
    interface View extends BaseView{
        void getJokeList(List<Joke.ShowapiResBodyBean.ContentlistBean> list);
    }

    interface Presenter extends BasePresenter<View>{
        void getJokeData(int maxResult, int page);
    }
}
