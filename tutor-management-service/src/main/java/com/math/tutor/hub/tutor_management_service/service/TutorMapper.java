package com.math.tutor.hub.tutor_management_service.service;

import com.math.tutor.hub.tutor_management_service.dto.TutorDTO;
import com.math.tutor.hub.tutor_management_service.dto.TutorYearsDTO;
import com.math.tutor.hub.tutor_management_service.model.Tutor;
import com.math.tutor.hub.tutor_management_service.model.TutorYears;

import java.util.ArrayList;
import java.util.List;

public class TutorMapper {

    public static TutorDTO convertTutorToTutorDTO(Tutor tutor){

        TutorDTO tutorDTO = new TutorDTO();
        tutorDTO.setFirstName(tutor.getFirstName());
        tutorDTO.setSurname(tutor.getSurname());
        tutorDTO.setEmail(tutor.getEmail());
        tutorDTO.setPhoneNumber(tutor.getPhoneNumber());
        tutorDTO.setPostcode(tutor.getPostcode());
        tutorDTO.setIsQualified(tutor.getIsQualified());
        tutorDTO.setIsActive(tutor.getIsActive());
        tutorDTO.setTutorYears(convertToTutorYearsDTOList(tutor.getTutorYears()));

        return tutorDTO;
    }

    public static List<TutorDTO> convertToTutorDTOList(List<Tutor> tutorList){
        List<TutorDTO> tutorDTOList = new ArrayList<>();
        for(Tutor tutor: tutorList){
            tutorDTOList.add(convertTutorToTutorDTO(tutor));
        }
        return tutorDTOList;
    }

    public static List<TutorYearsDTO> convertToTutorYearsDTOList(List<TutorYears> tutorYearsList){
        List<TutorYearsDTO> tutorYearsDTOList = new ArrayList<>();
        for (TutorYears tutorYears: tutorYearsList){
            TutorYearsDTO tutorYearsDTO = new TutorYearsDTO();
            tutorYearsDTO.setTutorYear(tutorYears.getTutorYearsPK().getTutoringYear());
            tutorYearsDTOList.add(tutorYearsDTO);
        }
        return tutorYearsDTOList;
    }

    public static Tutor convertToTutor(TutorDTO tutorDTO){
        Tutor tutor = new Tutor();

        tutor.setFirstName(tutorDTO.getFirstName());
        tutor.setSurname(tutorDTO.getSurname());
        tutor.setEmail(tutorDTO.getEmail());
        tutor.setPhoneNumber(tutorDTO.getPhoneNumber());
        tutor.setPostcode(tutorDTO.getPostcode());
        tutor.setIsActive(tutorDTO.getIsActive());
        tutor.setIsQualified(tutorDTO.getIsQualified());

        return tutor;
    }
}
