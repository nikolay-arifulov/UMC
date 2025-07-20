package ru.mts.media.platform.umc.dao.postgres.venue;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.mts.media.platform.umc.dao.postgres.common.FullExternalIdPk;
import ru.mts.media.platform.umc.dao.postgres.event.EventPgEntity;

@Entity
@Data
@Table(name = "venue",
    indexes = {
        @Index(name = "idx_venue_referenceId",
            columnList = "referenceId",
            unique = true)
    })
public class VenuePgEntity {
    @EmbeddedId
    private FullExternalIdPk id;

    private String referenceId;

    private String name;

    @ManyToMany(mappedBy = "venue", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<EventPgEntity> event = new HashSet<>();
}
