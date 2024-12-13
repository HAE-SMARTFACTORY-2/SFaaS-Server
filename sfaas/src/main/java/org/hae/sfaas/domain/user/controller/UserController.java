package org.hae.sfaas.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.user.dto.request.UpdateUserRoleRequest;
import org.hae.sfaas.domain.user.dto.request.UserLoginRequest;
import org.hae.sfaas.domain.user.dto.response.UserDetailInfoResponse;
import org.hae.sfaas.domain.user.dto.response.UserInfoResponse;
import org.hae.sfaas.domain.user.service.UserService;
import org.hae.sfaas.global.common.response.SFaaSResponse;
import org.hae.sfaas.global.common.response.SuccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity<SFaaSResponse<?>> getUserInfo(@RequestBody UserLoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.POST_LOGIN_SUCCESS, userService.getLoginInfo(request)));
    }

    @GetMapping("/users")
    public ResponseEntity<SFaaSResponse<?>> getUserInfos(@RequestParam("userId") Long userId) {
        List<UserDetailInfoResponse> response = userService.getUserInfos(userId);
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_USERINFOS_SUCCESS, response));
    }

    @PatchMapping("/user/role")
    public ResponseEntity<SFaaSResponse<?>> modifyUserRole(@RequestBody UpdateUserRoleRequest request) {
        userService.modifyUserRole(request);
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.PATCH_USERROLE_SUCCESS));
    }

    @GetMapping("/user/me/{id}")
    public ResponseEntity<SFaaSResponse<?>> getMyInfo(@PathVariable("id") Long userId) {
        UserDetailInfoResponse response = userService.getMyInfo(userId);
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_MYINFO_SUCCESS, response));
    }

}