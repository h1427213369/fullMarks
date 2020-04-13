package cn.hlsxn.fullmarks.mapper;

import cn.hlsxn.fullmarks.model.Permission;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface PermissionMapper {

    @Select("SELECT * FROM permission")
    @Results({
            @Result(id = true, column = "pid", property = "pid"),
            @Result(column = "permissionName", property = "permissionName"),
            @Result(column = "permissionDesc", property = "permissionDesc"),
            @Result(column = "roles",property = "pid",many = @Many(select = "cn.hlsxn.fullmarks.mapper.RoleMapper.getByPid",fetchType= FetchType.EAGER))
    })
    List<Permission> getAllPermissionWithRole();
}
