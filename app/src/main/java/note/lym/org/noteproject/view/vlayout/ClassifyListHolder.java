package note.lym.org.noteproject.view.vlayout;

import android.view.View;
import android.widget.ImageView;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.model.bean.HealthList;
import note.lym.org.noteproject.utils.DefIconFactory;
import note.lym.org.noteproject.utils.GlideUtils;

/**
 * description:
 *
 * @author yaoming.li
 * @version 1.0.0
 * @since 2017/11/15
 */
public class ClassifyListHolder extends VLayoutBaseViewHolder<HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean> {


    public ClassifyListHolder(View itemView) {
        super(itemView);
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

    @Override
    public void setData(HealthList.ShowapiResBodyBean.PagebeanBean.ContentlistBean item, int position) {
        super.setData(item, position);
        GlideUtils.load((ImageView) getView(R.id.iv_icon), item.getImg(), DefIconFactory.iconDefault());
        setText(R.id.tv_news_name, item.getTitle().replaceAll("<b>", "").replaceAll("</b>", ""));
        setText(R.id.tv_news_time, item.getTime());
        setText(R.id.tv_source, item.getAuthor());
    }
}
