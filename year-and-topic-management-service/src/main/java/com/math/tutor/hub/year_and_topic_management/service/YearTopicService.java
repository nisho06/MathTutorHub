package com.math.tutor.hub.year_and_topic_management.service;

import com.math.tutor.hub.year_and_topic_management.dto.YearTopicDTO;
import com.math.tutor.hub.year_and_topic_management.exception.YearTopicNotFoundException;
import com.math.tutor.hub.year_and_topic_management.mapper.YearTopicMapper;
import com.math.tutor.hub.year_and_topic_management.repository.YearTopicRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
@AllArgsConstructor
public class YearTopicService {

    private YearTopicRepository yearTopicRepository;

    public YearTopicDTO getYearTopicDetailsFromId(int yearTopicId) {
        return YearTopicMapper.convertToYearTopicDTO(yearTopicRepository.findByYearTopicId(yearTopicId).orElseThrow(
                () -> new YearTopicNotFoundException("Year Topic mapping not found for the id:- " + yearTopicId)));
    }
}
