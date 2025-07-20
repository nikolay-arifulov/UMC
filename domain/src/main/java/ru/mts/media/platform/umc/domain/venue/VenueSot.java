package ru.mts.media.platform.umc.domain.venue;

import java.util.List;
import ru.mts.media.platform.umc.domain.gql.types.EventFilterInput;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalIdInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.Optional;

public interface VenueSot {
    Optional<Venue> getVenueByReferenceId(String id);

    Optional<Venue> getVenueById(FullExternalIdInput externalId);

    List<Venue> getVenuesWithEventsFiltered(EventFilterInput filter);

    List<Venue> getVenuesByEventId(Long eventId);
}
