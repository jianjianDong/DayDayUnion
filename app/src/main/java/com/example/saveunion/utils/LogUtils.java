package com.example.saveunion.utils;

import android.util.Log;

public class LogUtils {

     static final int CURRENTLEV = 4;
     static final int DEBUGLEV = 4;
     static final int INFOLEV = 3;
     static final int WARNLEV = 2;
     static final int ERRORLEV = 1;

    public static void d(Class c, String log) {
        if (CURRENTLEV >= DEBUGLEV) {
            Log.d(c.getSimpleName(), log);
        }
    }

    public static void i(Class c, String log) {
        if (CURRENTLEV >= INFOLEV) {
            Log.i(c.getSimpleName(), log);
        }
    }
    public static void w(Class c, String log) {
        if (CURRENTLEV >= WARNLEV) {
            Log.w(c.getSimpleName(), log);
        }
    }
    public static void e(Class c, String log) {
        if (CURRENTLEV >= ERRORLEV) {
            Log.e(c.getSimpleName(), log);
        }
    }


}
