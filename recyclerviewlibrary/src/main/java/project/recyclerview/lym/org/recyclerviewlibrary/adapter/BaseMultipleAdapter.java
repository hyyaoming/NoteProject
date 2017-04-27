package project.recyclerview.lym.org.recyclerviewlibrary.adapter;

import android.support.annotation.LayoutRes;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.List;

import project.recyclerview.lym.org.recyclerviewlibrary.entity.MultipleType;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * Desp.
 *
 * @author yaoming.li
 * @version 8.1.0
 * @since 2017-03-15 15:56
 */
public abstract class BaseMultipleAdapter<T extends MultipleType,K extends BaseViewHolder> extends BaseFastAdapter<T,K>  {


    /**
     * layouts indexed with their types
     */
    private SparseArray<Integer> layouts;

    private static final int DEFAULT_VIEW_TYPE = -0xff;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data    A new list is created out of this one to avoid mutable list
     */
    public BaseMultipleAdapter( List<T> data) {
        super( data);
    }

    @Override
    protected int getDefItemViewType(int position) {
        Object item = mData.get(position);
        if (item instanceof MultipleType) {
            return ((MultipleType)item).getViewType();
        }
        return DEFAULT_VIEW_TYPE;
    }

    protected void setDefaultViewTypeLayout(@LayoutRes int layoutResId) {
        addItemType(DEFAULT_VIEW_TYPE, layoutResId);
    }

    @Override
    protected K onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, getLayoutId(viewType));
    }

    private int getLayoutId(int viewType) {
        return layouts.get(viewType);
    }

    protected void addItemType(int type, @LayoutRes int layoutResId) {
        if (layouts == null) {
            layouts = new SparseArray<>();
        }
        layouts.put(type, layoutResId);
    }
}
