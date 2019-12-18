package com.ldwj.library.util.shapeload;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.WindowManager;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishuai
 * @see ShapeLoadingUtil
 * @since ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(MainActivity.this);
 *        shapeLoadingDialog.setLoadingText("loading..."); shapeLoadingDialog.show();
 */

public class ShapeLoadingUtil
{

    private Context mContext;

    private Dialog mDialog;

    private LoadingView mLoadingView;

    public ShapeLoadingUtil(Context context)
    {
        this.mContext = context;
        init();
    }

    private void init()
    {
        mDialog = new Dialog(mContext, android.R.style.Theme_Material_Dialog);
        mLoadingView = new LoadingView(mContext);
        mDialog.setContentView(mLoadingView);
        int height = mLoadingView.dip2px(200);
        int width = height;
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.height = height;
        lp.width =width;
        mDialog.getWindow().setAttributes(lp);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }

    public void setBackground(int color)
    {
        GradientDrawable gradientDrawable = (GradientDrawable)mLoadingView.getBackground();
        gradientDrawable.setColor(color);
    }

    public void setLoadingText(CharSequence charSequence)
    {
        mLoadingView.setLoadingText(charSequence);
    }

    public void show()
    {
        mDialog.show();
    }

    public void dismiss()
    {
        mDialog.dismiss();
    }

    public Dialog getDialog()
    {
        return mDialog;
    }

    public void setCanceledOnTouchOutside(boolean cancel)
    {
        mDialog.setCanceledOnTouchOutside(cancel);
    }
}