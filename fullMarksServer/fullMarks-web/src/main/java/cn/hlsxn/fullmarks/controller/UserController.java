package cn.hlsxn.fullmarks.controller;

import cn.hlsxn.fullmarks.model.RespBean;
import cn.hlsxn.fullmarks.model.User;
import cn.hlsxn.fullmarks.service.UserService;
import cn.hlsxn.fullmarks.utils.CodeUtil;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/a")
    @ResponseBody
    public String home() {
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        return "d:Hello World!";
    }

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
