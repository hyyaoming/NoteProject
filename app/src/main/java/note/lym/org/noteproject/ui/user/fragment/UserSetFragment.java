package note.lym.org.noteproject.ui.user.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.ui.web.activity.WebActivity;

/**
 * 用户设置fragment
 *
 * @author yaoming.li
 * @since 2017-07-07 10:53
 */
public class UserSetFragment extends PreferenceFragment {

    public static final String BGM_MUSIC = "bgm_music";
    public static final String LOAD_IMAGE = "load_image";
    private static final String JUMP_TO_GIT_HUB = "jump_to_git_hub";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    //点击监听
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (JUMP_TO_GIT_HUB.equals(preference.getKey())) {
            WebActivity.launch(getActivity(), WebActivity.URL,getString(R.string.GitHub));
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
