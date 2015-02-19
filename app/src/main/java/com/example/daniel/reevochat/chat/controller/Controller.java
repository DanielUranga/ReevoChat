package com.example.daniel.reevochat.chat.controller;

import android.util.Log;

import com.example.daniel.reevochat.chat.view.ChatActivity;

import jerklib.ConnectionManager;
import jerklib.Profile;
import jerklib.Session;
import jerklib.events.IRCEvent;
import jerklib.events.JoinCompleteEvent;
import jerklib.events.MessageEvent;
import jerklib.listeners.IRCEventListener;

public class Controller implements IRCEventListener {

    ChatActivity view;

    public Controller(ChatActivity view) {

        this.view = view;

        Profile profile = new Profile("default", "ReevoTest", "ReevoTest2");

        ConnectionManager manager = new ConnectionManager(profile);
        Session session = manager.requestConnection("irc.freenode.net");
        session.addIRCEventListener(this);
    }

    @Override
    public void receiveEvent(IRCEvent e) {
        switch (e.getType()) {
            case CHANNEL_MESSAGE:
                MessageEvent me = (MessageEvent) e;
                view.addMessage("<" + me.getNick() + ">" + " : " + me.getMessage() + "\n");
                break;
            case CONNECT_COMPLETE:
                e.getSession().join("#reevo");
                break;
            case JOIN_COMPLETE:
                JoinCompleteEvent jce = (JoinCompleteEvent) e;
                jce.getChannel().say("Hello from Jerklib");
                break;
            default:
                Log.d("event", e.getRawEventData());
                break;
        }
    }

}
