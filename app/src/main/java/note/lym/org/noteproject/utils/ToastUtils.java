package note.lym.org.noteproject.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * toast util
 */
public class ToastUtils {

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;
    private static Toast sToast = null;
    private static String sOldMessage;
    private static long sOneTime;
    private static long sTwoTime;

    private ToastUtils() {
        throw new RuntimeException("ToastUtils cannot be initialized");
    }

    /**
     * 初始化
     *
     * @param context context
     */
    public static void init(Context context) {
        sContext = context;
    }

    /**
     * 弹出吐司
     *
     * @param msg message
     */
    public static void showToast(String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(sContext, msg, Toast.LENGTH_LONG);
            sToast.show();
            sOneTime = System.currentTimeMillis();
        } else {
            sTwoTime = System.currentTimeMillis();
            if (!TextUtils.isEmpty(sOldMessage) && sOldMessage.equals(msg)) {
                if (sTwoTime > sOneTime) {
                    sToast.show();
                }
            } else {
                sOldMessage = msg;
                sToast.setText(msg);
                sToast.show();
            }
            sOneTime = sTwoTime;
        }
    }

    /**
     * 吐司
     *
     * @param resId 弹出吐司
     */
    public static void showToast(int resId) {
        showToast(sContext.getString(resId));
    }


}
