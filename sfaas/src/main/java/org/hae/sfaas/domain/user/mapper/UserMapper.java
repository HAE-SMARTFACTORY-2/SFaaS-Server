package org.hae.sfaas.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hae.sfaas.domain.user.dto.request.UserLoginRequest;
import org.hae.sfaas.domain.user.model.User;

@Mapper
public interface UserMapper {
    User loginUser(UserLoginRequest request);
}
