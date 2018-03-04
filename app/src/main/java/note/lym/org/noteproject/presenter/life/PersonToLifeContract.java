package note.lym.org.noteproject.presenter.life;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.MoreType;

/**
 * doc  人生百态
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface PersonToLifeContract {
    interface View extends BaseView{
        void getMoreTypeData(List<MoreType.ShowapiResBodyBean.ListBeanX> listBeanXes);
    }

    interface Presenter extends BasePresenter<View>{
        void getMoreTypeData();
    }
}
