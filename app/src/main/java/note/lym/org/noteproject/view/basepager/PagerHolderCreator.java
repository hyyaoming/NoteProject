package note.lym.org.noteproject.view.basepager;

import android.content.Context;

/**
 * ViewHolder建造基类
 *
 * @author yaoming.li
 * @since 2017-06-06 11:13
 */
public interface PagerHolderCreator<VH extends BasePagerHolder> {
    /**
     * 初始化一个ViewHolder
     *
     * @param context 上下文
     * @return 返回一个ViewHolder
     */
    VH createViewHolder(Context context);
}
