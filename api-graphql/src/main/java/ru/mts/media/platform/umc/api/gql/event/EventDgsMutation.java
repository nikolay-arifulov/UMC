package ru.mts.media.platform.umc.api.gql.event;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import ru.mts.media.platform.umc.domain.event.EventDomainService;
import ru.mts.media.platform.umc.domain.gql.types.Event;

@DgsComponent
@RequiredArgsConstructor
public class EventDgsMutation {

    private final EventDomainService domainService;

    @DgsMutation
    public Event createEvent(@InputArgument String venueReferenceId,
                             @InputArgument String name,
                             @InputArgument LocalDateTime startTime,
                             @InputArgument LocalDateTime endTime) {
        return domainService.create(venueReferenceId, name, startTime, endTime).getEntity();
    }
}
