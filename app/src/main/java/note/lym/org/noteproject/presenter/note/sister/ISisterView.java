package note.lym.org.noteproject.presenter.note.sister;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.SisterList;

/**
 *
 * @author yaoming.li
 * @since 2017-05-12 17:08
 */
public interface ISisterView extends BaseView {
    void getSisterClassify(List<SisterList.ShowapiResBodyBean.DataBean> list);
}
