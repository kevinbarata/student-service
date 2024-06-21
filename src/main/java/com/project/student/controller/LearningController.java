package com.project.student.controller;

import com.project.student.config.JwtUtil;
import com.project.student.dao.UserSessionDao;
import com.project.student.dto.ELearningDto;
import com.project.student.dto.EssayDto;
import com.project.student.service.StudyService;
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
@RequestMapping(value="/learning")
public class LearningController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudyService studyService;

    @Autowired
    private UserSessionDao userSessionDao;

    // mendapatkan semua list materi
    @RequestMapping(value = "/getAllMaterial", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAllMaterial(@RequestBody ELearningDto dto) {
        logger.info("learning.getAllMaterial parameter = " + dto);
        if (dto != null){
            Long userId = dto.getStudentId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(dto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
           ResponseEntityDto list = studyService.getAllMaterial(dto);
            return ResponseEntityBuilder.buildNormalResponse(list.getData());
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    // mendapatkan semua list essay
    @RequestMapping(value = "/getAllEssay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAllEssay(@RequestBody EssayDto dto) {
        logger.info("learning.getAllEssay parameter = " + dto);
        if (dto != null){
            Long userId = dto.getStudentId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(dto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            ResponseEntityDto list = studyService.getAllEssay(dto);
            return ResponseEntityBuilder.buildNormalResponse(list.getData());
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    // menjawab pertanyaan essat
    @RequestMapping(value = "/sendEssayAnswer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto sendEssayAnswer(@RequestBody EssayDto dto) {
        logger.info("learning.sendEssayAnswer parameter = " + dto);
        if (dto != null){
            Long userId = dto.getStudentId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(dto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            ResponseEntityDto send = studyService.sendEssayAnswer(dto);
            if (send.getStatus().equals("1")){
                return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.ESSAY_ANSWER_SUCCESS.getMessage(), send.getData());
            }
            return ResponseEntityBuilder.buildNormalResponse();
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
