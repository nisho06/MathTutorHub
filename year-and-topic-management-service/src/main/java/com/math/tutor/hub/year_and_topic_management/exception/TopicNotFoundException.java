package com.math.tutor.hub.year_and_topic_management.exception;

public class TopicNotFoundException extends RuntimeException{

    public TopicNotFoundException(String description){
        super(description);
    }
}
