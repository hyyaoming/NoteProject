package note.lym.org.noteproject.presenter.sister;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.SisterClassList;
import note.lym.org.noteproject.model.http.parameters.ApiParameters;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 漂亮姐姐逻辑类
 *
 * @author yaoming.li
 * @since 2017-05-12 17:37
 */
public class SisterClassifyPresenter extends RxPresenter<SisterClassifyContract.View> implements SisterClassifyContract.Presenter {

    private DataManager mHelper;

    @Inject
    public SisterClassifyPresenter(DataManager mHelper) {
        this.mHelper = mHelper;
    }

    @Override
    public void getPrettySister(final int page, final int result, final int type) {
        if (page == 1) {
            getView().showLoading();
        }
        HashMap<String, String> map = ApiParameters.getSisterClassifyMap(type, page, result);
        Flowable<SisterClassList> able = mHelper.getSisterClassifyList(map);
        ResourceSubscriber<SisterClassList> resource = new ResourceSubscriber<SisterClassList>() {
            @Override
            public void onNext(SisterClassList health) {
                getView().getPrettySisterList(health.getShowapi_res_body().getData());
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getPrettySister(page, result, type);
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
        addSubscription(startObservable(able, resource));
    }
}
