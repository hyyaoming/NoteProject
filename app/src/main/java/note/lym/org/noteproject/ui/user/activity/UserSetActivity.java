package note.lym.org.noteproject.ui.user.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import note.lym.org.noteproject.R;
import note.lym.org.noteproject.base.activity.SimpleActivity;
import note.lym.org.noteproject.ui.user.fragment.UserSetFragment;

/**
 * 用户设置Activity
 *
 * @author yaoming.li
 * @since 2017-07-07 15:32
 */
public class UserSetActivity extends SimpleActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_set;
    }

    @Override
    protected void initEventAndData() {
        bindView();
    }

    private void bindView() {
        initToolBar(mToolbar,true,R.string.user_setting);
        getFragmentManager().beginTransaction().replace(R.id.fl_content, new UserSetFragment()).commit();
    }

    public static void launch(Context context){
        Intent intent = new Intent(context,UserSetActivity.class);
        context.startActivity(intent);
    }

}
