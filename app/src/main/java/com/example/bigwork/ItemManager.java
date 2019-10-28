package com.example.bigwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private DBHelper dbHelper;
    private String TBNAME;

    public ItemManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(Item item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curtitle",item.getCurTitle());
        values.put("cururl",item.getCurUrl());
        values.put("curtag",item.getTag());
        db.insert(TBNAME,null,values);
        db.close();
    }

    public void addAll(List<Item> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (Item item : list){
            ContentValues values = new ContentValues();
            values.put("curtitle",item.getCurTitle());
            values.put("cururl",item.getCurUrl());
            values.put("curtag",item.getTag());
            db.insert(TBNAME,null,values);
        }
        db.close();
    }

    public List<Item> listAll(){
        List<Item> itemList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME,null,null,null,null,null,null);
        if (cursor != null){
            itemList = new ArrayList<Item>();
            while (cursor.moveToNext()){
                Item item = new Item();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurTitle(cursor.getString(cursor.getColumnIndex("CURTITLE")));
                item.setCurUrl(cursor.getString(cursor.getColumnIndex("CURURL")));
                item.setTag(cursor.getInt(cursor.getColumnIndex("CURTAG")));
                itemList.add(item);
            }
            cursor.close();
        }
        db.close();
        return itemList;
    }
}
