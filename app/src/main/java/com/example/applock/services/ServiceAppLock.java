package com.example.applock.services;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.applock.broadcast.RecieverAppLock;

public class ServiceAppLock extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ServiceAppLock() {
        super("ServiceAppLock");

    }
    private void runAppLock(){
        long endTime = System.currentTimeMillis()+210;
        while(System.currentTimeMillis() < endTime){
            synchronized (this){
                try {
                    Intent intent = new Intent(this, RecieverAppLock.class);
                    wait(endTime - System.currentTimeMillis());

                }catch (InterruptedException e){
                    e.printStackTrace();

                }

            }
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
