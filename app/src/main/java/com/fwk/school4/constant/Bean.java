package com.fwk.school4.constant;

import android.content.Context;
import android.content.SharedPreferences;

import com.fwk.school4.MyApp;


/**
 * Created by fanwenke on 16/8/19.
 */
public class Bean {
    public static final SharedPreferences SP = MyApp.getContext()
            .getSharedPreferences(Keyword.APPCONTEXT, Context.MODE_PRIVATE);
}
