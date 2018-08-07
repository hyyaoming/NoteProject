package note.lym.org.noteproject.presenter.sister;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.SisterList;
import note.lym.org.noteproject.model.http.parameters.ApiParameters;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 漂亮姐姐分类逻辑
 *
 * @author yaoming.li
 * @since 2017-05-12 17:10
 */
public class SisterListPresenter extends RxPresenter<SisterListContract.View> implements SisterListContract.Presenter {

    private DataManager mHelper;

    @Inject
    public SisterListPresenter(DataManager mHelper) {
        this.mHelper = mHelper;
    }

    @Override
    public void getSisterData() {
        HashMap<String, String> map = ApiParameters.getAppSignMap();
        Flowable<SisterList> able = mHelper.getSisterListData(map);
        ResourceSubscriber<SisterList> resource = new ResourceSubscriber<SisterList>() {
            @Override
            public void onNext(SisterList health) {
                if (null != health && health.getShowapi_res_body() != null && health.getShowapi_res_body().getData() != null) {
                    getView().getSisterClassify(health.getShowapi_res_body().getData());
                } else {
                    getView().showNoData();
                }
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getSisterData();
                    }
                });
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        };
        addSubscription(startObservable(able, resource));
    }
}
