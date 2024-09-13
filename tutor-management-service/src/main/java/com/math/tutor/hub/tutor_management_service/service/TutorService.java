package com.math.tutor.hub.tutor_management_service.service;

import com.math.tutor.hub.tutor_management_service.dto.TutorDTO;
import com.math.tutor.hub.tutor_management_service.dto.TutorYearsDTO;
import com.math.tutor.hub.tutor_management_service.exceptions.TutorAlreadyExistException;
import com.math.tutor.hub.tutor_management_service.exceptions.TutorNotFoundException;
import com.math.tutor.hub.tutor_management_service.model.Tutor;
import com.math.tutor.hub.tutor_management_service.model.TutorYears;
import com.math.tutor.hub.tutor_management_service.model.TutorYearsPK;
import com.math.tutor.hub.tutor_management_service.repository.TutorRepository;
import com.math.tutor.hub.tutor_management_service.repository.TutorYearsRepository;
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
            for (TutorYearsDTO tutorYearsDTO : tutorDTO.getTutorYears()){
                TutorYears tutorYears = new TutorYears();
                TutorYearsPK tutorYearsPK = new TutorYearsPK(savedTutor.getTutorId(), tutorYearsDTO.getTutorYear());
                tutorYears.setTutorYearsPK(tutorYearsPK);
                tutorYears.setTutor(savedTutor);
                tutorYearsList.add(tutorYears);
            }
            tutorYearsRepository.saveAll(tutorYearsList);
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
        List<TutorYearsDTO> tutorYears = tutorDTO.getTutorYears();

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
            for (TutorYearsDTO tutorYearsDTO: tutorYears){
                TutorYearsPK tutorYearsPK =
                        new TutorYearsPK(existingTutor.getTutorId(), tutorYearsDTO.getTutorYear());
                tutorYearsList.add(new TutorYears(tutorYearsPK, existingTutor));
            }

            tutorYearsRepository.saveAll(tutorYearsList);
            existingTutor.setTutorYears(tutorYearsRepository.findTutorYearsByTutorId(existingTutor.getTutorId()));
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
                                   Boolean isQualified, Boolean isActive, Integer tutorYear) {
        if (firstName == null && surname == null && postcode == null && phoneNumber == null &&
                isQualified == null && isActive == null && tutorYear == null){
            return getAllTutors();
        } else {
            return TutorMapper.convertToTutorDTOList(tutorRepository.filterTutors(firstName, surname, postcode, phoneNumber,
                    isQualified, isActive, tutorYear));
        }
    }
}

