package note.lym.org.noteproject.presenter.note;

import org.litepal.crud.DataSupport;

import java.util.List;

import javax.inject.Inject;

import note.lym.org.noteproject.adapter.NoteListAdapter;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.Note;
import note.lym.org.noteproject.model.http.RetrofitHelper;

/**
 * @author yaoming.li
 * @since 2017-05-03 11:31
 */
public class NoteListPresenter extends RxPresenter<INoteView> implements IBaseNotePresenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public NoteListPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }


    @Override
    public void getNoteList() {
        List<Note> list = getNoteListData();
        if (list.isEmpty()) {
            getView().noDataView();
        } else {
            getView().initHeaderView();
            getView().updateNoteList(list);
            getView().showDateView();
        }
    }

    @Override
    public void deleteNote(int position, Note note, NoteListAdapter adapter) {
        adapter.remove(position);
        DataSupport.deleteAll(Note.class, "date = ?", note.date);
        if (adapter.getData().isEmpty()) {
            getView().noDataView();
            getView().removeHeaderView();
        } else {
            getView().showDateView();
        }
    }

    @Override
    public List<Note> getNoteListData() {
        return DataSupport.findAll(Note.class);
    }

}
