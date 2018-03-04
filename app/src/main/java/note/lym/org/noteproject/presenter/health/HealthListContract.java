package note.lym.org.noteproject.presenter.health;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.HealthList;

/**
 * doc  健康咨询列表相关
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface HealthListContract {
    interface View extends BaseView {
        void getHealthListData(List<HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list);
    }

    interface Presenter extends BasePresenter<View> {
        void getHealthListData(String healthId, int page);
    }
}
