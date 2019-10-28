package com.example.bigwork;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FourthListFragment extends Fragment implements Runnable {

    private Handler handler;
    private View view;
    private MyAdapter myAdapter;
    private ListView listView;

    public FourthListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fourth_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Thread thread = new Thread(this);
        thread.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 4){
                    final List<Item> list = (List<Item>) msg.obj;
                    myAdapter = new MyAdapter(getContext(),R.layout.list_item,list);
                    listView = (ListView) getView().findViewById(R.id.fourth_content);
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
            if (item.getTag() == 4){
                retList.add(item);
            }
        }

        Message msg = handler.obtainMessage(4);
        msg.obj = retList;
        handler.sendMessage(msg);
    }
}
