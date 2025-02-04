package org.hae.sfaas.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hae.sfaas.domain.user.dto.request.UserLoginRequest;
import org.hae.sfaas.domain.user.model.DetailUser;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.user.model.UserRole;

import java.util.List;

@Mapper
public interface UserMapper {
    User loginUser(UserLoginRequest request);
    User findById(Long userId);
    User findByMgr(Long userMgr);
    List<DetailUser> findAll();
    List<DetailUser> findByFactoryId(Long factoryId);
    DetailUser getMyInfo(Long userId);
    void updateUserRoleById(Long userMgr, UserRole role);

    void updateLoginAt(Long userId);
}
