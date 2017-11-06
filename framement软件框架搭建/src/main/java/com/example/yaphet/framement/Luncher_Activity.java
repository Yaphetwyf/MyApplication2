package com.example.yaphet.framement;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.yaphet.framement.activity.MainActivity;

public class Luncher_Activity extends Activity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_luncher_);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //在主线程执行
                Intent intent=new Intent(Luncher_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
