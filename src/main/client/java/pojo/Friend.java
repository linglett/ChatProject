package client.java.pojo;

import java.io.Serializable;

public class Friend implements Serializable {
    public String friend_account;
    public String friend_head;
    public String friend_note;
    public String friend_friendName;
    public String friend_sex;

    public Friend(String friend_account, String friend_head, String friend_note, String friend_friendname, String friend_sex, String friend_birthday) {
        this.friend_account = friend_account;
        this.friend_head = friend_head;
        this.friend_note = friend_note;
        this.friend_friendName = friend_friendname;
        this.friend_sex = friend_sex;
        this.friend_birthday = friend_birthday;
    }
    @Override
    public String toString() {
        return "Friend{" +
                "friend_account='" + friend_account + '\'' +
                ", friend_head='" + friend_head + '\'' +
                ", friend_note='" + friend_note + '\'' +
                ", friend_friendname='" + friend_friendName + '\'' +
                ", friend_sex='" + friend_sex + '\'' +
                ", friend_birthday='" + friend_birthday + '\'' +
                '}';
    }

    public String getFriend_account() {
        return friend_account;
    }

    public void setFriend_account(String friend_account) {
        this.friend_account = friend_account;
    }

    public String getFriend_head() {
        return friend_head;
    }

    public void setFriend_head(String friend_head) {
        this.friend_head = friend_head;
    }

    public String getFriend_note() {
        return friend_note;
    }

    public void setFriend_note(String friend_note) {
        this.friend_note = friend_note;
    }

    public String getFriend_friendname() {
        return friend_friendName;
    }

    public void setFriend_friendname(String friend_friendname) {
        this.friend_friendName = friend_friendname;
    }

    public String getFriend_sex() {
        return friend_sex;
    }

    public void setFriend_sex(String friend_sex) {
        this.friend_sex = friend_sex;
    }

    public String getFriend_birthday() {
        return friend_birthday;
    }

    public void setFriend_birthday(String friend_birthday) {
        this.friend_birthday = friend_birthday;
    }


    private String friend_birthday;
}
