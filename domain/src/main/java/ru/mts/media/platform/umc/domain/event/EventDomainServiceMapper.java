package ru.mts.media.platform.umc.domain.event;

import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mts.media.platform.umc.domain.gql.types.Event;

@Mapper(componentModel = "spring")
public interface EventDomainServiceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "venue", ignore = true) // Связь задается вручную
    Event fromInput(String name, LocalDateTime startTime, LocalDateTime endTime);
}