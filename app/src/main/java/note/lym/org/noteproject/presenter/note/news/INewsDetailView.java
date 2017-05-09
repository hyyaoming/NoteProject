package note.lym.org.noteproject.presenter.note.news;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.NewsDetailBean;

/**
 *
 * @author yaoming.li
 * @since 2017-05-09 11:11
 */
public interface INewsDetailView extends BaseView {
    void getNewsDetail(NewsDetailBean newsDetail);
}
