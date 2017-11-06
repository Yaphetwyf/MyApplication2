package com.example.yaphet.framement.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yaphet.framement.Fragment.FirstFramnet;
import com.example.yaphet.framement.Fragment.SecondFrament;
import com.example.yaphet.framement.Fragment.ThirdFragment;
import com.example.yaphet.framement.Fragment.fourFrament;
import com.example.yaphet.framement.R;
import com.example.yaphet.framement.base.baseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    private List<baseFragment> baseFragments;
    private int position;
    private baseFragment mcontext;

    @Bind(R.id.Tv_main_title)
    TextView TvMainTitle;
    @Bind(R.id.Fl_main_FrameLayout)
    FrameLayout FlMainFrameLayout;
    @Bind(R.id.BR_main_Group)
    RadioGroup BRMainGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initradiogrouplistener();
    }

    private void initradiogrouplistener() {
        BRMainGroup.setOnCheckedChangeListener(new myListener());
        BRMainGroup.check(R.id.RB_main4_bttton1);
    }
    private void initFragment() {
        baseFragments=new ArrayList<>();
        baseFragments.add(new FirstFramnet());
        baseFragments.add(new SecondFrament());
        baseFragments.add(new ThirdFragment());
        baseFragments.add(new fourFrament());
    }
    private class myListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            switch (checkedId) {
                case  R.id.RB_main4_bttton1:
            //Toast.makeText(MainActivity.this, "1111", Toast.LENGTH_SHORT).show();
                    position=0;
                    break;
                case  R.id.RB_main4_bttton2:
                    position=1;
            //Toast.makeText(MainActivity.this, "2222", Toast.LENGTH_SHORT).show();
                    break;
                case  R.id.RB_main4_bttton3:
                    position=2;
            //Toast.makeText(MainActivity.this, "3333", Toast.LENGTH_SHORT).show();
                    break;
                case  R.id.RB_main4_bttton4:
            //Toast.makeText(MainActivity.this, "4444", Toast.LENGTH_SHORT).show();
                    position=3;
                    break;
                default:
                    position=0;
                    break;
            }
            baseFragment fram = getFrament();
            //replaceFrament(fram);
            replaceFrament1(mcontext,fram);
        }
    }
    private void replaceFrament(baseFragment fram) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Fl_main_FrameLayout,fram);
        fragmentTransaction.commit();
    }
    private void replaceFrament1(baseFragment from,baseFragment to){
        if(from!=to) {
            mcontext=to;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if(!to.isAdded()) {//没有添加就添加
                if(from!=null) {
                    fragmentTransaction.hide(from);
                }
                if(to!=null) {
                    fragmentTransaction.add(R.id.Fl_main_FrameLayout,to).commit();
                }
            }else {//已添加就要直接显示
                if(from!=null) {
                    fragmentTransaction.hide(from);
                }
                if(to!=null) {
                    fragmentTransaction.show(to).commit();
                }
            }
        }
    }
    private baseFragment getFrament(){
        if(baseFragments!=null) {
            baseFragment baseFrag = baseFragments.get(position);
            return baseFrag;
        }
       return null;
    }
}
