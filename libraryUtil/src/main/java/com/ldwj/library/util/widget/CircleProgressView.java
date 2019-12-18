package com.ldwj.library.util.widget;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.ldwj.library.util.R;

/**
 * //开锁执行动画效果
 circle_progress.startAnimProgress( 1200);
 //监听进度条进度
 circle_progress.setOnAnimProgressListener(new CircleProgressView.OnAnimProgressListener() {
@Override
public void valueUpdate(int progress) {
tv_progress.setText(String.valueOf(progress));
}
});
 //销毁
 if (circle_progress != null) {
 circle_progress.destroy();
 }
 */
public class CircleProgressView extends View {
    private float mCurrent = 75f;//当前进度
    private float mMax = 100f;//最大进度
    private Paint mBgPaint;//背景弧线paint
    private Paint mProgressPaint;//进度Paint
    private float mProgressWidth;//进度条宽度
    private int mProgressColor = Color.RED;//进度条颜色
    private int locationStart;//起始位置
    private float startAngle;//开始角度
    private ValueAnimator mAnimator;
    private int left =1;
    private int top = 2;
    private int right = 3;
    private int bottom =4;


    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        locationStart = typedArray.getInt(R.styleable.CircleProgressView_location_start, 2);
        mProgressWidth = typedArray.getDimension(R.styleable.CircleProgressView_progress_width, dp2px(context, 4));
        mProgressColor = typedArray.getColor(R.styleable.CircleProgressView_progress_color, mProgressColor);
        typedArray.recycle();

        //背景圆弧
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStrokeWidth(mProgressWidth);
        mBgPaint.setStyle(Paint.Style.STROKE);
        mBgPaint.setColor(Color.parseColor("#dddddd"));
        mBgPaint.setStrokeCap(Paint.Cap.ROUND);

        //进度圆弧
        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(mProgressWidth);
        mProgressPaint.setColor(mProgressColor);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);

        //进度条起始角度
        if (locationStart == left) {//左
            startAngle = -180;
        } else if (locationStart == top) {//上
            startAngle = -90;
        } else if (locationStart == right) {//右
            startAngle = 0;
        } else if (locationStart == bottom) {//下
            startAngle = 90;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width < height ? width : height;
        setMeasuredDimension(size, size);
    }

    /**
     * oval // 绘制范围
     * startAngle // 开始角度
     * sweepAngle // 扫过角度
     * useCenter // 是否使用中心
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //绘制背景圆弧
        RectF rectF = new RectF(mProgressWidth / 2, mProgressWidth / 2, getWidth() - mProgressWidth / 2, getHeight() - mProgressWidth / 2);
        canvas.drawArc(rectF, 0, 360, false, mBgPaint);
         //绘制当前进度
        float sweepAngle =  (  360 * mCurrent / mMax);
        canvas.drawArc(rectF, startAngle, sweepAngle, false, mProgressPaint);
    }

    public float getCurrent() {
        return mCurrent;
    }

    /**
     * 设置进度
     *
     * @param current
     */
    public void setCurrent(float current) {
        mCurrent = current;
        invalidate();
    }
    /**
     * 设置进度
     *
     * @param max
     */
    public void setmMax(float max) {
        this.mMax = max;
        invalidate();
    }
    private float tCurrent = -1;

    /**
     * 动画效果
     *
     * @param duration 动画时间
     */
    public void startAnimProgress(int duration) {
        mAnimator = ValueAnimator.ofFloat(0, mCurrent);
        mAnimator.setDuration(duration);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float current = (float) animation.getAnimatedValue();
                if (tCurrent != current) {
                    tCurrent = current;
                    setCurrent(current);
                    if (mOnAnimProgressListener != null) {
                        mOnAnimProgressListener.valueUpdate(current);
                    }
                }
            }
        });
        mAnimator.start();
    }

    public interface OnAnimProgressListener {
        void valueUpdate(float progress);
    }

    private OnAnimProgressListener mOnAnimProgressListener;

    /**
     * 监听进度条进度
     *
     * @param onAnimProgressListener
     */
    public void setOnAnimProgressListener(OnAnimProgressListener onAnimProgressListener) {
        mOnAnimProgressListener = onAnimProgressListener;
    }

    public void destroy() {
        if (mAnimator != null) {
            mAnimator.cancel();
        }
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
