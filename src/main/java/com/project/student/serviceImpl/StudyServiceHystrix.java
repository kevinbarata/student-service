package com.project.student.serviceImpl;

import com.project.student.dto.ELearningDto;
import com.project.student.dto.EssayDto;
import com.project.student.service.StudyService;
import com.project.student.util.ResponseEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class StudyServiceHystrix implements FallbackFactory<StudyService> {

    Logger logger = LoggerFactory.getLogger(StudyServiceHystrix.class);

    @Override
    public StudyService create(Throwable e) {
        return new StudyService() {

            @Override
            public ResponseEntityDto getAllMaterial(@RequestBody ELearningDto dto) {
                logger.error("Got an error when executing 'getAllMaterial(ELearningDto dto)'!!!",e);
                return null;
            }

            @Override
            public ResponseEntityDto getAllEssay(@RequestBody EssayDto dto) {
                logger.error("Got an error when executing 'getAllEssay(EssayDto dto)'!!!",e);
                return null;
            }

            @Override
            public ResponseEntityDto sendEssayAnswer(@RequestBody EssayDto dto) {
                logger.error("Got an error when executing 'sendEssayAnswer(EssayDto dto)'!!!",e);
                return null;
            }

        };
    }

}
