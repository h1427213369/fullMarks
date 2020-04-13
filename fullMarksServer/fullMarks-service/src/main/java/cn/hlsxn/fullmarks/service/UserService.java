package cn.hlsxn.fullmarks.service;

import cn.hlsxn.fullmarks.mapper.RoleMapper;
import cn.hlsxn.fullmarks.mapper.UserMapper;
import cn.hlsxn.fullmarks.model.RespBean;
import cn.hlsxn.fullmarks.model.User;
import cn.hlsxn.fullmarks.utils.MailUtils;
import cn.hlsxn.fullmarks.utils.UuidUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class UserService implements UserDetailsService {
    private Logger log = Logger.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public RespBean login(HttpServletRequest request, String userName, String password) {
        User user = userMapper.login(userName, password);
        if (null == user) {
            return RespBean.error("账号或者密码错误，请检查后重新输入");
        }
        int status = user.getUstatus();
        if (0 == status) {
            return RespBean.error("账户未激活，请先激活");
        }
        request.getSession().setAttribute("user", user);
        return RespBean.ok("");
    }

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        user.setRoles(roleMapper.getByUid(user.getUid()));
        return user;
    }
}
