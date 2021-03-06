package com.meng.mbrowser.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/3/13.
 */

public class sharedPreferenceHelper{

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Context context;

    public sharedPreferenceHelper(Context c,String name){
        context=c;
        sp=context.getSharedPreferences(name,0);
        editor=sp.edit();
    }

	public boolean getBoolean(String key,boolean p1){
		return sp.getBoolean(key,p1);
	}
    public void putValue(String key,String value){
        editor=sp.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public String getValue(String key,String defaultValue){
        return sp.getString(key,defaultValue);
    }

    public String getValue(String key){
        return sp.getString(key,null);
    }
    public boolean getBoolean(String key) {
        return sp.getBoolean(key,false);
    }
    public void putBoolean(String key,Boolean value){
        editor=sp.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
}

