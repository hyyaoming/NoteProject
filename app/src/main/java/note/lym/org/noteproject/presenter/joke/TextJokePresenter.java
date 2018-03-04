package note.lym.org.noteproject.presenter.joke;

import android.util.Log;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.TextJoke;
import note.lym.org.noteproject.model.http.parameters.ApiParameters;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * @author yaoming.li
 * @since 2017-05-11 23:07
 */
public class TextJokePresenter extends RxPresenter<TextJokeContract.View> implements TextJokeContract.Presenter {

    private DataManager mHelper;

    @Inject
    public TextJokePresenter(DataManager mHelper) {
        this.mHelper = mHelper;
    }

    @Override
    public void getTextJokeData(final int maxResult, final int page) {
        if (page == 1) {
            getView().showLoading();
        }
        HashMap<String, String> map = ApiParameters.getJokeOrGifListMap(page, maxResult);
        Flowable<TextJoke> able = mHelper.getTextJoke(map);
        ResourceSubscriber<TextJoke> jokeResource = new ResourceSubscriber<TextJoke>() {
            @Override
            public void onNext(TextJoke joke) {
                Log.i("Tag", joke.getShowapi_res_body().getContentlist().size() + "");
                getView().getTextJokeList(joke.getShowapi_res_body().getContentlist());
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getTextJokeData(maxResult, page);
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
        addSubscription(startObservable(able, jokeResource));
    }
}
