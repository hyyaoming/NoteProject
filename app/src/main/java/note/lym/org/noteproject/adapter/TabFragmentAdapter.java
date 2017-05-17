package note.lym.org.noteproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;

import java.util.List;

import note.lym.org.noteproject.fragment.BelleListFragment;
import note.lym.org.noteproject.fragment.JokeListFragment;
import note.lym.org.noteproject.fragment.MaySisterFragment;
import note.lym.org.noteproject.fragment.NewsListFragment;
import note.lym.org.noteproject.fragment.PersonToLifeFragment;
import note.lym.org.noteproject.fragment.TextJokeListFragment;

/**
 * FragmentPagerAdapter适用于数量较少，page固定的情况，因为fragment不会被被完全回收，不会走onDestroy
 * 1、通过offscreenPageLimit来控制page container的cache数量 n*2+1;
 * 2、当加载的page超出cache count会用FragmentManager来释放fragment
 * 3、被释放的fragment实际上不会被完全回收，因为没有调用onDestroy(),当再次回到这个page时也没有调用onCreate()；
 * 4、当fragment被显示在屏幕上时，setUserVisibleHint为true,不显示时为false.
 * <p>
 * FragmentStatePagerAdapter适用于数量较多的fragment，保存fragment可以得到回收，清理内存，会走onDestroy
 * 前三点基本一致
 * 根据情况这里应该使用第一种适配器。
 *
 * @author yaoming.li
 * @since 2017-05-04 17:58
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {

    private static final int BELLE_ITEM = 0;
    private static final int NEW_ITEM = 1;
    private SparseArray<Fragment> mArray = new SparseArray<>();
    private static final int JOKE_ITEM = 4;
    private static final int TEXT_JOKE_ITEM = 3;
    private static final int MAY_SISTER_ITEM = 2;
    private static final int PERSIONS_TO_LIFE = 5;
    private Fragment mFragment;
    private List<String> mList;

    public TabFragmentAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case BELLE_ITEM:
                mFragment = mArray.get(BELLE_ITEM);
                if (null == mFragment) {
                    BelleListFragment fragment = new BelleListFragment();
                    mFragment = fragment;
                    mArray.put(BELLE_ITEM, fragment);
                    return fragment;
                } else {
                    return mFragment;
                }
            case NEW_ITEM:
                mFragment = mArray.get(NEW_ITEM);
                if (null == mFragment) {
                    NewsListFragment fragment = new NewsListFragment();
                    mFragment = fragment;
                    mArray.put(NEW_ITEM, fragment);
                    return fragment;
                } else {
                    return mFragment;
                }
            case JOKE_ITEM:
                mFragment = mArray.get(JOKE_ITEM);
                if (null == mFragment) {
                    JokeListFragment fragment = new JokeListFragment();
                    mFragment = fragment;
                    mArray.put(JOKE_ITEM, fragment);
                    return fragment;
                } else {
                    return mFragment;
                }
            case TEXT_JOKE_ITEM:
                mFragment = mArray.get(TEXT_JOKE_ITEM);
                if (null == mFragment) {
                    TextJokeListFragment fragment = new TextJokeListFragment();
                    mFragment = fragment;
                    mArray.put(TEXT_JOKE_ITEM, fragment);
                    return fragment;
                } else {
                    return mFragment;
                }
            case MAY_SISTER_ITEM:
                mFragment = mArray.get(MAY_SISTER_ITEM);
                if (null == mFragment) {
                    MaySisterFragment fragment = new MaySisterFragment();
                    mFragment = fragment;
                    mArray.put(MAY_SISTER_ITEM, fragment);
                    return fragment;
                } else {
                    return mFragment;
                }
            case PERSIONS_TO_LIFE:
                mFragment = mArray.get(PERSIONS_TO_LIFE);
                if (null == mFragment) {
                    PersonToLifeFragment fragment = new PersonToLifeFragment();
                    mFragment = fragment;
                    mArray.put(PERSIONS_TO_LIFE, fragment);
                    return fragment;
                } else {
                    return mFragment;
                }
        }
        return new Fragment();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }
}
