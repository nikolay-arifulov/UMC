package ru.mts.media.platform.umc.dao.postgres.event;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mts.media.platform.umc.dao.postgres.common.FullExternalIdPk;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgRepository;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;

@Component
@RequiredArgsConstructor
public class EventPgDao implements EventSot {
    private final EventPgRepository repository;
    private final VenuePgRepository venueRepository;
    private final EventPgMapper mapper;

    @Override
    public List<Event> getAllWithVenues() {
        return repository.findAllWithVenues()
            .stream()
            .map(mapper::asModel)
            .toList();
    }

    @Override
    public List<Event> getEventsByVenueExternalId(FullExternalId externalId) {
        List<EventPgEntity> entities = repository.findAllByVenueExternalId(
            externalId.getProviderId(),
            externalId.getBrandId(),
            externalId.getExternalId()
        );
        return entities.stream()
            .map(mapper::asModel)
            .toList();
    }

    @Override
    public Event save(Event event) {
        var entity = mapper.asEntity(event);

        Set<VenuePgEntity> venueEntities = event.getVenue().stream()
            .map(v -> venueRepository.findById(new FullExternalIdPk(
                v.getExternalId().getBrandId(),
                v.getExternalId().getProviderId(),
                v.getExternalId().getExternalId()
            )))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());

        entity.setVenue(venueEntities);

        EventPgEntity saved = repository.save(entity);

        return mapper.asModel(saved);
    }
}
