package com.example.yaphet.event_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.yaphet.event_test.R.id.tv_main_massage;

public class EventBusActivity extends AppCompatActivity {
    private Button bt_bus_send;
    private Button bt_bus_send_sticky;
    private TextView bt_bus_textxt;
    private Boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        initView();
        initViewListener();
    }

    private void initViewListener() {
        bt_bus_send.setOnClickListener(new MyLitener());
        bt_bus_send_sticky.setOnClickListener(new MyLitener());
    }

    private void initView() {
        bt_bus_send = (Button)findViewById(R.id.bt_bus_send);
        bt_bus_send_sticky = (Button)findViewById(R.id.bt_bus_send_sticky);
        bt_bus_textxt = (TextView)findViewById(R.id.bt_bus_textxt);
    }

    private class MyLitener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case  R.id.bt_bus_send:
            //Toast.makeText(EventBusActivity.this, "post普通事件", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new Evemassage("post普通消息"));
                    finish();
                    break;
                case  R.id.bt_bus_send_sticky:
                    if(flag) {
                        flag=false;
                        EventBus.getDefault().register(EventBusActivity.this);
                    }

            Toast.makeText(EventBusActivity.this, "post粘连事件", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
    @Subscribe(threadMode= ThreadMode.MAIN,sticky = true)
    public void StickyEventMassage(StickyMassage stickyMassage){
        String name = stickyMassage.name;
        bt_bus_textxt.setText(name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(EventBusActivity.this);
    }
}
