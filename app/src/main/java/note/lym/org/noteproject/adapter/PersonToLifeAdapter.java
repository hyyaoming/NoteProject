package note.lym.org.noteproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.model.bean.MoreType;
import note.lym.org.noteproject.view.NoteGridView;

/**
 * 人生百态
 *
 * @author yaoming.li
 * @since 2017-05-16 18:19
 */
public class PersonToLifeAdapter extends BaseAdapter {

    private List<MoreType.ShowapiResBodyBean.ListBeanX> mList;
    private LayoutInflater mInflater;
    private Context mContext;

    public PersonToLifeAdapter(Context context) {
        mList = new ArrayList<>();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<MoreType.ShowapiResBodyBean.ListBeanX> data) {
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExpandedViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.expanded_item, null);
            viewHolder = new ExpandedViewHolder();
            viewHolder.mGvDown = (NoteGridView) convertView.findViewById(R.id.gv_note);
            viewHolder.mTvUp = (TextView) convertView.findViewById(R.id.tv_up);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ExpandedViewHolder) convertView.getTag();
        }
        viewHolder.mGvDown.setAdapter(new ExpandedDownAdapter(mList.get(position).getList(),mContext));
        viewHolder.mTvUp.setText(mList.get(position).getName());
        return convertView;
    }

    class ExpandedViewHolder {
        public TextView mTvUp;
        public NoteGridView mGvDown;
    }

}
