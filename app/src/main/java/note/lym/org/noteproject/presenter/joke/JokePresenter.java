package note.lym.org.noteproject.presenter.joke;

import android.util.Log;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.base.presenter.RxPresenter;
import note.lym.org.noteproject.model.bean.Joke;
import note.lym.org.noteproject.model.http.parameters.ApiParameters;
import note.lym.org.noteproject.model.http.data.DataManager;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 笑话大全逻辑类
 *
 * @author yaoming.li
 * @since 2017-05-11 16:05
 */
public class JokePresenter extends RxPresenter<JokeContract.View> implements JokeContract.Presenter {

    private DataManager mHelper;

    @Inject
    public JokePresenter(DataManager helper) {
        this.mHelper = helper;
    }

    @Override
    public void getJokeData(final int maxResult, final int page) {
        if (page == 1) {
            getView().showLoading();
        }
        HashMap<String, String> map = ApiParameters.getJokeOrGifListMap(page, maxResult);
        Flowable<Joke> able = mHelper.getJokes(map);
        ResourceSubscriber<Joke> jokeResource = new ResourceSubscriber<Joke>() {
            @Override
            public void onNext(Joke joke) {
                Log.i("Tag", joke.getShowapi_res_body().getContentlist().size() + "");
                getView().getJokeList(joke.getShowapi_res_body().getContentlist());
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getJokeData(maxResult, page);
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
