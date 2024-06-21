package com.project.student.dao;

import com.project.student.dto.AttendanceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttendanceDao {

    int checkIn(AttendanceDto attendanceDto);

    int checkOut(AttendanceDto attendanceDto);

    List<AttendanceDto> getStudentAttendance(AttendanceDto attendanceDto);

    AttendanceDto getTodayLog(AttendanceDto attendanceDto);
}
