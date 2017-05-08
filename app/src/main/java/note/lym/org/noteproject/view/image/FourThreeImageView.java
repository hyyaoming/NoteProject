package note.lym.org.noteproject.view.image;

import android.content.Context;
import android.util.AttributeSet;

/**
 *
 * @author yaoming.li
 * @since 2017-05-04 16:08
 */
public class FourThreeImageView extends ForegroundImageView {
    public FourThreeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FourThreeImageView(Context context,AttributeSet attrs,int defStyle){
        super(context, attrs,defStyle);

    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int fourThreeHeight = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthSpec) * 3 / 4,
                MeasureSpec.EXACTLY);
        super.onMeasure(widthSpec, fourThreeHeight);
    }

}
