package note.lym.org.noteproject.presenter.news;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.NewsList;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * @author yaoming.li
 * @since 2017-05-08 18:24
 */
public class NewsListPresenter extends RxPresenter<NewsListContract.View> implements NewsListContract.Presenter {

    private DataManager mHelper;

    @Inject
    public NewsListPresenter(DataManager helper) {
        this.mHelper = helper;
    }

    @Override
    public void getNewsData(final int page) {
        if (page == 0) {
            getView().showLoading();
        }
        Flowable<NewsList> able = mHelper.getNews(page);
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
        addSubscription(startObservable(able, subscriber));
    }
}
