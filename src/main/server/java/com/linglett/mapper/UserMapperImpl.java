package server.java.com.linglett.mapper;

import org.apache.ibatis.session.SqlSession;
import server.java.com.linglett.pojo.User;

import java.util.List;

public class UserMapperImpl implements UserMapper{
    private SqlSession sqlSession;
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    public UserMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }



    public List<User> selectUser(String account, String password) {
        UserMapper userMapper =sqlSession.getMapper(UserMapper.class);
        return userMapper.selectUser(account,password);
    }

    public void addUser(String account, String password, String nackName, String sex, String birthday, byte[] head_portrait) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.addUser(account, password,nackName, sex,birthday,head_portrait);
    }

    public User selectUserAccount(String account) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user=userMapper.selectUserAccount(account);
        return user;
    }
}
