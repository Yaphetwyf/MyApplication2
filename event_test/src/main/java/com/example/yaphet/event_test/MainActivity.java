package com.example.yaphet.event_test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends Activity {
    private Button bt_main_Busreceived;
    private Button bt_main_Busreceivedsticky;
    private TextView tv_main_massage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewListener();
        EventBus.getDefault().register(this);
    }

    private void initViewListener() {
        bt_main_Busreceived.setOnClickListener(new MyViewListener());
        bt_main_Busreceivedsticky.setOnClickListener(new MyViewListener());
        tv_main_massage.setOnClickListener(new MyViewListener());
    }
    private void initView() {
        bt_main_Busreceived = findViewById(R.id.bt_main_Busreceived);
        bt_main_Busreceivedsticky = findViewById(R.id.bt_main_Busreceivedsticky);
        tv_main_massage = findViewById(R.id.tv_main_massage);
    }

    private class MyViewListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_main_Busreceived :
            Toast.makeText(MainActivity.this, "Busreceived", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,EventBusActivity.class);
                    startActivity(intent);
                    break;
                case R.id.bt_main_Busreceivedsticky :
            Toast.makeText(MainActivity.this, "Busreceivedsticky", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().postSticky(new StickyMassage("我是粘连事件消息"));
                    Intent intent1=new Intent(MainActivity.this,EventBusActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(Evemassage evemassage){
        String name = evemassage.name;
        tv_main_massage.setText(name);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
}
