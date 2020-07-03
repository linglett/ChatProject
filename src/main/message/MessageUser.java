package message;

import java.io.Serializable;
import java.sql.Blob;

public class MessageUser extends Message implements Serializable {
    private byte[] headPortrait;
    public MessageUser(String messageFormat, String messageTextBody, String sender, String receiver) {
        super(messageFormat, messageTextBody, sender, receiver);
    }

    public MessageUser(String messageFormat, String messageTextBody) {
        super(messageFormat, messageTextBody);
    }

    public byte[] getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(byte[] headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Override
    public String toString() {
        return "MessageUser{" +
                "headPortrait=" + headPortrait +
                '}';
    }
}
