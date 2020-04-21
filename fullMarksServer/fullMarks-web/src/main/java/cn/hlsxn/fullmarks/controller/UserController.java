package cn.hlsxn.fullmarks.controller;

import cn.hlsxn.fullmarks.controller.util.SessionUtil;
import cn.hlsxn.fullmarks.model.RespBean;
import cn.hlsxn.fullmarks.model.User;
import cn.hlsxn.fullmarks.service.UserService;
import cn.hlsxn.fullmarks.utils.CodeUtil;
import com.google.code.kaptcha.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/a")
    public String home() {
        List<Object> objects = sessionRegistry.getAllPrincipals();
        log.info("userSize"+objects.size());
        for (Object object : objects) {
            List<SessionInformation> allSessions = sessionRegistry.getAllSessions(object, false);
            log.info("第一次取得的数量:"+allSessions);
            List<SessionInformation> allSessions2 = sessionRegistry.getAllSessions(object, true);
            log.info("第二次取得的数量:"+allSessions2);
            log.info("user:"+(User)object);
        }


        return "d:Hello World!";
    }

    /**
     * 此方法配合security使用，直接使用security的时候sessionRegistry注入不正确，
     * 先把sessionRegistry中的session清除，然后去退出操作
     * @param request
     * @param authentication
     * @return
     */
    @GetMapping("/logout")
    public RespBean logOut(HttpServletRequest request,Authentication authentication){
        User user = (User) authentication.getPrincipal();
        SessionUtil.expireSession(request,user,sessionRegistry);
        return RespBean.ok("session清除成功!");
    }

//    /**
//     * 登陆超时处置
//     * @return
//     */


    @PostMapping("/register")
    @ResponseBody
    public RespBean register(HttpServletRequest request, User user) {
        if (!CodeUtil.checkVerifyCode(request)) {
            return RespBean.error("验证码错误，请重新输入!");
        }
        RespBean respBean = userService.register(user);
        request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        return respBean;
    }

}
