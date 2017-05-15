package note.lym.org.noteproject.presenter.note.sister;

import org.reactivestreams.Publisher;

import java.util.HashMap;

import javax.inject.Inject;
import javax.xml.transform.Transformer;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.subscribers.ResourceSubscriber;
import note.lym.org.noteproject.app.Constants;
import note.lym.org.noteproject.base.RxPresenter;
import note.lym.org.noteproject.model.bean.SisterClassList;
import note.lym.org.noteproject.model.http.RetrofitHelper;

/**
 * 漂亮姐姐逻辑类
 *
 * @author yaoming.li
 * @since 2017-05-12 17:37
 */
public class SisterClassifyPresenter extends RxPresenter<ISisterClassifyView> implements ISisterClassifyBasePresenter{

    private RetrofitHelper mHelper;

    @Inject
    public SisterClassifyPresenter(RetrofitHelper mHelper) {
        this.mHelper = mHelper;
    }

    @Override
    public void getPrettySister(final int page, int result, int type) {
        if(page ==1){
            getView().showLoading();
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("showapi_appid", Constants.SHOW_API_ID);
        map.put("showapi_sign", Constants.SHOW_API_KEY);
        map.put("type",String.valueOf(type));
        map.put("page",String.valueOf(page));
        map.put("rows",String.valueOf(result));
        Flowable<SisterClassList> able = mHelper.getSisterClassifyDataList(map);
        ResourceSubscriber<SisterClassList> resource = new ResourceSubscriber<SisterClassList>() {
            @Override
            public void onNext(SisterClassList health) {
                getView().getPrettySisterList(health.getShowapi_res_body().getData());
                if(page ==1){
                    getView().hideLoading();
                }
            }

            @Override
            public void onError(Throwable t) {
                getView().showError(t.getMessage());
                if(page ==1){
                    getView().hideLoading();
                }
            }

            @Override
            public void onComplete() {

            }
        };
        addSubscription(mHelper.startObservable(able,resource));
    }
}
