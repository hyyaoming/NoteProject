package note.lym.org.noteproject.presenter.note.news;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.NewsDetailBean;
import note.lym.org.noteproject.model.http.RetrofitHelper;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * @author yaoming.li
 * @since 2017-05-09 11:13
 */
public class NewsDetailPresenter extends RxPresenter<INewsDetailView> implements IBaseNewsDetailPresenter {

    private RetrofitHelper mHelper;

    @Inject
    public NewsDetailPresenter(RetrofitHelper helper) {
        this.mHelper = helper;
    }


    @Override
    public void getNewsDetailId(final String id) {
        Flowable<Map<String, NewsDetailBean>> able = mHelper.getNewsDetail(id);
        ResourceSubscriber<Map<String, NewsDetailBean>> subscriber = new ResourceSubscriber<Map<String, NewsDetailBean>>() {
            @Override
            public void onNext(Map<String, NewsDetailBean> stringNewsDetailBeanMap) {
                    getView().getNewsDetail(stringNewsDetailBeanMap.get(id));
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getNewsDetailId(id);
                    }
                });
            }

            @Override
            public void onComplete() {
                    getView().hideLoading();
            }
        };
        addSubscription(mHelper.startObservable(able,subscriber));
    }
}
