package note.lym.org.noteproject.view.basepager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import note.lym.org.noteproject.R;

/**
 * 通用的一个ViewPager，借鉴Banner的一些思想，以及将PagerAdapter进行封装抽象一下，这样更人性化。
 *
 * @author yaoming.li
 * @since 2017-06-06 11:19
 */
public class BaseViewPager<T> extends FrameLayout implements ViewPager.OnPageChangeListener {
    private static final String TAG = "BaseViewPager";
    @BindView(R.id.base_viewpager)
    ViewPager mPager;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.tv_bg_indicator_number)
    TextView mTvBgNumber;
    @BindView(R.id.tv_titles)
    TextView mTvTitle;
    @BindView(R.id.layout_indicator)
    LinearLayout mIndicator;
    @BindView(R.id.layout_num_indicator)
    LinearLayout mLayoutPoint;
    @BindView(R.id.layout_indicator_title_number)
    LinearLayout mBgIndicator;
    /**
     * viewpager holder creator
     */
    private PagerHolderCreator mCreate;
    /**
     * Viewpager数据
     */
    private List<T> mData;
    /**
     * 标题数据
     */
    private List<String> mTitles;
    /**
     * change listener
     */
    private ViewPager.OnPageChangeListener mPageChangeListener;
    /**
     * 记录viewpager上个角标的位置
     */
    private int mLastPosition = 1;
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
    private int mDelayedTime = 2400;
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
    /**
     * viewpager当前的角标
     */
    private int mCurrentItem;

    /**
     * 开启轮询
     */
    public void startAutoPager() {
        mHandler.removeCallbacks(mTask);
        mHandler.postDelayed(mTask, mDelayedTime);
    }

    /**
     * 设置ViewPager滑动监听
     *
     * @param onPageChangeListener 滑动监听
     */
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mPageChangeListener = onPageChangeListener;
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
        mTitles = new ArrayList<>();
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

    /**
     * 各种初始化工作
     *
     * @param create viewpager的holder建造者
     */
    public void begin(PagerHolderCreator create) {
        this.mCreate = create;
        setPagerStyle();
        initImageOrTitles();
        setData();
    }

    /**
     * 设置标题数据
     *
     * @param data 标题数据源
     * @return 返回当前类
     */
    public BaseViewPager setTitle(List<String> data) {
        this.mTitles = data;
        return this;
    }

    private void setData() {
        mCurrentItem = 1;
        CommonPagerAdapter adapter = new CommonPagerAdapter(mData, true, mCreate);
        mPager.addOnPageChangeListener(this);
        mPager.setAdapter(adapter);
        mPager.setCurrentItem(1);
        mPager.setOnTouchListener(touch);
        if (isAutoPlay && count > 1) {
            startAutoPager();
        }
    }

    /**
     * 根据样式展示具体的UI
     */
    private void initImageOrTitles() {
        if (mPagerStyle == PagerStyle.SINGLE_INDICATOR || mPagerStyle == PagerStyle.BG_TITLE_AND_INDICATOR) {
            createIndicator();
        } else if (mPagerStyle == PagerStyle.SINGLE_NUMBER) {
            mTvNumber.setText(1 + "/" + count);
        } else if (mPagerStyle == PagerStyle.BG_TITLE_AND_NUMBER) {
            mTvBgNumber.setText(1 + "/" + count);
        }
    }

    /**
     * 创建小圆点指示器
     */
    private void createIndicator() {
        mList.clear();
        mIndicator.removeAllViews();
        mLayoutPoint.removeAllViews();
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
                if (mPagerStyle == PagerStyle.SINGLE_INDICATOR) {
                    mIndicator.addView(iv, params);
                } else if (mPagerStyle == PagerStyle.BG_TITLE_AND_INDICATOR) {
                    mLayoutPoint.addView(iv, params);
                }
            }
        }
    }

    /**
     * 设置圆点指示器居中方向
     * @param gravity   具体居中方向
     * @return  当前类
     */
    public BaseViewPager setIndicatorGravity(int gravity){
        mIndicator.setGravity(gravity);
        return this;
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
        ButterKnife.bind(view);
        changePagerSkipSpeed();
    }

    /**
     * 改变ViewPager换页速率
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
        if (PagerStyle.SINGLE_INDICATOR == mPagerStyle || PagerStyle.BG_TITLE_AND_INDICATOR == mPagerStyle) {
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
        if (PagerStyle.SINGLE_NUMBER == mPagerStyle) {
            mTvNumber.setText(position + "/" + count);
        } else if (PagerStyle.BG_SINGLE_TITLE == mPagerStyle || mPagerStyle == PagerStyle.BG_TITLE_AND_INDICATOR) {
            mTvTitle.setText(mTitles.get(position - 1));
        } else if (mPagerStyle == PagerStyle.BG_TITLE_AND_NUMBER) {
            mTvTitle.setText(mTitles.get(position - 1));
            mTvBgNumber.setText(position + "/" + count);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mPageChangeListener != null) {
            mPageChangeListener.onPageScrollStateChanged(state);
        }
        mCurrentItem = mPager.getCurrentItem();
        switch (state) {
            case 0:
                if (mCurrentItem == 0) {
                    mPager.setCurrentItem(count, false);
                } else if (mCurrentItem == count + 1) {
                    mPager.setCurrentItem(1, false);
                }
                break;
            case 1:
                if (mCurrentItem == count + 1) {
                    mPager.setCurrentItem(1, false);
                } else if (mCurrentItem == 0) {
                    mPager.setCurrentItem(count, false);
                }
                break;
            case 2:
                break;
        }
    }

    /**
     * 设置viewpager的换页动画
     *
     * @param transformer 具体的动画
     * @param flag        是否执行动画
     * @return 返回当前类
     */
    public BaseViewPager setPageTransformer(ViewPager.PageTransformer transformer, boolean flag) {
        mPager.setPageTransformer(flag, transformer);
        return this;
    }

    /**
     * 设置指示器的样式
     *
     * @param bannerStyle 具体的样式
     * @return 返回当前类
     */
    public BaseViewPager setStyle(int bannerStyle) {
        this.mPagerStyle = bannerStyle;
        return this;
    }

    private void setPagerStyle() {
        switch (mPagerStyle) {
            case PagerStyle.SINGLE_INDICATOR:
                mIndicator.setVisibility(VISIBLE);
                break;
            case PagerStyle.SINGLE_NUMBER:
                mTvNumber.setVisibility(VISIBLE);
                break;
            case PagerStyle.BG_SINGLE_TITLE:
                setTextStyle();
                break;
            case PagerStyle.BG_TITLE_AND_NUMBER:
                mTvBgNumber.setVisibility(VISIBLE);
                setTextStyle();
                break;
            case PagerStyle.BG_TITLE_AND_INDICATOR:
                mLayoutPoint.setVisibility(VISIBLE);
                setTextStyle();
                break;
            default:
                break;

        }
    }

    private void setTextStyle() {
        if (mTitles.isEmpty() || mTitles.size() != mData.size()) {
            throw new RuntimeException("title size with image size is different ?");
        }
        mBgIndicator.setVisibility(VISIBLE);
        mTvTitle.setVisibility(VISIBLE);
        mTvTitle.setText(mTitles.get(0));
    }

    public interface PagerStyle {
        int SINGLE_INDICATOR = 0x001;
        int SINGLE_NUMBER = 0x002;
        int BG_SINGLE_TITLE = 0x003;
        int BG_TITLE_AND_INDICATOR = 0x004;
        int BG_TITLE_AND_NUMBER = 0x005;
        int NO_INDICATOR = 0x006;
    }

}
