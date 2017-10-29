package com.example.yaphet.a06;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by WYF on 2017/9/23.
 * 仿照Viewpager
 */

public class myViewpager extends ViewGroup {
    /**
     * //手势识别器的使用（识别你的手势，比如在屏幕上双击，单击，滑动等）
     * 1.定义成成员变量
     * 2.实例化，把想要的方法进行重写
     * 3.在onTouchEvent方法中把事件传递给手势识别器
     *
     *
     */
    private GestureDetector detector;
    private int currentindex;
    /**
     * 系统有定义好的类Scroller，效果和myScroller一样
     */
    private myScroller scroller;

    public myViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    private void initview(Context context) {
        scroller=new myScroller();
        //实例化手势识别器
        detector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            /**
             *滑动事件
             * @param e1 第一次点击的事件
             * @param e2  第二次离开的事件
             * @param distanceX 在x轴滑动的距离
             * @param distanceY  y轴滑动的距离
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                scrollBy((int) distanceX,getScrollY());//所有的view都有这个方法，让自己的内容发生变化，而自己不发生变化
                //scrollby根据自己当前的位置进行移动的，scrollto是具体移动到那个坐标点去。scrollyby的内部就是scrollyto
                //getScrollY()创建的时候默认的一个起始值
                return true;//返回true代表我们处理事件了
            }
        });
    }

    /**
     * 1.测量的时候多次测量
     * 2.widthMeasureSpec是父层视图给当前视图的宽和模式
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        MeasureSpec.getSize(widthMeasureSpec);
        MeasureSpec.getMode(widthMeasureSpec);
        MeasureSpec.getSize(heightMeasureSpec);
        MeasureSpec.getMode(heightMeasureSpec);//系统给出的工具类，测出宽高和模式

        for(int i = 0; i <getChildCount() ; i++) {
            View childAt = getChildAt(i);
            childAt.measure(widthMeasureSpec,heightMeasureSpec);
        }
    }
    /**
     *设置视图布局
     * @param b
     * @param i
     * @param i1
     * @param i2
     * @param i3
     */
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
    //遍历孩子，给每个孩子指定在屏幕上的坐标位置
        for(int i4 = 0; i4 < getChildCount(); i4++) {
            View view=getChildAt(i4);
            view.layout(i4*getWidth(),0,(i4+1)*getWidth(),getHeight());
        }
    }
    private float startposition;
    private float endpotion;
    private float sildpotion;

    /**
     * 返回true则表示拦截该事件，事件不继续往下分发，交给自身视图的onTouchEvent处理
     * @param ev
     * @return
     */
    private boolean onInterceptTouchEvent =false;
    private float startxx;
    private float startyy;
    private float endxx;
    private float endyy;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {//事件拦截机制
        detector.onTouchEvent(ev);//拦截的时候没有onTouchEvent的down事件。导致左右移动的时候onScroll会猛增，所以要将移动事件回传给手势识别器
        switch (ev.getAction()) {
            case  MotionEvent.ACTION_DOWN:
                Log.e("TAG","onInterceptTouchEvent--ACTION_DOWN");
                startxx=ev.getX();
                startyy=ev.getY();
                break;
            case  MotionEvent.ACTION_MOVE:
                Log.e("TAG","onInterceptTouchEvent--ACTION_MOVE");
                endxx= ev.getX();
                endyy=ev.getY();
                float disxx=Math.abs(endxx-startxx);//取绝对值
                float disyy=Math.abs(endyy-startyy);
                if(disxx>disyy&disxx>10) {
                    return true;
                }else{
                    movetoindext(currentindex);
                }
                break;
            case  MotionEvent.ACTION_UP:
                Log.e("TAG","onInterceptTouchEvent--ACTION_UP");
                break;
        }
        return onInterceptTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        detector.onTouchEvent(event);//把事件传递给手势识别器
        switch (event.getAction()) {
            case  MotionEvent.ACTION_DOWN:

                startposition= event.getX();
                Log.e("TAG","ACTION_DOWN_onTouchEvent"+startposition);
                break;
            case  MotionEvent.ACTION_MOVE:
                Log.e("TAG","ACTION_MOVE_onTouchEvent"+startposition);
                break;
            case  MotionEvent.ACTION_UP:
                endpotion=event.getX();

                int potionindex=currentindex;
                sildpotion=endpotion-startposition;
                if(endpotion-startposition>getWidth()/2) {
                    potionindex--;
                }else if(startposition-endpotion>getWidth()/2) {
                    potionindex++;
                }
                movetoindext(potionindex);
               Log.e("TAG","ACTION_UP_onTouchEvent"+sildpotion);

                break;
        }
        return true;
    }

    /**
     * 屏蔽非法值，根据位置移动到指定页面
     * @param potionindex
     */
    public void movetoindext(int potionindex) {
        if(potionindex<0){
            potionindex=0;
        }
        if(potionindex>getChildCount()-1){
            potionindex=getChildCount()-1;
        }
         currentindex=potionindex;//当前页面下标的位置
        if(pagerChangelistener!=null) {
            pagerChangelistener.onScrollTopager(currentindex);
        }
        /**
         * 解决回弹生硬
         */
        int distance=currentindex*getWidth()-getScrollX();//要自动移动的总距离，目标的位置减去起始位置
        scroller.startScroll(getScrollX(),getScrollY(),distance,0);
        invalidate();//重新绘制

       // scrollTo(currentindex*getWidth(),getScrollY());//移动到指定的视图的位置，currentindex*getWidth()可以理解成视图左顶点的坐标就是视图的坐标
    }

    @Override
    public void computeScroll() {//在重新绘制之后，会调用ondraw和competeScroll函数
        if(scroller.competeScolloffset()) {
        float currentx=scroller.getCurrentx();
            scrollTo((int)currentx,0);
            invalidate();
        }
    }
    /**
     * 定义一个返回值的接口，监听页面的改变
     */
    public interface onPagerChangelistener{//注意定义接口的使用，重点
        /**
         * 当页面改变的时候回调这个方法
         * @param potion 并且把当前的页面的下标回传
         */
        void onScrollTopager(int potion);
    }
    private onPagerChangelistener pagerChangelistener;
    public void setOnPagerChangeListener(onPagerChangelistener listener){
        pagerChangelistener=listener;
    }
}
