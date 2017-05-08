package note.lym.org.noteproject.presenter.note.news;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.NewsList;

/**
 *
 * @author yaoming.li
 * @since 2017-05-08 18:20
 */
public interface INewsView extends BaseView {
    void getNewsList(List<NewsList.NewsBean> list);
}
