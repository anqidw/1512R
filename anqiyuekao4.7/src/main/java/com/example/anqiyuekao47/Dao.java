package com.example.anqiyuekao47;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * author:Created by WangZhiQiang on 2018/4/7.
 */

public class Dao {
    private final SQLiteDatabase rd;

    public Dao(Context context) {
        Sql myHelper = new Sql(context);
        rd = myHelper.getReadableDatabase();
    }

    //先删除再添加
    public void insertData(String url, String json) {
        rd.delete("user", "url=?", new String[]{json});
        ContentValues values = new ContentValues();
        values.put("url", url);
        values.put("json", json);
        rd.insert("user", null, values);
    }

    //查询
    public String queryData(String url) {
        String json = "";
        Cursor cursor = rd.query("user", null, "url=?", new String[]{url}, null, null, null);
        while (cursor.moveToNext()) {
            json = cursor.getString(cursor.getColumnIndex("json"));
        }

        return json;
    }
}
