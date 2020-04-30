package com.nihal.android.popularmovies;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

import static com.nihal.android.popularmovies.utilities.Constant.THREE;
import static com.nihal.android.popularmovies.utilities.Constant.TWO;

/**
 * The ThreeTwoImageView class is responsible for making ImageView 3:2 aspect ratio.
 * The ThreeTwoImageView is used for movie backdrop image in the activity_detail.xml.
 */
public class ThreeTwoImageView extends AppCompatImageView {

    /**
     * Creates ThreeTwoImageView
     *
     * @param context Used to talk to the UI and app resources
     */
    public ThreeTwoImageView(Context context) {
        super(context);
    }

    public ThreeTwoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ThreeTwoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * This method measures the view and its content to determine the measured width and the measured
     * height, which will make 3:2 aspect ratio.
     *
     * @param widthMeasureSpec horizontal space requirements as imposed by the parent
     * @param heightMeasureSpec vertical space requirements as imposed by th parent
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int threeTwoHeight = MeasureSpec.getSize(widthMeasureSpec) * TWO / THREE;
        int threeTwoHeightSpec =
                MeasureSpec.makeMeasureSpec(threeTwoHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, threeTwoHeightSpec);
    }
}
