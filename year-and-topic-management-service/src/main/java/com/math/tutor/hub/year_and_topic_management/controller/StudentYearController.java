package com.math.tutor.hub.year_and_topic_management.controller;

import com.math.tutor.hub.year_and_topic_management.dto.StudentYearResponseDTO;
import com.math.tutor.hub.year_and_topic_management.service.StudentYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/years")
public class StudentYearController {

    private StudentYearService studentYearService;

    @Autowired
    public StudentYearController(StudentYearService studentYearService){
        this.studentYearService = studentYearService;
    }

    @GetMapping()
    public List<StudentYearResponseDTO> getAvailableStudentYears(){
        return studentYearService.getAvailableStudentYears();
    }

    @GetMapping(path = "/{yearId}")
    public StudentYearResponseDTO getStudentYearForId(@PathVariable int yearId){
        return studentYearService.getStudentYearForId(yearId);
    }
}
