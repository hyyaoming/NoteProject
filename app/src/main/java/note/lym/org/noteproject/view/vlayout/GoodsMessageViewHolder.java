package note.lym.org.noteproject.view.vlayout;

import android.view.View;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.bean.Note;

/**
 * description:
 *
 * @author yaoming.li
 * @version 1.0.0
 * @since 2017/11/16
 */
public class GoodsMessageViewHolder extends VLayoutBaseViewHolder<Note> {

    public GoodsMessageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(Note list, int position) {
        super.setData(list, position);
        setText(R.id.tv_name, list.noteName);
        setText(R.id.tv_date, list.date);
        setText(R.id.tv_content, list.content);
    }

    @Override
    protected void initView() {
        super.initView();
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClickListener(v, mData, mPosition);
            }
        });
    }
}
