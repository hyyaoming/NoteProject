package note.lym.org.noteproject.presenter.maysister;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.MaySisterData;
import note.lym.org.noteproject.model.http.parameters.ApiParameters;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 不得姐逻辑处理类
 *
 * @author yaoming.li
 * @since 2017-05-16 14:01
 */
public class MaySisterPresenter extends RxPresenter<MaySisterContract.View> implements MaySisterContract.Presenter {

    private DataManager mHelper;

    @Inject
    public MaySisterPresenter(DataManager helper) {
        this.mHelper = helper;
    }

    @Override
    public void getMaySisterData(final int page) {
        if (page == 1) {
            getView().showLoading();
        }
        HashMap<String, String> map = ApiParameters.getMaySisterMap();
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
        addSubscription(startObservable(able, resourceSubscriber));
    }
}
