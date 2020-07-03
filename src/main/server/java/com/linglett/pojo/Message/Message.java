package com.linglett.pojo.Message;

import java.io.Serializable;

public class Message implements Serializable {
    //0为登陆消息
    private String messageFormat;
    private String messageTextBody;
    private String sender;
    private String receiver;

    public Message(String messageFormat, String messageTextBody, String sender, String receiver) {
        this.messageFormat = messageFormat;
        this.messageTextBody = messageTextBody;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message(String messageFormat, String messageTextBody) {
        this.messageFormat = messageFormat;
        this.messageTextBody = messageTextBody;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageFormat=" + messageFormat +
                ", messageBody='" + messageTextBody + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }

    public String getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    public String getMessageTextBody() {
        return messageTextBody;
    }

    public void setMessageTextBody(String messageTextBody) {
        this.messageTextBody = messageTextBody;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
