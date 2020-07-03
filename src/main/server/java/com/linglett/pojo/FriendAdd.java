package server.java.com.linglett.pojo;

public class FriendAdd {
    private String owner;
    private String friend;
    private String note;

    public FriendAdd(String owner, String friend, String note) {
        this.owner = owner;
        this.friend = friend;
        this.note = note;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    @Override
    public String toString() {
        return "FriendAdd{" +
                "owner='" + owner + '\'' +
                ", friend='" + friend + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
