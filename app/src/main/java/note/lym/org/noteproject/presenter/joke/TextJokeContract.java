package note.lym.org.noteproject.presenter.joke;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.TextJoke;

/**
 * doc
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface TextJokeContract {
    interface View extends BaseView{
        void getTextJokeList(List<TextJoke.ShowapiResBodyBean.ContentlistBean> list);
    }

    interface Presenter extends BasePresenter<View>{
        void getTextJokeData(int maxResult,int page);
    }
}
