package com.example.bigwork;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
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
public class FirstFragment extends Fragment implements Runnable{

    private String TAG = "FirstFragment";
    private Handler handler;
    private View view;
    private TextView first_text;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        first_text = (TextView) getView().findViewById(R.id.first_text);
        first_text.setMovementMethod(ScrollingMovementMethod.getInstance());

        Thread thread = new Thread(this);
        thread.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1){
                    String all = (String) msg.obj;
                    first_text.setText(all);
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

            String url = "https://news.sina.com.cn/china/";
            Document doc = Jsoup.connect(url).get();
            Elements uls1 = doc.getElementsByClass("news-1");
            Elements uls2 = doc.getElementsByClass("news-2");

            Elements lis1 = uls1.select("li");
            Elements lis2 = uls2.select("li");

            List<Item> itemList = new ArrayList<Item>();

            for (int i = 0;i < lis1.size();i++) {

                String str = lis1.get(i).text();
                String val = lis1.get(i).select("a").attr("href");

                Document doc_temp = Jsoup.connect(val).get();
                Elements ps_temp = doc_temp.getElementsByClass("article");

                String all_temp = "";

                for (int j = 0;j<ps_temp.size();j++){
                    String piece_temp = ps_temp.get(j).text();
                    piece_temp = piece_temp.trim();
                    all_temp = all_temp + piece_temp + "\n";

                }
                itemList.add(new Item(str,all_temp,1));
            }

            for (int i = 0;i < lis2.size();i++) {

                String str = lis2.get(i).text();
                String val = lis2.get(i).select("a").attr("href");

                Document doc_temp = Jsoup.connect(val).get();
                Elements ps_temp = doc_temp.getElementsByClass("article");

                String all_temp = "";

                for (int j = 0;j<ps_temp.size();j++){
                    String piece_temp = ps_temp.get(j).text();
                    piece_temp = piece_temp.trim();
                    all_temp = all_temp + piece_temp + "\n";

                }
                itemList.add(new Item(str,all_temp,1));
            }

            ItemManager manager = new ItemManager(getActivity());
            manager.addAll(itemList);

            String url_new = lis1.get(0).select("a").attr("href");

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

        Message msg = handler.obtainMessage(1);
        msg.obj = all;
        handler.sendMessage(msg);
    }
}
