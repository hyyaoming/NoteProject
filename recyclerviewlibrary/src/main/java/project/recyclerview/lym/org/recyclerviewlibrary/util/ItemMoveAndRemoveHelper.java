package project.recyclerview.lym.org.recyclerviewlibrary.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;

/**
 *
 * @author yaoming.li
 * @since 2017-05-09 16:45
 */
public class ItemMoveAndRemoveHelper {

    public static void openItemMove(RecyclerView view, BaseFastAdapter adapter){
        RecyclerViewItemTouchHelper callback = new RecyclerViewItemTouchHelper(adapter);
        callback.openItemMove(true);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(view);
    }

    public static void OpenItemRemove(RecyclerView view,BaseFastAdapter adapter){
        RecyclerViewItemTouchHelper  callback = new RecyclerViewItemTouchHelper(adapter);
        callback.openItemRemove(true);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(view);
    }

    public static void OpenItemMoveAndRemove(RecyclerView view,BaseFastAdapter adapter){
        RecyclerViewItemTouchHelper  callback = new RecyclerViewItemTouchHelper(adapter);
        callback.openItemRemove(true);
        callback.openItemMove(true);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(view);
    }

}
