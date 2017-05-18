package note.lym.org.noteproject.presenter.note.joke;

import android.util.Log;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.app.Constants;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.TextJoke;
import note.lym.org.noteproject.model.http.RetrofitHelper;
import note.lym.org.noteproject.view.LoadStateView;

/**
 * @author yaoming.li
 * @since 2017-05-11 23:07
 */
public class TextJokePersenter extends RxPresenter<ITextJokeView> implements ITextJokeBasePresenter {

    private RetrofitHelper mHelper;

    @Inject
    public TextJokePersenter(RetrofitHelper mHelper) {
        this.mHelper = mHelper;
    }

    @Override
    public void getTextJokeData(final int maxResult, final int page) {
        if (page == 1) {
            getView().showLoading();
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("maxResult", String.valueOf(maxResult));
        map.put("page", String.valueOf(page));
        map.put("showapi_appid", Constants.SHOW_API_ID);
        map.put("showapi_sign", Constants.SHOW_API_KEY);
        Flowable<TextJoke> able = mHelper.getTextJokeList(map);
        ResourceSubscriber<TextJoke> jokeResource = new ResourceSubscriber<TextJoke>() {
            @Override
            public void onNext(TextJoke joke) {
                Log.i("Tag",joke.getShowapi_res_body().getContentlist().size()+"");
                getView().getTextJokeList(joke.getShowapi_res_body().getContentlist());
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(new LoadStateView.OnRequestListener() {
                    @Override
                    public void onRequest() {
                        getTextJokeData(maxResult,page);
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
