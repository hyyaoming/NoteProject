package note.lym.org.noteproject.presenter.note.more.life;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.Constants;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.MoreType;
import note.lym.org.noteproject.model.http.RetrofitHelper;
import note.lym.org.noteproject.utils.Static;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 更多种类逻辑类
 *
 * @author yaoming.li
 * @since 2017-05-16 18:57
 */
public class PersonToLifePresenter extends RxPresenter<IPersionToLifeView> implements IBasePersonToLifePresenter {

    private RetrofitHelper mHelper;

    @Inject
    public PersonToLifePresenter(RetrofitHelper helper) {
        this.mHelper = helper;
    }

    @Override
    public void getMoreTypeData() {
        getView().showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("showapi_appid", Constants.SHOW_API_ID);
        map.put("showapi_sign", Constants.SHOW_API_KEY);
        Flowable<MoreType> able = mHelper.getMoreTypeData(map);
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
        addSubscription(mHelper.startObservable(able,resourceSubscriber));
    }
}
