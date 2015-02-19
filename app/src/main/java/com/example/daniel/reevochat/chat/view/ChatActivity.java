package com.example.daniel.reevochat.chat.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.daniel.reevochat.R;
import com.example.daniel.reevochat.chat.controller.Controller;

public class ChatActivity extends Activity {

    Controller controller;
    TextView chatRoomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new Controller(this);

        setContentView(R.layout.activity_chat);
        chatRoomText = (TextView) findViewById(R.id.chatroomText);
    }

    public void addMessage(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatRoomText.append(msg);
            }
        });
    }

}
