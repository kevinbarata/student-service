package com.project.student.service;

import com.project.student.dto.AttendanceDto;

import java.util.List;

public interface AttendanceService {

    int checkIn(AttendanceDto attendanceDto);

    int checkOut(AttendanceDto attendanceDto);

    List<AttendanceDto> getStudentAttendance(AttendanceDto attendanceDto);

    AttendanceDto getTodayLog(AttendanceDto attendanceDto);
}
