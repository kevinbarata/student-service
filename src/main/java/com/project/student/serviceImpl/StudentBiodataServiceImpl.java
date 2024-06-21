package com.project.student.serviceImpl;

import com.project.student.dao.StudentBiodataDao;
import com.project.student.dto.BiodataDto;
import com.project.student.service.StudentBiodataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentBiodataServiceImpl implements StudentBiodataService {

    @Autowired
    private StudentBiodataDao studentBiodataDao;

    @Override
    public int insert(BiodataDto biodataDto) {
        int insert = studentBiodataDao.insert(biodataDto);
        return insert;
    }

    @Override
    public int update(BiodataDto biodataDto) {
        int insert = studentBiodataDao.update(biodataDto);
        return insert;
    }


    @Override
    public BiodataDto getBiodataByStudentId(BiodataDto biodataDto) {
        BiodataDto res = studentBiodataDao.getBiodataByStudentId(biodataDto);
        return res;
    }
}
