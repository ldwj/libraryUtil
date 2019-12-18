package com.ldwj.library.util.utils;


import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.ldwj.libraryapplication.BaseApplication;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目中涉及到的字符串的各种处理  字符串校验、正则校验、数字格式化
 */
public class StringUtils {

  //手机号
  public static final String REGEX_PHONE="^(13[0-9]|14[5|7|9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\\d{8}$";
  //邮箱 [A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}
  public static final String REGEX_EMAILS="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
  //中文
  public static final String REGEX_CHINESE="[\\u4e00-\\u9fa5]";

  /**
   * 判断字符串是否为空
   * @param sequence
   * @return
   */
  public static boolean isEmpty(CharSequence sequence) {
    //str == null || str.length() == 0
    return TextUtils.isEmpty(sequence);
  }

  /**
   * 是否是手机号的校验
   * @param phone
   * @return
   */
  public static boolean isPhoneValidate(String phone) {
    Pattern pattern = Pattern.compile(REGEX_PHONE);
    Matcher isPhone = pattern.matcher(phone);
    if (isPhone.matches()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 是否是邮箱的校验
   * @param emails
   * @return
   */
  public static boolean isEmailsValidate(String emails) {
    Pattern pattern = Pattern.compile(REGEX_EMAILS);
    Matcher isEmails = pattern.matcher(emails);
    if (isEmails.matches()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 是否含有中文
   * @param chinese
   * @return
   */
  public static boolean isChineseValidate(String chinese) {
    Pattern pattern = Pattern.compile(REGEX_CHINESE);
    Matcher isChinese = pattern.matcher(chinese);
    if (isChinese.find()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 功能：判断字符串是否为数字
   *
   * @param str
   * @return
   */
  private static boolean isNumeric(String str) {
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher isNum = pattern.matcher(str);
    if (isNum.matches()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 功能：判断字符串是否为日期格式
   *
   * @return
   */
  public static boolean isDateValidate(String strDate) {
    Pattern pattern = Pattern
            .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    Matcher m = pattern.matcher(strDate);
    if (m.matches()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 功能：身份证的有效验证
   *
   * @param IDStr
   *            身份证号
   * @return true 有效：false 无效
   * @throws ParseException
   */
  public static boolean isIDCardValidate(String IDStr) throws ParseException {
    String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
    String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
    String Ai = "";
    // ================ 号码的长度18位 ================
    if (IDStr.length() != 18) {
      return false;
    }
    // ================ 数字 除最后以为都为数字 ================
    if (IDStr.length() == 18) {
      Ai = IDStr.substring(0, 17);
    }
    if (isNumeric(Ai) == false) {
      //errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
      return false;
    }
    // ================ 出生年月是否有效 ================
    String strYear = Ai.substring(6, 10);// 年份
    String strMonth = Ai.substring(10, 12);// 月份
    String strDay = Ai.substring(12, 14);// 日
    if (isDateValidate(strYear + "-" + strMonth + "-" + strDay) == false) {
//          errorInfo = "身份证生日无效。";
      return false;
    }
    GregorianCalendar gc = new GregorianCalendar();
    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
    try {
      if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
        //errorInfo = "身份证生日不在有效范围。";
        return false;
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (java.text.ParseException e) {
      e.printStackTrace();
    }
    if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
      //errorInfo = "身份证月份无效";
      return false;
    }
    if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
      //errorInfo = "身份证日期无效";
      return false;
    }
    // ================ 地区码时候有效 ================
    Hashtable h = GetAreaCode();
    if (h.get(Ai.substring(0, 2)) == null) {
      //errorInfo = "身份证地区编码错误。";
      return false;
    }
    // ================ 判断最后一位的值 ================
    int TotalmulAiWi = 0;
    for (int i = 0; i < 17; i++) {
      TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
    }
    int modValue = TotalmulAiWi % 11;
    String strVerifyCode = ValCodeArr[modValue];
    Ai = Ai + strVerifyCode;

    if (IDStr.length() == 18) {
      if (Ai.equals(IDStr) == false) {
        //errorInfo = "身份证无效，不是合法的身份证号码";
        return false;
      }
    } else {
      return true;
    }
    return true;
  }


  /**
   * 功能：设置地区编码
   *
   * @return Hashtable 对象
   */
  @SuppressWarnings("unchecked")
  private static Hashtable GetAreaCode() {
    Hashtable hashtable = new Hashtable();
    hashtable.put("11", "北京");
    hashtable.put("12", "天津");
    hashtable.put("13", "河北");
    hashtable.put("14", "山西");
    hashtable.put("15", "内蒙古");
    hashtable.put("21", "辽宁");
    hashtable.put("22", "吉林");
    hashtable.put("23", "黑龙江");
    hashtable.put("31", "上海");
    hashtable.put("32", "江苏");
    hashtable.put("33", "浙江");
    hashtable.put("34", "安徽");
    hashtable.put("35", "福建");
    hashtable.put("36", "江西");
    hashtable.put("37", "山东");
    hashtable.put("41", "河南");
    hashtable.put("42", "湖北");
    hashtable.put("43", "湖南");
    hashtable.put("44", "广东");
    hashtable.put("45", "广西");
    hashtable.put("46", "海南");
    hashtable.put("50", "重庆");
    hashtable.put("51", "四川");
    hashtable.put("52", "贵州");
    hashtable.put("53", "云南");
    hashtable.put("54", "西藏");
    hashtable.put("61", "陕西");
    hashtable.put("62", "甘肃");
    hashtable.put("63", "青海");
    hashtable.put("64", "宁夏");
    hashtable.put("65", "新疆");
    hashtable.put("71", "台湾");
    hashtable.put("81", "香港");
    hashtable.put("82", "澳门");
    hashtable.put("91", "国外");
    return hashtable;
  }

  public static final String NUMBER_FOMART_JING_0="#";//四舍五入 获取整数
  public static final String NUMBER_FOMART_JING_1="#.#";//获取一位小数
  public static final String NUMBER_FOMART_JING_2="#.##";//获取两位小数
  public static final String NUMBER_FOMART_JING_3="#.###";//获取三位小数
  public static final String NUMBER_FOMART_ZERO_1="#.0";//获取一位小数 不够补“0”
  public static final String NUMBER_FOMART_ZERO_2="#.00";//获取两位小数 不够补“0”
  public static final String NUMBER_FOMART_ZERO_3="#.000";//获取三位小数 不够补“0”
  /**
   * 主要是处理double转成其他格式的数据
   * @param number
   * @return
   */
  public static String getFomartNumber(double number,String pattern){
    return new DecimalFormat(pattern).format(number);
  }
  /**
   * 获取textview一行最大能显示几个字(需要在TextView测量完成之后)
   *
   */
  public static int getLineMaxNumber(Context context, TextView tvView, int otherWidth) {
    int  width = (ScreenUtils.getScreenWidth(BaseApplication.getInstance()) - DisplayUtils.dp2px(context, otherWidth));
    int number = Math.round(width / tvView.getTextSize());
    return number;
  }

  /**
   * 数字字符串转ASCII码字符串
   *
   * @param content
   *            字符串
   * @return ASCII字符串
   */
  public static String StringToAsciiString(String content) {
    String result = "";
    int max = content.length();
    for (int i = 0; i < max; i++) {
      char c = content.charAt(i);
      String b = Integer.toHexString(c);
      result = result + b;
    }
    return result;
  }
  /**
   * 十六进制转字符串
   *
   * @param hexString
   *            十六进制字符串
   * @param encodeType
   *            编码类型4：Unicode，2：普通编码
   * @return 字符串
   */
  public static String hexStringToString(String hexString, int encodeType) {
    String result = "";
    int max = hexString.length() / encodeType;
    for (int i = 0; i < max; i++) {
      char c = (char) hexStringToAlgorism(hexString
              .substring(i * encodeType, (i + 1) * encodeType));
      result += c;
    }
    return result;
  }
  /**
   * 十六进制字符串装十进制
   *
   * @param hex
   *            十六进制字符串
   * @return 十进制数值
   */
  public static int hexStringToAlgorism(String hex) {
    hex = hex.toUpperCase();
    int max = hex.length();
    int result = 0;
    for (int i = max; i > 0; i--) {
      char c = hex.charAt(i - 1);
      int algorism = 0;
      if (c >= '0' && c <= '9') {
        algorism = c - '0';
      } else {
        algorism = c - 55;
      }
      result += Math.pow(16, max - i) * algorism;
    }
    return result;
  }
  /**
   * 十六转二进制
   *
   * @param hex
   *            十六进制字符串
   * @return 二进制字符串
   */
  public static String hexStringToBinary(String hex) {
    hex = hex.toUpperCase();
    String result = "";
    int max = hex.length();
    for (int i = 0; i < max; i++) {
      char c = hex.charAt(i);
      switch (c) {
        case '0':
          result += "0000";
          break;
        case '1':
          result += "0001";
          break;
        case '2':
          result += "0010";
          break;
        case '3':
          result += "0011";
          break;
        case '4':
          result += "0100";
          break;
        case '5':
          result += "0101";
          break;
        case '6':
          result += "0110";
          break;
        case '7':
          result += "0111";
          break;
        case '8':
          result += "1000";
          break;
        case '9':
          result += "1001";
          break;
        case 'A':
          result += "1010";
          break;
        case 'B':
          result += "1011";
          break;
        case 'C':
          result += "1100";
          break;
        case 'D':
          result += "1101";
          break;
        case 'E':
          result += "1110";
          break;
        case 'F':
          result += "1111";
          break;
      }
    }
    return result;
  }
  /**
   * ASCII码字符串转数字字符串
   *
   * @param content
   *            ASCII字符串
   * @return 字符串
   */
  public static String AsciiStringToString(String content) {
    String result = "";
    int length = content.length() / 2;
    for (int i = 0; i < length; i++) {
      String c = content.substring(i * 2, i * 2 + 2);
      int a = hexStringToAlgorism(c);
      char b = (char) a;
      String d = String.valueOf(b);
      result += d;
    }
    return result;
  }
  /**
   * 将十进制转换为指定长度的十六进制字符串
   *
   * @param algorism
   *            int 十进制数字
   * @param maxLength
   *            int 转换后的十六进制字符串长度
   * @return String 转换后的十六进制字符串
   */
  public static String algorismToHEXString(int algorism, int maxLength) {
    String result = "";
    result = Integer.toHexString(algorism);

    if (result.length() % 2 == 1) {
      result = "0" + result;
    }
    return patchHexString(result.toUpperCase(), maxLength);
  }
  /**
   * 字节数组转为普通字符串（ASCII对应的字符）
   *
   * @param bytearray
   *            byte[]
   * @return String
   */
  public static String bytetoString(byte[] bytearray) {
    String result = "";
    char temp;

    int length = bytearray.length;
    for (int i = 0; i < length; i++) {
      temp = (char) bytearray[i];
      result += temp;
    }
    return result;
  }
  /**
   * 二进制字符串转十进制
   *
   * @param binary
   *            二进制字符串
   * @return 十进制数值
   */
  public static int binaryToAlgorism(String binary) {
    int max = binary.length();
    int result = 0;
    for (int i = max; i > 0; i--) {
      char c = binary.charAt(i - 1);
      int algorism = c - '0';
      result += Math.pow(2, max - i) * algorism;
    }
    return result;
  }

  /**
   * 十进制转换为十六进制字符串
   *
   * @param algorism
   *            int 十进制的数字
   * @return String 对应的十六进制字符串
   */
  public static String algorismToHEXString(int algorism) {
    String result = "";
    result = Integer.toHexString(algorism);

    if (result.length() % 2 == 1) {
      result = "0" + result;

    }
    result = result.toUpperCase();

    return result;
  }
  /**
   * HEX字符串前补0，主要用于长度位数不足。
   *
   * @param str
   *            String 需要补充长度的十六进制字符串
   * @param maxLength
   *            int 补充后十六进制字符串的长度
   * @return 补充结果
   */
  static public String patchHexString(String str, int maxLength) {
    String temp = "";
    for (int i = 0; i < maxLength - str.length(); i++) {
      temp = "0" + temp;
    }
    str = (temp + str).substring(0, maxLength);
    return str;
  }
  /**
   * 将一个字符串转换为int
   *
   * @param s
   *            String 要转换的字符串
   * @param defaultInt
   *            int 如果出现异常,默认返回的数字
   * @param radix
   *            int 要转换的字符串是什么进制的,如16 8 10.
   * @return int 转换后的数字
   */
  public static int parseToInt(String s, int defaultInt, int radix) {
    int i = 0;
    try {
      i = Integer.parseInt(s, radix);
    } catch (NumberFormatException ex) {
      i = defaultInt;
    }
    return i;
  }
  /**
   * 将一个十进制形式的数字字符串转换为int
   *
   * @param s
   *            String 要转换的字符串
   * @param defaultInt
   *            int 如果出现异常,默认返回的数字
   * @return int 转换后的数字
   */
  public static int parseToInt(String s, int defaultInt) {
    int i = 0;
    try {
      i = Integer.parseInt(s);
    } catch (NumberFormatException ex) {
      i = defaultInt;
    }
    return i;
  }
  /**
   * 十六进制字符串转为Byte数组,每两个十六进制字符转为一个Byte
   *
   * @param hex
   *            十六进制字符串
   * @return byte 转换结果
   */
  public static byte[] hexStringToByte(String hex) {
    int max = hex.length() / 2;
    byte[] bytes = new byte[max];
    String binarys = hexStringToBinary(hex);
    for (int i = 0; i < max; i++) {
      bytes[i] = (byte)binaryToAlgorism(binarys.substring(
              i * 8 + 1, (i + 1) * 8));
      if (binarys.charAt(8 * i) == '1') {
        bytes[i] = (byte) (0 - bytes[i]);
      }
    }
    return bytes;
  }
  /**
   * 十六进制串转化为byte数组
   *
   * @return the array of byte
   */
  public static final byte[] hex2byte(String hex)
          throws IllegalArgumentException {
    if (hex.length() % 2 != 0) {
      throw new IllegalArgumentException();
    }
    char[] arr = hex.toCharArray();
    byte[] b = new byte[hex.length() / 2];
    for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
      String swap = "" + arr[i++] + arr[i];
      int byteint = Integer.parseInt(swap, 16) & 0xFF;
      b[j] = new Integer(byteint).byteValue();
    }
    return b;
  }
  /**
   * 字节数组转换为十六进制字符串
   *
   * @param b
   *            byte[] 需要转换的字节数组
   * @return String 十六进制字符串
   */
  public static final String byte2hex(byte b[]) {
    if (b == null) {
      throw new IllegalArgumentException(
              "Argument b ( byte array ) is null! ");
    }
    String hs = "";
    String stmp = "";
    for (int n = 0; n < b.length; n++) {
      stmp = Integer.toHexString(b[n] & 0xff);
      if (stmp.length() == 1) {
        hs = hs + "0" + stmp;
      } else {
        hs = hs + stmp;
      }
    }
    return hs.toUpperCase();
  }
}
