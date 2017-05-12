package note.lym.org.noteproject.presenter.note.health;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.app.Constants;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.Health;
import note.lym.org.noteproject.model.http.RetrofitHelper;

/**
 * 获取健康咨询逻辑类
 *
 * @author yaoming.li
 * @since 2017-05-12 10:31
 */
public class HealthPresenter extends RxPresenter<IHealthView> implements IBaseHealthPresenter {

    private RetrofitHelper mHelper;

    @Inject
    public HealthPresenter(RetrofitHelper mHelper) {
        this.mHelper = mHelper;
    }

    @Override
    public void getHealthClassifyList() {
        HashMap<String,String> map = new HashMap<>();
        map.put("showapi_appid", Constants.SHOW_API_ID);
        map.put("showapi_sign", Constants.SHOW_API_KEY);
        Flowable<Health> able = mHelper.getHealthClassifyList(map);
        ResourceSubscriber<Health> resource = new ResourceSubscriber<Health>() {
            @Override
            public void onNext(Health health) {
                getView().getHealthClassifyList(health.getShowapi_res_body().getList());
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(t.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        addSubscription(mHelper.startObservable(able,resource));
    }
}
