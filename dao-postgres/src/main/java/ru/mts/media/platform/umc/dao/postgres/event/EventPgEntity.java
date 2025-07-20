package ru.mts.media.platform.umc.dao.postgres.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;

@Entity
@Table(name = "event")
@Data
public class EventPgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "event_venue",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = {
            @JoinColumn(name = "brand", referencedColumnName = "brand"),
            @JoinColumn(name = "provider", referencedColumnName = "provider"),
            @JoinColumn(name = "external_id", referencedColumnName = "external_id")
        })
    private Set<VenuePgEntity> venue = new HashSet<>();
}
