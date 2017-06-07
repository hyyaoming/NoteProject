package note.lym.org.noteproject.view.basepager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import note.lym.org.noteproject.view.PopupUtils;

/**
 * viewpager公用的Adapter
 *
 * @author yaoming.li
 * @since 2017-06-07 10:14
 */
public class CommonPagerAdapter<T> extends PagerAdapter {
    /**
     * 是否需要轮询
     */
    private boolean slider = false;
    private List<T> list = new ArrayList<>();
    private PagerHolderCreator mCreate;

    /**
     * 构造器
     * @param data  数据源
     * @param slider    是否开启轮询模式
     * @param creator   建造Holder
     */
    public CommonPagerAdapter(List<T> data, boolean slider, PagerHolderCreator creator) {
        this.list = data;
        this.mCreate = creator;
        this.slider = slider;
    }

    @Override
    public int getCount() {
        return slider ? list.size() + 2 : list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(null,position,container);
        container.addView(view);
        return view;
    }

    private View getView(View view, int position, ViewGroup parent) {
        BasePagerHolder holder;
        if (view == null) {
            holder = mCreate.createViewHolder(parent.getContext());
            view = holder.onCreateView(parent.getContext());
            view.setTag(holder);
        } else {
            holder = (BasePagerHolder) view.getTag();
        }
        if (!list.isEmpty() && holder != null) {
            if (slider) {
                int index = toRealPosition(position);
                holder.onBindView(parent.getContext(), index, list.get(index));
            } else {
                holder.onBindView(parent.getContext(), position, list.get(position));
            }
        }
        return view;
    }

    /**
     * 返回真实的位置
     *
     * @param position 角标
     * @return 下标从0开始
     */
    private int toRealPosition(int position) {
        int realPosition = (position - 1) % list.size();
        if (realPosition < 0) {
            realPosition += list.size();
        }
        return realPosition;
    }

}
