package project.recyclerview.lym.org.recyclerviewlibrary.listener;

import android.view.View;


import project.recyclerview.lym.org.recyclerviewlibrary.adapter.BaseFastAdapter;

/**
 * create by: allen on 16/8/3.
 */

public abstract class OnItemLongClickListener extends SimpleClickListener {




    @Override
    public void onItemClick(BaseFastAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemLongClick(BaseFastAdapter adapter, View view, int position) {
        onSimpleItemLongClick(adapter,  view,  position);
    }

    @Override
    public void onItemChildClick(BaseFastAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseFastAdapter adapter, View view, int position) {
    }
    public abstract void onSimpleItemLongClick(BaseFastAdapter adapter, View view, int position);
}
