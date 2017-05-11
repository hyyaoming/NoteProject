package note.lym.org.noteproject.adapter;

import android.text.TextUtils;
import android.widget.TextView;

import com.zzhoujay.richtext.RichText;

import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.bean.TextJoke;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * 文本笑话大全适配器
 *
 * @author yaoming.li
 * @since 2017-05-11 23:17
 */
public class TextJokeListAdapter extends BaseFastAdapter<TextJoke.ShowapiResBodyBean.ContentlistBean, BaseViewHolder> {
    public TextJokeListAdapter(int layoutResId, List<TextJoke.ShowapiResBodyBean.ContentlistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TextJoke.ShowapiResBodyBean.ContentlistBean item) {
        helper.setText(R.id.tv_text_joke_title, item.getTitle());
        if(!TextUtils.isEmpty(item.getText()) && item.getCt().length() > 10){
            helper.setText(R.id.tv_text_joke_date, item.getCt().substring(0,10));
        }
        helper.setText(R.id.tv_text_joke_content, note.lym.org.noteproject.utils.TextUtils.replaceWhiteSpace(item.getText()));
    }
}
