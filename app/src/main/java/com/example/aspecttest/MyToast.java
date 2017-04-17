package com.example.aspecttest;

import android.content.Context;
import android.widget.Toast;

import com.example.aoplib.MyLog;

/**
 * Created by little on 2017/4/17 0017.
 */

public class MyToast {

    @MyLog
    public static void show (Context context,String string) {
        Toast.makeText(context, string,Toast.LENGTH_LONG).show();
    }
}
