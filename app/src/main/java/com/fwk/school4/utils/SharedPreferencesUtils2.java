package com.fwk.school4.utils;

import android.content.SharedPreferences;

import com.fwk.school4.MyApp;
import com.fwk.school4.constant.Keyword;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2016/8/1.
 */
public class SharedPreferencesUtils2 {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public SharedPreferencesUtils2(){
        sp = MyApp.getContext().getSharedPreferences(Keyword.APPORIGINALDATA, MyApp.getContext().MODE_PRIVATE);
        editor = sp.edit();
    }
    public void saveToShared(String key, Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(obj);
            String value = new String(Base64.encode(out.toByteArray()));
            editor = sp.edit();
            editor.putString(key, value);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object queryForSharedToObject(String key) {

        String value = sp.getString(key, null);
        if (value != null) {
            byte[] valueBytes = Base64.decode(value);
            ByteArrayInputStream bin = new ByteArrayInputStream(valueBytes);
            try {
                ObjectInputStream oin = new ObjectInputStream(bin);

                return oin.readObject();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public void setString(String key, String value){
        editor.putString(key,value).commit();
    }
    public String getString(String key){
        return sp.getString(key,null);
    }
    public void setInt(String key, int value){
        editor.putInt(key,value).commit();
    }
    public int getInt(String key){
        return sp.getInt(key,0);
    }
    public void setboolean(String key, boolean value){
        editor.putBoolean(key,value).commit();
    }
    public boolean getBoolean(String key){
        return sp.getBoolean(key,false);
    }
    public void removData(){
        editor.clear();
        editor.commit();
    }


}
