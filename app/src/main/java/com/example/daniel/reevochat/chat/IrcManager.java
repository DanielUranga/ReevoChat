package com.example.daniel.reevochat.chat;

import android.util.Log;

import jerklib.Channel;
import jerklib.ConnectionManager;
import jerklib.Profile;
import jerklib.Session;
import jerklib.events.IRCEvent;
import jerklib.events.JoinCompleteEvent;
import jerklib.events.MessageEvent;
import jerklib.listeners.IRCEventListener;

public class IrcManager implements IRCEventListener {

    ChatActivity activity;
    Channel reevo;

    public IrcManager(ChatActivity activity, String loginName) {
        this.activity = activity;
        Profile profile = new Profile("default", loginName, loginName+"2");
        ConnectionManager manager = new ConnectionManager(profile);
        Session session = manager.requestConnection("irc.freenode.net");
        session.addIRCEventListener(this);
    }

    public void sayInChannel(String text) {
        if (reevo!=null) {
            reevo.say(text);
        }
    }

    @Override
    public void receiveEvent(IRCEvent e) {
        switch (e.getType()) {
            case CHANNEL_MESSAGE:
                MessageEvent me = (MessageEvent) e;
                activity.addMessage("<" + me.getNick() + ">" + " : " + me.getMessage() + "\n");
                break;
            case CONNECT_COMPLETE:
                e.getSession().join("#reevo");
                break;
            case JOIN_COMPLETE:
                activity.addMessage("Connected!");
                JoinCompleteEvent jce = (JoinCompleteEvent) e;
                reevo = jce.getChannel();
                break;
            default:
                Log.d("event", e.getRawEventData());
                break;
        }
    }
}
