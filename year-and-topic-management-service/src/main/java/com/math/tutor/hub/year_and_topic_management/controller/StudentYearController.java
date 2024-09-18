package com.math.tutor.hub.year_and_topic_management.controller;

import com.math.tutor.hub.year_and_topic_management.dto.StudentYearResponseDTO;
import com.math.tutor.hub.year_and_topic_management.service.StudentYearService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getAvailableStudentYears(){
        List<StudentYearResponseDTO> studentYearResponseDTOList = studentYearService.getAvailableStudentYears();
        return new ResponseEntity<>(studentYearResponseDTOList, HttpStatus.OK);
    }

    @GetMapping(path = "/{yearId}")
    public ResponseEntity<?> getStudentYearForId(@PathVariable int yearId){
        StudentYearResponseDTO studentYearResponseDTO = studentYearService.getStudentYearForId(yearId);
        return new ResponseEntity<>(studentYearResponseDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/{year}")
    public ResponseEntity<?> addTopicToStudentYear(@PathVariable int year, @RequestParam List<String> topic){
        studentYearService.addTopicToStudentYear(year, topic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
