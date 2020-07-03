package server.java.com.linglett.mapper;

import org.apache.ibatis.annotations.Param;
import server.java.com.linglett.pojo.FriendAdd;

import java.util.List;

public interface FriendMapper {
    public List<FriendAdd> selectFriend(@Param("owner") String account);
}
