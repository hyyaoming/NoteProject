package note.lym.org.noteproject.presenter.note.sister;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.app.Constants;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.Health;
import note.lym.org.noteproject.model.bean.SisterList;
import note.lym.org.noteproject.model.http.RetrofitHelper;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 漂亮姐姐分类逻辑
 *
 * @author yaoming.li
 * @since 2017-05-12 17:10
 */
public class SisterListPresenter extends RxPresenter<ISisterView> implements ISisterBasePresenter {

    private RetrofitHelper mHelper;

    @Inject
    public SisterListPresenter(RetrofitHelper mHelper) {
        this.mHelper = mHelper;
    }

    @Override
    public void getSisterData() {
        HashMap<String,String> map = new HashMap<>();
        map.put("showapi_appid", Constants.SHOW_API_ID);
        map.put("showapi_sign", Constants.SHOW_API_KEY);
        Flowable<SisterList> able = mHelper.getSisterClassifyList(map);
        ResourceSubscriber<SisterList> resource = new ResourceSubscriber<SisterList>() {
            @Override
            public void onNext(SisterList health) {
                getView().getSisterClassify(health.getShowapi_res_body().getData());
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
        addSubscription(mHelper.startObservable(able,resource));
    }
}
