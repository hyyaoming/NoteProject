package note.lym.org.noteproject.presenter.note.belle;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.Belle;

/**
 *
 * @author yaoming.li
 * @since 2017-05-03 21:03
 */
public interface IBelleView extends BaseView {

    void getBelleList(List<Belle.ResultsBean> list);

}
