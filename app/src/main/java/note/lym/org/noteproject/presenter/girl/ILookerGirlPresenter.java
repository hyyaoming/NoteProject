package note.lym.org.noteproject.presenter.girl;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.LookerGirl;
import note.lym.org.noteproject.model.http.parameters.ApiParameters;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 获取美女图片逻辑
 *
 * @author yaoming.li
 * @since 2017-05-16 23:43
 */
public class ILookerGirlPresenter extends RxPresenter<LookerContract.View> implements LookerContract.Presenter {

    private DataManager mHelper;

    @Inject
    public ILookerGirlPresenter(DataManager helper) {
        this.mHelper = helper;
    }

    @Override
    public void getLookerList(final int page, final String type) {
        if (page == 1) {
            getView().showLoading();
        }
        HashMap<String, String> map = ApiParameters.getLookGirlParametersMap(type, String.valueOf(page));
        Flowable<LookerGirl> able = mHelper.getLookerGirl(map);
        ResourceSubscriber<LookerGirl> girl = new ResourceSubscriber<LookerGirl>() {
            @Override
            public void onNext(LookerGirl lookerGirl) {
                if (lookerGirl != null && lookerGirl.getShowapi_res_body() != null && lookerGirl.getShowapi_res_body().getPagebean() != null) {
                    getView().getLookerGirl(lookerGirl.getShowapi_res_body().getPagebean().getContentlist());
                } else {
                    if (page == 1) {
                        getView().noDataView();
                    } else {
                        getView().noMoreData();
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getLookerList(page, type);
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
        addSubscription(startObservable(able, girl));
    }
}
