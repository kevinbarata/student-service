package com.project.student.service;

import com.project.student.dto.BiodataDto;

public interface StudentBiodataService {

    int insert(BiodataDto biodataDto);

    int update(BiodataDto biodataDto);

    BiodataDto getBiodataByStudentId(BiodataDto biodataDto);
}
