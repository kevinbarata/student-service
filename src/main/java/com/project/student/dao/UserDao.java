package com.project.student.dao;

import com.project.student.dto.StudentDto;
import com.project.student.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    int add(UserDto userDto);

    List<UserDto> get(UserDto userDto);

    List<StudentDto> getAllStudent(StudentDto userDto);

    int update(UserDto userDto);

    UserDto getUserByPassword(UserDto userDto);

    int changePassword(UserDto userDto);
}
