package com.project.student.serviceImpl;

import com.project.student.dao.AttendanceDao;
import com.project.student.dto.AttendanceDto;
import com.project.student.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    @Override
    public int checkIn(AttendanceDto attendanceDto) {
        attendanceDto.setCheckInTime(new Date());
        int insert = attendanceDao.checkIn(attendanceDto);
        return insert;
    }

    @Override
    public int checkOut(AttendanceDto attendanceDto) {
        attendanceDto.setCheckOutTime(new Date());
        attendanceDto.setAttendanceHour(calculateAttendanceHours(attendanceDto.getCheckInTime(),attendanceDto.getCheckOutTime()));
        int update = attendanceDao.checkOut(attendanceDto);
        return update;
    }

    public static int calculateAttendanceHours(Date checkInTime, Date checkOutTime) {
        long diffInMillies = checkOutTime.getTime() - checkInTime.getTime();
        return (int) TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    @Override
    public List<AttendanceDto> getStudentAttendance(AttendanceDto attendanceDto) {
        List<AttendanceDto> list = attendanceDao.getStudentAttendance(attendanceDto);
        return list;
    }

    @Override
    public AttendanceDto getTodayLog(AttendanceDto attendanceDto) {
        AttendanceDto list = attendanceDao.getTodayLog(attendanceDto);
        return list;
    }
}
