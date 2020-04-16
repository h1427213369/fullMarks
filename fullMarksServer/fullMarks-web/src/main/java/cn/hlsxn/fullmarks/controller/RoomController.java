package cn.hlsxn.fullmarks.controller;

import cn.hlsxn.fullmarks.model.RespBean;
import cn.hlsxn.fullmarks.model.RespPageBean;
import cn.hlsxn.fullmarks.model.Room;
import cn.hlsxn.fullmarks.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/getAll/{level}")
    public RespPageBean getAll(@PathVariable Integer level){
        RespPageBean respBean = roomService.getAll(level);
        return respBean;
    }

    @PostMapping("/create")
    public RespBean create(Authentication authentication,@RequestBody Room room){
        return roomService.create(authentication,room);
    }
}
