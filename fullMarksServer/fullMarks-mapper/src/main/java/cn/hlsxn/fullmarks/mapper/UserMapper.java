package cn.hlsxn.fullmarks.mapper;

import cn.hlsxn.fullmarks.model.User;
import org.apache.ibatis.annotations.*;

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
     * 获得房间的用户
     * @param roomId
     * @return
     */
    @Select("SELECT * FROM users WHERE user_roomId = #{roomId}")
    List<User> findByRoomId(Integer roomId);

    @Update("UPDATE users SET user_roomId = #{rid} where uid = #{uid}")
    void updateRid(@Param("rid") Integer rid,@Param("uid") Integer uid);
}
