package com.example.aoplib;

import android.util.Log;

/**
 * Created by little on 2017/4/17 0017.
 */

public class AOPLog {

    public static void e (String tag, String msg) {
        Log.e("from AOPLog : " + tag,"" + msg);
    }

}
