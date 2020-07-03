package server.java.com.linglett.mapper;

import org.apache.ibatis.session.SqlSession;
import server.java.com.linglett.pojo.Friend;
import server.java.com.linglett.pojo.FriendAdd;

import java.util.List;

public class FriendMapperImpl implements FriendMapper {
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public FriendMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<FriendAdd> selectFriend(String account) {
        FriendMapper friendMapper = sqlSession .getMapper(FriendMapper.class);
        return friendMapper.selectFriend(account);
    }
}
