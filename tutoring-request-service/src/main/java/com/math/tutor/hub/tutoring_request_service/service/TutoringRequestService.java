package com.math.tutor.hub.tutoring_request_service.service;

import com.math.tutor.hub.tutoring_request_service.dto.StudentYearResponseDTO;
import com.math.tutor.hub.tutoring_request_service.dto.TutoringRequestResponseDTO;
import com.math.tutor.hub.tutoring_request_service.dto.YearTopicDTO;
import com.math.tutor.hub.tutoring_request_service.exceptions.TopicNotExistForYearException;
import com.math.tutor.hub.tutoring_request_service.exceptions.TutoringRequestNotFoundException;
import com.math.tutor.hub.tutoring_request_service.mapper.TutoringRequestMapper;
import com.math.tutor.hub.tutoring_request_service.model.TutoringRequest;
import com.math.tutor.hub.tutoring_request_service.repository.TutoringRequestRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class TutoringRequestService {

    private final TutoringRequestRepository tutoringRequestRepository;

    private final WebClient yearTopicWebClient;

    private final WebClient yearsWebClient;

    public TutoringRequestService(TutoringRequestRepository tutoringRequestRepository,
                                  @Qualifier("yearTopicWebClient") WebClient yearTopicWebClient,
                                  @Qualifier("yearsWebClient") WebClient yearsWebClient) {
        this.tutoringRequestRepository = tutoringRequestRepository;
        this.yearTopicWebClient = yearTopicWebClient;
        this.yearsWebClient = yearsWebClient;
    }

    public TutoringRequestResponseDTO getTutoringRequestForId(int requestId) {
        List<String> topics = new ArrayList<>();
        TutoringRequest tutoringRequest = tutoringRequestRepository.findByRequestId(requestId).orElseThrow(
                () -> new TutoringRequestNotFoundException("Tutoring Request not found for the id:- " + requestId));
        List<YearTopicDTO> yearTopicDTOList = getYearTopicListFromIds(
                TutoringRequestMapper.convertToYearTopicIdList(tutoringRequest.getRequestTopics()));

        int studentYear = getStudentYearFromId(tutoringRequest.getStudentYearId()).getYear();

        for (YearTopicDTO yearTopicDTO: yearTopicDTOList){
            if (yearTopicDTO.getStudentYear().getYear() != studentYear){
                throw new TopicNotExistForYearException(
                        "Topic '" + yearTopicDTO.getTopic().getTopic() + "' does not exist for year " + studentYear);
            }
            topics.add(yearTopicDTO.getTopic().getTopic());
        }

        TutoringRequestResponseDTO tutoringRequestResponseDTO =
                TutoringRequestMapper.convertToTutoringRequestResponseDTO(tutoringRequest);
        tutoringRequestResponseDTO.setTopics(topics);
        tutoringRequestResponseDTO.setStudentYear(studentYear);

        return tutoringRequestResponseDTO;
    }

    public List<YearTopicDTO> getYearTopicListFromIds(List<Integer> yearTopicIdList) {
        return yearTopicWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("id", yearTopicIdList.toArray())
                        .build())
                .retrieve()
                .bodyToFlux(YearTopicDTO.class)
                .collectList()
                .block();
    }

    public StudentYearResponseDTO getStudentYearFromId(int yearId){
        return yearsWebClient.get()
                .uri("/{yearId}", yearId)
                .retrieve()
                .bodyToMono(StudentYearResponseDTO.class)
                .block();
    }
}
