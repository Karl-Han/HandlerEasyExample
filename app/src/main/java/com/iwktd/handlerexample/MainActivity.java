package com.iwktd.handlerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Another.Callback {
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            String str = (String) msg.obj;
            updateUI(str);
        }
    };

    final Handler postHandler = new Handler();
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MainActivity self = this;
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Another another = new Another();
                //another.setCallback(self);
                dialog = ProgressDialog.show(MainActivity.this, "",
                        "Loading. Please wait...", true);
                another.setHandler(postHandler, MainActivity.this, MainActivity.this);
                another.newThread();
            }
        });
    }

    private void updateUI(String string) {
        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(string);
    }

    @Override
    public void onNewMessage(String string) {
        Message msg = handler.obtainMessage();
        msg.obj = string;
        handler.sendMessage(msg);
    }

    public void login(){
        //MainActivity.dialog.dismiss();
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, NewAc.class);
        startActivity(intent);
    }
}

