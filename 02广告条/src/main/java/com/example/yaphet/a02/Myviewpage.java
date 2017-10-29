package com.example.yaphet.a02;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

/**
 * Created by WYF on 2017/9/19.
 */

public class Myviewpage extends PagerAdapter{
    private Handler handler1;
    private List<ImageView> imageViewss;
    private Context context1;
    private final String[] data={"第一天","第二天","第三天","第四天","第五天"};
    public Myviewpage(List<ImageView> imageViews, Handler handler, Context context) {
        this.handler1=handler;
        this.imageViewss=imageViews;
        this.context1=context;
    }

    @Override
    public int getCount() {//得到图片的总数
       return Integer.MAX_VALUE;
       // return imageViewss.size();
    }

    /**
     * 比较view和object是否是同一个实例
     * @param view view就是viewpager中的某一个页面
     * @param object object就是instantiateItem方法所返回的结果
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    /**
     * 相当于getview方法,container相当于viewpager，position是当前实例化页面的位置
     * @param container 指的就是viewpager
     * @param position 当前实例化页面的位置
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {//相当于getview方法
        int reaposition=position%imageViewss.size();
        ImageView view=imageViewss.get(reaposition);
        container.addView(view);//添加到viewpager中去
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case  MotionEvent.ACTION_DOWN://按下
                        Log.e("TAG","setOnTouchListener-------");
                        handler1.removeCallbacksAndMessages(null);
                        break;
                    case  MotionEvent.ACTION_MOVE://手指在空间上移动

                        break;
                    /*case  MotionEvent.ACTION_CANCEL://按下拖动的时候，up事件会丢失，从而触发cancel事件
                        handler1.removeCallbacksAndMessages(null);
                        handler1.sendEmptyMessageDelayed(0,4000);
                        break;*/
                    case  MotionEvent.ACTION_UP://离开
                        handler1.removeCallbacksAndMessages(null);
                        handler1.sendEmptyMessageDelayed(0,4000);
                        break;
                }
                return false;
            }
        });
        view.setTag(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=(int)view.getTag()%data.length;//获取位置
                Toast.makeText(context1, data[position], Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    /**
     *释放资源
     * @param container viewpager
     * @param position  要释放的位置
     * @param object  要释放的页面
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
