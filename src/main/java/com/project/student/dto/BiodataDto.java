package com.project.student.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BiodataDto {
    private Long id;

    private Long studentId;
    private String studentName;
    private String gender;

    private String major;
    private String grade;
    private String motherName;
    private String fatherName;
    private String religion;

    private String bornCity;
    private String birthDay;
    private String province;
    private String city;
    private String district;

    private String address;
    private Date createTime;
    private Date updateTime;
    private String token;

}
