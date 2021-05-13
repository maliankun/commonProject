package com.inspur.osp.util;

import android.util.Log;

public class L {

    public static final String TAG = "L";

    public static void D(Object object) {
        Log.d(TAG, object == null ? "null" : object.toString());
    }
}
