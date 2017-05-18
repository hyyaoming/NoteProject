package note.lym.org.noteproject.presenter.note.belle;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.Belle;
import note.lym.org.noteproject.model.http.RetrofitHelper;
import note.lym.org.noteproject.utils.Static;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * @author yaoming.li
 * @since 2017-05-03 21:07
 */
public class BellePresenter extends RxPresenter<IBelleView> implements IBaseBellePresenter {

    private RetrofitHelper mHelper;

    @Inject
    public BellePresenter(RetrofitHelper helper) {
        this.mHelper = helper;
    }


    @Override
    public void getBelleData(final int page, final int count) {
        if (page == 1) {
            getView().showLoading();
        }
        Flowable<Belle> flow = mHelper.getBelleData(page, count);
        ResourceSubscriber<Belle> observer = new ResourceSubscriber<Belle>() {
            @Override
            public void onNext(Belle list) {
                if (null != list && !list.getResults().isEmpty()) {
                    getView().getBelleList(list.getResults());
                }
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getBelleData(page, count);
                    }
                });
            }

            @Override
            public void onComplete() {
                if (page == 1) {
                    getView().hideLoading();
                }
            }
        };
        addSubscription(mHelper.startObservable(flow, observer));
    }
}
