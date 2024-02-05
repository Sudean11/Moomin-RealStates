package com.academicproject.moomin.realstates.entity.dtos.requestDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String email;
    private String password;
}
