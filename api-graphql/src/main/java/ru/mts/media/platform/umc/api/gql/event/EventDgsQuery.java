package ru.mts.media.platform.umc.api.gql.event;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;

@DgsComponent
@RequiredArgsConstructor
public class EventDgsQuery {

    private final EventSot sot;

    @DgsQuery
    public List<Event> eventsWithVenues() {
        return sot.getAllWithVenues();
    }
}
