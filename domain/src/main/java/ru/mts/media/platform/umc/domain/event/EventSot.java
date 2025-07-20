package ru.mts.media.platform.umc.domain.event;

import java.util.List;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;

public interface EventSot {
    List<Event> getAllWithVenues();

    List<Event> getEventsByVenueExternalId(FullExternalId externalId);

    Event save(Event event);
}
