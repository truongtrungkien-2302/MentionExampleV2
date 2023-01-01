package com.example.mentionexample;

import android.text.Spannable;

public class Message {
    private Spannable Message;
    private String CreateMessage;

    public Message(Spannable strMessage) {
        this.Message = strMessage;
    }

    public Spannable getMessage() {
        return Message;
    }

    public String getCreateMessage() {
        return CreateMessage;
    }

    public void setMessage(Spannable message) {
        this.Message = message;
    }

    public void setCreateMessage(String createMessage) {
        this.CreateMessage = createMessage;
    }
}
