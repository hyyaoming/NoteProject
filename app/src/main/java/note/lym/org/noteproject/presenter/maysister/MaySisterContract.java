package note.lym.org.noteproject.presenter.maysister;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.MaySisterData;

/**
 * doc  不得姐
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface MaySisterContract  {
    interface View extends BaseView{
        void getMaySisterData(List<MaySisterData.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list);
    }

    interface Presenter extends BasePresenter<View>{
        void getMaySisterData(int page);
    }
}
