package note.lym.org.noteproject.presenter.note.maysister;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.app.Constants;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.MaySisterData;
import note.lym.org.noteproject.model.http.RetrofitHelper;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 不得姐逻辑处理类
 *
 * @author yaoming.li
 * @since 2017-05-16 14:01
 */
public class MaySisterPresenter extends RxPresenter<IMaySisterView> implements IBaseMaySisterPresenter {

    private RetrofitHelper mHelper;

    @Inject
    public MaySisterPresenter(RetrofitHelper helper) {
        this.mHelper = helper;
    }

    @Override
    public void getMaySisterData(final int page) {
        if (page == 1) {
            getView().showLoading();
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("showapi_appid", Constants.SHOW_API_ID);
        map.put("showapi_sign", Constants.SHOW_API_KEY);
        map.put("type", "29");

        Flowable<MaySisterData> able = mHelper.getMaySisterData(map);
        ResourceSubscriber<MaySisterData> resourceSubscriber = new ResourceSubscriber<MaySisterData>() {
            @Override
            public void onNext(MaySisterData maySisterData) {
                getView().getMaySisterData(maySisterData.getShowapi_res_body().getPagebean().getContentlist());
            }
            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getMaySisterData(page);
                    }
                });
            }

            @Override
            public void onComplete() {
                if (page == 1) {
                    getView().hideLoading();
                }
            }
        };
        addSubscription(mHelper.startObservable(able, resourceSubscriber));
    }
}
