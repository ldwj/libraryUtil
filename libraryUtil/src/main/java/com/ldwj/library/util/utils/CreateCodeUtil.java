package com.ldwj.library.util.utils;


import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishuai
 * @see CreateCodeUtil
 * @since
 */

public class CreateCodeUtil
{
    // 随机数数组 01,iIlOo易混淆的去掉了
    private static char[] CHARS = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();

    // default settings
    // 验证码默认随机数的个数
    private static final int DEFAULT_CODE_LENGTH = 6;

    // 默认字体大小
    private static final float DEFAULT_FONT_SIZE = 45f;

    // 默认线条的条数
    private static final int DEFAULT_LINE_NUMBER = 5;

    private static CreateCodeUtil bmpCode;

    public static CreateCodeUtil getInstance()
    {
        if (bmpCode == null) {
            bmpCode = new CreateCodeUtil();
        }
        return bmpCode;
    }

    // padding值
    private static final int BASE_PADDING_LEFT = 30, BASE_PADDING_RIGHT = 10,
        BASE_PADDING_TOP = 10, BASE_PADDING_BOTTOM = 20;

    // 验证码的默认宽高
    private static final int DEFAULT_WIDTH = 260, DEFAULT_HEIGHT = 100;

    // settings decided by the layout xml
    // canvas width and height
    private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

    //  验证码的位数
    private int codeLength = DEFAULT_CODE_LENGTH;
    //干扰线的数量
    private int line_number = DEFAULT_LINE_NUMBER;
    //字体大小
    float font_size = DEFAULT_FONT_SIZE;
    //随机的数据源
    private char[] chars = CHARS;
    // 生成的验证码
    private String code;

    private Random random = new Random();

    public int getCodeLength()
    {
        return codeLength;
    }

    public int getLine_number()
    {
        return line_number;
    }

    public float getFont_size()
    {
        return font_size;
    }

    public char[] getChars()
    {
        return chars;
    }

    public void setChars(char[] chars)
    {
        this.chars = chars;
    }

    public void setCodeLength(int codeLength)
    {
        this.codeLength = codeLength;
    }

    public void setLine_number(int line_number)
    {
        this.line_number = line_number;
    }

    public void setFont_size(float font_size)
    {
        this.font_size = font_size;
    }

    // 验证码图片
    public Bitmap createBitmap()
    {

        Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas c = new Canvas(bp);

        code = createCode();

        c.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(getFont_size());
        // 画验证码
        int x = (width - BASE_PADDING_LEFT - BASE_PADDING_RIGHT) / (code.length());
        int y = height - BASE_PADDING_BOTTOM - BASE_PADDING_TOP;
        for (int i = 0; i < code.length(); i++ )
        {
            randomTextStyle(paint);
            c.drawText(code.charAt(i) + "", BASE_PADDING_LEFT + x * i, y, paint);
        }
        // 画线条
        for (int i = 0; i < line_number; i++ )
        {
            drawLine(c, paint);
        }
        c.save();// 保存
        c.restore();//
        return bp;
    }

    public String getCode()
    {
        return code;
    }

    // 生成验证码
    private String createCode()
    {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < codeLength; i++ )
        {
            buffer.append(chars[random.nextInt(chars.length)]);
        }
        return buffer.toString();
    }

    // 画干扰线
    private void drawLine(Canvas canvas, Paint paint)
    {
        int color = randomColor();
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        int stopX = random.nextInt(width);
        int stopY = random.nextInt(height);
        paint.setStrokeWidth(1);
        paint.setColor(color);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    // 生成随机颜色
    private int randomColor()
    {
        return randomColor(1);
    }

    private int randomColor(int rate)
    {
        int red = random.nextInt(256) / rate;
        int green = random.nextInt(256) / rate;
        int blue = random.nextInt(256) / rate;
        return Color.rgb(red, green, blue);
    }

    // 随机生成文字样式，颜色，粗细，倾斜度
    private void randomTextStyle(Paint paint)
    {
        int color = randomColor();
        paint.setColor(color);
        paint.setFakeBoldText(true); // true为粗体，false为非粗体
        float skewX = random.nextInt(11) / 10;
        skewX = random.nextBoolean() ? skewX : -skewX;
        paint.setTextSkewX(skewX); // float类型参数，负数表示右斜，正数左斜
        paint.setUnderlineText(true); // true为下划线，false为非下划线
        paint.setStrikeThruText(true); // true为删除线，false为非删除线
    }

}
