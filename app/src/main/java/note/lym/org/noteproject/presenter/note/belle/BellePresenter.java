package note.lym.org.noteproject.presenter.note.belle;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.Belle;
import note.lym.org.noteproject.model.http.RetrofitHelper;
import note.lym.org.noteproject.utils.Static;

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
    public void getBelleData(final int page,int count) {
        if(page==1){
            getView().showLoading();
        }
        Flowable<Belle> flow = mHelper.getBelleData(page,count);
        ResourceSubscriber<Belle> observer = new ResourceSubscriber<Belle>() {
            @Override
            public void onNext(Belle list) {
                if(page==1){
                    getView().hideLoading();
                }
                if (null != list && !list.getResults().isEmpty()) {
                    getView().getBelleList(list.getResults());
                }else{
                    getView().showError(Static.CONTEXT.getString(R.string.no_date));
                }
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(Static.CONTEXT.getString(R.string.no_network));
                if(page==1){
                    getView().hideLoading();
                }
            }

            @Override
            public void onComplete() {

            }
        };
        addSubscription(mHelper.startObservable(flow, observer));
    }
}
