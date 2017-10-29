package com.example.yaphet.a02;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager vp_main_advertise;
    private TextView tv_main_downtext;
    private LinearLayout ll_mian_pointgroup;

    private List<ImageView> imageViews;
    private final String[] data={"第一天","第二天","第三天","第四天","第五天"};
    private final int[] ids={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
    private int preposition=0;
    private boolean isscall=false;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int itenm=vp_main_advertise.getCurrentItem()+1;
            vp_main_advertise.setCurrentItem(itenm);
            handler.sendEmptyMessageDelayed(0,4000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp_main_advertise = (ViewPager)findViewById(R.id.vp_main_advertise);
        tv_main_downtext = (TextView)findViewById(R.id.tv_main_downtext);
        ll_mian_pointgroup = (LinearLayout)findViewById(R.id.ll_mian_pointgroup);
        imageViews=new ArrayList<>();//准备数据
        for (int i=0;i<ids.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(ids[i]);

            imageViews.add(imageView);//添加到imageView集合中

            //添加点
            ImageView point=new ImageView(this);
            point.setBackgroundResource(R.drawable.point_select);//默认状态下为绿色，按下为红色
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            if(i==0){
                point.setEnabled(true);
            }else{
                point.setEnabled(false);
                params.leftMargin = 30;
            }
            point.setLayoutParams(params);
            ll_mian_pointgroup.addView(point);
        }
        vp_main_advertise.setAdapter(new Myviewpage(imageViews,handler,MainActivity.this));
        tv_main_downtext.setText(data[preposition]);//默认状态下，设置为第一个位置的信息
        int item=Integer.MAX_VALUE/2-Integer.MIN_VALUE/2%imageViews.size();//要保证是imageviews的整数倍，此种设置保证默认出现在中间位置
/**
 * 设置中间位置时，默认情况下还是从0开始（但是需要0位置可以取到），可以理解为在中间位置取图片数组的长度，然后默认从这个长度里从第一个默认实例化（在第一个位置可以取到的情况下）
 */
        //设置中间位置
        int item1 = Integer.MAX_VALUE/2 - Integer.MAX_VALUE/2%imageViews.size();//要保证imageViews的整数倍
        vp_main_advertise.setCurrentItem(item1);//设置当前位置为viewpager的中间位置，功能为支持左右无线滑动
        handler.sendEmptyMessageDelayed(0,4000);
        vp_main_advertise.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 当页面滚动了以后点用这个方法
             * @param position 当前页面的位置
             * @param positionOffset  滑动页面的百分比
             * @param positionOffsetPixels 在屏幕上滑动的像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 当某个页面被选中的时候回调
             * @param position 被选中页面的位置
             */
            @Override
            public void onPageSelected(int position) {
            //设置对应位置的文本信息
                // 把上一个高亮位置设置为默认
                //当前位置设置为高亮
                int realposition=position%imageViews.size();
                tv_main_downtext.setText(data[realposition]);
                ll_mian_pointgroup.getChildAt(preposition).setEnabled(false);
                ll_mian_pointgroup.getChildAt(realposition).setEnabled(true);
                preposition=realposition;
            }
            /**
             * 页面滚动的状态发生变化时回调这个方法
             * 静止到滑动，滑动到静止，静止到拖拽。这三种状态
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {
            if(state==ViewPager.SCROLL_STATE_DRAGGING) {//拖拽
                isscall=true;
                handler.removeCallbacksAndMessages(null);
            }else if(state==ViewPager.SCROLL_STATE_IDLE&isscall) {//空闲状态
                isscall=false;
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0,4000);
            }else if(state==ViewPager.SCROLL_STATE_SETTLING) {//理解为：通过拖动/滑动，安放到了目标页，则 state = ViewPager.SCROLL_STATE_SETTLING  

            }
            }
        });

    }
}
