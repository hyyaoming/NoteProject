package note.lym.org.noteproject.adapter;

import android.renderscript.Matrix2f;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;

import java.util.List;

import note.lym.org.noteproject.fragment.BelleListFragment;
import note.lym.org.noteproject.fragment.NewsListFragment;
import note.lym.org.noteproject.fragment.NoteListFragment;

/**
 * Desp.
 *
 * @author yaoming.li
 * @since 2017-05-04 17:58
 */
public class TabFragmentAdapter extends FragmentStatePagerAdapter {

    private static final int BELLE_ITEM = 0;
    private static final int NEW_ITEM = 1;
    private SparseArray<Fragment> mArray = new SparseArray<>();
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
                if(null == mFragment){
                    NewsListFragment fragment = new NewsListFragment();
                    mFragment = fragment;
                    mArray.put(NEW_ITEM,fragment);
                    return fragment;
                }else{
                    return  mFragment;
                }
        }
        return new Fragment();
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
