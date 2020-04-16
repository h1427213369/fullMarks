package cn.hlsxn.fullmarks.model;

import java.util.List;

/**
 * 房间内通信
 */
public class RoomMsg {
    private UserFriend from;//发送信息的玩家
    private String username;//当前玩家
    private int status;//准备状态 0 未准备 1 已准备 2开始游戏 3等待房主开始游戏
    private int index;//玩家所在下标
    private List<UserFriend> friends;//好友
    private int roomId;//房间号
    private List<UserFriend> players;//房间内的玩家

    @Override
    public String toString() {
        return "RoomMsg{" +
                "from=" + from +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", index=" + index +
                ", friends=" + friends +
                ", roomId=" + roomId +
                ", players=" + players +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserFriend> getPlayers() {
        return players;
    }

    public void setPlayers(List<UserFriend> players) {
        this.players = players;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setFrom(UserFriend from) {
        this.from = from;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<UserFriend> getFriends() {
        return friends;
    }

    public void setFriends(List<UserFriend> friends) {
        this.friends = friends;
    }

    public UserFriend getFrom() {
        return from;

    }
}
