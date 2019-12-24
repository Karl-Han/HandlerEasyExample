package com.iwktd.handlerexample;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

public class Another implements Runnable {
    private Callback mCallback;
    private Handler anotherHandler;
    private Context context;
    private Dialog dialog;
    private MainActivity mainAc;

    public interface Callback {
        void onNewMessage(String string);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public void newMessage() {
        if (mCallback != null) {
            mCallback.onNewMessage("message from Another");
        }
    }

    public void newThread() {
        Thread childThread = new Thread(this, "Another");
        childThread.start();
    }

    public void run() {
        //newMessage();
        postRes();
    }

    public void setHandler(Handler handler, Context context, MainActivity mainActivity){
        anotherHandler = handler;
        this.context = context;
        this.mainAc = mainActivity;
    }

    public void postRes(){
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        anotherHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "From another", Toast.LENGTH_SHORT).show();
                //context.dialog.dismiss();
                Intent intent = new Intent();
                intent.setClass(context, NewAc.class);
                context.startActivity(intent);
                mainAc.dialog.dismiss();
            }
        });
    }
}
