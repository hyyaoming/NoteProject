package note.lym.org.noteproject.adapter;

import android.widget.ImageView;

import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.model.bean.HealthList;
import note.lym.org.noteproject.utils.DefIconFactory;
import note.lym.org.noteproject.utils.GlideUtils;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * 咨询列表适配器
 *
 * @author yaoming.li
 * @since 2017-05-12 11:15
 */
public class HealthListAdapter extends BaseFastAdapter<HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean, BaseViewHolder> {
    public HealthListAdapter(int layoutResId, List<HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean item) {
        ImageView iv = helper.getView(R.id.iv_icon);
        GlideUtils.load(NoteApplication.getInstance(),iv,item.getImg(), DefIconFactory.iconDefault());
        helper.setText(R.id.tv_news_name,item.getTitle().replaceAll("<b>","").replaceAll("</b>","").toString());
        helper.setText(R.id.tv_news_time,item.getTime());
        helper.setText(R.id.tv_source,item.getAuthor());
    }

}
