package org.hae.sfaas.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.user.dto.request.UpdateUserRoleRequest;
import org.hae.sfaas.domain.user.dto.request.UserLoginRequest;
import org.hae.sfaas.domain.user.dto.response.UserDetailInfoResponse;
import org.hae.sfaas.domain.user.dto.response.UserInfoResponse;
import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.DetailUser;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.user.model.UserRole;
import org.hae.sfaas.global.common.exception.SFaaSException;
import org.hae.sfaas.global.common.response.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    @Transactional
    public UserInfoResponse getLoginInfo(final UserLoginRequest request) {
        User user = userMapper.loginUser(request);
        if(isInvalidUser(user)) {
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }
        userMapper.updateLoginAt(user.getUserId());
        return UserInfoResponse.of(user);
    }

    public List<UserInfoResponse> getUserInfos(Long userId) {
        User user = userMapper.findById(userId);
        if(isInvalidUser(user)) {
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        List<User> users = null;
        if (user.getUserRole().equals(UserRole.ADMIN)) {
            users = userMapper.findAll();
        } else if (user.getUserRole().equals(UserRole.MANAGER)) {
            users = userMapper.findByFactoryId(user.getFactoryId());
        } else {
            throw new SFaaSException(ErrorType.FORBIDDEN_USER);
        }

        return users.stream().map(UserInfoResponse::of).toList();
    }

    @Transactional
    public void modifyUserRole(UpdateUserRoleRequest request) {
        User supervisor = userMapper.findById(request.userId());
        User targetUser = userMapper.findById(request.targetUserId());

        if (supervisor.getUserRole().equals(UserRole.ADMIN)) {
            userMapper.updateUserRoleById(request.targetUserId(), request.role());
        } else if (supervisor.getUserRole().equals(UserRole.MANAGER)) {
            if (supervisor.getFactoryId().equals(targetUser.getFactoryId())) {
                if(request.role().equals(UserRole.ADMIN)) {
                    throw new SFaaSException(ErrorType.FORBIDDEN_USER_MODIFY);
                }
                userMapper.updateUserRoleById(request.targetUserId(), request.role());
            } else {
                throw new SFaaSException(ErrorType.FORBIDDEN_USER_MODIFY);
            }
        } else {
            throw new SFaaSException(ErrorType.FORBIDDEN_USER_MODIFY);
        }
    }

    public UserDetailInfoResponse getMyInfo(Long userId) {
        DetailUser detailUser = userMapper.getMyInfo(userId);
        if (detailUser == null) {
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }
        return UserDetailInfoResponse.of(detailUser);
    }

    private boolean isInvalidUser(User user) {
        return user == null;
    }
}
