package cn.hlsxn.fullmarks.service;

import cn.hlsxn.fullmarks.mapper.PermissionMapper;
import cn.hlsxn.fullmarks.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> getAllPermissionWithRole() {
        return permissionMapper.getAllPermissionWithRole();
    }
}
