package project.recyclerview.lym.org.recyclerviewlibrary.listener;

import android.view.View;

import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;

/**
 * Created by AllenCoder on 2016/8/03.
 * A convenience class to extend when you only want to OnItemChildClickListener for a subset
 * of all the SimpleClickListener. This implements all methods in the
 * {@link SimpleClickListener}
 **/

public abstract class OnItemChildClickListener extends SimpleClickListener {

    @Override
    public void onItemClick(BaseFastAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemLongClick(BaseFastAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseFastAdapter adapter, View view, int position) {
        onSimpleItemChildClick(adapter, view, position);
    }

    @Override
    public void onItemChildLongClick(BaseFastAdapter adapter, View view, int position) {

    }

    public  abstract void onSimpleItemChildClick(BaseFastAdapter adapter, View view, int position);

}
