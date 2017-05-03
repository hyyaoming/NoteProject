package note.lym.org.noteproject.presenter.note;

import org.litepal.crud.DataSupport;

import java.util.List;

import note.lym.org.noteproject.adapter.NoteListAdapter;
import note.lym.org.noteproject.model.Note;

/**
 * @author yaoming.li
 * @since 2017-05-03 11:31
 */
public class NoteListPresenter implements IBaseNotePresenter {

    private INotePresenter mNotePresenter;

    public NoteListPresenter(INotePresenter presenter) {
        mNotePresenter = presenter;
    }

    @Override
    public void getNoteList() {
        List<Note> list = getNoteListData();
        if (list.isEmpty()) {
            mNotePresenter.noDataView();
        } else {
            mNotePresenter.initHeaderView();
            mNotePresenter.updateNoteList(DataSupport.findAll(Note.class));
            mNotePresenter.showDateView();
        }
    }

    @Override
    public void deleteNote(int position, Note note, NoteListAdapter adapter) {
        adapter.remove(position);
        DataSupport.deleteAll(Note.class, "date = ?", note.date);
        if (adapter.getData().isEmpty()) {
            mNotePresenter.noDataView();
            mNotePresenter.removeHeaderView();
        } else {
            mNotePresenter.showDateView();
        }
    }

    @Override
    public List<Note> getNoteListData() {
        return DataSupport.findAll(Note.class);
    }

}
