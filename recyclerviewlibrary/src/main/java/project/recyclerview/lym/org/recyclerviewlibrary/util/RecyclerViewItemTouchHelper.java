package project.recyclerview.lym.org.recyclerviewlibrary.util;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import project.recyclerview.lym.org.recyclerviewlibrary.listener.ItemRemoveAdapterHelper;

/**
 *
 * @author yaoming.li
 * @since 2017-05-09 15:23
 */
public class RecyclerViewItemTouchHelper extends ItemTouchHelper.Callback {


    private ItemRemoveAdapterHelper mHelper;
    private boolean isItemMove =false;
    private boolean isItemRemove = false;

    public void openItemMove(boolean flag){
        this.isItemMove = flag;
    }

    public void openItemRemove(boolean flag){
        this.isItemRemove = flag;
    }

    public RecyclerViewItemTouchHelper(ItemRemoveAdapterHelper helper){
        this.mHelper = helper;
    }

    //该方法决定侧滑的方法和是否可以拖拽。
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    //当用户拖动一个Item进行上下移动从旧的位置到新的位置的时候会调用该方法。
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        mHelper.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }


    //当用户左右滑动Item达到删除条件时，会调用该方法。
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mHelper.onItemVanish(viewHolder);
    }

    //当用户操作完毕某个item并且其动画也结束后会调用该方法，一般我们在该方法内恢复ItemView的初始状态，
    // 防止由于复用而产生的显示错乱问题。
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    //从静止状态变为拖拽或者滑动的时候会回调该方法，参数actionState表示当前的状态。
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    //我们可以在这个方法内实现我们自定义的交互规则或者自定义的动画效果。
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    //是否支持长按拖动，默认支持。
    @Override
    public boolean isLongPressDragEnabled() {
        return isItemMove;
    }

    //是否支持左右滑动,默认支持
    @Override
    public boolean isItemViewSwipeEnabled() {
        return isItemRemove;
    }
}
