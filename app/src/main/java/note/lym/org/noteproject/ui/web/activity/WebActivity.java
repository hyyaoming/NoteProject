package note.lym.org.noteproject.ui.web.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.activity.SimpleActivity;
import note.lym.org.noteproject.view.HtmlWebView;

/**
 * 用来展示网页
 *
 * @author yaoming.li
 * @since 2017-07-07 16:46
 */
public class WebActivity extends SimpleActivity {

    public static final String URL = "https://github.com/hyyaoming/NoteProject";
    public static final String LINK_URL = "link_url";
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.html_parent)
    HtmlWebView mFrame;
    private WebView mWebView;
    @BindView(R.id.web_progressBar)
    ProgressBar mBar;
    private String url;
    /**
     * 标题名字
     */
    public static final String TITLE_NAME = "title_name";
    private String mTitleName;

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initEventAndData() {
        getIntentData();
        initToolBar(mToolBar,true,mTitleName);
        bindView();
    }

    private void getIntentData() {
        mTitleName = getIntent().getStringExtra(TITLE_NAME);
        url = getIntent().getStringExtra(LINK_URL);
    }

    private void bindView() {
        mWebView = mFrame.getWebView();
        setWebView();
    }

    private void setWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.loadUrl(url);
        mWebView.setWebChromeClient(new MyWebChrome());
        mWebView.setWebViewClient(new MyWebClient());
    }

    private class MyWebChrome extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mBar.setProgress(newProgress);
            if (newProgress == 100) {
                mBar.setVisibility(View.GONE);
            }
        }
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            mBar.setVisibility(View.GONE);
        }
    }

    public static void launch(Context context,String url,String titleName){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra(LINK_URL,url);
        intent.putExtra(TITLE_NAME,titleName);
        context.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        mFrame.release();
        super.onDestroy();
    }

}
