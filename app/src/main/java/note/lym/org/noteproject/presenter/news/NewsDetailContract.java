package note.lym.org.noteproject.presenter.news;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.NewsDetailBean;

/**
 * doc
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface NewsDetailContract {
    interface View extends BaseView{
        void getNewsDetail(NewsDetailBean newsDetail);
    }

    interface Presenter extends BasePresenter<View>{
        void getNewsDetailId(String id);
    }
}
