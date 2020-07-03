package server.java.com.linglett.pojo;

import java.io.Serializable;
import java.sql.Blob;

public class User implements Serializable {
    private String account;
    private String password;
    private String userName;
    private String sex;
    private String birthday;
    private byte[] head_portrait;


    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", headPortrait=" + head_portrait +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public byte[] getHeadPortrait() {
        return head_portrait;
    }

    public void setHeadPortrait(byte[] headPortrait) {
        this.head_portrait = headPortrait;
    }

    public User(String account, String password, String userName, String sex, String birthday, byte[] headPortrait) {
        this.account = account;
        this.password = password;
        this.userName = userName;
        this.sex = sex;
        this.birthday = birthday;
        this.head_portrait = headPortrait;
    }
}
