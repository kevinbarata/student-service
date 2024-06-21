package com.project.student.controller;

import com.project.student.config.JwtUtil;
import com.project.student.dao.UserSessionDao;
import com.project.student.dto.AttendanceDto;
import com.project.student.service.AttendanceService;
import com.project.student.util.ErrorCodeEnum;
import com.project.student.util.ResponseEntityBuilder;
import com.project.student.util.ResponseEntityDto;
import com.project.student.util.SuccessCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/attendance")
public class AttendanceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserSessionDao userSessionDao;

    // absen masuk
    @RequestMapping(value = "/checkIn", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto checkIn(@RequestBody AttendanceDto attendanceDto) {
        logger.info("attendance.checkIn parameter = " + attendanceDto);
        if (attendanceDto != null) {
            Long userId = attendanceDto.getStudentId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(attendanceDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (attendanceService.getTodayLog(attendanceDto) != null){
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.CHECK_IN_DONE_TODAY);
            }
            int add = attendanceService.checkIn(attendanceDto);
            if (add == 1) {
                return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.CHECK_IN_SUCCESS.getMessage(), add);
            }
            return ResponseEntityBuilder.buildNormalResponse();
        } else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    // absen keluar
    @RequestMapping(value = "/checkOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto checkOut(@RequestBody AttendanceDto attendanceDto) {
        logger.info("attendance.checkOut parameter = " + attendanceDto);
        if (attendanceDto != null) {
            Long userId = attendanceDto.getStudentId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(attendanceDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            // cek absen
            AttendanceDto checkLogToday = attendanceService.getTodayLog(attendanceDto);
            if (checkLogToday == null){
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.CHECK_IN_UN_DONE_TODAY);
            } else {
                attendanceDto.setId(checkLogToday.getId());
                attendanceDto.setCheckInTime(checkLogToday.getCheckInTime());
                int add = attendanceService.checkOut(attendanceDto);
                if (add == 1) {
                    return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.CHECK_OUT_SUCCESS.getMessage(), add);
                }
            }
            return ResponseEntityBuilder.buildNormalResponse();
        } else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    // mendapatkan absensi murid
    @RequestMapping(value = "/getStudentAttendance", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getStudentAttendance(@RequestBody AttendanceDto attendanceDto) {
        logger.info("attendance.getStudentAttendance parameter = " + attendanceDto);
        if (attendanceDto != null){
            List<AttendanceDto> list = attendanceService.getStudentAttendance(attendanceDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
