package com.math.tutor.hub.year_and_topic_management.mapper;

import com.math.tutor.hub.year_and_topic_management.dto.StudentYearResponseDTO;
import com.math.tutor.hub.year_and_topic_management.dto.TopicResponseDTO;
import com.math.tutor.hub.year_and_topic_management.dto.YearTopicDTO;
import com.math.tutor.hub.year_and_topic_management.model.YearTopic;

public class YearTopicMapper {

    public static YearTopicDTO convertToYearTopicDTO(YearTopic yearTopic){
        YearTopicDTO yearTopicDTO = new YearTopicDTO();
        TopicResponseDTO topicResponseDTO = TopicMapper.convertToTopicResponseDTO(yearTopic.getTopic());
        StudentYearResponseDTO studentYearResponseDTO =
                StudentYearMapper.convertToStudentYearResponseDTO(yearTopic.getStudentYear());
        yearTopicDTO.setYearTopicId(yearTopic.getYearTopicId());
        yearTopicDTO.setTopic(topicResponseDTO);
        yearTopicDTO.setStudentYear(studentYearResponseDTO);
        return yearTopicDTO;
    }
}
