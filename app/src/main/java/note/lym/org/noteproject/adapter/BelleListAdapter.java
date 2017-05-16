package note.lym.org.noteproject.adapter;

import android.util.Log;
import android.widget.ImageView;

import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.app.NoteApplication;
import note.lym.org.noteproject.model.bean.Belle;
import note.lym.org.noteproject.utils.DefIconFactory;
import note.lym.org.noteproject.utils.GlideUtils;
import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;
import project.recyclerview.lym.org.recyclerviewlibrary.viewholder.BaseViewHolder;

/**
 * 妹子图片适配器
 *
 * @author yaoming.li
 * @since 2017-05-04 13:53
 */
public class BelleListAdapter extends BaseFastAdapter<Belle.ResultsBean, BaseViewHolder> {
    public BelleListAdapter(int layoutResId, List<Belle.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Belle.ResultsBean item) {
        ImageView iv = helper.getView(R.id.iv_belle);
        GlideUtils.loadCutImage(iv,item.getUrl());
    }
}
