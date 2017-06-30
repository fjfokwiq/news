package com.example.fjfokwiq.news.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by fjfokwiq on 2017/6/3.
 */

public class CircleImageView extends ImageView{
    private Paint mPaint;
    private BitmapShader shader;
    private Matrix matrix;
    private int mRadius;
    private int mWidth;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        matrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = Math.min(getMeasuredHeight(), getMeasuredWidth());
        mRadius = mWidth / 2;
        setMeasuredDimension(mWidth, mWidth);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable=getDrawable();
        if (drawable == null) {
            return;
        }

        Bitmap bitmap=obtainBitmap(drawable);

        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale=1.0f;
        int bitmapSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
        scale=mWidth*1.0f/bitmapSize;
        matrix.setScale(scale,scale);
        shader.setLocalMatrix(matrix);
        mPaint.setShader(shader);
        canvas.save();
        canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);
        canvas.restore();

    }

    private Bitmap obtainBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int w=drawable.getIntrinsicHeight();
        int h=drawable.getIntrinsicHeight();
        Bitmap bit=Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bit);
        drawable.setBounds(0,0,w,h);
        drawable.draw(canvas);

        return bit;
    }
}
