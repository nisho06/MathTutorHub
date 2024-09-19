package com.math.tutor.hub.tutoring_request_service.mapper;

import com.math.tutor.hub.tutoring_request_service.dto.TutoringRequestResponseDTO;
import com.math.tutor.hub.tutoring_request_service.model.RequestTopic;
import com.math.tutor.hub.tutoring_request_service.model.TutoringRequest;

import java.util.ArrayList;
import java.util.List;

public class TutoringRequestMapper {

    public static TutoringRequestResponseDTO convertToTutoringRequestResponseDTO(TutoringRequest tutoringRequest){
        TutoringRequestResponseDTO tutoringRequestResponseDTO = new TutoringRequestResponseDTO();
        tutoringRequestResponseDTO.setRequestId(tutoringRequest.getRequestId());
        tutoringRequestResponseDTO.setStudentName(tutoringRequest.getStudentName());
        tutoringRequestResponseDTO.setUserId(tutoringRequest.getUserId());
        tutoringRequestResponseDTO.setParentMobile(tutoringRequest.getParentMobile());
        tutoringRequestResponseDTO.setModeOfTeaching(tutoringRequest.getModeOfTeaching());
        tutoringRequestResponseDTO.setRequestDate(tutoringRequest.getRequestDate());
        tutoringRequestResponseDTO.setStatus(tutoringRequest.getStatus());

        return tutoringRequestResponseDTO;
    }

    public static List<Integer> convertToYearTopicIdList(List<RequestTopic> requestTopicList){
        List<Integer> yearTopicIdList = new ArrayList<>();
        for(RequestTopic requestTopic: requestTopicList){
            yearTopicIdList.add(requestTopic.getRequestTopicPK().getYearTopicId());
        }
        return yearTopicIdList;
    }
}
