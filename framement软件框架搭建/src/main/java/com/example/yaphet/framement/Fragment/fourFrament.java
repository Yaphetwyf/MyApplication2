package com.example.yaphet.framement.Fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.yaphet.framement.base.baseFragment;

/**
 * Created by WYF on 2017/11/6.
 */

public class fourFrament extends baseFragment {
    private TextView textView;
    @Override
    protected View initView() {
        textView=new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        return textView;
    }

    @Override
    protected void initdate() {
        super.initdate();
        textView.setText("第四页");
    }
}
