package cn.hlsxn.fullmarks.mapper;

import cn.hlsxn.fullmarks.model.Room;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoomMapper {
    @Select("SELECT * FROM room WHERE rlevel = #{level}")
    @Results({
        @Result(id = true,column = "rid",property = "rid"),
        @Result(column = "roomName",property = "roomName"),
        @Result(column = "roomPassword",property = "roomPassword"),
        @Result(column = "roomWay",property = "roomWay"),
        @Result(column = "roomState",property = "roomState"),
        @Result(column = "room_userId",property = "room_userId"),
        @Result(column = "roomId",property = "roomId"),
        @Result(column = "rlevel",property = "rlevel"),
        @Result(column = "roomNum",property = "roomNum"),
        @Result(column = "rid",property = "users",javaType=List.class,
                many = @Many(select = "cn.hlsxn.fullmarks.mapper.UserMapper.findByRoomId",fetchType= FetchType.EAGER))
    })
    List<Room> getAll(Integer level);

    @Insert("INSERT INTO room(roomName,roomPassword,roomWay,room_userId,rlevel) " +
            " VALUES(#{room.roomName},#{room.roomPassword},#{room.roomWay},#{room.room_userId},#{room.rlevel})")
    @Options(useGeneratedKeys = true, keyProperty = "room.rid")
    void create(@Param("room") Room room);
}
