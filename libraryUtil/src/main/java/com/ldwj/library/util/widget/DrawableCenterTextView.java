package com.ldwj.library.util.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/** 
 * drawableLeft与文本一起居中显示 
 *  
 */ 
public class DrawableCenterTextView extends AppCompatTextView {
	  public DrawableCenterTextView(Context context, AttributeSet attrs,
                                    int defStyle) {
	        super(context, attrs, defStyle);  
	    }  
	  
	    public DrawableCenterTextView(Context context, AttributeSet attrs) {
	        super(context, attrs);  
	    }  
	  
	    public DrawableCenterTextView(Context context) {
	        super(context);  
	    }  
	  
	    @Override
	    protected void onDraw(Canvas canvas) {
	        Drawable[] drawables = getCompoundDrawables();
	        if (drawables != null) {  
	            Drawable drawableLeft = drawables[0];
	            Drawable drawableRight = drawables[2];
	            float textWidth = getPaint().measureText(getText().toString());  
                int drawablePadding = getCompoundDrawablePadding();  
                int drawableWidth = 0; 
	            if (drawableLeft != null) {  
	                drawableWidth = drawableLeft.getIntrinsicWidth();  
	                float bodyWidth = textWidth + drawableWidth + drawablePadding;  
	                canvas.translate((getWidth() - bodyWidth) / 2, 0);  
	            } 
	            
	            if(drawableRight != null){
	                drawableWidth = drawableRight.getIntrinsicWidth();  
	                float bodyWidth = textWidth + drawableWidth + drawablePadding;  
	                setPadding(0, 0, 0, 0);  
	                canvas.translate((getWidth() - bodyWidth) / 2, 0);  
	            }
	        }  
	        super.onDraw(canvas);  
	    }  
}
