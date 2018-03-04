package note.lym.org.noteproject.presenter.note;

import java.util.List;

import note.lym.org.noteproject.base.presenter.BasePresenter;
import note.lym.org.noteproject.base.presenter.BaseView;
import note.lym.org.noteproject.model.bean.Note;
import note.lym.org.noteproject.ui.note.adapter.NoteListAdapter;

/**
 * doc
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface NoteListContract {
    interface View extends BaseView{
        void showDateView();

        void noDataView();

        void initHeaderView();

        void removeHeaderView();

        void updateNoteList(List<Note> notes);
    }

    interface Presenter extends BasePresenter<View>{
        void getNoteList();

        void deleteNote(int position, Note note, NoteListAdapter adapter);

        List<Note> getNoteListData();

    }
}
