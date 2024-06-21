package com.project.student.dao;

import com.project.student.dto.BiodataDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentBiodataDao {

    int insert(BiodataDto dto);

    int update(BiodataDto dto);

    BiodataDto getBiodataByStudentId(BiodataDto dto);
}
