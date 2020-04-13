package cn.hlsxn.fullmarks.service;

import cn.hlsxn.fullmarks.mapper.RoomMapper;
import cn.hlsxn.fullmarks.mapper.UserMapper;
import cn.hlsxn.fullmarks.model.RespBean;
import cn.hlsxn.fullmarks.model.RespPageBean;
import cn.hlsxn.fullmarks.model.Room;
import cn.hlsxn.fullmarks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.List;

@Service
public class RoomService {
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
            System.out.println(room);
        }
        return respPageBean;
    }

    public RespBean create(Authentication authentication, Room room){
        User currentUser = (User)authentication.getPrincipal();
        int uid = currentUser.getUid();
        room.setRoom_userId(uid);
        System.out.println("*******插入前数据:"+room);
        roomMapper.create(room);
        int rid = room.getRid();
        System.out.println("rid:"+rid+"==currentUser:"+currentUser);
        room.setRoom_userId(uid);
        userMapper.updateRid(rid,uid);
        return RespBean.ok("创建成功");
    }
}
