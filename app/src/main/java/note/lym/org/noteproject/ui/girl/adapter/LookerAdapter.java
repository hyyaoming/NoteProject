package note.lym.org.noteproject.ui.girl.adapter;

import android.widget.ImageView;

import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.bean.LookerGirl;
import note.lym.org.noteproject.utils.DefIconFactory;
import note.lym.org.noteproject.utils.GlideUtils;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * 美图适配器
 *
 * @author yaoming.li
 * @since 2017-05-17 00:01
 */
public class LookerAdapter extends BaseFastAdapter<LookerGirl.ShowapiResBodyBean.PagebeanBean.ContentlistBean,BaseViewHolder> {
    public LookerAdapter(int layoutResId, List<LookerGirl.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LookerGirl.ShowapiResBodyBean.PagebeanBean.ContentlistBean item) {
        ImageView iv = helper.getView(R.id.iv_sister_photo);
        LookerGirl.ShowapiResBodyBean.PagebeanBean.ContentlistBean.ListBean bean = item.getList().get(0);
        GlideUtils.load(iv,bean.getMiddle(), DefIconFactory.iconDefault());
        helper.setText(R.id.tv_title,item.getTitle());
    }
}
