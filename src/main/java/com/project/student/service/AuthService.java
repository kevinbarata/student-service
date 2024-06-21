package com.project.student.service;

import com.project.student.dto.AuthDto;

import java.util.List;

public interface AuthService {

    int add(AuthDto authDto);

    List<AuthDto> get(AuthDto authDto);

    int update(AuthDto authDto);

    AuthDto findCodeByEmail(AuthDto authDto);
}
