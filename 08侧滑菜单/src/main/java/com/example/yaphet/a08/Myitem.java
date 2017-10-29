package com.example.yaphet.a08;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by WYF on 2017/10/11.
 * 侧滑菜单自定义item布局
 */
public class Myitem extends FrameLayout {
    private View content;
    private View meau;
    private int contentwidth;
    private int meauwidth;
    private int samehight;
    private Scroller scroller;
    public Myitem(@NonNull Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        scroller=new Scroller(context);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        contentwidth=content.getMeasuredWidth();
        meauwidth=meau.getMeasuredWidth();
        samehight=meau.getMeasuredHeight();
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Boolean onInterceptTouch=false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                downx=startx= event.getX();
                downy=starty=event.getY();
                if(listener!=null) {
                    listener.OnDown(this);
                }
                break;
            case MotionEvent.ACTION_MOVE :
                float endx=event.getX();
                float endy=event.getY();
                //在x轴y轴滑动的距离
                float Dx=Math.abs(endx-downx);
                float Dy=Math.abs(endy-downy);
                if(Dx>8){
                    //拦截事件
                    onInterceptTouch=true;
                }
                break;
            case MotionEvent.ACTION_UP :
                break;
        }
        return onInterceptTouch;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        content.layout(0,0,contentwidth,samehight);
        meau.layout(contentwidth,0,contentwidth+meauwidth,samehight);
    }
    /**
     * 布局文件加载完成后，会回调onFinishInflate方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        content= getChildAt(0);
        meau=getChildAt(1);
    }
    private float startx;
    private float starty;
    private float downx;
    private float downy;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                downx=startx= event.getX();
                downy=starty=event.getY();
                break;
            case MotionEvent.ACTION_MOVE :
                float endx=event.getX();
                float endy=event.getY();
                float distence=endx-startx;

                Log.e("distence=","distence="+distence+"meauwidth="+meauwidth);
                int distancex= (int) (getScrollX()-distence);
                if(distancex<0){
                    distancex=0;
                }else if(distancex>meauwidth){
                    distancex=meauwidth;
                }
                scrollTo(distancex,getScrollY());
                startx= event.getX();
                starty=event.getY();
                //在x轴y轴滑动的距离
                float Dx=Math.abs(endx-downx);
                float Dy=Math.abs(endy-downy);
                if(Dx>Dy){
                    //响应侧滑
                    getParent().requestDisallowInterceptTouchEvent(true);//事件反拦截,只要一次满足即可
                }
                Log.e("Tag","Dx="+Dx+"----Dy="+Dy);
                break;
            case MotionEvent.ACTION_UP :
               float totalx=getScrollX();
                if(totalx>meauwidth/2) {
                    scrollmeau();
                    //scrollTo(meauwidth,getScrollY());
                }else{
                    //scrollTo(0,getScrollY());
                    scrolltozero();
                }
                Log.e("Tag","onTouchEvent---ACTION_UP");
                break;
        }
        return true;
    }
    public void scrolltozero() {
        int distance=0-getScrollX();
        scroller.startScroll(getScrollX(),getScrollY(),distance,0);
        invalidate();
        //当关闭的时候
        if(listener!=null) {
            listener.OnClose(this);
        }
    }
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            int currX = scroller.getCurrX();
            scrollTo(currX,0);
            invalidate();
        }
    }
    public void scrollmeau() {
        int distance=meauwidth-getScrollX();
        scroller.startScroll(getScrollX(),getScrollY(),distance,0);
        invalidate();
        if(listener!=null) {
            listener.OnOpen(this);
        }
    }
    public void setListener(onStatechangeListener listener) {
        this.listener = listener;
    }
    private onStatechangeListener listener;
    public interface onStatechangeListener{
        void OnOpen(Myitem myitem);
        void OnClose(Myitem myitem);
        void OnDown(Myitem myitem);
    }
}
