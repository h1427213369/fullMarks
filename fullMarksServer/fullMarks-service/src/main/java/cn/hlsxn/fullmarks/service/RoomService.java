package cn.hlsxn.fullmarks.service;

import cn.hlsxn.fullmarks.mapper.RoomMapper;
import cn.hlsxn.fullmarks.mapper.UserMapper;
import cn.hlsxn.fullmarks.model.RespBean;
import cn.hlsxn.fullmarks.model.RespPageBean;
import cn.hlsxn.fullmarks.model.Room;
import cn.hlsxn.fullmarks.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.List;

@Service
public class RoomService {
    private Logger log = LoggerFactory.getLogger(RoomService.class);
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取房间列表
     * @param level 房间等级
     * @return
     */
    public RespPageBean getAll(Integer level) {
        List<Room> all = roomMapper.getAll(level);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(all);
        for (Room room : all) {
            log.info("" + room);
        }
        return respPageBean;
    }

    /**
     * 获取房间人数
     * @param roomId
     * @return
     */
    public int getRoomNum(int roomId) {
        return roomMapper.getRoomNumByRid(roomId);
    }

    public RespBean create(Authentication authentication, Room room){
        User currentUser = (User)authentication.getPrincipal();
        int uid = currentUser.getUid();
        room.setRoom_userId(uid);
        roomMapper.create(room);
        int rid = room.getRid();
        room.setRoom_userId(uid);
        userMapper.updateRid(rid,uid);
        return RespBean.ok("创建成功");
    }

    /**
     * 更新房间人数+1
     * @param roomId
     */
    public void updateAddRoomNum(int roomId) {
        roomMapper.updateAddRoomNum(roomId);
    }

    /**
     * 更新房间人数-1
     * @param roomId
     */
    public void updateSubtractRoomNum(int roomId) {
        roomMapper.updateSubtractRoomNum(roomId);
    }

    /**
     * 获取房主id
     * @param roomId
     * @return
     */
    public int getUidByRoomId(Integer roomId) {
        return roomMapper.getUidByRoomId(roomId);
    }
}
