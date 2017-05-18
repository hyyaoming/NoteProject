package note.lym.org.noteproject.presenter.note.news;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.NewsList;
import note.lym.org.noteproject.model.http.RetrofitHelper;
import note.lym.org.noteproject.utils.Static;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * @author yaoming.li
 * @since 2017-05-08 18:24
 */
public class NewsListPresenter extends RxPresenter<INewsView> implements IBaseNewsPresenter {

    private RetrofitHelper mHelper;

    @Inject
    public NewsListPresenter(RetrofitHelper helper) {
        this.mHelper = helper;
    }

    @Override
    public void getNewsData(final int page) {
        if (page == 0) {
            getView().showLoading();
        }
        Flowable<NewsList> able = mHelper.getNewsList(page);
        ResourceSubscriber<NewsList> subscriber = new ResourceSubscriber<NewsList>() {
            @Override
            public void onNext(NewsList newsList) {
                if (null != newsList && newsList.getList() != null) {
                    getView().getNewsList(newsList.getList());
                }
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getNewsData(page);
                    }
                });
            }

            @Override
            public void onComplete() {
                if (page == 0) {
                    getView().hideLoading();
                }
            }
        };
        addSubscription(mHelper.startObservable(able, subscriber));
    }
}
