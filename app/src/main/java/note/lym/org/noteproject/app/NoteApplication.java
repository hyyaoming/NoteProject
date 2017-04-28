package note.lym.org.noteproject.app;

import org.litepal.LitePalApplication;

import note.lym.org.noteproject.utils.Static;
import note.lym.org.noteproject.utils.ToastUtils;

/**
 *
 * application
 * @author yaoming.li
 * @since 2017-04-25 15:08
 */
public class NoteApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
        Static.CONTEXT = this;
    }
}
