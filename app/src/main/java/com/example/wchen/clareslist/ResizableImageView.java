package com.example.wchen.clareslist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * http://stackoverflow.com/questions/5554682/android-imageview-adjusting-parents-height-and-fitting-width
 * Created by wchen on 9/30/15.
 * Class that extends imageview to preserve aspect ratio and have width fit the width of the device
 */

public class ResizableImageView extends ImageView {

    public ResizableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        Drawable d = getDrawable();
        if(d != null){
            // ceil not round - avoid thin vertical gaps along the left/right edges
            int width = MeasureSpec.getSize(widthMeasureSpec);
            // 400 is fixed height of the image, so that it is cropped
            setMeasuredDimension(width, 400);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}