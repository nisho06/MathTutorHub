//package com.math.tutor.hub.tutor_management_service.model;
//
//import jakarta.persistence.Embeddable;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class TutorYearsPK implements Serializable {
//    private int tutorId;
//    private int tutoringYear;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o){
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()){
//            return false;
//        }
//        TutorYearsPK that = (TutorYearsPK) o;
//        return tutorId == that.tutorId && tutoringYear == that.tutoringYear;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(tutorId, tutoringYear);
//    }
//
//
//}
