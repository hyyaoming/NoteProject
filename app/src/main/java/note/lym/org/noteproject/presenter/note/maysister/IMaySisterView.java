package note.lym.org.noteproject.presenter.note.maysister;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.MaySisterData;

/**
 * 不得姐基类
 *
 * @author yaoming.li
 * @since 2017-05-16 13:49
 */
public interface IMaySisterView extends BaseView {
    void getMaySisterData(List<MaySisterData.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list);
}
