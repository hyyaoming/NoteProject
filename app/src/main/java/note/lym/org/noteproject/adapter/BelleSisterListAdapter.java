package note.lym.org.noteproject.adapter;

import android.widget.ImageView;

import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.bean.SisterClassList;
import note.lym.org.noteproject.utils.GlideUtils;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * @author yaoming.li
 * @since 2017-05-12 17:46
 */
public class BelleSisterListAdapter extends BaseFastAdapter<SisterClassList.ShowapiResBodyBean.DataBean, BaseViewHolder> {


    public BelleSisterListAdapter(int layoutResId, List<SisterClassList.ShowapiResBodyBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final SisterClassList.ShowapiResBodyBean.DataBean item) {
        final ImageView view = helper.getView(R.id.iv_sister_photo);
        helper.setText(R.id.tv_title, item.getTitle());
        GlideUtils.loadCutImage(view,item.getImgurl());
    }
}
