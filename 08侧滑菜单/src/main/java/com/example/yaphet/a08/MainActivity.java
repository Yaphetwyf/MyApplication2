package com.example.yaphet.a08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ListView lv_main_listview;
    private ArrayList<Content> contents;
    private Myadapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_main_listview = (ListView)findViewById(R.id.lv_main_listview);
        intnitdata();
        myadapter=new Myadapter();
        lv_main_listview.setAdapter(myadapter);
    }
    private void intnitdata() {
        contents=new ArrayList<>();
        for(int i = 0; i <30 ; i++) {
            contents.add(new Content("part"+i));
        }
    }

    class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return contents.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
                convertView=View.inflate(MainActivity.this,R.layout.list_item,null);
                viewHolder=new ViewHolder();
                viewHolder.itemcontent= (TextView) convertView.findViewById(R.id.it_content);
                viewHolder.itemMeau= (TextView) convertView.findViewById(R.id.it_meau);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            final String setcontent=contents.get(position).getContent();
            //根据设置内容
            viewHolder.itemcontent.setText(setcontent);
            viewHolder.itemcontent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, setcontent, Toast.LENGTH_SHORT).show();
                }
            });
            viewHolder.itemMeau.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contents.remove(position);
                    Myitem myitem= (Myitem) v.getParent();
                    myitem.scrolltozero();
                    myadapter.notifyDataSetChanged();
                }
            });
            Myitem myitem= (Myitem) convertView;//
            myitem.setListener(new Myitem.onStatechangeListener() {//new出来的对象每一个内存地址都不一样，不是同一个对象
                @Override
                public void OnOpen(Myitem myitem) {
                    myitemm=myitem;
                }
                @Override
                public void OnClose(Myitem myitem) {
                if(myitemm==myitem) {
                    myitemm=null;
                }
                }
                @Override
                public void OnDown(Myitem myitem) {
                    if(myitemm!=null&&myitemm!=myitem) {
                        myitemm.scrolltozero();
                    }
                }
            });
            return convertView;
        }
    }
    private Myitem myitemm;
    static class ViewHolder{
        TextView itemcontent;
        TextView itemMeau;
    }
}
