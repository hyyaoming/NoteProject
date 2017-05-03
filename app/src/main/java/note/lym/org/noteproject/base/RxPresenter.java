package note.lym.org.noteproject.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


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

    protected void addSubscription(Disposable disposable) {
        if (disposable == null) return;
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
        mDisposables.add(disposable);
    }

}
