package com.academicproject.moomin.realstates.service;


import com.academicproject.moomin.realstates.entity.dtos.requestDto.LoginRequest;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.RefreshTokenRequest;
import com.academicproject.moomin.realstates.entity.dtos.responseDto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
