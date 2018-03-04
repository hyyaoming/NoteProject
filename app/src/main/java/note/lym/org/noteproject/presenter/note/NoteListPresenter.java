package note.lym.org.noteproject.presenter.note;

import org.litepal.crud.DataSupport;

import java.util.List;

import javax.inject.Inject;

import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.Note;
import note.lym.org.noteproject.model.dao.NoteDao;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.ui.note.adapter.NoteListAdapter;

/**
 * @author yaoming.li
 * @since 2017-05-03 11:31
 */
public class NoteListPresenter extends RxPresenter<NoteListContract.View> implements NoteListContract.Presenter {


    private DataManager mRetrofitHelper;

    @Inject
    public NoteListPresenter(DataManager mRetrofitHelper) {
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
        return NoteDao.findAllNotes();
    }

}
