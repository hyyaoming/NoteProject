package note.lym.org.noteproject.view.basepager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import note.lym.org.noteproject.R;

/**
 * 通用的一个ViewPager
 *
 * @author yaoming.li
 * @since 2017-06-06 11:19
 */
public class BaseViewPager<T> extends FrameLayout implements ViewPager.OnPageChangeListener {
    private static final String TAG = "BaseViewPager";
    private PagerHolderCreator mCreate;
    private List<T> mData;
    private ViewPager mPager;
    private TextView mTvIndicator;
    private ViewPager.OnPageChangeListener mPageChangeListener;
    private int mLastPosition = 1;
    private ViewPagerAdapter mAdapter;

    /**
     * 设置ViewPager滑动监听
     *
     * @param onPageChangeListener 滑动监听
     */
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mPageChangeListener = onPageChangeListener;
    }

    /**
     * 装小圆点指示器
     */
    private List<ImageView> mList;
    /**
     * 指示器默认间距
     */
    private static final int PADDING_SIZE = 5;
    /**
     * 数据大小
     */
    private int count = 0;
    /**
     * 是否开启轮播，默认开启
     */
    private boolean isAutoPlay = true;
    /**
     * 装载小圆点的容器
     */
    private LinearLayout mIndicator;
    /**
     * 指示器默认计算大小
     */
    private static final int DEFAULT_INDICATOR_SIZE = 80;
    /**
     * 选中小圆点的背景色
     */
    private int mSelectColor = R.drawable.shape_select_bg;
    /**
     * 默认小圆点的背景色
     */
    private int mUnSelectColor = R.drawable.shape_un_select_bg;
    /**
     * 指示器宽度
     */
    private int mIndicatorWidth;
    /**
     * 指示器高度
     */
    private int mIndicatorHeight;
    /**
     * 指示器间距
     */
    private int mIndicatorMargin;
    /**
     * 页面多久切换一次，默认两秒
     */
    private int mDelayedTime = 2000;
    /**
     * Viewpager切换速率
     */
    private int mChangeTime = 1000;
    /**
     * 指示器大小
     */
    private int mIndicatorSize;
    /**
     * 加入弱引用队列的Handler
     */
    private WeakHandler mHandler = new WeakHandler();
    /**
     * 指示器样式，默认为只有圆点指示器
     */
    private int mPagerStyle = PagerStyle.SINGLE_INDICATOR;
    private int mCurrentItem;

    /**
     * 开启轮询
     */
    public void startAutoPager() {
        mHandler.removeCallbacks(mTask);
        mHandler.postDelayed(mTask, mDelayedTime);
    }

    /**
     * 停止轮询
     */
    public void stopAutoPager() {
        mHandler.removeCallbacks(mTask);
    }

    /**
     * 设置是否开启轮询
     *
     * @param autoPlay true 开启 false 关闭
     * @return 返回当前类
     */
    public BaseViewPager setAutoPlay(boolean autoPlay) {
        this.isAutoPlay = autoPlay;
        return this;
    }

    /**
     * 轮询任务
     */
    private final Runnable mTask = new Runnable() {
        @Override
        public void run() {
            if (isAutoPlay && count > 1) {
                mCurrentItem = mCurrentItem % (count + 1) + 1;
                if (mCurrentItem == 1) {
                    mPager.setCurrentItem(mCurrentItem, false);
                    mHandler.post(mTask);
                } else {
                    mPager.setCurrentItem(mCurrentItem);
                    mHandler.postDelayed(mTask, mDelayedTime);
                }
            }
        }
    };

    public BaseViewPager(Context context) {
        this(context, null);
    }

    public BaseViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mList = new ArrayList<>();
        mData = new ArrayList<>();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        mIndicatorSize = dm.widthPixels / DEFAULT_INDICATOR_SIZE;
        getAttrArray(context, attrs);
        init(context);
    }

    /**
     * 获取自定义属性
     *
     * @param context 上下文
     * @param attrs   自定义属性要从该对象中获取
     */
    private void getAttrArray(Context context, AttributeSet attrs) {
        mList.clear();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BaseViewPager);
        mIndicatorWidth = array.getDimensionPixelOffset(R.styleable.BaseViewPager_indicator_width, mIndicatorSize);
        mIndicatorHeight = array.getDimensionPixelOffset(R.styleable.BaseViewPager_indicator_height, mIndicatorSize);
        mIndicatorMargin = array.getDimensionPixelOffset(R.styleable.BaseViewPager_indicator_margin, PADDING_SIZE);
        mUnSelectColor = array.getResourceId(R.styleable.BaseViewPager_indicator_un_select_color, R.drawable.shape_un_select_bg);
        mSelectColor = array.getResourceId(R.styleable.BaseViewPager_indicator_select_color, R.drawable.shape_select_bg);
        array.recycle();
    }

    /**
     * 设置数据
     *
     * @param data 数据源
     * @return 返回当前类
     */
    public BaseViewPager setImages(List<T> data) {
        this.mData = data;
        this.count = data.size();
        return this;
    }

    public void begin(PagerHolderCreator create) {
        this.mCreate = create;
        setPagerStyle();
        initImageOrTitles();
        setData();
    }

    private void setData() {
        mCurrentItem = 1;
        if (mAdapter == null) {
            mAdapter = new ViewPagerAdapter();
            mPager.addOnPageChangeListener(this);
        }
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(1);
        mPager.setOnTouchListener(touch);
        if (isAutoPlay && count > 1) {
            startAutoPager();
        }
    }

    private void initImageOrTitles() {
        if (mPagerStyle == PagerStyle.SINGLE_INDICATOR || mPagerStyle == PagerStyle.INDICATOR_AND_TITLE) {
            createIndicator();
        } else if (mPagerStyle == PagerStyle.SINGLE_TITLE) {
            mTvIndicator.setText(1 + "/" + count);
        }
    }

    private void createIndicator() {
        mList.clear();
        mIndicator.removeAllViews();
        if (count > 1) {
            for (int x = 0; x < count; x++) {
                ImageView iv = new ImageView(getContext());
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mIndicatorWidth, mIndicatorHeight);
                params.leftMargin = mIndicatorMargin;
                params.rightMargin = mIndicatorMargin;
                if (x == 0) {
                    iv.setImageResource(mSelectColor);
                } else {
                    iv.setImageResource(mUnSelectColor);
                }
                mList.add(iv);
                mIndicator.addView(iv, params);
            }
        }

    }

    /**
     * 在开启轮询时，需处理一下触摸事件。
     */
    private View.OnTouchListener touch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    stopAutoPager();
                    break;
                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    startAutoPager();
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    /**
     * 初始化控件
     *
     * @param context 上下文
     */
    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_base_pager, this, true);
        mPager = (ViewPager) view.findViewById(R.id.base_viewpager);
        mTvIndicator = (TextView) view.findViewById(R.id.num_indicator);
        mIndicator = (LinearLayout) view.findViewById(R.id.layout_indicator);
        changePagerSkipSpeed();
    }

    /**
     * 改变ViewPager换页速
     */
    private void changePagerSkipSpeed() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroll = new FixedSpeedScroller(getContext());
            scroll.setDuration(mChangeTime);
            field.set(mPager, scroll);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mPageChangeListener != null) {
            mPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (mPageChangeListener != null) {
            mPageChangeListener.onPageSelected(position);
        }
        if (PagerStyle.SINGLE_INDICATOR == mPagerStyle || PagerStyle.INDICATOR_AND_TITLE == mPagerStyle) {
            mList.get((mLastPosition - 1 + count) % count).setImageResource(mUnSelectColor);
            mList.get((position - 1 + count) % count).setImageResource(mSelectColor);
            mLastPosition = position;
        }
        if (position == 0) {
            position = count;
        }
        if (position > count) {
            position = 1;
        }
        if (PagerStyle.SINGLE_TITLE == mPagerStyle) {
            mTvIndicator.setText(position + "/" + count);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mPageChangeListener != null) {
            mPageChangeListener.onPageScrollStateChanged(state);
        }
        mCurrentItem = mPager.getCurrentItem();
        switch (state) {
            case 0://No operation
                if (mCurrentItem == 0) {
                    mPager.setCurrentItem(count, false);
                } else if (mCurrentItem == count + 1) {
                    mPager.setCurrentItem(1, false);
                }
                break;
            case 1://start Sliding
                if (mCurrentItem == count + 1) {
                    mPager.setCurrentItem(1, false);
                } else if (mCurrentItem == 0) {
                    mPager.setCurrentItem(count, false);
                }
                break;
            case 2://end Sliding
                break;
        }
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mData == null ? 0 : mData.size() + 2;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getView(container, position, null);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        private View getView(ViewGroup parent, int position, View view) {
            BasePagerHolder holder;
            if (null == view) {
                holder = mCreate.createViewHolder(parent.getContext());
                view = holder.onCreateView(parent.getContext());
                view.setTag(holder);
            } else {
                holder = (BasePagerHolder) view.getTag();
            }
            if (null != holder && mData != null && mData.size() > 0) {
                holder.onBindView(parent.getContext(), toRealPosition(position),mData.get(toRealPosition(position)));
            }
            return view;
        }
    }

    public BaseViewPager setPageTransformer(ViewPager.PageTransformer transformer,boolean flag){
        mPager.setPageTransformer(flag,transformer);
        return this;
    }

    /**
     * 返回真实的位置
     *
     * @param position  角标
     * @return 下标从0开始
     */
    public int toRealPosition(int position) {
        int realPosition = (position - 1) % count;
        if (realPosition < 0){
            realPosition += count;
        }
        return realPosition;
    }

    public BaseViewPager<T> setStyle(int bannerStyle) {
        this.mPagerStyle = bannerStyle;
        return this;
    }

    private void setPagerStyle() {
        switch (mPagerStyle) {
            case PagerStyle.SINGLE_INDICATOR:
                mIndicator.setVisibility(VISIBLE);
                break;
            case PagerStyle.SINGLE_TITLE:
                mTvIndicator.setVisibility(VISIBLE);
                break;
            default:
                break;

        }
    }

    public interface PagerStyle {
        int SINGLE_INDICATOR = 1;
        int INDICATOR_AND_TITLE = 2;
        int NO_INDICATOR = 0;
        int SINGLE_TITLE = 3;
    }

}
