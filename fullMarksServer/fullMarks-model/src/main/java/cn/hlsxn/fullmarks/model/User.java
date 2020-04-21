package cn.hlsxn.fullmarks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class User implements UserDetails {
    private static Logger log = LoggerFactory.getLogger(User.class);
    private Integer uid;
    private String username;//用户名
    private String upassword;//密码
    private Long unumber;//手机号
    private String uemail;//邮箱
    private String uuid;//激活邮箱唯一验证码
    private Integer ustatus;//激活状态
    private String uname;//昵称
    private String uface;//头像
    private Integer ugrade;//分数
    private Integer ugold;//金币
    private Integer user_roomId;//所在房间号
    private List<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", unumber=" + unumber +
                ", uemail='" + uemail + '\'' +
                ", uuid='" + uuid + '\'' +
                ", ustatus=" + ustatus +
                ", uname='" + uname + '\'' +
                ", uface='" + uface + '\'' +
                ", ugrade=" + ugrade +
                ", ugold=" + ugold +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
//        log.info("用户:" + username + "使用了equals");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
//        298225121
//        int hash = 0;
//        hash += (this.username + this.uname).hashCode();
//        log.info("用户:" + username  + "使用了hashCode:" + hash);
        return Objects.hash(username,uname);
    }

    public Integer getUser_roomId() {
        return user_roomId;
    }
    public void setUser_roomId(int roomId) {
        this.user_roomId = roomId;
    }

    public String getName(){
        return this.username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    //返回验证用户密码,无法返回则NULL
    @Override
    public String getPassword() {
        return this.upassword;
    }

    public String getUsername() {
        return username;
    }


    //账户是否过期,过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否被禁用,禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public Long getUnumber() {
        return unumber;
    }

    public void setUnumber(Long unumber) {
        this.unumber = unumber;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUstatus() {
        return ustatus;
    }

    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUface() {
        return uface;
    }

    public void setUface(String uface) {
        this.uface = uface;
    }

    public Integer getUgrade() {
        return ugrade;
    }

    public void setUgrade(Integer ugrade) {
        this.ugrade = ugrade;
    }

    public Integer getUgold() {
        return ugold;
    }

    public void setUgold(Integer ugold) {
        this.ugold = ugold;
    }
}
