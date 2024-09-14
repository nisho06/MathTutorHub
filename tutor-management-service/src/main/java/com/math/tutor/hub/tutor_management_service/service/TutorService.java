package com.math.tutor.hub.tutor_management_service.service;

import com.math.tutor.hub.tutor_management_service.dto.TutorDTO;
import com.math.tutor.hub.tutor_management_service.enums.DayOfWeek;
import com.math.tutor.hub.tutor_management_service.exceptions.TutorAlreadyExistException;
import com.math.tutor.hub.tutor_management_service.exceptions.TutorNotFoundException;
import com.math.tutor.hub.tutor_management_service.model.*;
import com.math.tutor.hub.tutor_management_service.repository.TutorRepository;
import com.math.tutor.hub.tutor_management_service.repository.TutorYearsRepository;
import com.math.tutor.hub.tutor_management_service.repository.TutorsAvailabilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class TutorService {

    private TutorRepository tutorRepository;

    private TutorYearsRepository tutorYearsRepository;

    private TutorsAvailabilityRepository tutorsAvailabilityRepository;

    public List<TutorDTO> getAllTutors() {
        return TutorMapper.convertToTutorDTOList(tutorRepository.findAll());
    }

    public TutorDTO getTutorByEmail(String email) {
        return TutorMapper.convertTutorToTutorDTO(tutorRepository.findTutorByEmail(email)
                .orElseThrow(() -> new TutorNotFoundException("Tutor not found for the email:- " + email)));
    }

    public TutorDTO getTutorByTutorId(int tutorId) {
        return TutorMapper.convertTutorToTutorDTO(tutorRepository.findTutorByTutorId(tutorId)
                .orElseThrow(()-> new TutorNotFoundException("Tutor not found for the tutor id:- " + tutorId)));
    }

    @Transactional
    public TutorDTO registerTutor(TutorDTO tutorDTO) {
        boolean isTutorExist = tutorRepository.existsByEmail(tutorDTO.getEmail());

        if (isTutorExist){
            throw new TutorAlreadyExistException("Tutor already exist for the email:- " + tutorDTO.getEmail());
        }

        Tutor tutor = TutorMapper.convertToTutor(tutorDTO);
        tutor.setUpdatedAt(LocalDateTime.now());
        tutor.setCreatedAt(LocalDateTime.now());

        Tutor savedTutor = tutorRepository.save(tutor);

        if (tutorDTO.getTutorYears() != null){
            List<TutorYears> tutorYearsList = new ArrayList<>();
            for (Integer year : tutorDTO.getTutorYears()){
                TutorYears tutorYears = new TutorYears();
                TutorYearsPK tutorYearsPK = new TutorYearsPK(savedTutor.getTutorId(), year);
                tutorYears.setTutorYearsPK(tutorYearsPK);
                tutorYears.setTutor(savedTutor);
                tutorYearsList.add(tutorYears);
            }
            tutorYearsRepository.saveAll(tutorYearsList);
        }

        if (tutorDTO.getTutorAvailabilities() != null){
            List<TutorsAvailability> tutorsAvailabilityList = new ArrayList<>();
            for (DayOfWeek day: tutorDTO.getTutorAvailabilities()){
                TutorsAvailability tutorsAvailability = new TutorsAvailability();
                TutorsAvailabilityPK tutorsAvailabilityPK = new TutorsAvailabilityPK(savedTutor.getTutorId(), day);
                tutorsAvailability.setTutorsAvailabilityPK(tutorsAvailabilityPK);
                tutorsAvailability.setTutor(savedTutor);
                tutorsAvailabilityList.add(tutorsAvailability);
            }
            tutorsAvailabilityRepository.saveAll(tutorsAvailabilityList);
        }

        return tutorDTO;
    }

    @Transactional
    public TutorDTO updateTutor(String email, TutorDTO tutorDTO) {

        Tutor existingTutor = tutorRepository.findTutorByEmail(email)
                .orElseThrow(()-> new TutorNotFoundException("Tutor not found for the email:- " + email));

        String firstName = tutorDTO.getFirstName();
        String surname = tutorDTO.getSurname();
        String phoneNumber = tutorDTO.getPhoneNumber();
        String postcode = tutorDTO.getPostcode();
        Boolean isQualified = tutorDTO.getIsQualified();
        Boolean isActive = tutorDTO.getIsActive();
        List<Integer> tutorYears = tutorDTO.getTutorYears();
        List<DayOfWeek> availableDays = tutorDTO.getTutorAvailabilities();

        if (firstName != null && !Objects.equals(firstName, existingTutor.getFirstName())){
            existingTutor.setFirstName(firstName);
        }

        if (surname != null && !Objects.equals(surname, existingTutor.getSurname())){
            existingTutor.setSurname(surname);
        }

        if (phoneNumber != null && !Objects.equals(phoneNumber, existingTutor.getPhoneNumber())){
            existingTutor.setPhoneNumber(phoneNumber);
        }

        if (postcode != null && !Objects.equals(postcode, existingTutor.getPostcode())){
            existingTutor.setPostcode(postcode);
        }

        if (isQualified != null && !Objects.equals(isQualified, existingTutor.getIsQualified())){
            existingTutor.setIsQualified(isQualified);
        }

        if (isActive != null && !Objects.equals(isActive, existingTutor.getIsActive())){
            existingTutor.setIsActive(isActive);
        }

        if (tutorYears != null){
            List<TutorYears> tutorYearsList = new ArrayList<>();
            for (Integer year: tutorYears){
                TutorYearsPK tutorYearsPK =
                        new TutorYearsPK(existingTutor.getTutorId(), year);
                tutorYearsList.add(new TutorYears(tutorYearsPK, existingTutor));
            }

            tutorYearsRepository.saveAll(tutorYearsList);
            existingTutor.setTutorYears(tutorYearsRepository.findTutorYearsByTutorId(existingTutor.getTutorId()));
        }

        if (availableDays != null){
            List<TutorsAvailability> tutorsAvailabilityList = new ArrayList<>();
            for (DayOfWeek tutoringDay: availableDays){
                TutorsAvailabilityPK tutorsAvailabilityPK =
                        new TutorsAvailabilityPK(existingTutor.getTutorId(), tutoringDay);
                tutorsAvailabilityList.add(new TutorsAvailability(tutorsAvailabilityPK, existingTutor));
            }
            tutorsAvailabilityRepository.saveAll(tutorsAvailabilityList);
            existingTutor.setTutorsAvailabilities(
                    tutorsAvailabilityRepository.findTutorsAvailabilityByTutorId(existingTutor.getTutorId()));
        }

        existingTutor.setUpdatedAt(LocalDateTime.now());
        tutorRepository.save(existingTutor);

        return TutorMapper.convertTutorToTutorDTO(existingTutor);
    }

    public void deleteTutorByEmail(String email) {
        if (tutorRepository.existsByEmail(email)){
            tutorRepository.deleteByEmail(email);
        } else {
            throw new TutorNotFoundException("Tutor not found for the email:- " + email);
        }
    }

    public List<TutorDTO> filterTutor(String firstName, String surname, String postcode, String phoneNumber,
                                   Boolean isQualified, Boolean isActive,
                                      List<Integer> tutoringYear, List<DayOfWeek> dayOfWeekList) {
        if (firstName == null && surname == null && postcode == null && phoneNumber == null &&
                isQualified == null && isActive == null && tutoringYear == null && dayOfWeekList == null){
            return getAllTutors();
        } else {
            // TODO - Optimize the algorithm.
            int noOfTutoringYear = 0;
            if (tutoringYear != null) {
                noOfTutoringYear = tutoringYear.size();
            }
            int noOfDaysOfTutoring = 0;
            if (dayOfWeekList != null) {
                noOfDaysOfTutoring = dayOfWeekList.size();
            }

            if (tutoringYear != null && dayOfWeekList != null){
                return TutorMapper.convertToTutorDTOList(tutorRepository.filterTutors(firstName, surname, postcode, phoneNumber,
                        isQualified, isActive, tutoringYear, noOfTutoringYear, dayOfWeekList, noOfDaysOfTutoring));
            } else if (tutoringYear != null){
                return TutorMapper.convertToTutorDTOList(tutorRepository.filterTutorsWhenDayOfWeekIsNull(firstName, surname, postcode, phoneNumber,
                        isQualified, isActive, tutoringYear, noOfTutoringYear));
            } else {
                return TutorMapper.convertToTutorDTOList(tutorRepository.filterTutorsWhenTutoringYearIsNull(firstName, surname, postcode, phoneNumber,
                        isQualified, isActive, dayOfWeekList, noOfDaysOfTutoring));
            }
        }
    }
}

