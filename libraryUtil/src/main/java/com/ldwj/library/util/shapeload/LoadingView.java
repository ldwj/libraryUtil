package com.ldwj.library.util.shapeload;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldwj.library.util.R;
/**
 * android:id="@+id/loadView" android:layout_width="wrap_content"
 * android:layout_height="wrap_content" android:layout_centerInParent="true"
 * app:loadingText="加载中..." 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishuai
 * @see LoadingView
 * @since
 */
public class LoadingView extends FrameLayout
{
    private static final int ANIMATION_DURATION = 500;

    private static float mDistance = 200;

    private ShapeLoadingView mShapeLoadingView;

    private ImageView mIndicationIm;

    private TextView mLoadTextView;

    private int mTextAppearance;

    private String mLoadText;

    public LoadingView(Context context)
    {
        this(context, null);

    }

    public LoadingView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);

    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
        mLoadText = typedArray.getString(R.styleable.LoadingView_loadingText);
        mTextAppearance = typedArray.getResourceId(R.styleable.LoadingView_loadingTextAppearance,
            -1);
        typedArray.recycle();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.load_view, null);

        mDistance = dip2px(54f);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.gravity = Gravity.CENTER;

        mShapeLoadingView = (ShapeLoadingView)view.findViewById(R.id.shapeLoadingView);

        mIndicationIm = (ImageView)view.findViewById(R.id.indication);
        mLoadTextView = (TextView)view.findViewById(R.id.promptTV);

        if (mTextAppearance != -1)
        {
            mLoadTextView.setTextAppearance(getContext(), mTextAppearance);
        }
        setLoadingText(mLoadText);

        addView(view, layoutParams);

        this.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                freeFall();
            }
        }, 900);
    }

    // @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    // public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    // super(context, attrs, defStyleAttr, defStyleRes);
    // init(context, attrs);
    // }

    public int dip2px(float dipValue)
    {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

    }

    public void setLoadingText(CharSequence loadingText)
    {
        if (mLoadTextView == null)
        {
            return;
        }

        if (TextUtils.isEmpty(loadingText))
        {
            mLoadTextView.setVisibility(GONE);
        }
        else
        {
            mLoadTextView.setVisibility(VISIBLE);
        }

        mLoadTextView.setText(loadingText);
    }

    /**
     * 上抛
     */
    public void upThrow()
    {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mShapeLoadingView, "translationY",
            mDistance, 0);
        ObjectAnimator scaleIndication = ObjectAnimator.ofFloat(mIndicationIm, "scaleX", 0.2f, 1);

        ObjectAnimator objectAnimator1 = null;
        switch (mShapeLoadingView.getShape())
        {
            case SHAPE_RECT:

                objectAnimator1 = ObjectAnimator.ofFloat(mShapeLoadingView, "rotation", 0, -120);

                break;
            case SHAPE_CIRCLE:
                objectAnimator1 = ObjectAnimator.ofFloat(mShapeLoadingView, "rotation", 0, 180);

                break;
            case SHAPE_TRIANGLE:

                objectAnimator1 = ObjectAnimator.ofFloat(mShapeLoadingView, "rotation", 0, 180);

                break;
        }

        objectAnimator.setDuration(ANIMATION_DURATION);
        objectAnimator1.setDuration(ANIMATION_DURATION);
        objectAnimator.setInterpolator(new DecelerateInterpolator(factor));
        objectAnimator1.setInterpolator(new DecelerateInterpolator(factor));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(ANIMATION_DURATION);
        animatorSet.playTogether(objectAnimator, objectAnimator1, scaleIndication);

        animatorSet.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation)
            {

            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                freeFall();

            }

            @Override
            public void onAnimationCancel(Animator animation)
            {

            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {

            }
        });
        animatorSet.start();

    }

    public float factor = 1.2f;

    /**
     * 下落
     */
    public void freeFall()
    {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mShapeLoadingView, "translationY",
            0, mDistance);
        ObjectAnimator scaleIndication = ObjectAnimator.ofFloat(mIndicationIm, "scaleX", 1, 0.2f);

        objectAnimator.setDuration(ANIMATION_DURATION);
        objectAnimator.setInterpolator(new AccelerateInterpolator(factor));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(ANIMATION_DURATION);
        animatorSet.playTogether(objectAnimator, scaleIndication);
        animatorSet.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation)
            {

            }

            @Override
            public void onAnimationEnd(Animator animation)
            {

                mShapeLoadingView.changeShape();
                upThrow();
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {

            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {

            }
        });
        animatorSet.start();

    }

}
