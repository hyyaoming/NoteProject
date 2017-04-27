package project.recyclerview.lym.org.recyclerviewlibrary.loadmore;


import project.recyclerview.lym.org.recyclerviewlibrary.R;

/**
 * Created by BlingBling on 2016/10/11.
 */

public final class SimpleLoadMoreView extends LoadMoreView {

    @Override public int getLayoutId() {
        return R.layout.loading_item;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
