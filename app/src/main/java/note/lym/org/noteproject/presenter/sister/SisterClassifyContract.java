package note.lym.org.noteproject.presenter.sister;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.SisterClassList;

/**
 * doc
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface SisterClassifyContract {
    interface View extends BaseView{
        void getPrettySisterList(List<SisterClassList.ShowapiResBodyBean.DataBean> list);
    }

    interface Presenter extends BasePresenter<View>{
        void getPrettySister(int page,int result,int type);
    }
}
