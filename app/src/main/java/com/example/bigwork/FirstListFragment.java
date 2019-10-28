package com.example.bigwork;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstListFragment extends Fragment implements Runnable{

    private Handler handler;
    private View view;
    private MyAdapter myAdapter;
    private ListView listView;

    private String TAG = "FirstListFragment";

    public FirstListFragment() {
        // Required empty public constructor
    }


   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first_list, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Thread thread = new Thread(this);
        thread.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1){
                    final List<Item> list = (List<Item>) msg.obj;
                    myAdapter = new MyAdapter(getContext(),R.layout.list_item,list);
                    listView = (ListView) getView().findViewById(R.id.first_content);
                    listView.setAdapter(myAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Item item = list.get(position);
                            String title = item.getCurTitle();
                            String url = item.getCurUrl();

                            Intent intent = new Intent(getContext(),LastActivity.class);
                            intent.putExtra("title",title);
                            intent.putExtra("url",url);
                            startActivity(intent);
                        }
                    });
                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    public void run() {
        List<Item> retList = new ArrayList<>();

        ItemManager manager = new ItemManager(getActivity());
        for (Item item : manager.listAll()){
            if (item.getTag() == 1){
                retList.add(item);
            }
        }

        Message msg = handler.obtainMessage(1);
        msg.obj = retList;
        handler.sendMessage(msg);
    }
}
