package cn.hlsxn.fullmarks.model;

import java.util.List;

public class Room {
    private Integer rid;
    private String roomName;//房间名
    private String roomPassword;//密码
    private boolean roomWay;//加入方式 默认0 不加锁
    private boolean roomState;//房间状态 默认 0 房间人数未满可以加入
    private Integer roomNum;//房间人数
    private Integer rlevel;//房间模式 默认0 普通模式
    private Integer room_userId;//房主id
    private Integer roomId;//房间号
    private List<UserFriend> users;

    @Override
    public String toString() {
        return "Room{" +
                "rid=" + rid +
                ", roomName='" + roomName + '\'' +
                ", roomPassword='" + roomPassword + '\'' +
                ", roomWay=" + roomWay +
                ", roomState=" + roomState +
                ", roomNum=" + roomNum +
                ", rlevel=" + rlevel +
                ", room_userId=" + room_userId +
                ", roomId=" + roomId +
                ", users=" + users +
                '}';
    }

    public Integer getRlevel() {
        return rlevel;
    }

    public void setRlevel(Integer rlevel) {
        this.rlevel = rlevel;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }


    public List<UserFriend> getUsers() {
        return users;
    }

    public void setUsers(List<UserFriend> users) {
        this.users = users;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    public boolean isRoomWay() {
        return roomWay;
    }

    public void setRoomWay(boolean roomWay) {
        this.roomWay = roomWay;
    }

    public boolean isRoomState() {
        return roomState;
    }

    public void setRoomState(boolean roomState) {
        this.roomState = roomState;
    }

    public Integer getRoom_userId() {
        return room_userId;
    }

    public void setRoom_userId(Integer room_userId) {
        this.room_userId = room_userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
