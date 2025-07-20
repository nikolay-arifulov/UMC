package ru.mts.media.platform.umc.dao.postgres.event;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mts.media.platform.umc.domain.gql.types.Event;

@Mapper(componentModel = SPRING)
public interface EventPgMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "startTime", source = "startTime")
    @Mapping(target = "endTime", source = "endTime")
    @Mapping(target = "venue", ignore = true)
    Event asModel(EventPgEntity eventPg);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "startTime", source = "startTime")
    @Mapping(target = "endTime", source = "endTime")
    @Mapping(target = "venue", ignore = true)
    EventPgEntity asEntity(Event event);
}
