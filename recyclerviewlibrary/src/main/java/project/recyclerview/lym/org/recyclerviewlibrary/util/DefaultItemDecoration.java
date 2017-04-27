package project.recyclerview.lym.org.recyclerviewlibrary.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Desp.
 *
 * @author yaoming.li
 * @version 8.1.0
 * @since 2017-03-16 16:33
 */
public class DefaultItemDecoration extends RecyclerView.ItemDecoration {

    //我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
    public static final int[] ATRRS  = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDrawable;
    private int mOrientation;
    private static final int H = LinearLayoutManager.HORIZONTAL;
    private static final int V = LinearLayoutManager.VERTICAL;

    /**
     * 构造方法一般可以在这里做一些初始化的操作，设置画笔，获取属性之类的
     */
    public DefaultItemDecoration(Context context, int orientation) {
        final TypedArray ta = context.obtainStyledAttributes(ATRRS);
        this.mDrawable = ta.getDrawable(0);
        ta.recycle();
        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if (orientation != H && orientation != V) {
            throw new IllegalArgumentException("invalid orientation");
        }
        this.mOrientation = orientation;
    }

    /**
     * 指定item之间的间距(就是指定分割线的宽度)   回调顺序 1
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == H){
            outRect.set(0,0,0,mDrawable.getIntrinsicWidth());
        }else{
            outRect.set(0,0,mDrawable.getIntrinsicWidth(),0);
        }
    }

    /**
     * 在item 绘制之前调用(就是绘制在 item 的底层)  回调顺序 2
     * 一般分割线在这里绘制
     * 看到canvas,对自定义控件有一定了解的话,就能想到为什么说给RecyclerView设置分割线更灵活了
     *
     * @param c      Canvas to draw into
     * @param parent RecyclerView this ItemDecoration is drawing into
     * @param state  The current state of RecyclerView
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == H) {
            drawH(c,parent);
        } else {
            drawV(c,parent);
        }

    }

    private void drawH(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for(int x = 0; x < childCount ; x++){
            View child = parent.getChildAt(x);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() +  params.bottomMargin;
            int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left,top,right,bottom);
            mDrawable.draw(c);
        }
    }

    private void drawV(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for(int x = 0 ; x < childCount; x++){
            View child = parent.getChildAt(x);
            RecyclerView.LayoutParams parms = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + parms.rightMargin;
            int right = left + mDrawable.getIntrinsicWidth();
            mDrawable.setBounds(left,top,right,bottom);
            mDrawable.draw(c);
        }
        }


    /**
     * 在item 绘制之后调用(就是绘制在 item 的上层)  回调顺序 3
     * 也可以在这里绘制分割线,和上面的方法 二选一
     *
     * @param c      Canvas to draw into
     * @param parent RecyclerView this ItemDecoration is drawing into
     * @param state  The current state of RecyclerView
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }


}
