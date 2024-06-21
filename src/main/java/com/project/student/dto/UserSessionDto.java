package com.project.student.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserSessionDto {
    private Long id;
    private Long userId;
    private String token;

    private Date createTime;
    private Date updateTime;

}
