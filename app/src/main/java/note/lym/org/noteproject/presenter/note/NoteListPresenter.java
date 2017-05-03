package note.lym.org.noteproject.presenter.note;

import android.util.Log;

import org.litepal.crud.DataSupport;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.adapter.NoteListAdapter;
import note.lym.org.noteproject.app.Constants;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.Note;
import note.lym.org.noteproject.model.bean.xxxData;
import note.lym.org.noteproject.model.http.HttpResponse;
import note.lym.org.noteproject.model.http.RetrofitHelper;

/**
 * @author yaoming.li
 * @since 2017-05-03 11:31
 */
public class NoteListPresenter extends RxPresenter<INoteView> implements IBaseNotePresenter {

    private int mPage = 1;

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

    @Override
    public void getData(boolean isRefresh, int size) {
        if (isRefresh) {
            mPage = 1;
        } else {
            mPage++;
        }

        Flowable<HttpResponse<List<xxxData>>> flowable = mRetrofitHelper.fetchGetData(size,mPage);

        ResourceSubscriber<HttpResponse<List<xxxData>>> observer = new ResourceSubscriber<HttpResponse<List<xxxData>>>() {

            @Override
            public void onNext(HttpResponse<List<xxxData>> data) {
                if (null != getView() && null != data) {
                    if (!data.error ) {
                        Log.d(Constants.Async,data.results.toString());
//                        getView().showSuccess(data.results);
                    } else {
//                        getView().showFailure();
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
//                if (getView() != null) getView().showNetWorkError();
            }

            @Override
            public void onComplete() {

            }

        };

        addSubscription(mRetrofitHelper.startObservable(flowable, observer));



    }




}
