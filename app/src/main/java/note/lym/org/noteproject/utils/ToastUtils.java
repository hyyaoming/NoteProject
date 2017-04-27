package note.lym.org.noteproject.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * toast util
 */
public class ToastUtils {

    private static Context _context;
    private static Toast _toast = null;
    private static String _oldMsg;
    private static long _oneTime;
    private static long _twoTime;

    private ToastUtils() {
        throw new RuntimeException("ToastUtils cannot be initialized");
    }

    public static void init(Context context) {
        _context = context;
    }

    public static void showToast(String _msg) {
        if (_toast == null) {
            _toast = Toast.makeText(_context, _msg, Toast.LENGTH_SHORT);
            _toast.show();
            _oneTime = System.currentTimeMillis();
        } else {
            _twoTime = System.currentTimeMillis();
            if (!TextUtils.isEmpty(_oldMsg) && _oldMsg.equals(_msg)) {
                if (_twoTime > _oneTime) {
                    _toast.show();
                }
            } else {
                _oldMsg = _msg;
                _toast.setText(_msg);
                _toast.show();
            }
            _oneTime = _twoTime;
        }
    }

    public static void showToast(int _msgId) {
        showToast(_context.getString(_msgId));
    }


}
