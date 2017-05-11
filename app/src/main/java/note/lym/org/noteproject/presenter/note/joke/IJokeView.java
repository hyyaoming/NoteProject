package note.lym.org.noteproject.presenter.note.joke;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.Joke;

/**
 *  获取笑话数据
 * @author yaoming.li
 * @since 2017-05-11 16:03
 */
public interface IJokeView extends BaseView {

    void getJokeList(List<Joke.ShowapiResBodyBean.ContentlistBean> list);

}
