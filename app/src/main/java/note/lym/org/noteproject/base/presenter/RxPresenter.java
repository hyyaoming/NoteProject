package note.lym.org.noteproject.base.presenter;

import java.lang.ref.WeakReference;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;


public class RxPresenter<T extends BaseView>  implements BasePresenter<T>{


    protected WeakReference<T> mView;

    CompositeDisposable mDisposables;


    @Override
    public void attachView(T view) {
        mView = new WeakReference<T>(view);
    }

    @Override
    public void detachView() {
        mView.clear();
        mView = null;
        dispose();
    }

    protected T getView(){
        if(mView != null){
            return mView.get();
        }
        return null;
    }

    //取消所有的订阅
    public void dispose(){
        if(mDisposables!=null){
            mDisposables.clear();
        }
    }

    /**
     * 初始化通用的观察者
     *
     * @param observable 观察者
     */
    protected ResourceSubscriber startObservable(Flowable observable, ResourceSubscriber subscriber) {
        return (ResourceSubscriber) observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(subscriber);
    }


    protected void addSubscription(Disposable disposable) {
        if (disposable == null) return;
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
        mDisposables.add(disposable);
    }

}
