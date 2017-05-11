package note.lym.org.noteproject.adapter;

import android.widget.ImageView;

import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.model.bean.NewsList;
import note.lym.org.noteproject.utils.DefIconFactory;
import note.lym.org.noteproject.utils.GlideUtils;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * 新闻列表
 *
 * @author yaoming.li
 * @since 2017-05-08 18:39
 */
public class NewsListAdapter extends BaseFastAdapter<NewsList.NewsBean, BaseViewHolder> {
    public NewsListAdapter(int layoutResId, List<NewsList.NewsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsList.NewsBean item) {
        ImageView iv = helper.getView(R.id.iv_icon);
        GlideUtils.load(NoteApplication.getInstance(),iv,item.getImgsrc(), DefIconFactory.iconDefault());
        helper.setText(R.id.tv_news_name,item.getTitle());
        helper.setText(R.id.tv_news_time,item.getPtime());
        helper.setText(R.id.tv_source,item.getSource());
    }
}
