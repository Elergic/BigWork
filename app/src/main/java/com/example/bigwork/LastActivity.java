package com.example.bigwork;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LastActivity extends AppCompatActivity {

    private TextView last_title;
    private TextView last_content;
    private String title;
    private String url;
    private Handler handler;
    private String TAG = "LastActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        last_title = (TextView) findViewById(R.id.last_title);
        last_content = (TextView) findViewById(R.id.last_content);
        last_content.setMovementMethod(ScrollingMovementMethod.getInstance());

        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");

        last_title.setText(title);
        last_content.setText(url);
    }

}
