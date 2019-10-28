package com.example.bigwork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends ArrayAdapter<Item> {

    public MyAdapter(Context context, int resource, List<Item> objects){

        super(context,resource,objects);

    }

    public View getView(int postion, View convertView, ViewGroup parent) {
        Item item = getItem(postion);
        View itemView;
        ViewHolder viewHolder;
        if (convertView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) itemView.findViewById(R.id.itemTitle);
            itemView.setTag(viewHolder);
        }else {
            itemView = convertView;
            viewHolder = (ViewHolder) itemView.getTag();
        }

        viewHolder.title.setText(item.getCurTitle());

        return itemView;
    }

    class ViewHolder{
        TextView title;
    }

}
