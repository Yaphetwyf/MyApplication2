package com.example.yaphet.a07;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView Lv_main_listview;
    private TextView tv_main_centerword;
    private Rightwords Right_view_words;
    private String [] words={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                if(tv_main_centerword.isShown()){
                    tv_main_centerword.setVisibility(View.GONE);
                }
            }
        }
    };
    private List<Person> persons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Lv_main_listview= (ListView) findViewById(R.id.Lv_main_listview);
        tv_main_centerword = (TextView)findViewById(R.id.tv_main_centerword);
        Right_view_words = (Rightwords)findViewById(R.id.Right_view_words);
        Right_view_words.setonWordChangerlistener(new Rightwords.onWordChangerlistener() {
            @Override
            public void onWordtoCenter(int wordpotion) {
                    tv_main_centerword.setVisibility(View.VISIBLE);
                    tv_main_centerword.setText(words[wordpotion]);
                    String s=words[wordpotion];
                    updatelistview(s);
                    //发消息之前把消息清空
                    handler.removeCallbacksAndMessages(null);//避免消息越来越密
                    handler.sendEmptyMessageDelayed(0,3000);
            }
        });
        initPerson();
        PersonAdapter personAdapter=new PersonAdapter();
        Lv_main_listview.setAdapter(personAdapter);
    }

    private void updatelistview(String s) {
        for(int i = 0; i < persons.size(); i++) {
            String substring = persons.get(i).getPingying().substring(0, 1);
            if(substring.equals(s)) {
                Lv_main_listview.setSelection(i);//定位到ListVeiw中的某个位置
                return;
          }
        }
    }

    public class PersonAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return persons.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if(convertView==null) {
            view=View.inflate(MainActivity.this,R.layout.person_item,null);
            viewHolder=new ViewHolder();
            viewHolder.personname= (TextView) view.findViewById(R.id.tv_name);
            viewHolder.personpinyin= (TextView) view.findViewById(R.id.tv_pingyin);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        String pingying = persons.get(position).getPingying().substring(0,1);
        String name = persons.get(position).getName();
        //隐藏相同的部分的拼音
        if(position==0) {
            viewHolder.personpinyin.setVisibility(View.VISIBLE);
        }else{
            String substring = persons.get(position - 1).getPingying().substring(0, 1);
            if(pingying.equals(substring)){
                viewHolder.personpinyin.setVisibility(View.GONE);
            }else {
                viewHolder.personpinyin.setVisibility(View.VISIBLE);
            }
        }
        viewHolder.personpinyin.setText(pingying);
        viewHolder.personname.setText(name);
        return view;
    }
}
    static class ViewHolder{
        TextView personname;
        TextView personpinyin;
    }
    /**
     * 添加数据
     */
    private void initPerson() {
        persons=new ArrayList<>();
        persons.add(new Person("阿大"));
        persons.add(new Person("啊二"));
        persons.add(new Person("把三"));
        persons.add(new Person("包思"));
        persons.add(new Person("成数据"));
        persons.add(new Person("层的"));
        persons.add(new Person("当但是"));
        persons.add(new Person("等的"));
        persons.add(new Person("饿了"));
        persons.add(new Person("发二"));
        persons.add(new Person("父二"));
        persons.add(new Person("方二"));
        persons.add(new Person("份二"));
        persons.add(new Person("高二"));
        persons.add(new Person("该二"));
        persons.add(new Person("会二"));
        persons.add(new Person("回二"));
        persons.add(new Person("童天天"));
        persons.add(new Person("宋二"));
        persons.add(new Person("吖二"));
        persons.add(new Person("腌二"));
        persons.add(new Person("腌二"));
        persons.add(new Person("王亚峰"));
        persons.add(new Person("我二"));
        persons.add(new Person("王二"));
        persons.add(new Person("张晓飞"));
        persons.add(new Person("杨光福"));
        persons.add(new Person("胡继群"));
        persons.add(new Person("刘畅"));

        persons.add(new Person("钟泽兴"));
        persons.add(new Person("尹革新"));
        persons.add(new Person("安传鑫"));
        persons.add(new Person("张骞壬"));

        persons.add(new Person("温松"));
        persons.add(new Person("李凤秋"));
        persons.add(new Person("刘甫"));
        persons.add(new Person("娄全超"));
        persons.add(new Person("张猛"));

        persons.add(new Person("王英杰"));
        persons.add(new Person("李振南"));
        persons.add(new Person("孙仁政"));
        persons.add(new Person("唐春雷"));
        persons.add(new Person("牛鹏伟"));
        persons.add(new Person("姜宇航"));

        persons.add(new Person("刘挺"));
        persons.add(new Person("张洪瑞"));
        persons.add(new Person("张建忠"));
        persons.add(new Person("侯亚帅"));
        persons.add(new Person("刘帅"));

        persons.add(new Person("乔竞飞"));
        persons.add(new Person("徐雨健"));
        persons.add(new Person("吴亮"));
        persons.add(new Person("王兆霖"));

        persons.add(new Person("阿三"));
        persons.add(new Person("李博俊"));
        //数组内排序
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getPingying().compareTo(o2.getPingying());
            }
        });
    }
}
