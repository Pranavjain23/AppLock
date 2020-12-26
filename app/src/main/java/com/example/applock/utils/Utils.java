package com.example.applock.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Process;

public class Utils {


    public  static boolean checkPermission(Context ctx){
        AppOpsManager appOpsManager = (AppOpsManager)ctx.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(),ctx.getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;

    }
}
