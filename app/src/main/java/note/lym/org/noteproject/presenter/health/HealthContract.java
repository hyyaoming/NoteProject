package note.lym.org.noteproject.presenter.health;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.Health;

/**
 * doc 健康咨询相关
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface HealthContract {
    interface View extends BaseView {
        void getHealthClassifyList(List<Health.ShowapiResBodyBean.ListBean> listBeanList);
    }

    interface Presenter extends BasePresenter<View> {
        void getHealthClassifyList();
    }
}
