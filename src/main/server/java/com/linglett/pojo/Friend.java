package server.java.com.linglett.pojo;

import java.io.Serializable;
import java.util.Arrays;

public class Friend implements Serializable {
    private String friendAccount;
    private byte[] friendHead;
    private String friendNote;
    private String friendFriendName;
    private String friendSex;
    private String friend_birthday;
    public Friend(String friendAccount, byte[] friendHead, String friendNote, String friendFriendName, String friendSex) {
        this.friendAccount = friendAccount;
        this.friendHead = friendHead;
        this.friendNote = friendNote;
        this.friendFriendName = friendFriendName;
        this.friendSex = friendSex;
    }

    public String getFriend_birthday() {
        return friend_birthday;
    }

    public void setFriend_birthday(String friend_birthday) {
        this.friend_birthday = friend_birthday;
    }

    public Friend(String friendAccount, byte[] friendHead, String friendNote, String friendFriendName, String friendSex, String friend_birthday) {
        this.friendAccount = friendAccount;
        this.friendHead = friendHead;
        this.friendNote = friendNote;
        this.friendFriendName = friendFriendName;
        this.friendSex = friendSex;
        this.friend_birthday = friend_birthday;
    }

    public String getFriendAccount() {
        return friendAccount;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "friendAccount='" + friendAccount + '\'' +
                ", friendHead=" + Arrays.toString(friendHead) +
                ", friendNote='" + friendNote + '\'' +
                ", friendFriendName='" + friendFriendName + '\'' +
                ", friendSex='" + friendSex + '\'' +
                '}';
    }

    public void setFriendAccount(String friendAccount) {
        this.friendAccount = friendAccount;
    }

    public byte[] getFriendHead() {
        return friendHead;
    }

    public void setFriendHead(byte[] friendHead) {
        this.friendHead = friendHead;
    }

    public String getFriendNote() {
        return friendNote;
    }

    public void setFriendNote(String friendNote) {
        this.friendNote = friendNote;
    }

    public String getFriendFriendName() {
        return friendFriendName;
    }

    public void setFriendFriendName(String friendFriendName) {
        this.friendFriendName = friendFriendName;
    }

    public String getFriendSex() {
        return friendSex;
    }

    public void setFriendSex(String friendSex) {
        this.friendSex = friendSex;
    }
}
