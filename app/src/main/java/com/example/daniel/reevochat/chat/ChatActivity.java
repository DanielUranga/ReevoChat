package com.example.daniel.reevochat.chat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.daniel.reevochat.R;

public class ChatActivity extends Activity {

    TextView chatRoomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String loginName = getIntent().getStringExtra("loginName");

        if (loginName==null) {
            loginName = "AndroidReevoChatUser" + new Integer((int) (Math.random()*100));
        }

        final IrcManager irc = new IrcManager(this, loginName);
        setContentView(R.layout.activity_chat);
        chatRoomText = (TextView) findViewById(R.id.chatroomText);
        final EditText chatInput = (EditText) findViewById(R.id.chatInput);
        Button btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = chatInput.getText().toString();
                addMessage(msg);
                irc.sayInChannel(msg);
                chatInput.setText("");
            }
        });

    }

    public void addMessage(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatRoomText.append(msg+"\n");
            }
        });
    }

}
