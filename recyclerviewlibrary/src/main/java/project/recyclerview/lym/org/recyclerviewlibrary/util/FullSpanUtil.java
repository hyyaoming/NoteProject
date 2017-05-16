package project.recyclerview.lym.org.recyclerviewlibrary.util;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

/**
 * Desp.
 *
 * @author yaoming.li
 * @version 8.1.0
 * @since 2017-03-16 12:51
 */
public class FullSpanUtil {
    public static void onAttachedToRecyclerView(RecyclerView recyclerView, final RecyclerView.Adapter adapter, final int pinnedHeaderType) {
        // 如果是网格布局，这里处理标签的布局占满一行
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup oldSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (adapter.getItemViewType(position) == pinnedHeaderType) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (oldSizeLookup != null) {
                        return oldSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });
        }
    }

    public static void onViewAttachedToWindow(RecyclerView.ViewHolder holder, RecyclerView.Adapter adapter, int pinnedHeaderType) {
        // 如果是瀑布流布局，这里处理标签的布局占满一行
        final ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            final StaggeredGridLayoutManager.LayoutParams slp = (StaggeredGridLayoutManager.LayoutParams) lp;
            slp.setFullSpan(adapter.getItemViewType(holder.getLayoutPosition()) == pinnedHeaderType);
        }
    }

    /**
     * RecyclerView为线性布局时的一些配合
     *
     * @param rv      RecyclerView
     * @param adapter 适配器
     * @param type    1为垂直线性布局，2为横向线性布局
     */
    public static void setLinearLayoutManage(RecyclerView rv, RecyclerView.Adapter adapter, int type) {
        LinearLayoutManager manager = new LinearLayoutManager(rv.getContext());
        if (type == 1) {
            manager.setOrientation(LinearLayoutManager.VERTICAL);
        } else {
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        }
        rv.setHasFixedSize(true);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }

    /**
     * RecyclerView瀑布流布局时的一些配置
     *
     * @param rv      RecyclerView
     * @param adapter 适配器
     * @param num     一行加载几个
     * @param type    类型
     */
    public static void setStaggeredGridLayoutManager(RecyclerView rv, RecyclerView.Adapter adapter, int num, int type) {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(num, type);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }

}
