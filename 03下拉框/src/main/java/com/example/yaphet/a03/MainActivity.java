package com.example.yaphet.a03;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText et_main_input;
    private ImageView IV_main_right;
    private PopupWindow popupWindow;
    private ListView listView;
    private List<String> stringList=new ArrayList<>();
    private mylistAdapter mylistAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_main_input = (EditText)findViewById(R.id.et_main_input);
        IV_main_right = (ImageView)findViewById(R.id.IV_main_right);
        IV_main_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(popupWindow==null) {
                   popupWindow=new PopupWindow(MainActivity.this);
                   int dip2px = DensityUtil.dip2px(MainActivity.this, 200);
                   popupWindow.setHeight(dip2px);
                   popupWindow.setWidth(et_main_input.getWidth());
                   popupWindow.setContentView(listView);
                   popupWindow.setBackgroundDrawable(new BitmapDrawable());//设置了之后才可以动画
                   popupWindow.setFocusable(true);//设置焦点，否则点击事件不起作用

                   listView.setBackgroundResource(R.drawable.listview_background);
                   popupWindow.setContentView(listView);
               }
                popupWindow.showAsDropDown(et_main_input,0,0);
            }
        });
        for (int j=0;j<=10;j++){
            stringList.add(j+"aaaaaaaaa"+j);
        }
        listView=new ListView(MainActivity.this);
        mylistAdapter1=new mylistAdapter();
        listView.setAdapter(mylistAdapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String massage=stringList.get(i);
                et_main_input.setText(massage);
                if(popupWindow!=null&popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow=null;
                }
            }
        });
    }
    class mylistAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return stringList.size();
        }

        @Override
        public Object getItem(int i) {
            return stringList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup viewGroup) {
            View view;
            ViewHolder viewHolder;
            String string=stringList.get(position);

            if(convertview==null) {
                view= View.inflate(MainActivity.this,R.layout.iv_item,null);
                viewHolder=new ViewHolder();
                viewHolder.stringname=(TextView)view.findViewById(R.id.tv_item_middle_word);
                viewHolder.peopledelete=(ImageView)view.findViewById(R.id.iv_item_right);
                view.setTag(viewHolder);
            }else{
                view=convertview;
                viewHolder=(ViewHolder)view.getTag();
            }
            final String msg=stringList.get(position);
            viewHolder.stringname.setText(msg);
            viewHolder.peopledelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stringList.remove(msg);//移除消息
                    mylistAdapter1.notifyDataSetChanged();//刷新适配器
                }
            });
            return view;
        }
        class ViewHolder{
            TextView  stringname;
            ImageView peopledelete;
        }
    }
}
