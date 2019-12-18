package com.ldwj.library.util.flippingloadingdialog;


import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.ldwj.library.util.R;


/**
 * 左侧icon转动,右侧提示文字
 */
public class FlippingLoadingDialog extends Dialog
{

    private FlippingImageView mFivIcon;

    private TextView mHtvText;

    private String mText;

    public FlippingLoadingDialog(Context context, String text)
    {
        super(context);
        mText = text;
        init();
    }

    private void init()
    {
        setContentView(R.layout.common_flipping_loading_diloag);
        mFivIcon = (FlippingImageView)findViewById(R.id.loadingdialog_fiv_icon);
        mHtvText = (TextView)findViewById(R.id.loadingdialog_htv_text);
        mFivIcon.startAnimation();
        mHtvText.setText(mText);
    }

    public void setText(String text)
    {
        mText = text;
        mHtvText.setText(mText);
    }

    @Override
    public void dismiss()
    {
        if (isShowing())
        {
            super.dismiss();
        }
    }
}
