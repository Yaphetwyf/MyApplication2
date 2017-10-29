package com.example.yaphet.a06;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity {
    private myViewpager viewpager;
    private int[] ids={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6};
    private RadioGroup rg_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = (myViewpager)findViewById(R.id.mv_main_myViewpager);
        rg_main = (RadioGroup)findViewById(R.id.rg_main);
        //添加界面
        for(int i = 0; i <ids.length; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(ids[i]);
            //把图片添加在Viewgroup
            viewpager.addView(imageView);
        }
        View inflate = View.inflate(this, R.layout.textview, null);
        viewpager.addView(inflate);//加入测试页

        for(int i = 0; i <viewpager.getChildCount() ; i++) {
            RadioButton button=new RadioButton(this);
            button.setId(i);//把button的ID设置为0到5；
            if(i==0) {
                button.setChecked(true);
            }
            rg_main.addView(button);
        }
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             *给rg_main设置内容监听
             * @param radioGroup
             * @param i 在radioGroup中选中的下标
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                viewpager.movetoindext(i);
            }
        });
        viewpager.setOnPagerChangeListener(new myViewpager.onPagerChangelistener() {//调用接口里的方法
            @Override
            public void onScrollTopager(int potion) {
                rg_main.check(potion);//设置radiogroup中被选中的位置
            }
        });
    }
}
