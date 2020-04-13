package cn.hlsxn.fullmarks.mapper;

import cn.hlsxn.fullmarks.model.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {

    @Select("SELECT r.* FROM role r,users_role ur WHERE r.`rid` = ur.`roleId` AND ur.`userId` = #{uid}")
    List<Role> getByUid(Integer uid);

    @Select("SELECT r.* FROM role r,role_permission rp WHERE r.`rid` = rp.`roleId` AND rp.`permissionId` = #{pid}")
    List<Role> getByPid(Integer pid);

}
