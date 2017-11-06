package com.example.yaphet.framement.Fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yaphet.framement.R;
import com.example.yaphet.framement.base.baseFragment;

/**
 * Created by WYF on 2017/11/6.
 */

public class FirstFramnet extends baseFragment {
    private ListView listView;
    private ArrayAdapter<String>arrayAdapter;
    private String[] data={"OKHttp", "xUtils3","Retrofit2","Fresco","Glide","greenDao","RxJava","volley","Gson","FastJson","picasso","evenBus","jcvideoplayer","pulltorefresh","Expandablelistview","UniversalVideoView"};
    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.listviwe_first, null);
        listView =(ListView)view.findViewById(R.id.list_view);
        return view;
    }

    @Override
    protected void initdate() {
        super.initdate();
        arrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new Mylistener());
    }

    private class Mylistener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String s = data[position];
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        }
    }
}
