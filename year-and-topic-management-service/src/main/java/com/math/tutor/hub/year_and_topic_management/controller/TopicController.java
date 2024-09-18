package com.math.tutor.hub.year_and_topic_management.controller;

import com.math.tutor.hub.year_and_topic_management.dto.TopicResponseDTO;
import com.math.tutor.hub.year_and_topic_management.dto.TopicsForStudentYearDTO;
import com.math.tutor.hub.year_and_topic_management.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {

    private TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService){
        this.topicService = topicService;
    }

    @GetMapping()
    public List<TopicResponseDTO> getAvailableTopics(){
        return topicService.getAvailableTopics();
    }

    @GetMapping(path = "/{topicId}")
    public ResponseEntity<?> getTopicForId(@PathVariable int topicId){
        TopicResponseDTO topicResponseDTO = topicService.getTopicForId(topicId);
        return new ResponseEntity<>(topicResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAllTopicsForStudentYear(@RequestParam int studentYear){
        TopicsForStudentYearDTO topicsForStudentYearDTO = topicService.getAllTopicsForStudentYear(studentYear);
        return new ResponseEntity<>(topicsForStudentYearDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> registerTopic(@RequestParam String topic){
        TopicResponseDTO topicResponseDTO = topicService.registerTopic(topic);
        return new ResponseEntity<>(topicResponseDTO, HttpStatus.OK);
    }
}
