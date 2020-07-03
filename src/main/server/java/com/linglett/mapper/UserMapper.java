package server.java.com.linglett.mapper;

import org.apache.ibatis.annotations.Param;
import server.java.com.linglett.pojo.User;

import java.util.List;


public interface UserMapper {
    public List<User> selectUser(@Param("account") String account, @Param("password") String password);
    public void addUser(@Param("account") String account,@Param("password") String password,@Param("username") String nackName,@Param("sex") String sex,@Param("birthday") String birthday,@Param("head_portrait") byte[] head_portrait);
    public User selectUserAccount(@Param("account")String account);
}
