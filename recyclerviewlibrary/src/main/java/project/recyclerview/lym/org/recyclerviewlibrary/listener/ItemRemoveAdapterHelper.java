package project.recyclerview.lym.org.recyclerviewlibrary.listener;

import android.support.v7.widget.RecyclerView;

/**
 *
 * @author yaoming.li
 * @since 2017-05-09 15:25
 */
public interface ItemRemoveAdapterHelper {

    boolean onItemMove(int formPosition, int toPosition);

    boolean onItemVanish(RecyclerView.ViewHolder holder);

}
