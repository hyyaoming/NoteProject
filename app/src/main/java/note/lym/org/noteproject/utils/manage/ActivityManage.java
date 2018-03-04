package note.lym.org.noteproject.utils.manage;

import android.app.Activity;

import java.util.Stack;

/**
 * 活动管理类
 *
 * @author yaoming.li
 * @since 2017-04-25 10:32
 */
public class ActivityManage {

    private static ActivityManage _Instance;
    private static Stack<Activity> arrays = new Stack<>();

    private ActivityManage() {
    }

    /**
     * instance
     *
     * @return ActivityManage
     */
    public static ActivityManage getInstance() {
        if (_Instance == null) {
            synchronized (ActivityManage.class) {
                if (_Instance == null) {
                    _Instance = new ActivityManage();
                }
            }
        }
        return _Instance;
    }

    /**
     * 添加指定的Activity
     *
     * @param activity activity
     */
    public static void addActivityToStack(Activity activity) {
        if (null != activity) {
            arrays.add(activity);
        }
    }

    /**
     * 移除当前的Activity
     *
     * @param activity activity
     */
    public static void removeActivityBackStack(Activity activity) {
        if (null != activity && arrays.contains(activity)) {
            arrays.remove(activity);
        }
    }

    /**
     * 结束所有的Activity
     */
    public static void backAllActivityToStack() {
        for (Activity act : arrays) {
            if (null != act) {
                removeActivityBackStack(act);
            }
        }
        arrays.clear();
    }

    /**
     * 获取当前栈顶Activity
     * @return
     */
    public static Activity getStackTopActivity() {
        if (!arrays.isEmpty()) {
            return arrays.lastElement();
        }
        return null;
    }


}
