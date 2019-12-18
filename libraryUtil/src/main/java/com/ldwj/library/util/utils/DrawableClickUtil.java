package com.ldwj.library.util.utils;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_UP;

/**
 * drawable的点击监听
 *
 */
public class DrawableClickUtil {
    /**
     * TextView四周drawable的序号。
     * 0 left,  1 top, 2 right, 3 bottom
     */
    private final int LEFT = 0;
    private final int TOP = 1;
    private final int RIGHT = 2;
    private final int BOTTOM = 3;
    private TextView mTextView;

    DrawableLeftListener mLeftListener;
    DrawableTopListener mTopListener;
    DrawableRightListener mRightListener;
    DrawableBottomListener mBottonListener;

    public DrawableClickUtil(TextView textView) {
        mTextView = textView;
        mTextView.setOnTouchListener(mOnTouchListener);
    }
    public void setDrawableLeftListener(DrawableLeftListener listener) {
        this.mLeftListener = listener;
    }

    public void setDrawableTopListener(DrawableTopListener listener){
        this.mTopListener = listener;
    }

    public void setDrawableRightListener(DrawableRightListener listener) {
        this.mRightListener = listener;
    }

    public void setDrawableBottonListener(DrawableBottomListener listener){
        this.mBottonListener = listener;
    }

    //左侧
    public interface DrawableLeftListener {
        public void onDrawableLeftClick(View view, Drawable drawableLeft) ;
    }
    //上
    public interface DrawableTopListener {
        public void onDrawableTopClick(View view, Drawable drawableTop) ;
    }
    //右侧
    public interface DrawableRightListener {
        public void onDrawableRightClick(View view, Drawable drawableRight) ;
    }
    //下
    public interface DrawableBottomListener {
        public void onDrawableBottonClick(View view, Drawable drawableBottom) ;
    }
    //点击
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case ACTION_DOWN:
                    Drawable[] drawables = mTextView.getCompoundDrawables();
                    Drawable drawableLeft = drawables[LEFT] ;
                    Drawable drawableTop = drawables[TOP] ;
                    Drawable drawableRight = drawables[RIGHT] ;
                    Drawable drawableBottom = drawables[BOTTOM] ;

                    int drawablePadding = mTextView.getCompoundDrawablePadding();
                    int  width = mTextView.getWidth();
                    int height = mTextView.getHeight();
                    int drawablePaddingLeft =  mTextView.getPaddingLeft();
                    int drawablePaddingTop =  mTextView.getPaddingTop();
                    int drawablePaddingRight =  mTextView.getPaddingRight();
                    int drawablePaddingBottom =  mTextView.getPaddingBottom();

                    /**
                     * drawableleft click listener
                     */
                    if (mLeftListener != null) {
                        if (drawableLeft != null
                                && event.getX() <= (drawableLeft.getBounds().width() + drawablePaddingLeft)
                                && event.getX() >= drawablePaddingLeft
                                && event.getY() <= height - drawablePaddingBottom - (drawableBottom != null?drawableBottom.getBounds().height():0) -drawablePadding
                                && event.getY() >= drawablePaddingTop + (drawableTop != null?drawableTop.getBounds().height():0) + drawablePadding) {
                            mLeftListener.onDrawableLeftClick(mTextView,drawableLeft);
                            return true ;
                        }
                    }
                    /**
                     * drawableTop click listener
                     */
                    if(mTopListener != null){
                        if(drawableTop != null
                                && event.getX() <= width - (drawableRight != null?drawableRight.getBounds().width():0) - drawablePaddingRight - drawablePadding
                                && event.getX() >= drawablePaddingLeft + (drawableLeft != null ?drawableLeft.getBounds().width():0) + drawablePadding
                                && event.getY() <= drawablePaddingTop + drawableTop.getBounds().height()
                                && event.getY() >= drawablePaddingTop){
                            mTopListener.onDrawableTopClick(mTextView,drawableTop);
                            return true;
                        }
                    }
                    /**
                     * drawableright click listener
                     */
                    if (mRightListener != null) {
                        if (drawableRight != null
                                && event.getX() <= width - drawablePaddingRight
                                && event.getX() >= width - drawablePaddingRight - drawableRight.getBounds().width()
                                && event.getY() <= height - drawablePaddingBottom - (drawableBottom != null ? drawableBottom.getBounds().height():0) -drawablePadding
                                && event.getY() >= drawablePaddingTop + (drawableTop != null ? drawableTop.getBounds().height():0) + drawablePadding) {

                            mRightListener.onDrawableRightClick(mTextView,drawableRight) ;
                            return true ;
                        }
                    }
                    /**
                     * drawableBottom click listener
                     */
                    if (mBottonListener != null) {
                        if (drawableBottom != null
                                && event.getX() <= width - (drawableRight != null ? drawableRight.getBounds().width():0) - drawablePaddingRight - drawablePadding
                                && event.getX() >= drawablePaddingLeft + (drawableLeft != null ? drawableLeft.getBounds().width():0) + drawablePadding
                                && event.getY() <= height - drawablePaddingBottom
                                && event.getY() >= height - (drawableBottom != null ? drawableBottom.getBounds().height():0) - drawablePaddingBottom) {
                            mBottonListener.onDrawableBottonClick(mTextView,drawableBottom);
                            return true ;
                        }
                    }
            }
            return false;
        }
    };
}
