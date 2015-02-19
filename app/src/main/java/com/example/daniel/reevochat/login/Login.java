package com.example.daniel.reevochat.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.daniel.reevochat.R;
import com.example.daniel.reevochat.chat.view.ChatActivity;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startActivity(new Intent(this, ChatActivity.class));
    }

}
