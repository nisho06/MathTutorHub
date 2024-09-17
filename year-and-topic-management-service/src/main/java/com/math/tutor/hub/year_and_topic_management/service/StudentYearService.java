package com.math.tutor.hub.year_and_topic_management.service;

import com.math.tutor.hub.year_and_topic_management.dto.StudentYearResponseDTO;
import com.math.tutor.hub.year_and_topic_management.exception.StudentYearNotFoundException;
import com.math.tutor.hub.year_and_topic_management.mapper.StudentYearMapper;
import com.math.tutor.hub.year_and_topic_management.repository.StudentYearRepository;
import com.math.tutor.hub.year_and_topic_management.repository.YearTopicRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class StudentYearService {

    private StudentYearRepository studentYearRepository;

    private YearTopicRepository yearTopicRepository;

    public List<StudentYearResponseDTO> getAvailableStudentYears(){
        List<com.math.tutor.hub.year_and_topic_management.model.StudentYear> studentYearList =
                studentYearRepository.findAll();
        return StudentYearMapper.convertToStudentYearResponseDTOList(studentYearList);
    }

    public StudentYearResponseDTO getStudentYearForId(int yearId) {
        return StudentYearMapper.convertToStudentYearResponseDTO(studentYearRepository.findByYearId(yearId)
                .orElseThrow(() ->
                        new StudentYearNotFoundException("Student Year not found for the year id:- " + yearId)));
    }

}
