package note.lym.org.noteproject.view.basepager;

import android.content.Context;
import android.view.View;

/**
 * viewpager的holder基类
 *
 * @author yaoming.li
 * @since 2017-06-06 11:13
 */
public interface BasePagerHolder<T> {
    /**
     * 初始化一个布局视图
     *
     * @param context 上下文
     */
    View onCreateView(Context context);

    /**
     * 绑定数据
     *
     * @param context  上下文
     * @param position 角标
     * @param data     数据
     */
    void onBindView(Context context, int position, T data);
}
