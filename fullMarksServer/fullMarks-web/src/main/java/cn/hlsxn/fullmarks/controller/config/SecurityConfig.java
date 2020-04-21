package cn.hlsxn.fullmarks.controller.config;


import cn.hlsxn.fullmarks.controller.config.security.CustomFilterInvocationSecurityMetadataSource;
import cn.hlsxn.fullmarks.controller.config.security.CustomUrlDecisionManager;
import cn.hlsxn.fullmarks.controller.config.security.LoginSuccessHandler;
import cn.hlsxn.fullmarks.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * security配置类
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    UserService userService;

    //配置访问规则
    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    //验证访问是否合法
    @Autowired
    CustomUrlDecisionManager customUrlDecisionManager;

    //唯一session
    @Autowired
    SessionRegistry sessionRegistry;
    
    //过期session
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    //无效session
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    //退出登陆成功
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    //登陆成功
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    //登陆失败
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    //未认证用户
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 加载userDetails
     * @param auth
     * @tuserows Exception
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login","/kaptcha","/css/**","/js/**","/index.html","/img/**","/fonts/**","/favicon.ico","/timeOut");
    }


    /**
     * 解决session失效后 sessionRegistry中session没有同步失效的问题，启用并发session控制，首先需要在配置中增加下面监听器
     * @return
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
//
//    /**
//     * 注册bean sessionRegistry
//     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        //以下这句就可以控制单个用户只能创建一个session，也就只能在服务器登录一次
        http.sessionManagement()
                // 无效session跳转
                .invalidSessionStrategy(invalidSessionStrategy)
                // 最大在线用户数
                .maximumSessions(1)
                // session过期跳转
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .sessionRegistry(sessionRegistry);
        http.authorizeRequests()//方法有多个子节点，每个macher按照他们的声明顺序执行。
                .anyRequest().authenticated()
                //通过withObjectPostProcessor将刚刚创建的UrlFilterInvocationSecurityMetadataSource和UrlAccessDecisionManager注入进来
                .withObjectPostProcessor(urlObjectPostProcessor())
                .and()
                    .formLogin()
                    .usernameParameter("username")
                    .passwordParameter("upassword")
                    .loginProcessingUrl("/doLogin")
                    .loginPage("/login")
                    .successHandler(loginSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/quit")
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .permitAll()
                .and()
                    .csrf().disable().exceptionHandling()
                        //没有认证时，在这里处理结果，不要重定向
                    .authenticationEntryPoint(authenticationEntryPoint);

    }
    public ObjectPostProcessor urlObjectPostProcessor() {
        return new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setAccessDecisionManager(customUrlDecisionManager);
                object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                return object;
            }
        };
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
