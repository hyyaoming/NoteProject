package note.lym.org.noteproject.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.dao.Collect;
import note.lym.org.noteproject.ui.home.BigBelleActivity;
import note.lym.org.noteproject.utils.DefIconFactory;
import note.lym.org.noteproject.utils.GlideUtils;

/**
 * 关注页适配器
 *
 * @author yaoming.li
 * @since 2017-05-17 13:51
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<Collect> mList = new ArrayList<>();

    public ViewPagerAdapter(List<Collect> list) {
        mList.addAll(list);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.attention_vp_item_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_attention);
        Collect collect = mList.get(position);
        GlideUtils.load(container.getContext(), iv, collect.url, DefIconFactory.iconDefault());
        container.addView(view);
        jumpToBigBelle(container.getContext(), view, collect.url);
        return view;
    }

    private void jumpToBigBelle(final Context context, View view, final String url) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigBelleActivity.action(context, url);
            }
        });
    }
}
