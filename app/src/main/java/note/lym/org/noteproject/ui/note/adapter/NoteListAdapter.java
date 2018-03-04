package note.lym.org.noteproject.ui.note.adapter;

import android.support.v4.content.ContextCompat;

import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.constant.Constants;
import note.lym.org.noteproject.model.bean.Note;
import note.lym.org.noteproject.utils.TextUtils;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * @author yaoming.li
 * @since 2017-04-26 11:43
 */
public class NoteListAdapter extends BaseFastAdapter<Note, BaseViewHolder> {

    private String mKey;

    public NoteListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Note item) {
        helper.setText(R.id.tv_item_note_name, android.text.TextUtils.isEmpty(mKey) ? item.noteName : TextUtils.marryKeyChangeColor(item.noteName, mKey, ContextCompat.getColor(Constants.CONTEXT, R.color.key_color)));
        helper.setText(R.id.tv_item_note_time, android.text.TextUtils.isEmpty(mKey) ? item.date : TextUtils.marryKeyChangeColor(item.date, mKey, ContextCompat.getColor(Constants.CONTEXT, R.color.key_color)));
        helper.setText(R.id.tv_item_note_content, android.text.TextUtils.isEmpty(mKey) ? item.content : TextUtils.marryKeyChangeColor(item.content, mKey, ContextCompat.getColor(Constants.CONTEXT, R.color.key_color)));
    }

    /**
     * 设置搜索关键字
     *
     * @param key 关键字
     */
    public void filterKey(String key) {
        this.mKey = key;
        notifyDataSetChanged();
    }

}
