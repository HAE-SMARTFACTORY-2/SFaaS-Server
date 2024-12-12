package org.hae.sfaas.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.user.dto.request.UserLoginRequest;
import org.hae.sfaas.domain.user.service.UserService;
import org.hae.sfaas.global.common.response.SFaaSResponse;
import org.hae.sfaas.global.common.response.SuccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<SFaaSResponse<?>> getUserInfo(@RequestBody UserLoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.OK, userService.getMyProfile(request)));
    }
}