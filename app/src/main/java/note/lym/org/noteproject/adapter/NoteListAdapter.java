package note.lym.org.noteproject.adapter;

import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.Note;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * @author yaoming.li
 * @since 2017-04-26 11:43
 */
public class NoteListAdapter extends BaseFastAdapter<Note, BaseViewHolder> {


    public NoteListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Note item) {
        helper.setText(R.id.tv_item_note_name, item.noteName);
        helper.setText(R.id.tv_item_note_time, item.date);
        helper.setText(R.id.tv_item_note_content, item.content);
    }
}
