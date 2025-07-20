package ru.mts.media.platform.umc.domain.event;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.Venue;
import ru.mts.media.platform.umc.domain.venue.VenueSot;

@Service
@RequiredArgsConstructor
public class EventDomainService {

    private final ApplicationEventPublisher eventPublisher;
    private final EventSot eventSot;
    private final VenueSot venueSot;
    private final EventDomainServiceMapper mapper;

    public EventSave create(String venueReferenceId, String name, LocalDateTime startTime, LocalDateTime endTime) {
        var venueOptional = venueSot.getVenueByReferenceId(venueReferenceId);

        if (venueOptional.isEmpty()) {
            throw new IllegalArgumentException("Venue not found with referenceId: " + venueReferenceId);
        }

        Venue venue = venueOptional.get();
        Event newEvent = mapper.fromInput(name, startTime, endTime);
        newEvent.setVenue(List.of(venue));

        var savedEvent = eventSot.save(newEvent);
        EventSave eventSave = new EventSave(savedEvent);
        eventPublisher.publishEvent(eventSave);

        return eventSave;
    }
}