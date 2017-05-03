package note.lym.org.noteproject.presenter.note;

import java.util.List;

import note.lym.org.noteproject.base.BaseView;
import note.lym.org.noteproject.model.bean.Note;

/**
 * @author yaoming.li
 * @since 2017-05-03 11:28
 */
public interface INoteView extends BaseView {

    void showDateView();

    void noDataView();

    void initHeaderView();

    void removeHeaderView();

    void updateNoteList(List<Note> notes);



}
