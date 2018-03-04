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

    private static ActivityManage sInstance;
    private static Stack<Activity> sArray = new Stack<>();

    private ActivityManage() {
    }

    /**
     * instance
     *
     * @return ActivityManage
     */
    public static ActivityManage getInstance() {
        if (sInstance == null) {
            synchronized (ActivityManage.class) {
                if (sInstance == null) {
                    sInstance = new ActivityManage();
                }
            }
        }
        return sInstance;
    }

    /**
     * 添加指定的Activity
     *
     * @param activity activity
     */
    public void addActivityToStack(Activity activity) {
        if (null != activity) {
            sArray.add(activity);
        }
    }

    /**
     * 移除当前的Activity
     *
     * @param activity activity
     */
    public void removeActivityBackStack(Activity activity) {
        if (null != activity && sArray.contains(activity)) {
            sArray.remove(activity);
        }
    }

    /**
     * 结束所有的Activity
     */
    public void backAllActivityToStack() {
        for (Activity act : sArray) {
            if (null != act) {
                removeActivityBackStack(act);
            }
        }
        sArray.clear();
    }

    /**
     * 获取当前栈顶Activity
     *
     * @return  Activity
     */
    public Activity getStackTopActivity() {
        if (!sArray.isEmpty()) {
            return sArray.lastElement();
        }
        return null;
    }


}
