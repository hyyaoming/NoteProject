package note.lym.org.noteproject.ui.news.adapter;

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
import note.lym.org.noteproject.ui.girl.activity.LookerGirlActivity;

/**
 * @author yaoming.li
 * @since 2017-05-16 21:42
 */
public class ExpandedDownAdapter extends BaseAdapter {

    private List<MoreType.ShowapiResBodyBean.ListBeanX.ListBean> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;

    public ExpandedDownAdapter(List<MoreType.ShowapiResBodyBean.ListBeanX.ListBean> list, Context context) {
        mList.addAll(list);
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        DownViewHolder vh;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.expanded_down_item, null);
            vh = new DownViewHolder();
            vh.mTv = (TextView) convertView.findViewById(R.id.tv_expanded_down);
            convertView.setTag(vh);
        } else {
            vh = (DownViewHolder) convertView.getTag();
        }
        final MoreType.ShowapiResBodyBean.ListBeanX.ListBean bean = mList.get(position);
        vh.mTv.setText(bean.getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LookerGirlActivity.launch(mContext,String.valueOf(bean.getId()),bean.getName());
            }
        });
        return convertView;
    }

    class DownViewHolder {
        TextView mTv;
    }

}
