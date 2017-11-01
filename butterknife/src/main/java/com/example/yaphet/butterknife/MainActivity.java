package com.example.yaphet.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class MainActivity extends Activity {

    @Bind(R.id.tv_main_textview)
    TextView tvMainTextview;
    @Bind(R.id.cb_main_Box)
    CheckBox cbMainBox;
    @Bind(R.id.bt_main_button)
    Button btMainButton;//初始化完毕

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//光标留在main后面。alt+shift+s---》generate Butterknife
        ButterKnife.bind(this);
        initDate();
    }
    private void initDate() {
        tvMainTextview.setText("我好喜欢Butterknife");
    }
    @OnClick(R.id.cb_main_Box)
    void cbButterknife(View v){
        Toast.makeText(MainActivity.this, "点击了CheckBox", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.bt_main_button)
    void btButterKnife(View v){//view可以给也可以不给
        Toast.makeText(MainActivity.this, "点击了Button", Toast.LENGTH_SHORT).show();
    }
}
