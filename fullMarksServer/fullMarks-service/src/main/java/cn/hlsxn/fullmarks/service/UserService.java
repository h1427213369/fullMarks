package cn.hlsxn.fullmarks.service;

import cn.hlsxn.fullmarks.mapper.RoleMapper;
import cn.hlsxn.fullmarks.mapper.UserMapper;
import cn.hlsxn.fullmarks.model.RespBean;
import cn.hlsxn.fullmarks.model.User;
import cn.hlsxn.fullmarks.model.UserFriend;
import cn.hlsxn.fullmarks.utils.MailUtils;
import cn.hlsxn.fullmarks.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class UserService implements UserDetailsService {
    private static Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SessionRegistry sessionRegistry;//检测用户是否登陆

    public List<User> findAll() {
        return userMapper.findAll();
    }

//    public RespBean login(HttpServletRequest request, String userName, String password) {
//        User user = userMapper.login(userName, password);
//        if (null == user) {
//            return RespBean.error("账号或者密码错误，请检查后重新输入");
//        }
//        int status = user.getUstatus();
//        if (0 == status) {
//            return RespBean.error("账户未激活，请先激活");
//        }
//        request.getSession().setAttribute("user", user);
//        return RespBean.ok("");
//    }

    public RespBean register(User user) {
        String username = userMapper.findByUserName(user.getUsername());
        if (null != username) {
            return RespBean.error("用户名重复，请重新输入!");
        }
        user.setUname(user.getUsername());
        user.setUface("");
        user.setUuid(UuidUtil.getUuid());
        user.setUgold(0);
        user.setUgold(200);
        userMapper.insert(user);
        //注册成功，发送邮件
        String text="<a href=\"http://h1427213369.picp.net/travel/user/active?code="+user.getUuid()+"\">正在注册百分纸牌，请点击激活</a>";
        MailUtils.sendMail(user.getUemail(), text, "百分纸牌网游");
        return RespBean.ok("注册成功,请查收邮件并激活!");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,SessionAuthenticationException{
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
//        List<Object> o = sessionRegistry.getAllPrincipals();
//        for ( Object principal : o) {
//            if (principal instanceof User && (user.getUsername().equals(((User) principal).getUsername()))) {
////                throw new SessionAuthenticationException("当前用户已经在线，登录失败！！！");
//                log.info("这货我都登陆了还想登陆！");
//            }
//            if (principal instanceof User){
//                log.info("sessionUser:"+(User)principal);
//            }
//        }
        user.setRoles(roleMapper.getByUid(user.getUid()));
        return user;
    }

    /**
     * 查找当前登陆玩家信息
     * @param username
     * @return
     */
    public UserFriend getRoomUser(String username) {
        return userMapper.getRoomUser(username);
    }

    /**
     * 查找玩家好友
     * @param uid
     * @return
     */
    public List<UserFriend> getFriends(Integer uid) {
        return userMapper.getFriends(uid);
    }
}
