package com.project.student.service;

import com.project.student.dto.*;
import com.project.student.util.ResponseEntityDto;

import java.util.List;

public interface UserService {

    ResponseEntityDto register(UserDto userDto);

    ResponseEntityDto login(UserDto userDto);

    ResponseEntityDto logout(UserSessionDto userSessionDto);

    ResponseEntityDto changePassword(ChangePasswordDto changePasswordDto);

    ResponseEntityDto forgetPassword(ForgetPasswordDto forgetPasswordDto);

    List<StudentDto> getAllStudent(StudentDto dto);

    int validateSession(UserSessionDto userSessionDto);

    int update(UserDto userDto);
}
