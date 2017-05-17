package com.tjerkw.slideexpandable.library;

import android.content.Context;
import android.view.View;
import android.widget.ListAdapter;

/**
 * ListAdapter that adds sliding functionality to a list.
 * Uses R.id.expandalbe_toggle_button and R.id.expandable id's if no
 * ids are given in the contructor.
 *
 * @author tjerk
 * @date 6/13/12 8:04 AM
 */
public class SlideExpandableListAdapter extends AbstractSlideExpandableListAdapter {
	private int toggle_button_id;
	private int expandable_view_id;
	/**
	 * 箭头
	 */
	private int expandable_arrow_id;
	private Context mContext;

	/**
	 * constructor
	 *
	 * @param wrapped            ListAdapter
	 * @param toggle_button_id   int
	 * @param expandable_view_id int
	 * @param arrow_id           int
	 */
	public SlideExpandableListAdapter(ListAdapter wrapped, int toggle_button_id, int expandable_view_id, int arrow_id) {
		this(wrapped, toggle_button_id, expandable_view_id, arrow_id, -1, null);
	}

	/**
	 * constructor
	 *
	 * @param wrapped            ListAdapter
	 * @param toggle_button_id   int
	 * @param expandable_view_id int
	 * @param arrow_id           int
	 * @param position           int
	 * @param context            Context
	 */
	public SlideExpandableListAdapter(ListAdapter wrapped, int toggle_button_id, int expandable_view_id, int arrow_id, int position, Context context) {
		super(wrapped, position);
		if (toggle_button_id < 0) {
			throw new IllegalArgumentException("toggleButtonId can NOT be negative");
		}
		if (expandable_view_id < 0) {
			throw new IllegalArgumentException("expandableViewId can NOT be negative");
		}
		if (arrow_id < 0) {
			throw new IllegalArgumentException("arrowId can NOT be negative");
		}

		this.toggle_button_id = toggle_button_id;
		this.expandable_view_id = expandable_view_id;
		this.expandable_arrow_id = arrow_id;
		this.mContext = context;
	}

	@Override
	public View getExpandToggleButton(View parent) {
		return toggle_button_id > 0 ? parent.findViewById(toggle_button_id) : null;
	}

	@Override
	public View getExpandableView(View parent) {
		return expandable_view_id > 0 ? parent.findViewById(expandable_view_id) : null;
	}

	@Override
	public View getArrowId(View parent) {
		return expandable_arrow_id > 0 ? parent.findViewById(expandable_arrow_id) : null;
	}

	@Override
	protected Context getContext() {
		return mContext;
	}
}
