package com.math.tutor.hub.year_and_topic_management.mapper;

import com.math.tutor.hub.year_and_topic_management.dto.TopicRequestDTO;
import com.math.tutor.hub.year_and_topic_management.dto.TopicResponseDTO;
import com.math.tutor.hub.year_and_topic_management.dto.TopicsForStudentYearDTO;
import com.math.tutor.hub.year_and_topic_management.model.StudentYear;
import com.math.tutor.hub.year_and_topic_management.model.Topic;
import com.math.tutor.hub.year_and_topic_management.model.YearTopic;

import java.util.ArrayList;
import java.util.List;

public class TopicMapper {

    public static Topic convertToTopic(TopicRequestDTO topicRequestDTO){
        Topic topic = new Topic();
        topic.setTopic(topicRequestDTO.getTopic());
        return topic;
    }

    public static TopicResponseDTO convertToTopicResponseDTO(Topic topic){
        TopicResponseDTO topicResponseDTO = new TopicResponseDTO();
        topicResponseDTO.setTopicId(topic.getTopicId());
        topicResponseDTO.setTopic(topic.getTopic());
        return topicResponseDTO;
    }

    public static List<TopicResponseDTO> convertToTopicResponseDTOList(List<Topic> topicList){
        List<TopicResponseDTO> topicResponseDTOList = new ArrayList<>();
        for(Topic topic: topicList){
            topicResponseDTOList.add(convertToTopicResponseDTO(topic));
        }
        return topicResponseDTOList;
    }

    public static TopicsForStudentYearDTO convertToTopicsForStudentYearDTO(StudentYear studentYear){
        TopicsForStudentYearDTO topicsForStudentYearDTO = new TopicsForStudentYearDTO();
        List<String> topicList = new ArrayList<>();

        List<YearTopic> yearTopicList = studentYear.getYearTopicSet();
        for (YearTopic yearTopic: yearTopicList){
            topicList.add(yearTopic.getTopic().getTopic());
        }

        topicsForStudentYearDTO.setYearId(studentYear.getYearId());
        topicsForStudentYearDTO.setYear(studentYear.getYearLabel());
        topicsForStudentYearDTO.setTopics(topicList);

        return topicsForStudentYearDTO;
    }
}
