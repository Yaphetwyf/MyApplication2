package com.example.yaphet.aview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button sin;
    private Button cos;
    private SurfaceView surfaceView_main;
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    final int HIGHT=320;
    final int WIDTH=768;
    final int x_offset=5;
    private int cx=x_offset;
    int centery=HIGHT/2;
    Timer timer=new Timer();
    TimerTask task=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cos = (Button) findViewById(R.id.cos);
        sin = (Button) findViewById(R.id.sin);
        surfaceView_main = (SurfaceView)findViewById(R.id.show);

        //初始化SurfaceView
        surfaceHolder=surfaceView_main.getHolder();
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
               Toast.makeText(MainActivity.this,"测试",Toast.LENGTH_SHORT).show();
                drawBack(surfaceHolder);
                cx=x_offset;
                if(task!=null) {
                    task.cancel();
                }
                task=new TimerTask() {
                    @Override
                    public void run() {
                        int cy=centery-(int)(100*Math.sin((cx-5)*2*Math.PI/150));
                        Canvas canvas=surfaceHolder.lockCanvas(new Rect(cx,cy-2,cx+2,cy+2));
                        canvas.drawPoint(cx,cy,paint);
                        cx++;
                        if(cx>WIDTH) {
                            task.cancel();
                            task=null;
                        }
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                };
            timer.schedule(task,0,30);
            }
        });
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder surfaceHolder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                drawBack(surfaceHolder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                timer.cancel();
            }
        });
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Makecos();
            }
        });
    }

    private void Makesin() {
       this.setContentView(new Myview(this,null));
        Toast.makeText(this,"sin函数",Toast.LENGTH_SHORT).show();
    }

    private void Makecos() {

    }

    private void drawBack(SurfaceHolder surfaceHolder){
        Canvas canvas=surfaceHolder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawLine(x_offset,centery,WIDTH,centery,paint);//画x轴
        canvas.drawLine(x_offset,40,x_offset,HIGHT,paint);//画y轴
        surfaceHolder.unlockCanvasAndPost(canvas);

        surfaceHolder.lockCanvas(new Rect(0,0,0,0));
        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}

