package note.lym.org.noteproject.presenter.healthdetail;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.app.constant.Constants;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.HealthDetail;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 健康资讯详情逻辑类
 *
 * @author yaoming.li
 * @since 2017-05-12 14:43
 */
public class HealthDetailPresenter extends RxPresenter<HealthDetailContract.View> implements HealthDetailContract.Presenter {

    private DataManager mHelper;

    @Inject
    public HealthDetailPresenter(DataManager helper) {
        this.mHelper = helper;
    }

    @Override
    public void getHealthDetail(final String healthId) {
        getView().showLoading();
        HashMap<String,String> map = new HashMap<>();
        map.put("showapi_appid", Constants.SHOW_API_ID);
        map.put("showapi_sign", Constants.SHOW_API_KEY);
        map.put("id",healthId);
       Flowable<HealthDetail> able=  mHelper.getHealthDetailData(map);
        ResourceSubscriber<HealthDetail> detail = new ResourceSubscriber<HealthDetail>() {
            @Override
            public void onNext(HealthDetail detail) {
                 getView().getHealthDetail(detail);
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getHealthDetail(healthId);
                    }
                });
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        };
        addSubscription(startObservable(able,detail));
    }
}
