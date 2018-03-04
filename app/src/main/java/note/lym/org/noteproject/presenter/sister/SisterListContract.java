package note.lym.org.noteproject.presenter.sister;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.SisterList;

/**
 * doc  漂亮姐姐列表
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface SisterListContract {
    interface View extends BaseView{
        void getSisterClassify(List<SisterList.ShowapiResBodyBean.DataBean> list);
    }

    interface Presenter extends BasePresenter<View>{
        void getSisterData();
    }
}
