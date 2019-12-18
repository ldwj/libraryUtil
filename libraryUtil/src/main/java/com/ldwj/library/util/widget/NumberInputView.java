package com.ldwj.library.util.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ldwj.library.util.R;
import com.ldwj.library.util.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ldwj.library.util.utils.ScreenUtils.dp2px;

/**
 * Created by lishuai on 2019/7/8.
 * 输入验证码数字 一个数字一个框
 */ //使用方法: 代码中设置完成监听setOnCompleteListener
      /*<NumberInputView
    android:id="@+id/numberinput_view"
    android:layout_width="match_parent"
    android:layout_height="50dp"
    android:gravity="center"
    app:bgInputed="@drawable/bg_inputed"
    app:bgInputing="@drawable/bg_inputing"
    app:count="6"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:passwordSize="14"
    app:showPassword="true" />*/
public class NumberInputView extends RelativeLayout {
    private Context mContext;
    private EditText mEditText;
    private List<EditText> editTexts;   //选用 EditText 而不用 TextView 是因为 EditText 可以密文显示
    private int count = 4;  //密码框数量
    private int passwordSize = 14;  //密码文本大小
    private boolean showPassword = true;   //密码是否密文显示,true 为一直明文显示,false 为 0.5s 后密文显示
    private int bgInputing = R.drawable.bg_inputing; //待输入的密码框的背景
    private int bgInputed = R.drawable.bg_inputed;  //非待输入的密码框的背景
    private onCompletionListener mOnCompletionListener;

    public NumberInputView(Context context) {
        this(context, null);
    }

    public NumberInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs, defStyleAttr);
        initView();
        addListener();
    }

    /**
     * 初始化自定义属性
     */
    private void initAttr(AttributeSet attrs, int defStyleAttr) {
        TypedArray mTypeArray = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.NumberInputView, defStyleAttr, 0);
        count = mTypeArray.getInt(R.styleable.NumberInputView_count, 4);
        passwordSize = mTypeArray.getInt(R.styleable.NumberInputView_passwordSize, 14);
        showPassword = mTypeArray.getBoolean(R.styleable.NumberInputView_showPassword, true);
        bgInputing = mTypeArray.getResourceId(R.styleable.NumberInputView_bgInputing, R.drawable.bg_inputing);
        bgInputed = mTypeArray.getResourceId(R.styleable.NumberInputView_bgInputed, R.drawable.bg_inputed);
    }

    /**
     * 添加控件,密码框和不可见的输入框
     */
    private void initView() {
        //新建一个容器
        LinearLayout mLinearLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mLinearLayout.setLayoutParams(linearLayoutParams);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        //根据 count 添加一定数量的密码框
        editTexts = new ArrayList<EditText>();
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(mContext) / count - dp2px(mContext, 20), ScreenUtils.getScreenWidth(mContext) / count - dp2px(mContext, 20));
        textViewParams.setMargins(dp2px(mContext, 10), dp2px(mContext, 10), dp2px(mContext, 10), dp2px(mContext, 10));
        for (int i = 0; i < count; i++) {
            EditText et = new EditText(mContext);
            et.setLayoutParams(textViewParams);
            et.setBackgroundResource(R.drawable.bg_inputed); //设置背景
            et.setGravity(Gravity.CENTER);   //设置文本显示位置
            et.setTextColor(Color.BLACK);
            et.setTextSize(passwordSize);    //设置文本大小
            et.setFocusable(false);  //设置无法获得焦点
            editTexts.add(et);
            mLinearLayout.addView(et);
        }
        editTexts.get(0).setBackgroundResource(bgInputing);
        //添加不可见的 EditText
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mEditText = new EditText(mContext);
        mEditText.setLayoutParams(editTextParams);
        mEditText.setCursorVisible(false);  //设置输入游标不可见
        mEditText.setBackgroundResource(0); //设置透明背景,让下划线不可见
        mEditText.setAlpha(0.0f);   //设置为全透明,让输入的内容不可见
        mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);    //设置只能输入数字
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(count)});   //限制输入长度
        addView(mLinearLayout);
        addView(mEditText);
    }

    /**
     *  为输入框添加监听器
     */
    private void addListener() {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            /**
             *
             * @param s 输入后的文本
             * @param start 输入的位置
             * @param before 输入的位置上之前的字符数
             * @param count 输入的位置上新输入的字符数
             */
            @Override
            public void onTextChanged(CharSequence s, final int start, int before, int count) {
                if (before == 0 && count == 1) {
                    //为对应显示框设置对应显示内容
                    editTexts.get(start).setText(s.subSequence(start, start + 1));
                    //修改输入了内容的密码框的背景
                    editTexts.get(start).setBackgroundResource(bgInputed);
                    //如果还有下一个密码框,将其背景设置为待输入的背景
                    if (start + 1 < editTexts.size()) {
                        editTexts.get(start + 1).setBackgroundResource(bgInputing);
                    } else {
                        //输入完成后关闭软键盘
                        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
                        //如果添加了监听器,则回调
                        if (mOnCompletionListener != null) {
                            mOnCompletionListener.onCompletion(s.toString());
                        }
                    }
                    //如果需要密文显示,则 0.5s 后设置为密文显示
                    if (!showPassword) {
                        editTexts.get(start).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                editTexts.get(start).setTransformationMethod(PasswordTransformationMethod.getInstance());
                            }
                        }, 500);
                    }
                    //如果上一个显示框还不是密文显示的话,立即将其设置为密文显示,前提是需要密文显示
                    if (!showPassword && start > 0 && editTexts.get(start - 1).getTransformationMethod() instanceof HideReturnsTransformationMethod) {
                        editTexts.get(start - 1).setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                } else if (before == 1 && count == 0) {
                    //清除退格位置对应显示框的内容
                    editTexts.get(start).setText("");
                    //将其退格的位置设置为明文显示
                    editTexts.get(start).setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    //设置退格位置的背景
                    for (EditText editText : editTexts) {
                        editText.setBackgroundResource(bgInputed);
                    }
                    editTexts.get(start).setBackgroundResource(bgInputing);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void setOnCompleteListener(onCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }
    public interface onCompletionListener {
        void onCompletion(String code);
    }
}