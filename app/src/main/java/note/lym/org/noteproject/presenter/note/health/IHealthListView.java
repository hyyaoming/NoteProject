package note.lym.org.noteproject.presenter.note.health;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.HealthList;

/**
 * 咨询列表基类
 *
 * @author yaoming.li
 * @since 2017-05-12 10:58
 */
public interface IHealthListView extends BaseView {
    void getHealthListData(List<HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list);
}
