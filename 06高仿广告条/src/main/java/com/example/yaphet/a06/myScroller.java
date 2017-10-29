package com.example.yaphet.a06;

import android.os.SystemClock;

/**
 * Created by WYF on 2017/9/24.
 */

public class myScroller {
    /**
     * 记录起始坐标
     */
    private float startX;
    private float startY;

    private float distancex;//x轴移动的距离
    private int distanceY;//y轴移动的距离
    private long startTime;//开始的时间
    private boolean iefinish;//记录是否完成，false代表没有移动完成，true代表移动完成


    public void startScroll(float startX, float startY, float distancex, int distanceY){
        this.startX=startX;
        this.startY=startY;
        this.distancex=distancex;
        this.distanceY=distanceY;
        this.startTime= SystemClock.uptimeMillis();//系统开机一刹那到现在的时间
        this.iefinish=false;
    }
    private long totaltime=200;//总共的运动时间
    private float currentx;//当前在x方向要移动的一小段距离

    public float getCurrentx() {
        return currentx;
    }
    public void setCurrentx(float currentx) {
        this.currentx = currentx;
    }
    /**
     * 计算在x轴方向上一小段的的滑动距离
     * true代表还在滑动，false代表结束滑动
     */
    public boolean competeScolloffset(){//计算滚动的平移量
        if(iefinish) {
            return false;
        }
        long endtime=SystemClock.uptimeMillis();
        long passtime=endtime-startTime;
        if(passtime<totaltime) {
            //运动还在继续
            //计算运动的速度为
            float speedx = distancex / totaltime;
            //计算当前一小段移动的距离后的位置
            this.currentx=startX+passtime*speedx;
        }else {
            this.currentx=startX+distancex;
            iefinish=true;
        }
        return true;
    }
}
