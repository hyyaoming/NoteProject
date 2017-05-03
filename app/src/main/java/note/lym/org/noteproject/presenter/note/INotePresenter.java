package note.lym.org.noteproject.presenter.note;

import java.util.List;

import note.lym.org.noteproject.model.Note;

/**
 * @author yaoming.li
 * @since 2017-05-03 11:28
 */
public interface INotePresenter extends INoteBaseView {
    void updateNoteList(List<Note> notes);
}
