package note.lym.org.noteproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * webView操作类
 *
 * @author yaoming.li
 * @since 2017-07-07 16:50
 */
public class HtmlWebView extends FrameLayout {

    private WebView mWebView;

    public HtmlWebView(Context context) {
        this(context, null);
    }

    public HtmlWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HtmlWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mWebView = new WebView(context);
        addView(mWebView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * 获取当前的WebView
     *
     * @return WebView
     */
    public WebView getWebView() {
        if (mWebView != null) {
            return mWebView;
        }
        return null;
    }

    /**
     * 释放资源
     */
    public void release() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            removeView(mWebView);
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }

}
