package com.math.tutor.hub.year_and_topic_management.controller;

import com.math.tutor.hub.year_and_topic_management.dto.TopicResponseDTO;
import com.math.tutor.hub.year_and_topic_management.dto.TopicsForStudentYear;
import com.math.tutor.hub.year_and_topic_management.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public TopicResponseDTO getTopicForId(@PathVariable int topicId){
        return topicService.getTopicForId(topicId);
    }

    @GetMapping("/search")
    public TopicsForStudentYear getAllTopicsForStudentYear(@RequestParam int studentYear){
        return topicService.getAllTopicsForStudentYear(studentYear);
    }
}
