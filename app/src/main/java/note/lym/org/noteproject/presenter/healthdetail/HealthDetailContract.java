package note.lym.org.noteproject.presenter.healthdetail;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.HealthDetail;

/**
 * doc  健康咨询详情
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface HealthDetailContract {
    interface View extends BaseView {
        void getHealthDetail(HealthDetail detail);
    }

    interface Presenter extends BasePresenter<View> {
        void getHealthDetail(String healthId);
    }
}
