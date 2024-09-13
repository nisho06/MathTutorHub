package com.math.tutor.hub.tutor_management_service.controller;

import com.math.tutor.hub.tutor_management_service.dto.TutorDTO;
import com.math.tutor.hub.tutor_management_service.model.Tutor;
import com.math.tutor.hub.tutor_management_service.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tutors")
public class TutorController {

    private TutorService tutorService;

    @Autowired
    private TutorController(TutorService tutorService){
        this.tutorService = tutorService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTutors(){
        List<TutorDTO> tutorList = tutorService.getAllTutors();
        return new ResponseEntity<>(tutorList, HttpStatus.OK);
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<?> getTutorByEmail(@PathVariable String email){
        TutorDTO tutorDTO = tutorService.getTutorByEmail(email);
        return new ResponseEntity<>(tutorDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{tutorId}")
    public ResponseEntity<?> getTutorByTutorId(@PathVariable int tutorId){
        TutorDTO tutorDTO = tutorService.getTutorByTutorId(tutorId);
        return new ResponseEntity<>(tutorDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registerTutor(@RequestBody TutorDTO tutorDTO){
        TutorDTO createdTutor = tutorService.registerTutor(tutorDTO);
        return new ResponseEntity<>(createdTutor, HttpStatus.CREATED);
    }

    @PutMapping(path = "/email/{email}")
    public ResponseEntity<?> updateTutor(@PathVariable String email, @RequestBody TutorDTO tutorDTO){
        TutorDTO updatedTutorDTO = tutorService.updateTutor(email, tutorDTO);
        return new ResponseEntity<>(updatedTutorDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/email/{email}")
    public ResponseEntity<?> deleteTutorByEmail(@PathVariable String email){
        tutorService.deleteTutorByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<?> filterTutorsByParameters(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String postcode,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) Boolean isQualified,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) Integer tutorYear
            ){
        List<TutorDTO> filteredTutorDTOList = tutorService.filterTutor(firstName, surname, postcode, phoneNumber,
                isQualified, isActive, tutorYear);
        return new ResponseEntity<>(filteredTutorDTOList, HttpStatus.OK);
    }
}
