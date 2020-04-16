package cn.hlsxn.fullmarks.mapper;

import cn.hlsxn.fullmarks.model.User;
import cn.hlsxn.fullmarks.model.UserFriend;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from users")
    public List<User> findAll();

    @Select("select uid,username,ustatus,upassword,unumber,uemail,uname,uface,ugrade,user_houseId from users " +
            " where username = #{user} And upassword = #{password}")
    User login(@Param("user") String user, @Param("password") String password);

    @Select("select username from users where username = #{username}")
    String findByUserName(String username);

    @Insert("insert into users(username,upassword,unumber,uemail,uuid,uname,uface) values(" +
            "#{user.username},#{user.upassword},#{user.unumber},#{user.uemail},#{user.uuid},#{user.uname},#{user.uface})")
    void insert(@Param("user") User user);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User loadUserByUsername(String username);

    /**
     * 当前用户，去掉某些不用属性
     * @param username
     * @return
     */
    @Select("SELECT uid,username,uname,uface FROM users WHERE username = #{username}")
    UserFriend getRoomUser(String username);

    /**
     * 查询玩家好友列表
     * @param uid
     * @return
     */
    @Select("SELECT uid,username,uname,uface FROM users u  WHERE u.uid IN(" +
            "    SELECT f1.friend_uid FROM friend f1 WHERE f1.uid = #{uid})")
    List<UserFriend> getFriends(Integer uid);

    /**
     * 获得房间的用户
     * @param roomId
     * @return
     */
    @Select("SELECT u.uid,u.username,u.uname,u.uface FROM users u  JOIN room_user ru ON u.uid = ru.uid AND ru.roomId = #{roomId}")
    @Results(value = {
            @Result(id = true,property = "uid",column = "uid"),
            @Result(property = "username",column = "username"),
            @Result(property = "uname",column = "uname"),
            @Result(property = "uface",column = "uface"),
            @Result(property = "status",column = "uid",one = @One(select = "cn.hlsxn.fullmarks.mapper.RoomUserMapper.getStatus",fetchType= FetchType.EAGER)),
    })
    List<UserFriend> findByRoomId(Integer roomId);

    @Update("UPDATE users SET user_roomId = #{rid} where uid = #{uid}")
    void updateRid(@Param("rid") Integer rid,@Param("uid") Integer uid);

    /**
     * 根据用户名获得主键
     * @param username
     * @return
     */
    @Select("SELECT uid FROM users WHERE username = #{username}")
    int getUidByUsername(String username);
}
