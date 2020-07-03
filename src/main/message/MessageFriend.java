package message;

import server.java.com.linglett.pojo.Friend;

import java.io.Serializable;
import java.util.List;

public class MessageFriend extends Message implements Serializable {
    private List<Friend> list;

    public List<Friend> getList() {
        return list;
    }

    public MessageFriend(String messageFormat, String messageTextBody, String sender, String receiver,List list) {
        super(messageFormat, messageTextBody, sender, receiver);
        this.list=list;
    }

    @Override
    public String toString() {
        return "MessageFriend{" +
                "list=" + list +
                '}';
    }

    public MessageFriend(String messageFormat, String messageTextBody) {
        super(messageFormat, messageTextBody);
    }

    public void setList(List<Friend> list) {
        this.list = list;
    }
}
