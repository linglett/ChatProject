package server.java.com.linglett.utils;

import message.Message;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import server.java.com.linglett.mapper.FriendMapper;
import server.java.com.linglett.mapper.FriendMapperImpl;
import server.java.com.linglett.mapper.UserMapper;
import server.java.com.linglett.mapper.UserMapperImpl;
import server.java.com.linglett.pojo.Friend;
import server.java.com.linglett.pojo.FriendAdd;
import server.java.com.linglett.pojo.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 *
 */
public class MessageDispose {
    private static ApplicationContext APPLICATIONCONTEXT = new ClassPathXmlApplicationContext("server-spring-dao.xml");
    public static User loginMessage(Message message){
        User user =null;
        String messageText = message.getMessageTextBody();
        //登陆的文章格式是"account&password&"
        String[] strings=messageText.split("&");
        UserMapper userMapper=(UserMapperImpl) APPLICATIONCONTEXT.getBean("UserMapper");
        List<User> list= userMapper.selectUser(strings[0],strings[1]);
        Iterator<User> iterator = list.iterator();
        if(iterator.hasNext()){
            user=iterator.next();
            return user;
        }else{
            return null;
        }
    }

    public static User loginAccount(String account){
        UserMapper userMapper = (UserMapperImpl) APPLICATIONCONTEXT.getBean("UserMapper");
        return userMapper.selectUserAccount(account);
    }

    public static String registerMessage(Message message){
        String account = GenerateAccount.CreateAccount();
        String[]strings =message.getMessageTextBody().split("&");
        UserMapper userMapper =(UserMapperImpl) APPLICATIONCONTEXT.getBean("UserMapper");
        try {
            userMapper.addUser(account,strings[1],strings[0],"无",null,ImageConversion.imageToOutput(new File("C:\\Users\\12508\\IdeaProjects\\ChatProject\\src\\main\\server\\java\\com\\linglett\\image\\head.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return account;
    }

    /*public static void main(String[] args) {
        Message message =new Message("01","1250871597&20001228&","11","11");
        loginMessage(message);
    }*/
    public static List<Friend> friendMessage(Message message){
        String account = message.getSender();
        FriendMapper friendMapper = (FriendMapperImpl) APPLICATIONCONTEXT.getBean("FriendMapper");
        List<FriendAdd> list= friendMapper.selectFriend(account);
        List<Friend> friendList = new LinkedList<Friend>();
        for (FriendAdd friendAdd : list) {
            User user = loginAccount(friendAdd.getFriend());
            Friend friend =new Friend(user.getAccount(),user.getHeadPortrait(),friendAdd.getNote(),user.getUserName(),user.getSex(),user.getBirthday());
            friendList.add(friend);
        }
        return friendList;
    }

}
