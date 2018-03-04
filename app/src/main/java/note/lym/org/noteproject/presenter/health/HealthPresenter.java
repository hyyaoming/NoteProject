package note.lym.org.noteproject.presenter.health;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.Health;
import note.lym.org.noteproject.model.http.parameters.ApiParameters;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 获取健康咨询逻辑类
 *
 * @author yaoming.li
 * @since 2017-05-12 10:31
 */
public class HealthPresenter extends RxPresenter<HealthContract.View> implements HealthContract.Presenter {

    private DataManager mHelper;

    @Inject
    public HealthPresenter(DataManager mHelper) {
        this.mHelper = mHelper;
    }

    @Override
    public void getHealthClassifyList() {
        HashMap<String, String> map = ApiParameters.getAppSignMap();
        Flowable<Health> able = mHelper.getHealthClassifyList(map);
        ResourceSubscriber<Health> resource = new ResourceSubscriber<Health>() {
            @Override
            public void onNext(Health health) {
                getView().getHealthClassifyList(health.getShowapi_res_body().getList());
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getHealthClassifyList();
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
