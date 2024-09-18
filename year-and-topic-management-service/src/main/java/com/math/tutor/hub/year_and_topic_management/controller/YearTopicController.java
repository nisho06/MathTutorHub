package com.math.tutor.hub.year_and_topic_management.controller;

import com.math.tutor.hub.year_and_topic_management.dto.YearTopicDTO;
import com.math.tutor.hub.year_and_topic_management.service.YearTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/year-topic")
public class YearTopicController {

    private YearTopicService yearTopicService;

    @Autowired
    public YearTopicController(YearTopicService yearTopicService){
        this.yearTopicService = yearTopicService;
    }

    @RequestMapping(path = "/{yearTopicId}")
    public ResponseEntity<?> getYearTopicDetailsFromId(@PathVariable int yearTopicId){
        YearTopicDTO yearTopicDTO = yearTopicService.getYearTopicDetailsFromId(yearTopicId);
        return new ResponseEntity<>(yearTopicDTO, HttpStatus.OK);
    }

}
