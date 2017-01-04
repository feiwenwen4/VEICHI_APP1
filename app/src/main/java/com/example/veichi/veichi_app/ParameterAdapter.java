package com.example.veichi.veichi_app;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


public class ParameterAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> list;
    public LayoutInflater inflater;
    public ParameterAdapter(Context context,ArrayList list){//构造函数使其属性本地化方便加载数据
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {//用于获得数据的长度
        return list.size();
    }

    @Override
    public Object getItem(int position){//用于获得某一位置的数据
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {//用于获得数据的位置
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        TextView textView = new TextView(this.context);
        textView.setText(list.get(position));
        return textView;
    }
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        Holder holder;
//        if(convertView==null){
//            holder = new Holder();
//            convertView = inflater.inflate(R.layout.item_listview, null);
//            holder.name = (TextView) convertView.findViewById(R.id.item_name);
//            holder.birth = (TextView) convertView.findViewById(R.id.item_birth);
//            holder.itembtn = (Button) convertView.findViewById(R.id.item_btn);
//            convertView.setTag(holder);
//        }else{
//            holder = (Holder) convertView.getTag();
//        }
        //holder.name.setText(list.get(position).name);
       // holder.birth.setText(list.get(position).birth);
//        holder.itembtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(context, list.get(position).name+"的生日是："+list.get(position).birth, Toast.LENGTH_LONG).show();
//            }
//        });
//       return convertView;
//    }

//    protected class Holder{
//        TextView name;
//        TextView birth;
//        Button itembtn;
//    }
}