package note.lym.org.noteproject.presenter.note.looker.girl;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.LookerGirl;

/**
 * 美女基类
 *
 * @author yaoming.li
 * @since 2017-05-16 23:40
 */
public interface IlookerGirlView extends BaseView {
    void getLookerGirl(List<LookerGirl.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list);

    void noDataView();
}
