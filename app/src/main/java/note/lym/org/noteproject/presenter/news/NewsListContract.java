package note.lym.org.noteproject.presenter.news;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.NewsList;

/**
 * doc  网易新闻
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface NewsListContract {
    interface View extends BaseView{
        void getNewsList(List<NewsList.NewsBean> list);
    }

    interface Presenter extends BasePresenter<View>{
        void getNewsData(int page);
    }
}
