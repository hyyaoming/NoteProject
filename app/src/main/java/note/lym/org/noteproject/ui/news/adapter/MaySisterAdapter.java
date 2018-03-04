package note.lym.org.noteproject.ui.news.adapter;

import android.widget.ImageView;

import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.bean.MaySisterData;
import note.lym.org.noteproject.utils.GlideUtils;
import note.lym.org.noteproject.utils.TextUtils;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * 不得姐适配器
 *
 * @author yaoming.li
 * @since 2017-05-16 14:14
 */
public class MaySisterAdapter extends BaseFastAdapter<MaySisterData.ShowapiResBodyBean.PagebeanBean.ContentlistBean, BaseViewHolder> {
    public MaySisterAdapter(int layoutResId, List<MaySisterData.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MaySisterData.ShowapiResBodyBean.PagebeanBean.ContentlistBean item) {
        ImageView iv = helper.getView(R.id.iv_may_sister_icon);
        GlideUtils.loadCircleImage(item.getProfile_image(), iv);
        helper.setText(R.id.tv_may_sister_name, item.getName());
        helper.setText(R.id.tv_may_sister_date, item.getCreate_time());
        helper.setText(R.id.tv_may_sister_content, TextUtils.trimAllWhitespace(item.getText()));
    }
}
