package com.math.tutor.hub.year_and_topic_management.service;

import com.math.tutor.hub.year_and_topic_management.dto.TopicResponseDTO;
import com.math.tutor.hub.year_and_topic_management.dto.TopicsForStudentYearDTO;
import com.math.tutor.hub.year_and_topic_management.exception.StudentYearNotFoundException;
import com.math.tutor.hub.year_and_topic_management.exception.TopicNotFoundException;
import com.math.tutor.hub.year_and_topic_management.mapper.TopicMapper;
import com.math.tutor.hub.year_and_topic_management.model.StudentYear;
import com.math.tutor.hub.year_and_topic_management.model.Topic;
import com.math.tutor.hub.year_and_topic_management.repository.TopicRepository;
import com.math.tutor.hub.year_and_topic_management.repository.StudentYearRepository;
import com.math.tutor.hub.year_and_topic_management.repository.YearTopicRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Data
@Service
public class TopicService {

    private TopicRepository topicRepository;

    private YearTopicRepository yearTopicRepository;

    private StudentYearRepository studentYearRepository;


    public List<TopicResponseDTO> getAvailableTopics() {
        List<Topic> topicList = topicRepository.findAll();
        return (TopicMapper.convertToTopicResponseDTOList(topicList));
    }

    public TopicResponseDTO getTopicForId(int topicId) {
        Topic topic = topicRepository.findByTopicId(topicId).orElseThrow(() ->
                new TopicNotFoundException("Topic not found for the topic id:- " + topicId));
        return TopicMapper.convertToTopicResponseDTO(topic);
    }

    public TopicsForStudentYearDTO getAllTopicsForStudentYear(int studentYearLabel) {
        StudentYear studentYear = studentYearRepository.findByYearLabel(studentYearLabel)
                .orElseThrow(() -> new StudentYearNotFoundException("Student year:- " + studentYearLabel + " not found."));
        return TopicMapper.convertToTopicsForStudentYearDTO(studentYear);
    }

    public TopicResponseDTO registerTopic(String topic) {
        Topic topicObject = new Topic();
        topicObject.setTopic(topic);
        return TopicMapper.convertToTopicResponseDTO(topicRepository.save(topicObject));
    }
}
