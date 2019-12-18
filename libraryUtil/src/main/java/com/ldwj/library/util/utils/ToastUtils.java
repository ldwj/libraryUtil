package com.ldwj.library.util.utils;


import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ldwj.libraryapplication.BaseApplication;
import com.ldwj.library.util.R;

/**
 * 提示 Toast 的工具类
 */
public class ToastUtils {

  //较长时间展示
  private static final int LENGTH_LONG = Toast.LENGTH_LONG;
  //较短时间展示
  private static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
  //顶部
  private static final int TOP = Gravity.TOP;
  //底部
  private static final int BOTTOM = Gravity.BOTTOM;
  // 居中
  private static final int CENTER = Gravity.CENTER;
  private static Toast toast;


  /**
   * 顶部弹出短时间的提示
   * @param msg 展示内容
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showTSToast(String msg,boolean isImg){
      showToast(msg,TOP,LENGTH_SHORT,isImg);
  }
  public static void showTSToast(String msg){
    showToast(msg,TOP,LENGTH_SHORT,false);
  }
  /**
   * 顶部弹出短时间的提示
   * @param msgId 展示内容 string.xml获取
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showTSToast(int msgId,boolean isImg){
    String _MsgString;
    try {
      _MsgString = BaseApplication.getInstance().getResources().getString(msgId);
    } catch (Resources.NotFoundException e) {
      _MsgString = null;
    }
    showToast(_MsgString,TOP,LENGTH_SHORT,isImg);
  }

  /**
   * 中间弹出短时间的提示
   * @param msg 展示内容
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showCSToast(String msg,boolean isImg){
    showToast(msg,CENTER,LENGTH_SHORT,isImg);
  }
  public static void showCSToast(String msg){
    showToast(msg,CENTER,LENGTH_SHORT,false);
  }

  /**
   * 中间弹出短时间的提示
   * @param msgId 展示内容 string.xml获取
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showCSToast(int msgId,boolean isImg){
    String _MsgString;
    try {
      _MsgString = BaseApplication.getInstance().getResources().getString(msgId);
    } catch (Resources.NotFoundException e) {
      _MsgString = null;
    }
    showToast(_MsgString,CENTER,LENGTH_SHORT,isImg);
  }

  /**
   * 底部弹出短时间的提示
   * @param msg 展示内容
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showBSToast(String msg,boolean isImg){
    showToast(msg,BOTTOM,LENGTH_SHORT,isImg);
  }

  /**
   * 底部弹出短时间的提示
   * @param msgId 展示内容  string.xml获取
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showBSToast(int msgId,boolean isImg){
    String _MsgString;
    try {
      _MsgString = BaseApplication.getInstance().getResources().getString(msgId);
    } catch (Resources.NotFoundException e) {
      _MsgString = null;
    }
    showToast(_MsgString,BOTTOM,LENGTH_SHORT,isImg);
  }

  /**
   * 顶部弹出长时间的提示
   * @param msg 展示内容
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showTLToast(String msg,boolean isImg){
    showToast(msg,TOP,LENGTH_LONG,isImg);
  }
  public static void showTLToast(String msg){
    showToast(msg,TOP,LENGTH_LONG,false);
  }
  /**
   * 顶部弹出长时间的提示
   * @param msgId 展示内容 string.xml获取
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showTLToast(int msgId,boolean isImg){
    String _MsgString;
    try {
      _MsgString = BaseApplication.getInstance().getResources().getString(msgId);
    } catch (Resources.NotFoundException e) {
      _MsgString = null;
    }
    showToast(_MsgString,TOP,LENGTH_LONG,isImg);
  }

  /**
   * 中间弹出长时间的提示
   * @param msg 展示内容
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showCLToast(String msg,boolean isImg){
    showToast(msg,CENTER,LENGTH_LONG,isImg);
  }

  /**
   * 中间弹出长时间的提示
   * @param msgId 展示内容 string.xml获取
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showCLToast(int msgId,boolean isImg){
    String _MsgString;
    try {
      _MsgString = BaseApplication.getInstance().getResources().getString(msgId);
    } catch (Resources.NotFoundException e) {
      _MsgString = null;
    }
    showToast(_MsgString,CENTER,LENGTH_LONG,isImg);
  }

  /**
   * 底部部弹出长时间的提示
   * @param msg 展示内容
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showBLToast(String msg,boolean isImg){
    showToast(msg,BOTTOM,LENGTH_LONG,isImg);
  }
  public static void showBLToast(String msg){
    showToast(msg,BOTTOM,LENGTH_LONG,false);
  }

  /**
   * 底部部弹出长时间的提示
   * @param msgId 展示内容 string.xml获取
   * @param isImg 是否展示提示左边APP的logo
   */
  public static void showBLToast(int msgId,boolean isImg){
    String _MsgString;
    try {
      _MsgString = BaseApplication.getInstance().getResources().getString(msgId);
    } catch (Resources.NotFoundException e) {
      _MsgString = null;
    }
    showToast(_MsgString,BOTTOM,LENGTH_LONG,isImg);
  }

  //设置toast展示 带图片
  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
  private static void showToast(String msg, int gravite, int duration, boolean isImgShow){
//    if(toast == null){
      toast = new Toast(BaseApplication.getInstance());
//    }
    toast.setDuration(duration);
    toast.setGravity(gravite, 0, 50);
    View view = getContentView();
    ImageView img = view.findViewById(R.id.img_toast);
    img.setVisibility(isImgShow==true?View.VISIBLE:View.GONE);
    if(isImgShow){
      //获取APP的logo展示到toast的左边
      img.setBackground(AppUtil.getDrawable());
    }
    TextView txt_message = (TextView)view.findViewById(R.id.txt_message);
    txt_message.setText(msg);
    toast.setView(view);
    toast.show();
  }

  //获取toast的自定义view
  private static View getContentView(){
    return View.inflate(BaseApplication.getInstance(), R.layout.toast_layout, null);
  }
}