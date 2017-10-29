package com.example.yaphet.a07;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by WYF on 2017/9/28.
 * 绘制快速索引的英文字母
 */

public class Rightwords extends View {
    //每个子项的高和宽；
    private float itemhight;
    private float itemWidth;

    private float wordhight;
    private float wordWidth;

    private Paint paint;
    private String [] words={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public Rightwords(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setTextSize(DensityUtil.dip2px(getContext(),16));//设置字体大小
        paint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体类型
        Log.e("TAG","number"+words.length);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemhight=getMeasuredHeight()/26;
        itemWidth=getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i < words.length; i++) {
            if(touchindex==i) {
            paint.setColor(Color.YELLOW);
            }else{
                paint.setColor(Color.BLUE);
            }
            Rect rect=new Rect();
            paint.getTextBounds(words[i],0,1,rect);//用矩形包裹字****
            wordhight = rect.height();
            wordWidth=rect.width();
            float wordx=itemWidth/2-wordWidth/2;
            float wordy=itemhight/2+wordhight/2+itemhight*i;
            canvas.drawText(words[i],wordx,wordy,paint);
        }
    }
    private int touchindex=-1;
    private int index;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
         super.onTouchEvent(event);
        switch (event.getAction()) {
            case  MotionEvent.ACTION_DOWN:
            case  MotionEvent.ACTION_MOVE:
                float potion=event.getY();
                index= (int) (potion/itemhight);//点击的坐标
                Log.e("TAG","----"+index);
                if(index!=touchindex) {
                    touchindex=index;
                    if(changerlistener!=null&touchindex<=words.length){
                    changerlistener.onWordtoCenter(touchindex);}
                    invalidate();
                }
                break;
            case  MotionEvent.ACTION_UP:
                touchindex=-1;
                invalidate();
                break;
        }
        return true;
    }
    public interface onWordChangerlistener{
        void onWordtoCenter(int wordpotion);
    }
    private onWordChangerlistener changerlistener;
    public void setonWordChangerlistener(onWordChangerlistener listener){
        changerlistener=listener;
    }
}
