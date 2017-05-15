package note.lym.org.noteproject.presenter.note.sister;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.SisterClassList;

/**
 * 漂亮姐姐
 *
 * @author yaoming.li
 * @since 2017-05-12 17:35
 */
public interface ISisterClassifyView extends BaseView{
    void getPrettySisterList(List<SisterClassList.ShowapiResBodyBean.DataBean> list);
}
