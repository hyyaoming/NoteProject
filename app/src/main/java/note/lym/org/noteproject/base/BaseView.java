package note.lym.org.noteproject.base;

import note.lym.org.noteproject.view.LoadStateView;

/**
 * View  基类
 */
public interface BaseView {

    void showError(LoadStateView.OnRequestListener listener);

    void showLoading();

    void hideLoading();
}