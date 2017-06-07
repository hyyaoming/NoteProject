package note.lym.org.noteproject.presenter.note.health;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.app.Constants;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.Health;
import note.lym.org.noteproject.model.bean.HealthList;
import note.lym.org.noteproject.model.http.RetrofitHelper;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 咨询列表处理类
 *
 * @author yaoming.li
 * @since 2017-05-12 11:01
 */
public class HelthListPresenter extends RxPresenter<IHealthListView> implements IBaseHealthListPresenter {

    private RetrofitHelper mHelper;

    @Inject
    public HelthListPresenter(RetrofitHelper mHelper) {
        this.mHelper = mHelper;
    }

    @Override
    public void getHealthListData(final String healthId, final int page) {
        if (page == 1) {
            getView().showLoading();
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("showapi_appid", Constants.SHOW_API_ID);
        map.put("showapi_sign", Constants.SHOW_API_KEY);
        map.put("page", String.valueOf(page));
        map.put("tid", healthId);
        Flowable<HealthList> able = mHelper.getHealthList(map);
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
        addSubscription(mHelper.startObservable(able, resource));
    }
}
