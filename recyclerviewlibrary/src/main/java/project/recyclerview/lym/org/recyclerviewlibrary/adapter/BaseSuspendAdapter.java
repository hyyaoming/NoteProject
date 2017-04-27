package project.recyclerview.lym.org.recyclerviewlibrary.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseMultipleAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.entity.MultipleType;
import project.recyclerview.lym.org.recyclerviewlibrary.util.FullSpanUtil;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * Desp.
 *
 * @author yaoming.li
 * @version 8.1.0
 * @since 2017-03-16 12:53
 */
public abstract class BaseSuspendAdapter<T extends MultipleType> extends BaseMultipleAdapter<T, BaseViewHolder> {

    public static final int HEAD_ITEM = 0x0000;
    public static final int CONTENT_ITEM = 0x0011;


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseSuspendAdapter(List<T> data) {
        super(data);
        addItemTypes();
    }

    public abstract void addItemTypes();

}
