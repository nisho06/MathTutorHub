package com.math.tutor.hub.tutor_management_service.service;

import com.math.tutor.hub.tutor_management_service.exceptions.TutorAlreadyExistException;
import com.math.tutor.hub.tutor_management_service.exceptions.TutorNotFoundException;
import com.math.tutor.hub.tutor_management_service.model.Tutor;
import com.math.tutor.hub.tutor_management_service.repository.TutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class TutorService {

    private TutorRepository tutorRepository;

    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    public Tutor getTutorByEmail(String email) {
        return tutorRepository.findTutorByEmail(email)
                .orElseThrow(() -> new TutorNotFoundException("Tutor not found for the email:- " + email));
    }

    public Tutor getTutorByTutorId(int tutorId) {
        return tutorRepository.findTutorByTutorId(tutorId)
                .orElseThrow(()-> new TutorNotFoundException("Tutor not found for the tutor id:- " + tutorId));
    }

    public Tutor registerTutor(Tutor tutor) {
        boolean isTutorExist = tutorRepository.existsByEmail(tutor.getEmail());

        if (isTutorExist){
            throw new TutorAlreadyExistException("Tutor already exist for the email:- " + tutor.getEmail());
        }

        tutor.setUpdatedAt(LocalDateTime.now());
        tutor.setCreatedAt(LocalDateTime.now());

        tutorRepository.save(tutor);

        return tutor;
    }

    public Tutor updateTutor(String email, Tutor tutor) {

        Tutor existingTutor = tutorRepository.findTutorByEmail(email)
                .orElseThrow(()-> new TutorNotFoundException("Tutor not found for the email:- " + email));

        String firstName = tutor.getFirstName();
        String surname = tutor.getSurname();
        String phoneNumber = tutor.getPhoneNumber();
        String postcode = tutor.getPostcode();
        Boolean isQualified = tutor.getIsQualified();
        Boolean isActive = tutor.getIsActive();

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

        existingTutor.setUpdatedAt(LocalDateTime.now());
        tutorRepository.save(existingTutor);

        return existingTutor;
    }

    public void deleteTutorByEmail(String email) {
        if (tutorRepository.existsByEmail(email)){
            tutorRepository.deleteByEmail(email);
        } else {
            throw new TutorNotFoundException("Tutor not found for the email:- " + email);
        }
    }

    public List<Tutor> filterTutor(String firstName, String surname, String postcode, String phoneNumber,
                                   Boolean isQualified, Boolean isActive) {
        if (firstName == null && surname == null && postcode == null && phoneNumber == null &&
                isQualified == null && isActive == null){
            return getAllTutors();
        } else {
            return tutorRepository.filterTutors(firstName, surname, postcode, phoneNumber,
                    isQualified, isActive);
        }
    }
}

