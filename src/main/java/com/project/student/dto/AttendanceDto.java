package com.project.student.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AttendanceDto {
    private Long id;
    private Long studentId;
    private Date checkInTime;
    private String checkInLocation; //deteksi dari frontend
    private Date checkOutTime;
    private String checkOutLocation; //deteksi dari frontend
    private int attendanceHour;
    private Date createTime;
    private Date updateTime;
    private String token;

}
