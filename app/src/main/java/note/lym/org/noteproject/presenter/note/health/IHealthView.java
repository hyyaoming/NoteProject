package note.lym.org.noteproject.presenter.note.health;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.Health;

/**
 * 获取健康咨询分类基类
 *
 * @author yaoming.li
 * @since 2017-05-12 10:27
 */
public interface IHealthView extends BaseView {
    void getHealthClassifyList(List<Health.ShowapiResBodyBean.ListBean> listBeanList);
}
