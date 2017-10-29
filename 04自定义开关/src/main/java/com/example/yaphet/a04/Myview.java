package com.example.yaphet.a04;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by WYF on 2017/9/21.
 * view绘制原理
 * 1.构造方法实例化该类
 * 2.测量 measure（int，int），-》onmeasure，如果当前View是一个viewgroup它还有义务去测量它的孩子，孩子有建议权
 * 3.布局 指定位置layout--》onlayout，指定控件的位置，一般view不用重写这个方法，viewgroup的时候才需要
 * 4.绘制，draw重写ondraw（canves）方法--》根据上面两个参数进行绘制
 * 5.事件处理
 * 6.死亡
 */

public class Myview extends View implements View.OnClickListener, View.OnTouchListener {
    private Bitmap bitebackground;
    private Bitmap biteslidfore;
    private int slidingmax;
    private Paint paint;
    private boolean isshow=false;
    private int slideleft;


    private float onTouchstartx;
    private float onTouchend;

    private boolean isonclickshow=true;
    /**
     * 默认点击事件生效，即当isonclickshow=true时。点击时间生效，滑动事件不生效
     */
    private float measuremove;
    /**
     * 如果我们在布局文件中使用该类，将会用这个构造方法来实例该类，如果没有，就会奔溃
     * @param context
     * @param attrs
     */
    public Myview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview();
    }

    private void initview() {
        bitebackground=BitmapFactory.decodeResource(getResources(),R.drawable.switch_background);
        biteslidfore=BitmapFactory.decodeResource(getResources(),R.drawable.slide_button);
        slidingmax=bitebackground.getWidth()-biteslidfore.getWidth();
        paint=new Paint();
        paint.setAntiAlias(true);//设置抗锯齿
        setOnClickListener(this);//内部添加点击事件
        setOnTouchListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(bitebackground.getWidth(),bitebackground.getHeight());//测量
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // super.onDraw(canvas);
        canvas.drawBitmap(bitebackground,0,0,paint);
       canvas.drawBitmap(biteslidfore,slideleft,0,paint);
    }

    @Override
    public void onClick(View view) {
        Log.e("TAG","onClick");
        if(isonclickshow) {
            isshow=!isshow;
            if(isshow) {
                slideleft=slidingmax;
            }else{
                slideleft=0;
            }
            invalidate();//强行进行重新绘制
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case  MotionEvent.ACTION_DOWN:
                measuremove=onTouchstartx=motionEvent.getX();
                isonclickshow=true;
                Log.e("TAG","ACTION_DOWN"+view.getId()+onTouchstartx);
                break;
            case MotionEvent.ACTION_MOVE:
                int moveend= (int) motionEvent.getX();
                if(Math.abs(moveend-measuremove)>5) {
                    isonclickshow=false;
                }
                Log.e("TAG","ACTION_MOVE"+view.getId());
                break;
            case MotionEvent.ACTION_UP:
                if(!isonclickshow) {
                    onTouchend=motionEvent.getX();
                    slideleft= (int) (onTouchend-onTouchstartx);
                    if(slideleft>slidingmax/2) {
                        slideleft=slidingmax;
                    }else if(slideleft<slidingmax/2) {
                        slideleft=0;
                    }
                    if(slideleft<0){
                        slideleft=0;
                    }else if(slideleft>slidingmax) {
                        slideleft=slidingmax;
                    }
                    invalidate();
                }
                //强行进行重新绘制
                Log.e("TAG","ACTION_UP"+view.getId()+onTouchend);
                break;
        }
        return false;
    }
}
