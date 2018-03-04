package note.lym.org.noteproject.presenter.life;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.MoreType;
import note.lym.org.noteproject.model.http.parameters.ApiParameters;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 更多种类逻辑类
 *
 * @author yaoming.li
 * @since 2017-05-16 18:57
 */
public class PersonToLifePresenter extends RxPresenter<PersonToLifeContract.View> implements PersonToLifeContract.Presenter {

    private DataManager mHelper;

    @Inject
    public PersonToLifePresenter(DataManager helper) {
        this.mHelper = helper;
    }

    @Override
    public void getMoreTypeData() {
        getView().showLoading();
        HashMap<String, String> map = ApiParameters.getAppSignMap();
        Flowable<MoreType> able = mHelper.getPersonToLife(map);
        ResourceSubscriber<MoreType> resourceSubscriber = new ResourceSubscriber<MoreType>() {
            @Override
            public void onNext(MoreType moreType) {
                getView().getMoreTypeData(moreType.getShowapi_res_body().getList());
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getMoreTypeData();
                    }
                });
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        };
        addSubscription(startObservable(able,resourceSubscriber));
    }
}
