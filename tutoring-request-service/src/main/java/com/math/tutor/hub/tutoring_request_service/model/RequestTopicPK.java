package com.math.tutor.hub.tutoring_request_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestTopicPK implements Serializable {
    @Column(name = "request_id")
    private int requestId;
    @Column(name = "year_topic_id")
    private int yearTopicId;

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        RequestTopicPK that = (RequestTopicPK) o;
        return requestId == that.requestId && yearTopicId == that.yearTopicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, yearTopicId);
    }

}
