package note.lym.org.noteproject.vlayout;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.litepal.crud.DataSupport;

import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.bean.BannerModel;
import note.lym.org.noteproject.model.dao.Collect;

/**
 * description:
 *
 * @author yaoming.li
 * @version 1.0.0
 * @since 2017/11/15
 */
public class BannerViewHolder extends VLayoutBaseViewHolder<BannerModel> {

    public BannerViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(BannerModel list, int position) {
        super.setData(list, position);
        Banner banner = getView(R.id.banner);
        //设置图片集合
        banner.setImages(list.getBanner());
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(mContext).load((String) o).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into
                        (imageView);
            }
        });

        //设置点击事件监听
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                mListener.onItemClickListener(mRootView, mData,i);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.isAutoPlay(false);
        banner.start();
    }

}
