package note.lym.org.noteproject.presenter.note;

import java.util.List;

import note.lym.org.noteproject.adapter.NoteListAdapter;
import note.lym.org.noteproject.model.bean.Note;

/**
 * @author yaoming.li
 * @since 2017-05-03 11:34
 */
public interface IBaseNotePresenter {
    void getNoteList();

    void deleteNote(int position, Note note, NoteListAdapter adapter);

    List<Note> getNoteListData();

    void getData(boolean isRefresh,int size);

}
