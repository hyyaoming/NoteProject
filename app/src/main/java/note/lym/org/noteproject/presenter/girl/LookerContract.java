package note.lym.org.noteproject.presenter.girl;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.LookerGirl;

/**
 * doc
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface LookerContract {
    interface View extends BaseView {
        void getLookerGirl(List<LookerGirl.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list);

        void noDataView();

        void noMoreData();
    }

    interface Presenter extends BasePresenter<View> {
        void getLookerList(int page, String type);

    }
}
