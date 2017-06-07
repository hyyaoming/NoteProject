package note.lym.org.noteproject.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import note.lym.org.noteproject.R;
import note.lym.org.noteproject.utils.ToastUtils;
import note.lym.org.noteproject.view.basepager.BasePagerHolder;
import note.lym.org.noteproject.view.basepager.BaseViewPager;
import note.lym.org.noteproject.view.basepager.PagerHolderCreator;

/**
 * 测试类
 *
 * @author yaoming.li
 * @since 2017-06-07 10:33
 */
public class TestActivity extends AppCompatActivity {


    private BaseViewPager mPager;
    private int[] defaultImage = new int[]{
            R.drawable.ic_default_1, R.drawable.ic_default_2, R.drawable.ic_default_3, R.drawable.ic_default_4, R.drawable.ic_default_5};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mPager = (BaseViewPager) findViewById(R.id.base_vp);
        List<Integer> data = new ArrayList<>();
        for (int x = 0; x < defaultImage.length; x++) {
            data.add(defaultImage[x]);
        }
        List<String> title = new ArrayList<>();
        title.add("廖婿璨");
        title.add("黄海军");
        title.add("李耀明");
        title.add("贺宏昌");
        title.add("谢凯");
        mPager.setImages(data)
                .setTitle(title)
                .setStyle(BaseViewPager.PagerStyle.SINGLE_INDICATOR)
                .begin(new PagerHolderCreator() {
                    @Override
                    public BasePagerHolder createViewHolder(Context context) {
                        return new ImageHolder();
                    }
                });
    }

    class ImageHolder implements BasePagerHolder<Integer> {
        private ImageView mIv;
        private View view;

        @Override
        public View onCreateView(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.test, null);
            mIv = (ImageView) view.findViewById(R.id.iv);
            return view;
        }

        @Override
        public void onBindView(Context context, final int position, Integer integer) {
            mIv.setImageResource(integer);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showToast("你点击了第" + position + "个");
                }
            });
        }
    }

}
