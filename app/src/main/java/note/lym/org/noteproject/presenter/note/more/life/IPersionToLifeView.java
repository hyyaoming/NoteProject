package note.lym.org.noteproject.presenter.note.more.life;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.MoreType;

/**
 * 更多种类图片基类
 *
 * @author yaoming.li
 * @since 2017-05-16 18:55
 */
public interface IPersionToLifeView extends BaseView {
    void getMoreTypeData(List<MoreType.ShowapiResBodyBean.ListBeanX> listBeanXes);
}
