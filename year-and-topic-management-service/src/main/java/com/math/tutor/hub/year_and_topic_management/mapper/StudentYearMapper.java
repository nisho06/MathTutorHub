package com.math.tutor.hub.year_and_topic_management.mapper;

import com.math.tutor.hub.year_and_topic_management.dto.StudentYearRequestDTO;
import com.math.tutor.hub.year_and_topic_management.dto.StudentYearResponseDTO;
import com.math.tutor.hub.year_and_topic_management.model.StudentYear;

import java.util.ArrayList;
import java.util.List;

public class StudentYearMapper {


    public static StudentYear convertToStudentYear(StudentYearRequestDTO studentYearRequestDTO){
        StudentYear studentYear = new StudentYear();
        studentYear.setYearLabel(studentYearRequestDTO.getStudentYearLabel());
        return studentYear;
    }

    public static StudentYearResponseDTO convertToStudentYearResponseDTO(StudentYear studentYear){
        StudentYearResponseDTO studentYearResponseDTO = new StudentYearResponseDTO();
        studentYearResponseDTO.setYearId(studentYear.getYearId());
        studentYearResponseDTO.setYear(studentYear.getYearLabel());
        return studentYearResponseDTO;
    }

    public static List<StudentYearResponseDTO> convertToStudentYearResponseDTOList(List<StudentYear> studentYearList){
        List<StudentYearResponseDTO> studentYearResponseDTOList = new ArrayList<>();
        for (StudentYear studentYear: studentYearList){
            studentYearResponseDTOList.add(convertToStudentYearResponseDTO(studentYear));
        }
        return studentYearResponseDTOList;
    }
}
