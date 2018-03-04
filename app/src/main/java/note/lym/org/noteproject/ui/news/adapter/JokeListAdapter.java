package note.lym.org.noteproject.ui.news.adapter;

import android.widget.ImageView;

import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.bean.Joke;
import note.lym.org.noteproject.utils.DefIconFactory;
import note.lym.org.noteproject.utils.GlideUtils;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 *  笑话适配器
 * @author yaoming.li
 * @since 2017-05-11 16:18
 */
public class JokeListAdapter extends BaseFastAdapter<Joke.ShowapiResBodyBean.ContentlistBean, BaseViewHolder> {


    public JokeListAdapter(int layoutResId, List<Joke.ShowapiResBodyBean.ContentlistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Joke.ShowapiResBodyBean.ContentlistBean item) {
        ImageView iv = helper.getView(R.id.iv_joke_gif);
        GlideUtils.loadGif(item.getImg(),iv, DefIconFactory.iconDefault());
        helper.setText(R.id.tv_joke_title, item.getTitle());
        helper.setText(R.id.tv_joke_time, item.getCt());
    }
}
