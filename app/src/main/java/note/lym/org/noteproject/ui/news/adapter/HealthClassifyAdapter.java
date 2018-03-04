package note.lym.org.noteproject.ui.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 健康资讯类适配器
 *
 * @author yaoming.li
 * @since 2017-05-12 10:37
 */
public class HealthClassifyAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private List<String> mTitles;

    public HealthClassifyAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    public void setItems(LinkedList<Fragment> fragments, LinkedList<String> titles) {
        this.mFragments = fragments;
        this.mTitles = titles;
        notifyDataSetChanged();
    }
}
