package ru.mts.media.platform.umc.dao.postgres.event;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPgRepository extends JpaRepository<EventPgEntity, Long> {

    @Query("SELECT DISTINCT e FROM EventPgEntity e LEFT JOIN FETCH e.venue")
    List<EventPgEntity> findAllWithVenues();

    @Query("""
            select e from EventPgEntity e
            join e.venue v
            where v.id.provider = :providerId
              and v.id.brand = :brandId
              and v.id.externalId = :externalId
        """)
    List<EventPgEntity> findAllByVenueExternalId(
        @Param("providerId") String providerId,
        @Param("brandId") String brandId,
        @Param("externalId") String externalId);
}
