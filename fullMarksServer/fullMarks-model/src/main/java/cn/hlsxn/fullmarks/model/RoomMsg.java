package cn.hlsxn.fullmarks.model;

import java.util.List;

/**
 * 房间内通信
 */
public class RoomMsg {
    private UserFriend from;//发送信息的玩家
    private String username;//当前玩家
    private int type;// 0、加入房间 1、退出房间 2、切换至准备状态 3、切换至未准备状态
    private int index;//玩家所在下标
    private List<UserFriend> friends;//好友
    private int roomId;//房间号
    private List<UserFriend> players;//房间内的玩家
    private List<Integer[]> boards;//所有玩家的牌
    private List<Grade> grade;//玩家所叫的分数
    private Integer count;//第几个玩家
    private Integer maxGrade;//当前叫的最大的分数
    private Integer num;//副家获得的分数
    private Integer masterIndex;//主家下标
    private Integer masterBoard;//主牌

    @Override
    public String toString() {
        return "RoomMsg{" +
                "from=" + from +
                ", username='" + username + '\'' +
                ", type=" + type +
                ", index=" + index +
                ", friends=" + friends +
                ", roomId=" + roomId +
                ", players=" + players +
                ", boards=" + boards +
                ", grade=" + grade +
                ", count=" + count +
                ",  maxGrade=" + maxGrade +
                ", num=" + num +
                ", masterIndex=" + masterIndex +
                ", masterBoard=" + masterBoard +
                '}';
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMasterIndex() {
        return masterIndex;
    }

    public void setMasterIndex(Integer masterIndex) {
        this.masterIndex = masterIndex;
    }

    public Integer getMasterBoard() {
        return masterBoard;
    }

    public void setMasterBoard(Integer masterBoard) {
        this.masterBoard = masterBoard;
    }

    public Integer getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(Integer maxGrade) {
        this.maxGrade = maxGrade;
    }

    public List<Grade> getGrade() {
        return grade;
    }

    public void setGrade(List<Grade> grade) {
        this.grade = grade;
    }

    public List<Integer[]> getBoards() {
        return boards;
    }

    public void setBoards(List<Integer[]> boards) {
        this.boards = boards;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
