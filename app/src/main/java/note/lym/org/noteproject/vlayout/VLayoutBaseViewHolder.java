package note.lym.org.noteproject.vlayout;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * description:
 *
 * @author yaoming.li
 * @version 1.0.0
 * @since 2017/11/15
 */
public class VLayoutBaseViewHolder<T> extends RecyclerView.ViewHolder {
    private final SparseArray<View> views;
    protected T mData;
    protected View mRootView;
    protected OnItemListener mListener;
    protected Context mContext;
    protected int mPosition;

    public VLayoutBaseViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<View>();
        mRootView = itemView;
        initView();
    }

    protected void initView() {

    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = mRootView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * Will set the text of a TextView.
     *
     * @param viewId The view id.
     * @param value  The text to put in the text view.
     * @return The BaseViewHolder for chaining.
     */
    public void setText(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
    }

    public void setText(int viewId, @StringRes int strId) {
        TextView view = getView(viewId);
        view.setText(strId);
    }

    public void setData(T list, int position) {
        this.mData = list;
        this.mPosition = position;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setListener(OnItemListener listener) {
        this.mListener = listener;
    }

}
