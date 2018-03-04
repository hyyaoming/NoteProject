package com.tjerkw.slideexpandable.library;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Simple subclass of listview which does nothing more than wrap
 * any ListAdapter in a SlideExpandalbeListAdapter
 */
public class SlideExpandableListView extends ListView implements AbstractSlideExpandableListAdapter.OnItemExpandCollapseListener {
	private SlideExpandableListAdapter adapter;

	public SlideExpandableListView(Context context) {
		super(context);
	}

	public SlideExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SlideExpandableListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * Collapses the currently open view.
	 *
	 * @return true if a view was collapsed, false if there was no open view.
	 */
	public boolean collapse() {
		if (adapter != null) {
			return adapter.collapseLastOpen();
		}
		return false;
	}

	/**
	 * @param adapter            ListAdapter
	 * @param toggle_button_id   右键按钮的id
	 * @param expandable_view_id 菜单布局id
	 * @param arrow_id           箭头id
	 */
	public void setAdapter(ListAdapter adapter, int toggle_button_id, int expandable_view_id, int arrow_id) {
		this.adapter = new SlideExpandableListAdapter(adapter, toggle_button_id, expandable_view_id, arrow_id);
		this.adapter.setItemExpandCollapseListener(this);
		super.setAdapter(this.adapter);
	}

	/**
	 * set adapter
	 *
	 * @param adapter            ListAdapter
	 * @param toggle_button_id   右键按钮的id
	 * @param expandable_view_id 菜单布局id
	 * @param arrow_id           箭头id
	 * @param position           int
	 * @param context            Context
	 */
	public void setAdapter(ListAdapter adapter, int toggle_button_id, int expandable_view_id, int arrow_id, int position, Context context) {
		this.adapter = new SlideExpandableListAdapter(adapter, toggle_button_id, expandable_view_id, arrow_id, position, context);
		this.adapter.setItemExpandCollapseListener(this);
		super.setAdapter(this.adapter);
	}

	/**
	 * Registers a OnItemClickListener for this listview which will
	 * expand the item by default. Any other OnItemClickListener will be overriden.
	 * <p/>
	 * To undo call setOnItemClickListener(null)
	 * <p/>
	 * Important: This method call setOnItemClickListener, so the value will be reset
	 */
	public void enableExpandOnItemClick() {
		this.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				if (adapter != null) {
					adapter.getExpandToggleButton(view).performClick();
				}
			}
		});
	}


	@Override
	public Parcelable onSaveInstanceState() {
		if(adapter==null)
			return super.onSaveInstanceState();
		return adapter.onSaveInstanceState(super.onSaveInstanceState());
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		if (!(state instanceof AbstractSlideExpandableListAdapter.SavedState)) {
			super.onRestoreInstanceState(state);
			return;
		}

		AbstractSlideExpandableListAdapter.SavedState ss = (AbstractSlideExpandableListAdapter.SavedState) state;
		super.onRestoreInstanceState(ss.getSuperState());

		if (adapter != null) {
			adapter.onRestoreInstanceState(ss);
		}
	}


	@Override
	public void onExpand(View itemView, int position, View togglebuttonView) {
		if(expandCollapseListener!=null){
			expandCollapseListener.onExpand(position);
		}
	}

	@Override
	public void onCollapse(View itemView, int position, View togglebuttonView) {
		if(expandCollapseListener!=null){
			expandCollapseListener.onCollapse(position);
		}
	}

	private OnItemExpandCollapseListener expandCollapseListener;

	public void setItemExpandCollapseListener(
			OnItemExpandCollapseListener listener) {
		expandCollapseListener = listener;
	}
	public interface OnItemExpandCollapseListener {
		void onExpand(int position);

		void onCollapse(int position);

	}
}
