package note.lym.org.noteproject.view.vlayout;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * description: 对VLayout进行了简单的封装
 *
 * @author yaoming.li
 * @version 1.0.0
 * @since 2017/11/15
 */
public class VLayoutBaseAdapter<T> extends DelegateAdapter.Adapter<VLayoutBaseViewHolder<T>> {

    private List<T> mData; //数据源
    private Context mContext;
    private int mResId;
    private LayoutHelper mHelper;
    private Class<? extends VLayoutBaseViewHolder> mClazz;
    private OnItemListener mListener;
    private RequestLoadMoreListener mRequestLoadMoreListener;


    /**
     * 构造器
     *
     * @param context 上下文
     */
    public VLayoutBaseAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 构造器
     *
     * @param list     数据源
     * @param context  上下文
     * @param id       布局资源id
     * @param helper   每个适用的布局
     * @param clazz    每个布局所对应的ViewHolder
     * @param listener 布局监听器
     */
    public VLayoutBaseAdapter(List<T> list, Context context, int id, LayoutHelper helper,
                              Class<? extends VLayoutBaseViewHolder> clazz, OnItemListener listener) {
        this.mData = list == null ? new ArrayList<T>() : list;
        this.mContext = context;
        this.mResId = id;
        this.mHelper = helper;
        this.mClazz = clazz;
        this.mListener = listener;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.mHelper;
    }

    @Override
    public VLayoutBaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mResId, parent, false);
        try {
            Constructor<? extends VLayoutBaseViewHolder> mClazzHolder = mClazz.getConstructor(View.class);
            if (null != mClazzHolder) {
                return mClazzHolder.newInstance(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置当前的布局id
     *
     * @param layoutId 布局id
     * @return 设置当前的布局id
     */
    public VLayoutBaseAdapter setLayoutId(int layoutId) {
        this.mResId = layoutId;
        return this;
    }

    /**
     * 加载更多
     * @param rv    RecyclerView
     * @param listener  RecyclerView的回调
     * @return  返回当前类
     */
    public VLayoutBaseAdapter setLoadMoreListener(RecyclerView rv, RequestLoadMoreListener listener) {
        this.mRequestLoadMoreListener = listener;
        rv.addOnScrollListener(mLoadMoreListener);
        return this;
    }

    private RecyclerView.OnScrollListener mLoadMoreListener = new RecyclerView.OnScrollListener() {

        private boolean isLastItemPosition = false;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (isLastItemPosition && newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                if (mRequestLoadMoreListener != null) {
                    mRequestLoadMoreListener.onLoadMoreRequested();
                }
            }
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
            int lastPosition = lm.findLastVisibleItemPosition();
            int position = lm.getItemCount() - 1;
            isLastItemPosition = dy > 0 && lastPosition == position;
        }
    };

    /**
     * 设置布局监听
     *
     * @param listener 布局监听
     * @return 返回当前类
     */
    public VLayoutBaseAdapter setListener(OnItemListener<T> listener) {
        this.mListener = listener;
        return this;
    }

    /**
     * 设置当前的数据
     *
     * @param data 设置数据源
     * @return 返回当前类
     */
    public VLayoutBaseAdapter setData(List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        return this;
    }

    /**
     * 刷新数据
     *
     * @param data 需刷新的数据
     */
    public void addData(List<T> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 插入一条数据
     *
     * @param data 向当前数据源当中插入一条数据
     */
    public void addItem(T data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    /**
     * 删除一条指定的数据
     *
     * @param data 被指定删除的数据
     */
    public void removeItem(T data) {
        mData.remove(data);
        notifyDataSetChanged();
    }

    /***
     * 删除所有数据
     */
    public void removeAllData() {
        mData.clear();
        notifyDataSetChanged();
    }


    /**
     * 获取当前的数据
     *
     * @return 获取当前的数据
     */
    public List<T> getData() {
        return mData;
    }

    /**
     * 设置布局管理器
     *
     * @param helper 布局管理器
     * @return 返回当前类
     */
    public VLayoutBaseAdapter setLayoutHelper(LayoutHelper helper) {
        this.mHelper = helper;
        return this;
    }

    /**
     * 设置ViewHolder
     *
     * @param holder 设置对应布局的ViewHolder
     * @return 返回当前类
     */
    public VLayoutBaseAdapter setHolder(Class<? extends VLayoutBaseViewHolder> holder) {
        if (null == holder) {
            throw new RuntimeException("class is null, this is don't null");
        }
        this.mClazz = holder;
        return this;
    }

    @Override
    public void onBindViewHolder(VLayoutBaseViewHolder<T> holder, int position) {
        holder.setContext(mContext);
        holder.setData(mData.get(position), position);
        holder.setListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
