package cn.hlsxn.fullmarks.controller.config;

import cn.hlsxn.fullmarks.model.Permission;
import cn.hlsxn.fullmarks.model.Role;
import cn.hlsxn.fullmarks.service.PermissionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    Logger log = Logger.getLogger(CustomFilterInvocationSecurityMetadataSource.class);

    @Autowired
    PermissionService permissionService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Permission> permissions = permissionService.getAllPermissionWithRole();
        for (Permission permission : permissions) {
            if (antPathMatcher.match(permission.getPermissionName(), requestUrl)) {
                List<Role> roles = permission.getRoles();
                String[] str = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i).getRoleName();
                }
                return SecurityConfig.createList(str);
            }
        }
        log.info(permissions.size());
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
