package com.example.yaphet.a05;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by WYF on 2017/9/22.
 * 作用：自定义属性
 */

public class myAttributeView extends View {
    private int myage;
    private String myname;
    private Bitmap myBackground;
    /**
     * 采用pull解析activity_main.xml文件时，当遇见类时，会拿到这个类的全类名进行反射，以反射的方式进行实例化。系统会把属性封装在myAttributeView的AttributeSet参数中
     * @param context
     * @param attrs
     */
    public myAttributeView(Context context, @Nullable AttributeSet attrs) {//带有两个参数的构造方法，可以在布局中使用
        super(context, attrs);
        /**
         * 获取属性的三种方式
         * 1.用命名空间获取--"http://schemas.android.com/apk/res-auto"
         * 2.遍历属性集合，挨个取出来
         * 3.使用系统工具，获取属性
         */
        //1
        String age = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "my_age");
        String name = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "my_name");
        String background = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "my_background");
        System.out.println(age+"---"+name+"---"+background);
        //2
        for(int i = 0; i < attrs.getAttributeCount(); i++) {
          System.out.println(attrs.getAttributeName(i)+attrs.getAttributeValue(i));
        }
        //3,用系统工具获取属性
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.myAttributeView);
        for(int i = 0; i <typedArray.getIndexCount() ; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.myAttributeView_my_age :
                    myage=typedArray.getInt(index,0);
                    break;
                case R.styleable.myAttributeView_my_name :
                   myname= typedArray.getString(index);
                    break;
                case R.styleable.myAttributeView_my_background :
                    Drawable drawable = typedArray.getDrawable(index);
                    BitmapDrawable bitmapDrawable= (BitmapDrawable) drawable;
                    myBackground=bitmapDrawable.getBitmap();
                    break;
            }
        }
        typedArray.recycle();//回收资源
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        canvas.drawText(myage+myname,100,10,paint);
        canvas.drawBitmap(myBackground,20,20,paint);
    }
}
