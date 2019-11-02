package com.example.bigwork;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FifthFragment extends Fragment implements Runnable{

    private String TAG = "FifthFragment";
    private View view;
    private Handler handler;
    private TextView fifth_text;

    public FifthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_fifth, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fifth_text = (TextView) getView().findViewById(R.id.fifth_text);
        fifth_text.setMovementMethod(ScrollingMovementMethod.getInstance());

        Thread thread = new Thread(this);
        thread.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 5){
                    String all = (String) msg.obj;
                    fifth_text.setText(all);
                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    public void run() {
        String all = "";
        try {
            Thread.sleep(1000);

            String url = "https://tech.sina.com.cn/";
            Document doc = Jsoup.connect(url).get();
            Elements divs = doc.getElementsByClass("tech-news");

            Elements lis = divs.select("ul").select("li").select("a");

            List<Item> itemList = new ArrayList<Item>();

            for (int i = 0;i<lis.size();i++) {
                if (lis.get(i).text().equals("黑猫投诉")){
                    continue;
                }
                String str = lis.get(i).text();
                String val = lis.get(i).attr("href");

                Document doc_temp = Jsoup.connect(val).get();
                Elements ps_temp = doc_temp.getElementsByClass("article");

                String all_temp = "";

                for (int j = 0;j<ps_temp.size();j++){
                    String piece_temp = ps_temp.get(j).text();
                    piece_temp = piece_temp.trim();
                    all_temp = all_temp + piece_temp + "\n";

                }
                itemList.add(new Item(str,all_temp,5));
            }

            ItemManager manager = new ItemManager(getActivity());
            manager.addAll(itemList);

            String url_new = lis.get(2).attr("href");

            Document doc_new = Jsoup.connect(url_new).get();
            Elements ps = doc_new.getElementsByClass("article");

            for (int i = 0;i<ps.size();i++){
                String piece = ps.get(i).text();
                piece = piece.trim();
                all = all + piece + "\n";
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Message msg = handler.obtainMessage(5);
        msg.obj = all;
        handler.sendMessage(msg);
    }
}
