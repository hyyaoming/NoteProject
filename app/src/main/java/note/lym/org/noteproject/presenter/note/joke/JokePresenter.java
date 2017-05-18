package note.lym.org.noteproject.presenter.note.joke;

import android.util.Log;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.app.Constants;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.Joke;
import note.lym.org.noteproject.model.http.RetrofitHelper;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * 笑话大全逻辑类
 *
 * @author yaoming.li
 * @since 2017-05-11 16:05
 */
public class JokePresenter extends RxPresenter<IJokeView> implements IBaseJokePresenter {

    private RetrofitHelper mHelper;

    @Inject
    public JokePresenter(RetrofitHelper helper) {
        this.mHelper = helper;
    }

    @Override
    public void getJokeData(final int maxResult, final int page) {
        if (page == 1) {
            getView().showLoading();
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("maxResult", String.valueOf(maxResult));
        map.put("page", String.valueOf(page));
        map.put("showapi_appid", Constants.SHOW_API_ID);
        map.put("showapi_sign", Constants.SHOW_API_KEY);
        Flowable<Joke> able = mHelper.getJokeList(map);
        ResourceSubscriber<Joke> jokeResource = new ResourceSubscriber<Joke>() {
            @Override
            public void onNext(Joke joke) {
                Log.i("Tag",joke.getShowapi_res_body().getContentlist().size()+"");
                getView().getJokeList(joke.getShowapi_res_body().getContentlist());
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getJokeData(maxResult,page);
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
        addSubscription(mHelper.startObservable(able, jokeResource));
    }
}
