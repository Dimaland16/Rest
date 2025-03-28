package com.example.rest.mapper;

import com.example.rest.dto.topic.TopicRequestTo;
import com.example.rest.dto.topic.TopicResponseTo;
import com.example.rest.entity.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    Topic toEntity(TopicRequestTo topicRequestTo);
    TopicResponseTo toResponse(Topic topic);
}