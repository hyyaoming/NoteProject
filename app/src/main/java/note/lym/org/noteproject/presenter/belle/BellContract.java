package note.lym.org.noteproject.presenter.belle;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.Belle;

/**
 * doc
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface BellContract {
    interface View extends BaseView {
        void getBelleList(List<Belle.ResultsBean> list);
    }

    interface Presenter extends BasePresenter<View>{
        void getBelleData(int page, int count);
    }
}
