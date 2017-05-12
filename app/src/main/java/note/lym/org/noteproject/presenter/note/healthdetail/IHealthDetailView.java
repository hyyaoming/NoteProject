package note.lym.org.noteproject.presenter.note.healthdetail;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.HealthDetail;

/**
 *  健康资讯基类
 * @author yaoming.li
 * @since 2017-05-12 14:37
 */
public interface IHealthDetailView extends BaseView {
    void getHealthDetail(HealthDetail detail);
}
