package com.example.applock.services;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class ServiceAppLockJobIntent extends JobIntentService {
    private static final int JOB_ID = 15462;

    public static void enqueueWork(Context context, Intent work){
        enqueueWork(context,ServiceAppLockJobIntent.class,JOB_ID,work);


    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

    }
}
