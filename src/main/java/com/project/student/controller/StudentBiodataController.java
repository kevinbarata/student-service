package com.project.student.controller;

import com.project.student.config.JwtUtil;
import com.project.student.dao.UserSessionDao;
import com.project.student.dto.BiodataDto;
import com.project.student.service.StudentBiodataService;
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

@RestController
@RequestMapping(value="/biodata")
public class StudentBiodataController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudentBiodataService studentBiodataService;

    @Autowired
    private UserSessionDao userSessionDao;

    // buat atau perbaharui biodata murid
    @RequestMapping(value = "/addStudentBiodata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto addStudentBiodata(@RequestBody BiodataDto biodataDto) {
        logger.info("biodata.addStudentBiodata parameter = " + biodataDto);
        if (biodataDto != null) {
            Long userId = biodataDto.getStudentId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(biodataDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            // find biodata
            BiodataDto biodata = studentBiodataService.getBiodataByStudentId(biodataDto);
            if (biodata != null){
                int update = studentBiodataService.update(biodataDto);
                if (update == 1) {
                    return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.UPDATE_BIODATA_SUCCESS.getMessage(), update);
                }
            }else {
                int insert = studentBiodataService.insert(biodataDto);
                if (insert == 1) {
                    return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.UPDATE_BIODATA_SUCCESS.getMessage(), insert);
                }
            }

            return ResponseEntityBuilder.buildNormalResponse();
        } else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    // mendapatkan biodata murid
    @RequestMapping(value = "/getBiodataByStudentId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getBiodataByStudentId(@RequestBody BiodataDto biodataDto) {
        logger.info("biodata.getBiodataByStudentId parameter = " + biodataDto);
        if (biodataDto != null){
            BiodataDto list = studentBiodataService.getBiodataByStudentId(biodataDto);
            logger.info("getBiodataByStudentId = " + list);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
