package ru.mts.media.platform.umc.api.gql.venue;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

@DgsComponent
@RequiredArgsConstructor
public class VenueDataFetcher {
    private final EventSot eventSot;

    @DgsData(parentType = "Venue", field = "event")
    public List<Event> getEvents(DgsDataFetchingEnvironment dfe) {
        Venue venue = dfe.getSource();
        FullExternalId externalId = venue.getExternalId();
        return eventSot.getEventsByVenueExternalId(externalId);
    }
}
