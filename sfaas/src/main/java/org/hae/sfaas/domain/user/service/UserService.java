package org.hae.sfaas.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.user.dto.request.UserLoginRequest;
import org.hae.sfaas.domain.user.dto.response.UserInfoResponse;
import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public UserInfoResponse getMyProfile(final UserLoginRequest request) {
        User userVO = userMapper.loginUser(request);
        return UserInfoResponse.of(userVO);
    }

}
