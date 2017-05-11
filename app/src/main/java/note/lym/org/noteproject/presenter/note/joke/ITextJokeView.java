package note.lym.org.noteproject.presenter.note.joke;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.TextJoke;

/**
 * @author yaoming.li
 * @since 2017-05-11 23:04
 */
public interface ITextJokeView extends BaseView{
    void getTextJokeList(List<TextJoke.ShowapiResBodyBean.ContentlistBean> list);
}
