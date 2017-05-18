package note.lym.org.noteproject.presenter.note.looker.girl;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.Constants;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.LookerGirl;
import note.lym.org.noteproject.model.http.RetrofitHelper;
import note.lym.org.noteproject.utils.Static;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 获取美女图片逻辑
 *
 * @author yaoming.li
 * @since 2017-05-16 23:43
 */
public class ILookerGirlPresenter extends RxPresenter<IlookerGirlView> implements IBaseLookerGirlPresenter {

    private RetrofitHelper mHelper;

    @Inject
    public ILookerGirlPresenter(RetrofitHelper helper) {
        this.mHelper = helper;
    }

    @Override
    public void getLookerDatas(final int page, final String type) {
        if (page == 1) {
            getView().showLoading();
        }
        final HashMap<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(type));
        map.put("page", String.valueOf(page));
        map.put("showapi_appid", Constants.SHOW_API_ID);
        map.put("showapi_sign", Constants.SHOW_API_KEY);
        Flowable<LookerGirl> able = mHelper.getLookerGirlData(map);
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
                        getLookerDatas(page,type);
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
        addSubscription(mHelper.startObservable(able, girl));
    }
}
