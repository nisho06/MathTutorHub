package com.math.tutor.hub.year_and_topic_management.service;

import com.math.tutor.hub.year_and_topic_management.dto.StudentYearResponseDTO;
import com.math.tutor.hub.year_and_topic_management.exception.StudentYearNotFoundException;
import com.math.tutor.hub.year_and_topic_management.mapper.StudentYearMapper;
import com.math.tutor.hub.year_and_topic_management.model.StudentYear;
import com.math.tutor.hub.year_and_topic_management.model.Topic;
import com.math.tutor.hub.year_and_topic_management.model.YearTopic;
import com.math.tutor.hub.year_and_topic_management.repository.StudentYearRepository;
import com.math.tutor.hub.year_and_topic_management.repository.TopicRepository;
import com.math.tutor.hub.year_and_topic_management.repository.YearTopicRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class StudentYearService {

    private StudentYearRepository studentYearRepository;

    private YearTopicRepository yearTopicRepository;

    private TopicRepository topicRepository;

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

    @Transactional
    public void addTopicToStudentYear(int year, List<String> topicList) {
        StudentYear studentYear = studentYearRepository.findByYearLabel(year)
                .orElseThrow( () -> new StudentYearNotFoundException("Student year:- " + year + " not found."));
        for (String topic: topicList){

            Optional<Topic> topicOptional = topicRepository.findByTopic(topic);
            Topic topicToBeAdded = new Topic();
            Topic existingTopic;

            if (topicOptional.isEmpty()){
                topicToBeAdded.setTopic(topic);
                existingTopic = topicRepository.save(topicToBeAdded);
            } else {
                existingTopic = topicOptional.get();
            }

            YearTopic yearTopic = new YearTopic();
            yearTopic.setTopicId(existingTopic.getTopicId());
            yearTopic.setYearId(studentYear.getYearId());
            yearTopic.setTopic(existingTopic);
            yearTopic.setStudentYear(studentYear);

            yearTopicRepository.save(yearTopic);
        }
    }
}
