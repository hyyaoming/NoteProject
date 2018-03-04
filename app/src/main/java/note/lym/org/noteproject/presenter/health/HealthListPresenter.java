package note.lym.org.noteproject.presenter.health;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.HealthList;
import note.lym.org.noteproject.model.http.parameters.ApiParameters;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 咨询列表处理类
 *
 * @author yaoming.li
 * @since 2017-05-12 11:01
 */
public class HealthListPresenter extends RxPresenter<HealthListContract.View> implements HealthListContract.Presenter {

    private DataManager mHelper;

    @Inject
    public HealthListPresenter(DataManager mHelper) {
        this.mHelper = mHelper;
    }

    @Override
    public void getHealthListData(final String healthId, final int page) {
        if (page == 1) {
            getView().showLoading();
        }
        HashMap<String, String> map = ApiParameters.getHealthListMap(page, healthId);
        Flowable<HealthList> able = mHelper.getHealthListData(map);
        ResourceSubscriber<HealthList> resource = new ResourceSubscriber<HealthList>() {
            @Override
            public void onNext(HealthList healthList) {
                if (healthList != null && healthList.getShowapi_res_body() != null && healthList.getShowapi_res_body().getPagebean().getContentlist() != null) {
                    getView().getHealthListData(healthList.getShowapi_res_body().getPagebean().getContentlist());
                }
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getHealthListData(healthId, page);
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
