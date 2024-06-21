package com.project.student.service;

import com.project.student.dto.ELearningDto;
import com.project.student.dto.EssayDto;
import com.project.student.serviceImpl.StudyServiceHystrix;
import com.project.student.util.ResponseEntityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "STUDY-SERVICE",fallbackFactory = StudyServiceHystrix.class)
public interface StudyService {
    @RequestMapping(value = "/study/learning/getAllMaterial", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAllMaterial(@RequestBody ELearningDto dto);

    @RequestMapping(value = "/study/task/getAllEssay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAllEssay(@RequestBody EssayDto dto);

    @RequestMapping(value = "/study/task/sendEssayAnswer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto sendEssayAnswer(@RequestBody EssayDto dto);
}
