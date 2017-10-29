package com.example.yaphet.aview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by WYF on 2017/9/12.
 */

public class Myview extends SurfaceView implements SurfaceHolder.Callback {
    private Paint paint;
    SurfaceHolder surfaceHolder;
    Canvas canvas;
    int x = 0;

    public Myview(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public Myview(Context context)
    {
        super(context);
    }

    public Myview(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }
    public void initView(){//设置画笔
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);//设置空心线宽
        paint.setStyle(Paint.Style.FILL);//只绘制图形内容
    }

    private double degreeToRad(double degree){//角度转化为弧度
        return degree * Math.PI/180;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {//alt+enter
        initView();
        final int width = getWidth();
        int height = getHeight();
        final int centerY = height/2;
        canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText("X", 5, 25, paint);
        canvas.drawText("Y",5,centerY+25,paint);
        canvas.drawLine(0, centerY, width, centerY, paint);//在屏幕中心绘制x轴
        canvas.drawLine(0, 0, 0, height, paint);//绘制Y轴
        while (x < width) {//绘制曲线
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(5);
            double rad = degreeToRad(x);//角度转换成弧度
            int y = (int) (centerY - Math.sin(rad)*100);
            canvas.drawPoint(x,y,paint);
            x++;
        }
        surfaceHolder.unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像 
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
